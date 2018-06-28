package pricemycar.eni.fr.pricemycar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.parceler.Parcel;
import org.parceler.Parcels;
import org.w3c.dom.Text;

import info.hoang8f.widget.FButton;
import pricemycar.eni.fr.pricemycar.vehicleRecognition.Vehicle;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        TextView txtModele = findViewById(R.id.txtModel);
        TextView txtCarburant = findViewById(R.id.txtType);
        TextView txtYear = findViewById(R.id.txtYear);
        TextView txtPlate = findViewById(R.id.txtPlate);
        FButton backBtn = findViewById(R.id.back_button);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // deserialize object
        Vehicle vehicle = Parcels.unwrap(
                getIntent().getParcelableExtra("vehicle"));

        txtModele.setText(vehicle.getLibelleModele());
        txtCarburant.setText(vehicle.getCarburantVersion());
        txtYear.setText(vehicle.getAnneeSortie());
        txtPlate.setText(vehicle.getImmat());


    }
}
