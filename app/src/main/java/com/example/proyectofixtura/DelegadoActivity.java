package com.example.proyectofixtura;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DelegadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delegado);

        Button iranosotros = findViewById(R.id.iranosotros);

        iranosotros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent u = new Intent( DelegadoActivity.this, NosotrosActivity.class);
                startActivity(u);
            }
        });
    }
}
