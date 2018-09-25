package nottheory.donationtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;

public class RegistrationActivity extends AppCompatActivity {

    private EditText unEntry, pwEntry;
    private Button loginButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        unEntry = findViewById(R.id.register_unentry);
        pwEntry = findViewById(R.id.register_pwentry);
        loginButton = findViewById(R.id.register_loginbutton);
        cancelButton = findViewById(R.id.register_cancelbutton);

        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Implement registration behavior
            }
        });

        cancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, WelcomeActivity.class));
            }
        });

    }
}
