package br.com.narcisocsales.trabalho2fa7;

import android.content.ContentResolver;
import android.media.MediaPlayer;

import java.util.List;

/**
 * Created by bruno on 10/06/15.
 */
public interface IPlayerService {

    enum PlayerState {
        PREPARED,
        STARTED,
        PAUSED,
        STOPPED,
    }

    List<Musicas> retrieveSongList(ContentResolver resolver);

    void setOnCompletionListener(MediaPlayer.OnCompletionListener listener);

    void prepare(Musicas song);

    void play();

    void pause();

    void stop();

    void seekTo(int msec);

    void release();

    boolean isPlaying();

    boolean isPaused();

    boolean isStoped();

    int getDuration();

    int getCurrentPosition();

    PlayerState getState();

    void setVolume(float vol);

}
