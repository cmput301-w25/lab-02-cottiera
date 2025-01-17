package com.example.listycity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText textInput;
    private Button addButton;
    private ItemAdapter itemAdapter;
    private ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        textInput = findViewById(R.id.text_input);
        addButton = findViewById(R.id.add_button);

        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney",
                "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>(Arrays.asList(cities));

        /* Set up RecyclerView */
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter = new ItemAdapter(dataList, new ItemAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(int position) {
                deleteItem(position);
            }
        });
        recyclerView.setAdapter(itemAdapter);

        addButton.setOnClickListener(v -> addItem());
    }

    private void addItem() {
        String newItem = textInput.getText().toString().trim();
        if (!TextUtils.isEmpty(newItem)) {
            dataList.add(newItem);
            itemAdapter.notifyItemInserted(dataList.size() - 1);
            textInput.setText("");
        }
    }

    private void deleteItem(int position) {
        if (position >= 0 && position < dataList.size()) {
            dataList.remove(position);
            itemAdapter.notifyItemRemoved(position);
        }
    }
}
