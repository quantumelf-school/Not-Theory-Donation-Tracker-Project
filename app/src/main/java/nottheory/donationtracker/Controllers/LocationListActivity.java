package nottheory.donationtracker.Controllers;
import nottheory.donationtracker.Model.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import nottheory.donationtracker.R;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.support.v7.widget.LinearLayoutManager;

import nottheory.donationtracker.R;

public class LocationListActivity extends AppCompatActivity {

    private RecyclerView locationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);
        try{
            CSVReader reader = new CSVReader(getResources().openRawResource(R.raw.locationdata));
            locationList = findViewById(R.id.locationList);
            String[] locationArray = new String[reader.size()];
            for (int i = 1; i <= reader.size(); i++) {
                locationArray[i - 1] = reader.getData(i, 1);
            }
            locationList.setAdapter(new LocationAdapter(this, locationArray));
            locationList.setLayoutManager(new LinearLayoutManager(this));
        } catch(IOException e) {
            System.out.println("IOException, csv file cannot be read");
            return;
        }
    }

    private class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {
        private String[] locations;
        private Context context;
        public class LocationViewHolder extends RecyclerView.ViewHolder {
            public TextView location;

            public LocationViewHolder(TextView location) {
                super(location);
                this.location = location;
            }
        }

        public LocationAdapter(Context context, String[] locations) {
            this.locations = locations;
            this.context = context;
        }

        public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView view = (TextView) LayoutInflater.from(context).inflate(R.layout.location_recyclerview_row, parent, false);
            return new LocationViewHolder(view);
        }

        public void onBindViewHolder(LocationViewHolder viewHolder, int position) {
            viewHolder.location.setText(locations[position]);
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
