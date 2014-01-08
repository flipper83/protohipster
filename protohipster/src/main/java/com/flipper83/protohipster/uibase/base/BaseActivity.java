package com.flipper83.protohipster.uibase.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.flipper83.protohipster.uibase.app.ProtoApplication;

/**
 * This is base Activity, all activities must extends from this one.
 */
public class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(this);
    }

    public void inject(Object object) {
        // Perform injection so that when this call returns all dependencies will be available for use.
        ((ProtoApplication) getApplication()).inject(object);
    }

}
