package br.com.narcisocsales.trabalhoopcional2.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.narcisocsales.trabalhoopcional2.R;
import br.com.narcisocsales.trabalhoopcional2.dao.CarrosDao;
import br.com.narcisocsales.trabalhoopcional2.model.Carros;

public class Cadastro extends AppCompatActivity {

    private Button btSalvar;
    private Button btExcluir;
    private CarrosDao carrosDao;
    private EditText editTextNome;
    private EditText editTextMarca;
    private EditText editTextAno;
    private TextView textViewId;
    private TextView textViewNome;
    private TextView textViewMarca;
    private TextView textViewAno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telaformulario);

        textViewId = (TextView) findViewById(R.id.textViewId);
        textViewNome = (TextView) findViewById(R.id.textViewNome);
        textViewMarca = (TextView) findViewById(R.id.textViewMarca);
        textViewAno = (TextView) findViewById(R.id.textViewAno);

        editTextNome = (EditText) findViewById(R.id.editTextNome);
        editTextMarca = (EditText) findViewById(R.id.editTextMarca);
        editTextAno = (EditText) findViewById(R.id.editTextAno);

        if(!getIntent().hasExtra("id")) {
            textViewId.setText("");
            textViewNome.setText("");
            textViewMarca.setText("");
            textViewAno.setText("");
        }else{
            Bundle b = getIntent().getExtras();

            textViewId.setText(String.valueOf(b.getLong("id")));
            textViewNome.setText(b.getString("nome"));
            textViewMarca.setText(b.getString("marca"));
            textViewAno.setText(b.getString("ano"));

            editTextNome.setText(b.getString("nome"));
            editTextMarca.setText(b.getString("marca"));
            editTextAno.setText(b.getString("ano"));
        }

        carrosDao = new CarrosDao(this);

        btSalvar = (Button) findViewById(R.id.btSalvar);
        btExcluir = (Button) findViewById(R.id.btExcluir);

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!getIntent().hasExtra("id")) {
                    Carros c = new Carros(null, editTextNome.getText().toString(),editTextMarca.getText().toString(),editTextAno.getText().toString());
                    carrosDao.insert(c);
                }else{
                    Bundle b = getIntent().getExtras();
                    Carros c = new Carros(b.getLong("id"), editTextNome.getText().toString(),editTextMarca.getText().toString(),editTextAno.getText().toString());
                    carrosDao.update(c);
                }

                finish();
            }
        });

        btExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!getIntent().hasExtra("id")) {
                    Toast.makeText(getApplicationContext(), "Salve ou selecione um item antes", Toast.LENGTH_SHORT).show();
                }else{
                    Bundle b = getIntent().getExtras();
                    Carros c = new Carros(b.getLong("id"), b.getString("nome"),b.getString("marca"),b.getString("ano"));
                    carrosDao.delete(c);
                }

                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastro, menu);
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
