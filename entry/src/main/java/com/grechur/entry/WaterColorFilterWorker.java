package com.grechur.entry;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptC;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.work.WorkerParameters;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: WaterColorFilterWorker
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/7/3 13:58
 */
public class WaterColorFilterWorker extends BaseFilterWorker{
    public WaterColorFilterWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    Bitmap applyFilter(Bitmap bitmap) {
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
        }finally {
            rsContext.finish();
        }
        return null;
    }
}
