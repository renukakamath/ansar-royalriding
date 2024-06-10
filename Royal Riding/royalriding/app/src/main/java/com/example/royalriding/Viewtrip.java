package com.example.royalriding;

import android.content.DialogInterface;
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

public class Viewtrip extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {
    ListView l1;
    String [] trip,place,details,club,from_date,to_date,value,trip_id,amount;
   SharedPreferences sh;
   public static String tid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewtrip);
        l1=(ListView) findViewById(R.id.list);
        l1.setOnItemClickListener(this);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Viewtrip.this;
        String q = "/Viewtrip?login_id="+sh.getString("log_id","" );
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
                trip = new String[ja1.length()];
                place = new String[ja1.length()];
                details = new String[ja1.length()];
                club = new String[ja1.length()];
                from_date = new String[ja1.length()];
                to_date = new String[ja1.length()];
                trip_id =new String[ja1.length()];
                value = new String[ja1.length()];
                amount = new String[ja1.length()];



                String[] value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {
                    trip[i] = ja1.getJSONObject(i).getString("trip");
                    place[i] = ja1.getJSONObject(i).getString("place");
                    details[i] = ja1.getJSONObject(i).getString("details");
                    club[i] = ja1.getJSONObject(i).getString("club");
                    from_date[i] = ja1.getJSONObject(i).getString("from_date");
                    to_date[i] = ja1.getJSONObject(i).getString("to_date");
                    trip_id[i]=ja1.getJSONObject(i).getString("trip_id");
                    amount[i]=ja1.getJSONObject(i).getString("amount");






                    value[i] = "trip:" + trip[i] + "\n place: " + place[i] + "\n details: " + details[i] + "\n club: " + club[i] +"\n from_date:" +from_date[i] +"\n to_date:" + to_date[i]+"\n amount:" + amount[i];

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

        final CharSequence[] items = {"Send Request","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Viewtrip.this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Send Request")) {
                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Viewtrip.this;
                    String q = "/sendrequest?tid=" + tid + "&login_id="+sh.getString("log_id","" ) ;
                    q = q.replace(" ", "%20");
                    JR.execute(q);
                    Toast.makeText(getApplicationContext(),"Successfully Send Request",Toast.LENGTH_LONG).show();




                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }

        });
        builder.show();

    }
    }
