package nottheory.donationtracker.Controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nottheory.donationtracker.Model.Donation;
import nottheory.donationtracker.Model.DonationCollection;
import nottheory.donationtracker.Model.Location;
import nottheory.donationtracker.Model.LocationCollection;
import nottheory.donationtracker.Model.LoginManager;
import nottheory.donationtracker.R;

public class DonationSearchActivity extends AppCompatActivity {
    private Switch searchByCat;
    private TextView searchBox;
    private RecyclerView donationSearchList;
    private Spinner catSpinner;
    private Spinner locSpinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Button searchButton;
        Button backButton;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_search);

        searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSearch();
            }
        });

        backButton = findViewById(R.id.search_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        searchBox = findViewById(R.id.search_entry);
        searchBox.setVisibility(View.VISIBLE);

        locSpinner = findViewById(R.id.search_location_spinner);
        ArrayList<String> locationList = new ArrayList<>();
        locationList.add("All");
        locationList.addAll(LoginManager.locations.getLocationNames());
        locSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                locationList));

        catSpinner = findViewById(R.id.search_cat_spinner);
//        TODO: remove this hardcoded cat. list
        ArrayList<String> categoryList = new ArrayList<String>();
        categoryList.add("Clothing");
        categoryList.add("Hat");
        categoryList.add("Kitchen");
        categoryList.add("Electronics");
        categoryList.add("Household");
        categoryList.add("Other");
        catSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                categoryList));
        catSpinner.setVisibility(View.GONE);

        searchByCat = findViewById(R.id.search_switch);
        searchByCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchByCat.isChecked()) {
                    catSpinner.setVisibility(View.VISIBLE);
                    searchBox.setVisibility(View.GONE);
                } else {
                    catSpinner.setVisibility(View.GONE);
                    searchBox.setVisibility(View.VISIBLE);
                }
            }
        });

        donationSearchList = findViewById(R.id.donationSearchList);
        List<Donation> donationArray = LoginManager.locations.getAllDonationsAL();
        donationSearchList.setAdapter(new DonationSearchActivity.DonationAdapter(this,
                donationArray.toArray()));
        donationSearchList.setLayoutManager(new LinearLayoutManager(this));
    }

    private class DonationAdapter extends RecyclerView.Adapter
            <DonationSearchActivity.DonationAdapter.DonationViewHolder> {
        private Object[] donations;
        private Context context;
        public class DonationViewHolder extends RecyclerView.ViewHolder {
            Button donation;

            DonationViewHolder(View view) {
                super(view);
                donation = view.findViewById(R.id.location_row_button);
            }
        }

        DonationAdapter(Context context, Object[] donations) {
            this.donations = donations;
            this.context = context;
        }

        public DonationSearchActivity.DonationAdapter.DonationViewHolder onCreateViewHolder(
                ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.location_recyclerview_row,
                    parent, false);
            return new DonationSearchActivity.DonationAdapter.DonationViewHolder(view);
        }

        public void onBindViewHolder(DonationSearchActivity.DonationAdapter.
                                             DonationViewHolder viewHolder, final int position) {
            viewHolder.donation.setText(donations[position].toString());
            viewHolder.donation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DonationSearchActivity.this,
                            DonationInfoActivity.class);
                    intent.putExtra("donation", ((Donation) donations[position]).getName());
                    //feature envy is necessary to tell next activity which donation was clicked

                    Location l = LoginManager.locations.getLocationWithDonation(
                            (Donation) donations[position]);
                    intent.putExtra("location", l.getName());
                    startActivity(intent);
                }
            });
        }

        public int getItemCount() {
            return donations.length;
        }
    }

    private void doSearch() {
        ArrayList<Location> locationList  = LoginManager.locations.getLocations();
        ArrayList<Donation> donationList = new ArrayList<>();
        Object locSelected = locSpinner.getSelectedItem();
        if ((locSelected.toString().equals("All")) ||
                (locSelected.toString().equals(""))) {
            for (Location l : locationList) {
                donationList.addAll(l.getDonations());
            }
        } else {
            String locationString = locSelected.toString();
            LocationCollection locationCol = new LocationCollection(locationList);
            Location location = locationCol.getLocationByName(locationString);
            if (location != null) {
                donationList.addAll(location.getDonations());
            }
        }
        DonationCollection donations = new DonationCollection(donationList);
        Object catSelected = catSpinner.getSelectedItem();
        if (searchByCat.isChecked()) {
            donationList = donations.getDonationsByCategory(
                    catSelected.toString());
        } else {
            CharSequence searchText = searchBox.getText();
            donationList = donations.getDonationsBySimilarName(searchText.toString());
        }
        donationSearchList.setAdapter(new DonationSearchActivity.DonationAdapter(this,
                donationList.toArray()));
    }
}
