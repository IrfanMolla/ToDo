package com.irfanmolla.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2/11/2018.
 */

public class UserDBhelper extends SQLiteOpenHelper {

    private static final String DB_NAME="TODO";
            private static final int DB_VERSION=3;
            private static final String TABLE_NAME="menu";
            private static final String COL_0="id";
            private static final String COL_1="title";
            private static final String COL_2="dayname";
            private static final String COL_3="day";
            private static final String COL_4="month";
            private static final String COL_5="year";
            private static final String COL_6="hour";
            private static final String COL_7="minute";
            private static final String COL_8="note";
            private static final String COL_9="username";
            private static final String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME+ " ( " + COL_9+ " TEXT , " + COL_0+ " INTEGER PRIMARY KEY AUTOINCREMENT , " + COL_1+ " TEXT , " + COL_2+ " TEXT , " + COL_3+ " TEXT , " + COL_4+" TEXT , " + COL_5+ " TEXT , " + COL_6+ " TEXT , " + COL_7+ " TEXT , " + COL_8+ " TEXT );";



            private static final String USER_TABLE="users";
            private static final String COL_11="username";
            private static final String COL_12="name";
            private static final String COL_13="email";
            private static final String COL_14="password";

            private  static final String CREATE_USER = "CREATE TABLE " + USER_TABLE+ " ( " + COL_11+ " TEXT PRIMARY KEY , " + COL_12+ " TEXT , " + COL_13+ " TEXT , " + COL_14+ " TEXT );";

    public UserDBhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        db.execSQL(CREATE_USER);
    }

    public  void insertUser(SQLiteDatabase db,String username, String name, String email, String password){
        db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
         contentValues.put(COL_11,username);
         contentValues.put(COL_12,name);
         contentValues.put(COL_13,email);
         contentValues.put(COL_14,password);
         db.insert(USER_TABLE,null,contentValues);
    }

    public  void insertdata(String username,String title,String dayname, String day, String month, String year, String hour, String minute, String note,SQLiteDatabase db){

        db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_9,username);
        contentValues.put(COL_1,title);
        contentValues.put(COL_2,dayname);
        contentValues.put(COL_3,day);
        contentValues.put(COL_4,month);
        contentValues.put(COL_5,year);
        contentValues.put(COL_6,hour);
        contentValues.put(COL_7,minute);
        contentValues.put(COL_8,note);
        db.insert(TABLE_NAME,null,contentValues);
    }

    public int deletedata(SQLiteDatabase db, int id,String username){
        int result=db.delete(TABLE_NAME, "id = "+ id+ " AND username = '" + username+"'",null);
        return result;
    }


   public  Cursor viewdata(SQLiteDatabase db, String username){
            Cursor cursor=db.rawQuery("SELECT * FROM " + TABLE_NAME+ " WHERE username = '" + username+"'",null);
            return cursor;
   }

   public Cursor viewUsers(SQLiteDatabase db){
       Cursor cursor=db.rawQuery("SELECT * FROM " + USER_TABLE, null);
       return cursor;
   }

   public Cursor viewItem(SQLiteDatabase db, int id,String username){

       Cursor cursor=db.rawQuery("SELECT * FROM " + TABLE_NAME+ " WHERE id = " + id+ " AND username = '" + username+"'",null);
       return cursor;
   }

   public Cursor SearchUsername(SQLiteDatabase db,String username){
       Cursor cursor=db.rawQuery("SELECT * FROM " + USER_TABLE+ " WHERE username = '" + username+"'",null);
       return cursor;
   }

    public Cursor SearchUser(SQLiteDatabase db,String username, String password){
        Cursor cursor=db.rawQuery("SELECT * FROM " + USER_TABLE+ " WHERE username = '" + username+ "' AND password = '" + password+"'",null);
        return cursor;
    }

   public int updateData(SQLiteDatabase db, String username, int id, String title,String dayname, String day, String month, String year, String hour, String minute, String note ){
       ContentValues contentValues=new ContentValues();

       contentValues.put(COL_1,title);
       contentValues.put(COL_2,dayname);
       contentValues.put(COL_3,day);
       contentValues.put(COL_4,month);
       contentValues.put(COL_5,year);
       contentValues.put(COL_6,hour);
       contentValues.put(COL_7,minute);
       contentValues.put(COL_8,note);

       int result=db.update(TABLE_NAME,contentValues,"id = " + id+ " AND username = " + username,null );
       return result;
   }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
