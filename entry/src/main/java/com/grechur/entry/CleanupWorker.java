package com.grechur.entry;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.io.File;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: CleanupWorker
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/7/3 13:55
 */
public class CleanupWorker extends Worker {

    private static final String TAG = CleanupWorker.class.getSimpleName();

    public CleanupWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            File outputs = new File(getApplicationContext().getFilesDir(), "filter_outputs");
            if (outputs.exists()) {
                File[] files = outputs.listFiles();
                if (files != null && files.length > 0) {
                    for (File file : files) {
                        String name = file.getName();
                        if (!TextUtils.isEmpty(name) && name.endsWith(".png")) {
                            boolean delete = file.delete();
                            Log.i(TAG, String.format("Deleted %s - %s", name, delete));
                        }
                    }
                }
            }
            return Result.success();
        } catch (Exception e) {
            return Result.failure();
        }
    }
}
