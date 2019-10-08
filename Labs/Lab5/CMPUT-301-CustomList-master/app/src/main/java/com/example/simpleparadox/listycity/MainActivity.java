package com.example.simpleparadox.listycity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    // Declare the variables so that you will be able to reference it later.
    ListView cityList;
    ArrayAdapter<City> cityAdapter;
    ArrayList<City> cityDataList;

    CustomList customList;

    String TAG = "Sample";
    Button addCityButton;
    EditText addCityEditText;
    EditText addProviceEditText;
    FirebaseFirestore db;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

//        String []cities ={"Edmonton", "Vancouver", "Toronto", "Hamilton", "Denver", "Los Angeles"};
//        String []provinces = {"AB", "BC", "ON", "ON", "CO", "CA"};
//
//
        cityDataList = new ArrayList<>();
//
//        for(int i=0;i<cities.length;i++){
//            cityDataList.add((new City(cities[i], provinces[i])));
//        }

        cityAdapter = new CustomList(this, cityDataList);

        cityList.setAdapter(cityAdapter);

//        dataList = new ArrayList<>();
//        dataList.addAll(Arrays.asList(cities));
//
//        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
//
//        cityList.setAdapter(cityAdapter);

        this.addCityButton = findViewById(R.id.add_city_button);
        this.addCityEditText = findViewById(R.id.add_city_field);
        this.addProviceEditText = findViewById(R.id.add_province_edit_text);

//        FirebaseApp.initializeApp(this);

        db = FirebaseFirestore.getInstance();

        final CollectionReference collectionReference = db.collection("Cities");

        collectionReference.addSnapshotListener(((queryDocumentSnapshots, e) -> {
            cityDataList.clear();

            queryDocumentSnapshots.forEach((doc) -> {
                Log.d(TAG, String.valueOf(doc.getData().get("province_name")));
                String city = doc.getId();
                String province = (String) doc.getData().get("province_name");
                cityDataList.add(new City(city, province));

            });

        cityAdapter.notifyDataSetChanged();
        }));

        addCityButton.setOnClickListener((view) -> {
            final String cityName = this.addCityEditText.getText().toString();
            final String provinceName = this.addProviceEditText.getText().toString();

            HashMap<String, String> data = new HashMap<>();

            if (cityName.length() > 0 && provinceName.length() > 0) {
                data.put("province_name", provinceName);


                collectionReference.document(cityName)
                        .set(data)
                        .addOnSuccessListener((aVoid) -> Log.d(TAG, "Data addition successful"))
                        .addOnFailureListener((e) -> Log.d(TAG, "Data addition failed " + e.toString()));

                addCityEditText.setText("");
                addProviceEditText.setText("");
            }

//            collectionReference.addSnapshotListener(((queryDocumentSnapshots, e) -> {
//                cityDataList.clear();
//
//                queryDocumentSnapshots.forEach((doc) -> {
//                    Log.d(TAG, String.valueOf(doc.getData().get("province_name")));
//                    String city = doc.getId();
//                    String province = (String) doc.getData().get("province_name");
//                    cityDataList.add(new City(city, province));
//
//                });
//
//            }));
//            cityAdapter.notifyDataSetChanged();
        });


        cityList.setOnItemLongClickListener((arg0, view, index, arg3) -> {

            this.db.collection("Cities")
                    .document(cityDataList.get(index).getCityName())
                    .delete();
            cityDataList.remove(index);
            cityAdapter.notifyDataSetChanged();

            return true;
        });
    }


}
