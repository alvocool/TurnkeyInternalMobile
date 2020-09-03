package com.turnkeyafrica.turnkeybankassurance.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;

public final class ImageUtils {

    public static Bitmap decodeBase64(String base64Str) throws IllegalArgumentException
    {
        byte[] decodedBytes = Base64.decode(
                base64Str.substring(base64Str.indexOf(",")  + 1),
                Base64.NO_WRAP
        );

        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    public static byte[] encodeToBytes(Bitmap bitmap)
    {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }catch(OutOfMemoryError outOfMemoryError){
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }

    public static File generateImageDir(String appName, Context context){

            File path= new File(context.getFilesDir(), appName + File.separator + "Images");
            if(!path.exists()){
                if(path.mkdirs()){
                   return  path;
                }else {
                    return null;
                }
            }
        return path;
    }

    public static String encodeTobase64(Bitmap bitmap)
    {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] b = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(b, Base64.NO_WRAP);
        }catch(OutOfMemoryError outOfMemoryError){

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
            byte[] b = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(b, Base64.NO_WRAP);
        }
    }

    public static void chooseSinglePhoto(Activity activity, int code) {
        Intent gallery_intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        activity.startActivityForResult(gallery_intent, code);
    }

    public static void chooseMultiplePhoto(Activity activity, int code) {
        Intent gallery_intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        activity.startActivityForResult(gallery_intent, code);
    }
}
