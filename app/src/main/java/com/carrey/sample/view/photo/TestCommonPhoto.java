package com.carrey.sample.view.photo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.carrey.common.view.photopick.PhotoManager;
import com.carrey.common.view.photopick.PhotoModel;
import com.carrey.common.view.photopick.PhotoPicker;
import com.carrey.sample.R;

import org.xutils.common.util.LogUtil;

import java.util.List;

/**
 * 类描述：
 * 创建人：carrey
 * 创建时间：2016/1/21 13:58
 */

public class TestCommonPhoto extends AppCompatActivity implements View.OnClickListener {

    private PhotoPicker photoPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_photo);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        photoPicker = new PhotoPicker(this);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        photoPicker.dealResult(requestCode, resultCode, data, new PhotoManager.OnLocalRecentListener() {
            @Override
            public void onPhotoLoaded(List<PhotoModel> photos) {
                LogUtil.d(photos.toString());
            }
        });
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                single();
                break;
            case R.id.btn2:
                multiple();
                break;
            case R.id.btn3:
                cut();
                break;
            case R.id.btn4:

                break;
        }
    }

    private void cut() {
        photoPicker.setNeedCrop(true);
        photoPicker.pickPhoto();
    }

    private void multiple() {
        photoPicker.setNeedCrop(false);
        photoPicker.setMaxCount(10);
        photoPicker.pickPhoto();

    }

    private void single() {
        photoPicker.setNeedCrop(false);
        photoPicker.setMaxCount(1);
        photoPicker.pickPhoto();
    }
}
