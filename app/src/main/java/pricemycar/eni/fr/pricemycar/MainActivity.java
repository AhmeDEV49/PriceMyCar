package pricemycar.eni.fr.pricemycar;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pricemycar.eni.fr.pricemycar.ocrreader.OcrCaptureActivity;
import pricemycar.eni.fr.pricemycar.vehicleRecognition.PlateAPI;
import pricemycar.eni.fr.pricemycar.vehicleRecognition.Vehicle;

public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_CODE_ASK_PERMISSIONS = 1;

    private static final String[] REQUIRED_SDK_PERMISSIONS = new String[] {
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE };

    EditText searchTxt;
    ImageButton btnPhoto;
    ImageButton btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchTxt = findViewById(R.id.txtSearch);
        btnPhoto = findViewById(R.id.btnPhoto);
        btnSearch = findViewById(R.id.btnSearch);

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OcrCaptureActivity.class);
                startActivity(intent);
            }
        });






        btnSearch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PlateAPI api_request = new PlateAPI();
                        Vehicle vehicle = api_request.requestAPI(searchTxt.getText().toString(), new PlateAPI.OnGetPlate() {
                            @Override
                            public void onGetVehicle(Vehicle vehicle) {
                                Toast.makeText(MainActivity.this,vehicle.toString(),Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
        );

    }

    // Vérifie que la plaque d'immatriculation proposée est au bon format
    public static boolean isImmatriculationValid(String plateNumber)
    {
        String expression = "^[A-Za-z]{2}-[0-9]{3}-[A-Za-z]{2}([0-9]{2})?$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(plateNumber);
        return matcher.matches();
    }

    public void searchPlate(View view)
    {
        EditText et = findViewById(R.id.txtSearch);
        Boolean checkPlate = isImmatriculationValid(et.getText().toString());

        Log.i(et.getText().toString(), "plaque");
        if (checkPlate)
        {
            Toast.makeText(this, "Numéro de plaque valide.",
                    Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "Numéro de plaque invalide.",
                    Toast.LENGTH_LONG).show();

        }
    }
}
