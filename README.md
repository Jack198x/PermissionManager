# PermissionManager
runtime permission util
```java
PermissionManager permissionManager = new PermissionManager(this, listener);
permissionManager.requestPermission(Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA);
PermissionListener listener = new PermissionListener() {
        @Override
        public void onAllPermissionsGranted(List<String> grantedPermissions) {

        }

        @Override
        public void onPermissionsDenied(List<String> deniedPermissions) {


        }
    };
```

```groovy
compile 'cn.jack:PermissionManager:1.0.0'
```

