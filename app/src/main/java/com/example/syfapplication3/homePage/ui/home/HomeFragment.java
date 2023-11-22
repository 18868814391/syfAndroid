package com.example.syfapplication3.homePage.ui.home;

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

public class HomeFragment extends Fragment {
    Button btnRequest;
    private static final String TAG="homefragment";
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        btnRequest=(Button)view.findViewById(R.id.button4);
        btnRequest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                goRequest(v);
            }
        });

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}