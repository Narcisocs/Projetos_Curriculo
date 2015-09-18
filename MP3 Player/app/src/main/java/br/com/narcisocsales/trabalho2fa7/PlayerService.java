package br.com.narcisocsales.trabalho2fa7;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruno on 10/06/15.
 */
public class PlayerService extends Service implements IPlayerService, MediaPlayer.OnCompletionListener {

    private static final String SERVICE_TAG = "PlayerService";
    private final IBinder mBinder;
    private PlayerState mState;
    private MediaPlayer mPlayer;
    private List<Musicas> mSongs;
    private Musicas actualSong;
    private MediaPlayer.OnCompletionListener listener;

    public PlayerService() {
        this.mBinder = new PlayerBind();
        this.mSongs = new ArrayList<>();
        this.mPlayer = new MediaPlayer();
        this.mState = PlayerState.STOPPED;
        this.actualSong = null;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public List<Musicas> retrieveSongList(ContentResolver r) {

        // Retrieve the songs of external storage
        ContentResolver resolver = r;
        Uri songsUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selections = MediaStore.Audio.Media.DATA + " like '%/Music/%'";
        Cursor songsCursor = resolver.query(songsUri, null, selections, null, null);

        int isMusicColumn = songsCursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC);
        int songDataColumn = songsCursor.getColumnIndex(MediaStore.Audio.Media.DATA);

        if (songsCursor.getCount() > 0) {

            songsCursor.moveToFirst();

            MediaMetadataRetriever mmr = new MediaMetadataRetriever();

            do {

                if (songsCursor.getInt(isMusicColumn) > 0) {

                    mmr.setDataSource(songsCursor.getString(songDataColumn));

                    Musicas song = new Musicas();
                    song.setImage(R.mipmap.song);
                    song.setNome(songsCursor.getString(songsCursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
                    song.setNomeBanda(songsCursor.getString(songsCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
                    song.setNomeAlbum(songsCursor.getString(songsCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)));
                    /*song.setNome(mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE));
                    song.setNomeBanda(mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST));
                    song.setNomeAlbum(mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM));*/
                    song.setPlaying(false);
                    song.setPath(songsCursor.getString(songDataColumn));

                    mSongs.add(song);
                }

            } while (songsCursor.moveToNext());

        }

        return mSongs;
    }

    @Override
    public void setOnCompletionListener(MediaPlayer.OnCompletionListener listener) {
        this.listener = listener;
    }

    @Override
    public void prepare(Musicas song) {

        // Release the actual song
        this.release();

        // Create a new media player with new song
        mPlayer = MediaPlayer.create(this, Uri.parse(song.getPath()));
        mPlayer.setOnCompletionListener(this);
        actualSong = song;
        mState = PlayerState.PREPARED;
        Log.i(SERVICE_TAG, "Song " + song.getNome() + " is prepared.");

    }

    @Override
    public void play() {

        // If the song is in prepared or paused state, start it!
        if (PlayerState.PREPARED.equals(mState) || PlayerState.PAUSED.equals(mState)) {
            mPlayer.start();
            mState = PlayerState.STARTED;
            Log.i(SERVICE_TAG, "Song was played.");
        }

    }

    @Override
    public void pause() {

        // If the song is in started state, pause it!
        if (PlayerState.STARTED.equals(mState)) {
            mPlayer.pause();
            mState = PlayerState.PAUSED;
            Log.i(SERVICE_TAG, "Song was paused.");
        }

    }

    @Override
    public void stop() {

        // If the song is in started state, pause it!
        if (PlayerState.STARTED.equals(mState) || PlayerState.PAUSED.equals(mState)) {
            mPlayer.pause();
            mState = PlayerState.STOPPED;
            Log.i(SERVICE_TAG, "Song was stopped.");
        }

    }

    @Override
    public void seekTo(int msec) {
        if (PlayerState.STARTED.equals(mState) || PlayerState.PAUSED.equals(mState)) {
            mPlayer.seekTo(msec);
            Log.i(SERVICE_TAG, "Song was seek to " + msec + " ms ahead.");
        }
    }

    @Override
    public void release() {
        if (mPlayer != null) {
            mPlayer.release();
            Log.i(SERVICE_TAG, "Song was released.");
        }
    }

    @Override
    public boolean isPlaying() {
        return mPlayer.isPlaying();
    }

    @Override
    public boolean isPaused() {
        return !mPlayer.isPlaying() && PlayerState.PAUSED.equals(mState);
    }

    @Override
    public boolean isStoped() {
        return !mPlayer.isPlaying() && PlayerState.STOPPED.equals(mState);
    }

    @Override
    public int getDuration() {

        int duration = 0;

        if (PlayerState.PREPARED.equals(mState) || PlayerState.STARTED.equals(mState)) {
            duration = mPlayer.getDuration();
        }

        return duration;
    }

    @Override
    public int getCurrentPosition() {

        int currentPosition = 0;

        if (PlayerState.PREPARED.equals(mState) || PlayerState.STARTED.equals(mState)) {
            currentPosition = mPlayer.getCurrentPosition();
        }

        return currentPosition;

    }

    @Override
    public PlayerState getState() {
        return mState;
    }

    @Override
    public void setVolume(float vol) {
        mPlayer.setVolume(vol, vol);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

        Log.i(SERVICE_TAG, "Song finished, go to next!");
        int position = mSongs.indexOf(actualSong) + 1;

        if (position < mSongs.size()) {

            try {

                this.prepare(mSongs.get(position));
                Thread.sleep(100);
                this.play();

                /*if (listener != null) {
                    listener.onCompletion(mSongs.get(position));
                }*/

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } else {
            this.stop();
            if (listener != null) {
                listener.onCompletion(null);
            }
        }
    }

    public class PlayerBind extends Binder {
        public PlayerService getService() {
            return PlayerService.this;
        }
    }

}