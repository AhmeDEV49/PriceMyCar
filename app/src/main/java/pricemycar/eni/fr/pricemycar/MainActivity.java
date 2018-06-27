package pricemycar.eni.fr.pricemycar;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.parceler.Parcels;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import info.hoang8f.widget.FButton;
import pricemycar.eni.fr.pricemycar.ocrreader.OcrCaptureActivity;
import pricemycar.eni.fr.pricemycar.vehicleRecognition.PlateAPI;
import pricemycar.eni.fr.pricemycar.vehicleRecognition.Vehicle;


public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_CODE_ASK_PERMISSIONS = 1;
    private final static String EXTRA_OBJET = "vehicle";
    private static final String[] REQUIRED_SDK_PERMISSIONS = new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE };

    EditText searchTxt;
    FButton btnPhoto;
    FButton btnSearch;
    EditText historyTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PlateAPI plate = new PlateAPI();
        Toast.makeText(MainActivity.this,plate.getVehiculeCote(),Toast.LENGTH_LONG).show();


        searchTxt = findViewById(R.id.txtSearch);
        btnPhoto = findViewById(R.id.btnPhoto);
        btnSearch = findViewById(R.id.btnSearch);
        historyTxt = findViewById(R.id.txtHistory);

        SharedPreferences preferences =
        PreferenceManager.getDefaultSharedPreferences(this);
        historyTxt.setText(preferences.getString("1", "Aucun contenu"));

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OcrCaptureActivity.class);
                startActivity(intent);
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             if(searchPlate()){
                                             PlateAPI api_request = new PlateAPI();
                                             Vehicle vehicle = api_request.requestAPI(searchTxt.getText().toString(), new PlateAPI.OnGetPlate() {
                                                 @Override
                                                 public void onGetVehicle(Vehicle vehicle) {
                                                     SharedPreferences preferences =
                                                             PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                                                     SharedPreferences.Editor editor = preferences.edit();

                                                     editor.putString("1", searchTxt.getText().toString());
                                                     editor.apply();

                                                     Intent intent = new Intent(getApplicationContext(), ResultsActivity.class);
                                                     intent.putExtra(EXTRA_OBJET, Parcels.wrap(vehicle));
                                                     startActivity(intent);
                                                 }
                                             },MainActivity.this);
                                         }
                                     }}
        );
        historyTxt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                searchTxt.setText(historyTxt.getText());
            }
        });
    }
    // Vérifie que la plaque d'immatriculation proposée est au bon format
    public static boolean isImmatriculationValid(String plateNumber)
    {
        String expression = "^[A-Za-z]{2}-[0-9]{3}-[A-Za-z]{2}([0-9]{2})?$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(plateNumber);
        return matcher.matches();
    }

    public boolean searchPlate()
    {
        EditText et = findViewById(R.id.txtSearch);
        Boolean checkPlate = isImmatriculationValid(et.getText().toString());

        Log.i(et.getText().toString(), "plaque");
        if (!checkPlate)
        {
            Toast.makeText(this, "Veuillez entrer un numéro de plaque valide.",
                    Toast.LENGTH_LONG).show();
        }

        return checkPlate;
    }

     @Override
     protected void onResume() {

                historyTxt = findViewById(R.id.txtHistory);

                SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        historyTxt.setText(preferences.getString("1", "Aucun contenu"));
        super.onResume();
     }
}