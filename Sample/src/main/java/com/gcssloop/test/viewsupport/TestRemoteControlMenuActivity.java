package com.gcssloop.test.viewsupport;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gcssloop.test.base.BaseActivity;
import com.gcssloop.viewsupporttest.R;

/**
 * *
 * *
 * Project_Name:ViewSupport
 *
 * @author zhangxc
 * @date 2019-09-14 17:27
 * *
 */
public class TestRemoteControlMenuActivity extends BaseActivity {

    public static final String TAG = "TestRemoteControlMenuActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_remote_control_menu);
    }
}
