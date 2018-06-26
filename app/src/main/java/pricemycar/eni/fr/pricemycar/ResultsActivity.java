package pricemycar.eni.fr.pricemycar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.parceler.Parcel;
import org.parceler.Parcels;

import pricemycar.eni.fr.pricemycar.vehicleRecognition.Vehicle;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Vehicle vehicle = Parcels.unwrap(
                getIntent().getParcelableExtra("vehicle"));
        Toast.makeText(ResultsActivity.this,vehicle.toString(),Toast.LENGTH_LONG).show();
    }
}
