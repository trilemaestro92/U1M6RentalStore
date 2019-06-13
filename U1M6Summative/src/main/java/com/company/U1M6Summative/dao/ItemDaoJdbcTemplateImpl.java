package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ItemDaoJdbcTemplateImpl implements ItemDao {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_ITEM_SQL =
            "insert into item (name, description, daily_rate) values (?, ?, ?)";

    private static final String SELECT_ITEM_SQL =
            "select * from item where item_id = ?";

    private static final String SELECT_ALL_ITEMS_SQL =
            "select * from item";

    private static final String UPDATE_ITEM_SQL =
            "update item set name = ?, description = ?, daily_rate =? where item_id = ?";

    private static final String DELETE_ITEM_SQL =
            "delete from label where label_id = ?";

    @Autowired
    public ItemDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Item addItem(Item item) {

        jdbcTemplate.update(INSERT_ITEM_SQL, item.getName(), item.getDescription(), item.getDaily_rate());

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        item.setItem_id(id);

        return item;
    }

    @Override
    public Item getItem(int id) {

        try {
            return jdbcTemplate.queryForObject(SELECT_ITEM_SQL, this::mapRowToItem, id);
        } catch (EmptyResultDataAccessException e) {
            // if there are no item with this given id, just return null
            return null;
        }
    }

    @Override
    public List<Item> getAllItem() {

        return jdbcTemplate.query(SELECT_ALL_ITEMS_SQL, this::mapRowToItem);
    }

    @Override
    public Item updateItem(Item item) {

        jdbcTemplate.update(UPDATE_ITEM_SQL, item.getName(), item.getDescription(), item.getDaily_rate());

        return item;

    }

    @Override
    public void deleteItem(int id) {

        jdbcTemplate.update(DELETE_ITEM_SQL, id);


    }

    private Item mapRowToItem(ResultSet rs, int rowNum) throws SQLException {
        Item item = new Item();
        item.setItem_id(rs.getInt("item_id"));
        item.setName(rs.getString("name"));
        item.setDescription(rs.getString("description"));
        item.setDaily_rate(rs.getBigDecimal("daily_rate"));

        return item;
    }

}
