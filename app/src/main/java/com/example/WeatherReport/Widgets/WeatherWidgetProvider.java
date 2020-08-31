package com.example.WeatherReport.Widgets;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class WeatherWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {

        String 	action 		= intent.getAction();

        if(action != null)
            Log.d("### WIDGET ACTION ###", action);
        else
            Log.d("### WIDGET ACTION ###", "NULL");
        super.onReceive(context, intent);
    }
}
