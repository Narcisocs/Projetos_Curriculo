package br.com.narcisocsales.trabalho2fa7;

import android.media.MediaPlayer;

import java.util.concurrent.ExecutionException;

/**
 * Created by Narciso on 23/08/2015.
 */
public class PlayerMp3 implements MediaPlayer.OnCompletionListener {

    private static final int NOVO = 0;
    private static final int INICIADO = 1;
    private static final int PAUSADO = 2;
    private static final int PARADO = 3;

    private int status = NOVO;
    private MediaPlayer player;
    private String mp3;

    public PlayerMp3(){
        player = new MediaPlayer();
        player.setOnCompletionListener(this);
    }

    public void start(String mp3){
        this.mp3 = mp3;
        try{
            switch (status){
                case INICIADO:
                    player.stop();
                case PARADO:
                    player.reset();
                case NOVO:
                    player.setDataSource(mp3);
                    player.prepare();
                case PAUSADO:
                    player.start();
                    break;
            }
            status = INICIADO;
        }catch (Exception e){

        }
    }

    public void pause(){
        player.pause();
        status = PAUSADO;
    }

    public void stop(){
        player.stop();
        status = PARADO;
    }

    public void musicaAnteriorProxima(String mp3){
        player.stop();
        try {
            player.setDataSource(mp3);
            player.prepare();
            player.start();
        }catch (Exception e){

        }
    }

    public void fechar(){
        stop();
        player.release();
        player = null;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }
}
