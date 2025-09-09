package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.AlertDialog;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<City> cityAdapter;
    ArrayList<City> cityData;
    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        View root = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list);

        City []cities = {
            new City("Edmonton"),
            new City("Vancouver"),
            new City("Philadelphia"),
            new City("Hanoi"),
            new City("Tokyo"),
            new City("Beijing"),
            new City("Calgary"),
            new City("London"),
            new City("Paris"),
            new City("Toronto"),
            new City("New York"),
        };

        cityData = new ArrayList<>();
        cityData.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, cityData);
        cityList.setAdapter(cityAdapter);
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            cityList.setItemChecked(position, true);
        });

        Button addBtn = findViewById(R.id.add_city_button);
        Button delBtn = findViewById(R.id.delete_city_button);

        addBtn.setOnClickListener(v -> showAddDialog());
        delBtn.setOnClickListener(v -> {
            if (selectedPosition >= 0) {
                cityData.remove(selectedPosition);
                cityAdapter.notifyDataSetChanged();
                cityList.clearChoices();
                selectedPosition = -1;
            }
        });
    }

    private void showAddDialog() {
        EditText input = new EditText(this);
        input.setHint("City name");
        new AlertDialog.Builder(this)
                .setTitle("Add City")
                .setView(input)
                .setPositiveButton("CONFIRM", (d, w) -> {
                    String name = input.getText().toString().trim();
                    if (!name.isEmpty()) {
                        cityData.add(new City(name));
                        cityAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("CANCEL", null)
                .show();
    }
}