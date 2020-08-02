package com.grechur.entry;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: BaseFilterWorker
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/7/3 13:58
 */
public class BaseFilterWorker extends Worker {
    public static final String ASSET_PREFIX = "file:///android_asset/";
    public static final String OUTPUT_PATH = "filter_outputs";
    public BaseFilterWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    public InputStream inputStreamFor(Context context,String resourceUri) throws Exception {
        if(resourceUri.startsWith(ASSET_PREFIX)){
            AssetManager manager = context.getResources().getAssets();
            return manager.open(resourceUri.substring(ASSET_PREFIX.length()));
        }else{
            ContentResolver contentResolver = context.getContentResolver();
            return contentResolver.openInputStream(Uri.parse(resourceUri));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @NonNull
    @Override
    public Result doWork() {
        String imageUri = getInputData().getString("KEY_IMAGE_URI");
        try {
            if(TextUtils.isEmpty(imageUri)){
                throw new IllegalArgumentException("Invalid input uri");
            }
            Context context = getApplicationContext();
            Log.e("BaseFilterWorker",imageUri);
            InputStream inputStream = inputStreamFor(context, imageUri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            Bitmap out = applyFilter(bitmap);
            Uri uri = writeBitmapToFile(context, out);
            Data data = new Data.Builder()
                    .putString("KEY_IMAGE_URI", uri.toString())
                    .build();
            Log.e("BaseFilterWorker",uri.toString());
            return Result.success(data);
        }catch (Exception e){
            return Result.failure();
        }
    }

    private Uri writeBitmapToFile(Context context, Bitmap bitmap) {
        String format = String.format("filter-output-%s.png", UUID.randomUUID().toString());
        File file = new File(context.getFilesDir(), OUTPUT_PATH);
        if(!file.exists()){
            file.mkdir();
        }
        File outFile = new File(file, format);
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(outFile);
            bitmap.compress(Bitmap.CompressFormat.PNG,0,outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Uri.fromFile(outFile);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    Bitmap applyFilter(Bitmap bitmap){
        RenderScript rsContext = null;
        try {
            Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
            rsContext = RenderScript.create(getApplicationContext(),RenderScript.ContextType.DEBUG);
            Allocation inAlloc = Allocation.createFromBitmap(rsContext, bitmap);
            Allocation outAlloc = Allocation.createTyped(rsContext, inAlloc.getType());
            ScriptC_waterColorEffect oilFilterEffect = new ScriptC_waterColorEffect(rsContext);
            oilFilterEffect.set_script(oilFilterEffect);
            oilFilterEffect.set_width(bitmap.getWidth());
            oilFilterEffect.set_height(bitmap.getHeight());
            oilFilterEffect.set_in(inAlloc);
            oilFilterEffect.set_out(outAlloc);
            oilFilterEffect.invoke_filter();
            outAlloc.copyTo(output);
            return output;
        }catch (Exception e){
            Log.e("BaseFilterWorker",e.getMessage());
        }finally {
            rsContext.finish();
        }
        return null;
    }
}
