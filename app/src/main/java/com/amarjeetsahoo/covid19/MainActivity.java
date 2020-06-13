package com.amarjeetsahoo.covid19;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView flags,notify,dash;
    Spinner mSpinner;
    Button stat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpinner=(Spinner)findViewById(R.id.spinner);
        flags =(ImageView)findViewById(R.id.flag);
        stat=(Button)findViewById(R.id.stat);
        notify=(ImageView)findViewById(R.id.notify);

        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(MainActivity.this, notify);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.menu, popup.getMenu());
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getTitle().toString())
                        {
                            case "About Developer":
                                Toast.makeText(MainActivity.this, "Amarjeet Sahoo\n18ECE045\nGIET University",
                                        Toast.LENGTH_LONG).show();
                                break;
                        }
                        return true;
                    }
                }); popup.show();
            }
        });

        mSpinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,
                CounrtyData.countryNames));

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                flags.setImageResource(CounrtyData.countryFlag[mSpinner.getSelectedItemPosition()]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this,Statistics.class);
                startActivity(i);
            }
        });
    }

    public void CallNow(View view) {
        Intent i1 =new Intent(Intent.ACTION_VIEW, Uri.parse("tel:+911123978046"));
        startActivity(i1);
    }

    public void WhatsappReq(View view) {
        Intent i2 =new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://api.whatsapp.com/send?phone=41798931892&text=hi&source=&data=&app_absent="));
        startActivity(i2);
    }
}
