package com.example.somesh.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

public class QuizSubCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_sub_category);
        setTitle("Category");
        ListView quiz_sub_category_lv = findViewById(R.id.quiz_sub_category_lv_id);
        quiz_sub_category_lv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                startActivity(new Intent(QuizSubCategoryActivity.this, QuizInfoActivity.class));
                return false;
            }
        });
    }
}
