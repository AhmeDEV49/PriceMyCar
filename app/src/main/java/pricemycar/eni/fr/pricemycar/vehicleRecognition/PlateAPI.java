package pricemycar.eni.fr.pricemycar.vehicleRecognition;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import static org.apache.commons.lang3.StringUtils.substringBetween;

public class PlateAPI {

    final String API_URL = "http://www.regcheck.org.uk/api/reg.asmx/CheckFrance";
    final String API_ESTIMATION_URL = "https://api.autovisual.com/v2/av";
    final String API_ESTIMATION_KEY = "Gck5sjksJDnR7C1bTJuY7puv19qB6X2fULvQyPfpKmR6";
    final String LOG_JSON_ERROR = "L'appel AJAX a échoué !";
    final String USERNAME = "luc";
    final String PARSE_VEHICLE_ERR_MSG = "Impossible de parser le véhicule...";
    Vehicle vehicle;
    AsyncHttpClient client;


    public Vehicle requestAPI(final String plate_number, final OnGetPlate listener, final Context context){
            if (context instanceof Activity) {
                client = new AsyncHttpClient();

            } else {
                client = new SyncHttpClient();
            }


            // paramètres :
            RequestParams requestParams = new RequestParams();
            requestParams.put("RegistrationNumber", plate_number);
            requestParams.put("username", USERNAME);
            // appel :

            client.post(API_URL, requestParams, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                    String response_vehicle = new String(response);
                    response_vehicle = substringBetween(response_vehicle, "<vehicleJson>", "</vehicleJson");
                    // Parse it into JSON object
                    try {
                        JSONObject vehicle_json = new JSONObject(response_vehicle);
                        JSONObject french_raw_json = vehicle_json.getJSONObject("ExtendedData");
                        // Return the vehicule object
                        vehicle = new Vehicle(vehicle_json.getString("Description"),
                                french_raw_json.getString("anneeSortie"),
                                french_raw_json.getString("boiteDeVitesse"),
                                french_raw_json.getString("carburantVersion"),
                                french_raw_json.getString("libVersion"),
                                french_raw_json.getString("libelleModele"),
                                french_raw_json.getString("nbPlace"),
                                french_raw_json.getString("puissance"),
                                plate_number);
                        listener.onGetVehicle(vehicle);
                    } catch (JSONException e) {
                        Toast.makeText(context, "Erreur JSON", Toast.LENGTH_LONG).show();
                        Log.i("PARSE_VEHICLE_ERR", PARSE_VEHICLE_ERR_MSG);
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers,
                                      byte[] errorResponse, Throwable e) {
                    Log.e(LOG_JSON_ERROR, e.toString());
                }
            });
            return vehicle;
        }


        public String getVehiculeCote() {
            client = new AsyncHttpClient();
            // paramètres :
            RequestParams requestParams = new RequestParams();
            requestParams.put("key", API_ESTIMATION_KEY);
            requestParams.put("country_ref", "FR");
            requestParams.put("txt", "Renault Mégane 3 III dCi 110 ");
            requestParams.put("dt_entry_service", 2018);
            requestParams.put("km", 0);


            // appel :
            client.post(API_ESTIMATION_URL, requestParams, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                    String response_vehicle = new String(response);
                    try {
                        JSONObject estimation_vehicle_json = new JSONObject(response_vehicle);
                        Log.i("COTE VEHICULE : ", estimation_vehicle_json.toString());

                    } catch (JSONException e) {
                        Log.i("PARSE_VEHICLE_ERR_MSG", e.toString());
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers,
                                      byte[] errorResponse, Throwable e) {
                    Log.e(LOG_JSON_ERROR, e.toString());
                }
            });
            return "VIDEEEEEEEEEEEE";
        }

        public interface OnGetPlate {
            void onGetVehicle(Vehicle vehicle);
        }
    }
