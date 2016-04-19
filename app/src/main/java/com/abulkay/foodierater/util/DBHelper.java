package com.abulkay.foodierater.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by akay on 3/28/16.
 * http://www.tutorialspoint.com/android/android_sqlite_database.htm
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "foodierater.db";
    public static final String TABLE_NAME = "Ratings";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_RATING = "rating";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (" +
                        COLUMN_ID + " integer primary key, " +
                        COLUMN_NAME + " text," +
                        COLUMN_RATING + " REAL)"
        );
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addRating(String name, float rating) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, name);
        contentValues.put(COLUMN_RATING, rating);

        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where " + COLUMN_ID + " = " + id + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public boolean updateRating(Integer id, String name, float rating) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, name);
        contentValues.put(COLUMN_RATING, rating);

        db.update(TABLE_NAME, contentValues,
                COLUMN_ID + " = ? ",
                new String[]{Integer.toString(id)}
        );

        return true;
    }

    public Integer deleteRating(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,
                COLUMN_ID + " = ? ",
                new String[]{Integer.toString(id)}
        );
    }

    public HashMap<Integer,String[]> getAllRating() {
        hp = new HashMap<Integer,String[]>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME, null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            ArrayList<String> array_list = new ArrayList<String>();
            array_list.add(res.getString(res.getColumnIndex(COLUMN_NAME)));
            array_list.add(res.getFloat(res.getColumnIndex(COLUMN_RATING))+"");
            hp.put(res.getInt(res.getColumnIndex(COLUMN_ID)),array_list.toArray());
            res.moveToNext();
        }
        return hp;
    }
}