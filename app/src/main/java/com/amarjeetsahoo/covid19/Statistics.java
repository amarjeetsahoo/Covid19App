package com.amarjeetsahoo.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Statistics extends AppCompatActivity {
    ImageView back,nofity;
    TextView active,confirmed,deaths,recovered,newconfirmed;
    Button global;
    SliderLayout sliderLayout;
    FloatingActionButton floatbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        back =(ImageView)findViewById(R.id.dash);
        nofity=(ImageView)findViewById(R.id.notify);
        active=(TextView)findViewById(R.id.active);
        confirmed=(TextView)findViewById(R.id.confirmed);
        deaths=(TextView)findViewById(R.id.deaths);
        recovered=(TextView)findViewById(R.id.recovered);
        newconfirmed=(TextView)findViewById(R.id.newconfirmed);
        global=(Button)findViewById(R.id.button4);

        floatbtn=(FloatingActionButton)findViewById(R.id.floatbtn);
        floatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Statistics.this,bot.class);
                startActivity(intent);
            }
        });

        sliderLayout=(SliderLayout) findViewById(R.id.imageslider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setScrollTimeInSec(1);
        setSliderViews();

        global.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent g=new Intent(Statistics.this,GlobalStatistics.class);
                startActivity(g);
                finish();
            }
        });

        nofity.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          PopupMenu popup = new PopupMenu(Statistics.this, nofity);
                                          //Inflating the Popup using xml file
                                          popup.getMenuInflater().inflate(R.menu.menu, popup.getMenu());
                                          //registering popup with OnMenuItemClickListener
                                          popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                              public boolean onMenuItemClick(MenuItem item) {
                                                  switch (item.getTitle().toString())
                                                  {
                                                      case "About Developer":
                                                          Toast.makeText(Statistics.this, "Amarjeet Sahoo\n18ECE045\nGIET University",
                                                                  Toast.LENGTH_LONG).show();
                                                          break;
                                                  }
                                                  //Toast.makeText(Statistics.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                                                  return true;
                                              }
                                          }); popup.show();
                                      }
                                  });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Statistics.this,MainActivity.class);
                startActivity(i);
                finish();

            }
        });
        OkHttpClient client =new OkHttpClient();
        final Request request = new Request.Builder()
                .url("https://covid-19-india-data-by-zt.p.rapidapi.com/GetIndiaTotalCounts")
                .get()
                .addHeader("x-rapidapi-host", "covid-19-india-data-by-zt.p.rapidapi.com")
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
                    Statistics.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String activ,confirm,newconfirm,death,recover;
                            System.out.println(myResponse);

                            try {

                                JSONObject obj = new JSONObject(myResponse);

                                JSONArray userArray = obj.getJSONArray("data");

                                for (int i = 0; i < userArray.length(); i++) {

                                    JSONObject userDetail = userArray.getJSONObject(i);

                                    activ=userDetail.getString("active");
                                    active.setText(activ);

                                    confirm=userDetail.getString("confirmed");
                                    confirmed.setText(confirm);

                                    newconfirm=userDetail.getString("newconfirmed");
                                    newconfirmed.setText(newconfirm);

                                    death=userDetail.getString("deaths");
                                    deaths.setText(death);

                                    recover=userDetail.getString("recovered");
                                    recovered.setText(recover);

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }

            }
        });
    }

    private void setSliderViews() {
        for(int i=0;i<6;i++)
        {
            DefaultSliderView sliderView =new DefaultSliderView(this);

            switch (i)
            {
                case 0:
                    sliderView.setImageDrawable(R.drawable.mygov_158434557852221771);
                    sliderView.setDescription("Share your Ideas & Suggestions to help fight Coronavirus");
                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.mygov_158530539251553221);
                    sliderView.setDescription("Join us in the fight against COVID-19");
                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.mygov_158618781251553221);
                    sliderView.setDescription("Myths mislead you, facts protect you. Stay Updated with facts related to COVID-19");
                    break;
                case 3:
                    sliderView.setImageDrawable(R.drawable.tn_0);
                    sliderView.setDescription("Aarogya Setu App : COVID-19 Tracker launched to alert you and keep you safe. Download now!");
                    break;
                case 4:
                    sliderView.setImageDrawable(R.drawable.tn1);
                    sliderView.setDescription("MyGov COVID-19 Shri Shakti Challenge");
                    break;
                case 5:
                    sliderView.setImageDrawable(R.drawable.tn_1);
                    sliderView.setDescription("Innovation Challenge for Development of Video Conferencing Solution");
                    break;

            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            final int finalI=i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Toast.makeText(Statistics.this,"Redirecting... ",Toast.LENGTH_SHORT).show();
                    switch (finalI) {
                        case 0:
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://www.mygov.in/group-issue/share-your-ideas-suggestions-help-fight-coronavirus/"));
                            startActivity(browserIntent);
                            break;
                        case 1:
                            Intent browserIntent1 = new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://www.mygov.in/covid-19/?target=webview&type=campaign&nid=0"));
                            startActivity(browserIntent1);
                            break;
                        case 2:
                            Intent browserIntent2 = new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://www.mygov.in/covid-19/?target=webview&type=campaign&nid=0#mythbuster"));
                            startActivity(browserIntent2);
                            break;
                        case 3:
                            Intent browserIntent3 = new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://play.google.com/store/apps/details?id=nic.goi.aarogyasetu&hl=en"));
                            startActivity(browserIntent3);
                            break;
                        case 4:
                            Intent browserIntent4 = new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://www.mygov.in/task/mygov-covid-19-shri-shakti-challenge/"));
                            startActivity(browserIntent4);
                            break;
                        case 5:
                            Intent browserIntent5 = new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://www.mygov.in/task/innovation-challenge-development-video-conferencing-solution/"));
                            startActivity(browserIntent5);
                            break;
                    }
                }
            });
            sliderLayout.addSliderView(sliderView);
        }
    }
}
