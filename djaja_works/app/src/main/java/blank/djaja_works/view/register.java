package blank.djaja_works.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import blank.djaja_works.API.ApiClient;
import blank.djaja_works.API.ApiInterface;
import blank.djaja_works.R;
import blank.djaja_works.models.Akun;
import blank.djaja_works.models.DatabaseHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register extends AppCompatActivity {

    //hapus no_ktp
    private static final String TAG = register.class.getSimpleName();
    public Toolbar tb;
    private Spinner jk;
    //private Spinner st;
    private Button btnDaftar;
    private EditText em;
    private EditText nL;
    private EditText pss;
    private EditText umr;
    private EditText nktp;
    private EditText nRek;
    private String unVal;
    private String pssVal;
    private String nlVal;
    private String umrVal;
    //private String stVal;
    private String jkVal;
    private String nktpVal;
    private String rekVal;
    private DatabaseHelper dbhelper;
    private ProgressDialog pDialog;
    private List<Akun> accountsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //public Akun(int id, String uname, String email, String pass, String jK, String umur, String nktp, String nrek, String st){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        dbhelper = new DatabaseHelper(this);
        //Toolbar
        tb = findViewById(R.id.toolbar);
        tb.setTitle(R.string.text_Register);
        setSupportActionBar(tb);
        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        //end toolbar
        String jK[] = new String[]{
                "Laki-Laki", "Perempuan"
        };

        String status[] = new String[]{
                "Investor", "Pengusaha"
        };

        jk = (Spinner) findViewById(R.id.jK);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, jK);
        jk.setAdapter(adapter1);


        jk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jkVal = jk.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*st = (Spinner) findViewById(R.id.status);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, status);
        st.setAdapter(adapter2);


        st.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stVal = st.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
        em = findViewById(R.id.emailReg);
        nL = findViewById(R.id.namaReg);
        pss = findViewById(R.id.pwd);
        umr = findViewById(R.id.umur);
        nktp = findViewById(R.id.noKTP);
        nRek = findViewById(R.id.noRekening);

        btnDaftar = findViewById(R.id.btnReg);
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            //public Akun(int id, String uname, String email, String pass, String jK, String umur, String nktp, String nrek, String st){
            @Override
            public void onClick(View v) {
                unVal = em.getText().toString();
                pssVal = pss.getText().toString();
                umrVal = umr.getText().toString();
                nktpVal = nktp.getText().toString();
                rekVal = nRek.getText().toString();
                nlVal = nL.getText().toString();
                //(String email, String pass, String nL, String jK, String umur, String nktp, String nrek, String st)
                //String ts = x.getEmail();
                if (!unVal.isEmpty() && !pssVal.isEmpty() && !nlVal.isEmpty() && !umrVal.isEmpty() && !nktpVal.isEmpty() && !rekVal.isEmpty()) {
                    //Toast.makeText(getApplicationContext(), "Masih ada inputan yang kosong", Toast.LENGTH_LONG).show();
                    Akun x = new Akun(unVal, pssVal, nlVal, jkVal, umrVal, nktpVal, rekVal, 0);
                    regisOL(x);
                    //Toast.makeText(getApplicationContext(),ts,Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Inputan Masih Ada Yang Kosong.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected void daftarkan(Akun x) {
        //daftarAkun(String email, String pass, String nL, String jK, String umr, String nktp, String nrek, String st)
        dbhelper.daftarAkun(x.getEmail(), x.getPassword(), x.getNama_lengkap(), x.getJk(), x.getUmur(), x.getNoKtp(), x.getNoRek(), x.getNom());
        int b = dbhelper.getAkunCount();
        Toast.makeText(this, "Akun = " + x.getEmail() + " Terdaftar, Jumlah akun = " + b, Toast.LENGTH_SHORT).show();
    }

    protected void regisOL(Akun x){
        showDialog();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Akun> call = apiService.postAkun(x.getEmail(),x.getPassword(),x.getNama_lengkap(),x.getUmur(),x.getJk(),x.getNoKtp(),x.getNoRek());
        call.enqueue(new Callback<Akun>() {
            @Override
            public void onResponse(Call<Akun> call, Response<Akun> response) {
                hideDialog();
                Log.d("PostAccounts", "onResponse: " + response.body());
                Toast.makeText(getApplicationContext(), response.body().toString(), Toast.LENGTH_LONG);
                finish();
            }

            @Override
            public void onFailure(Call<Akun> call, Throwable t) {
                Log.d("PostAccounts", "onFailure: " + t);
            }
        });
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }*/

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    public void end(View view) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbhelper.close();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}
