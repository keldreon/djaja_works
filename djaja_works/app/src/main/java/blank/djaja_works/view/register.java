package blank.djaja_works.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import blank.djaja_works.R;

public class register extends AppCompatActivity {

    public Toolbar tb;
    private Spinner jk;
    private Spinner st;
    private String stVal;
    private String jkVal;

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
    }

    protected void daftarkan(){

    }
}
