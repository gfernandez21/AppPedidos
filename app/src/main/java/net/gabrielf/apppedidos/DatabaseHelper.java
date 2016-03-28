package net.gabrielf.apppedidos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pangui-dev-2015 on 22-03-2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="ped.db";
    private static final String TABLE_NAME ="contacts";
    private static final String COLUMN_ID ="id";
    private static final String COLUMN_UNAME ="uname";
    private static final String COLUMN_PASS ="pass";
    private static final String COLUMN_NAME ="name";
    private static final String COLUMN_EMAIL ="email";


    int COL_ID_INDEX=0;
    int COL_NAME_INDEX=1;
    int COL_EMAIL_INDEX=2;
    int COL_UNAME_INDEX=3;
    int COL_PASSWORD_INDEX=4;


    SQLiteDatabase db;

    private static final String TABLE_CREATE= "create table contacts (id integer primary key not null , " +
            "name text not null , email text not null , uname text not null , pass text not null);";

    public DatabaseHelper(Context context){

        super(context , DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db= db;

    }

    public void insertContact(Contact c){

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query ="select * from contacts";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_UNAME, c.getUname());
        values.put(COLUMN_PASS, c.getPass());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String searchPass(String uname){

            db = this.getReadableDatabase();
            String query ="select * from " + TABLE_NAME;
            Cursor cursor = db.rawQuery(query, null);
            String a, b;
            b = "not found";
            if (cursor.moveToFirst()){

                do {

                    a= cursor.getString(COL_UNAME_INDEX);
                    b= cursor.getString(COL_PASSWORD_INDEX);
                    if (a.equals(uname)){

                        b = cursor.getString(COL_PASSWORD_INDEX);
                        break;
                    }

                }
                while (cursor.moveToNext());

             }
            return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String query= "DROP TABLE IF EXISTS" + TABLE_NAME;
            db.execSQL(query);
            this.onCreate(db);

    }

    public String searchUser(String uname){

        db = this.getReadableDatabase();
        String query ="select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        a = "not found";
        if (cursor.moveToFirst()){

            do {

                a= cursor.getString(COL_UNAME_INDEX);
                //b= cursor.getString(COL_PASSWORD_INDEX);
                if (a.equals(uname)){

                    a = cursor.getString(COL_UNAME_INDEX);
                    break;
                }

            }
            while (cursor.moveToNext());

        }
        return a;
    }
}
