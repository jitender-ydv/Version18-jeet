package com.example.abhishek.version_18.Utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.abhishek.version_18.model.Contact;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    private static final String TAG = "Utils";

    public static List<Contact> loadContacts(Context context){
        try{
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            JSONArray array = new JSONArray(loadJsonFromAssets(context,"contacts.json"));
            List<Contact> contactList = new ArrayList<>();
            for(int i=0;i<array.length();i++){
                Contact contact =  gson.fromJson(array.getString(i),Contact.class);
                contactList.add(contact);
            }
            return contactList;
        }catch(Exception e){
            Log.d(TAG,"seedGames parseException " + e);
            e.printStackTrace();
            return null;
        }
    }

    private static String loadJsonFromAssets(Context context,String jsonFile){
        String json = null;
        InputStream is = null;

        try{
            AssetManager manager = context.getAssets();
            Log.d(TAG,"path "+jsonFile);
            is = manager.open(jsonFile);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return json;
    }
}
