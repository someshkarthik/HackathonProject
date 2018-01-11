package com.example.somesh.myproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    TextView question_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        question_tv = findViewById(R.id.question_tv_id);
        question_tv.setMovementMethod(new ScrollingMovementMethod());
    }
}
