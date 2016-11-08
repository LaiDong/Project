package com.example.ld.clienttoserver.about;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ld.clienttoserver.R;

/**
 * Created by ld on 27/07/2016.
 */
public class ActivityAbout {
    public static void custom_dialog(Context mContext, String title, String text, int uri){
        final Dialog dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle(title);
        TextView tv = (TextView) dialog.findViewById(R.id.textView);
        tv.setText(text);
        ImageView image = (ImageView)dialog.findViewById(R.id.image);
        image.setImageResource(uri);
        Button button = (Button) dialog.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();
    }
}
