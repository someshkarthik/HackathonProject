package com.example.somesh.myproject.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.somesh.myproject.data.Contract.CategoryEntry;
import com.example.somesh.myproject.data.Contract.QuestionsEntry;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "edtechDb.db";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create a String that contains the SQL statement to create the pets table
        String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + CategoryEntry.TABLE_NAME + "("
                + CategoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CategoryEntry.COLUMN_CATEGORY_NAME + " TEXT NOT NULL);";


        // Create a String that contains the SQL statement to create the pets table
        String CREATE_QUESTIONS_TABLE = "CREATE TABLE " + QuestionsEntry.TABLE_NAME + "("
                + QuestionsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + QuestionsEntry.COLUMN_Q_NO + " INTEGER NOT NULL,"
                + QuestionsEntry.COLUMN_Q_Q + " TEXT NOT NULL,"
                + QuestionsEntry.COLUMN_Q_ANS + " TEXT NOT NULL,"
                + QuestionsEntry.COLUMN_Q_OPTION1 + " TEXT NOT NULL,"
                + QuestionsEntry.COLUMN_Q_OPTION2 + " TEXT NOT NULL,"
                + QuestionsEntry.COLUMN_Q_OPTION3 + " TEXT NOT NULL,"
                + QuestionsEntry.COLUMN_Q_OPTION4 + " TEXT NOT NULL);";

        // Execute the SQL statement
        sqLiteDatabase.execSQL(CREATE_CATEGORIES_TABLE);
        // Execute the SQL statement
        sqLiteDatabase.execSQL(CREATE_QUESTIONS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}