package com.example.somesh.myproject;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.somesh.myproject.data.Contract;

public class DevQuizActivity extends AppCompatActivity {

    TextView question_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev_quiz);
        question_tv = findViewById(R.id.question_tv_id);
        question_tv.setMovementMethod(new ScrollingMovementMethod());
    }

    private void insertData() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.CategoryEntry.COLUMN_CATEGORY_NAME, "Dummy Data");
        Uri uri = getContentResolver().insert(Contract.CategoryEntry.CONTENT_URI, contentValues);
    }

    //    Helper method to delete all subject in the database.
    private void deleteAllCategories() {
        int rowsDeleted = getContentResolver().delete(Contract.CategoryEntry.CONTENT_URI,
                null, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_question, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_questions:
                // Pop up confirmation dialog for deletion
                showDeleteConfirmationDialog();
                return true;

            case R.id.action_insert_dummy_question:
                insertData();
                return true;

            case R.id.action_insert_question:
                Intent intent = new Intent(this, AddQuestionActivity.class);
                startActivity(intent);
                return true;

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(DevQuizActivity.this);
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

}
