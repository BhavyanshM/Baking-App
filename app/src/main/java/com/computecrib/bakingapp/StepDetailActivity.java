package com.computecrib.bakingapp;

import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepDetailActivity extends AppCompatActivity {

    @BindView(R.id.pv_step_media)
    PlayerView mPlayerView;
    private SimpleExoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
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
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPlayerView.setPlayer(null);
        player.release();
        player = null;
    }
}
