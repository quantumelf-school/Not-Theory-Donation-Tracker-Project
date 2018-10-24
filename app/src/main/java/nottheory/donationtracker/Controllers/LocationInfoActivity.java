package nottheory.donationtracker.Controllers;

import java.io.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import nottheory.donationtracker.Model.CSVReader;

import nottheory.donationtracker.Model.LocationCollection;
import nottheory.donationtracker.R;

public class LocationInfoActivity extends AppCompatActivity {

    private TextView locationText;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_info);

        backButton = findViewById(R.id.locationinfo_back_button);
        backButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LocationInfoActivity.this, LocationListActivity.class));
            }
        });

        locationText = findViewById(R.id.locationinfo_info_text);
        CSVReader reader;
        try {
            reader = new CSVReader(getResources().openRawResource(R.raw.locationdata));
            LocationCollection locations = new LocationCollection(reader);
            String text = locations.getLocationFromRow(getIntent().getIntExtra("pos", 1)).toString();

            locationText.setText(text);
            locationText.setVisibility(View.VISIBLE);
        } catch(IOException e) {
            System.out.println("IOException. The .csv file could not be read");
        }

    }

}
