package com.carrey.sample.view.duang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.carrey.common.view.explosionfield.ExplosionField;
import com.carrey.sample.R;

/**
 * 类描述：
 * 创建人：carrey
 * 创建时间：2016/1/21 14:56
 */

public class ViewBoomActivity extends AppCompatActivity implements View.OnClickListener {
    private ExplosionField mExplosionField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_boom);
        mExplosionField = ExplosionField.attach2Window(this);

        findViewById(R.id.imageView).setOnClickListener(this);
        findViewById(R.id.imageView1).setOnClickListener(this);
        findViewById(R.id.textVew).setOnClickListener(this);
        findViewById(R.id.button).setOnClickListener(this);


    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        mExplosionField.explode(v);
    }
}
