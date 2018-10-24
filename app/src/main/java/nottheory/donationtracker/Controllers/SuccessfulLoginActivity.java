package nottheory.donationtracker.Controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

import nottheory.donationtracker.Model.LoginManager;
import nottheory.donationtracker.R;

public class SuccessfulLoginActivity extends AppCompatActivity {

    Button logoutButton;
    Button importButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_login);
        logoutButton = findViewById(R.id.sucessfullogin_logout_button);
        importButton = findViewById(R.id.successfullogin_import_button);

        logoutButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.logoutAccount();
                startActivity(new Intent(SuccessfulLoginActivity.this, WelcomeActivity.class));
            }
        });

        importButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SuccessfulLoginActivity.this, LocationListActivity.class));
            }
        });
    }
}
