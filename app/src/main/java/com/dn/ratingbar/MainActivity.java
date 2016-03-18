package com.dn.ratingbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.dn.ratingbar.view.CustomRatingBar;

public class MainActivity extends AppCompatActivity implements CustomRatingBar.OnStarChangeListener {

    protected CustomRatingBar mRb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRb = (CustomRatingBar) findViewById(R.id.rb);
        assert mRb != null;
        mRb.setOnStarChangeListener(this);
    }

    @Override
    public void onStarChange(CustomRatingBar ratingBar, float star) {
        Log.d("MainActivity", "star:" + star);
    }
}
