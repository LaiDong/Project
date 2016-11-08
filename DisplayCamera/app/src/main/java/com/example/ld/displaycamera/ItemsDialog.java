package com.example.ld.displaycamera;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Created by ld on 11/3/16.
 */

public class ItemsDialog {
    public static ItemsDialog instance;
    Context mContext;

    ItemsDialog(Context mContext){
        this.mContext = mContext;
    }
    public static ItemsDialog getInstance(Context mContext){
        if(instance==null){
            instance = new ItemsDialog(mContext);
        }
        return instance;
    }

    AlertDialog alertDialog;
    public void CreateAlertDialogWithRadioButtonGroup(String tittle, final CharSequence[] values){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(tittle);
        builder.setSingleChoiceItems(values, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                switch (item){
                    case 0:
                    Toast.makeText(mContext, values[0], Toast.LENGTH_LONG).show();
                    break;
                    case 1:

                        Toast.makeText(mContext, values[1], Toast.LENGTH_LONG).show();
                        break;
                    case 2:

                        Toast.makeText(mContext, values[2], Toast.LENGTH_LONG).show();
                        break;
                    case 3:

                        Toast.makeText(mContext, values[3], Toast.LENGTH_LONG).show();
                        break;
                    case 4:

                        Toast.makeText(mContext, values[4], Toast.LENGTH_LONG).show();
                        break;
                    case 5:

                        Toast.makeText(mContext, values[5], Toast.LENGTH_LONG).show();
                        break;
                    case 6:

                        Toast.makeText(mContext, values[6], Toast.LENGTH_LONG).show();
                        break;
                    case 7:

                        Toast.makeText(mContext, values[7], Toast.LENGTH_LONG).show();
                        break;
                    case 8:

                        Toast.makeText(mContext, values[8], Toast.LENGTH_LONG).show();
                        break;
                }
                alertDialog.dismiss();
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }
}
