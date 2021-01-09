package com.example.project2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainActivity extends Activity {

    //Custom Flag for intent Extra
    protected  static final String IMAGE_ID = "POS";

    //Stores all the website url's for every manufacturer
    public HashMap<Integer, String> carWebsites = new HashMap<Integer, String>(){{
        put(R.drawable.focus, "https://www.ford.com/");
        put(R.drawable.outback, "https://www.subaru.com/vehicles/outback/index.html");
        put(R.drawable.civic, "https://automobiles.honda.com/civic");
        put(R.drawable.mercedes, "https://www.mbusa.com/en/vehicles/class/c-class/sedan");
        put(R.drawable.sienna, "https://www.toyota.com/sienna/");
        put(R.drawable.outlander, "https://www.mitsubishicars.com/outlander-sport/2020");
    }};

    //Stores the image Id's
    private ArrayList<Integer> carIDs = new ArrayList<Integer>(
            Arrays.asList(R.drawable.focus, R.drawable.outback,
                    R.drawable.sienna, R.drawable.mercedes, R.drawable.civic,
                    R.drawable.outlander));

    public GridView carMainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carMainView = (GridView) findViewById(R.id.carMainView);

        //Create custom adapter
        carMainView.setAdapter(new ImageAdapter(this, carIDs));

        //Assign context menu to the proper layout
        registerForContextMenu(carMainView);

        //Short click
        carMainView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                //If image is short clicked then start image view
                Intent intentOne = new Intent(MainActivity.this, ImageViewActivity.class);
                intentOne.putExtra(IMAGE_ID, (int) id);
                startActivity(intentOne);
            }
        });
    }

    //Create context menu for long click
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");

        //Add context menu options
        menu.add(0, v.getId(), 0, "Image View");
        menu.add(0, v.getId(), 0, "Dealership View");
        menu.add(0, v.getId(), 0, "Web Search");

    }

    //Define actions taken by each context menu option
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        //Get image id from the adapter
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int a = (int) info.id; //Used for the URL hash map as key

        //Go to image view activity
        if (item.getTitle() == "Image View") {
            Intent intent = new Intent(MainActivity.this, ImageViewActivity.class);

            intent.putExtra(IMAGE_ID, (int) info.id);

            startActivity(intent);
        }
        //Go to list view activity
        else if (item.getTitle() == "Dealership View") {

            Intent intentTwo = new Intent(MainActivity.this, ListViewActivity.class);
            intentTwo.putExtra(IMAGE_ID, (int) info.id);
            startActivity(intentTwo);
        }
        //Go to official car brand website
        else if (item.getTitle() == "Web Search") {

            Uri carSite = Uri.parse(carWebsites.get(a));
            Intent webSiteIntent = new Intent(Intent.ACTION_VIEW, carSite);
            if (webSiteIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(webSiteIntent);
            }
        }
        else {
            return  false;
        }
        return true;
    }

}