package com.example.proyectofixtura;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NosotrosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nosotros);

        Button iraprincipal = findViewById(R.id.iraprincipal);
        Button iradelegado = findViewById(R.id.iradelegado);
        Button irajurado = findViewById(R.id.irajurado);

        iraprincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent( NosotrosActivity.this, MainActivity.class);
                startActivity(o);
            }
        });
        iradelegado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent u = new Intent( NosotrosActivity.this, DelegadoActivity.class);
                startActivity(u);
            }
        });

        irajurado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent u = new Intent( NosotrosActivity.this, JuradoActivity.class);
                startActivity(u);
            }
        });


    }
}
