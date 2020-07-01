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
import com.app.forecastweather.models.onecallWeather.Daily;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.DayViewHolder>  {
    Context context;
    List<Daily> dailyList;

    public DayAdapter(Context context, List<Daily> dailyList) {
        this.context = context;
        this.dailyList = dailyList;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_daily,parent, false);
        return new DayViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        holder.dayTextView.setText(Common.getDay(dailyList.get(position).getDt()));
        Picasso.with(context)
                .load(Common.getImage(dailyList.get(position).getWeathers().get(0).getIcon()))
                .into(holder.iconDay);
        holder.minTempTextView.setText(String.format("%d°C", Math.round(dailyList.get(position).getTemp().getDay())));
        holder.maxTempTextView.setText(String.format("%d°C", Math.round(dailyList.get(position).getTemp().getDay())));
    }

    @Override
    public int getItemCount() {
        return dailyList.size();
    }

    public class DayViewHolder extends RecyclerView.ViewHolder {
        TextView dayTextView, minTempTextView, maxTempTextView;
        ImageView iconDay;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            dayTextView = itemView.findViewById(R.id.day_textView);
            iconDay = itemView.findViewById(R.id.icon_day);
            minTempTextView = itemView.findViewById(R.id.minTemp_TextView);
            maxTempTextView = itemView.findViewById(R.id.maxTemp_textView);
        }
    }
}
