package blank.djaja_works.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import blank.djaja_works.R;

public class register extends AppCompatActivity {

    public Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Toolbar
        tb = findViewById(R.id.toolbar);
        tb.setTitle(R.string.text_Register);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //end toolbar



    }
}
