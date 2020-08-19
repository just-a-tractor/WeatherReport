package com.example.test.Entity;

import android.content.Context;

public class AsyncParam<T> {

    private T obj;
    private Context context;
    private boolean flag;

    public AsyncParam(Context _context, T _obj, boolean _flag) {
        context = _context;
        obj = _obj;
        flag = _flag;
    }

    public Context getContext() {
        return context;
    }

    public T getObject() {
        return obj;
    }

    public boolean getFlag() {
        return flag;
    }
}
