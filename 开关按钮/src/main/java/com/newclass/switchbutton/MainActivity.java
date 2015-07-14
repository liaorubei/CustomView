package com.newclass.switchbutton;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //写个中文看看怎么滴

        List<PackageInfo> packages = getPackageManager().getInstalledPackages(PackageManager.GET_SIGNATURES);
        for (PackageInfo aPackage : packages) {
            System.out.println("packageName=" + aPackage.packageName + " versionName=" + aPackage.versionName);
            String dataDir = aPackage.applicationInfo.dataDir;
        }
    }


}
