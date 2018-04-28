package com.irfanmolla.todo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tomerrosenfeld.customanalogclockview.CustomAnalogClock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Home extends AppCompatActivity {


    CardView cardView;
    TextView id;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<List_Items> datamodel;
    ArrayList<List_Items> items=new ArrayList<>();
    ImageView sleeping;

    FloatingActionButton floatingActionButton;
    String username;

    long BackPressTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        SharedPreferences sharedPreferences=getSharedPreferences("Preference",MODE_PRIVATE);
        final String retrivedData=sharedPreferences.getString("username",null);
        if(retrivedData==null){
        //    username="NoRegisteredUserExist";
            Toast.makeText(Home.this,"Please login first",Toast.LENGTH_LONG).show();
        }else{
            username=retrivedData;

        }

//////////////////////////////////////FLOATING BUTTON//////////////////////////////////////////////////////////////////////////////////////


        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, NewItem.class);
                if (username!=null){
                    i.putExtra("username",username);
                    i.putExtra("pass_code",1);
                    startActivity(i);
                }
                else{
                    Toast.makeText(Home.this,"Please login first",Toast.LENGTH_LONG).show();

                    // i.putExtra("username","NoRegisteredUserExist");
                    //i.putExtra("pass_code",1);
                }


            }
        });

////////////////////////////////INITIALIZATION/////////////////////////////////////////////////////////////////////////////////////////////


        id = (TextView) findViewById(R.id.cardID);

        //CustomAnalogClock customAnalogClock = (CustomAnalogClock) findViewById(R.id.analogCLK);
        //customAnalogClock.setAutoUpdate(true);


        NestedScrollView nestedScrollView = (NestedScrollView) findViewById(R.id.nestedSV);






       // LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
       // linearLayoutManager.setReverseLayout(true);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
       // recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Home.this);
        recyclerView.setLayoutManager(layoutManager);







        sleeping=(ImageView)findViewById(R.id.sleeping);


        UserDBhelper helper = new UserDBhelper(Home.this);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor2 = helper.viewUsers(db);
        if (cursor2.getCount() == 0) {


            //helper.insertUser(db, "NoRegisteredUserExist", "NoUsername", "", "");
        } else {
            Cursor cursor = helper.viewdata(db, username);
            if (cursor.getCount() != 0) {
                cursor.moveToFirst();
                do {
                    List_Items list_items = new List_Items( cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9));
                    items.add(list_items);
                } while (cursor.moveToNext());

                adapter = new Adapter(Home.this, items);
                recyclerView.setAdapter(adapter);

            } else {
                Toast.makeText(this, "holo na", Toast.LENGTH_LONG).show();

            }
        }
    }



////////////////////////////////OPTION MENU/////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else{
            if (id==R.id.login){
                Intent i=new Intent(Home.this,Login.class);
                startActivity(i);
                return true;
            }
            else{
                if (id==R.id.profile) {
                    return  true;
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
       recreate();
    }

    @Override
    public void onBackPressed() {

        if(BackPressTime+3000>System.currentTimeMillis()){
            super.onBackPressed();
        }else{
            Toast.makeText(Home.this,"Press back again to exit",Toast.LENGTH_LONG).show();
        }
        BackPressTime=System.currentTimeMillis();

    }
}
