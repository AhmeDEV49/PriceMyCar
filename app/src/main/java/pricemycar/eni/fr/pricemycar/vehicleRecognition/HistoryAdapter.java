package pricemycar.eni.fr.pricemycar.vehicleRecognition;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pricemycar.eni.fr.pricemycar.R;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryViewHolder>
{
    // Liste d'objets métier :
    private List<String> vehicleHistoryList;
    // Constructeur :
    public HistoryAdapter(List<String> vehicleHistoryList)
    {
        this.vehicleHistoryList = vehicleHistoryList;
    }
    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View viewHistory = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_layout, parent, false);
        return new HistoryViewHolder(viewHistory);
    }
    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position)
    {
        holder.tv_history_label.setText(vehicleHistoryList.get(position));
    }
    @Override
    public int getItemCount()
    {
        return vehicleHistoryList.size();
    }
}