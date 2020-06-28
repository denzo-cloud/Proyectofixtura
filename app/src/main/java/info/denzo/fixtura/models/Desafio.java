package info.denzo.fixtura.models;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import info.denzo.fixtura.MainActivity;
import info.denzo.fixtura.helpers.QueueUtils;

public class Desafio {
    public String invitadoNombre;
    public String invitadoImage;
    public String retadorNombre;
    public String retadorImage;
    public String fecha;

    public Desafio(String _invitadoNombre, String _invitadoImage, String _retadorNombre,
                   String _retadorImage, String _fecha) {
        this.invitadoNombre = _invitadoNombre;
        this.invitadoImage = _invitadoImage;
        this.retadorNombre = _retadorNombre;
        this.retadorImage = _retadorImage;
        this.fecha = _fecha;
    }

    public static ArrayList getCollection() {
        ArrayList<Desafio> collection = new ArrayList<>();
        collection.add(new Desafio("Anonymous", "https://cdn0.iconfinder.com/data/icons/social-flat-rounded-rects/512/anonymous-512.png",
                "Monkeycoins", "https://cdn3.iconfinder.com/data/icons/virus-transmission-color/48/Monkey_Virus-512.png", "2020-06-25 21:15:39"));
        return collection;
    }

    public static void injectDesafiosFromCloud(final QueueUtils.QueueObject o,
                                               final ArrayList<Desafio> desafios,
                                               final MainActivity _interface) {
        String url = "http://protected-fjord-91518.herokuapp.com/api/auth/desafios";
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        if (response.has("objects")) {
                            try {
                                JSONArray list = response.getJSONArray("objects");
                                //JSONObject jsonObjectInvitado = response.getJSONObject("invitado");
                                for (int i=0; i < list.length(); i++) {
                                    JSONObject o = list.getJSONObject(i);
                                    //JSONObject re = response.getJSONObject("retador");
                                    desafios.add(new Desafio(o.getString("nombre"), "", "Plaga",
                                            "","2020-12-12"));
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
