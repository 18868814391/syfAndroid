package com.example.syfapplication3.homePage;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.syfapplication3.R;
import com.example.syfapplication3.classFun.MyRequest;
import com.example.syfapplication3.retrofitutils.http.RetrofitUtils;
import com.example.syfapplication3.retrofitutils.http.callback.StringCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.syfapplication3.databinding.ActivityNavBinding;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class navActivity extends AppCompatActivity {
    private static final String TAG="navActivity";
    private ActivityNavBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNavBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_nav);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

//    public void goRequest(View v){
//        JsonObject userObject = new JsonObject();
//        JsonArray addressArray = new JsonArray();
//        addressArray.add("node");
//        addressArray.add("js");
//        userObject.add("tab", addressArray);
//        String jsonStr = userObject.toString();
//
//        Gson gson = new Gson();
//        String body = "{\"tab\": \"[node,js]\"}";
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonStr);
//        RetrofitUtils.post("index.php?r=blog/tabs", requestBody, new StringCallback() {
//            @Override
//            public void onSuccess(String s) {
//
//                Log.d(TAG, "onSuccess: " + s);
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                Log.d(TAG, "onSuccess: " + t);
//            }
//        });
//    }

}