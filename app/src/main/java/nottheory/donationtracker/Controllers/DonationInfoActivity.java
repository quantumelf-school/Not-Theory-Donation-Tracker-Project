package nottheory.donationtracker.Controllers;

import nottheory.donationtracker.Model.Location;
import nottheory.donationtracker.R;
import nottheory.donationtracker.Model.LoginManager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DonationInfoActivity extends AppCompatActivity {
    private final Intent intent = getIntent();

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
        Location l = LoginManager.locations.getLocationByName(intent.getStringExtra("location"));
        text = l.getDonationCollection().getDonationByName(
                intent.getStringExtra("donation")).toString();
        donationText.setText(text);
        donationText.setVisibility(View.VISIBLE);
    }
}
