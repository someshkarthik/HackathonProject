package com.example.somesh.myproject.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Shiny Grace on 1/12/2018.
 */

public class CategoryProvider extends ContentProvider {
    private static final int Category = 100;
    private static final int Category_ID = 101;
    private static final UriMatcher sUrimatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUrimatcher.addURI("com.example.somesh.myproject", CategoryContract.categoryListEntry.TABLE_NAME, Category);
        sUrimatcher.addURI("com.example.somesh.myproject", CategoryContract.categoryListEntry.TABLE_NAME + "/#", Category_ID);
    }

    private CategoryDbHelper categoryDbHelper;

    @Override
    public boolean onCreate() {
        categoryDbHelper = new CategoryDbHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase sqLiteDatabase = categoryDbHelper.getReadableDatabase();
        Cursor cursor;
        int result = sUrimatcher.match(uri);
        switch (result) {
            case Category:
                cursor = sqLiteDatabase.query(CategoryContract.categoryListEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case Category_ID:
                selection = CategoryContract.categoryListEntry._id + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = sqLiteDatabase.query(CategoryContract.categoryListEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown uri" + uri);

        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        int result = sUrimatcher.match(uri);
        Log.e("The value of result", "" + result);
        switch (result) {
            case Category:
                Log.e("The value of result", "" + result);
                return insertCategory(uri, contentValues);

            default:
                throw new IllegalArgumentException("CANNOT INSERT UNKNOWN URI" + uri);
        }
    }

    private Uri insertCategory(Uri uri, ContentValues contentValues) {
        SQLiteDatabase sqLiteDatabase = categoryDbHelper.getReadableDatabase();
        Long rowID = sqLiteDatabase.insert(CategoryContract.categoryListEntry.TABLE_NAME, null, contentValues);
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.withAppendedPath(uri, rowID.toString());

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
