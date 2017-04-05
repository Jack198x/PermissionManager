package jack.cn.permissionexample;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;

import jack.cn.permissionmanager.PermissionListener;
import jack.cn.permissionmanager.PermissionManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final PermissionManager permissionManager = new PermissionManager(this, listener);

        findViewById(R.id.permissionButtion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permissionManager.requestPermission(Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA);
            }
        });
    }

    PermissionListener listener = new PermissionListener() {
        @Override
        public void onAllPermissionsGranted(List<String> grantedPermissions) {

        }

        @Override
        public void onPermissionsDenied(List<String> deniedPermissions) {


        }
    };

}
