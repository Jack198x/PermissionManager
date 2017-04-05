package cn.jack.permissionmanager;

import java.util.List;

/**
 * Created by Jack on 2017/3/31.
 */
public interface PermissionListener {
    void onAllPermissionsGranted(List<String> grantedPermissions);

    void onPermissionsDenied(List<String> deniedPermissions);
}
