package com.example.royalriding;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class viewrequest extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {
    ListView l1;
    SharedPreferences sh;
    String [] Trip,from_date,to_date,statu,value,trip_id;
    public static String tid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewrequest);
        l1=(ListView) findViewById(R.id.list);
        l1.setOnItemClickListener(this);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) viewrequest.this;
        String q = "/viewrequest?login_id="+sh.getString("log_id","" );
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
                Trip = new String[ja1.length()];
                from_date = new String[ja1.length()];
                to_date = new String[ja1.length()];
                statu = new String[ja1.length()];
                trip_id=new String[ja1.length()];


                value = new String[ja1.length()];


                String[] value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {
                    Trip[i] = ja1.getJSONObject(i).getString("trip");
                    from_date[i] = ja1.getJSONObject(i).getString("from_date");
                    to_date[i] = ja1.getJSONObject(i).getString("to_date");
                    statu[i] = ja1.getJSONObject(i).getString("status");
                    trip_id[i] = ja1.getJSONObject(i).getString("trip_id");




                    value[i] = "trip:" + Trip[i] + "\nfrom_date: " + from_date[i] + "\n to_date: " + to_date[i] + "\nstatu: " + statu[i];

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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        tid=trip_id[i];
        Toast.makeText(getApplicationContext(), tid, Toast.LENGTH_LONG).show();

        final CharSequence[] items = {"Add Photo","Chat","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(viewrequest.this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Add Photo")) {

                    startActivity(new Intent(getApplicationContext(),Uploadimage.class));


                }else if (items[item].equals("Chat")) {

                    startActivity(new Intent(getApplicationContext(),ChatHere.class));
                }
                else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }

        });
        builder.show();

    }
    }
