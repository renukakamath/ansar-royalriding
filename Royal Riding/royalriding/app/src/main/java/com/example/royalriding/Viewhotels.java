package com.example.royalriding;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

import org.json.JSONArray;
import org.json.JSONObject;

public class Viewhotels extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {
    ListView l1;
    SharedPreferences sh;
    String [] hotalname,phone,email,photo,hotelrent,longitude,latitude,value;
    public static String tid,tlati,tlongi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewhotels);
        l1=(ListView) findViewById(R.id.list);

        l1.setOnItemClickListener(this);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Viewhotels.this;
        String q = "/Viewhotels?login_id="+sh.getString("log_id","" );
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
                hotalname = new String[ja1.length()];
                phone = new String[ja1.length()];
                email = new String[ja1.length()];
                photo = new String[ja1.length()];
                hotelrent = new String[ja1.length()];
                longitude = new String[ja1.length()];
                latitude = new String[ja1.length()];



                value = new String[ja1.length()];


                String[] value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {
                    hotalname[i] = ja1.getJSONObject(i).getString("hotalname");
                    phone[i] = ja1.getJSONObject(i).getString("phone");
                    email[i] = ja1.getJSONObject(i).getString("email");
                    photo[i] = ja1.getJSONObject(i).getString("photo");

                    hotelrent[i] = ja1.getJSONObject(i).getString("hotelrent");
                    longitude[i] = ja1.getJSONObject(i).getString("longitude");
                    latitude[i] = ja1.getJSONObject(i).getString("latitude");


                    value[i] = "hotalname:" + hotalname[i] + "\nphone: " + phone[i] + "\n email: " + email[i] + "\nhotelrent: " + hotelrent[i];

                }
                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), R.layout.custtext, value);

                l1.setAdapter(ar);
                Custimage2 a = new Custimage2(this, hotalname, phone, email,photo,hotelrent );
                l1.setAdapter(a);

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        tlati=latitude[i];
        tlongi=longitude[i];

        final CharSequence[] items = {"View Location","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Viewhotels.this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("View Location")) {

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