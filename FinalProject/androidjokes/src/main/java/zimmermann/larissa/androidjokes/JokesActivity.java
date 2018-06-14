package zimmermann.larissa.androidjokes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import zimmermann.larissa.jokes.JokesProvider;

public class JokesActivity extends AppCompatActivity {

    private TextView jokesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);

        jokesTextView = findViewById(R.id.jokesTextView);

        String joke = getResources().getString(R.string.no_jokes);
        Intent intent = getIntent();
        if(intent.hasExtra(JokesProvider.JOKE)) {
            Bundle bundle = getIntent().getExtras();
            joke = bundle.getString(JokesProvider.JOKE);
        }

        jokesTextView.setText(joke);



    }
}
