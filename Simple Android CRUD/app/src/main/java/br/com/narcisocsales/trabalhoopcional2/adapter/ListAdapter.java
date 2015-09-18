package br.com.narcisocsales.trabalhoopcional2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.narcisocsales.trabalhoopcional2.R;
import br.com.narcisocsales.trabalhoopcional2.model.Carros;

/**
 * Created by narciso on 01/09/15.
 */
public class ListAdapter extends BaseAdapter
{

    private Context mContext;
    List<Carros> carros;

    public ListAdapter(Context context, List<Carros> carros)
    {
        super();
        mContext=context;
        this.carros = carros;
    }

    public int getCount()
    {
        return carros.size();
    }

    public View getView(int position,  View view, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.items, null);

        String nome = carros.get(position).getNome();
        String marca = carros.get(position).getMarca();
        String ano = carros.get(position).getAno();

        TextView textViewItemsNome = (TextView)view.findViewById(R.id.textViewItemNome);
        TextView textViewItemsMarca = (TextView)view.findViewById(R.id.textViewItemMarca);
        TextView textViewItemsAno = (TextView)view.findViewById(R.id.textViewItemAno);

        textViewItemsNome.setText(nome);
        textViewItemsMarca.setText(marca);
        textViewItemsAno.setText(ano);

        return view;
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
}