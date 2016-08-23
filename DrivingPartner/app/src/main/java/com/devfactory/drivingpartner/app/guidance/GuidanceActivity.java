package com.devfactory.drivingpartner.app.guidance;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.devfactory.drivingpartner.R;
import com.devfactory.drivingpartner.app.SplashActivity;
import com.devfactory.drivingpartner.app.seach.SearchListItem;
import com.devfactory.drivingpartner.key.ApiKeys;
import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapCompassManager;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapLocationManager;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.maps.overlay.NMapPathData;
import com.nhn.android.maps.overlay.NMapPathLineStyle;
import com.nhn.android.mapviewer.overlay.NMapMyLocationOverlay;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPathDataOverlay;

import java.util.ArrayList;

public class GuidanceActivity extends NMapActivity implements NMapView.OnMapStateChangeListener {

    // 네이버 맵 객체
    NMapView mMapView = null;
    // 맵 컨트롤러
    NMapController mMapController = null;
    // 맵을 추가할 레이아웃
    LinearLayout MapContainer;

    // 오버레이의 리소스를 제공하기 위한 객체
    NMapViewerResourceProvider mMapViewerResourceProvider = null;
    // 오버레이 관리자
    NMapOverlayManager mOverlayManager;
    private NMapMyLocationOverlay mMyLocationOverlay;
    private NMapLocationManager mMapLocationManager;
    private NMapCompassManager mMapCompassManager;

    private RecyclerView mRecyclerView;
    private ArrayList<SearchListItem> arrayList;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidance);

        // 네이버 지도를 넣기 위한 LinearLayout 컴포넌트
        MapContainer = (LinearLayout) findViewById(R.id.MapContainer);

        // 네이버 지도 객체 생성
        mMapView = new NMapView(this);

        // 지도 객체로부터 컨트롤러 추출
        mMapController = mMapView.getMapController();

        // 네이버 지도 객체에 APIKEY 지정
        String API_KEY = ApiKeys.NAVER_MAP_API_KEY;
        mMapView.setApiKey(API_KEY);

        // 생성된 네이버 지도 객체를 LinearLayout에 추가시킨다.
        MapContainer.addView(mMapView);

        // 지도를 터치할 수 있도록 옵션 활성화
        mMapView.setClickable(true);

        // 확대/축소를 위한 줌 컨트롤러 표시 옵션 활성화
        mMapView.setBuiltInZoomControls(true, null);

        // 지도에 대한 상태 변경 이벤트 연결
        mMapView.setOnMapStateChangeListener(this);

        /******************* 오버래이 초기화 ********************/
        // 오버래이 리소스 관리자 생성
        mMapViewerResourceProvider = new NMapViewerResourceProvider(this);

        // 오버래이 관리자 생성. 이 객체에게 맵뷰와 오버래이 리스소 관리자를 연결한다.
        mOverlayManager = new NMapOverlayManager(this, mMapView, mMapViewerResourceProvider);


        /******************* 출발 위치와 도착위치 지정 ********************/
        // set POI data
        NMapPOIdata poiData = new NMapPOIdata(2, mMapViewerResourceProvider);
        poiData.beginPOIdata(2);
        poiData.addPOIitem(127.108099, 37.366034,
                "출발", NMapPOIflagType.FROM, 0);
        poiData.addPOIitem(127.106279, 37.366380,
                "도착", NMapPOIflagType.TO, 0);
        poiData.endPOIdata();

        // create POI data overlay
        mOverlayManager.createPOIdataOverlay(poiData, null);

        //http://itpaper.co.kr/index.php?document_srl=2440&mid=android
        /******************* 이동 경로 지정 ********************/
        // 이동 경로를 추가하기 위한 객체
        NMapPathData pathData = new NMapPathData(9);

        // 이동 경로 표시 시작
        pathData.initPathData();

        // 이동 경로 추가
        pathData.addPathPoint(126.6173, 37.47545,NMapPathLineStyle.TYPE_SOLID);
        pathData.addPathPoint(126.4773, 37.37128, 0);
        pathData.addPathPoint(126.7362, 37.44316, 0);
        pathData.addPathPoint(126.6837, 37.44331, 0);
        pathData.addPathPoint(126.6988, 37.44261, 0);
        pathData.addPathPoint(127.106904, 37.365624, 0);
        pathData.addPathPoint(127.105933, 37.365621, NMapPathLineStyle.TYPE_DASH);
        pathData.addPathPoint(127.105929, 37.366378, 0);
        pathData.addPathPoint(127.106279, 37.366380, 0);

        // 이동 경로 표시 종료
        pathData.endPathData();

        // 지도상에 이동 경로 표시
        NMapPathDataOverlay pathDataOverlay
                = mOverlayManager.createPathDataOverlay(pathData);

        // 지도상의 표시 위치와 축적을 이동 경로를 포함한 화면으로 초기화
        pathDataOverlay.showAllPathData(0);


        Intent intent = getIntent();
        arrayList = intent.getParcelableArrayListExtra("arrayListSearch");

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.frgment_guidance,new GuidanceFragment());
        fragmentTransaction.commit();


        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                maa();
            }
        }, 1000);


    }

    public void maa(){
        NotificationManager nm = (NotificationManager)getSystemService(this.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, SplashActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(this)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.icon_my)
                .setContentTitle("할인 받으세요!")
                .setContentText("CJ ONE 5% 적립을 하고 있습니다.")
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .setTicker("카페베네 양재점에 도착하였습니다.");

        Notification notification = builder.build();
        nm.notify(4444, notification);

        Toast.makeText(this, "카페베네 양재점에 도착하였습니다.",Toast.LENGTH_SHORT);
    }

    ///http://itpaper.co.kr/index.php?document_srl=2400&mid=android
    @Override
    public void onMapInitHandler(NMapView nMapView, NMapError nMapError) {

    }

    @Override
    public void onMapCenterChange(NMapView nMapView, NGeoPoint nGeoPoint) {

    }

    @Override
    public void onMapCenterChangeFine(NMapView nMapView) {

    }

    @Override
    public void onZoomLevelChange(NMapView nMapView, int i) {

    }

    @Override
    public void onAnimationStateChange(NMapView nMapView, int i, int i1) {

    }
}
