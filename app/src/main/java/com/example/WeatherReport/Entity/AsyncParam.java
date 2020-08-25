package com.example.WeatherReport.Entity;

import android.content.Context;

public class AsyncParam<T> {

    private T obj;
    private Context context;
    private boolean boolParam;
    private int intParam;
    private String strParam;

    public AsyncParam(Context _context, T _obj, boolean _boolParam, int _intParam, String _strParam) {
        context = _context;
        obj = _obj;
        boolParam = _boolParam;
        intParam = _intParam;
        strParam = _strParam;
    }

    public Context getContext() {
        return context;
    }

    public T getObject() {
        return obj;
    }

    public boolean getBoolParam() {
        return boolParam;
    }
    public int getIntParam() {
        return intParam;
    }
    public String getStrParam() {
        return strParam;
    }
}
