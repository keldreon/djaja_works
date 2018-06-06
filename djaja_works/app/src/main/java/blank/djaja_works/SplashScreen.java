package blank.djaja_works;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import blank.djaja_works.view.login;

public class SplashScreen extends AppCompatActivity {
    //private SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //sm = new SessionManager(this);
        new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashScreen.this,login.class));
                    finish();
                }
            },1500);

    }
}
