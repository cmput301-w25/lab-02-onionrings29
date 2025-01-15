package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // required
        super.onCreate(savedInstanceState);
        //required - sets the content view
        setContentView(R.layout.activity_main);

        //optional - to take out whole page
        EdgeToEdge.enable(this);

        //optional
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list);
        Button button = findViewById(R.id.buttonAddCity);
        TextInputEditText textInputEditText = findViewById(R.id.textInputEditText);


        String[] cities = {"Edmonton", "Paris", "London"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities)); //converting primitive arrays to collection

        //adapter links visual elements to array list data type.
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        //removes city from the list
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            // Remove the clicked city from the list
            String removedCity = dataList.remove(position);

            // Notify the adapter that the data has changed
            cityAdapter.notifyDataSetChanged();

            // Show a toast confirming the removal
            Toast.makeText(MainActivity.this, "Removed: " + removedCity, Toast.LENGTH_SHORT).show();
        });

        //test
        // add city to the list
        button.setOnClickListener(v -> {
            String newCity = textInputEditText.getText() != null ? textInputEditText.getText().toString() : "";
            if (!newCity.isEmpty()) {
                dataList.add(newCity);
                cityAdapter.notifyDataSetChanged();
            }
        });


    }
}