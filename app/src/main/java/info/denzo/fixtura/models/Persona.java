package info.denzo.fixtura.models;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import info.denzo.fixtura.EquipoDetalleActivity;
import info.denzo.fixtura.helpers.QueueUtils;

public class Persona {
    public String nombre;
    public String apellido;

    public Persona(String _nombre, String _apellido) {
        this.nombre = _nombre;
        this.apellido = _apellido;
    }

    public static ArrayList getCollection() {
        ArrayList<Persona> collection = new ArrayList<>();
        collection.add(new Persona("Maria","Marin"));
        collection.add(new Persona("Rosa", "Mendoza"));
        collection.add(new Persona("Ana","Ortega"));
        return collection;
    }

    public static void injectPersonasFromCloud(final QueueUtils.QueueObject o,
                                              final ArrayList<Persona> personas,
                                              final EquipoDetalleActivity _interface, final Equipo equipo) {
        String url = "https://protected-fjord-91518.herokuapp.com/api/auth/competidorequipos/" + equipo.id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                if (response.has("objects")) {

                                    try {
                                        JSONArray list = response.getJSONArray("objects");
                                        for (int i=0; i < list.length(); i++) {
                                            JSONObject o = list.getJSONObject(i);
                                            personas.add(new Persona(o.getString("nombre"),
                                                    o.getString("apellido")));
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    _interface.refreshList(); // Esta función debemos implementarla
                                    // en nuestro activity
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        o.addToRequestQueue(jsonObjectRequest);
    }
}
