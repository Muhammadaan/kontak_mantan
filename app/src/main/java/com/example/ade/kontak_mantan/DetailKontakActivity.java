package com.example.ade.kontak_mantan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ade.kontak_mantan.entities.Kontak;
import com.example.ade.kontak_mantan.models.KontakModel;

public class DetailKontakActivity extends AppCompatActivity {
    //Data
    private KontakModel mKontak;
    private Kontak selectedKontak;

    //Komponen
    private EditText txtNama;
    private EditText txtNomor;
    private Button btnHapus;
    private Button btnKembali;
    private Button btnUbah;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kontak);

        this.initData();
        this.initComponents();
    }

    private void initData() {
        this.mKontak = new KontakModel(this);

        int selectedContactId=this.getIntent().getIntExtra("selectedContactId",-1);

        this.selectedKontak = this.mKontak.selectOne(selectedContactId);
    }

    private void initComponents() {
        this.txtNama = (EditText) this.findViewById(R.id.txtNama);
        this.txtNomor = (EditText) this.findViewById(R.id.txtNomor);
        this.btnUbah = (Button) this.findViewById(R.id.btnUbah);
        this.btnHapus = (Button) this.findViewById(R.id.btnHapus);
        this.btnKembali = (Button) this.findViewById(R.id.btnKembali);

        //isi teks pada komponen saat Activity baru dimunculkan
        this.txtNama.setText(this.selectedKontak.getNama());
        this.txtNomor.setText(this.selectedKontak.getNomor());
    }

    public void button_onClick(View view)
    {
        Button b = (Button) view;

        if(b.getId() == R.id.btnUbah)
        {
            this.ubahKontak();
        }
        else if (b.getId() == R.id.btnHapus)
        {
           this.hapusKontak();
        }
        else if (b.getId() == R.id.btnKembali)
        {
         this.finish();
        }
    }

    private void hapusKontak() {
        this.mKontak.delete(this.selectedKontak);

        this.resetFields("Kontak Mantan dihapus...",true);

        this.btnHapus.setEnabled(false);
    }

    private void ubahKontak() {
        String nama = this.txtNama.getText().toString();
        String nomor = this.txtNomor.getText().toString();

        this.selectedKontak.setNama(nama);
        this.selectedKontak.setNomor(nomor);

        this.mKontak.update(this.selectedKontak);

        this.resetFields("Data Mantan Berhasil Diganti :) ",false);
    }

    private void resetFields(String pesan, boolean clear) {

        Toast.makeText(this,pesan,Toast.LENGTH_SHORT).show();

        if(clear)
        {
            this.txtNama.setText(" ");
            this.txtNomor.setText(" ");
        }
    }
}
