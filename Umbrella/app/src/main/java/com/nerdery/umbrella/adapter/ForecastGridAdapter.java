package com.nerdery.umbrella.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.nerdery.umbrella.model.ForecastCondition;

/**
 * Adapter for showing Forecast data in a grid.
 *
 * @author Phil Brown
 * @since 11:19 AM Jul 24, 2015
 */
public class ForecastGridAdapter extends ArrayAdapter<ForecastCondition> {

    public ForecastGridAdapter(Context context, ForecastCondition[] conditions) {
        super(context, 0, conditions);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //TODO R.layout.cell_forecast
        return super.getView(position, convertView, parent);
    }
}
