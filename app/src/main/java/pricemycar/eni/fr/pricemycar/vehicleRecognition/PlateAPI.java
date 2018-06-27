package pricemycar.eni.fr.pricemycar.vehicleRecognition;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

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
    final String LOG_JSON_ERROR = "L'appel AJAX a échoué !";
    final String USERNAME = "martin";
    final String PARSE_VEHICLE_ERR_MSG = "Impossible de parser le véhicule...";
    Vehicle vehicle;

    public Vehicle requestAPI(String plate_number, final OnGetPlate listener, Context context) {
        AsyncHttpClient client;
        if(context instanceof Activity){
            client = new AsyncHttpClient();

        }else{
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
                            french_raw_json.getString("puissance"));
                    listener.onGetVehicle(vehicle);
                } catch (JSONException e) {
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
    public interface OnGetPlate{
        void onGetVehicle(Vehicle vehicle);
    }
}