package nottheory.donationtracker.Controllers;
import nottheory.donationtracker.Model.Account;
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

/**
 * activity for displaying the donations of a particular location
 * to the user
 */
public class DonationListActivity extends AppCompatActivity {
    
    private TextView errorText;
    private final int DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String fromLocation;
        RecyclerView donationList;
        Button backButton;
        Button addButton;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_list);

        String str = "location";
        Intent currIntent = getIntent();
        fromLocation = currIntent.getStringExtra(str);
        //fix so pushes name of the location picked
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
                Account currAccount = LoginManager.getCurrAccount();
                AccountType currAcctType = currAccount.getAcctType();
                if(currAcctType.equals(AccountType.values()[1])) {
                    Intent i = new Intent(DonationListActivity.this, AddDonationActivity.class);
                    i.putExtra("location", location.getName());
                    startActivity(i);
                } else {
                    errorText.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            errorText.setVisibility(View.INVISIBLE);
                        }
                    }, DELAY);
                }
            }
        });

        donationList = findViewById(R.id.donationList);
        List<Donation> donations = location.getDonations();
        Object donationArray[] = null;
        if(donations == null) {
            donationArray = new Object[0];
            donationList.setAdapter(new DonationListActivity.DonationAdapter(this, donationArray));
        } else {
            donationArray = new Object[donations.size()];
            donationList.setAdapter(new DonationListActivity.DonationAdapter(
                    this, donations.toArray(donationArray)));
        }


        donationList.setLayoutManager(new LinearLayoutManager(this));
    }

    private class DonationAdapter
            extends RecyclerView.Adapter<DonationListActivity.DonationAdapter.DonationViewHolder> {
        private final Object[] donations;
        private final Context context;
        public class DonationViewHolder extends RecyclerView.ViewHolder {
            final Button donation;

            DonationViewHolder(View view) {
                super(view);
                donation = view.findViewById(R.id.location_row_button);
            }
        }

        DonationAdapter(Context context, Object[] donations) {
            this.donations = donations;
            this.context = context;
        }

        @Override
        public DonationListActivity.DonationAdapter.DonationViewHolder onCreateViewHolder(
                ViewGroup parent, int viewType) {
            LayoutInflater currLayoutInflator = LayoutInflater.from(context);
            View view = currLayoutInflator.inflate(
                    R.layout.location_recyclerview_row, parent, false);
            return new DonationListActivity.DonationAdapter.DonationViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final DonationListActivity.
                DonationAdapter.DonationViewHolder viewHolder, int position) {
            viewHolder.donation.setText(donations[position].toString());
            viewHolder.donation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(
                            DonationListActivity.this, DonationInfoActivity.class);
                    intent.putExtra("dpos", viewHolder.getAdapterPosition());
                    intent.putExtra("donation", (
                            (Donation) donations[viewHolder.getAdapterPosition()]).getName());
                    Intent lastIntent = getIntent();
                    intent.putExtra("location", lastIntent.getStringExtra("location"));
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return donations.length;
        }
    }
}
