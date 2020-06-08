package com.example.proyectofixtura.models;

import java.util.ArrayList;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.proyectofixtura.EquipoActivity;
import com.example.proyectofixtura.MainActivity;
import com.example.proyectofixtura.helpers.QueueUtils;

public class Equipo {
    public String nombre;
    public String descripcion;

    public Equipo(String _nombre, String _descripcion) {
        this.nombre = _nombre;
        this.descripcion = _descripcion;
    }

    public static ArrayList getCollection() {
        ArrayList<Equipo> collection = new ArrayList<>();
        collection.add(new Equipo("Anonymous", "Computacion e Informatica"));
        collection.add(new Equipo("Teanyasha", "Contabilidad"));
        collection.add(new Equipo("Donvictorio", "Gatronomia y Arte Culinario"));
        return collection;
    }
    public static void injectTeamsFromCloud(final QueueUtils.QueueObject o,
                                            final ArrayList<Equipo> equipos,
                                            final EquipoActivity _interface) {
        String url = "https://protected-fjord-91518.herokuapp.com/api/equipos";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if (response.has("objects")) {

                            try {
                                JSONArray list = response.getJSONArray("objects");
                                for (int i=0; i < list.length(); i++) {
                                    JSONObject o = list.getJSONObject(i);
                                    equipos.add(new Equipo(o.getString("nombre"),
                                            o.getString("descripcion")));
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
