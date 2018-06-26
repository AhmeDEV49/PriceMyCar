package pricemycar.eni.fr.pricemycar;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pricemycar.eni.fr.pricemycar.ocrreader.OcrCaptureActivity;

public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_CODE_ASK_PERMISSIONS = 1;

    private static final String[] REQUIRED_SDK_PERMISSIONS = new String[] {
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE };

    EditText searchTxt;
    ImageButton btnPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchTxt = findViewById(R.id.txtSearch);
        btnPhoto = findViewById(R.id.btnPhoto);

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OcrCaptureActivity.class);
                startActivity(intent);
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
