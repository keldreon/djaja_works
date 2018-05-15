package blank.djaja_works.view.pengusaha;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import blank.djaja_works.R;
import blank.djaja_works.models.DatabaseHelper;
import blank.djaja_works.models.Investment;
import blank.djaja_works.models.SessionManager;

public class add_usaha extends AppCompatActivity {
    private Toolbar tb;
    private EditText nUsaha;
    private EditText dUsaha;
    private EditText nomModal;
    private EditText gBulanan;
    private Button modali;
    private Button batal;
    private DatabaseHelper db;
    private String nUsahaVal;
    private String dUsahaVal;
    private String nomModalVal;
    private String gBulananVal;
    private int nomModalValParse;
    private int gBulananValParse;
    private SessionManager session;
    private String uName;
    //private Spinner katInv;
    //private String katInvVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_usaha);
        tb = findViewById(R.id.toolbar);
        tb.setTitle(R.string.text_judulAddUsaha);
        setSupportActionBar(tb);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        db = new DatabaseHelper(getApplicationContext());
        session = new SessionManager(getApplicationContext());

        batal = findViewById(R.id.btnBatal);
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                akhiri();
            }
        });

        nUsaha = findViewById(R.id.namaUsaha);
        dUsaha = findViewById(R.id.deskripsiUsaha);
        nomModal = findViewById(R.id.butuhModal);
        gBulanan = findViewById(R.id.hasilBulanan);

        //katInv = findViewById(R.id.katInvest);//spinner untuk kategori invest/crowdfunding

        /*String kat[] = new String[]{
                "Pinjaman Modal", "Sumbangan"
        };
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, kat);
        katInv.setAdapter(adapter1);

        katInv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                katInvVal = katInv.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        modali = findViewById(R.id.btnModali);
        modali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nUsahaVal = nUsaha.getText().toString().trim();
                dUsahaVal = dUsaha.getText().toString().trim();
                nomModalVal = nomModal.getText().toString().trim();
                gBulananVal = gBulanan.getText().toString().trim();
                if(!nUsahaVal.isEmpty() && !dUsahaVal.isEmpty() && !nomModalVal.isEmpty() && !gBulananVal.isEmpty()){
                    nomModalValParse = Integer.parseInt(nomModalVal);
                    gBulananValParse = Integer.parseInt(gBulananVal);
                    uName = session.getNama();
                    Investment x = new Investment(uName,nUsahaVal, dUsahaVal, gBulananValParse, nomModalValParse,"Meminta");
                    daftarUsaha(x);

                }else{
                    Toast.makeText(getApplicationContext(), "Masukkan masih ada yang kosong!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void akhiri(){
        finish();
    }

    public void daftarUsaha(Investment x){
        db.daftarUsaha(x.getNama_lengkap(), x.getNamaUsaha(),x.getDeskripsi(), x.getgBulanan(), x.getNominal(), x.getStatus());
        int baru = db.getInvCount();
        Toast.makeText(getApplicationContext(), "Jumlah Usaha : "+baru, Toast.LENGTH_LONG).show();
        akhiri();
    }
}
