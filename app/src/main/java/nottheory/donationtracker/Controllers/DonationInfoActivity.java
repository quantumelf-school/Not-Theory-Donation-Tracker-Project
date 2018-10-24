package nottheory.donationtracker.Controllers;

import nottheory.donationtracker.R;
import nottheory.donationtracker.Model.LoginManager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DonationInfoActivity extends AppCompatActivity {
    private Button backButton;
    private TextView donationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_info);

        backButton = findViewById(R.id.donationinfo_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DonationInfoActivity.this, DonationListActivity.class));
            }
        });

        donationText = findViewById(R.id.donationinfo_info_text);
        String text = LoginManager.locations.getLocationFromRow(getIntent().getIntExtra("pos", 1)).getDonations().get(getIntent().getIntExtra("dpos", 0)).toString();

        donationText.setText(text);
        donationText.setVisibility(View.VISIBLE);
    }
}
