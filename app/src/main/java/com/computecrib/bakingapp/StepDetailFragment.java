package com.computecrib.bakingapp;


import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StepDetailFragment extends Fragment {
    @BindView(R.id.pv_step_media)
    PlayerView mPlayerView;
    @BindView(R.id.cv_step_detail)
    CardView mCardViewStepDetail;
    @BindView(R.id.cv_step_navigation) CardView mCardViewStepNavigation;
    private SimpleExoPlayer player;

    public StepDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        return inflater.inflate(R.layout.fragment_step_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        player = ExoPlayerFactory.newSimpleInstance(this,
                new DefaultTrackSelector());
        mPlayerView.setPlayer(player);
        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "BakingApp"));
        MediaSource mediaSource = new ExtractorMediaSource
                .Factory(dataSourceFactory)
                .createMediaSource(Uri.parse("https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4"));
        player.prepare(mediaSource);
        player.setPlayWhenReady(true);
        mPlayerView.setBackgroundColor(Color.parseColor("#133452"));
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mCardViewStepDetail.setVisibility(View.INVISIBLE);
            mCardViewStepNavigation.setVisibility(View.INVISIBLE);
            ViewGroup.LayoutParams params = mPlayerView.getLayoutParams();
            params.width = params.MATCH_PARENT;
            params.height = params.WRAP_CONTENT;
            mPlayerView.setLayoutParams(params);
            ((AppCompatActivity) this).getSupportActionBar().hide();
            getWindow().getDecorView().setBackgroundColor(Color.BLACK);

        }

}
