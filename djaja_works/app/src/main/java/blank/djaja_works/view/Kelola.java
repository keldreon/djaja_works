package blank.djaja_works.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import blank.djaja_works.profilakun;
import blank.djaja_works.R;
import blank.djaja_works.models.SessionManager;


public class Kelola extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button btnLogout;
    private Button btnAddusaha;
    private SessionManager session;
    private Intent intent, inten1;
    private String mTes1;
    private Button btntopUp, btnProf;

    public Kelola() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Kelola.
     */
    // TODO: Rename and change types and number of parameters
    public static Kelola newInstance(String param1, String param2) {
        Kelola fragment = new Kelola();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kelola, container, false);
        session = new SessionManager(getContext());
        btnLogout = view.findViewById(R.id.btnTest4);
        btnLogout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

        /*mTes1 = session.getStatus();
        if(mTes1.equals("Pengusaha")) {
            btnAddusaha = view.findViewById(R.id.btnAddUsaha);
            btnAddusaha.setVisibility(view.VISIBLE);
            btnAddusaha.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent(getContext(), add_usaha.class);
                    startActivity(intent);
                }
            });
        }*/

        btnProf = view.findViewById(R.id.btnProfil);
        btnProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inten1 = new Intent(getContext(), profilakun.class);
                startActivity(inten1);
            }
        });

        btntopUp = view.findViewById(R.id.btnIsi);
        btntopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), topup.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    private void logoutUser() {
        session.setLogin(false);
        session.logoutUser();
        // Launching the login activity
        intent = new Intent(getActivity().getApplicationContext(), login.class);
        startActivity(intent);
        getActivity().finish();
    }
}
