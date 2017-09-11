package cn.jack.permissionmanager;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2016/1/15.
 */
public class PermissionManager {

    static final int REQUEST_CODE_PERMISSIONS = 0x4321;
    private static final String TAG = "PermissionFragment";
    private Activity activity;
    private PermissionListener listener;
    private PermissionFragment fragment;
    private List<String> needRequestPermissions = new ArrayList<>();

    private PermissionManager(@NonNull Activity activity) {
        this.activity = activity;
        needRequestPermissions.clear();
        fragment = new PermissionFragment();
        FragmentManager fragmentManager = activity.getFragmentManager();
        fragmentManager
                .beginTransaction()
                .add(fragment, TAG)
                .commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
    }


    public static PermissionManager with(@NonNull Activity activity) {
        return new PermissionManager(activity);
    }

    public PermissionManager setListener(@NonNull PermissionListener listener) {
        this.listener = listener;
        fragment.setListener(listener);
        return this;
    }

    public PermissionManager permissions(@NonNull String... permissions) {
        addPermissions(permissions);
        return this;
    }

    public PermissionManager permissionGroups(@NonNull String[]... permissionGroups) {
        addPermissionGroups(permissionGroups);
        return this;
    }

    public void request() {
        requestPermissions();
    }


    private void requestPermissions() {
        if (isMarshmallow()) {
            int size = needRequestPermissions.size();
            if (size > 0) {
                String[] strings = needRequestPermissions.toArray(new String[size]);
                fragment.requestPermissions(strings, REQUEST_CODE_PERMISSIONS);
            } else {
                listener.onAllPermissionsGranted(needRequestPermissions);
            }
        } else {
            listener.onAllPermissionsGranted(needRequestPermissions);
        }
    }


    private void addPermissionGroups(@NonNull String[]... permissionGroups) {
        for (String[] permissions : permissionGroups) {
            addPermissions(permissions);
        }
    }


    private void addPermissions(@NonNull String... permissions) {
        for (String p : permissions) {
            if (ContextCompat.checkSelfPermission(activity, p) == PackageManager.PERMISSION_DENIED && !needRequestPermissions.contains(p)) {
                needRequestPermissions.add(p);
            }
        }
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


    private boolean isMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

}
