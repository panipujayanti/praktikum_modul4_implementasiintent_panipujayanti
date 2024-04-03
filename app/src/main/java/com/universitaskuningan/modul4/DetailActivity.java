package com.universitaskuningan.modul4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        // Mendapatkan Intent yang memulai aktivitas ini
        Intent intent = getIntent();

        // Mendapatkan data tambahan dari Intent
        String nama = intent.getStringExtra("key_nama");
        int umur = intent.getIntExtra("key_umur", 0); // 0 adalah nilai default jika data tidak ditemukan

        // Menampilkan data tambahan di TextView
        TextView textView = findViewById(R.id.text_view);
        textView.setText("Nama: " + nama + ", Umur: " + umur);
    }
}