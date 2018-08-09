package com.example.abhishek.version_18.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.VideoView;

import com.example.abhishek.version_18.R;


public class onLineFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public onLineFragment() {
        // Required empty public constructor
    }

    public static onLineFragment newInstance(String param1, String param2) {
        onLineFragment fragment = new onLineFragment();
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
        View v=inflater.inflate(R.layout.fragment_on_line, container, false);
        LinearLayout hobbit=(LinearLayout)v.findViewById(R.id.hobbitlayout);
        LinearLayout smash=(LinearLayout)v.findViewById(R.id.smashlayout);
        LinearLayout galaxy=(LinearLayout)v.findViewById(R.id.galaxylayout);

        hobbit.setOnClickListener(this);
        smash.setOnClickListener(this);
        galaxy.setOnClickListener(this);
        return v;
    }
    @Override
    public void onClick(View v) {
        Fragment des=new eventDescriptionFragment();
        Bundle bundle=new Bundle();
        switch (v.getId()){
            case R.id.hobbitlayout:
                bundle.putString("logo","hobbit");
                bundle.putString("ename","The Hobbit");
                bundle.putString("desc","THE HOBBIT  is an online game. One way to get the most out of it is to look upon it as an adventure .THE HOBBIT- conquering new worlds!  There are always new, grander challenges to confront, and real winner will embrace each one. This game gives you a tremendous chance to show your gaming skills against the other players.");
                bundle.putString("tagline","A mystical venture");
                bundle.putString("rules","Coming Soon !!!");
                des.setArguments(bundle);

                break;
            case R.id.smashlayout:
                bundle.putString("logo","smashandleap");
                bundle.putString("ename","Smash And Leap");
                bundle.putString("desc","Fellow bees! The hibernation is over. Shed your slough of magnanimous condescension and the bridled contumely of the ignorance that kept you from soaring so far. So Don your thinking hats. Turn into Sherlock Holmes. An event where you will have to rack your brains to decode the mystery and get your key to the prize. Gear up for a stimulating mental workout as presents to you. The winner is decided according to the leader board. The participant solving most number of clues in least time is winner.");
                bundle.putString("tagline","Cherish the Sherlock inside you");
                bundle.putString("rules","Coming Soon !!!");
                des.setArguments(bundle);

                break;
            case R.id.galaxylayout:
                bundle.putString("logo","galaxian");
                bundle.putString("ename","Galaxian");
                bundle.putString("desc","'Gal-Axian', as name says, there is a galaxy that has many planets. Participants will have to discover the keys at these planets. One planet may have zero, one or more than one keys. Each key is associated to one or more than one coding problems. First, participant needs to find keys and then code the corresponding problems. Hence this event is like 'online coding event'.");
                bundle.putString("tagline","Explore it, code it");
                bundle.putString("rules","Coming Soon !!!");
                des.setArguments(bundle);

                break;

        }
        FragmentTransaction t = getFragmentManager().beginTransaction();
        t.replace(R.id.frame,des);
        t.addToBackStack("events");
        t.commit();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
