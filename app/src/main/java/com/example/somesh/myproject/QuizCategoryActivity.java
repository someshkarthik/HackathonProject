package com.example.somesh.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class QuizCategoryActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_category);

        CardView aptitude_cardView = findViewById(R.id.aptitude_cardView_id);
        CardView cse_cardView = findViewById(R.id.cse_cardView_id);
        CardView mech_cardView = findViewById(R.id.mech_cardView_id);
        CardView civil_cardView = findViewById(R.id.civil_cardView_id);
        CardView bio_cardView = findViewById(R.id.bio_cardView_id);
        CardView electrical_cardView = findViewById(R.id.electrical_cardView_id);

        aptitude_cardView.setOnClickListener(this);
        cse_cardView.setOnClickListener(this);
        mech_cardView.setOnClickListener(this);
        civil_cardView.setOnClickListener(this);
        bio_cardView.setOnClickListener(this);
        electrical_cardView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(QuizCategoryActivity.this, DisplayCategoryActivity.class));
    }
}
