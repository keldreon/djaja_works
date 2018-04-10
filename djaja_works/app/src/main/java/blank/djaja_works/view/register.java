package blank.djaja_works.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import blank.djaja_works.R;
import blank.djaja_works.models.Akun;
import blank.djaja_works.models.DatabaseHelper;

public class register extends AppCompatActivity {

    public Toolbar tb;
    private Spinner jk;
    private Spinner st;
    private String stVal;
    private String jkVal;
    private Button btnDaftar;
    private EditText em;
    private EditText pss;
    private EditText umr;
    private EditText nktp;
    private EditText nRek;
    private String unVal;
    private String pssVal;
    private String umrVal;
    private String nktpVal;
    private String rekVal;
    private DatabaseHelper dbhelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //public Akun(int id, String uname, String email, String pass, String jK, String umur, String nktp, String nrek, String st){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbhelper = new DatabaseHelper(this);
        //Toolbar
        tb = findViewById(R.id.toolbar);
        tb.setTitle(R.string.text_Register);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //end toolbar
        String jK[] = new String[]{
                "Laki-Laki", "Perempuan"
        };

        String status[] = new String[]{
                "Investor", "Peminjam Modal"
        };

        jk = (Spinner) findViewById(R.id.jK);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, jK);
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

        st = (Spinner) findViewById(R.id.status);
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
        });
        em = findViewById(R.id.emailReg);
        pss = findViewById(R.id.pwd);
        umr= findViewById(R.id.umur);
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
                Akun x = new Akun(unVal, pssVal, jkVal, umrVal, nktpVal, rekVal, stVal);
                String ts = x.getEmail();
                daftarkan(x);
                if(!unVal.isEmpty() && !pssVal.isEmpty() && !nktpVal.isEmpty() && !umrVal.isEmpty() && !rekVal.isEmpty()){
                    //Toast.makeText(getApplicationContext(), "Masih ada inputan yang kosong", Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),ts,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Inputan Masih Ada Yang Kosong.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected void daftarkan(Akun x){
        //daftarAkun(String email, String jK, String nktp, String umr, String nrek, String pass, String st){
        dbhelper.daftarAkun(x.getEmail(), x.getJk(), x.getNoKtp(), x.getUmur(), x.getNoRek(), x.getPassword(), x.getStatus());
        int b = dbhelper.getAkunCount();
        Toast.makeText(this, "Jumlah akun = "+b, Toast.LENGTH_SHORT).show();
    }
}
