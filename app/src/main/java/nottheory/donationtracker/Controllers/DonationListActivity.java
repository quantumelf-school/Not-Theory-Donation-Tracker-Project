package nottheory.donationtracker.Controllers;
import nottheory.donationtracker.Model.AccountType;
import nottheory.donationtracker.Model.Donation;
import nottheory.donationtracker.Model.Location;
import nottheory.donationtracker.R;
import nottheory.donationtracker.Model.LoginManager;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class DonationListActivity extends AppCompatActivity {

    private String fromLocation;
    private RecyclerView donationList;
//    private EditText searchBar;
//    private Button searchButton;
    private Button backButton;
    private Button addButton;
//    private Spinner searchCriteria;
    private TextView errorText;
    final int DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_list);

        String str = "location";
        fromLocation = getIntent().getStringExtra(str); //fix so that this pushes the name of the location picked
        backButton = findViewById(R.id.donationlist_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(DonationListActivity.this, LocationInfoActivity.class);
                //i.putExtra("pos", getIntent().getIntExtra("pos", 0));
                //startActivity(i);
                finish();
            }
        });
        final Location location = LoginManager.locations.getLocationByName(fromLocation);
        addButton = findViewById(R.id.donationlist_add_button);
        errorText = findViewById(R.id.donationlist_error_text);
        errorText.setVisibility(View.INVISIBLE);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LoginManager.getCurrAccount().getAcctType().equals(AccountType.values()[1])) {
                    Intent i = new Intent(DonationListActivity.this, AddDonationActivity.class);
                    i.putExtra("location", location.getName());
                    startActivity(i);
                } else {
                    errorText.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            errorText.setVisibility(View.INVISIBLE);
                        }
                    }, DELAY);
                }
            }
        });

        donationList = findViewById(R.id.donationList);
        //ArrayList<Donation> donationArray =
        //ArrayList<Donation> donationArray = LoginManager.locations.getLocationFromRow(getIntent().getIntExtra("pos", 1)).getDonations();
        //donationList.setAdapter(new DonationListActivity.DonationAdapter(this, donationArray.toArray()
        List<Donation> donations = location.getDonations();
        Object donationArray[] = null;
        if(donations == null) {
            donationArray = new Object[0];
            donationList.setAdapter(new DonationListActivity.DonationAdapter(this, donationArray));
        } else {
            donationArray = new Object[donations.size()];
            donationList.setAdapter(new DonationListActivity.DonationAdapter(this, donations.toArray(donationArray)));
        }


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
                    intent.putExtra("donation", ((Donation) donations[position]).getName());
                    intent.putExtra("location", getIntent().getStringExtra("location"));
                    startActivity(intent);
                }
            });
        }

        public int getItemCount() {
            return donations.length;
        }
    }
}
