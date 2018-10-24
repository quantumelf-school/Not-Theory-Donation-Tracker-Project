package nottheory.donationtracker.Controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

import java.io.IOException;

import nottheory.donationtracker.Model.CSVReader;
import nottheory.donationtracker.Model.LocationCollection;
import nottheory.donationtracker.R;
import nottheory.donationtracker.Model.LoginManager;

public class WelcomeActivity extends AppCompatActivity {
    private Button loginButton, registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        loginButton = findViewById(R.id.welcome_loginbutton);
        registerButton = findViewById(R.id.welcome_registerbutton);
        try {
            CSVReader reader = new CSVReader(getResources().openRawResource(R.raw.locationdata));
            LoginManager.locations = new LocationCollection(reader);
        } catch(IOException e) {
            System.out.println("IOException, csv file cannot be read");
            return;
        }

        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
            }
        });

        registerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, RegistrationActivity.class));
            }
        });
    }
}
