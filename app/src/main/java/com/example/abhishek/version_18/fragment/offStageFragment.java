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

public class offStageFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public offStageFragment() {
        // Required empty public constructor
    }
    public static offStageFragment newInstance(String param1, String param2) {
        offStageFragment fragment = new offStageFragment();
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
        View v=inflater.inflate(R.layout.fragment_off_stage, container, false);
        LinearLayout prix=(LinearLayout)v.findViewById(R.id.codeprixlayout);
        LinearLayout block=(LinearLayout)v.findViewById(R.id.mindblocklayout);
        LinearLayout web=(LinearLayout)v.findViewById(R.id.weblayout);
        LinearLayout sequal=(LinearLayout)v.findViewById(R.id.sqllayout);
        LinearLayout games=(LinearLayout)v.findViewById(R.id.gameslayout);
        LinearLayout relay=(LinearLayout)v.findViewById(R.id.coderelaylayout);


        prix.setOnClickListener(this);
        relay.setOnClickListener(this);
        block.setOnClickListener(this);
        web.setOnClickListener(this);
        sequal.setOnClickListener(this);
        games.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        Fragment des=new eventDescriptionFragment();
        Bundle bundle=new Bundle();
        switch (v.getId()){
            case R.id.codeprixlayout:
                bundle.putString("logo","codeprix");
                bundle.putString("ename","CodePrix");
                bundle.putString("desc","Speed defines everything even your chance of winning this event. Codeprix is a form of coding competition where speed and accuracy matter the most. Slow and Steady is not the recipe for success you have to be fast and energetic. In the competition each participating team will be given one question at a time and once the question is completely submitted by any team then the question get locked for everyone and next question will be given . The one with maximum points at the end will be declared as the winner.");
                bundle.putString("tagline","Decode it before your opponent does");
                bundle.putString("rules","Coming Soon !!!");
                des.setArguments(bundle);

                break;
            case R.id.coderelaylayout:
                bundle.putString("logo","coderelay");
                bundle.putString("ename","CodeRelay");
                bundle.putString("desc","We believe 'Talent wins game but teamwork wins championship' so, to enhance your team spirit and examine your coordination we are here with this team event named 'Code Relay'. Cracking the code problem alone is easy but the talent is when you understand your teammate's code and contribute your mind into it to win the battle. So, if you aim above the mark, come and grab this opportunity to showcase your hidden talent.");
                bundle.putString("tagline","Strategies for success");
                bundle.putString("rules","Coming Soon !!!");
                des.setArguments(bundle);

                break;
            case R.id.mindblocklayout:
                bundle.putString("logo","mindblock");
                bundle.putString("ename","MindBlock");
                bundle.putString("desc","Great stories are the cornerstone of every great event, but harnessing their true potential is often overlooked in the planning phase. Coding an algorithm is easy but cracking a code from a story is a mystery. If your teammate is complementary and not contradictory then Mindblock is the platform to create momentum and win the contest. So think about how you can combine your potential with your teammate's intellect by telling a story in a unique way.");
                bundle.putString("tagline","Untangling the mystery");
                bundle.putString("rules","Coming Soon !!!");
                des.setArguments(bundle);

                break;
            case R.id.weblayout:
                bundle.putString("logo","concevoir");
                bundle.putString("ename","Concevoir Le Web");
                bundle.putString("desc","Creativity is a process not a random. Concevoir le web is an event to nurture your creative ideas and give them a platform to be showcased upon. And when Designing meets Coding, together they create great works. Colours, themes, images, texts and innovative ideas are the backbone of this event. So, if you carry all these come forward and harness your creativity.");
                bundle.putString("tagline","Creativity beyond imagination");
                bundle.putString("rules","Coming Soon !!!");
                des.setArguments(bundle);

                break;
            case R.id.sqllayout:
                bundle.putString("logo","sequelize");
                bundle.putString("ename","Sequalize");
                bundle.putString("desc","SELECT, JOIN and VIEW. Are these keywords your favorite? If big databases don't scare you and database management brings out the best in you then you are up for a treat. Because we bring you an event where you will be tested on database concepts and SQL. Quench your thrust by querying through two rounds in order to be crowned as champion. So, expect the unexpected, gear up with all your armour and brace up for DBMS.");
                bundle.putString("tagline","Be a data digger");
                bundle.putString("rules","Coming Soon !!!");
                des.setArguments(bundle);
                break;
            case R.id.gameslayout:
                bundle.putString("logo","gamerseden_new");
                bundle.putString("ename","Gamers Eden");
                bundle.putString("desc","I don't need to get a life, I'm a gamer, I have lots of lives!'. Version'18 welcomes you to the ultimate gaming arena! Escape the reality and land into the virtual world of survival. Team up! Push yourself further than you have ever before to see the face of your next nemesis. You have to face the bests in the business and then win to be crowned the victor.");
                bundle.putString("tagline","New dimension of battle");
                bundle.putString("rules","Coming Soon !!!");
                des.setArguments(bundle);

                break;
        }
        FragmentTransaction t = getFragmentManager().beginTransaction();
        t.replace(R.id.frame,des);
        t.addToBackStack("events");
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
