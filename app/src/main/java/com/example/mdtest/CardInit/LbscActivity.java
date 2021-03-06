package com.example.mdtest.CardInit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.mdtest.R;

import java.util.ArrayList;
import java.util.List;

public class LbscActivity extends AppCompatActivity {

    public LocationClient mLocationClient;
    private TextView positionText;
    private MapView mapView;
    private BaiduMap baiduMap;
    private boolean isFirstLocate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new LbscActivity.MyLocationListener());
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_lbsc);

        mapView = (MapView) findViewById(R.id.bmapView);
        baiduMap = mapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        positionText = (TextView) findViewById(R.id.position_text_view);

        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(LbscActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(LbscActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(LbscActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(LbscActivity.this, permissions, 1);
        } else {
            requestLocation();
        }
    }


    private void navigateTo(BDLocation location){
        if(isFirstLocate)
        {
            LatLng ll = new LatLng(location.getLatitude(),location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(17f);  //16f??????5?????????17f??????100m???18f??????50m???19f??????150m???20f??????200m
            baiduMap.animateMapStatus(update);
            isFirstLocate = false;
        }
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData = locationBuilder.build();
        baiduMap.setMyLocationData(locationData);
    }

    private void requestLocation(){
        initLocation();
        mLocationClient.start();
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(5000);

        //?????????????????????????????????
        option.setIsNeedAddress(true);

//        //???????????????????????????????????????GPS????????????
//        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
//
        //?????????????????????GPS???????????????????????????GPS???????????????????????????GPS???????????????????????????
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//
//        //??????????????????????????????????????????????????????
//        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);

        mLocationClient.setLocOption(option);
    }

    @Override
    protected void onResume(){
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mLocationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode){
            case 1:
                if(grantResults.length>0){
                    for(int result : grantResults){
                        if(result != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this, "?????????????????????????????????????????????",Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                }else{
                    Toast.makeText(this, "??????????????????", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location){
            if(location.getLocType() == BDLocation.TypeGpsLocation || location.getLocType() == BDLocation.TypeNetWorkLocation)
            {
                navigateTo(location);
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    StringBuilder currentPosition = new StringBuilder();

                    currentPosition.append("??????:").append(location.getLatitude()).append("\n");
                    currentPosition.append("??????:").append(location.getLongitude()).append("\n");
                    currentPosition.append("??????:").append(location.getCountry()).append("\n");
                    currentPosition.append("???:").append(location.getProvince()).append("\n");
                    currentPosition.append("???:").append(location.getCity()).append("\n");
                    currentPosition.append("???:").append(location.getDistrict()).append("\n");
                    currentPosition.append("??????:").append(location.getStreet()).append("\n");

                    currentPosition.append("????????????:");
                    if(location.getLocType() == BDLocation.TypeGpsLocation){
                        currentPosition.append("GPS");
                    }else if(location.getLocType() == BDLocation.TypeNetWorkLocation){
                        currentPosition.append("??????");
                    }
                    positionText.setText(currentPosition);
                }
            });
        }



        public void onConnectHotSpotMessage(String s, int i){

        }
    }

}
