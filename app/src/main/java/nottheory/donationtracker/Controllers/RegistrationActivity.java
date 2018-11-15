package nottheory.donationtracker.Controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
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

/**
 * The activity shown to register a new user
 */
public class RegistrationActivity extends AppCompatActivity {

    private EditText nameEntry;
    private EditText emailEntry;
    private EditText unEntry;
    private EditText pwEntry;
    private Spinner acctTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button loginButton;
        Button cancelButton;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        nameEntry = findViewById(R.id.register_nameentry);
        emailEntry = findViewById(R.id.register_emailentry);
        unEntry = findViewById(R.id.register_unentry);
        pwEntry = findViewById(R.id.register_pwentry);
        loginButton = findViewById(R.id.register_loginbutton);
        cancelButton = findViewById(R.id.register_cancelbutton);
        acctTypeSpinner = findViewById(R.id.registration_accttypespinner);

        acctTypeSpinner.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, AccountType.values()));

        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable nameEdit = nameEntry.getText();
                String name = nameEdit.toString(); //this is the most concise way
                Editable emailEdit = emailEntry.getText();
                String email = emailEdit.toString();
                Editable unEdit = unEntry.getText();
                String un = unEdit.toString();
                Editable pwEdit = pwEntry.getText();
                String pw = pwEdit.toString();
                AccountType acctType = (AccountType) acctTypeSpinner.getSelectedItem();

                if (!"".equals(name) && !"".equals(email) && !"".equals(un) && !"".equals(pw)) {
                    boolean goodCheck = LoginManager.addCredentials(un,
                            new Account(name, un, pw, email, acctType));
                    if (goodCheck) {
                        startActivity(new Intent(RegistrationActivity.this, WelcomeActivity.class));
                    }
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
