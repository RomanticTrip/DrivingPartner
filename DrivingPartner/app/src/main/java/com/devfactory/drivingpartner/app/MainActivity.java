package com.devfactory.drivingpartner.app;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.devfactory.drivingpartner.R;
import com.devfactory.drivingpartner.app.guidance.GuidanceActivity;
import com.devfactory.drivingpartner.app.main.MainFavoritesFragment;
import com.devfactory.drivingpartner.app.main.MainMapFragment;
import com.devfactory.drivingpartner.app.main.MainPrivateFragment;
import com.devfactory.drivingpartner.app.main.MainThemeFragment;
import com.devfactory.drivingpartner.app.seach.SearchActivity;
import com.devfactory.drivingpartner.app.seach.SearchListItem;
import com.devfactory.drivingpartner.app.seach.SearchUndermeunAdapter;
import com.devfactory.drivingpartner.db.DBHelper;
import com.devfactory.drivingpartner.model.Location;
import com.mingle.sweetpick.SweetSheet;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    ///////////////데이터 베이스//////////////////
    private DBHelper dbHelper;
    private ArrayList<SearchListItem> arrayListSearch;


    //액티비티간의 데이터 전송 박스
    Bundle bundleArrayLocation;

    ///////////////UI wedget//////////////////
    private RelativeLayout mSearchLayout;
    private Button mStartButton;
    private RecyclerView mRecyclerView;
    private RelativeLayout view1;
    private RelativeLayout view2;
    private ImageView imgBtn, imgBtna;
    //----------------------------------------
    private Animation aniShow, aniHide;
    private Boolean open = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 스플레시를 실행한다.
        // 로그인인 페이지 실행도 나중에 여기에 구현할 예정이다.
        //startActivity(new Intent(this, SplashActivity.class));

        initData();
        initUi();

        //Tab Create
        ViewGroup tab = (ViewGroup) findViewById(R.id.tab);
        tab.addView(LayoutInflater.from(this).inflate(R.layout.tab_main, tab, false));

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);

        FragmentPagerItems pages = new FragmentPagerItems(this);
        pages.add(FragmentPagerItem.of("추천목적지", MainPrivateFragment.class, bundleArrayLocation));
        pages.add(FragmentPagerItem.of("테마추천", MainThemeFragment.class, bundleArrayLocation));
        pages.add(FragmentPagerItem.of("추천로드", MainFavoritesFragment.class, bundleArrayLocation));

        FragmentPagerItemAdapter tabAdapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), pages);

        viewPager.setAdapter(tabAdapter);
        viewPagerTab.setViewPager(viewPager);



        //https://developer.android.com/guide/topics/ui/notifiers/notifications.html?hl=ko
        //http://itmir.tistory.com/457
        //http://boxfoxs.tistory.com/266
        NotificationManager nm = (NotificationManager)getSystemService(this.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, SplashActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(this)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.icon_my)
                .setContentTitle("조금 일찍 출발하세요! 도로에 사고가 발생했어요!")
                .setContentText("현재 올림필 대로에서 사고가 발생하였습니다. 혹시 회사에 가신다면 일찍 출발하세요!")
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .setTicker("조금 일찍 출발하세요!");

        Notification notification = builder.build();
        nm.notify(2345, notification);

    }

    /**
     * initData()
     * 메인 엑티비티에서 사용할 Data를 초기화 하는 메소드
     */

    private void initData() {

//        dbHelper = new DBHelper(this);
//        String testJson = dbHelper.getJsonTestData();
//            dbHelper.insertLocations(testJson);
//        arrayListLocation = dbHelper.getLocationTable();

        bundleArrayLocation = new Bundle();
        bundleArrayLocation.putParcelableArrayList("arrayListSearch", arrayListSearch);

    }

    /**
     * initUi()
     * 메인 엑티비티에서 사용할 widget을 초기화 선언하는 메소드
     */
    private void initUi() {

        mSearchLayout = (RelativeLayout) findViewById(R.id.search_layout_main);
        mSearchLayout.setOnClickListener(this);

        mStartButton = (Button) findViewById(R.id.start_button_main);
        mStartButton.setOnClickListener(this);

        view1 = (RelativeLayout) findViewById(R.id.view1_main);
        view2 = (RelativeLayout) findViewById(R.id.view2_main);

        view2.setVisibility(View.GONE);
        aniShow = AnimationUtils.loadAnimation(this, R.anim.under_in);
        aniHide = AnimationUtils.loadAnimation(this, R.anim.under_out);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_search111);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        SearchUndermeunAdapter adapter1 = new SearchUndermeunAdapter(arrayListSearch);
        adapter1.notifyDataSetChanged();
        mRecyclerView.setAdapter(adapter1);

        imgBtn = (ImageView) findViewById(R.id.under_btn_up);
        imgBtn.setOnClickListener(this);

        imgBtna = (ImageView) findViewById(R.id.under_btn_down);
        imgBtna.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId())
        {
            case R.id.search_layout_main:
                intent = new Intent(this, SearchActivity.class);
                intent.putExtra("arrayListSearch", arrayListSearch);
                startActivity(intent);
                break;
            case R.id.start_button_main:

                intent = new Intent(this, GuidanceActivity.class);
                intent.putExtra("arrayListSearch", arrayListSearch);
                startActivity(intent);
                break;
            case R.id.under_btn_up:
                if(!open) {
                    view2.setVisibility(View.VISIBLE);
                    view2.startAnimation(aniShow);
                    open = true;
                }else{
                    view2.startAnimation(aniHide);
                    view2.setVisibility(View.GONE);
                    open = false;
                }break;
            case R.id.under_btn_down:
                if(!open) {
                    view2.setVisibility(View.VISIBLE);
                    view2.startAnimation(aniShow);
                    open = true;
                }else{
                    view2.startAnimation(aniHide);
                    view2.setVisibility(View.GONE);
                    open = false;
                }
        }
    }
}
