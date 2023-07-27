package com.example.todolist;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class db extends SQLiteOpenHelper {

    private static final String dbname = "databases";
    private static final String TABLE_NAME = "notss";
    private static final String ID = "id";
    private static final int version = 1;
    private static final String NAME_NOT = "nots";

    public db(@Nullable Context context) {
        super(context, dbname, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query ="create table " + TABLE_NAME + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME_NOT + " TEXT )";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String upgrade = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        db.execSQL(upgrade);
        onCreate(db);
    }

    public void Add_Note(Listtodo listtodo){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NAME_NOT,listtodo.getTodo());
        db.insert(TABLE_NAME,null,cv);
        db.close();
    }

    @SuppressLint("SuspiciousIndentation")
    public List<Listtodo> getnots(){

        SQLiteDatabase db = this.getReadableDatabase();
        List<Listtodo> note = new ArrayList<>();
        String getAllNote = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(getAllNote,null);


        if (cursor.moveToFirst())
            do {
                Listtodo todo = new Listtodo();
                todo.setTodo(cursor.getString(1));
                note.add(todo);

            }while (cursor.moveToNext());

                return note;

    }



    public void deleteCourse(String courseName) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, NAME_NOT+"=?", new String[]{courseName});
        db.close();


    }
}



























