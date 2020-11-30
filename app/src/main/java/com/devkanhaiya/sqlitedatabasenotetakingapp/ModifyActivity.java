package com.devkanhaiya.sqlitedatabasenotetakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModifyActivity extends Activity implements View.OnClickListener {
    private EditText subject_edit;
    private EditText description_edit;
    private Button btnUpdate,btnDelete;
    DbManager dbManager;

    Long lid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        dbManager=new DbManager(this);
        dbManager.open();


        subject_edit=findViewById(R.id.subject_edit);
        description_edit=findViewById(R.id.description_edit);
        btnUpdate=findViewById(R.id.btn_update);
        btnDelete=findViewById(R.id.btn_delete);




        Intent intent =getIntent();

        long kid = intent.getLongExtra("id",0);
        String title = intent.getStringExtra("subject");
        String descri = intent.getStringExtra("description");

        lid=kid;

        subject_edit.setText(title);
        description_edit.setText(descri);


        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
    }
    public void onClick(View v){
        int id=v.getId();
        switch (id){
            case R.id.btn_update:
                String title=subject_edit.getText().toString();
                String des=description_edit.getText().toString();
                dbManager.update(lid,title,des);
                this.returnHome();
                break;
            case R.id.btn_delete:
                dbManager.delete(lid);
                this.returnHome();
        }

    }
    public void returnHome(){
        Intent intent = new Intent(getApplicationContext(),CountryListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}