package nottheory.donationtracker.Controllers;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.TextView;

import nottheory.donationtracker.Model.LoginManager;
import nottheory.donationtracker.R;

/**
 * The activity shown to allow users to log in
 */
public class LoginActivity extends AppCompatActivity {

    private EditText unEntry;
    private EditText pwEntry;
    private TextView invalidLoginText;
    private TextView tryAgainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button loginButton;
        Button cancelButton;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unEntry = findViewById(R.id.login_unentry);
        pwEntry = findViewById(R.id.login_pwentry);
        invalidLoginText = findViewById(R.id.login_invalidlogintext);
        tryAgainText = findViewById(R.id.login_tryagaintext);
        loginButton = findViewById(R.id.login_loginbutton);
        cancelButton = findViewById(R.id.login_cancelbutton);

        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable unEdit = unEntry.getText();
                String un = unEdit.toString(); //most concise way to do this
                Editable pwEdit = pwEntry.getText();
                String pw = pwEdit.toString(); //and it obeys law of demeter

                if (LoginManager.checkCredentials(un, pw)) {
                    LoginManager.logAccount(un);
                    startActivity(new Intent(LoginActivity.this, SuccessfulLoginActivity.class));
                } else {
                    invalidLoginText.setVisibility(View.VISIBLE);
                    tryAgainText.setVisibility(View.VISIBLE);
                }
            }
        });

        cancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, WelcomeActivity.class));
            }
        });



    }
}
