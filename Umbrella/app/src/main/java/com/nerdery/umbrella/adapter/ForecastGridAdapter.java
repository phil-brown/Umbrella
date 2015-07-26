package com.nerdery.umbrella.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.nerdery.umbrella.R;
import com.nerdery.umbrella.api.ApiManager;
import com.nerdery.umbrella.api.IconApi;
import com.nerdery.umbrella.model.ForecastCondition;
import com.squareup.picasso.Picasso;

/**
 * Adapter for showing Forecast data in a grid.
 *
 * @author Phil Brown
 * @since 11:19 AM Jul 24, 2015
 */
public class ForecastGridAdapter extends ArrayAdapter<ForecastCondition> {

    LayoutInflater mLayoutInflater;
    Context mContext;
    boolean fahrenheit;
    private IconApi mIconApi;

    public ForecastGridAdapter(Context context, ForecastCondition[] conditions, boolean fahrenheit) {
        super(context, 0, conditions);
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
        this.fahrenheit = fahrenheit;
        mIconApi = ApiManager.getIconApi();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.cell_forecast, parent, false);
            holder = new ViewHolder();
            holder.time = (TextView) convertView.findViewById(R.id.time);
            holder.temperature = (TextView) convertView.findViewById(R.id.temperature);
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        ForecastCondition condition = getItem(position);
        holder.time.setText(condition.displayTime);
        Picasso.with(mContext).load(mIconApi.getUrlForIcon(condition.condition, false)).into(holder.icon);
        if (fahrenheit) {
            holder.temperature.setText(Html.fromHtml(condition.tempFahrenheit + "&deg;"));
        }
        else {
            holder.temperature.setText(Html.fromHtml(condition.tempCelsius + "&deg;"));
        }
        //TODO set colors
        convertView.setTag(holder);
        return convertView;
    }

    public static class ViewHolder {
        TextView time, temperature;
        ImageView icon;
    }
}
