package com.example.abhishek.version_18.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.abhishek.version_18.R;
public class submissionFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public submissionFragment() {
        // Required empty public constructor
    }
    public static submissionFragment newInstance(String param1, String param2) {
        submissionFragment fragment = new submissionFragment();
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
       View v= inflater.inflate(R.layout.fragment_submission, container, false);
        LinearLayout scib=(LinearLayout)v.findViewById(R.id.scriblelayout);
        LinearLayout photo=(LinearLayout)v.findViewById(R.id.photolayout);
        scib.setOnClickListener(this);
        photo.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        Fragment des=new eventDescriptionFragment();
        Bundle bundle=new Bundle();
        switch (v.getId()){
            case R.id.scriblelayout:
                bundle.putString("logo","scribble");
                bundle.putString("ename","Scribble");
                bundle.putString("desc","Ever sat in a class or lecture and found your pen wandering across your notebook and making a series of patterns? Creativity involves breaking out of established  patterns in order  to look at things in a different way. Scribble is the event to discover and unleash the creativity in you through a doodling masterpiece that comes from a conflict of ideas.");
                bundle.putString("tagline","Unleash the artist");
                bundle.putString("rules","Coming Soon !!!");
                des.setArguments(bundle);

                break;
            case R.id.photolayout:
                bundle.putString("logo","photography");
                bundle.putString("ename","PhotoGraphy");
                bundle.putString("desc","ABC");
                bundle.putString("tagline","XYZ");
                bundle.putString("rules","Coming Soon !!!");
                des.setArguments(bundle);

                break;

        }
        FragmentTransaction t = getFragmentManager().beginTransaction();
        t.replace(R.id.frame,des);
        t.addToBackStack("events1");
        t.commit();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
