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

public class DisplayCategoryActivity extends AppCompatActivity implements View.OnClickListener,
        LoaderManager.LoaderCallbacks<Cursor> {

    //    Identifier for the category data loader
    private static final int CATEGORY_LOADER = 0;

    //    Adapter for the ListView
    CategoryCursorAdapter mCursorAdapter;

    ListView categories_list_lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_sub_category);

        setTitle("Category Display");
        categories_list_lv = findViewById(R.id.quiz_sub_category_lv_id);

        // Setup an Adapter to create a list item for each row of subject data in the Cursor.
        // There is no subject data yet (until the loader finishes) so pass in null for the Cursor.
        mCursorAdapter = new CategoryCursorAdapter(this, null);
        categories_list_lv.setAdapter(mCursorAdapter);

        // Setup the item click listener
        categories_list_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                /*
                //  Developer Mode - For Editing categories
                // Create new intent to go to {@link EditActivity}
                Intent intent = new Intent(DisplayCategoryActivity.this,
                        AddCategoryActivity.class);
                // Form the content URI that represents the specific subject that was clicked on,
                // by appending the "id" (passed as input to this method) onto the
                // {@link SubjectEntry#CONTENT_URI}.
                // For example, the URI would be "content://com.example.android.subjects/subjects/2"
                // if the subject with ID 2 was clicked on.
                Uri currentCategoryUri = ContentUris.withAppendedId(CategoryEntry.CONTENT_URI, id);
                // Set the URI on the data field of the intent
                intent.setData(currentCategoryUri);
                // Launch the {@link EditActivity} to display the data for the current subject.
                startActivity(intent);
                */
                Intent intent = new Intent(DisplayCategoryActivity.this,
                        QuizActivity.class);
                startActivity(intent);
            }
        });

        // Kick off the loader
        getLoaderManager().initLoader(CATEGORY_LOADER, null, this);
    }

    private void insertData() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CategoryEntry.COLUMN_CATEGORY_NAME, "Dummy Data");
        Uri uri = getContentResolver().insert(CategoryEntry.CONTENT_URI, contentValues);
    }

    //    Helper method to delete all subject in the database.
    private void deleteAllCategories() {
        int rowsDeleted = getContentResolver().delete(CategoryEntry.CONTENT_URI,
                null, null);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, AddCategoryActivity.class);
        startActivity(intent);
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

            case R.id.action_insert_data:
                Intent intent = new Intent(this, AddCategoryActivity.class);
                startActivity(intent);
                return true;

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(DisplayCategoryActivity.this);
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
        // Define a projection that specifies the columns from the table we care about.
        String[] projection = {
                CategoryEntry._ID,
                CategoryEntry.COLUMN_CATEGORY_NAME};
        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                CategoryEntry.CONTENT_URI,   // Provider content URI to query
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Update {@link SubjectCursorAdapter} with this new cursor containing updated subject data
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Callback called when the data needs to be deleted
        mCursorAdapter.swapCursor(null);
    }
}
