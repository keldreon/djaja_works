package blank.djaja_works.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import blank.djaja_works.R;
import blank.djaja_works.models.DatabaseHelper;
import blank.djaja_works.models.SessionManager;

public class topup extends AppCompatActivity {
    private TextView isiNama;
    private TextView isiNominal;
    private DatabaseHelper db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup);
        db = new DatabaseHelper(this);
        session = new SessionManager(this);
        String nama = session.getNama();
        isiNama = findViewById(R.id.uName);
        isiNama.setText(nama);
        //isiNominal = findViewById(R.id.nom);
        //db.getA
    }
}
