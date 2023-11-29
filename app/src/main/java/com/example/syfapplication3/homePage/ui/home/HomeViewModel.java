package com.example.syfapplication3.homePage.ui.home;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.syfapplication3.retrofitutils.http.RetrofitUtils;
import com.example.syfapplication3.retrofitutils.http.callback.StringCallback;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class HomeViewModel extends ViewModel {
    private static final String TAG="homeViewModal";
    private final MutableLiveData<String> mText;
    private MutableLiveData<List<String>> tabs;
    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<String> getTabas() {
        if(tabs==null){

        }
        return mText;
    }
    public void goRequest(View v){
        JsonObject userObject = new JsonObject();
        JsonArray addressArray = new JsonArray();
        addressArray.add("node");
        addressArray.add("js");
        userObject.add("tab", addressArray);
        String jsonStr = userObject.toString();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonStr);
        RetrofitUtils.post("index.php?r=blog/tabs", requestBody, new StringCallback() {
            @Override
            public void onSuccess(String s) {
                Log.d(TAG, "onSuccess: " + s);
            }
            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onSuccess: " + t);
            }
        });
    }



}