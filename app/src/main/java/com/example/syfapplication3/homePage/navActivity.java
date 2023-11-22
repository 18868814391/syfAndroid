package com.example.syfapplication3.homePage;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.syfapplication3.R;
import com.example.syfapplication3.classFun.MyRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.syfapplication3.databinding.ActivityNavBinding;

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

    public void goRequest(View v){
        Log.d(TAG, "goRequest: ");
        new Thread() {//网络请求需要在子线程中完成
            @Override
            public void run() {
                MyRequest request = new MyRequest();
                String data = "username=root&password=12345";//POST请求的参数
                String res = request.post("https://www.baidu.com", data);//调用我们写的post方法
                Log.d(TAG, res);
            }
        }.start();
    }

}