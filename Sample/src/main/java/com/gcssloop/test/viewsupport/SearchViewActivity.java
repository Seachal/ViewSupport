package com.gcssloop.test.viewsupport;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gcssloop.test.base.BaseActivity;
import com.gcssloop.test.widget.SearchView;

/**
 * *
 * *
 * Project_Name:ViewSupport
 *
 * @author zhangxc
 * @date 2020-02-13 17:03
 * *
 */
public class SearchViewActivity  extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SearchView(this));
    }
}
