package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.model.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ItemDaoTest {

    @Autowired
    protected ItemDao itemDao;

    @Before
    public void setUp() throws Exception {
        // Clean up the test db
        List<Item> itemList = itemDao.getAllItem();
        itemList.stream()
                .forEach(e -> itemDao.deleteItem(e.getItem_id()));

    }

    @After
    public void tearDown() throws Exception {
        List<Item> itemList = itemDao.getAllItem();
        itemList.stream()
                .forEach(e -> itemDao.deleteItem(e.getItem_id()));
    }

    @Test
    public void addGetDeleteItem() {

        Item item = new Item("bike", "rent a motobike", new BigDecimal("4.50"));

        item = itemDao.addItem(item);

        Item item1 = itemDao.getItem(item.getItem_id());

        assertEquals(item1, item);

        itemDao.deleteItem(item.getItem_id());

        item1 = itemDao.getItem(item.getItem_id());

        assertNull(item1);
    }

    @Test
    public void updateItem() {

        Item item = new Item("bike", "rent a motobike", new BigDecimal("4.50"));

        item = itemDao.addItem(item);

        item.setName("Trinkets");
        item.setDescription("Rent trinkets");
        item.setDaily_rate(new BigDecimal(".99"));

        itemDao.updateItem(item);

        Item item1 = itemDao.getItem(item.getItem_id());

        assertEquals(item1, item);
    }

    @Test
    public void getAllItem() {

        Item item = new Item("bike", "rent a motobike", new BigDecimal("4.50"));

        item = itemDao.addItem(item);

        item = new Item("trinkets", "rent a trinket", new BigDecimal(".99"));

        item = itemDao.addItem(item);

        List<Item> itemList = itemDao.getAllItem();
        assertEquals(itemList.size(), 2);


    }

}