package com.example.royalriding;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class Searchplace extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {
    EditText e1;
    ListView l1;
    String[] place,value,place_id;
    String search,status;
    public static  String pid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchplace);
        e1=(EditText)findViewById(R.id.search);
        l1=(ListView) findViewById(R.id.list);
        l1.setOnItemClickListener(this);
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Searchplace.this;
        String q = "/viewplace";
        q = q.replace(" ", "%20");
        JR.execute(q);

        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                search=e1.getText().toString();

                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) Searchplace.this;
                String q = "/searchplace?&search=" + search ;
                q = q.replace(" ", "%20");
                JR.execute(q);

            }
        });

    }


    @Override
    public void response(JSONObject jo) {
        try {

            String method = jo.getString("method");
            Log.d("pearl", method);

            if (method.equalsIgnoreCase("viewplace")) {
                status = jo.getString("status");
                Log.d("pearlssssss", status);


                if (status.equalsIgnoreCase("success")) {
                    l1.setVisibility(View.VISIBLE);
                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                    place = new String[ja1.length()];
                    place_id=new String[ja1.length()];




                    value = new String[ja1.length()];

                    for (int i = 0; i < ja1.length(); i++) {
                        place[i] = ja1.getJSONObject(i).getString("place");
                        place_id[i] = ja1.getJSONObject(i).getString("place_id");

                        value[i] = "place:" + place[i] ;

                    }
                    ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), R.layout.custtext, value);

                    l1.setAdapter(ar);



                }
                else{
                    Toast.makeText(getApplicationContext(),"No Data",Toast.LENGTH_LONG).show();
                    l1.setVisibility(View.GONE);
                }
            }
            if (method.equalsIgnoreCase("search")) {
                status = jo.getString("status");
                Log.d("pearlsssss", status);


                if (status.equalsIgnoreCase("success")) {
                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                    place = new String[ja1.length()];
                    place_id=new String[ja1.length()];




                    String[] value = new String[ja1.length()];

                    for (int i = 0; i < ja1.length(); i++) {
                        place[i] = ja1.getJSONObject(i).getString("place");
                        place_id[i] = ja1.getJSONObject(i).getString("place_id");

                        Toast.makeText(getApplicationContext(), place[i], Toast.LENGTH_LONG).show();





                        value[i] = "place:" + place[i] ;

                    }
                    ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(),  R.layout.custtext, value);

                    l1.setAdapter(ar);


                }
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
        pid=place_id[i];
        final CharSequence[] items = {"View Packages","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Searchplace.this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("View Packages")) {

                    startActivity(new Intent(getApplicationContext(),ViewPackages.class));


                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }

        });
        builder.show();
    }
}