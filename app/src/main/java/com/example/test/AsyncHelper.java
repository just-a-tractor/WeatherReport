package com.example.test;

import android.content.Context;
import android.os.AsyncTask;

import com.example.test.Entity.AsyncParam;
import com.example.test.Entity.AsyncRet;
import com.example.test.Entity.Cities.City;
import com.google.gson.Gson;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.test.Utils.Utils;

public class AsyncHelper {

    private Context context;
    private Utils utils;
    private WebApi service;
    protected Gson gson;


    public AsyncHelper(Context _context) {
        context = _context;
        gson = new Gson();
        utils = new Utils();
        CreateWebService();
    }

    public Context getContext() {
        return context;
    }

    public WebApi getWebService() {
        return service;
    }

    public Utils getUtils() {
        return utils;
    }

    private void CreateWebService() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.SRV_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(WebApi.class);
    }

    void ReCreateWebService() {
        CreateWebService();
    }

    public interface OnTaskEventListener<T> {
        void onSuccess(T object);

        void onFailure(String error);
    }

    public interface OnTaskCompleteListener<T> {
        void onTaskComplete(T object);
    }

    public interface OnTaskCompleteRetListener<T> {
        void onTaskComplete(AsyncRet<T> ret);
    }

    public interface OnTaskBooleanCompleteListener<T> {
        void onTaskComplete(boolean result);
    }

    public interface OnTaskIntCompleteListener<T> {
        void onTaskComplete(int result);
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------
    public static class GetCities extends AsyncTask<AsyncParam<AsyncHelper>, Void, AsyncRet<List<City>>> {
        OnTaskCompleteListener<AsyncRet<List<City>>> mCallBack;
        boolean isError = false;
        String message = "";

        GetCities(OnTaskCompleteListener<AsyncRet<List<City>>> callback) {
            mCallBack = callback;
        }

        @Override
        protected final AsyncRet<List<City>> doInBackground(AsyncParam... params) {
            AsyncHelper ah = (AsyncHelper) params[0].getObject();
            String searchCity = params[0].getStrParam();

            List<City> citiesList = null;
            try {
                Response<List<City>> response = ah.getWebService().getCities(Utils.SRV_VER, searchCity, Utils.SRV_KEY, Utils.getLanguage(ah.context)).execute();
                if (response.isSuccessful() && response.body() != null) {
                    citiesList = response.body();
                } else if (!response.isSuccessful()) {
                    isError = true;
                    message = response.message();
                }

                return new AsyncRet<>(isError, response.code(), message, citiesList);

            } catch (Exception ex) {
                isError = true;
                message = ex.getMessage();
                return new AsyncRet<>(isError, 0, message, citiesList);
            }
        }

        @Override
        protected void onPostExecute(AsyncRet<List<City>> result) {
            super.onPostExecute(result);
            mCallBack.onTaskComplete(result);
        }
    }
}
