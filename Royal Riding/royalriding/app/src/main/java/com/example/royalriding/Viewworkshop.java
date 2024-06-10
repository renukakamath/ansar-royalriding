package com.example.royalriding;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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

public class Viewworkshop extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {
    ListView l1;
    String [] workshop,details,images,value,phone,longitude,latitude;
    public static String cid,phn,tlati,tlongi;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewworkshop);
       sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1=(ListView)findViewById(R.id.list);
        l1.setOnItemClickListener(this);
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Viewworkshop.this;
        String q = "/viewworkshop?login_id="+sh.getString("log_id","" );
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
                workshop = new String[ja1.length()];
                details = new String[ja1.length()];
                phone=new String[ja1.length()];
                images=new String[ja1.length()];
                longitude = new String[ja1.length()];
                latitude = new String[ja1.length()];



                value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {
                    workshop[i] = ja1.getJSONObject(i).getString("workshop");
                    details[i] = ja1.getJSONObject(i).getString("details");
                    phone[i] = ja1.getJSONObject(i).getString("phone");

                    images[i] = ja1.getJSONObject(i).getString("image");
                    longitude[i] = ja1.getJSONObject(i).getString("longitude");
                    latitude[i] = ja1.getJSONObject(i).getString("latitude");




                    value[i] ="workshop:" + workshop[i]+ "\ndetails: " + details[i]  +"\nphone:" +phone[i] ;

                }
                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(),  R.layout.custtext, value);

                l1.setAdapter(ar);

                Custimage a = new Custimage(this, workshop, phone, images, details);
                l1.setAdapter(a);

            }
        }

        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        phn=phone[i];
        tlati=latitude[i];
        tlongi=longitude[i];
        final CharSequence[] items = {"Make Call","View Location","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Viewworkshop.this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Make Call")) {
                    // getting phone number from edit text and changing it to String
                    String phone_number = phn;

                    // Getting instance of Intent with action as ACTION_CALL
                    Intent phone_intent = new Intent(Intent.ACTION_CALL);

                    // Set data of Intent through Uri by parsing phone number
                    phone_intent.setData(Uri.parse("tel:" + phone_number));

                    // start Intent
                    startActivity(phone_intent);

                }
                else if (items[item].equals("View Location")) {

                    //                    startActivity(new Intent(getApplicationContext(),UserHotelRoomBooking.class));
                    String url = "http://www.google.com/maps?saddr=" + LocationService.lati + "" + "," + LocationService.logi + "" + "&&daddr=" + tlati + "," + tlongi;

                    Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(in);

                }
               else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }

        });
        builder.show();

    }
    }
