package com.devfactory.drivingpartner.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.devfactory.drivingpartner.model.Location;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by choiyejin on 16. 8. 22..
 */
public class DBHelper {

    private static final String LOG_TAG = "DBHelper";
    private static ArrayList<String> TABEL_QUERY_List = new ArrayList<>();

    private Context context;
    private SQLiteDatabase database;

    public DBHelper(Context _context) {
        this.context = _context;

        TABEL_QUERY_List.add("Create Table IF not exists LocationTable ( ID integer primary key autoincrement, TourName text, Age integer, Sex integer, Time integer, VitCnt integer, Weather text, Week text, Month integer, Latitude double, Hardness double, Area text, Address text)");

        createDatebase();
    }


    //데이터베이스 생성
    private void createDatebase() {
        //이미 데이터 베이스 테이블이 있다면 실행 하지 않음.
        database = context.openOrCreateDatabase("Test.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        for (int postion = 0; postion<TABEL_QUERY_List.size(); postion++) {
            createTable(TABEL_QUERY_List.get(postion));
        }

    }

    //테이블 생성
    private void createTable(String query) {
        try {
            database.execSQL(query);
        } catch (Exception e){
            Log.e(LOG_TAG, "CREATE TABLE FAILED! - " + e);
            e.printStackTrace();
        }
    }

    public void insertLocation(String _tourName, int _age, int _sex, int _time, int _vitCnt, String _weather, String _week, int _month, double _latitude, double _hardness, String _area, String _address) {
        try {
            String sql = "Insert Into testTable (TourName, Age, Sex, Time, VitCnt, Weather, Week, Month, Latitude, Hardness, Area, Address)"
                         + "Values ('" + _tourName + "','" + _age + "','" + _sex + "','"+_time + "','" +_vitCnt+ "','" +_weather+ "','" +_week+ "','"
                         +_month+ "','" +_latitude+ "','" +_hardness+ "','" +_area+ "','" +_address+ "');" ;

            database.execSQL(sql);
        }  catch (Exception e) {
            Log.e(LOG_TAG, "DB ERROR! - " + e);
            e.printStackTrace();
        }
    }

    public ArrayList<Location> getLocationTable() {
        ArrayList<Location> arrayList = new ArrayList<>();

        String query = "Select * from testTable;";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();

        while (cursor.moveToNext()){
            arrayList.add(new Location(cursor.getString(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7), cursor.getDouble(8), cursor.getDouble(9), cursor.getString(10), cursor.getString(11)));
        }
        cursor.close();
        return arrayList;
    }

    public Location getLocation(String coulnm, String taget) {
        Location location = null;
        String query = null;

        if(coulnm.equalsIgnoreCase("TourName")) {
            query = "Select * from TestTable where TourName = " + taget;
        } else if (coulnm.equalsIgnoreCase("Age")) {
            query = "Select * from TestTable where Age = " + taget;
        } else if (coulnm.equalsIgnoreCase("Sex")) {
            query = "Select * from TestTable where Sex = " + taget;
        } else if (coulnm.equalsIgnoreCase("Time")) {
            query = "Select * from TestTable where Time = " + taget;
        } else if (coulnm.equalsIgnoreCase("VitCnt")) {
            query = "Select * from TestTable where VitCnt = " + taget;
        } else if (coulnm.equalsIgnoreCase("Weather")) {
            query = "Select * from TestTable where Weather = " + taget;
        } else if (coulnm.equalsIgnoreCase("Week")) {
            query = "Select * from TestTable where Week = " + taget;
        } else if (coulnm.equalsIgnoreCase("Month")) {
            query = "Select * from TestTable where Month = " + taget;
        } else if (coulnm.equalsIgnoreCase("Latitude")) {
            query = "Select * from TestTable where Latitude = " + taget;
        } else if (coulnm.equalsIgnoreCase("Hardness")) {
            query = "Select * from TestTable where Hardness = " + taget;
        } else if (coulnm.equalsIgnoreCase("Area")) {
            query = "Select * from TestTable where Area = " + taget;
        } else if (coulnm.equalsIgnoreCase("Address")) {
            query = "Select * from TestTable where Address = " + taget;
        } else {
            Log.e(LOG_TAG, "DB ERROR! - No one input column.");
        }

        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();

        location = new Location(cursor.getString(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7), cursor.getDouble(8), cursor.getDouble(9), cursor.getString(10), cursor.getString(11));

        return location;
    }

