package com.devkanhaiya.sqlitedatabasenotetakingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class CountryListActivity extends AppCompatActivity {

    private DbManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    private static final String[] from = new String[]{
      mDbHelper._ID,mDbHelper.SUBJECT,mDbHelper.DESC
    };

    private static final int[] to = new int[]{
      R.id.cid,R.id.subject,R.id.desc
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listview);
        View empty=findViewById(R.id.empty);
        listView.setEmptyView(empty);
        dbManager=new DbManager(this);
        dbManager.open();
        Cursor cursor=dbManager.fetch();

        adapter= new SimpleCursorAdapter(this,R.layout.activity_view_record,cursor,from,to,0);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long vid) {
                TextView itemid=view.findViewById(R.id.cid);
                TextView itemSubject=view.findViewById(R.id.subject);
                TextView itemDescription=view.findViewById(R.id.desc);


                long id=vid;
                String subject = itemSubject.getText().toString();
                String description=itemDescription.getText().toString();


                Intent modify_intent=new Intent(getApplicationContext(),ModifyActivity.class);
                modify_intent.putExtra("id",id);
                modify_intent.putExtra("subject",subject);
                modify_intent.putExtra("description",description);
                startActivity(modify_intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main,menu);
    return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.add_record){
            Intent intent=new Intent(CountryListActivity.this,Add_Country_Activity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}