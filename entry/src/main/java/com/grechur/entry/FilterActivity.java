package com.grechur.entry;

import android.Manifest;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Operation;
import androidx.work.WorkContinuation;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.bumptech.glide.Glide;
import com.google.common.util.concurrent.ListenableFuture;
import com.grechur.common.util.toast.ToastUtils;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: FilterActivity
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/7/3 11:40
 */
public class FilterActivity extends AppCompatActivity {
    String imageUri;
    ImageView filter_image;
    String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_activity_filter);
        if (getIntent() != null) {
            imageUri = getIntent().getStringExtra("KEY_IMAGE_URI");
            Log.e("FilterActivity","imageUri:"+imageUri);
        }
        filter_image = findViewById(R.id.filter_image);
        Glide.with(this)
                .load(imageUri)
                .into(filter_image);
        LiveData<List<WorkInfo>> output = WorkManager.getInstance(this).getWorkInfosByTagLiveData("OUTPUT");
        output.observe(this, new Observer<List<WorkInfo>>() {
            @Override
            public void onChanged(List<WorkInfo> workInfos) {
                if(workInfos!=null&&!workInfos.isEmpty()) {
                    WorkInfo workInfo = workInfos.get(0);
                    Data outputData = workInfo.getOutputData();
                    String outputDataString = outputData.getString("KEY_IMAGE_URI");
                    Log.e("FilterActivity","imageUri:"+outputDataString);
                    if(!TextUtils.isEmpty(outputDataString)) {
                        Glide.with(FilterActivity.this)
                                .load(outputDataString)
                                .into(filter_image);
                    }
                }
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions,1001);
        }
    }
    int grant = 1;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1001){
            for (int grantResult : grantResults) {
                grant &= grantResult;
            }

        }
    }

    public void onBtnClick(View view) {
        if(grant == 0) {

            OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(BaseFilterWorker.class)
                    .setInputData(createInputData()).build();
            OneTimeWorkRequest saveRequest = new OneTimeWorkRequest.Builder(SaveImageToGalleryWorker.class)
                    .setInputData(createInputData())
                    .addTag("OUTPUT").build();
            WorkManager.getInstance(this)
                    .beginUniqueWork("image_manipulation_work",
                            ExistingWorkPolicy.REPLACE,
                            OneTimeWorkRequest.from(CleanupWorker.class))
                    .then(workRequest)
                    .then(saveRequest)
                    .enqueue();
        }
    }

    public Data createInputData(){
        Data data = new Data.Builder()
                .putString("KEY_IMAGE_URI", imageUri)
                .build();
        return data;
    }
}
