package pricemycar.eni.fr.pricemycar.vehicleRecognition;

import android.util.Log;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class PlateAPI {
    final String API_URL = "http://www.regcheck.org.uk/api/reg.asmx/CheckFrance";
    final String API_USER = "moi";
    final String LOG_JSON_ERROR = "L'appel AJAX a échoué !";
    private String vehicle_plate = "bd-454-dx";


    public Vehicle requestAPI(String plate_number){
        AsyncHttpClient client = new AsyncHttpClient();
        // paramètres :
        RequestParams requestParams = new RequestParams();
        requestParams.put("RegistrationNumber", vehicle_plate);
        requestParams.put("username",API_USER);
        // appel :
        client.post(API_URL, requestParams, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String json = new String(response);
                json = StringUtils.substringBetween(json, "<vehicleJson>", "</vehicleJson>");
                try {
                    JSONObject jsonObj = new JSONObject(json);
                    JSONObject extended = jsonObj.getJSONObject("ExtendedData");
                    Log.i("LA VERSION !!! ",extended.getString("libVersion"));
                    Log.i("!!!! LE MODELE : ",extended.getString("libelleModele"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] errorResponse, Throwable e) {
                Log.e(LOG_JSON_ERROR, e.toString());
            }
        });
    }


}
