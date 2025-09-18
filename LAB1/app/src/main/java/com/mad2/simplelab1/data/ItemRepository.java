package com.mad2.simplelab1.data;

import com.mad2.simplelab1.model.Item;
import java.util.ArrayList;
import java.util.List;

public class ItemRepository {
    private List<Item> items;

    public ItemRepository() {
        items = new ArrayList<>();
        items.add(new Item(1, "Apple", "A juicy fruit"));
        items.add(new Item(2, "Banana", "A yellow fruit"));
        items.add(new Item(3, "Carrot", "A crunchy vegetable"));
        items.add(new Item(4, "Dragonfruit", "Exotic and sweet"));
        items.add(new Item(5, "Eggplant", "A purple vegetable"));
        items.add(new Item(6, "Fig", "Sweet and chewy fruit"));
        items.add(new Item(7, "Grape", "Small round juicy fruit"));
        items.add(new Item(8, "Honeydew", "Green-fleshed melon"));
        items.add(new Item(9, "Kiwi", "Brown fuzzy fruit with green flesh"));
        items.add(new Item(10, "Lemon", "Sour yellow citrus fruit"));
        // Add more as needed
    }

    public Item getItemById(int id) {
        for (Item item : items) {
            if (item.getId() == id) return item;
        }
        return null;
    }
}