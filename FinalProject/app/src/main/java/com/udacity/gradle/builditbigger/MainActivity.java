package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import zimmermann.larissa.androidjokes.JokesActivity;
import zimmermann.larissa.jokes.JokesProvider;


public class MainActivity extends AppCompatActivity implements AsyncResponse{

    private EndpointsAsyncTask endpointsAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        endpointsAsyncTask = new EndpointsAsyncTask(this);
        endpointsAsyncTask.execute();
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
        //JokesProvider jokesProvider = new JokesProvider();
        //String joke = jokesProvider.tellJoke();
        //Toast.makeText(this, jokesProvider.tellJoke(), Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "NEW PERFORMANCE", Toast.LENGTH_SHORT).show();

        String joke = "TESTEEEEEEE";
        try {
            joke = endpointsAsyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (joke != null) {
            //Starting JokeActivity
            Intent intent = new Intent(this, JokesActivity.class);
            Bundle b = new Bundle();
            b.putString(JokesProvider.JOKE, joke);
            intent.putExtras(b);
            startActivity(intent);
        }
    }


    @Override
    public void processFinish(String output) {

    }
}
