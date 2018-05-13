package blank.djaja_works.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import blank.djaja_works.R;
import blank.djaja_works.adapter.MyDividerItemDecoration;
import blank.djaja_works.adapter.adapter;
import blank.djaja_works.models.DatabaseHelper;
import blank.djaja_works.models.Investment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private adapter mAdapter;
    private List<Investment> invList = new ArrayList<>();
    private CoordinatorLayout cdLayout;
    private RecyclerView rcView;
    private TextView noListView;
    private DatabaseHelper db;

    private OnFragmentInteractionListener mListener;

    public ListFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        cdLayout = view.findViewById(R.id.coordinator_layout);
        rcView = view.findViewById(R.id.recycler_view);
        noListView = view.findViewById(R.id.not_found);

        db = new DatabaseHelper(getContext());

        if(invList.size()!=db.getInvCount()){
            invList.clear();
        }
        invList.addAll(db.getAllInvest());

        mAdapter = new adapter(invList,getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        rcView.setLayoutManager(mLayoutManager);
        rcView.setItemAnimator(new DefaultItemAnimator());
        rcView.addItemDecoration(new MyDividerItemDecoration(Objects.requireNonNull(getContext()), LinearLayoutManager.VERTICAL, 16));
        rcView.setAdapter(mAdapter);
        /*int tes = db.getInvCount();
        if (tes > 0) {
            noListView.setVisibility(View.GONE);
        } else {
            noListView.setVisibility(View.VISIBLE);
        }*/

        toggleEmptyNotes();

        return view;
    }

    private void toggleEmptyNotes() {
        int tes = db.getInvCount();
        if (tes > 0) {
            noListView.setVisibility(View.GONE);
        } else {
            noListView.setVisibility(View.VISIBLE);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

/*    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public void createList(){

    }
}