    public void insertLocations(String jsonData) {
        String _tourName;
        int _age;
        int _sex;
        int _time;
        int _vitCnt;
        String _weather;
        String _week;
        int _month;
        double _latitude;
        double _hardness;
        String _area;
        String _address;

        try {

            JSONArray jArr = new JSONArray(jsonData);
//            jArr.put(jsonData);

            for (int i = 0; i < jArr.length(); i++) {
                JSONObject jObj = jArr.getJSONObject(i);

                _tourName = jObj.getString("TourName");
                _age = jObj.getInt("Age");
                _sex = jObj.getInt("Sex");
                _time = jObj.getInt("Time");
                _vitCnt = jObj.getInt("VitCnt");
                _weather = jObj.getString("Weather");
                _week = jObj.getString("Week");
                _month = jObj.getInt("Month");
                _latitude = jObj.getDouble("Latitude");
                _hardness = jObj.getDouble("Hardness");
                _area = jObj.getString("Area");
                _address = jObj.getString("Address");


                //DB에 데이터 넣기
                String sql = "INSERT INTO App(Name, CPU, Memory, IO, Request)"
                        + " VALUES('" + _tourName + "', " + _age + ", " + _sex + ", " + _time + ", " + _vitCnt
                        + ", " + _weather + ", " + _week + ", " + _month + ", " + _latitude + ", " + _hardness + ", " + _area + ", " + _address + ");";

                Log.i("TEST", sql);

                try {
                    database.execSQL(sql);
                } catch (Exception e) {
                    Log.e(LOG_TAG, "DB ERROR! - " + e);
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "JSON ERROR!! - " + e);
            e.printStackTrace();
        }
    }

    public String getJsonTestData() {
        StringBuilder sb = new StringBuilder();

        sb.append("[");
        sb.append("      {");
        sb.append("         'TourName':'차이나타운',");
        sb.append("         'Age':'50',");
        sb.append("         'Sex':'1',");
        sb.append("         'Time':'4',");
        sb.append("         'VitCnt':'1982',");
        sb.append("         'Weather':'맑음',");
        sb.append("         'Week':'토',");
        sb.append("         'Month':'3',");
        sb.append("         'Latitude':'37.47545',");
        sb.append("         'Hardness':'126.6173',");
        sb.append("         'Area':'인천',");
        sb.append("         'Address':'인천광역시 중구 북성동 차이나타운로44번길'");
        sb.append("      },");
        sb.append("      {");
        sb.append("         'TourName':'송도',");
        sb.append("         'Age':'30',");
        sb.append("         'Sex':'1',");
        sb.append("         'Time':'5',");
        sb.append("         'VitCnt':'827',");
        sb.append("         'Weather':'맑음',");
        sb.append("         'Week':'목',");
        sb.append("         'Month':'4',");
        sb.append("         'Latitude':'37.37128',");
        sb.append("         'Hardness':'126.4773',");
        sb.append("         'Area':'인천',");
        sb.append("         'Address':'인천광역시연수구송도동'");
        sb.append("      },");
        sb.append("      {");
        sb.append("         'TourName':'인천대공원',");
        sb.append("         'Age':'30',");
        sb.append("         'Sex':'0',");
        sb.append("         'Time':'5',");
        sb.append("         'VitCnt':'18293',");
        sb.append("         'Weather':'맑음',");
        sb.append("         'Week':'일',");
        sb.append("         'Month':'3',");
        sb.append("         'Latitude':'37.44316',");
        sb.append("         'Hardness':'126.7362',");
        sb.append("         'Area':'인천',");
        sb.append("         'Address':'인천광역시 남동구 장수동 무네미로'");
        sb.append("      },");
        sb.append("      {");
        sb.append("         'TourName':'인천 CGV',");
        sb.append("         'Age':'30',");
        sb.append("         'Sex':'0',");
        sb.append("         'Time':'6',");
        sb.append("         'VitCnt':'9027',");
        sb.append("         'Weather':'흐림',");
        sb.append("         'Week':'토',");
        sb.append("         'Month':'8',");
        sb.append("         'Latitude':'37.44331',");
        sb.append("         'Hardness':'126.6837',");
        sb.append("         'Area':'인천',");
        sb.append("         'Address':'인천광역시 남동구 구월1동 예술로 198'");
        sb.append("      },");
        sb.append("      {");
        sb.append("         'TourName':'인천 신세계 백화점',");
        sb.append("         'Age':'40',");
        sb.append("         'Sex':'0',");
        sb.append("         'Time':'7',");
        sb.append("         'VitCnt':'7219',");
        sb.append("         'Weather':'흐림',");
        sb.append("         'Week':'화',");
        sb.append("         'Month':'10',");
        sb.append("         'Latitude':'37.44261',");
        sb.append("         'Hardness':'126.6988',");
        sb.append("         'Area':'인천',");
        sb.append("         'Address':'인천광역시 남구 구월1동 연남로 35'");
        sb.append("      },");
        sb.append("      {");
        sb.append("         'TourName':'인천 동화마을',");
        sb.append("         'Age':'20',");
        sb.append("         'Sex':'0',");
        sb.append("         'Time':'4',");
        sb.append("         'VitCnt':'1782',");
        sb.append("         'Weather':'맑음',");
        sb.append("         'Week':'일',");
        sb.append("         'Month':'9',");
        sb.append("         'Latitude':'37.49514',");
        sb.append("         'Hardness':'126.721',");
        sb.append("         'Area':'인천',");
        sb.append("         'Address':'인천광역시 부평구 부평동 부평대로 206-15'");
        sb.append("      },");
        sb.append("      {");
        sb.append("         'TourName':'인천대교',");
        sb.append("         'Age':'20',");
        sb.append("         'Sex':'1',");
        sb.append("         'Time':'6',");
        sb.append("         'VitCnt':'6281',");
        sb.append("         'Weather':'맑음',");
        sb.append("         'Week':'일',");
        sb.append("         'Month':'8',");
        sb.append("         'Latitude':'37.41366',");
        sb.append("         'Hardness':'126.5643',");
        sb.append("         'Area':'인천',");
        sb.append("         'Address':'인천광역시 중구 항동7가'");
        sb.append("      },");
        sb.append("      {");
        sb.append("         'TourName':'인천공항',");
        sb.append("         'Age':'20',");
        sb.append("         'Sex':'0',");
        sb.append("         'Time':'3',");
        sb.append("         'VitCnt':'48271',");
        sb.append("         'Weather':'맑음',");
        sb.append("         'Week':'일',");
        sb.append("         'Month':'8',");
        sb.append("         'Latitude':'37.4602',");
        sb.append("         'Hardness':'126.4385',");
        sb.append("         'Area':'인천',");
        sb.append("         'Address':'인천광역시 중구 운서동'");
        sb.append("      },");
        sb.append("      {");
        sb.append("         'TourName':'소래포구',");
        sb.append("         'Age':'30',");
        sb.append("         'Sex':'1',");
        sb.append("         'Time':'5',");
        sb.append("         'VitCnt':'28391',");
        sb.append("         'Weather':'맑음',");
        sb.append("         'Week':'토',");
        sb.append("         'Month':'2',");
        sb.append("         'Latitude':'37.39866',");
        sb.append("         'Hardness':'126.7386',");
        sb.append("         'Area':'인천',");
        sb.append("         'Address':'인천광역시 남동구 논현동 서해안로 111'");
        sb.append("      },");
        sb.append("      {");
        sb.append("         'TourName':'오이도',");
        sb.append("         'Age':'20',");
        sb.append("         'Sex':'0',");
        sb.append("         'Time':'5',");
        sb.append("         'VitCnt':'10823',");
        sb.append("         'Weather':'맑음',");
        sb.append("         'Week':'일',");
        sb.append("         'Month':'2',");
        sb.append("         'Latitude':'37.39878',");
        sb.append("         'Hardness':'126.6707',");
        sb.append("         'Area':'시흥',");
        sb.append("         'Address':'경기도 시흥시 정왕동 오이도로 170'");
        sb.append("      },");
        sb.append("      {");
        sb.append("         'TourName':'월곷',");
        sb.append("         'Age':'40',");
        sb.append("         'Sex':'0',");
        sb.append("         'Time':'7',");
        sb.append("         'VitCnt':'12932',");
        sb.append("         'Weather':'맑음',");
        sb.append("         'Week':'토',");
        sb.append("         'Month':'1',");
        sb.append("         'Latitude':'37.39182',");
        sb.append("         'Hardness':'126.7405',");
        sb.append("         'Area':'시흥',");
        sb.append("         'Address':'경기도시흥시군자동서해안로'");
        sb.append("      },");
        sb.append("      {");
        sb.append("         'TourName':'인천 고요남',");
        sb.append("         'Age':'20',");
        sb.append("         'Sex':'1',");
        sb.append("         'Time':'5',");
        sb.append("         'VitCnt':'328',");
        sb.append("         'Weather':'비',");
        sb.append("         'Week':'일',");
        sb.append("         'Month':'4',");
        sb.append("         'Latitude':'37.54326',");
        sb.append("         'Hardness':'126.7263',");
        sb.append("         'Area':'인천',");
        sb.append("         'Address':'인천 계양구 계양4동'");
        sb.append("      },");
        sb.append("      {");
        sb.append("         'TourName':'화룽',");
        sb.append("         'Age':'20',");
        sb.append("         'Sex':'1',");
        sb.append("         'Time':'4',");
        sb.append("         'VitCnt':'7291',");
        sb.append("         'Weather':'비',");
        sb.append("         'Week':'화',");
        sb.append("         'Month':'1',");
        sb.append("         'Latitude':'37.45348',");
        sb.append("         'Hardness':'126.7006',");
        sb.append("         'Area':'인천',");
        sb.append("         'Address':'인천광역시 남동구 구월1동 구월남로'");
        sb.append("      }");
        sb.append("]");

        return sb.toString();
    }
}
