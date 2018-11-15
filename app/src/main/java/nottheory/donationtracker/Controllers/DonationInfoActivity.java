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
    private Button backButton;
    private TextView donationText;
    Intent intent = getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
//        if(getIntent().getStringExtra("is master").equals("t")) {
//            text = LoginManager.donations.getDonations().get(getIntent().getIntExtra("dpos", 0)).toString();
//        } else {
        Location l = LoginManager.locations.getLocationByName(intent.getStringExtra("location"));
        text = l.getDonationCollection().getDonationByName(intent.getStringExtra("donation")).toString();
        //text = l.getDonations().get(getIntent().getIntExtra("dpos", 0)).toString();
        donationText.setText(text);
        donationText.setVisibility(View.VISIBLE);
    }
}
