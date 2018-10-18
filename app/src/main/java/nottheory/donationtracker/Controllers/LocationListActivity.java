package nottheory.donationtracker.Controllers;
import nottheory.donationtracker.Model.CSVReader;
import java.io.InputStream;
import nottheory.donationtracker.R;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import nottheory.donationtracker.R;

public class LocationListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);
    }

    protected void pushData() {
        InputStream inputStream = getResources().openRawResource(R.raw.locationdata);
        try {
            CSVReader acsvreader = new CSVReader(inputStream);
            while () {

            }
        } catch {
            return;
        }

    }
}
