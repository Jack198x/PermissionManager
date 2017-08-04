package cn.jack.permissionmanager;

import android.app.Fragment;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jack on 2017/3/31.
 */

public class PermissionFragment extends Fragment {


    private PermissionListener listener;

    public PermissionFragment() {

    }

    public void setListener(PermissionListener listener) {
        this.listener = listener;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (getActivity() != null) {
            handleRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }


    public void handleRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(listener==null){
            return;
        }
        boolean allPermissionsGranted = true;
        List<String> deniedPermissions = new ArrayList<>();
        if (requestCode == PermissionManager.REQUEST_CODE_PERMISSIONS) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    allPermissionsGranted = false;
                    deniedPermissions.add(permissions[i]);
                }
            }
            if (allPermissionsGranted) {
                listener.onAllPermissionsGranted(Arrays.asList(permissions));
            } else {
                listener.onPermissionsDenied(deniedPermissions);
            }
        }
    }
}
