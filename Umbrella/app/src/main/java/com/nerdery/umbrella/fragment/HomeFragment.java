package com.nerdery.umbrella.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.nerdery.umbrella.BuildConfig;
import com.nerdery.umbrella.R;
import com.nerdery.umbrella.adapter.ForecastAdapter;
import com.nerdery.umbrella.api.ApiManager;
import com.nerdery.umbrella.model.CurrentObservation;
import com.nerdery.umbrella.model.ForecastCondition;
import com.nerdery.umbrella.model.WeatherData;
import com.nerdery.umbrella.widget.DynamicGridLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * This fragment shows the weather conditions.
 *
 * @author Phil Brown
 * @since 9:52 AM Jul 22, 2015
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    /**
     * The latest weather data
     */
    private List<ForecastCondition> mWeatherData;

    /**
     * Adapter for displaying up-to-date forecast data
     */
    private ForecastAdapter mForecastAdapter;

    /**
     * SharedPreferences where user settings are stored
     */
    private SharedPreferences mSharedPreferences;

    /**
     * SharedPreferences key for accessing the zipcode
     */
    private String zipKey;

    /**
     * SharedPreferences key for accessing the units
     */
    private String unitsKey;

    /**
     * Fahrenheit units name, for String comparison
     */
    private String fahrenheit;

    /**
     * Used to asynchronously retrieve the SharedPreferences.
     */
    private FutureTask<SharedPreferences> mFutureTask;

    /**
     * View that holds the current conditions. This background changes based on temperature.
     */
    private View currentCondition;

    /**
     * Displays the city specified by the zip code
     */
    private TextView city;
    /**
     * Displays the current temperature in the user-specified units
     */
    private TextView temperature;
    /**
     * Current textual weather conditions, such as "clear"
     */
    private TextView condition;

    /**
     * Create a new HomeFragment object
     * @return a new HomeFragment.
     */
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        mFutureTask = new FutureTask<>(new Callable<SharedPreferences>() {
            @Override
            public SharedPreferences call() throws Exception {
                return PreferenceManager.getDefaultSharedPreferences(activity);
            }
        });
        Executors.newSingleThreadExecutor().execute(mFutureTask);
        zipKey = activity.getString(R.string.key_zipcode_preference);
        unitsKey = activity.getString(R.string.key_units_preference);
        fahrenheit = activity.getString(R.string.fahrenheit);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWeatherData = new ArrayList<>();//TODO get saved data from bundle
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.cardList);
        recyclerView.setHasFixedSize(true);
        DynamicGridLayoutManager llm = new DynamicGridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(llm);

        mForecastAdapter = new ForecastAdapter(getActivity(), mWeatherData);
        recyclerView.setAdapter(mForecastAdapter);

        currentCondition = view.findViewById(R.id.current_condition);
        city = (TextView) view.findViewById(R.id.city);
        temperature = (TextView) view.findViewById(R.id.current_temperature);
        condition = (TextView) view.findViewById(R.id.current_conditions);
        ImageButton settings = (ImageButton) view.findViewById(R.id.btn_settings);
        settings.setOnClickListener(this);

        try {
            mSharedPreferences = mFutureTask.get();
        } catch (Exception e) {
            //FutureTask Failed - so just get it again.
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshData();
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        if (id == R.id.btn_settings) {
            openPreferences();
        }
    }

    /**
     * Update the UI with the latest weather data.
     * @param data
     */
    private void update(WeatherData data) {
        mWeatherData.clear();
        mWeatherData.addAll(data.forecast);
        mForecastAdapter.notifyDataSetChanged();

        //update current weather conditions.
        CurrentObservation observation = data.currentObservation;
        if (getActivity() != null && isAdded()) {
            city.setText(observation.displayLocation.getDisplayName());
            String units = mSharedPreferences.getString(unitsKey, getString(R.string.default_units));
            if (fahrenheit.equals(units)) {
                temperature.setText(Html.fromHtml(((int) observation.tempFahrenheit) + "&deg;"));
            }
            else {
                temperature.setText(Html.fromHtml(((int) observation.tempCelsius) + "&deg;"));
            }
            condition.setText(observation.weather);
            if (observation.tempFahrenheit >= 60) {
                currentCondition.setBackgroundColor(getResources().getColor(R.color.weather_warm));
            }
            else {
                currentCondition.setBackgroundColor(getResources().getColor(R.color.weather_cool));
            }
        }

    }

    /**
     * Refreshes the weather data
     */
    private void refreshData() {
        String zipCode = mSharedPreferences.getString(zipKey, null);
        if (zipCode == null) {
            openPreferences();
            return;
        }
        ApiManager.getWeatherApi().getForecastForZip(Integer.parseInt(zipCode), new Callback<WeatherData>() {
            @Override
            public void success(WeatherData weatherData, Response response) {
                update(weatherData);
            }

            @Override
            public void failure(RetrofitError error) {
                if (BuildConfig.DEBUG) {
                    error.printStackTrace();
                }
                Activity activity = getActivity();
                if (activity != null && isAdded()) {
                    Toast.makeText(activity, R.string.could_not_refresh, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Open the preferences fragment
     */
    private void openPreferences() {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null && isAdded()) {
            fragmentManager.beginTransaction().replace(R.id.container, new PreferenceFragment()).addToBackStack(PreferenceFragment.class.getSimpleName()).commitAllowingStateLoss();
        }
    }
}
