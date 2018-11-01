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

import nottheory.donationtracker.Model.AccountType;
import nottheory.donationtracker.Model.Location;
import nottheory.donationtracker.Model.LoginManager;
import nottheory.donationtracker.Model.LocationCollection;
import nottheory.donationtracker.R;

public class LocationInfoActivity extends AppCompatActivity {

    private TextView locationText;
    private Button backButton;
    private Button donationButton;
    private Intent locationIntent;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_info);


        location = LoginManager.locations.getLocationByName(getIntent().getStringExtra("location"));
        backButton = findViewById(R.id.locationinfo_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(LocationInfoActivity.this, LocationListActivity.class));
            }
        });

        donationButton = findViewById(R.id.locationinfo_donation_button);
        donationButton.setVisibility(View.VISIBLE);
        donationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                locationIntent = new Intent(LocationInfoActivity.this, DonationListActivity.class);
                String key = "location";
                locationIntent.putExtra(key, location.getName());
                locationIntent.putExtra("pos", getIntent().getIntExtra("pos", 0));
                startActivity(locationIntent);
            }
        });


//        if (LoginManager.getCurrAccount().getAcctType().equals(AccountType.values()[1])) {
//            donationButton.setVisibility(View.VISIBLE);
//        }

        locationText = findViewById(R.id.locationinfo_info_text);
        String text = LoginManager.locations.getLocationFromRow(getIntent().getIntExtra("pos", 0)).toString();

        locationText.setText(text);
        locationText.setVisibility(View.VISIBLE);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

    }

}
