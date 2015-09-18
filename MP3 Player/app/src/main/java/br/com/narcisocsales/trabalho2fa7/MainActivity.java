package br.com.narcisocsales.trabalho2fa7;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, SensorEventListener {

    private Toolbar mMainToolbar;
    private RecyclerView mRecyclerView;
    //private PlayerMp3 player = new PlayerMp3();
    private PlayerService player;
    private SeekBar seekBar;
    private ImageButton btTocar;
    private ImageButton btAnterior;
    private ImageButton btProxima;
    private TextView duracaoAtual;
    private TextView duracaoTotal;
    private List<Musicas> musicas = null;
    private MyAdapter adapter;
    private Utilities utils;
    private Handler mHandler = new Handler();
    private static final int TIPO_SENSOR = Sensor.TYPE_ACCELEROMETER;
    private SensorManager sensorManager;
    private Sensor sensor;
    private long time;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;
    private boolean mBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        btTocar = (ImageButton) findViewById(R.id.btTocar);
        btTocar.setOnClickListener(this);
        btAnterior = (ImageButton) findViewById(R.id.btAnterior);
        btAnterior.setOnClickListener(this);
        btProxima = (ImageButton) findViewById(R.id.btProxima);
        btProxima.setOnClickListener(this);

        utils = new Utilities();

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);

        duracaoAtual = (TextView) findViewById(R.id.duracaoAtual);
        duracaoTotal = (TextView) findViewById(R.id.duracaoTotal);

        mMainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        mMainToolbar.setTitle("UI Example");
        mMainToolbar.setSubtitle("Main Activity");
        mMainToolbar.setLogo(R.mipmap.app_logo);
        setSupportActionBar(mMainToolbar);

        //musicas = new ArrayList<Musicas>();

        /*ContentResolver contentResolver = getContentResolver();
        Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        caminho = uri.getPath();
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        if (cursor == null) {
            // query failed, handle error.
        } else if (!cursor.moveToFirst()) {
            // no media on the device
        } else {
            int nome = cursor.getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE);
            int banda = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int album = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
            do {

            } while (cursor.moveToNext());
        }

        cursor.close();
        */

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(TIPO_SENSOR) != null)
            sensor = sensorManager.getDefaultSensor(TIPO_SENSOR);
        else
            Toast.makeText(this, "Sensor não disponível", Toast.LENGTH_SHORT).show();

        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensor != null)
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent it = new Intent(this, PlayerService.class);
        bindService(it, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound)
            unbindService(mConnection);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.stop();
        player.release();
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            PlayerService.PlayerBind binder = (PlayerService.PlayerBind) service;

            if (player == null) {
                player = binder.getService();
                musicas = player.retrieveSongList(getContentResolver());
            }

            LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
            adapter = new MyAdapter(MainActivity.this, musicas, player);
            mRecyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(llm);
            mRecyclerView.setAdapter(adapter);

            updateProgressBar();

            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        long now = System.currentTimeMillis();
        float[] values = SensorUtil.fixAcelerometro(this, event);

        if (now - time > 1000) {
            time = now;

            if (player != null) {
                if (values[2] < 0)
                    player.pause();
                else
                    player.play();
            }
        }

        mAccelLast = mAccelCurrent;
        mAccelCurrent = (float) Math.sqrt((double) (values[0] * values[0] + values[1] * values[1] + values[2] * values[2]));
        float delta = mAccelCurrent - mAccelLast;
        mAccel = mAccel * 0.9f + delta;

        if (mAccel > 12) {
            Toast.makeText(this, "Device has shaken", Toast.LENGTH_LONG).show();
            //player.stop();
            if (adapter.getPosicaoMusica() + 1 <= (musicas.size() - 1)) {
                player.prepare(musicas.get(adapter.getPosicaoMusica() + 1));
                adapter.setPosicaoMusica(adapter.getPosicaoMusica() + 1);
            } else {
                // play first song
                player.prepare(musicas.get(0));
                adapter.setPosicaoMusica(0);
            }

            player.play();
        }
    }

    @Override
    public void onClick(View v) {
        try {
            if (v == btTocar) {
                //Toast.makeText(this, caminho + "/" + musicas.get(adapter.getPosicaoMusica()).getNomeBanda() + " - " + musicas.get(adapter.getPosicaoMusica()).getNome() + ".mp3", Toast.LENGTH_SHORT).show();
                if (player.isPlaying()) {
                    if (player != null) {
                        player.stop();
                        btTocar.setBackgroundResource(R.mipmap.play);
                    }
                } else {
                    seekBar.setProgress(0);
                    seekBar.setMax(100);
                    player.prepare(musicas.get(adapter.getPosicaoMusica()));
                    player.play();
                    btTocar.setBackgroundResource(R.mipmap.stop);
                    updateProgressBar();
                }

                /*MediaPlayer innerPlayer = new MediaPlayer();
                //File file = new File(caminho, musicas.get(adapter.posicaoMusica).getNomeBanda() + " - " + musicas.get(adapter.posicaoMusica).getNome() + ".mp3");
                //file.setReadable(true);
                innerPlayer.setDataSource(caminho + "/" + musicas.get(adapter.getPosicaoMusica()).getNomeBanda() + " - " + musicas.get(adapter.getPosicaoMusica()).getNome() + ".mp3");//file.getAbsolutePath());
                innerPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                innerPlayer.prepare();
                innerPlayer.start();*/
            }
            if (v == btAnterior) {
                //Toast.makeText(this, musicas.get(adapter.getPosicaoMusica() - 1).getNome() + ".mp3", Toast.LENGTH_SHORT).show();
                if (adapter.getPosicaoMusica() - 1 >= 0) {
                    player.prepare(musicas.get(adapter.getPosicaoMusica() - 1));
                    adapter.setPosicaoMusica(adapter.getPosicaoMusica() - 1);
                } else {
                    // play last song
                    player.prepare(musicas.get(musicas.size() - 1));
                    adapter.setPosicaoMusica(musicas.size() - 1);
                }
                btTocar.setBackgroundResource(R.mipmap.stop);
                player.play();
            }
            if (v == btProxima) {
                //Toast.makeText(this, musicas.get(adapter.getPosicaoMusica() + 1).getNome() + ".mp3", Toast.LENGTH_SHORT).show();
                if (adapter.getPosicaoMusica() + 1 <= (musicas.size() - 1)) {
                    player.prepare(musicas.get(adapter.getPosicaoMusica() + 1));
                    adapter.setPosicaoMusica(adapter.getPosicaoMusica() + 1);
                } else {
                    player.prepare(musicas.get(0));
                    adapter.setPosicaoMusica(0);
                }
                btTocar.setBackgroundResource(R.mipmap.stop);
                player.play();
            }
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateProgressBar() {
        mHandler.postDelayed(mUpdateTimeTask, 100);
    }

    /**
     * Background Runnable thread
     */
    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            long totalDuration = player.getDuration();
            long currentDuration = player.getCurrentPosition();

            // Displaying Total Duration time
            duracaoTotal.setText("" + utils.milliSecondsToTimer(totalDuration));
            // Displaying time completed playing
            duracaoAtual.setText("" + utils.milliSecondsToTimer(currentDuration));

            // Updating progress bar
            int progress = (int) (utils.getProgressPercentage(currentDuration, totalDuration));
            seekBar.setProgress(progress);

            // Running this thread after 100 milliseconds
            mHandler.postDelayed(this, 100);
        }
    };

    /**
     *
     * */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {

    }

    /**
     * When user starts moving the progress handler
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // remove message Handler from updating progress bar
        mHandler.removeCallbacks(mUpdateTimeTask);
    }

    /**
     * When user stops moving the progress hanlder
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mHandler.removeCallbacks(mUpdateTimeTask);
        int totalDuration = player.getDuration();
        int currentPosition = utils.progressToTimer(seekBar.getProgress(), totalDuration);

        // forward or backward to certain seconds
        player.seekTo(currentPosition);

        // update timer progress again
        updateProgressBar();
    }


}
