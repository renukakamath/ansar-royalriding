package com.example.royalriding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class ViewPackages extends AppCompatActivity implements JsonResponse {
    ListView l1;
    SharedPreferences sh;
    String [] packagename,Amount,details,place,value,place_id;
    public static String tid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_packages);
        l1=(ListView) findViewById(R.id.list);


        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) ViewPackages.this;
        String q = "/ViewPackages?login_id="+sh.getString("log_id","" )+"&place_id="+Searchplace.pid;
        q = q.replace(" ", "%20");
        JR.execute(q);
    }

    @Override
    public void response(JSONObject jo) {
        try {

            String status = jo.getString("status");
            Log.d("pearl", status);


            if (status.equalsIgnoreCase("success")) {
                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                packagename = new String[ja1.length()];
                Amount = new String[ja1.length()];
                details = new String[ja1.length()];
                place = new String[ja1.length()];



                value = new String[ja1.length()];


                String[] value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {
                    packagename[i] = ja1.getJSONObject(i).getString("packagename");
                    Amount[i] = ja1.getJSONObject(i).getString("Amount");
                    details[i] = ja1.getJSONObject(i).getString("details");
                    place[i] = ja1.getJSONObject(i).getString("place");


                    value[i] = "packagename:" + packagename[i] + "\nAmount: " + Amount[i] + "\n details: " + details[i] + "\nplace: " + place[i];

                }
                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), R.layout.custtext, value);

                l1.setAdapter(ar);

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();

        }
    }
}