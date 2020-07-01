package com.app.forecastweather.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.forecastweather.R;
import com.app.forecastweather.common.Common;
import com.app.forecastweather.models.onecallWeather.Hourly;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourViewHolder> {
    List<Hourly> hourly;
    Context context;

    public HourAdapter(Context context, List<Hourly> hourly) {
        this.context = context;
        this.hourly = hourly;
    }

    @NonNull
    @Override
    public HourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_hour, parent, false);
        return new HourViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull HourViewHolder holder, int position) {
        holder.hour_textView.setText(String.format("%d:00", Common.getHour(hourly.get(position).getDt())));
        holder.temp_hour_textView.setText(String.format("%d°C",Math.round(hourly.get(position).getTemp())));
        Picasso.with(this.context)
                .load(Common.getImage(hourly.get(position).getWeathers().get(0).getIcon()))
                .into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return hourly.size();
    }

    public class HourViewHolder extends RecyclerView.ViewHolder {
        TextView hour_textView, temp_hour_textView;
        ImageView icon;

        public HourViewHolder(@NonNull View itemView) {
            super(itemView);
            hour_textView = itemView.findViewById(R.id.hour_textView);
            temp_hour_textView = itemView.findViewById(R.id.temp_hourly_textView);
            icon = itemView.findViewById(R.id.icon);
        }
    }
}
