package com.mad2.simplelab1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.mad2.simplelab1.data.ItemRepository;
import com.mad2.simplelab1.model.Item;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

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

        EditText inputFakeId = findViewById(R.id.input_fake_id);
        Button buttonFakeSearch = findViewById(R.id.button_fake_search);
        TextView fakeApiDisplay = findViewById(R.id.fake_api_display);

        RequestQueue queue = Volley.newRequestQueue(this);

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
                }}
        });

        buttonFakeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idText = inputFakeId.getText().toString();
                if(idText.isEmpty()) {
                    fakeApiDisplay.setText("Please enter a user ID.");
                    return;
                }
                int userId = Integer.parseInt(idText);

                // Example using ReqRes: https://reqres.in/api/users/{id}
                String url = "https://reqres.in/api/users/" + userId;

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        response -> {
                            try {
                                JSONObject obj = new JSONObject(response);
                                JSONObject user = obj.getJSONObject("data");
                                String name = user.getString("first_name") + " " + user.getString("last_name");
                                String email = user.getString("email");
                                String avatar = user.getString("avatar");
                                fakeApiDisplay.setText("Name: " + name
                                        + "\nEmail: " + email
                                        + "\nAvatar: " + avatar);
                            } catch (Exception e) {
                                fakeApiDisplay.setText("Error parsing response.");
                            }
                        },
                        error -> fakeApiDisplay.setText("User not found or network error.")
                );
                queue.add(stringRequest);
            }
        });
    }
}