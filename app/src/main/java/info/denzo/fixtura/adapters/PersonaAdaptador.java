package info.denzo.fixtura.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import info.denzo.fixtura.R;
import info.denzo.fixtura.models.Persona;

public class PersonaAdaptador extends ArrayAdapter<Persona> {
    Context context;
    private class ViewHolder {
        TextView nombre;
        TextView apellido;

        private ViewHolder() {
        }
    }
    public PersonaAdaptador(Context context, List<Persona> items) {
        super(context, 0, items);
        this.context = context;
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final Persona rowItem = (Persona) getItem(position);
        LayoutInflater mInflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_persona, null);
            holder = new ViewHolder();
            holder.nombre = (TextView) convertView.findViewById(R.id.nombre);
            holder.apellido = (TextView) convertView.findViewById(R.id.apellido);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.nombre.setText(rowItem.nombre);
        holder.apellido.setText(rowItem.apellido);
        return convertView;
    }
}
