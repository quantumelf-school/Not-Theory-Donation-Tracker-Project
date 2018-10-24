package nottheory.donationtracker.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import nottheory.donationtracker.Model.AccountType;
import nottheory.donationtracker.Model.Donation;
import nottheory.donationtracker.Model.LoginManager;
import nottheory.donationtracker.R;

public class AddDonationActivity extends AppCompatActivity {
    private Button backButton;
    private Button addItemButton;
    private EditText shortDescText;
    private EditText fullDescText;
    private EditText dateTime;
    private Spinner category;
    private EditText value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);
        shortDescText = findViewById(R.id.additem_shortdesc);
        fullDescText = findViewById(R.id.additem_fulldesc);
        dateTime = findViewById(R.id.additem_time);
        category = findViewById(R.id.additem_catspinner);
        value = findViewById(R.id.additem_value);

        ArrayList<String> categoryList = new ArrayList<String>();
        categoryList.add("monetary");
        categoryList.add("non-monetary");
        category.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryList));


        backButton = findViewById(R.id.additem_cancelbutton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddDonationActivity.this, DonationListActivity.class));
            }
        });

        addItemButton = findViewById(R.id.additem_enterbutton);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shortDesc = shortDescText.getText().toString();
                String fullDesc = fullDescText.getText().toString();
                String time = dateTime.getText().toString();
                String dcategory = (String) category.getSelectedItem();
                String dvalue = value.getText().toString();

                if (!shortDesc.equals("") && !fullDesc.equals("") && !time.equals("") && !dcategory.equals("") && !dvalue.equals("")) {
                    Donation newDonation = new Donation(time, shortDesc, fullDesc, dvalue, dcategory);
                    LoginManager.locations.getLocationFromRow(getIntent().getIntExtra("pos", 1)).addDonation(newDonation);
                }

                startActivity(new Intent(AddDonationActivity.this, DonationListActivity.class));
            }
        });
    }
}
