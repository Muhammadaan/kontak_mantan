package com.example.ade.kontak_mantan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.ade.kontak_mantan.entities.Kontak;
import com.example.ade.kontak_mantan.liibraries.DbHelper;
import com.example.ade.kontak_mantan.models.KontakModel;

import java.util.ArrayList;

public class LihatKontakActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    //DATA
    private KontakModel mKontak;
    private ArrayList<Kontak> allKontak;
    private ArrayList<String> daftarNama;

    //Komponen
    private ListView lstDaftarKontak;
    private Button btnOk;
    private DbHelper db;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_kontak);


        this.initData();
        this.initComponents();
    }
    private void initData() {
        this.daftarNama = new ArrayList<>();

        this.mKontak = new KontakModel(this);

        this.allKontak = this.mKontak.selectAll();

        for (Kontak k : allKontak)
        {
            this.daftarNama.add(k.getNama());
        }
    }

    private void initComponents() {
        this.lstDaftarKontak = (ListView) this.findViewById(R.id.lstDaftarKontak);

        this.btnOk = (Button) this.findViewById(R.id.btnOk);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,this.daftarNama);

        this.lstDaftarKontak.setAdapter(adapter);

        this.lstDaftarKontak.setOnItemClickListener(this);


    }



    public void button_onClick(View view)
    {
        Button b = (Button) view;

        if(b == this.btnOk)
        {
            Intent mIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mIntent);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        int id = this.allKontak.get(i).getId();

        Intent intent = new Intent(this,DetailKontakActivity.class);

        intent.putExtra("selectedContactId",id);

        this.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
//        adapter.notifyDataSetChanged();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,this.daftarNama);

        this.lstDaftarKontak.setAdapter(adapter);

        this.lstDaftarKontak.setOnItemClickListener(this);

    }
}
