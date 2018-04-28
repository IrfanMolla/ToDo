package com.irfanmolla.todo;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    SQLiteDatabase db;
    UserDBhelper userDBhelper;
    ImageButton imageButton;
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9;
    EditText e1,e2,e3,e4,e5;
    CardView RegisterButton;
    Cursor cursor;
    int code1=0,code2=0,code4=0,code5=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        imageButton=(ImageButton)findViewById(R.id.backBtnRegister);
        t1=(TextView)findViewById(R.id.textView6);
        t2=(TextView)findViewById(R.id.textView7);
        t3=(TextView)findViewById(R.id.textView8);
        t4=(TextView)findViewById(R.id.textView9);
        t5=(TextView)findViewById(R.id.textView10);
        t6=(TextView)findViewById(R.id.textView11);
        t7=(TextView)findViewById(R.id.textView12);
        t8=(TextView)findViewById(R.id.textView13);
        t9=(TextView)findViewById(R.id.textView14);


        e1=(EditText)findViewById(R.id.registerUsername);
        e2=(EditText)findViewById(R.id.registerName);
        e3=(EditText)findViewById(R.id.registerEmail);
        e4=(EditText)findViewById(R.id.registerPassword);
        e5=(EditText)findViewById(R.id.registerConfirmPass);

        RegisterButton=(CardView)findViewById(R.id.registerButton);

        userDBhelper=new UserDBhelper(RegisterActivity.this);
        db=userDBhelper.getWritableDatabase();

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e4==null){
                    t4.setTextColor(getResources().getColor(R.color.colorAccent));
                    e4.setHint("This can't be empty");
                    e4.setHintTextColor(getResources().getColor(R.color.colorAccent));
                    code4=0;
                }else{
                    code4=1;
                }
                if(e5==null){
                    t5.setTextColor(getResources().getColor(R.color.colorAccent));
                    e5.setHint("This can't be empty");
                    e5.setHintTextColor(getResources().getColor(R.color.colorAccent));
                    code5=0;
                }else{
                    code5=1;
                }

                if(e1==null){
                    t1.setTextColor(getResources().getColor(R.color.colorAccent));
                    e1.setHint("This can't be empty");
                    e1.setHintTextColor(getResources().getColor(R.color.colorAccent));
                    code1=0;
                }else{
                    code1=1;
                }

                if(e2==null){
                    t2.setTextColor(getResources().getColor(R.color.colorAccent));
                    e2.setHint("This can't be empty");
                    e2.setHintTextColor(getResources().getColor(R.color.colorAccent));
                    code2=0;
                }else {
                    code2=1;
                }

                if(e5!=e4){
                    t9.setVisibility(View.VISIBLE);
                    e5.setTextColor(getResources().getColor(R.color.colorAccent));
                    t5.setTextColor(getResources().getColor(R.color.colorAccent));
                    code5=0;
                }
                else{
                    code5=1;
                }
                if(e4.length()<6){
                    t8.setVisibility(View.VISIBLE);
                    e4.setTextColor(getResources().getColor(R.color.colorAccent));
                    t4.setTextColor(getResources().getColor(R.color.colorAccent));
                    code4=0;
                }else{
                    code4=1;
                }
                if (e1.length()<4){
                    t6.setVisibility(View.VISIBLE);
                    e1.setTextColor(getResources().getColor(R.color.colorAccent));
                    t1.setTextColor(getResources().getColor(R.color.colorAccent));
                    code1=0;
                }
                else{
                    cursor=userDBhelper.SearchUsername(db,e1.getText().toString());
                    if (cursor.getCount()!=0){
                        t6.setVisibility(View.INVISIBLE);
                        t7.setVisibility(View.VISIBLE);
                        e1.setTextColor(getResources().getColor(R.color.colorAccent));
                        t1.setTextColor(getResources().getColor(R.color.colorAccent));
                        code1=0;

                    }else{
                        code1 = 1;

                    }
                }
                if (code1==1&&code2==1&&code4==1&&code5==1){
                    userDBhelper.insertUser(db,e1.getText().toString(),e2.getText().toString(),e3.getText().toString(),e4.getText().toString());
                    SharedPreferences.Editor editor = getSharedPreferences("Preference",MODE_PRIVATE).edit();
                    editor.putString("username",e1.getText().toString());
                    editor.apply();
                    Toast.makeText(RegisterActivity.this,"Hoye geche vai", Toast.LENGTH_LONG).show();
                    Login.object.finish();
                    finish();
                }
            }
        });



    }
}
