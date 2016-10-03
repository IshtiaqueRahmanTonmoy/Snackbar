package info.androidhive.snackbar;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;

public class AddnewexpenseActivity extends AppCompatActivity {

    Spinner spinner;
    private ArrayList<String> expenselist;

    EditText distancefrom,distanceto,nameofvehicle,cost,distanceinkm,mobilerent,packetwithdraw,othercost,total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_addnewexpense);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        distancefrom = (EditText) findViewById(R.id.Destinationfrom);
        distanceto = (EditText) findViewById(R.id.Destinationto);
        nameofvehicle = (EditText) findViewById(R.id.Vehiclename);
        cost = (EditText) findViewById(R.id.cost);
        distanceinkm = (EditText) findViewById(R.id.Distanceinkm);
        mobilerent = (EditText) findViewById(R.id.Mobilerent);
        packetwithdraw = (EditText) findViewById(R.id.Packetwithdraw);
        othercost = (EditText) findViewById(R.id.Othercost);
        total = (EditText) findViewById(R.id.Totalamount);

        spinner = (Spinner) findViewById(R.id.expensetype);
        expenselist = new ArrayList<String>();

        expenselist.add("মোটর সাইকেল ছাড়া");
        expenselist.add("কোম্পানির মোটর সাইকেল");
        expenselist.add("নিজের মোটর সাইকেল");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(AddnewexpenseActivity.this, android.R.layout.simple_spinner_item, expenselist);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);


        spinner = (Spinner) findViewById(R.id.expensetype);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}