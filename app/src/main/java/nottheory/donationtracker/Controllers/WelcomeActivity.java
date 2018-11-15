package nottheory.donationtracker.Controllers;

import android.content.Intent;
import android.content.res.Resources;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

import java.io.IOException;

import nottheory.donationtracker.Model.CSVReader;
import nottheory.donationtracker.R;
import nottheory.donationtracker.Model.LoginManager;

/**
 * The Activity shown which leads to login and registration
 */
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button loginButton;
        Button registerButton;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        StrictMode.ThreadPolicy.Builder policyBuilder = new StrictMode.ThreadPolicy.Builder();
        StrictMode.ThreadPolicy.Builder permitAll = policyBuilder.permitAll();
        StrictMode.ThreadPolicy policy = permitAll.build();
        //uses builder design pattern so chained calls are ok
        StrictMode.setThreadPolicy(policy);
        loginButton = findViewById(R.id.welcome_loginbutton);
        registerButton = findViewById(R.id.welcome_registerbutton);

        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
            }
        });

        LoginManager.initialize_tables();
        CSVReader aReader = new CSVReader();
        try {
            Resources theseResources = getResources();
            aReader.readFile(theseResources.openRawResource(R.raw.locationdata));
            LoginManager.setLocations(aReader.getLC());
        } catch(IOException e) {
            return;
        }

        registerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, RegistrationActivity.class));
            }
        });
    }
}
