package com.example.proyectofixtura.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import com.example.proyectofixtura.R;
import com.example.proyectofixtura.models.Equipo;

public class EquipoAdaptador extends ArrayAdapter<Equipo>{
    Context context;
    private class ViewHolder {
        TextView nombre;
        TextView descripcion;

        private ViewHolder() {
        }
    }
    public EquipoAdaptador(Context context, List<Equipo> items) {
        super(context, 0, items);
        this.context = context;
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final Equipo rowItem = (Equipo) getItem(position);
        LayoutInflater mInflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.equipo_item, null);
            holder = new ViewHolder();
            holder.nombre = (TextView) convertView.findViewById(R.id.nombre);
            holder.descripcion = (TextView) convertView.findViewById(R.id.descripcion);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.nombre.setText(rowItem.nombre);
        holder.descripcion.setText(rowItem.descripcion);
        return convertView;
    }
}
