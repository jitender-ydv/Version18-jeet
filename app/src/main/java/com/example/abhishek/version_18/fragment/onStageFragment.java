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

public class onStageFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public onStageFragment() {
        // Required empty public constructor
    }

    public static onStageFragment newInstance(String param1, String param2) {
        onStageFragment fragment = new onStageFragment();
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
        View v=inflater.inflate(R.layout.fragment_on_stage, container, false);
        LinearLayout hammer=(LinearLayout)v.findViewById(R.id.codehammerlayout);
        LinearLayout phoenix=(LinearLayout)v.findViewById(R.id.phoenixlayout);
        LinearLayout turn=(LinearLayout)v.findViewById(R.id.turncoatlayout);

        hammer.setOnClickListener(this);
        phoenix.setOnClickListener(this);
        turn.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        Fragment des=new eventDescriptionFragment();
        Bundle bundle=new Bundle();
        switch (v.getId()){
            case R.id.codehammerlayout:
                bundle.putString("logo","codehammer1");
                bundle.putString("ename","Code Hammer");
                bundle.putString("desc","Think you got craze for bidding.Trust your instincts and play your money to earn money.Bidding isn't easy and it gets more difficult when you are bidding for someone else. Welcome to bid-o-code, a unique competition which stitches the trills of bidding and the challenge of coding into a seamless fabric where the question you bid has to be solved by your teammates.");
                bundle.putString("tagline","Auction beyond action");
                bundle.putString("rules","Coming Soon !!!");
                des.setArguments(bundle);

                break;
            case R.id.phoenixlayout:
                bundle.putString("logo","phoenix");
                bundle.putString("ename","The Phoenix");
                bundle.putString("desc","It's not an event, it's a test of fire. It's not a competition, it's survival of the fittest. It's not about winning, it's about conquering. THE PHOENIX an event to dare for, to die for and to live for. Permanence, perseverance and persistence in spite of all obstacles, discouragement, and impossibilities: It is this, that in all things distinguishes the strong soul from the weak.It's a battle of wits, words and wills.");
                bundle.putString("tagline","Focusing the masterpiece");
                bundle.putString("rules","Coming Soon !!!");
                des.setArguments(bundle);

                break;
            case R.id.turncoatlayout:
                bundle.putString("logo","turncoat");
                bundle.putString("ename","TurnCoat");
                bundle.putString("desc","Turncoat will push you to contradict yourself, and that too convincingly. It appreciates your confusion and rewards you for your inability to pick a side.You will be your own biggest competition.You just need to toggle between for and against multiple times thereby contradicting the previous statements.If you can contradict yourself for long, your argument is so strong. Just don't go wrong, only turn your coat at every beat of gong. Beware your words going to hurt you later!!! Turncoat is a form of debate where the speaker literally debates against himself. The speaker starts by taking a stance on the topic and switches sides after a specific duration of time.");
                bundle.putString("tagline","Debate with yourself");
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
