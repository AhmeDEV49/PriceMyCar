package pricemycar.eni.fr.pricemycar;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pricemycar.eni.fr.pricemycar.vehicleRecognition.HistoryAdapter;

public class ActivityHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
// init :
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_history);
// à ajouter pour de meilleures performances :
        recyclerView.setHasFixedSize(true);
// layout manager, décrivant comment les items sont disposés :
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
// contenu d'exemple :

        List<String> listVehicleHistory = new ArrayList<>();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Map<String,?> keys = prefs.getAll();

        for(Map.Entry<String,?> entry : keys.entrySet()){
            Log.d("Previous search : ",entry.getKey() + " : " +
                    entry.getValue().toString());
            listVehicleHistory.add(entry.getValue().toString());
        }
// adapter :
        HistoryAdapter historiesAdapter = new HistoryAdapter(listVehicleHistory);
        recyclerView.setAdapter(historiesAdapter);
    }
}
