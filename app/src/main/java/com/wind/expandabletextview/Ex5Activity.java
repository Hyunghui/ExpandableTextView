package com.wind.expandabletextview;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Ex5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex5);

        ExpandableView expandableTextView = (ExpandableView) findViewById(R.id.Extv_body);

        expandableTextView.setTitleText("ttt");
        expandableTextView.setTitleSize(10);
        expandableTextView.setTitleColor(ContextCompat.getColor(this, android.R.color.holo_blue_light));

        expandableTextView.setMarginBetweenTitleAndTextBody(30);
        expandableTextView.setTextBodyText("hahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahaha");
        expandableTextView.setTextBodySize(20);
        expandableTextView.setTextBodyColor(ContextCompat.getColor(this, R.color.colorAccent));
        expandableTextView.setTextBodyMaxLines(7);
        expandableTextView.setTextBodyMinLines(1);

        expandableTextView.setMarginBetweenTextBodyAndShowMoreLess(100);
        expandableTextView.setShowLessText("Read Less");
        expandableTextView.setShowMoreText("Read More");
        expandableTextView.setShowMoreLessSize(30);
        expandableTextView.setTextBodyColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
    }
}
