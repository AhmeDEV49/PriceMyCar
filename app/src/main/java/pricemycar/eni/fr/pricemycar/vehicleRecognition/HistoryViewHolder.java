package pricemycar.eni.fr.pricemycar.vehicleRecognition;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import pricemycar.eni.fr.pricemycar.R;

public class HistoryViewHolder extends RecyclerView.ViewHolder
{
    // TextView intitulé history :
    public TextView tv_history_label;
    // Constructeur :
    public HistoryViewHolder(View itemView)
    {
        super(itemView);
        tv_history_label = itemView.findViewById(R.id.tv_history_label);
    }
}