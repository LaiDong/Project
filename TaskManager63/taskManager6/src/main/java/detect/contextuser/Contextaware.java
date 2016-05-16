package detect.contextuser;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import com.hust.common.ProcessUtils;
import com.hust.model.MainListItem;

import java.util.ArrayList;
import java.util.List;

import main.hut.ShellUtils;

/**
 * Created by Lai Dong on 5/16/2016.
 */
public class Contextaware {
    static private ActivityManager activityManager;
    static PackageManager manager;
    static private ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
    public static void autoFreeze(Context context){
        List<MainListItem> items = new ArrayList<MainListItem>();
        ProcessUtils.getInstance(context).getAllList(items);
        activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(memoryInfo);
        manager = context.getPackageManager();
        //list app he thong
        List<String> appSystem = new ArrayList<>();
        List<PackageInfo> list = manager.getInstalledPackages(0);
        for(PackageInfo pi : list) {
            try {
                ApplicationInfo ai = manager.getApplicationInfo(pi.packageName, 0);
                if ((ai.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                    appSystem.add(pi.packageName);
                }
            } catch (PackageManager.NameNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        for(MainListItem item : items){
            if(!item.processName.equals("com.hust.activity")&&!appSystem.contains(item.processName)) {
                if ((item.importance == 300 || item.importance == 400) && memoryInfo.availMem / 1024 < 1000000) {
                    if (ShellUtils.runCmd("am force-stop " + item.processName)) {
                        Toast.makeText(context, "Hibernated " + item.label, Toast.LENGTH_SHORT)
                                .show();
                    }
                }
            }
        }
    }
}
