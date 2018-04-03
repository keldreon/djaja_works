package blank.djaja_works.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import blank.djaja_works.MainActivity;
import blank.djaja_works.R;
import blank.djaja_works.models.DatabaseHelper;
import blank.djaja_works.models.SessionManager;

public class login extends AppCompatActivity {
    //public Toolbar toolbar;
    private TextView reg;
    private DatabaseHelper dbhelper;
    private TextView lupa;
    private EditText email;
    private EditText pwd;
    private Intent nIntent;
    private Button btnLogin;
    private String nama;
    private String pwdi;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        reg = findViewById(R.id.textViewLinkRegister);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goRegister();
            }
        });

        btnLogin = findViewById(R.id.btnLogin);
        email = findViewById(R.id.email);
        pwd = findViewById(R.id.password);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = email.getText().toString().trim();
                pwdi = pwd.getText().toString().trim();
                if(!nama.isEmpty() && !pwdi.isEmpty()) {

                }
                else{
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(), "Masukkan masih ada yang kosong!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void goRegister(){
        nIntent = new Intent(getApplicationContext(),register.class);
        startActivity(nIntent);
    }

    public void checklogin(String nama, String password){
        String cek1 = nama;
        String cek2 = password;
        if(password.equals("mobproghore")){
            //Toast.makeText(getApplicationContext(), password, Toast.LENGTH_LONG).show();
            nIntent = new Intent(getBaseContext(), MainActivity.class);
            nIntent.putExtra("uname", nama);
            //gunakan jika kamu tidak memerlukan
            //data dari Activity 2
            //startActivity(mainIntent);

            //gunakan jika Memerlukan data dari activity 2
            startActivity(nIntent);
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "Password " +password+ " Salah!", Toast.LENGTH_LONG).show();
        }
    }

    public void finis(String response){
        if(Objects.equals(response, "true")) {
            session.setLogin(true);
            Intent intent = new Intent(login.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "Akun sudah dipakai.", Toast.LENGTH_LONG).show();
        }
    }
}
