package com.example.project2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import java.util.HashMap;

public class ImageViewActivity extends Activity {

    public Intent intent;

    //This hash map is used to determine witch URL will be search for using the devices browser
    //The key is the id of the picture that was pressed
    public HashMap<Integer, String> carWebsites = new HashMap<Integer, String>(){{
        put(R.drawable.focus, "https://www.ford.com/");
        put(R.drawable.outback, "https://www.subaru.com/vehicles/outback/index.html");
        put(R.drawable.civic, "https://automobiles.honda.com/civic");
        put(R.drawable.mercedes, "https://www.mbusa.com/en/vehicles/class/c-class/sedan");
        put(R.drawable.sienna, "https://www.toyota.com/sienna/");
        put(R.drawable.outlander, "https://www.mitsubishicars.com/outlander-sport/2020");
    }};

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        //Get intent extra that was passed from main activity
        intent = getIntent();
        ImageView carView = (ImageView) findViewById(R.id.carView);

        //Set the image that will be displayed as the image that was pressed
        carView.setImageResource(intent.getIntExtra(MainActivity.IMAGE_ID, 0));

    }

    //If the user presses on the picture displayed then a browser search will be initialized
    public void goToWebsite(View v) {
        //Get the url for the correct website for the car image
        Uri carSite = Uri.parse(carWebsites.get(intent.getIntExtra(MainActivity.IMAGE_ID, 0)));

        //Perform an implicit intent to find the devices default browser
        Intent webSiteIntent = new Intent(Intent.ACTION_VIEW, carSite);

        if (webSiteIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(webSiteIntent);
        }
    }

    //When the back pressed key is used destroy the activity
    @Override
    public void onBackPressed(){
        finish();
    }

}
