package com.example.android_theme_demo.manager;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.android_theme_demo.listener.ILoadListener;
import com.example.android_theme_demo.listener.ISkinUpdate;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zt on 2016/7/2.
 */
public final class SkinManager {
    private static final String TAG = SkinManager.class.getSimpleName();
    private static final Object mClock = new Object();
    private static SkinManager mInstance;

    private Context mContext;
    private Resources mResources;
    private String mSkinPkgName;
    private String mSkinApkPath;
    private List<ISkinUpdate> mObservers;
    private SkinManager(){

    }
    public static SkinManager getInstance(){
        if (mInstance == null){
            synchronized (mClock){
                if (mInstance == null){
                    mInstance = new SkinManager();
                }
            }
        }
        return mInstance;
    }
    public void init(Context context){
        enableContext(context);
        mContext = context.getApplicationContext();
    }
    public void loadSkin(){
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            String skinPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + File.separator + "theme" + File.separator + "test.apk";
            loadSkin(skinPath);
        } else {
            Toast.makeText(mContext,"没有外部存储卡",Toast.LENGTH_SHORT).show();
        }
    }
    public void loadSkin(String skinPath){
        loadSkin(skinPath,null);
    }

    public void loadSkin(final String skinPath, final ILoadListener listener){
        enableContext(mContext);
        if (TextUtils.isEmpty(skinPath)|| skinPath.equals(mSkinApkPath)){
            return ;
        }
        new AsyncTask<String,Void,Resources>(){

            @Override
            protected void onPreExecute() {
                if (null != listener){
                    listener.onStart();
                }
            }

            @Override
            protected Resources doInBackground(String... params) {
                if ( null != params && params.length == 1) {
                    String skinPath = params[0];
                    File file = new File(skinPath);
                    if (null != file && file.exists()) {
                        PackageManager packageManager = mContext.getPackageManager();
                        PackageInfo packageInfo = packageManager.getPackageArchiveInfo(skinPath,PackageManager.GET_ACTIVITIES);
                        if (null!= packageInfo){
                            mSkinPkgName = packageInfo.packageName;
                        }
                        return getResources(mContext,skinPath);
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Resources result) {
				super.onPostExecute(result);
                if (null != result){
					Log.i(TAG, "load skin success");
					mSkinApkPath = skinPath;
                    mResources = result;
					notifySkinUpdate();
                    if (null!= listener) {
                        listener.onSuccess();
                    }
                } else {
					Log.i(TAG, "load skin failure");
                    if ( null != listener) {
                        listener.onFailure();
                    }
                }
            }
        }.execute(skinPath);
    }

    public Resources getResources(Context context, String apkPath){
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getDeclaredMethod("addAssetPath",String.class);
            addAssetPath.setAccessible(true);
            addAssetPath.invoke(assetManager,apkPath);

            Resources r = context.getResources();
            Resources skinResources = new Resources(assetManager,r.getDisplayMetrics(),r.getConfiguration());
            return skinResources;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public void restoreDefaultSkin(){
        if (null != mResources) {
            mResources = null;
            mSkinPkgName = null;
			mSkinApkPath = null;
			notifySkinUpdate();
        }
    }
    public int getColor(int id){
        enableContext(mContext);
        Resources originResources = mContext.getResources();
        int originColor = originResources.getColor(id);
        if (null == mResources || TextUtils.isEmpty(mSkinPkgName)){
            return originColor;
        }
        try {
            String entryName = mResources.getResourceEntryName(id);
            int resourceId = mResources.getIdentifier(entryName,"color",mSkinPkgName);
            return mResources.getColor(resourceId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return originColor;
    }
    public Drawable getDrawable(int id){
        enableContext(mContext);
        Resources originResources = mContext.getResources();
        Drawable originDrawable = originResources.getDrawable(id);
        if(null == mResources || TextUtils.isEmpty(mSkinPkgName)) {
            return originDrawable;
        }
        try {
            String entryName = mResources.getResourceEntryName(id);
            int resourceId = mResources.getIdentifier(entryName, "drawable", mSkinPkgName);
            return mResources.getDrawable(resourceId);
        } catch (Exception e) {
        }
        return originDrawable;
    }
    public void onAttach(ISkinUpdate observer){
        if (null == observer) return;
        if (null == mObservers) {
            mObservers = new ArrayList<>();
        }
        if (!mObservers.contains(observer)){
            mObservers.add(observer);
        }
    }
    public void onDettach(ISkinUpdate observer){
        if (null == observer || null == mObservers) return;
        mObservers.remove(observer);
        observer = null;
    }
    public void notifySkinUpdate(){
        if ( null != mObservers){
            for (ISkinUpdate observer : mObservers){
                observer.updateSkin();
            }
        }
    }
    private void enableContext(Context context){
        if ( null == context){
            throw new NullPointerException();
        }
    }
    public boolean isExternalSkin() {
        return null == mResources ? false : true;
    }
}
