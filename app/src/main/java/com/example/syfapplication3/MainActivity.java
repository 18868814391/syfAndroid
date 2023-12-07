package com.example.syfapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
//import android.graphics.Camera;
import android.hardware.Camera;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syfapplication3.retrofitutils.http.RetrofitUtils;
import com.example.syfapplication3.retrofitutils.http.RetrofitManager;
import com.example.syfapplication3.retrofitutils.http.callback.StringCallback;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private Button QrOpen;
    private Camera camera;
    private Boolean isLightOn = false;
    private final static int REQUEST_CODE = 1001;
    private static final String TAG = "MainActivity";
    private static final String TAG2 = "MainActivity234";
    private static final Retrofit retrofit = RetrofitManager.getInstance().setBaseUrl("https://www.shenyifan.top/apis/php/yii/web/").getStringRetrofit();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        QrOpen = (Button) findViewById(R.id.QrOpen);
        QrOpen.setOnClickListener(QrOpenListener);
    }

    private View.OnClickListener QrOpenListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
            intentIntegrator.setOrientationLocked(false);
            intentIntegrator.initiateScan();
        }
    };
    // 扫完码之后会在onActivityResult方法中回调结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        EditText name= (EditText) findViewById(R.id.name);  //还是根据ID找到对象，并进行接下来的操作
        TextView text2= (TextView) findViewById(R.id.txv);
        if(result != null) {
            Log.d(TAG2, "112233");
            if(result.getContents() == null) {
                Log.d(TAG2, "Cancelled");
                text2.setText("Cancelled");
            } else {
                Log.d(TAG2, "Cancelled"+result.getContents());
                String text=result.getContents();
                //将扫描结果放到textview中
                text2.setText(result.getContents());
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("text", text);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(context, "扫描结果:" + text, Toast.LENGTH_SHORT).show();

            }
        } else {
            Log.d(TAG2, "334455");
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void openFlash(View v){
        CameraManager camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
//        String cameraId = camManager.getCameraIdList()[0]; // Usually front camera is at 0 position.
//        camManager.setTorchMode(cameraId, true);

//        CameraManager cameraManager= (CameraManager) getSystemService(CAMERA_SERVICE);
//        String cameraIdList[]=cameraManager.getCameraIdList();
//
//        String cameraId = null;
//
//        for(int i=0;i<cameraIdList.length;i++){
//            CameraCharacteristics cameraCharacteristics=cameraManager.getCameraCharacteristics(cameraIdList[i]);
//            //可获取闪光灯&&朝向为后置
//            if(cameraCharacteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE)&&
//                    cameraCharacteristics.get(CameraCharacteristics.LENS_FACING)==CameraCharacteristics.LENS_FACING_BACK){
//                cameraId=cameraIdList[i];
//                break;
//            }
//        }
//        cameraManager.setTorchMode(cameraId,true);
    }


    int size=30;
    public void bigger(View v){     // 按钮对应的 onclick 响应
        TextView txv;
        txv= (TextView) findViewById(R.id.txv);  // 根据ID找到对应的text对象
        txv.setTextSize(++size);       // 修改对象的字符大小-size

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