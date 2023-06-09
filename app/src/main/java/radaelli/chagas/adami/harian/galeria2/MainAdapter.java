package radaelli.chagas.adami.harian.galeria2;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import radaelli.chagas.adami.harian.galeria2.util.Util;

public class MainAdapter extends RecyclerView.Adapter{
    MainActivity mainActivity;
    List<String> photos;


    public MainAdapter(MainActivity mainActivity, List<String> photos){
        this.mainActivity = mainActivity;
        this.photos = photos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //obtendo um inflador de layout
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        //usando o inflador para criar os elementos e guardando eles dentro de uma view
        View v = inflater.inflate(R.layout.list_item,parent,false);
        //guardando a view dentro do MyViewHolder
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position){
        ImageView imPhoto = holder.itemView.findViewById(R.id.imItem);
        //obtendo as dimensoes que a imagem vai ter na lista
        int w = (int) mainActivity.getResources().getDimension(R.dimen.itemWidth);
        int h = (int) mainActivity.getResources().getDimension(R.dimen.itemHeight);
        //carregando a imagem em um bitmap e escalando a foto para casar com os tamanhos definidos
        Bitmap bitmap = Util.getBitmap(photos.get(position), w, h);
        //setando o bitmap na imageView
        imPhoto.setImageBitmap(bitmap);
        //fazendo a app navegar para a PhotoActivity, caos o usuario clique em cima da imagem
        imPhoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mainActivity.startPhotoActivity(photos.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

}
