package info.denzo.fixtura.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.Network;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.w3c.dom.Text;

import java.util.List;

import info.denzo.fixtura.R;
import info.denzo.fixtura.models.Equipo;

public class EquipoAdaptador extends ArrayAdapter<Equipo> {
    Context context;
    ImageLoader queue;

    private class ViewHolder {
        TextView nombre;
        TextView descripcion;
        NetworkImageView image;

        private ViewHolder() {
        }
    }
    public EquipoAdaptador(Context context, List<Equipo> items, ImageLoader _queue) {
        super(context, 0, items);
        this.context = context;
        this.queue = _queue;
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final Equipo rowItem = (Equipo) getItem(position);
        LayoutInflater mInflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_equipo, null);
            holder = new ViewHolder();
            holder.nombre = (TextView) convertView.findViewById(R.id.nombre);
            holder.descripcion = (TextView) convertView.findViewById(R.id.descripcion);
            holder.image = (NetworkImageView) convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.nombre.setText(rowItem.nombre);
        holder.descripcion.setText(rowItem.descripcion);
        holder.image.setImageUrl(rowItem.urlImage, this.queue);
        return convertView;
    }
}
