package jack.cn.permissionexample;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.util.List;

import cn.jack.permissionmanager.PermissionGroup;
import cn.jack.permissionmanager.PermissionListener;
import cn.jack.permissionmanager.PermissionManager;


public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        Log.e("onCreate","onCreate");
        PermissionManager.with(this)
                .permissions(Manifest.permission.READ_PHONE_STATE,Manifest.permission.CAMERA)
                .permissionGroups(PermissionGroup.STORAGE,PermissionGroup.CAMERA)
                .setListener(listener)
                .request();

//        final PermissionManager permissionManager = new PermissionManager(this, listener);
//
//        permissionManager.requestPermission(Manifest.permission.READ_PHONE_STATE,
//                Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.CAMERA);
//
//        findViewById(R.id.permissionButtion).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                permissionManager.requestPermission(Manifest.permission.READ_PHONE_STATE,
//                        Manifest.permission.READ_EXTERNAL_STORAGE,
//                        Manifest.permission.CAMERA);
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("onStart","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("onResume","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("onPause","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("onStop","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("onDestroy","onDestroy");
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
