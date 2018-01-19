package com.example.somesh.myproject;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.somesh.myproject.data.Contract.CategoryEntry;

public class QuizSubCategoryActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private final static int CATEGORY_LOADER = 0;
    CategoryCursorAdapter categoryCursorAdapter;
    ListView quiz_sub_category_lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_sub_category);

        setTitle("Category");
//        insertData();
        quiz_sub_category_lv = findViewById(R.id.quiz_sub_category_lv_id);
        categoryCursorAdapter = new CategoryCursorAdapter(this, null);
        quiz_sub_category_lv.setAdapter(categoryCursorAdapter);
        quiz_sub_category_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(QuizSubCategoryActivity.this, QuizInfoActivity.class));
            }
        });
        getLoaderManager().initLoader(CATEGORY_LOADER, null, this);
    }

    private void insertData() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CategoryEntry.COLUMN_CATEGORY_NAME, "Java");
        Uri uri = getContentResolver().insert(CategoryEntry.CONTENT_URI, contentValues);
    }

    //    Helper method to delete all subject in the database.
    private void deleteAllCategories() {
        int rowsDeleted = getContentResolver().delete(CategoryEntry.CONTENT_URI,
                null, null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Pop up confirmation dialog for deletion
                showDeleteConfirmationDialog();
                return true;

            case R.id.action_insert_dummy:
                insertData();
                return true;

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(QuizSubCategoryActivity.this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //    Prompt the user to confirm that they want to delete all categories.
    private void showDeleteConfirmationDialog() {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the positive and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Delete all categories?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Delete" button, so delete the category.
                deleteAllCategories();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Cancel" button, so dismiss the dialog
                // and continue editing the category.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                CategoryEntry._ID,
                CategoryEntry.COLUMN_CATEGORY_NAME,
        };
        return new CursorLoader(this,
                CategoryEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        categoryCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        categoryCursorAdapter.swapCursor(null);
    }
}
