package info.denzo.fixtura.models;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Queue;

import info.denzo.fixtura.EquipoDetalleActivity;
import info.denzo.fixtura.MainActivity;
import info.denzo.fixtura.helpers.QueueUtils;

public class Equipo {
    public int id;
    public String nombre;
    public String descripcion;
    public String urlImage;

    public Equipo(int _id, String _nombre, String _descripcion, String _urlImage) {
        this.id = _id;
        this.nombre = _nombre;
        this.descripcion = _descripcion;
        this.urlImage = _urlImage;
    }

    public static ArrayList getCollection() {
        ArrayList<Equipo> collection = new ArrayList<>();
        collection.add(new Equipo(0,"Anonymous", "Computacion e Informatica", ""));
        collection.add(new Equipo(0, "Teanyasha", "Contabilidad", ""));
        collection.add(new Equipo(0,"Donvictorio", "Gatronomia y Arte Culinario", ""));
        return collection;
    }

    public static void injectEquipoFromCloud(final QueueUtils.QueueObject o,
                                             final Equipo equipo,
                                             final EquipoDetalleActivity _interface) {
        String url = "https://reqres.in/api/users/" + equipo.id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                if (response.has("data")) {

                                    try {
                                        JSONObject objeto = response.getJSONObject("data");
                                        equipo.nombre = objeto.getString("first_name");
                                        equipo.descripcion = objeto.getString("last_name");
                                        equipo.urlImage = objeto.getString("avatar");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    _interface.refresh(); // Esta función debemos implementarla
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

    public static void injectEquiposFromCloud(final QueueUtils.QueueObject o,
                                              final ArrayList<Equipo> equipos,
                                              final MainActivity _interface) {
        String url = "https://protected-fjord-91518.herokuapp.com/api/equipos";
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
                                            equipos.add(new Equipo(o.getInt("id"), o.getString("nombre"),
                                                    o.getString("descripcion"), o.getString("image")));
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
