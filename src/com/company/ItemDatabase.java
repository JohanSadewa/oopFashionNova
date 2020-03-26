package com.company;

import java.util.*;

public class ItemDatabase {
    //public item data base adalah class yabg merepresentasikan database ,class ini ounya 3 fungsi

    // storage is iternal repreentation of database using hashmap, key is ID
    private Map<Integer, Item> storage = new HashMap<>();

    public List<Item> allItems(){
        return new ArrayList<Item>(storage.values());
    }

    public boolean isEmpty() { return storage.isEmpty(); }
    public Optional<Item> getItemByID(int id){
        var item = storage.get(id);
        return Optional.ofNullable(item);
    }
    public void reduceItemStock(int itemid, int stock){
        var optItem = getItemByID(itemid);
        if (optItem.isEmpty()) {
            throw new RuntimeException("OOO Item with id not found");
        }
        var item = optItem.get();
        item.stock = item.stock - stock;
    }
    public void addItem(Item item){
        //tambah item,kalo id sudah ada  munculkan error
        var optItem = getItemByID(item.id);
        if (optItem.isPresent()) {
            throw new RuntimeException("OOO Item with id already exist found");
        }

        storage.put(item.id, item);
    }
}
