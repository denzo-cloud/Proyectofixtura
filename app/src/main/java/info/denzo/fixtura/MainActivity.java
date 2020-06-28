package info.denzo.fixtura;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.toolbox.ImageLoader;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import info.denzo.fixtura.adapters.DesafioAdaptador;
import info.denzo.fixtura.helpers.QueueUtils;
import info.denzo.fixtura.models.Desafio;

public class MainActivity extends AppCompatActivity {

    ListView desafiosList;
    DesafioAdaptador desafioAdaptador;
    QueueUtils.QueueObject queue = null;
    ImageLoader queueImage = null;
    ArrayList<Desafio> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        desafiosList = findViewById(R.id.desafiosList);
        queue = QueueUtils.getInstance(this.getApplicationContext());
        queueImage = queue.getImageLoader();
        items = new ArrayList<>();
        Desafio.injectDesafiosFromCloud(queue, items, this);
        desafioAdaptador = new DesafioAdaptador(this, items, queueImage);
        desafiosList.setAdapter(desafioAdaptador);
    }

    public void refreshList(){
        if ( desafioAdaptador!= null ) {
            desafioAdaptador.notifyDataSetChanged();
        }
    }
}