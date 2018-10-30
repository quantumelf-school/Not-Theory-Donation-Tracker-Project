package nottheory.donationtracker.Controllers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import nottheory.donationtracker.R;

public class DonationSearchActivity extends AppCompatActivity {
    private Switch searchByCat;
    private TextView searchBox;
    private RecyclerView donationSearchList;
    private Spinner catSpinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_search);

        searchBox = findViewById(R.id.search_entry);
        searchBox.setVisibility(View.VISIBLE);

        catSpinner = findViewById(R.id.search_cat_spinner);
//        TODO: remove this hardcoded cat. list
        ArrayList<String> categoryList = new ArrayList<String>();
        categoryList.add("monetary");
        categoryList.add("non-monetary");
        catSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryList));
        catSpinner.setVisibility(View.GONE);

        searchByCat = findViewById(R.id.search_switch);
        searchByCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchByCat.isChecked()) {
                    catSpinner.setVisibility(View.VISIBLE);
                    searchBox.setVisibility(View.GONE);
                } else {
                    catSpinner.setVisibility(View.GONE);
                    searchBox.setVisibility(View.VISIBLE);
                }
            }
        });

        donationSearchList = findViewById(R.id.donationSearchList);
    }
}
