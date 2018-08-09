package com.example.abhishek.version_18;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.abhishek.version_18.helper.VolleySingleton;
import com.example.abhishek.version_18.model.Member;
import com.example.abhishek.version_18.model.User;
import com.example.abhishek.version_18.other.SharedPrefManager;
import com.example.abhishek.version_18.other.URLs;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity {

    private EditText editTextName,editTextClgname,editTextContact,editTextEmail,
            editTextPassword,edittextConfirmPwd,edittexttname,editTextgname,editTextgphone;

    private RadioGroup radioGroupGuardian;
    private RadioButton radioButton,radioButton1;

    private Spinner spinner;
    private LinearLayout linearLayout,linearitem[],guardianlist;
    private ProgressDialog pDialog;
    private CheckBox checkBox;

    private EditText edittextMember_name[],editTextMember_phone[];
    private RadioGroup radioGroupsMember[];
    private RadioGroup radiogroupGuardian;
    private RadioButton radioButtonMember[];
    private HashMap<Integer,String> mp;
    private int count=1;
    private ArrayList<Member> members;
    private String gname,gphone,ggender="Male";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        radiogroupGuardian= ((RadioGroup) findViewById(R.id.radiogroupGuardian));
        editTextMember_phone = new EditText[5];
        for(int i=1;i<=5;i++){
            int layoutid = getResources().getIdentifier("edittextphone"+i,"id",
                    getPackageName());
            editTextMember_phone[i-1] = findViewById(layoutid);
        }

        edittextMember_name = new EditText[5];
        for(int i=1;i<=5;i++){
            int layoutid = getResources().getIdentifier("edittextname"+i,"id",
                    getPackageName());
            edittextMember_name[i-1] = findViewById(layoutid);
        }

        radioGroupsMember = new RadioGroup[5];
        for(int i=1;i<=5;i++){
            int layoutid = getResources().getIdentifier("radiogroup"+i,"id",
                    getPackageName());
            radioGroupsMember[i-1] = findViewById(layoutid);
        }

        radioButtonMember = new RadioButton[5];
        for(int i=1;i<=5;i++){
            radioButtonMember[i-1]=null;
        }

        members = new ArrayList<Member>();

        spinner = findViewById(R.id.memberCountSpinner);
        linearLayout = findViewById(R.id.memberListlayout);
        checkBox = findViewById(R.id.checkbox);
        guardianlist = findViewById(R.id.guardianlist);

        mp = new HashMap<Integer, String>();
        //add element to hashmap
        mp.put(1,"One");
        mp.put(2,"Two");
        mp.put(3,"Three");
        mp.put(4,"Four");
        mp.put(5,"Five");

        //Spinner drop down elements
        List<String> no_of_members = new ArrayList<String>();
        no_of_members.add("One");
        no_of_members.add("Two");
        no_of_members.add("Three");
        no_of_members.add("Four");
        no_of_members.add("Five");

        //Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,no_of_members);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        linearitem = new LinearLayout[5];
        for(int i=1;i<=5;i++){
            int layoutid = getResources().getIdentifier("memberitem"+i,"id",
                    getPackageName());
            linearitem[i-1] = findViewById(layoutid);
        }

        //Spinner Click Listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if(!members.isEmpty()){
                    members.clear();
                }
                String item = parent.getItemAtPosition(position).toString();
                int i = position+1;
                count = i;
                toogleVisibility();
                while(i<5){
                    linearitem[i].setVisibility(View.GONE);
                    i++;
                }

                i=0;
                while(i<count){
                    edittextMember_name[i].setText("");
                    editTextMember_phone[i].setText("");
                    i++;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Checkbox Listener
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    guardianlist.setVisibility(View.VISIBLE);
                    editTextgname = findViewById(R.id.edittextgname);
                    editTextgphone = findViewById(R.id.edittextgphone);
                }else{
                    guardianlist.setVisibility(View.GONE);
                    editTextgname = null;
                    editTextgphone = null;
                }
            }
        });



        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
            return;
        }


        editTextClgname = findViewById(R.id.editTextClgName);
        editTextName = findViewById(R.id.editTextName);
        editTextContact = findViewById(R.id.editTextContact);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        edittextConfirmPwd = findViewById(R.id.edittextConfirmPwd);
        edittexttname = findViewById(R.id.edittexttname);
        radioGroupGuardian = findViewById(R.id.radiogroupGuardian);






        findViewById(R.id.buttonRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        findViewById(R.id.textViewLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
    }

    public void registerUser(){
        final String clgname = editTextClgname.getText().toString().trim();
        final String name = editTextName.getText().toString().trim();
        final String contact = editTextContact.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String confirmedPwd = edittextConfirmPwd.getText().toString().trim();
        final String tname = edittexttname.getText().toString().trim();
        final String memberlist;

        if(editTextgname!=null && editTextgphone!=null){
            gname = editTextgname.getText().toString().trim();
            gphone = editTextgphone.getText().toString().trim();
            if (radiogroupGuardian.getCheckedRadioButtonId()!=-1) {
                ggender = ((RadioButton) findViewById(radiogroupGuardian.getCheckedRadioButtonId())).getText().toString().trim();
            }

        }else{
            gname = "No Guardian";
            gphone = "0000000000";
            ggender = "Female";
        }

        if(TextUtils.isEmpty(clgname)){
            editTextClgname.setError("Please enter college name");
            editTextClgname.requestFocus();
            return;
        }

        if(!(clgname.length() >= 3  && validateName(clgname))){
            editTextClgname.setError("Please enter a valid college name");
            editTextClgname.requestFocus();
            return;
        }


        if(TextUtils.isEmpty(name)){
            editTextName.setError("Please enter your name");
            editTextName.requestFocus();
            return;
        }

        if(!(name.length() >= 3  && validateName(name))){
            editTextClgname.setError("Please enter a valid name");
            editTextClgname.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(email)){
            editTextEmail.setError("Please enter college email");
            editTextEmail.requestFocus();
            return;
        }

        if(!isValidEmail(email)){
            editTextEmail.setError("Please enter valid college email id");
            editTextEmail.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(password)){
            editTextPassword.setError("Please enter password");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length()<6){
            editTextPassword.setError("Password must at least 6 character");
            editTextPassword.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(confirmedPwd)){
            edittextConfirmPwd.setError("Please enter confirm password");
            edittextConfirmPwd.requestFocus();
            return;
        }

        if(!confirmedPwd.equals(password)){
            edittextConfirmPwd.setError("Please enter correct confirm password");
            edittextConfirmPwd.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(contact)){
            editTextContact.setError("Please enter contact");
            editTextContact.requestFocus();
            return;
        }

        if(!(contact.length()==10 && isValidMobile(contact))){
            editTextContact.setError("Please enter valid contact");
            editTextContact.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(tname)){
            edittexttname.setError("Please enter team name");
            edittexttname.requestFocus();
            return;
        }

        if(!(tname.length() >= 3  && validateTeamName(tname))){
            editTextClgname.setError("Please enter a valid team name");
            editTextClgname.requestFocus();
            return;
        }

        int i=0;
        while(i<count) {
            String mname = edittextMember_name[i].getText().toString().trim();
            String mphone = editTextMember_phone[i].getText().toString().trim();
            String mgender;
            if (TextUtils.isEmpty(mname)) {
                edittextMember_name[i].setError("Please enter member name");
                edittextMember_name[i].requestFocus();
                return;
            }

            if(!(mname.length() >= 3  && validateName(mname))){
                editTextClgname.setError("Please enter a valid member name");
                editTextClgname.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(mphone)) {
                editTextMember_phone[i].setError("Please enter member phone");
                editTextMember_phone[i].requestFocus();
                return;
            }

            if(!(mphone.length()==10 && isValidMobile(mphone))){
                editTextMember_phone[i].setError("Please enter valid contact");
                editTextMember_phone[i].requestFocus();
                return;
            }

            mgender = ((RadioButton) findViewById(radioGroupsMember[i].getCheckedRadioButtonId())).getText().toString().trim();
            Member member = new Member(mname,mphone,mgender);
            members.add(i,member);
            i++;
        }

        if(checkBox.isChecked()){
            if(TextUtils.isEmpty(gname)){
                editTextgname.setError("Please enter guardian name");
                editTextgname.requestFocus();
                return;
            }

            if(!(gname.length() >= 3  && validateName(gname))){
                editTextClgname.setError("Please enter a valid guardian name");
                editTextClgname.requestFocus();
                return;
            }

            if(TextUtils.isEmpty(gphone)){
                editTextgphone.setError("Please enter guardian phone");
                editTextgphone.requestFocus();
                return;
            }

            if(!(gphone.length()==10 && isValidMobile(gphone))){
                editTextgphone.setError("Please enter valid contact");
                editTextgphone.requestFocus();
                return;
            }
        }

        memberlist = new Gson().toJson(members);
        members.clear();
        //Toast.makeText(RegisterActivity.this,memberlist,Toast.LENGTH_LONG).show();
        pDialog.setMessage("Registering ...");
        showDialog();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            hideDialog();

                            if (!jsonObject.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"),
                                        Toast.LENGTH_SHORT).show();
                                JSONObject userJson = jsonObject.getJSONObject("user");

                                User user = new User(
                                        userJson.getString("name"),
                                        userJson.getString("clgName"),
                                        userJson.getString("contact"),
                                        userJson.getString("email"),
                                        userJson.getString("tname"),
                                        userJson.getString("gname"),
                                        userJson.getString("gphone"),
                                        userJson.getString("ggender")
                                );

                                Toast.makeText(getApplicationContext(), user.toString(),
                                        Toast.LENGTH_SHORT).show();

                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                                finish();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage()+"Connection", Toast.LENGTH_SHORT).show();
                        hideDialog();
                    }

                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("clgName",clgname);
                params.put("password",password);
                params.put("email", email);
                params.put("tname",tname);
                params.put("memberlist",memberlist);
                params.put("contact",contact);
                params.put("gname",gname);
                params.put("gphone",gphone);
                params.put("ggender",ggender);
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }


    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    void toogleVisibility(){
        for(int i=0;i<5;i++){
            linearitem[i].setVisibility(View.VISIBLE);
        }
    }

    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validateName(String name){
        String regx = "^[a-zA-Z\\s]*$";
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }

    private boolean validateTeamName(String tname){
        String regx = "^[a-zA-Z_$][a-zA-Z_$0-9]*$";
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(tname);
        return matcher.find();
    }

    private boolean isValidMobile(String phone) {
        return Patterns.PHONE.matcher(phone).matches();
    }
}
