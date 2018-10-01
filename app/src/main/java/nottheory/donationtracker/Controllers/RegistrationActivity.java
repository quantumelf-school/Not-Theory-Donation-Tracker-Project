package nottheory.donationtracker.Controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.Spinner;

import nottheory.donationtracker.Model.Account;
import nottheory.donationtracker.Model.AccountType;
import nottheory.donationtracker.Model.LoginManager;
import nottheory.donationtracker.R;

public class RegistrationActivity extends AppCompatActivity {

    private EditText unEntry, pwEntry;
    private Button loginButton, cancelButton;
    private Spinner acctTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        unEntry = findViewById(R.id.register_unentry);
        pwEntry = findViewById(R.id.register_pwentry);
        loginButton = findViewById(R.id.register_loginbutton);
        cancelButton = findViewById(R.id.register_cancelbutton);
        acctTypeSpinner = findViewById(R.id.registration_accttypespinner);

        acctTypeSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, AccountType.values()));

        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               String un = unEntry.getText().toString();
               String pw = pwEntry.getText().toString();
               AccountType acctType = (AccountType) acctTypeSpinner.getSelectedItem();

                LoginManager.addCredentials(un, new Account(un, pw, acctType));
                startActivity(new Intent(RegistrationActivity.this, WelcomeActivity.class));
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
