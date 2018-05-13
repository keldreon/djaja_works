package blank.djaja_works.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import blank.djaja_works.API.ApiClient;
import blank.djaja_works.API.ApiInterface;
import blank.djaja_works.MainActivity;
import blank.djaja_works.R;
import blank.djaja_works.models.Akun;
import blank.djaja_works.models.DatabaseHelper;
import blank.djaja_works.models.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {
    //public Toolbar toolbar;
    private static final String TAG = login.class.getSimpleName();
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
    private List<Akun> accountsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbhelper = new DatabaseHelper(this);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        
        session = new SessionManager(getApplicationContext());
        //kalau statusnya sejak awal udah login
        if(session.isLoggedIn()){
            session.setLogin(true);
            //session.createLoginSession(h.getEmail(),h.getStatus());
            nIntent = new Intent(login.this, MainActivity.class);
            startActivity(nIntent);
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
                    loginOL(nama,pwdi);
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
        //String cek3 = h.getStatus();
        //Toast.makeText(getApplicationContext(), "Detail : "+cek1+", "+cek2+", "+cek3, Toast.LENGTH_LONG).show();
        if(!nama.isEmpty() && !password.isEmpty()) {
            if (nama.equals(cek1) && password.equals(cek2)) {
                //Toast.makeText(getApplicationContext(), "Detail : "+cek1+", "+cek2+", "+cek3, Toast.LENGTH_LONG).show();
                session.setLogin(true);
                finis(h);
            } else if (cek1.isEmpty() && cek2.isEmpty()){
                Toast.makeText(getApplicationContext(), "Email atau Username Tidak Terdaftar!", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(), "Email atau Username Salah!", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Email atau Username Salah!", Toast.LENGTH_LONG).show();
        }
    }

    public void finis(Akun h){
        /*if(Objects.equals(response, "true")) {*/
        session.createLoginSession(h.getEmail(),h.getNama_lengkap());
        nIntent = new Intent(login.this, MainActivity.class);
        startActivity(nIntent);
        finish();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    final Context c = this;
    public void loginOL(String nama, String password){
        showDialog();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Akun>> call = apiService.getUserLogin(nama, password);
        call.enqueue(new Callback<List<Akun>>() {
            @Override
            public void onResponse(Call<List<Akun>> call, Response<List<Akun>> response) {
                //Toast.makeText(getApplicationContext(),)
                accountsList = response.body();
                hideDialog();
                if (response.body().size() != 0){
                    /*ed = sp.edit();
                    ed.putString("email", accountsList.get(0).getEmail());
                    ed.putBoolean("logged", true);
                    ed.apply();*/
                    session.createLoginSession(accountsList.get(0).getEmail(),accountsList.get(0).getNama_lengkap());
                    Toast.makeText(c, "Akun "+accountsList.get(0).getEmail()+", "+ accountsList.get(0).getNama_lengkap(), Toast.LENGTH_LONG).show();
                    //finish();
                    //startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                }
                else{
                    Toast.makeText(c, "Username, Email atau Password salah", Toast.LENGTH_LONG).show();
                    //dialog.dismiss();
                    return;
                }
            }
            @Override
            public void onFailure(Call<List<Akun>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                Toast.makeText(c, "Ada Yang Salah", Toast.LENGTH_LONG).show();
            }
        });
    }
}
