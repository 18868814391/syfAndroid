package com.example.syfapplication3.homePage.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.syfapplication3.R;
import com.example.syfapplication3.databinding.FragmentHomeBinding;
import com.example.syfapplication3.retrofitutils.http.RetrofitUtils;
import com.example.syfapplication3.retrofitutils.http.callback.StringCallback;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.lang.reflect.*;
import java.util.ArrayList;

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
                try {
                    goRequest(v);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void goRequest(View v) throws Exception{
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

                Log.d(TAG, "onSuccess:111 " + s);
                ResultBean resultBean = new Gson().fromJson(s,ResultBean.class);
                Log.d(TAG, "onSuccess:222 " + resultBean);
                Log.d(TAG, "onSuccess:333 " + resultBean.data.js);
                ResultBean.Mdata userBeanObj = resultBean.getData();
                Log.d(TAG, "userBeanList: "+userBeanObj);

                ArrayList<BtnItem> btnList = new ArrayList<>();
                Class<?> objClass = userBeanObj.getClass();
                Field[] fields = objClass.getDeclaredFields();
                for (Field field : fields) {
                    String name = field.getName();//node
                    field.setAccessible(true);
                    Number value = null;//userBeanObj.getNode()
                    try {
                        value = (Number) field.get(userBeanObj);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }

                    Log.d(TAG, "keyAndValue: "+name+value);
                    BtnItem btnObj = new BtnItem();
                    btnObj.setName(name);
                    btnObj.setNum(value);
                    btnList.add(btnObj);
                }
                Log.d(TAG, "btns: "+btnList);
                Log.d(TAG, "btns: "+btnList.size());
                Log.d(TAG, "btns: "+btnList.get(0));
                Log.d(TAG, "btns: "+btnList.get(0).getName());
//
                LinearLayout scrViewButLay = (LinearLayout) v.findViewById(R.id.btnBox);
                Button[] myButton = new Button[4];

                for(int index = 0; index < 4; index++){
//                    Log.i("ForTag", "Inside for loop");
//                    Log.i("ForTag", "button length is "+myButton.length);
                    myButton[index] = new Button(getActivity());
                    myButton[index].setText("Button # ");//null ptr exception error
//                    Log.i("ForTag", "After set text");
                    scrViewButLay.addView(myButton[index]);
//                    Log.i("ForTag", "After adding to view");
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onSuccess: " + t);
            }
        });
    }
    public class ResultBean {
        private int errcode;
        private String errmsg;
        private Mdata data;

        public class Mdata{
            private Number node;
            private Number js;

            public Number getNode() {
                return node;
            }

            public void setNode(Number node) {
                this.node = node;
            }

            public Number getJs() {
                return js;
            }

            public void setJs(Number js) {
                this.js = js;
            }
        }
        public Mdata getData(){
            return data;
        }
    }

    class BtnItem {
        private String  name;
        private Number num;
        public void setName(String name) {
            this.name = name;
        }
        public void setNum(Number num) {
            this.num = num;
        }

        public String getName() {
            return name;
        }

        public Number getNum() {
            return num;
        }
    }

}