package br.com.narcisocsales.trabalho2fa7;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Narciso on 23/08/2015.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private List<Musicas> musicas;
    private LayoutInflater nLayoutInflater;
    private PlayerService player;
    private int posicaoMusica;
    private ImageButton imageButton;
    private MainActivity mainActivity;

    public MyAdapter(Context context, List<Musicas> musicas, PlayerService player){
        this.musicas = musicas;
        this.player = player;
        this.nLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getPosicaoMusica() {
        return posicaoMusica;
    }

    public void setPosicaoMusica(int posicaoMusica){
        this.posicaoMusica = posicaoMusica;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = nLayoutInflater.inflate(R.layout.cardview_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Musicas m = musicas.get(position);
        holder.imageView.setImageResource(m.getImage());
        holder.nomeMusica.setText(m.getNome());
        holder.nomeBanda.setText(m.getNomeBanda());
        holder.nomeAlbum.setText(m.getNomeAlbum());
    }

    @Override
    public int getItemCount() {
        return musicas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView imageView;
        public TextView nomeMusica;
        public TextView nomeBanda;
        public TextView nomeAlbum;

        public MyViewHolder(View itemview){
            super(itemview);
            imageView = (ImageView) itemview.findViewById(R.id.imageView);
            nomeMusica = (TextView) itemview.findViewById(R.id.nomeMusica);
            nomeBanda = (TextView) itemview.findViewById(R.id.nomeBanda);
            nomeAlbum = (TextView) itemview.findViewById(R.id.nomeAlbum);
            itemview.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            posicaoMusica = getAdapterPosition();
            //v.setSelected(true);

            mainActivity = (MainActivity) v.getContext();
            imageButton = (ImageButton) mainActivity.findViewById(R.id.btTocar);

            if(player.isPlaying()) {
                //imageButton.setBackgroundResource(R.mipmap.play);
                player.stop();
            }else {
                imageButton.setBackgroundResource(R.mipmap.stop);
            }

            player.prepare(musicas.get(getAdapterPosition()));
            mainActivity.updateProgressBar();
            player.play();
            Toast.makeText(v.getContext(), "teste" + getAdapterPosition(), Toast.LENGTH_SHORT).show();
        }
    }
}
