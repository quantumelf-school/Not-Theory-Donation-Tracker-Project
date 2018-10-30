package nottheory.donationtracker.Controllers;
import nottheory.donationtracker.Model.CSVReader;

import java.io.IOException;
import java.io.InputStream;

import nottheory.donationtracker.Model.LocationCollection;
import nottheory.donationtracker.R;
import nottheory.donationtracker.Controllers.WelcomeActivity;
import nottheory.donationtracker.Model.LoginManager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.support.v7.widget.LinearLayoutManager;



public class LocationListActivity extends AppCompatActivity {

    private RecyclerView locationList;
    private Button backButton;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        backButton = findViewById(R.id.locationlist_back_button);
        backButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LocationListActivity.this, SuccessfulLoginActivity.class));
            }
        });

        searchButton = findViewById(R.id.locationlist_search_button);
        searchButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LocationListActivity.this, DonationSearchActivity.class));
            }
        });

        locationList = findViewById(R.id.locationList);
        String[] locationArray = LoginManager.locations.getLocationNames().toArray(new String[LoginManager.locations.getNumLocations()]);
        locationList.setAdapter(new LocationAdapter(this, locationArray));
        locationList.setLayoutManager(new LinearLayoutManager(this));
    }

    private class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {
        private String[] locations;
        private Context context;
        public class LocationViewHolder extends RecyclerView.ViewHolder {
            public Button location;

            public LocationViewHolder(View view) {
                super(view);
                location = view.findViewById(R.id.location_row_button);
            }
        }

        public LocationAdapter(Context context, String[] locations) {
            this.locations = locations;
            this.context = context;
        }

        public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.location_recyclerview_row, parent, false);
            return new LocationViewHolder(view);
        }

        public void onBindViewHolder(LocationViewHolder viewHolder, final int position) {
            viewHolder.location.setText(locations[position]);
            viewHolder.location.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                   Intent intent = new Intent(LocationListActivity.this, LocationInfoActivity.class);
                   intent.putExtra("pos", position + 1);//+1 b/c array has position 0 is the first non key row
                   startActivity(intent);
                }
            });
        }

        public int getItemCount() {
            return locations.length;
        }
    }

//    protected void pushData() {
//        InputStream inputStream = getResources().openRawResource(R.raw.locationdata);
//        try {
//            CSVReader acsvreader = new CSVReader(inputStream);
//            while () {
//
//            }
//        } catch(IOException e){
//            System.out.println("IOException. .csv file could not be read");
//        }
//
//    }
}
