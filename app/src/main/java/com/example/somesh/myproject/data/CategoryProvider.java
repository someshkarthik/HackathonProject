package com.example.somesh.myproject.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.somesh.myproject.data.Contract.CategoryEntry;
import com.example.somesh.myproject.data.Contract.QuestionsEntry;

public class CategoryProvider extends ContentProvider {

    private static final int CATEGORIES = 100;
    private static final int CATEGORIES_ID = 101;
    private static final int QUESTIONS = 200;
    private static final int QUESTIONS_ID = 201;


    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(Contract.CONTENT_AUTHORITY, Contract.PATH_CATEGORIES, CATEGORIES);
        sUriMatcher.addURI(Contract.CONTENT_AUTHORITY, Contract.PATH_CATEGORIES + "/#", CATEGORIES_ID);
        sUriMatcher.addURI(Contract.CONTENT_AUTHORITY, Contract.PATH_QUESTIONS, QUESTIONS);
        sUriMatcher.addURI(Contract.CONTENT_AUTHORITY, Contract.PATH_QUESTIONS + "/#", QUESTIONS_ID);
    }

    private DbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new DbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        Cursor cursor;

        int match = sUriMatcher.match(uri);
        switch (match) {
            case CATEGORIES:
                cursor = database.query(CategoryEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case CATEGORIES_ID:
                selection = CategoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                cursor = database.query(CategoryEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case QUESTIONS:
                cursor = database.query(QuestionsEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case QUESTIONS_ID:
                selection = CategoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                cursor = database.query(QuestionsEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case CATEGORIES:
                return insertCategory(uri, contentValues);
            case QUESTIONS:
                return insertQuestion(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertCategory(Uri uri, ContentValues values) {
        String name = values.getAsString(Contract.CategoryEntry.COLUMN_CATEGORY_NAME);
        if (name == null) {
            throw new IllegalArgumentException("Category requires a name");
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        long id = database.insert(Contract.CategoryEntry.TABLE_NAME, null, values);
        if (id == -1) {
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    private Uri insertQuestion(Uri uri, ContentValues values) {
        String name = values.getAsString(QuestionsEntry.COLUMN_Q_NO);
        if (name == null) {
            throw new IllegalArgumentException("Category requires a name");
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        long id = database.insert(Contract.CategoryEntry.TABLE_NAME, null, values);
        if (id == -1) {
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case CATEGORIES:
                return updateCategory(uri, contentValues, selection, selectionArgs);
            case CATEGORIES_ID:
                selection = Contract.CategoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateCategory(uri, contentValues, selection, selectionArgs);
            case QUESTIONS:
                return updateQuestion(uri, contentValues, selection, selectionArgs);
            case QUESTIONS_ID:
                selection = Contract.CategoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateQuestion(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    private int updateQuestion(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // If the {@link SubjectEntry#COLUMN_SUBJECT_NAME} key is present,
        // check that the name value is not null.
        if (values.containsKey(QuestionsEntry.COLUMN_Q_NO)) {
            String name = values.getAsString(QuestionsEntry.COLUMN_Q_NO);
            if (name == null) {
                throw new IllegalArgumentException("Expense requires a label");
            }
        }

        // If there are no values to update, then don't try to update the database
        if (values.size() == 0) {
            return 0;
        }

        // Otherwise, get writeable database to update the data
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Perform the update on the database and get the number of rows affected
        int rowsUpdated = database.update(QuestionsEntry.TABLE_NAME, values, selection, selectionArgs);

        // If 1 or more rows were updated, then notify all listeners that the data at the
        // given URI has changed
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        // Return the number of rows updated
        return rowsUpdated;
    }

    private int updateCategory(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (values.containsKey(Contract.CategoryEntry.COLUMN_CATEGORY_NAME)) {
            String name = values.getAsString(Contract.CategoryEntry.COLUMN_CATEGORY_NAME);
            if (name == null) {
                throw new IllegalArgumentException("Category requires a name");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(Contract.CategoryEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Get writeable database
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Track the number of rows that were deleted
        int rowsDeleted;

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case CATEGORIES:
                rowsDeleted = database.delete(CategoryEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case CATEGORIES_ID:
                selection = Contract.CategoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(CategoryEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case QUESTIONS:
                rowsDeleted = database.delete(QuestionsEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case QUESTIONS_ID:
                selection = Contract.CategoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(QuestionsEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
        // If 1 or more rows were deleted, then notify all listeners that the data at the
        // given URI has changed
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        // Return the number of rows deleted
        return rowsDeleted;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case CATEGORIES:
                return CategoryEntry.CONTENT_LIST_TYPE;
            case CATEGORIES_ID:
                return CategoryEntry.CONTENT_ITEM_TYPE;
            case QUESTIONS:
                return QuestionsEntry.CONTENT_LIST_TYPE;
            case QUESTIONS_ID:
                return QuestionsEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }
}
