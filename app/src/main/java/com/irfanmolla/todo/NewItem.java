package com.irfanmolla.todo;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.opengl.Visibility;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class NewItem extends AppCompatActivity {

    EditText title,date,time,note;
    ImageButton btn,btn2;
    CardView setDate,setTime;
    TextView Stime,Sdate, tv_title;
    int id;

    String username;

    int daynameNO,year1,month1,day,Hour,Minute;
    String MonthName,dayname;
    int pass_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);



//////////////////////////////////////////////INTIALIZATION/////////////////////////////////////////////////////////////////////////////////////
        btn=(ImageButton) findViewById(R.id.button);
        btn2=(ImageButton) findViewById(R.id.button2);
        title=(EditText)findViewById(R.id.editText);
        date=(EditText)findViewById(R.id.editText2);
        time=(EditText)findViewById(R.id.editText3);
        note=(EditText)findViewById(R.id.editText4);


        setDate=(CardView)findViewById(R.id.setDate);
        setTime=(CardView)findViewById(R.id.setTime);

        Stime=(TextView)findViewById(R.id.Stime);
        Sdate=(TextView)findViewById(R.id.Sdate);
        tv_title=(TextView)findViewById(R.id.tv_title);

        Calendar calendar = Calendar.getInstance();


        Intent i=getIntent();

        if(i!=null){
            if(i.getIntExtra("pass_code",0)==1){
                username=i.getStringExtra("username");
            }
            if (i.getIntExtra("pass_code",0)==101){
                if (i.getStringExtra("id")!=null){

                    id=Integer.parseInt(i.getStringExtra("id"));

                }

                year1=Integer.parseInt(i.getStringExtra("year"));
                month1=Integer.parseInt(i.getStringExtra("month"));
                day=Integer.parseInt(i.getStringExtra("day"));
                daynameNO=Integer.parseInt(i.getStringExtra("dayname"));
                title.setText(i.getStringExtra("title"));
                note.setText(i.getStringExtra("note"));
            }else {
                year1 = calendar.get(Calendar.YEAR);
                month1 = calendar.get(Calendar.MONTH) + 1;
                day = calendar.get(Calendar.DAY_OF_MONTH);
                daynameNO=calendar.get(Calendar.DAY_OF_WEEK);
            }
        }



        switch (month1){
            case 1:
                MonthName="Jan";
                break;
            case 2:
                MonthName="Feb";
                break;
            case 3:
                MonthName="Mar";
                break;
            case 4:
                MonthName="Apr";
                break;

            case 5:
                MonthName="May";
                break;

            case 6:
                MonthName="Jun";
                break;

            case 7:
                MonthName="Jul";
                break;

            case 8:
                MonthName="Aug";
                break;

            case 9:
                MonthName="Sep";
                break;

            case 10:
                MonthName="Oct";
                break;

            case 11:
                MonthName="Nov";
                break;

            case 12:
                MonthName="Dec";
                break;

        }

        switch (daynameNO){
            case 1:
                dayname="Mon";
                break;
            case 2:
                dayname="Tue";
                break;
            case 3:
                dayname="Wed";
                break;
            case 4:
                dayname="Thu";
                break;
            case 5:
                dayname="Fri";
                break;
            case 6:
                dayname="Sat";
                break;
            case 7:
                dayname="Sun";
                break;

        }
        Sdate.setText(dayname+ " " +day+"th "+MonthName+","+year1%100);
        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog=new DatePickerDialog(NewItem.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        switch (month+1){
                            case 1:
                                MonthName="Jan";
                                break;
                            case 2:
                                MonthName="Feb";
                                break;
                            case 3:
                                MonthName="Mar";
                                break;
                            case 4:
                                MonthName="Apr";
                                break;
                            case 5:
                                MonthName="May";
                                break;
                            case 6:
                                MonthName="Jun";
                                break;
                            case 7:
                                MonthName="Jul";
                                break;
                            case 8:
                                MonthName="Aug";
                                break;
                            case 9:
                                MonthName="Sep";
                                break;
                            case 10:
                                MonthName="Oct";
                                break;
                            case 11:
                                MonthName="Nov";
                                break;
                            case 12:
                                MonthName="Dec";
                                break;
                        }
                        Sdate.setText(dayOfMonth+"th "+MonthName+","+year%100);
                        year1=year;
                        month1=month;
                        day=dayOfMonth;
                    }
                },year1,month1,day);
                datePickerDialog.show();

            }
        });

////////////////////////////////////////////SET TIME BUTTON//////////////////////////////////////////////////////////////////////////////////

        Hour=calendar.get(Calendar.HOUR_OF_DAY);
        Minute=calendar.get(Calendar.MINUTE);


        Stime.setText(Hour+" : "+Minute);

        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TimePickerDialog timePickerDialog=new TimePickerDialog(NewItem.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Stime.setText(hourOfDay+" : "+minute);
                        Hour=hourOfDay;
                        Minute=minute;
                    }
                },Hour,Minute,true);

                timePickerDialog.show();
            }
        });





  ///////////////////////////////////////////////BUTTON CLICK////////////////////////////////////////////////////////////////////////
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(title.getText().toString().equals("")){

                    tv_title.setTextColor(getResources().getColor(R.color.colorAccent));
                }else {
                    UserDBhelper helper = new UserDBhelper(NewItem.this);
                    SQLiteDatabase db = helper.getWritableDatabase();
                    Cursor cursor = helper.viewItem(db, id, username);
                    if (cursor.getCount() != 0) {
                        int result = helper.updateData(db, username, id, title.getText().toString(), String.valueOf(daynameNO), String.valueOf(day), String.valueOf(month1), String.valueOf(year1), String.valueOf(Hour), String.valueOf(Minute), note.getText().toString());
                        if (result == 0) {
                            Toast.makeText(NewItem.this, "Holo na", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(NewItem.this, "Hye gche vai", Toast.LENGTH_LONG).show();

                        }
                    } else {
                        helper.insertdata(username, title.getText().toString(), String.valueOf(daynameNO), String.valueOf(day), String.valueOf(month1), String.valueOf(year1), String.valueOf(Hour), String.valueOf(Minute), note.getText().toString(), db);

                    }
                    NewItem.this.finish();
                }

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(NewItem.this);
                builder.setMessage("Want to save it?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        if(title.getText().toString().equals("")){

                            tv_title.setTextColor(getResources().getColor(R.color.colorAccent));
                        }else {
                            UserDBhelper helper = new UserDBhelper(NewItem.this);
                            SQLiteDatabase db = helper.getWritableDatabase();
                            Cursor cursor = helper.viewItem(db, id, username);
                            if (cursor.getCount() != 0) {
                                int result = helper.updateData(db, username, id, title.getText().toString(), String.valueOf(daynameNO), String.valueOf(day), String.valueOf(month1), String.valueOf(year1), String.valueOf(Hour), String.valueOf(Minute), note.getText().toString());
                                if (result == 0) {
                                    Toast.makeText(NewItem.this, "Holo na", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(NewItem.this, "Hye gche vai", Toast.LENGTH_LONG).show();

                                }
                            } else {
                                helper.insertdata(username, title.getText().toString(), String.valueOf(daynameNO), String.valueOf(day), String.valueOf(month1), String.valueOf(year1), String.valueOf(Hour), String.valueOf(Minute), note.getText().toString(), db);

                            }
                            NewItem.this.finish();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });

        title.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                tv_title.setTextColor(getResources().getColor(R.color.Text));
                return false;
            }
        });


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    }
}
