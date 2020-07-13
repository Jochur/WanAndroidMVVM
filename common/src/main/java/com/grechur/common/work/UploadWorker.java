package com.grechur.common.work;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: UploadWorker
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/7/3 10:58
 */
public class UploadWorker extends Worker {
    private Context context;
    public UploadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {

        return Result.success();
    }

    public void request(){
        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(true)
                .build();
        PeriodicWorkRequest.Builder builder = new PeriodicWorkRequest.Builder(UploadWorker.class, 2, TimeUnit.SECONDS);
        PeriodicWorkRequest workRequest = builder.setConstraints(constraints)
                .setBackoffCriteria(BackoffPolicy.LINEAR,
                    PeriodicWorkRequest.MIN_BACKOFF_MILLIS,
                    TimeUnit.MILLISECONDS)
                .build();
        WorkManager.getInstance(context).enqueue(workRequest);
    }
}
