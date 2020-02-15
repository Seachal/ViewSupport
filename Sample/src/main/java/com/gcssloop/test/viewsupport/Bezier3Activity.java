package com.gcssloop.test.viewsupport;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gcssloop.test.base.BaseActivity;
import com.gcssloop.test.widget.Bezier3;

/**
 * *
 * *
 * Project_Name:ViewSupport
 *
 * @author zhangxc
 * @date 2020-02-13 16:14
 * *
 */
public class Bezier3Activity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Bezier3(this));


    }
}
