package com.phoenix.rxweather;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.phoenix.rxweather.constant.AppConstant;
import com.phoenix.rxweather.data.WeatherBean;
import com.phoenix.rxweather.net.ApiWeather;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class WeatherActivity extends Activity {
    private static final String TAG = "WeatherActivity";

    private TextView mLocation;
    private TextView mTmp;

    private WeatherBean mBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_activity);
        mLocation = findViewById(R.id.weather_local);
        mTmp = findViewById(R.id.weather_tmp);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMultiWeatherFlatmap();
    }

    private void getWeatherRetrofitRx() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(AppConstant.ULR)
                .build();

        ApiWeather apiWeather = retrofit.create(ApiWeather.class);
        apiWeather.getWeather("Beijing", AppConstant.APP_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<WeatherBean, String>() {
                    @Override
                    public String call(WeatherBean bean) {
                        return String.valueOf(bean.getMain().getTemp());
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        mTmp.setText(String.valueOf(s);
                    }
                });

    }

    private void getMultiWeatherFlatmap() {
        String[] cities = new String[]{"Beijing", "Shanghai", "Guangzhou"};
        final Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(AppConstant.ULR)
                .build();

        Observable.from(cities)
                .flatMap(new Func1<String, Observable<WeatherBean>>() {
                    @Override
                    public Observable<WeatherBean> call(String s) {
                        ApiWeather apiWeather = retrofit.create(ApiWeather.class);
                        return apiWeather.getWeather(s, AppConstant.APP_KEY);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onNext(WeatherBean bean) {
                        String temp = String.valueOf(bean.getMain().getTemp());
                        Log.d(TAG, "onNext: " + temp);
                    }
                });
    }

    /*
    private void getWeatherRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.ULR)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiWeather apiWeather = retrofit.create(ApiWeather.class);
        Call<ResponseBody> call = apiWeather.getWeather("Beijing", AppConstant.APP_KEY);
        call.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                Log.d(TAG, "onResponse: ");
                try {
                    if(response.body() != null) {
                        String body = response.body().string();
                        Log.d(TAG, "onResponse: " + body);
                        Type type = new TypeToken<WeatherBean>() {}.getType();
                        mBean = new Gson().fromJson(body, type);

                        WeatherActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                invalidateWeather(mBean);
                            }
                        });
                    }
                } catch (Exception e) {
                    Log.d(TAG, "onResponse: " + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }
    */

    /*
    private void getWeatherRx() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                final Request request = new Request.Builder().url(getWeatherUrl()).build();
                Call call = OKHttpManager.getInstance().getClient().newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d(TAG, "onFailure: " + e.getMessage());
                        subscriber.onError(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            if(response.body() != null) {
                                String body = response.body().string();
                                Log.d(TAG, "onResponse: " + body);
                                Type type = new TypeToken<WeatherBean>() {}.getType();
                                mBean = new Gson().fromJson(body, type);

                                subscriber.onNext(String.valueOf(mBean.getMain().getTemp()));
                                subscriber.onCompleted();
                            }
                        } catch (Exception e) {
                            Log.d(TAG, "onResponse: " + e.getMessage());
                        }
                    }
                });
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: ");
                        mTmp.setText(s);
                    }
                });
    }
    */

    /*
    private void getWeatherJava() {
        final Request request = new Request.Builder().url(getWeatherUrl()).build();
        okhttp3.Call call = OKHttpManager.getInstance().getClient().newCall(request);

        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                try {
                    if(response.body() != null) {
                        String body = response.body().string();
                        Log.d(TAG, "onResponse: " + body);
                        Type type = new TypeToken<WeatherBean>() {}.getType();
                        WeatherBean bean = new Gson().fromJson(body, type);

                        WeatherActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                invalidateWeather(bean);
                            }
                        });
                    }
                } catch (Exception e) {
                    Log.d(TAG, "onResponse: " + e.getMessage());
                }
            }
        });
    }
    */


    /**
     * @param bean 请求结果
     */
    private void invalidateWeather(WeatherBean bean) {
        mLocation.setText(bean.getSys().getCountry());
        mTmp.setText(String.valueOf(bean.getMain().getTemp()));
    }

    /**
     * @return 某地区天气请求的url
     */
    private String getWeatherUrl() {
        String location = "Beijing";
        return String.format(AppConstant.URL_CITY, location, AppConstant.APP_KEY);
    }
}
