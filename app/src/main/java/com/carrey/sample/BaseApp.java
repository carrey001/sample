package com.carrey.sample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.carrey.common.CommonApp;
import com.carrey.common.util.BaseConstant;
import com.carrey.common.util.FileUtil;
import com.carrey.sample.util.Constant;

import org.xutils.x;

import java.io.File;

/**
 * 类描述：
 * 创建人：carrey
 * 创建时间：2016/1/20 14:46
 */

public class BaseApp extends CommonApp {

    public static final String APP_SIGN = "1111";
    public String mCurrentAppVersion;//当前应用版本
    public boolean mIsFinishDownload;
    public boolean mIsDownloding;
    public boolean mFirstShowUpdateDialog;
    private IntentFilter mFilter;
    private BroadcastReceiver mBatInfoReceiver;

    @Override
    protected void onInit() {
            checkApp();
        x.Ext.init(this);
        x.Ext.setDebug(true); // 是否输出debug日志
    }

    @Override
    protected void onStart() {
        //监听屏幕变暗
        mFilter=new IntentFilter();
        // 屏幕灭屏广播
        mFilter.addAction(Intent.ACTION_SCREEN_OFF);
        // 屏幕亮屏广播
        mFilter.addAction(Intent.ACTION_SCREEN_ON);
        // 屏幕解锁广播
        mFilter.addAction(Intent.ACTION_USER_PRESENT);
        mBatInfoReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, final Intent intent) {
                String action = intent.getAction();

//                if (Intent.ACTION_SCREEN_OFF.equals(action)) {//屏幕变暗
//                    Settings.archive_gesture_checked = false;
//                    Settings.sNeedGestureCheckAgain = true;
//                }
            }
        };
        registerReceiver(mBatInfoReceiver, mFilter);

        mFirstShowUpdateDialog=true;
    }

    @Override
    protected void onStop() {
        //上传错误日志
        uploadErrorLog();
        mFirstShowUpdateDialog=false;

        if (mBatInfoReceiver != null) {
            unregisterReceiver(mBatInfoReceiver);
        }
    }


    /**
     * 设置项目的初始log路径
     *
     * @return 路径
     */
    @Override
    protected String getLogPath() {
        return Constant.LOG_PATH;
    }
    /**
     * 处理错误日志
     */
    protected void uploadErrorLog() {

        File file = new File(BaseConstant.LOG_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }

        File[] files = file.listFiles();

        if(files != null && files.length > 0){
            for (int i =0; i < files.length; i++){
                final File f = files[i];
                if (f != null && f.isFile() && !f.getName().contains("_upload")){
                    String errorLog = FileUtil.readStr(f.getPath());
                    String[] splites = errorLog.split("\n");
                    String log = splites[0];
                    String trace = errorLog.substring(log.length()+1);
//                    new SettingManager().uploadErrorLog(log, trace, new ResponseCallback() {
//                        @Override
//                        public void onSuccess(ResponseInfo responseInfo) {
//                            super.onSuccess(responseInfo);
//                            File newFile = new File(BaseConstant.LOG_PATH+"/"+f.getName()+"_upload");
//                            f.renameTo(newFile);
//                        }
//
//                        @Override
//                        public boolean isShowNotice() {
//                            return false;
//                        }
//                    });
//                    break;
                }
            }
        }
    }

    // 如果签名信息改变，不让进入app
    private void checkApp() {
//        String sign = SystemUtil.getMD5Signature(this);
//        if (!APP_SIGN.equals(sign)) {
//            System.exit(-1);
//        }
    }
}
