package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.model.Item;

import java.util.List;

public interface ItemDao {

    Item addItem(Item book);

    Item getItem(int id);

    List<Item> getAllItem();

    Item updateItem(Item item);

    void deleteItem(int id);
}
