package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zj.filters.FiltrateBean;
import com.zj.filters.FlowPopWindow;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView one;
    private FlowPopWindow flowPopWindow;
    private List<FiltrateBean> dictList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initParam();
        initView();
    }

    private void initView() {
        one = findViewById(R.id.one);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flowPopWindow = new FlowPopWindow(MainActivity.this, dictList);
                flowPopWindow.showAsDropDown(one);
                flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClick() {
                        StringBuilder sb = new StringBuilder();
                        for (FiltrateBean fb : dictList) {
                            List<FiltrateBean.Children> cdList = fb.getChildren();
                            for (int x = 0; x < cdList.size(); x++) {
                                FiltrateBean.Children children = cdList.get(x);
                                if (children.isSelected())
                                    sb.append(fb.getTypeName() + ":" + children.getValue() + "；");
                            }
                        }
                        if (!TextUtils.isEmpty(sb.toString()))
                            Toast.makeText(MainActivity.this, "111"+sb.toString(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }

    private void initParam() {
        String[] types = {"合作", "赞助"};
        String[] factorys = {"文体娱乐", "社会实践", "科技赛事", "公益志愿", "不限"};
        String[] places = {"成都市成华区", "成都市双流区", "成都市龙泉驿区", "成都市青白江区", "成都市新都区","成都市温江区","成都市武侯区","成都市锦江区","成都市青羊区","成都市金牛区"};

        FiltrateBean fb1 = new FiltrateBean();
        fb1.setTypeName("类型");
        List<FiltrateBean.Children> childrenList = new ArrayList<>();
        for (String type : types) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(type);
            childrenList.add(cd);
        }
        fb1.setChildren(childrenList);

        FiltrateBean fb2 = new FiltrateBean();
        fb2.setTypeName("性质");
        List<FiltrateBean.Children> childrenList2 = new ArrayList<>();
        for (String factory : factorys) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(factory);
            childrenList2.add(cd);
        }
        fb2.setChildren(childrenList2);

        FiltrateBean fb3 = new FiltrateBean();
        fb3.setTypeName("地点");
        List<FiltrateBean.Children> childrenList3 = new ArrayList<>();
        for (String place : places) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(place);
            childrenList3.add(cd);
        }
        fb3.setChildren(childrenList3);

        dictList.add(fb1);
        dictList.add(fb2);
        dictList.add(fb3);
    }
}
