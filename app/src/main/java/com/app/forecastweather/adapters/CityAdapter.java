package com.app.forecastweather.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.forecastweather.CityDBHelper;
import com.app.forecastweather.R;
import com.app.forecastweather.models.City;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {
    Context context;
    List<City> cities;
    private OnCityListener mOnCityListener;

    public CityAdapter(Context context, List<City> cities, OnCityListener onCityListener) {
        this.context = context;
        this.cities = cities;
        this.mOnCityListener = onCityListener;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_city,parent, false);
        return new CityViewHolder(view, mOnCityListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        holder.city.setText(cities.get(position).getName());

    }



    @Override
    public int getItemCount() {
        return cities.size();
    }

    public class CityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView city;
        ImageButton deleteCity;
        OnCityListener onCityListener;

        public CityViewHolder(@NonNull View itemView, OnCityListener onCityListener) {
            super(itemView);
            city = (TextView) itemView.findViewById(R.id.city);
            deleteCity = (ImageButton) itemView.findViewById(R.id.deleteCity);
            this.onCityListener = onCityListener;
            itemView.setOnClickListener(this);

            deleteCity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAbsoluteAdapterPosition();
                    CityDBHelper cityDBHelper = new CityDBHelper(v.getContext());
                    cityDBHelper.deleteCity(cities.get(position).getId());
                    cities.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, cities.size());
                }
            });
        }

        @Override
        public void onClick(View v) {
            onCityListener.onCityClick(getAdapterPosition());
        }
    }

    public  interface OnCityListener {
        void onCityClick(int position);
    }
}
