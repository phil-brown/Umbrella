package com.nerdery.umbrella.adapter;

import android.content.Context;
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
    private ForecastCondition[] today, tomorrow;

    private Context mContext;

    /**
     * Constructor
     * @param weatherData
     */
    public ForecastAdapter(Context context, List<ForecastCondition> weatherData) {
        mContext = context;
        today = new ForecastCondition[24];
        tomorrow = new ForecastCondition[24];
        int index = 0;
        ForecastCondition[] array = today;
        ForecastCondition condition = null;
        do {
            condition = weatherData.get(index);
            array[index] = condition;
            index++;
        }
        while (condition != null && !condition.displayTime.equals("12:00 AM"));//TODO consider using condition.time with Calendar.
        today = resizeArray(today);
        array = tomorrow;
        for (int i = 0; i < weatherData.size() - index; i++) {
            array[i] = weatherData.get(i);
        }
    }

    public ForecastCondition[] getToday() {
        return today;
    }

    public void setToday(ForecastCondition[] today) {
        this.today = today;
    }

    public ForecastCondition[] getTomorrow() {
        return tomorrow;
    }

    public void setTomorrow(ForecastCondition[] tomorrow) {
        this.tomorrow = tomorrow;
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
        if (position == 0) {
            //today
            holder.day.setText("Today");
            holder.grid.setAdapter(new ForecastGridAdapter(mContext, today));
        }
        else {
            //tomorrow
            holder.day.setText("Tomorrow");
            holder.grid.setAdapter(new ForecastGridAdapter(mContext, tomorrow));
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
