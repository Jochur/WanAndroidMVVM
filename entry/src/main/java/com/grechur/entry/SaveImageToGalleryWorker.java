package com.grechur.entry;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: SaveImageToGalleryWorker
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/7/3 15:30
 */
public class SaveImageToGalleryWorker extends Worker {
    private final static String TITLE = "Filtered Image";
    private SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss z", Locale.getDefault());
    public SaveImageToGalleryWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        ContentResolver resolver = getApplicationContext().getContentResolver();
        String resourceUri = getInputData().getString("KEY_IMAGE_URI");
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(
                    resolver.openInputStream(Uri.parse(resourceUri)));
            String imageUrl = MediaStore.Images.Media.insertImage(resolver, bitmap, TITLE, DATE_FORMATTER.format(new Date()));
            if (TextUtils.isEmpty(imageUrl)) {
                return Result.failure();
            }
            Data data = new Data.Builder()
                    .putString("KEY_IMAGE_URI", imageUrl)
                    .build();
            return Result.success(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
