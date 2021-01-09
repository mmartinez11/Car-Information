package com.example.project2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.HashMap;

@SuppressWarnings( "deprecation" )

public class ListViewActivity extends ListActivity {

    //This hash map will determine witch string array will be shown in the List View
    //The key will be the picture id that is passed by the main activity
    public HashMap<Integer, String> carLocations = new HashMap<Integer, String>(){{
        put(R.drawable.focus, "focus");
        put(R.drawable.outback, "outback");
        put(R.drawable.civic, "civic");
        put(R.drawable.mercedes, "mercedes");
        put(R.drawable.sienna, "sienna");
        put(R.drawable.outlander, "outlander");
    }};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get the intent extra from the main activity
        Intent intent = getIntent();

        //Different List views will be shown based on the picture that was pressed on the cell grid
        if(carLocations.get(intent.getIntExtra(MainActivity.IMAGE_ID, 0)) == "focus") {
            setListAdapter(new ArrayAdapter<String>(this, R.layout.actvity_list, getResources().getStringArray(R.array.focus)));
        }
        else if(carLocations.get(intent.getIntExtra(MainActivity.IMAGE_ID, 0)) == "outback") {
            setListAdapter(new ArrayAdapter<String>(this, R.layout.actvity_list, getResources().getStringArray(R.array.outback)));
        }
        else if(carLocations.get(intent.getIntExtra(MainActivity.IMAGE_ID, 0)) == "civic") {
            setListAdapter(new ArrayAdapter<String>(this, R.layout.actvity_list, getResources().getStringArray(R.array.civic)));
        }
        else if(carLocations.get(intent.getIntExtra(MainActivity.IMAGE_ID, 0)) == "mercedes") {
            setListAdapter(new ArrayAdapter<String>(this, R.layout.actvity_list, getResources().getStringArray(R.array.mercedes)));
        }
        else if(carLocations.get(intent.getIntExtra(MainActivity.IMAGE_ID, 0)) == "sienna") {
            setListAdapter(new ArrayAdapter<String>(this, R.layout.actvity_list, getResources().getStringArray(R.array.sienna)));
        }
        else if(carLocations.get(intent.getIntExtra(MainActivity.IMAGE_ID, 0)) == "outlander") {
            setListAdapter(new ArrayAdapter<String>(this, R.layout.actvity_list, getResources().getStringArray(R.array.outlander)));
        }

        ListView clv = getListView();
        clv.setTextFilterEnabled(true);

    }
}
