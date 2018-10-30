package nottheory.donationtracker.Controllers;
import nottheory.donationtracker.Model.AccountType;
import nottheory.donationtracker.Model.CSVReader;
import nottheory.donationtracker.Model.Donation;
import nottheory.donationtracker.Model.LocationCollection;
import nottheory.donationtracker.R;
import nottheory.donationtracker.Model.LoginManager;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;

public class DonationListActivity extends AppCompatActivity {


    private RecyclerView donationList;
    private EditText searchBar;
    private Button searchButton;
    private Button backButton;
    private Button addButton;
    private Spinner searchCriteria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_list);
        searchBar = findViewById(R.id.donationlist_searchbar_input);
        searchButton = findViewById(R.id.donationlist_search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = searchBar.getText().toString();
                //Tyler this is where the code to put the stuff into the recycler view is
            }
        });
        searchCriteria = findViewById(R.id.donationlist_search_criteria);
        String[] searchCrits = {"Name", "Category"};

        searchCriteria.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, searchCrits));

        backButton = findViewById(R.id.donationlist_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DonationListActivity.this, LocationInfoActivity.class));
            }
        });
        addButton = findViewById(R.id.donationlist_add_button);
        if(LoginManager.getCurrAccount().getAcctType().equals(AccountType.values()[1])) {
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(DonationListActivity.this, AddDonationActivity.class));
                }
            });
        }
        donationList = findViewById(R.id.donationList);
        ArrayList<Donation> donationArray = LoginManager.locations.getLocationFromRow(getIntent().getIntExtra("pos", 1)).getDonations();
        donationList.setAdapter(new DonationListActivity.DonationAdapter(this, donationArray.toArray()));
        donationList.setLayoutManager(new LinearLayoutManager(this));
    }

    private class DonationAdapter extends RecyclerView.Adapter<DonationListActivity.DonationAdapter.DonationViewHolder> {
        private Object[] donations;
        private Context context;
        public class DonationViewHolder extends RecyclerView.ViewHolder {
            public Button donation;

            public DonationViewHolder(View view) {
                super(view);
                donation = view.findViewById(R.id.location_row_button);
            }
        }

        public DonationAdapter(Context context, Object[] donations) {
            this.donations = donations;
            this.context = context;
        }

        public DonationListActivity.DonationAdapter.DonationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.location_recyclerview_row, parent, false);
            return new DonationListActivity.DonationAdapter.DonationViewHolder(view);
        }

        public void onBindViewHolder(DonationListActivity.DonationAdapter.DonationViewHolder viewHolder, final int position) {
            viewHolder.donation.setText(donations[position].toString());
            viewHolder.donation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DonationListActivity.this, DonationInfoActivity.class);
                    intent.putExtra("dpos", position);
                    startActivity(intent);
                }
            });
        }

        public int getItemCount() {
            return donations.length;
        }
    }
}
