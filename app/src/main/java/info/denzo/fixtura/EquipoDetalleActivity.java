package info.denzo.fixtura;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import info.denzo.fixtura.helpers.QueueUtils;
import info.denzo.fixtura.models.Equipo;

public class EquipoDetalleActivity extends AppCompatActivity {
    QueueUtils.QueueObject queue = null;
    int equipoId;
    Equipo equipoObject = new Equipo(0,"","","");
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
        /// Consumimos informacion detallada de la nube
        equipoObject.id = equipoId;
        queue = QueueUtils.getInstance(this.getApplicationContext());
        Equipo.injectEquipoFromCloud(queue, equipoObject, this);

    }
    public void refresh() {
        TextView txtNombre = findViewById(R.id.txtNombre);
        txtNombre.setText(equipoObject.nombre);
    }
}