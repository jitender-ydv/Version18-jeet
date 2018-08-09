package com.example.abhishek.version_18.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.abhishek.version_18.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        LinearLayout offstg=(LinearLayout)view.findViewById(R.id.cardOffstage);
        offstg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment off=new offStageFragment();
                FragmentTransaction t = getFragmentManager().beginTransaction();
                t.replace(R.id.frame,off);
                t.addToBackStack("offstage");
                t.commit();
            }
        });
        LinearLayout onstg=(LinearLayout)view.findViewById(R.id.cardOnstage);
        onstg.  setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment on=new onStageFragment();
                FragmentTransaction t = getFragmentManager().beginTransaction();
                t.replace(R.id.frame,on);
                t.addToBackStack("onstage");
                t.commit();
            }
        });
        LinearLayout sub=(LinearLayout) view.findViewById(R.id.cardSubmission);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment submiss=new submissionFragment();
                FragmentTransaction t = getFragmentManager().beginTransaction();
                t.replace(R.id.frame,submiss);
                t.addToBackStack("submission");
                t.commit();
            }
        });
        LinearLayout online=(LinearLayout)view.findViewById(R.id.cardOnline);
        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment onln=new onLineFragment();
                FragmentTransaction t = getFragmentManager().beginTransaction();
                t.replace(R.id.frame,onln);
                t.addToBackStack("online");
                t.commit();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
