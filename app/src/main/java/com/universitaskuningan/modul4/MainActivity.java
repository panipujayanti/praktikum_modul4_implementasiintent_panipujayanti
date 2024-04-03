package com.universitaskuningan.modul4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText mWebsiteEditText;
    private EditText mLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnShare = findViewById(R.id.btnShare);
        Button btnWeb = findViewById(R.id.open_website_button);
        Button btnMap = findViewById(R.id.open_location_button);
        mWebsiteEditText = findViewById(R.id.website_edittext);
        mLocationEditText = findViewById(R.id.location_edittext);
        Button btnOpenActivity = findViewById(R.id.btnMove);

        // Menambahkan listener klik pada tombol
        btnOpenActivity.setOnClickListener(v ->{

            // Membuat Intent untuk membuka TujuanActivity
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);

            // Menambahkan data tambahan ke Intent
            intent.putExtra("key_nama", "Abdul Majid");
            intent.putExtra("key_umur", 25);

            // Memulai aktivitas tujuan dengan Intent yang telah disiapkan
            startActivity(intent);

        });

        btnShare.setOnClickListener(v -> {

            // Perintah Intent Implicit untuk share ke sosmed
            Intent intent = new Intent(Intent.ACTION_SEND);
            // Membawa data / pesan yang ingin dishare
            intent.putExtra(intent.EXTRA_TEXT,"Hallo ini adalah data dari aplikasi ini ya yang akan dikirim dengan  saya share ke sosial media ");
            intent.setType("text/plain");
            // Menjalankan perintah Intent Implicit
            startActivity(Intent.createChooser(intent,"Share to :"));
        });


        btnMap.setOnClickListener(view -> {
            // Get the string indicating a location. Input is not validated; it is
            // passed to the location handler intact.
            String loc = mLocationEditText.getText().toString();

            // Parse the location and create the intent.
            Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
            Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

            try{
                startActivity(intent);
            }
            catch (Exception e){
                Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
            }

        });

        btnWeb.setOnClickListener(v -> {
            // Get the URL text.
            String url = mWebsiteEditText.getText().toString();

            // Pastikan EditText tidak kosong.
            if (!url.isEmpty()) {
                // Tambahkan skema http:// atau https:// jika tidak disertakan.
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "http://" + url;
                }

                // Parse the URI and create the intent.
                Uri webpage = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

                // Tentukan komponen untuk browser Chrome
                intent.setPackage("com.android.chrome");

                // Temukan activity untuk menangani intent dan mulai activity tersebut.
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    // Jika Chrome tidak terinstal, buka browser default
                    intent.setPackage(null);
                    startActivity(intent);
                }
            } else {
                // Tampilkan pesan jika URL kosong.
                Toast.makeText(this, "URL tidak boleh kosong!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}