package com.nerdery.umbrella.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nerdery.umbrella.R;
import com.nerdery.umbrella.model.ForecastCondition;
import com.nerdery.umbrella.model.WeatherData;
import com.nerdery.umbrella.ui.ForecastConditionViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Used for displaying forecast information.
 * @author Phil Brown
 * @since 8:37 AM Jul 24, 2015
 */
public class ForecastAdapter extends RecyclerView.Adapter<ForecastConditionViewHolder> {

    /**
     * The displayed data
     */
    private List<ForecastCondition> mWeatherData;

    /**
     * Constructor
     * @param weatherData
     */
    public ForecastAdapter(List<ForecastCondition> weatherData) {
        this.mWeatherData = weatherData;
        if (this.mWeatherData == null) {
            this.mWeatherData = new ArrayList<>();
        }
    }

    @Override
    public ForecastConditionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.cell_weather, parent, false);

        return new ForecastConditionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ForecastConditionViewHolder holder, int position) {
        ForecastCondition data = mWeatherData.get(position);
        holder.day.setText(data.displayTime);
        //TODO add more data as needed.
    }

    @Override
    public int getItemCount() {
        return mWeatherData.size();
    }
}
