package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.concurrent.ExecutionException;

import zimmermann.larissa.androidjokes.JokesActivity;
import zimmermann.larissa.jokes.JokesProvider;


public class MainActivity extends AppCompatActivity implements AsyncResponse{
    private InterstitialAd interstitialAd;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        if (getPackageName().equals("com.udacity.gradle.testing.free")) {
            MobileAds.initialize(this,
                    "ca-app-pub-3940256099942544~3347511713");

            interstitialAd = new InterstitialAd(this);
            interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
            interstitialAd.loadAd(new AdRequest.Builder().build());

            interstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    // Load the next interstitial.
                    interstitialAd.loadAd(new AdRequest.Builder().build());
                }

            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        spinner.setVisibility(View.VISIBLE);
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(this);
        endpointsAsyncTask.execute();

        if(getPackageName().equals("com.udacity.gradle.testing.free")) {
            interstitialAd.show();
        }
    }


    @Override
    public void processFinish(String output) {
        spinner.setVisibility(View.GONE);
        if (output != null) {
            //Starting JokeActivity
            spinner.setVisibility(View.GONE);
            Intent intent = new Intent(this, JokesActivity.class);
            Bundle b = new Bundle();
            b.putString(JokesProvider.JOKE, output);
            intent.putExtras(b);
            startActivity(intent);
        }
    }
}
