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

public class Viewclub extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {
    ListView l1;
    String [] club,phone,email,image,latitude,longitude,club_id;
    public static String cid,phn,tlati,tlongi;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewclub);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1=(ListView) findViewById(R.id.list);
        l1.setOnItemClickListener(this);

        startService(new Intent(getApplicationContext(), LocationService.class));


        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Viewclub.this;
        String q = "/Viewclub?login_id="+sh.getString("log_id","" );
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
                club = new String[ja1.length()];
                phone = new String[ja1.length()];
                email = new String[ja1.length()];
                latitude = new String[ja1.length()];
                longitude = new String[ja1.length()];
                club_id=new String[ja1.length()];
                image=new String[ja1.length()];




                String[] value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {
                    club[i] = ja1.getJSONObject(i).getString("club");
                    phone[i] = ja1.getJSONObject(i).getString("phone");
                    email[i] = ja1.getJSONObject(i).getString("email");
                    latitude[i] = ja1.getJSONObject(i).getString("latitude");
                    longitude[i] = ja1.getJSONObject(i).getString("longitude");
                    club_id[i]=ja1.getJSONObject(i).getString("club_id");
                    image[i]=ja1.getJSONObject(i).getString("image");



                    value[i] ="club:" + club[i]+ "\nphone: " + phone[i] + "\nemail: " + email[i]  ;

                }
                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(),  R.layout.custtext, value);


                Custimage1 a = new Custimage1(this, club, phone, email,image, latitude,longitude);
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
        cid=club_id[i];
        phn=phone[i];

        tlati=latitude[i];
        tlongi=longitude[i];

        final CharSequence[] items = {"Join club","View Location","Make Call","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Viewclub.this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Join club")) {
                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Viewclub.this;
                    String q = "/Addclub?cid=" + cid + "&login_id="+sh.getString("log_id","" ) ;
                    q = q.replace(" ", "%20");
                    JR.execute(q);
                    Toast.makeText(getApplicationContext(),"Successfully join",Toast.LENGTH_LONG).show();




                }else if (items[item].equals("View Location")){

                    //                    startActivity(new Intent(getApplicationContext(),UserHotelRoomBooking.class));
                    String url = "http://www.google.com/maps?saddr=" + LocationService.lati + "" + "," + LocationService.logi + "" + "&&daddr=" + tlati + "," + tlongi;

                    Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(in);


                }
                else if (items[item].equals("Make Call")) {

                    // getting phone number from edit text and changing it to String
                    String phone_number = phn;

                    // Getting instance of Intent with action as ACTION_CALL
                    Intent phone_intent = new Intent(Intent.ACTION_CALL);

                    // Set data of Intent through Uri by parsing phone number
                    phone_intent.setData(Uri.parse("tel:" + phone_number));

                    // start Intent
                    startActivity(phone_intent);

                }else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }

        });
        builder.show();

    }
}

