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
import pricemycar.eni.fr.pricemycar.vehicleRecognition.PlateAPI;
import pricemycar.eni.fr.pricemycar.vehicleRecognition.Vehicle;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        final TextView txtModele = findViewById(R.id.txtModel);
        final TextView txtCarburant = findViewById(R.id.txtType);
        final TextView txtYear = findViewById(R.id.txtYear);
        final TextView txtPlate = findViewById(R.id.txtPlate);
        final TextView txtCote = findViewById(R.id.txtCote);
        FButton backBtn = findViewById(R.id.back_button);

        final PlateAPI api_request = new PlateAPI();
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        // deserialize object
        final Vehicle vehicle = Parcels.unwrap(
                getIntent().getParcelableExtra("vehicle"));

        api_request.setVehiculeCote(vehicle, new PlateAPI.OnGetCote() {
            @Override
            public void onGetCote(String cote_vehicule) {

                vehicle.setCote(cote_vehicule);
                Toast.makeText(ResultsActivity.this,vehicle.getAnneeSortie(),Toast.LENGTH_LONG).show();
                Toast.makeText(ResultsActivity.this,vehicle.toString(),Toast.LENGTH_LONG).show();

                txtModele.setText(vehicle.getLibelleModele());
                txtCarburant.setText(vehicle.getCarburantVersion());
                txtYear.setText(vehicle.getAnneeSortie());
                txtPlate.setText(vehicle.getImmat());
                txtCote.setText(vehicle.getCote());
            }
        });


    }
}
