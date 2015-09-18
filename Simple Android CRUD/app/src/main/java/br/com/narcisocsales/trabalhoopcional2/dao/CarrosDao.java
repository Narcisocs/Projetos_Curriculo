package br.com.narcisocsales.trabalhoopcional2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.narcisocsales.trabalhoopcional2.model.Carros;

/**
 * Created by Narciso on 01/09/15.
 */
public class CarrosDao extends GenericDAO<Carros> {

    private static final String TABLE_NAME = "carros";

    public CarrosDao(Context context) {
        super(context);
    }

    @Override
    public void insert(Carros obj) {
        db.insert(TABLE_NAME, null, createContentValue(obj));
    }

    @Override
    public void update(Carros obj) {
        db.update(TABLE_NAME, createContentValue(obj), "_id =" + String.valueOf(obj.getId()), null);
    }

    @Override
    public void delete(Carros obj) {
        db.delete(TABLE_NAME, "_id =" + String.valueOf(obj.getId()), null);
    }

    @Override
    public Carros find(Long id) {
        return null;
    }

    @Override
    public List<Carros> findAll() {
        List<Carros> list = new ArrayList<>();

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, "_id");
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);

                Long id = cursor.getLong(cursor.getColumnIndex("_id"));
                String nome = cursor.getString(cursor.getColumnIndex("nome"));
                String marca = cursor.getString(cursor.getColumnIndex("marca"));
                String ano = cursor.getString(cursor.getColumnIndex("ano"));

                Carros p = new Carros(id, nome, marca, ano);
                list.add(p);

            }
        }

        return list;
    }

    @Override
    protected ContentValues createContentValue(Carros obj) {
        ContentValues values = new ContentValues();
        values.put("_id", obj.getId());
        values.put("nome", obj.getNome());
        values.put("marca", obj.getMarca());
        values.put("ano", obj.getAno());
        return values;
    }

}
