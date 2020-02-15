package com.gcssloop.test.viewsupport;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gcssloop.test.base.BaseActivity;
import com.gcssloop.test.widget.TestPathView;

/**
 * *
 * *Path之基本操作
 * Project_Name:ViewSupport
 *
 * @author zhangxc
 * @date 2020-02-13 15:16
 * *
 */
public class TestPathActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new TestPathView(this));
    }

}
