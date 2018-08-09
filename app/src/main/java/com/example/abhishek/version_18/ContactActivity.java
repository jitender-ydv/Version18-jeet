package com.example.abhishek.version_18;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.abhishek.version_18.Utils.Utils;
import com.example.abhishek.version_18.model.Contact;
import com.example.abhishek.version_18.model.Info;
import com.mindorks.placeholderview.ExpandablePlaceHolderView;

import com.example.abhishek.version_18.View.HeadingView;
import com.example.abhishek.version_18.View.InfoView;



public class ContactActivity extends AppCompatActivity {

    private ExpandablePlaceHolderView mExpandableView;
    private Context mContext;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        toolbar= (Toolbar)findViewById(R.id.toolbar_contact);
        toolbar.setTitle(getString(R.string.nav_contactus));
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_back);

        mContext = this.getApplicationContext();
        mExpandableView = (ExpandablePlaceHolderView)findViewById(R.id.expandableView);
        for(Contact contact : Utils.loadContacts(this.getApplicationContext())){
            mExpandableView.addView(new HeadingView(mContext, contact.getHeading()));
            for(Info info : contact.getInfoList()){
                mExpandableView.addView(new InfoView(mContext, info));
            }
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
