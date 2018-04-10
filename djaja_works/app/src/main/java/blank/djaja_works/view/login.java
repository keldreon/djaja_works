package blank.djaja_works.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import blank.djaja_works.MainActivity;
import blank.djaja_works.R;
import blank.djaja_works.models.Akun;
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
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbhelper = new DatabaseHelper(this);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        
        session = new SessionManager(getApplicationContext());
        //kalau statusnya sejak awal udah login
        if(session.isLoggedIn()){
            session.setLogin(true);
            //session.createLoginSession(h.getEmail(),h.getStatus());
            Intent intent = new Intent(login.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

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
                    checklogin(nama,pwdi);
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
        Akun h = dbhelper.getInfoAkun(nama, password);
        String cek1 = h.getEmail();
        String cek2 = h.getPassword();
        String cek3 = h.getStatus();
        Toast.makeText(getApplicationContext(), "Detail : "+cek1+", "+cek2+", "+cek3, Toast.LENGTH_LONG).show();
        if(nama.equals(cek1) && password.equals(cek2)){
            Toast.makeText(getApplicationContext(), "Detail : "+cek1+", "+cek2+", "+cek3, Toast.LENGTH_LONG).show();
            finis(h);
        }else{
            Toast.makeText(getApplicationContext(), "Email atau Username Salah!", Toast.LENGTH_LONG).show();
        }
    }

    public void finis(Akun h){
        /*if(Objects.equals(response, "true")) {*/
        if(session.isLoggedIn()){
            session.setLogin(true);
            session.createLoginSession(h.getEmail(),h.getStatus());
            Intent intent = new Intent(login.this, MainActivity.class);
            startActivity(intent);
            finish();
        }/*else{
            Toast.makeText(getApplicationContext(), "Akun sudah dipakai.", Toast.LENGTH_LONG).show();
        }*/
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
