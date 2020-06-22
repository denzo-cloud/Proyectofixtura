package info.denzo.fixtura;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import info.denzo.fixtura.adapters.EquipoAdaptador;
import info.denzo.fixtura.adapters.PersonaAdaptador;
import info.denzo.fixtura.helpers.QueueUtils;
import info.denzo.fixtura.models.Equipo;
import info.denzo.fixtura.models.Persona;

public class EquipoDetalleActivity extends AppCompatActivity {
    QueueUtils.QueueObject queue = null;
    int equipoId;
    Equipo equipoObject = new Equipo(0,"","","");
    //ImageLoader imageLoader;
    ListView personasList;
    PersonaAdaptador personaAdaptador;
    ArrayList<Persona> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo_detalle);

        Intent o = getIntent();
        equipoId = o.getIntExtra("equipoId", -1);
        if ( equipoId <= -1 ) {
            Toast.makeText(this, "No se selecciono un contacto.",
                    Toast.LENGTH_SHORT).show();
        }
        //Consumimos informacion detallada de la nube
        equipoObject.id = equipoId;
        personasList = findViewById(R.id.personasList);
        queue = QueueUtils.getInstance(this.getApplicationContext());
        items = new ArrayList<>();
        Persona.injectPersonasFromCloud(queue, items, this, equipoObject);
        personaAdaptador = new PersonaAdaptador(this, items);
        personasList.setAdapter(personaAdaptador);

        /*equipoObject.id = equipoId;
        items = new ArrayList<>();*/
        //Item fue llenando desde la nuebe
        /*equipoObject.id = equipoId;

        personasList = findViewById(R.id.personasList);
        personaAdaptador = new PersonaAdaptador(this, items);
        personasList.setAdapter(personaAdaptador);*/


        /*imageLoader = queue.getImageLoader();
        Equipo.injectEquipoFromCloud(queue, equipoObject, this);*/

    }
    public void refreshList() {
        /*NetworkImageView imgFoto = findViewById(R.id.imgFoto);
        imgFoto.setImageUrl(equipoObject.urlImage, imageLoader);*/
        if ( personaAdaptador != null ) {
            personaAdaptador.notifyDataSetChanged();
        }
    }
}