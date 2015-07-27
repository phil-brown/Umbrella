package com.nerdery.umbrella.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private ForecastCondition[] today, tomorrow;

    private Context mContext;

    private boolean fahrenheit;

    private List<ForecastCondition> mWeatherData;

    /**
     * Constructor
     * @param weatherData
     */
    public ForecastAdapter(Context context, List<ForecastCondition> weatherData) {
        mContext = context;
        mWeatherData = weatherData;
        updateForecast();
    }



    public void setFahrenheit(boolean fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

    public ForecastCondition[] getToday() {
        return today;
    }

    public ForecastCondition[] getTomorrow() {
        return tomorrow;
    }

    @Override
    public ForecastConditionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.cell_weather, parent, false);

        return new ForecastConditionViewHolder(itemView);
    }

    public void updateForecast() {
        if (mWeatherData == null || mWeatherData.isEmpty()) {
            today = new ForecastCondition[0];
            tomorrow = new ForecastCondition[0];
            return;
        }
        today = new ForecastCondition[24];
        tomorrow = new ForecastCondition[24];
        int index = -1;
        ForecastCondition[] array = today;
        ForecastCondition condition = null;
        do {
            index++;
            condition = mWeatherData.get(index);
            array[index] = condition;
        }
        while (condition != null && !condition.displayTime.equals("12:00 AM"));//TODO consider using condition.time with Calendar.
        today = resizeArray(today);
        array = tomorrow;
        for (int i = 0; i < mWeatherData.size() - index; i++) {
            array[i] = mWeatherData.get(i);
        }
    }

    @Override
    public void onBindViewHolder(ForecastConditionViewHolder holder, int position) {
        updateForecast();//FIXME not a good place to do calculation
        if (position == 0) {
            //today
            holder.day.setText("Today");
            holder.grid.setAdapter(new ForecastGridAdapter(mContext, today, fahrenheit));
        }
        else {
            //tomorrow
            holder.day.setText("Tomorrow");
            holder.grid.setAdapter(new ForecastGridAdapter(mContext, tomorrow, fahrenheit));
        }
    }

    @Override
    public int getItemCount() {
        //Today and tomorrow
        return 2;
    }

    /**
     * Removes nulls
     * @param array the array to resize
     * @return a new, possibly shorter array with no nulls.
     */
    public static ForecastCondition[] resizeArray(ForecastCondition[] array) {
        //all nulls will be at the front
        ForecastCondition[] newArray = null;
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                newArray = new ForecastCondition[array.length - i];
                index = i;
                break;
            }
        }
        if (newArray == null) {
            return new ForecastCondition[0];
        }
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[index];
        }
        return newArray;
    }
}
