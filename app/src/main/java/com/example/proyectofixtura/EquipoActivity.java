package com.example.proyectofixtura;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import com.example.proyectofixtura.adapters.EquipoAdaptador;
import com.example.proyectofixtura.helpers.QueueUtils;
import com.example.proyectofixtura.models.Equipo;

public class EquipoActivity extends AppCompatActivity {

    ListView equiposList;
    EquipoAdaptador equipoAdaptador;
    QueueUtils.QueueObject queue = null;
    ArrayList<Equipo> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo);

        equiposList = findViewById(R.id.equiposList);
        queue = QueueUtils.getInstance(this.getApplicationContext());
        items = new ArrayList<>();
        Equipo.injectTeamsFromCloud(queue, items, this);
        equipoAdaptador = new EquipoAdaptador(this, items);
        equiposList.setAdapter(equipoAdaptador);
    }
    public void refreshList(){
        if ( equipoAdaptador != null ) {
            equipoAdaptador.notifyDataSetChanged();
        }
    }
}