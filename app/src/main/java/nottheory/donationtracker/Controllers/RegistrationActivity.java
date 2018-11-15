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

//import com.google.firebase.firestore.FirebaseFirestore;

import nottheory.donationtracker.Model.Account;
import nottheory.donationtracker.Model.AccountType;
import nottheory.donationtracker.Model.LoginManager;
import nottheory.donationtracker.R;

public class RegistrationActivity extends AppCompatActivity {

    private EditText nameEntry, emailEntry, unEntry, pwEntry;
    private Spinner acctTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button loginButton, cancelButton;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        nameEntry = findViewById(R.id.register_nameentry);
        emailEntry = findViewById(R.id.register_emailentry);
        unEntry = findViewById(R.id.register_unentry);
        pwEntry = findViewById(R.id.register_pwentry);
        loginButton = findViewById(R.id.register_loginbutton);
        cancelButton = findViewById(R.id.register_cancelbutton);
        acctTypeSpinner = findViewById(R.id.registration_accttypespinner);

        acctTypeSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, AccountType.values()));

        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEntry.getText().toString(); //this is the most concise way
                String email = emailEntry.getText().toString();
                String un = unEntry.getText().toString();
                String pw = pwEntry.getText().toString();
                AccountType acctType = (AccountType) acctTypeSpinner.getSelectedItem();

                //TODO: Implement this logic through try, catch and exception in login class
                //TODO: Add a message for invalid credentials
                if (!"".equals(name) && !"".equals(email) && !"".equals(un) && !"".equals(pw)) {

                    LoginManager.addCredentials(un, new Account(name, email, un, pw, acctType));
                    startActivity(new Intent(RegistrationActivity.this, WelcomeActivity.class));

                }
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
