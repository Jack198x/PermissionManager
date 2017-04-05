package jack.cn.permissionexample;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.jack.permissionmanager.PermissionListener;
import cn.jack.permissionmanager.PermissionManager;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;


public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<Protocol> protocols = new ArrayList<>();
        protocols.add(Protocol.HTTP_1_1);
        protocols.add(Protocol.HTTP_2);
        OkHttpClient client = new OkHttpClient.Builder()
                .protocols(protocols)
                .build();
        final Picasso picasso = new Picasso.Builder(this)
                .downloader(new OkHttp3Downloader(client))
                .build();


        imageView = (ImageView) findViewById(R.id.imageView);


        final PermissionManager permissionManager = new PermissionManager(this, listener);

        findViewById(R.id.permissionButtion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                picasso.load("http://omqw2x0jj.bkt.clouddn.com/93E27260A48D4E25963169C9F504EF071491378197610avatar.jpg").into(imageView);

//                permissionManager.requestPermission(Manifest.permission.READ_PHONE_STATE,
//                        Manifest.permission.READ_EXTERNAL_STORAGE,
//                        Manifest.permission.CAMERA);
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
