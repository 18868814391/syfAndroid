package com.example.syfapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.syfapplication3.retrofitutils.http.RetrofitUtils;
import com.example.syfapplication3.retrofitutils.http.RetrofitManager;
import com.example.syfapplication3.retrofitutils.http.callback.StringCallback;

import retrofit2.Retrofit;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final Retrofit retrofit = RetrofitManager.getInstance().setBaseUrl("https://www.shenyifan.top/apis/php/yii/web/").getStringRetrofit();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int size=30;
    public void bigger(View v){     // 按钮对应的 onclick 响应
        TextView txv;
        txv= (TextView) findViewById(R.id.txv);  // 根据ID找到对应的text对象
        txv.setTextSize(++size);       // 修改对象的字符大小-size


        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name","张三");
        hashMap.put("age","20");
        RetrofitUtils.post("index.php?r=blog/tabs", hashMap, new StringCallback() {
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
    public void display(View v){     // 另外一个按钮对应的 onclick 响应
        EditText name= (EditText) findViewById(R.id.name);  //还是根据ID找到对象，并进行接下来的操作
        TextView text2= (TextView) findViewById(R.id.txv);

        text2.setText(name.getText().toString());   // 设置字符
    }

    public void goPage(View v){
        Intent intent=new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
}