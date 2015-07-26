package com.nerdery.umbrella.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.nerdery.umbrella.R;

/**
 * ViewHolder for displaying a Forecast card in the layout.
 *
 * @author Phil Brown
 * @since 8:39 AM Jul 24, 2015
 */
public class ForecastConditionViewHolder extends RecyclerView.ViewHolder {

    /**
     * Displays the name of the day (Today or Tomorrow)
     */
    public TextView day;
    /**
     * The Grid that displays the hourly weather conditions.
     */
    public GridView grid;

    /**
     * Constructor
     * @param itemView  The view to reference
     */
    public ForecastConditionViewHolder(View itemView) {
        super(itemView);
        day = (TextView) itemView.findViewById(R.id.day);
        grid = (GridView) itemView.findViewById(R.id.grid);
        grid.setNumColumns(4);
    }
}
