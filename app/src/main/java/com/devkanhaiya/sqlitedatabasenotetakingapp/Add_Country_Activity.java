package com.devkanhaiya.sqlitedatabasenotetakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Add_Country_Activity extends Activity implements View.OnClickListener {
private EditText subject;
private EditText description;
private Button addRecord;
    DbManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Record");
        setContentView(R.layout.activity_add__country_);
    subject=findViewById(R.id.subject);
    description=findViewById(R.id.etdescription);
    addRecord=findViewById(R.id.add_rrecord);

   dbManager =new DbManager(this);
    dbManager.open();
    addRecord.setOnClickListener(this);

    }


    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.add_rrecord:
                                final String name = subject.getText().toString();
                                final String dsc = description.getText().toString();

                                dbManager.insert(name,dsc);
                Intent intent= new Intent(Add_Country_Activity.this,CountryListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                break;
        }

    }


}