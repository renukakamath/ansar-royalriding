package com.example.royalriding;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class Updatelicencedetails extends AppCompatActivity implements  JsonResponse{
    EditText e1,e2,e3,e4,e5;
    Button b1;
    String name,place,phone,email,licence;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatelicencedetails);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1=(EditText) findViewById(R.id.fname);
        e2=(EditText) findViewById(R.id.place);
        e3=(EditText) findViewById(R.id.phone);
        e4=(EditText) findViewById(R.id.email);
        e5=(EditText)findViewById(R.id.licencedetails) ;
        b1=(Button) findViewById(R.id.update);
        JsonReq JR = new JsonReq();

        JR.json_response = (JsonResponse) Updatelicencedetails.this;
        String q = "/viewusers?lid="+sh.getString("log_id", "");
        q = q.replace(" ", "%20");
        JR.execute(q);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=e1.getText().toString();
                place=e2.getText().toString();
                phone=e3.getText().toString();
                email=e4.getText().toString();
                licence=e5.getText().toString();


                JsonReq JR=new JsonReq();
                JR.json_response=(JsonResponse) Updatelicencedetails.this;
                String q = "/updateuser?login_id="+sh.getString("log_id", "")+"&name="+name+"&place="+place+"&Phone="+phone+ "&email="+email +"&licence="+licence;
                q=q.replace(" ","%20");
                JR.execute(q);
            }
        });


    }

    @Override
    public void response(JSONObject jo) {
        try {
            String method=jo.getString("method");

            if(method.equalsIgnoreCase("viewusers")) {
                String status = jo.getString("status");
                Log.d("pearl", status);

                if (status.equalsIgnoreCase("success")) {
                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                    e1.setText(ja1.getJSONObject(0).getString("fname"));
                    e2.setText(ja1.getJSONObject(0).getString("place"));
                    e3.setText(ja1.getJSONObject(0).getString("phone"));
                    e4.setText(ja1.getJSONObject(0).getString("email"));
                    e5.setText(ja1.getJSONObject(0).getString("license"));





                    SharedPreferences.Editor e = sh.edit();
                    //e.putString("log_id", logid);
                    e.commit();
                }
            }
            else if(method.equalsIgnoreCase("updateuser"))
            {
                try {
                    String status=jo.getString("status");
                    Log.d("pearl",status);


                    if(status.equalsIgnoreCase("success")){

                        Toast.makeText(getApplicationContext(), "UPDATED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),Updatelicencedetails.class));

                    }
                    else
                    {
                        startActivity(new Intent(getApplicationContext(),Updatelicencedetails.class));
                        Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }

            }
//            else {
//                Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
//                startActivity(new Intent(getApplicationContext(), Login.class));
//            }
        } catch (Exception e) {
            // TODO: handle exception

            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }


}