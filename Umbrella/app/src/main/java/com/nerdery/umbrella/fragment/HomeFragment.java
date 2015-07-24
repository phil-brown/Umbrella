package com.nerdery.umbrella.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

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
     * Used to asynchronously retrieve the SharedPreferences.
     */
    private FutureTask<SharedPreferences> mFutureTask;

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
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWeatherData = new ArrayList<>();//TODO get saved data from bundle
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SwipeRefreshLayout view = (SwipeRefreshLayout) inflater.inflate(R.layout.fragment_home, container, false);
        view.setOnRefreshListener(this);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.cardList);
        recyclerView.setHasFixedSize(true);
        DynamicGridLayoutManager llm = new DynamicGridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(llm);

        mForecastAdapter = new ForecastAdapter(mWeatherData);
        recyclerView.setAdapter(mForecastAdapter);

        try {
            mSharedPreferences = mFutureTask.get();
        } catch (Exception e) {
            //FutureTask Failed - so just get it again.
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        }

        return view;
    }

    @Override
    public void onRefresh() {
        refreshData();
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshData();
    }

    private void update(WeatherData data) {
        mWeatherData.clear();
        mWeatherData.addAll(data.forecast);
        mForecastAdapter.notifyDataSetChanged();

        //update current weather conditions.
        CurrentObservation observation = data.currentObservation;
        //TODO update UI
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
            fragmentManager.beginTransaction().add(R.id.container, new PreferenceFragment()).addToBackStack(PreferenceFragment.class.getSimpleName()).commitAllowingStateLoss();
        }
    }
}
