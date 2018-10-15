package com.github.calvinyan.wastenot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.StringTokenizer;

public class ResultActivity extends AppCompatActivity {

    private static String TAG = "ResultActivity";

    private String classification;

    // The minimum confidence of the top category required to classify the image as that category
    private double cutoff = 0.7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent showResult = this.getIntent();
        classification = showResult.getStringExtra("Result");
        setResultText();
    }

    // Display the text corresponding to the classification result
    private void setResultText() {
        // The text fields we need to programmatically change
        TextView classificationText = findViewById(R.id.textViewClassification);
        TextView resultText = findViewById(R.id.textViewResult);
        TextView explanationText = findViewById(R.id.textViewExplanation);

        // Get the top result
        StringTokenizer tokenizer = new StringTokenizer(classification);
        String category = tokenizer.nextToken();
        double confidence = Double.valueOf(tokenizer.nextToken());

        Log.d(TAG, category + " " + confidence);

        // Are we confident enough about our classification?
        if (confidence >= cutoff) {
            switch(category) {
                case "cardboard":
                    classificationText.setText(R.string.classify_cardboard);
                    resultText.setTextColor(getResources().getColor(R.color.colorAccent));
                    resultText.setText(R.string.result_positive);
                    explanationText.setText(R.string.explanation_cardboard);
                    break;
                case "glass":
                    classificationText.setText(R.string.classify_glass);
                    resultText.setTextColor(getResources().getColor(R.color.colorAccent));
                    resultText.setText(R.string.result_positive);
                    explanationText.setText(R.string.explanation_glass);
                    break;
                case "metal":
                    classificationText.setText(R.string.classify_metal);
                    resultText.setTextColor(getResources().getColor(R.color.colorAccent));
                    resultText.setText(R.string.result_positive);
                    explanationText.setText(R.string.explanation_metal);
                    break;
                case "paper":
                    classificationText.setText(R.string.classify_paper);
                    resultText.setTextColor(getResources().getColor(R.color.colorAccent));
                    resultText.setText(R.string.result_positive);
                    explanationText.setText(R.string.explanation_paper);
                    break;
                case "plastic":
                    classificationText.setText(R.string.classify_plastic);
                    resultText.setTextColor(getResources().getColor(R.color.colorAccent));
                    resultText.setText(R.string.result_positive);
                    explanationText.setText(R.string.explanation_plastic);
                    break;
                case "trash":
                    classificationText.setText(R.string.classify_trash);
                    resultText.setTextColor(getResources().getColor(R.color.colorBlack));
                    resultText.setText(R.string.result_negative);
                    explanationText.setText(R.string.explanation_trash);
                    break;
            }
        } else {
            // Better to play it safe and trash it than incorrectly assume
            classificationText.setText(R.string.classify_trash);
            resultText.setTextColor(getResources().getColor(R.color.colorBlack));
            resultText.setText(R.string.result_negative);
            explanationText.setText(R.string.explanation_trash);
        }
    }
}
