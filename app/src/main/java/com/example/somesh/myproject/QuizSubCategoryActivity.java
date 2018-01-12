package com.example.somesh.myproject;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.somesh.myproject.database.CategoryContract;
import com.example.somesh.myproject.database.CategoryCursor;

public class QuizSubCategoryActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private final static int Url_loader = 0;
    CategoryCursor categoryCursor;

    private String[] projection = {
            CategoryContract.categoryListEntry._id,
            CategoryContract.categoryListEntry.COLUMN_CATEGORY_NAME,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_sub_category);
        setTitle("Category");
        insertData();
        ListView quiz_sub_category_lv = findViewById(R.id.quiz_sub_category_lv_id);
        CategoryCursor categoryCursor = new CategoryCursor(this, null);
        quiz_sub_category_lv.setAdapter(categoryCursor);
        quiz_sub_category_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(QuizSubCategoryActivity.this, QuizInfoActivity.class));
            }
        });
        getLoaderManager().initLoader(Url_loader, null, this);

    }

    private void insertData() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CategoryContract.categoryListEntry.COLUMN_CATEGORY_NAME, "Java");
        callContentResolver(contentValues);
    }

    private void callContentResolver(ContentValues contentValues) {
        Uri uri;
        uri = getContentResolver().insert(CategoryContract.categoryListEntry.Category_Content_Uri, contentValues);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        switch (i) {
            case Url_loader:
                return new CursorLoader(this, CategoryContract.categoryListEntry.Category_Content_Uri, projection, null, null, null);
            default:
                return null;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        categoryCursor.swapCursor(cursor);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        categoryCursor.swapCursor(null);

    }
}
