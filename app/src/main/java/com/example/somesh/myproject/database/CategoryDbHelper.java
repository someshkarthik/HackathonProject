package com.example.somesh.myproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shiny Grace on 1/12/2018.
 */

public class CategoryDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Inventory.db";
    private static final int DATABASE_VERSION = 1;

    public CategoryDbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String Create_Dp_Table = "CREATE TABLE " + CategoryContract.categoryListEntry.TABLE_NAME + "("
                + CategoryContract.categoryListEntry._id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CategoryContract.categoryListEntry.COLUMN_CATEGORY_NAME + " TEXT NOT NULL);";
        sqLiteDatabase.execSQL(Create_Dp_Table);
        String Create_Questions_Table = "CREATE TABLE " + CategoryContract.categoryQuestions.TABLE_NAME + "("
                + CategoryContract.categoryQuestions._id + " Integer PRIMARY KEY AUTOINCREMENT,"
                + CategoryContract.categoryQuestions.COLUMN_CATEGORY_NAME + " TEXT NOT NULL,"
                + CategoryContract.categoryQuestions.COLUMN_QUESTIONS + " TEXT NOT NULL,"
                + CategoryContract.categoryQuestions.COLUMN_OPTIONA + " TEXT NOT NULL,"
                + CategoryContract.categoryQuestions.COLUMN_OPTIONB + " TEXT NOT NULL,"
                + CategoryContract.categoryQuestions.COLUMN_OPTIONC + " TEXT NOT NULL,"
                + CategoryContract.categoryQuestions.COLUMN_OPTIOND + " TEXT NOT NULL,"
                + CategoryContract.categoryQuestions.COLUMN_CORRECT_OPTION + " TEXT NOT NULL);";
        sqLiteDatabase.execSQL(Create_Dp_Table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
