package com.example.abhishek.version_18;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.abhishek.version_18.helper.VolleySingleton;
import com.example.abhishek.version_18.model.User;
import com.example.abhishek.version_18.other.SharedPrefManager;
import com.example.abhishek.version_18.other.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextClgname,editTextPassword,editTextPasskey;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            return;
        }

        editTextClgname = findViewById(R.id.editTextClgname);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPasskey = findViewById(R.id.edittextPasskey);

        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                userLogin();
            }
        });

        findViewById(R.id.textViewRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });
    }

    public void userLogin(){
        final String clgName = editTextClgname.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String pass_key = editTextPasskey.getText().toString().trim();

        if (TextUtils.isEmpty(clgName)) {
            editTextClgname.setError("Please enter your college name");
            editTextClgname.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Please enter your password");
            editTextPassword.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(pass_key)) {
            editTextPassword.setError("Please enter your passkey");
            editTextPassword.requestFocus();
            return;
        }

        pDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideDialog();
                        try {
                            Log.d("LoginActivity",response);
                            JSONObject jsonObject = new JSONObject(response);
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

                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                                finish();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
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
                        hideDialog();
                        Toast.makeText(getApplicationContext(), error.getMessage()+"Connection problem", Toast.LENGTH_SHORT).show();
                    }

                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("clgName", clgName);
                params.put("password", password);
                params.put("pass_key",pass_key);
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
}
