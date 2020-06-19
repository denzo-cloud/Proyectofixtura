package info.denzo.fixtura;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;

import info.denzo.fixtura.adapters.EquipoAdaptador;
import info.denzo.fixtura.helpers.QueueUtils;
import info.denzo.fixtura.models.Equipo;

public class MainActivity extends AppCompatActivity {

    ListView equiposList;
    EquipoAdaptador equipoAdaptador;
    QueueUtils.QueueObject queue = null;
    ImageLoader queueImage = null;
    ArrayList<Equipo> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = QueueUtils.getInstance(this.getApplicationContext());
        queueImage = queue.getImageLoader();
        items = new ArrayList<>();
        // Item fue llenando desde la nuebe
        Equipo.injectEquiposFromCloud(queue, items, this);

        equiposList = findViewById(R.id.equiposList);
        equipoAdaptador = new EquipoAdaptador(this, items, queueImage);
        equiposList.setAdapter(equipoAdaptador);

        equiposList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // En esta area puedo solicitar mas datos a la nube
                Equipo registro = items.get(position);
                ShowDetails(registro);
                /*Toast.makeText(MainActivity.this, "Persona " + registro.nombre,
                        Toast.LENGTH_SHORT).show();*/
            }
        });

    }

    public void ShowDetails(Equipo item) {
        Intent o = new Intent(this, EquipoDetalleActivity.class);
        o.putExtra("equipoId", item.id);
        startActivity(o);

    }

    public void refreshList(){
        if ( equipoAdaptador != null ) {
            equipoAdaptador.notifyDataSetChanged();
        }
    }
}