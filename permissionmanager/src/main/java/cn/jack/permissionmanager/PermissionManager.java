package cn.jack.permissionmanager;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jack on 2016/1/15.
 */
public class PermissionManager {

    public static final int REQUEST_CODE_PERMISSIONS = 0x4321;
    private static final String TAG = "PermissionFragment";
    private Activity activity;
    private PermissionListener listener;
    private PermissionFragment fragment;


    public PermissionManager(@NonNull Activity activity, @NonNull PermissionListener listener) {
        this.activity = activity;
        this.listener = listener;
        fragment = new PermissionFragment();
        FragmentManager fragmentManager = activity.getFragmentManager();
        fragmentManager
                .beginTransaction()
                .add(fragment, TAG)
                .commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
    }


    /**
     * requestPermission
     *
     * @param permissions
     */
    public void requestPermission(@NonNull String... permissions) {
        if (isMarshmallow()) {
            List<String> needRequestPermissions = new ArrayList<>();
            for (String p : permissions) {
                if (ContextCompat.checkSelfPermission(activity, p) == PackageManager.PERMISSION_DENIED) {
                    needRequestPermissions.add(p);
                }
            }
            int size = needRequestPermissions.size();
            if (size > 0) {
                String[] strings = needRequestPermissions.toArray(new String[size]);
                fragment.requestPermissions(strings, REQUEST_CODE_PERMISSIONS);
            } else {
                listener.onAllPermissionsGranted(Arrays.asList(permissions));
            }
        } else {
            listener.onAllPermissionsGranted(Arrays.asList(permissions));
        }
    }


    private boolean isMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }


    /**
     * checkPermission
     *
     * @param context
     * @param permission
     * @return
     */
    public static boolean checkPermission(Context context, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
        } else {
            return true;
        }
    }

}
