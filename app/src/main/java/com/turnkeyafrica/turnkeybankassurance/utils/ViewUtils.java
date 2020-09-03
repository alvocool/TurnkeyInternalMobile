package com.turnkeyafrica.turnkeybankassurance.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.turnkeyafrica.turnkeybankassurance.R;

import androidx.core.content.ContextCompat;


public final class ViewUtils {

    private ViewUtils() {
    }

    public static void changeIconDrawableToGray(Context context, Drawable drawable) {
        if (drawable != null) {
            drawable.mutate();
            drawable.setColorFilter(ContextCompat.getColor(context, R.color.gray), PorterDuff.Mode.SRC_ATOP);
        }
    }

    public static int dpToPx(float dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    public static float pxToDp(float px) {
        float densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi;
        return px / (densityDpi / 160f);
    }

    public static AlertDialog showDialog(Context context,String title, String message, String positiveLabel, DialogInterface.OnClickListener onClickListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setMessage(message);
        builder.setPositiveButton(positiveLabel,onClickListener);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        return alertDialog;
    }

    public static AlertDialog uploadingDialog(Context context, Activity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setCancelable(false);
        LayoutInflater inflater = activity.getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_upload, null));

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        return alertDialog;
    }

    public static AlertDialog isLoadingDialog(Context context, Activity activity, String infoText) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        LayoutInflater inflater = activity.getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_progress, null));

        AlertDialog alertDialog = builder.create();

        alertDialog.show();

        TextView alertTextView =  alertDialog.findViewById(R.id.progressBarText);

        if(!CommonUtils.StringIsEmpty(infoText)) {
            alertTextView.setText(infoText);
        }

        return alertDialog;
    }


}
