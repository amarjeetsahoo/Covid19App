package com.amarjeetsahoo.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GlobalStatistics extends AppCompatActivity {
    ImageView back,gnotify;
    TextView active,confirmed,deaths,recovered,newconfirmed;
    Button india;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_statistics);

        back =(ImageView)findViewById(R.id.gimageView5);
        gnotify=(ImageView)findViewById(R.id.gnotify);
        active=(TextView)findViewById(R.id.gactive);
        confirmed=(TextView)findViewById(R.id.gconfirmed);
        deaths=(TextView)findViewById(R.id.gdeaths);
        recovered=(TextView)findViewById(R.id.grecovered);
        newconfirmed=(TextView)findViewById(R.id.gnewconfirmed);
        india=(Button)findViewById(R.id.gbutton3);

        india.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(GlobalStatistics.this,Statistics.class);
                startActivity(in);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(GlobalStatistics.this,MainActivity.class);
                startActivity(i);
                finish();

            }
        });

        gnotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(GlobalStatistics.this,gnotify);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.menu, popup.getMenu());
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getTitle().toString())
                        {
                            case "About Developer":
                                Toast.makeText(GlobalStatistics.this, "Amarjeet Sahoo\n18ECE045\nGIET University",
                                        Toast.LENGTH_LONG).show();
                                break;
                        }
                        return true;
                    }
                }); popup.show();
            }
        });


        OkHttpClient client =new OkHttpClient();

        final Request request = new Request.Builder()
                .url("https://coronavirus-monitor.p.rapidapi.com/coronavirus/worldstat.php")
                .get()
                .addHeader("x-rapidapi-host", "coronavirus-monitor.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "PASTE YOUR KEY HERE FROM RAPID_API") //HAVE TO REMOVE FOR PRIVACY CONCERN
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful())
                {
                    final String myResponse =response.body().string();
                    GlobalStatistics.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String activ,confirm,newconfirm,death,recover;


                            try {
                                JSONObject obj = new JSONObject(myResponse);
                                activ=obj.getString("active_cases");
                                confirm=obj.getString("total_cases");
                                newconfirm=obj.getString("new_cases");
                                death=obj.getString("total_deaths");
                                recover=obj.getString("total_recovered");
                                recovered.setText(recover);
                                deaths.setText(death);
                                newconfirmed.setText(newconfirm);
                                confirmed.setText(confirm);
                                active.setText(activ);
                            }
                            catch (JSONException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    });
                }

            }
        });
    }
}
