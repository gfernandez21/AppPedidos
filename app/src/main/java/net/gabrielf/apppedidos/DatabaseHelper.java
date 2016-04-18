package net.gabrielf.apppedidos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by pangui-dev-2015 on 22-03-2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="ped4.db";
    //table contacts
    private static final String TABLE_NAME ="contacts";
    private static final String COLUMN_ID ="_id";
    private static final String COLUMN_UNAME ="uname";
    private static final String COLUMN_PASS ="pass";
    private static final String COLUMN_NAME ="name";
    private static final String COLUMN_EMAIL ="email";
    //table items
    private static final String TABLE_NAME2 ="items";
    private static final String COLUMN_ID_PROD="_id";
    private static final String COLUMN_NOMBREPROD ="nombreprod";
    private static final String COLUMN_DETALLEPROD ="detalleprod";
    private static final String COLUMN_PRECIOPROD ="precioprod";


    int COL_ID_INDEX=0;
    int COL_NAME_INDEX=1;
    int COL_EMAIL_INDEX=2;
    int COL_UNAME_INDEX=3;
    int COL_PASSWORD_INDEX=4;

    int COL_ID_PROD=0;
    int COL_NOMBREPROD=1;
    int COL_DETALLEPROD=2;
    int COL_PRECIOPROD=3;



    SQLiteDatabase db;

    private static final String TABLE_CREATE= "create table contacts (_id integer primary key not null , " +
            "name text not null , email text not null , uname text not null , pass text not null);";

    private static final String TABLE_CREATE2= "create table items (_id integer primary key not null , " +
            "nombreprod text not null , detalleprod text not null , precioprod real not null);";

    public DatabaseHelper(Context context){

        super(context , DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        db.execSQL(TABLE_CREATE2);
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

    public void insertProd(Item d){

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query ="select * from items";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID_PROD, count);
        values.put(COLUMN_NOMBREPROD, d.getNombreprod());
        values.put(COLUMN_DETALLEPROD, d.getDetalleprod());
        values.put(COLUMN_PRECIOPROD, d.getPrecioprod());

        db.insert(TABLE_NAME2, null, values);
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
            String query2= "DROP TABLE IF EXISTS" + TABLE_NAME2;
            db.execSQL(query);
            db.execSQL(query2);
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

    public String searchEmail(String mail){

        db = this.getReadableDatabase();
        String query ="select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        a = "not found";
        if (cursor.moveToFirst()){

            do {

                a= cursor.getString(COL_EMAIL_INDEX);
                //b= cursor.getString(COL_PASSWORD_INDEX);
                if (a.equals(mail)){

                    a = cursor.getString(COL_EMAIL_INDEX);
                    break;
                }

            }
            while (cursor.moveToNext());

        }
        return a;
    }

    //listar a todos los productos
    public Cursor listaritems(){
        SQLiteDatabase db = getReadableDatabase();
        String query = ("SELECT * FROM " + TABLE_NAME2 + " WHERE" +" "  + COLUMN_NOMBREPROD + " " + "LIKE" + "'P%'" + ";");
        Cursor c = db.rawQuery(query, null);

        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    public Cursor listaritems2(){
        SQLiteDatabase db = getReadableDatabase();
        String query = ("SELECT * FROM " + TABLE_NAME2 + " WHERE" +" "  + COLUMN_NOMBREPROD + " " + "LIKE" + "'H%'" + ";");
        Cursor c = db.rawQuery(query, null);

        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    public Cursor listaritems3(){
        SQLiteDatabase db = getReadableDatabase();
        String query = ("SELECT * FROM " + TABLE_NAME2 + " WHERE" +" "  + COLUMN_NOMBREPROD + " " + "LIKE" + "'NU%'" + ";");
        Cursor c = db.rawQuery(query, null);

        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    public Cursor listaritems4(){
        SQLiteDatabase db = getReadableDatabase();
        String query = ("SELECT * FROM " + TABLE_NAME2 + " WHERE" +" "  + COLUMN_NOMBREPROD + " " + "LIKE" + "'PA%'" + ";");
        Cursor c = db.rawQuery(query, null);

        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    public Cursor listaritems5(){
        SQLiteDatabase db = getReadableDatabase();
        String query = ("SELECT * FROM " + TABLE_NAME2 + " WHERE" +" "  + COLUMN_NOMBREPROD + " " + "LIKE" + "'CH%'" + ";");
        Cursor c = db.rawQuery(query, null);

        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    public Cursor listaritems6(){
        SQLiteDatabase db = getReadableDatabase();
        String query = ("SELECT * FROM " + TABLE_NAME2 + " WHERE" +" "  + COLUMN_NOMBREPROD + " " + "LIKE" + "'BE%'" + ";");
        Cursor c = db.rawQuery(query, null);

        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    public Cursor listaritems7(){
        SQLiteDatabase db = getReadableDatabase();
        String query = ("SELECT * FROM " + TABLE_NAME2 + " WHERE" +" "  + COLUMN_NOMBREPROD + " " + "LIKE" + "'TA%'" + ";");
        Cursor c = db.rawQuery(query, null);

        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    public Cursor listaritems8(){
        SQLiteDatabase db = getReadableDatabase();
        String query = ("SELECT * FROM " + TABLE_NAME2 + " WHERE" +" "  + COLUMN_NOMBREPROD + " " + "LIKE" + "'CO%'" + ";");
        Cursor c = db.rawQuery(query, null);

        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    public Cursor listaritems9(){
        SQLiteDatabase db = getReadableDatabase();
        String query = ("SELECT * FROM " + TABLE_NAME2 + " WHERE" +" "  + COLUMN_NOMBREPROD + " " + "LIKE" + "'CAF%'" + ";");
        Cursor c = db.rawQuery(query, null);

        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    public void borrarItems(int items_id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME2 + " WHERE " + COLUMN_ID_PROD + " = " + items_id + ";");
        db.close();
    }

    public void updateItem(Item item){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBREPROD, item.getNombreprod());
        values.put(COLUMN_DETALLEPROD, item.getDetalleprod());
        values.put(COLUMN_PRECIOPROD, item.getPrecioprod());
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_NAME2, values, COLUMN_ID + "= ?", new String[]{String.valueOf(item.get_id())});
        db.close();

    }

    public Cursor itemByid(int id){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME2 + " WHERE " + COLUMN_ID_PROD + " = " + id + ";";
        Cursor c = db.rawQuery(query, null);

        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }



}
