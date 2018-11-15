package nottheory.donationtracker.Controllers;

import nottheory.donationtracker.Model.Donation;
import nottheory.donationtracker.Model.DonationCollection;
import nottheory.donationtracker.Model.Location;
import nottheory.donationtracker.R;
import nottheory.donationtracker.Model.LoginManager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * activity giving information about a particular donation to the user
 */
public class DonationInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button backButton;
        TextView donationText;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_info);

        backButton = findViewById(R.id.donationinfo_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        donationText = findViewById(R.id.donationinfo_info_text);
        String text = "";
        final Intent intent = getIntent();
        Location l = LoginManager.getLocations().getLocationByName(intent.getStringExtra("location"));
        DonationCollection gottenCollection = l.getDonationCollection();
        Donation foundDonation = gottenCollection.getDonationByName(
                intent.getStringExtra("donation"));
        text = foundDonation.toString();
        donationText.setText(text);
        donationText.setVisibility(View.VISIBLE);
    }
}
