package com.irfanmolla.todo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    EditText username,password;
    TextView RegisterHere,loginBtn,TitleUsername,TitlePassword;
    ImageButton backBtn,eye,hide;
    public static Activity object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        object=this;

        username=(EditText)findViewById(R.id.loginUsername);
        password=(EditText)findViewById(R.id.loginPassword);
        loginBtn=(TextView) findViewById(R.id.loginButton);
        RegisterHere=(TextView)findViewById(R.id.RegisterHere);
        TitleUsername=(TextView)findViewById(R.id.TitleUsername);
        TitlePassword=(TextView)findViewById(R.id.TitlePassword);
        backBtn=(ImageButton)findViewById(R.id.backBtn);
        eye=(ImageButton)findViewById(R.id.EYE);
        hide=(ImageButton)findViewById(R.id.HIDE);




        loginBtn.setOnClickListener(new View.OnClickListener() {
            UserDBhelper helper=new UserDBhelper(Login.this);
            SQLiteDatabase db=helper.getWritableDatabase();
            @Override
            public void onClick(View v) {
                if (username.getText().toString()==""){
                    username.setHint("Username can't be empty");
                    username.setHintTextColor(getResources().getColor(R.color.colorAccent));
                }

                if (password.getText().toString()==""){
                    password.setHint("Password can't be empty");
                    password.setHintTextColor(getResources().getColor(R.color.colorAccent));
                }



                Cursor cursor=helper.SearchUsername(db,username.getText().toString());
                if (cursor.getCount()==0){
                    username.setTextColor(getResources().getColor(R.color.colorAccent));
                    TitleUsername.setTextColor(getResources().getColor(R.color.colorAccent));
                    password.setTextColor(getResources().getColor(R.color.colorAccent));
                    TitlePassword.setTextColor(getResources().getColor(R.color.colorAccent));
                }else{
                    cursor=helper.SearchUser(db,username.getText().toString(),password.getText().toString());
                    if (cursor.getCount()==0){
                        username.setTextColor(getResources().getColor(R.color.colorAccent));
                        TitleUsername.setTextColor(getResources().getColor(R.color.colorAccent));
                        password.setTextColor(getResources().getColor(R.color.colorAccent));
                        TitlePassword.setTextColor(getResources().getColor(R.color.colorAccent));

                    }else{
                        SharedPreferences.Editor editor = getSharedPreferences("Preference",MODE_PRIVATE).edit();
                        editor.putString("username",username.getText().toString());
                        editor.apply();
                        finish();
                    }
                }
            }
        });
        username.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                username.setTextColor(getResources().getColor(R.color.Text));
                TitleUsername.setTextColor(getResources().getColor(R.color.Text));
                username.setHint("");
                username.setHintTextColor(getResources().getColor(R.color.colorAccent));

                return false;
            }
        });

        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                password.setTextColor(getResources().getColor(R.color.Text));
                TitlePassword.setTextColor(getResources().getColor(R.color.Text));
                password.setHint("");
                password.setHintTextColor(getResources().getColor(R.color.colorAccent));

                return false;
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide.setVisibility(View.VISIBLE);
                password.setInputType(InputType.TYPE_CLASS_TEXT);
            }
        });

        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide.setVisibility(View.GONE);
                eye.setVisibility(View.VISIBLE);
                password.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        });
        RegisterHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login.this,RegisterActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
     finish();
        super.onBackPressed();
    }
}
