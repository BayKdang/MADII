package com.mad2.simplelab1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.mad2.simplelab1.data.ItemRepository;
import com.mad2.simplelab1.model.Item;

public class MainActivity extends AppCompatActivity {

    private ItemRepository itemRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemRepository = new ItemRepository();

        TextView itemDisplay = findViewById(R.id.item_display);
        EditText inputId = findViewById(R.id.input_id);
        Button buttonSearch = findViewById(R.id.button_search);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idText = inputId.getText().toString();
                if (idText.isEmpty()) {
                    itemDisplay.setText("Please enter an ID.");
                    return;
                }
                int id = Integer.parseInt(idText);
                Item item = itemRepository.getItemById(id);
                if (item != null) {
                    itemDisplay.setText("ID: " + item.getId() +
                            "\nName: " + item.getName() +
                            "\nDescription: " + item.getDescription());
                } else {
                    itemDisplay.setText("Item not found.");
                }
            }
        });
    }
}