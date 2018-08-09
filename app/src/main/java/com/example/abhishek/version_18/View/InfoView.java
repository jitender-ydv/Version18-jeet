package com.example.abhishek.version_18.View;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.expand.ChildPosition;
import com.mindorks.placeholderview.annotations.expand.ParentPosition;
import com.example.abhishek.version_18.R;
import com.example.abhishek.version_18.model.Info;

@Layout(R.layout.contact_list)

public class InfoView {
    @ParentPosition
    private int mParentPosition;

    @ChildPosition
    private int mChildPosition;

    @View(R.id.NameTxt)
    private TextView NameTxt;

    @View(R.id.PhoneTxt)
    private TextView PhoneTxt;

    @View(R.id.EmailTxt)
    private TextView EmailTxt;

    @View(R.id.imageView)
    private ImageView imageView;

    private Info mInfo;
    private Context mContext;

    public InfoView(Context context, Info info) {
        mContext = context;
        mInfo = info;
    }

    @Resolve
    private void onResolved() {
        NameTxt.setText(mInfo.getName());
        PhoneTxt.setText(mInfo.getPhoneNo());
        EmailTxt.setText(mInfo.getEmail_id());
        Glide.with(mContext).load(mInfo.getImageUrl()).into(imageView);
    }
}

