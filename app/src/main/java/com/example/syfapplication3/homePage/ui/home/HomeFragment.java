package com.example.syfapplication3.homePage.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.syfapplication3.R;
import com.example.syfapplication3.databinding.FragmentHomeBinding;
import com.example.syfapplication3.classFun.MyRequest;
import com.example.syfapplication3.retrofitutils.http.RetrofitUtils;
import com.example.syfapplication3.retrofitutils.http.callback.StringCallback;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class HomeFragment extends Fragment {
    private Activity mActivity;
    Button btnRequest;
    private static final String TAG="homefragment";
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mActivity = getActivity();//在界面创建时，定义父Activity
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // fragment_test1 布局中的一个测试按钮
        Button test_button= (Button) view.findViewById(R.id.button4);
//        test_button.setOnClickListener(this);//设置按钮监听事件
        test_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                goRequest(v);
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void goRequest(View v){
        JsonObject userObject = new JsonObject();
        JsonArray addressArray = new JsonArray();
        addressArray.add("node");
        addressArray.add("js");
        userObject.add("tab", addressArray);
        String jsonStr = userObject.toString();

        Gson gson = new Gson();
        String body = "{\"tab\": \"[node,js]\"}";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonStr);
        RetrofitUtils.post("index.php?r=blog/tabs", requestBody, new StringCallback() {
            @Override
            public void onSuccess(String s) {
                Log.d(TAG, "onSuccess:111 " + s);
                
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onSuccess: " + t);
            }
        });
    }
}