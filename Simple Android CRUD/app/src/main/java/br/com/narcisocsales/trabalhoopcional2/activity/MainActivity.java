package br.com.narcisocsales.trabalhoopcional2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.narcisocsales.trabalhoopcional2.R;
import br.com.narcisocsales.trabalhoopcional2.adapter.ListAdapter;
import br.com.narcisocsales.trabalhoopcional2.dao.CarrosDao;
import br.com.narcisocsales.trabalhoopcional2.model.Carros;

public class MainActivity extends AppCompatActivity {

    private Button btNovo;
    private ListView listView;
    private List<Carros> carros;
    private CarrosDao carrosDao;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carrosDao = new CarrosDao(this);
        carros = carrosDao.findAll();

        adapter = new ListAdapter(this, carros);

        btNovo = (Button) findViewById(R.id.btNovo);
        btNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, Cadastro.class);
                startActivity(it);
            }
        });

        listView = (ListView) findViewById(R.id.listaCarros);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(MainActivity.this, Cadastro.class);
                Bundle b = new Bundle();
                b.putLong("id", carros.get(position).getId());
                b.putString("nome", carros.get(position).getNome());
                b.putString("marca", carros.get(position).getMarca());
                b.putString("ano", carros.get(position).getAno());
                it.putExtras(b);
                startActivity(it);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                carros.clear();
                carros.addAll(carrosDao.findAll());
                adapter.notifyDataSetChanged();
                listView.invalidateViews();
                listView.refreshDrawableState();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
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
}
