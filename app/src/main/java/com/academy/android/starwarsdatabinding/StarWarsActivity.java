package com.academy.android.starwarsdatabinding;

import com.academy.android.starwarsdatabinding.databinding.ActivityStarWarsBinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

public class StarWarsActivity extends AppCompatActivity
        implements View.OnClickListener, StarWarsViewModel.StarWasViewModelListener {

    private StarWarsViewModel mViewModel;

    private Animation mCrawlAnimation;

    private TextView animatedText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStarWarsBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_star_wars);
        mViewModel = new StarWarsViewModel(this, getAssets());
        binding.setData(mViewModel);
        findViewById(R.id.asw_btn_star_wars_logo).setOnClickListener(this);
        animatedText = (TextView) findViewById(R.id.asw_tv_crawl);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewModel.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.onDestroy();
        if (isFinishing()) {
            mViewModel = null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.asw_btn_star_wars_logo:
                mViewModel.onLogoClicked();
                break;
        }
    }

    @Override
    public void showToast() {
        Toast.makeText(this, "Patience you must have my young padawan",
                Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void playAnimation() {
        if (mCrawlAnimation == null) {
            mCrawlAnimation = AnimationUtils.loadAnimation(this, R.anim.crawl_animations);
        }
        animatedText.startAnimation(mCrawlAnimation);
    }
}
