package radaelli.chagas.adami.harian.galeria2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;


import java.io.File;

import radaelli.chagas.adami.harian.galeria2.util.Util;

public class PhotoActivity extends AppCompatActivity {

    String photoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        //setando a minha toolbar como a toolbar padrao da activity
        Toolbar toolbar = findViewById(R.id.tbPhoto);
        setSupportActionBar(toolbar);

        //obtendo a action bar padrao e habilitando o botao de voltar na actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        photoPath = i.getStringExtra("photo_path");
        Bitmap bitmap = Util.getBitmap(photoPath);
        ImageView imPhoto = findViewById(R.id.imPhoto);
        imPhoto.setImageBitmap(bitmap);
    }



    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.opShare: sharePhoto();
                return true;
            default: return super.onOptionsItemSelected(item);

        }
    }

    void sharePhoto(){
        //codigo para compartilhar a foto com outras apps
        Uri photoUri = FileProvider.getUriForFile(PhotoActivity.this,"radaelli.chagas.adami.harian.galeria2.fileprovider", new File(photoPath));
        Intent i = new Intent(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_STREAM, photoUri);
        i.setType("imagem/jpeg");
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_tb,menu);
        return true;
    }
}