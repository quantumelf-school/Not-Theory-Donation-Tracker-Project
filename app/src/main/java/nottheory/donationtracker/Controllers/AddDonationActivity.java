package nottheory.donationtracker.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import nottheory.donationtracker.Model.Donation;
import nottheory.donationtracker.Model.Location;
import nottheory.donationtracker.Model.LoginManager;
import nottheory.donationtracker.R;

public class AddDonationActivity extends AppCompatActivity {
    private EditText shortDescText;
    private EditText fullDescText;
    private EditText dateTime;
    private Spinner category;
    private EditText value;
    private TextView errorMess;
    private final int DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button backButton;
        Button addItemButton;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);
        shortDescText = findViewById(R.id.additem_shortdesc);
        fullDescText = findViewById(R.id.additem_fulldesc);
        dateTime = findViewById(R.id.additem_time);
        category = findViewById(R.id.additem_catspinner);
        value = findViewById(R.id.additem_value);
        final Intent intent = getIntent();

//        TODO: remove this hardcoded garbage cat. list
        ArrayList<String> categoryList = new ArrayList<>();
        categoryList.add("Clothing");
        categoryList.add("Hat");
        categoryList.add("Kitchen");
        categoryList.add("Electronics");
        categoryList.add("Household");
        categoryList.add("Other");
        category.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                categoryList));


        backButton = findViewById(R.id.additem_cancelbutton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

                //startActivity(new Intent(AddDonationActivity.this, DonationListActivity.class));
            }
        });
        errorMess = findViewById(R.id.additem_errormess);
        errorMess.setVisibility(View.INVISIBLE);
        addItemButton = findViewById(R.id.additem_enterbutton);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //JoeyFix
                String shortDesc = shortDescText.getText().toString(); //most concise way to do this
                String fullDesc = fullDescText.getText().toString(); //also does meed LoD
                String time = dateTime.getText().toString();
                String dcategory = (String) category.getSelectedItem();
                String dvalue = value.getText().toString();

                Location l = LoginManager.locations.getLocationByName(
                        intent.getStringExtra("location"));

                if (!"".equals(shortDesc) && !"".equals(fullDesc) && !"".equals(time) &&
                        !"".equals(dcategory) && !"".equals(dvalue)) {
                    Donation newDonation = new Donation(
                            time, shortDesc, fullDesc, dvalue, dcategory);
                    l.addDonation(newDonation);
                    //System.out.println("TEST go bac");
                    Intent i = new Intent(
                            AddDonationActivity.this, DonationListActivity.class);
                    i.putExtra("location", l.getName());
                    startActivity(i);
                    finish();
                } else {
                    //show error message
                    errorMess.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            errorMess.setVisibility(View.INVISIBLE);
                        }
                    }, DELAY);
                }

            }
        });
    }
}
