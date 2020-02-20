package com.gardenlab.samplepermission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
        };

        checkPermissions(permissions);

    }

    public void checkPermissions(String[] permissions) {
        ArrayList<String> targetList = new ArrayList<String>();

        for(int i=0; i<permissions.length; i++) {
            Log.d("targets",":"+i);
            String curPermission = permissions[i];
            int permissionCheck = ContextCompat.checkSelfPermission(this,curPermission);
            if(permissionCheck == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, curPermission+"권한 있음.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, curPermission+"권한 없음", Toast.LENGTH_LONG).show();
//                if(ActivityCompat.shouldShowRequestPermissionRationale(this,curPermission)) {
//                    // 사용자가 이전에 권한 거부하여 권한 획득 이유 설명하기 위함. (권한 재요청)
//                    // 다시묻지 않기 선택시 이 메서드 상관없이 거부할 때랑 동일한 콜백 함수 실행
//                    Toast.makeText(this,curPermission+"권한 설명 필요", Toast.LENGTH_SHORT).show();
//                } else {
//                    targetList.add(curPermission);
//                }
                targetList.add(curPermission);
            }
        }
        String[] targets = new String[targetList.size()];
        targetList.toArray(targets);

        ActivityCompat.requestPermissions(this,targets,101);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case 101: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "첫번째 권한을 사용자가 승인함", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "첫번째 권한 거부됨", Toast.LENGTH_SHORT).show();
                }

                if(grantResults.length > 0 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "두번째 권한을 사용자가 승인함", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "두번째 권한 거부됨", Toast.LENGTH_SHORT).show();
                }

                return;
            }
        }
    }
}
