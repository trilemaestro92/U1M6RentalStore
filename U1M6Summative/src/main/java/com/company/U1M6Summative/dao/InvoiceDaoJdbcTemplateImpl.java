package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceDaoJdbcTemplateImpl implements InvoiceDao {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_INVOICE_SQL =
            "insert into invoice (customer_id, order_date, pickup_date, return_date, late_fee) values (?, ?, ?, ?, ?)";

    private static final String SELECT_INVOICE_BY_CUSTOMER_SQL =
            "select * from invoice where customer_id = ?";

    private static final String DELETE_INVOICE_SQL =
            "delete from invoice where invoice_id = ?";

    private static final String SELECT_ALL_INVOICES_SQL =
            "select * from invoice";

    private static final String SELECT_INVOICE_BY_ID_SQL =
            "select * from invoice where invoice_id = ?";

    @Autowired
    public InvoiceDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Invoice addInvoice(Invoice invoice) {
        jdbcTemplate.update(INSERT_INVOICE_SQL,
                invoice.getCustomer_id(),
                invoice.getOrder_date(),
                invoice.getPickup_date(),
                invoice.getReturn_date(),
                invoice.getLate_fee());

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        invoice.setInvoice_id(id);
        return invoice;
    }

    @Override
    public void deleteInvoice(int id) {
        jdbcTemplate.update(DELETE_INVOICE_SQL, id);
//        return jdbcTemplate.queryForObject(SELECT_INVOICE_BY_ID_SQL, this::mapRowtoInvoice, id);
    }

    @Override
    public Invoice findInvoiceByCustomer(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_INVOICE_BY_CUSTOMER_SQL, this::mapRowtoInvoice, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return jdbcTemplate.query(SELECT_ALL_INVOICES_SQL, this::mapRowtoInvoice);
    }

    @Override
    public Invoice findInvoiceById(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_INVOICE_BY_ID_SQL, this::mapRowtoInvoice, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private Invoice mapRowtoInvoice(ResultSet rs, int rowNum) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setInvoice_id(rs.getInt("invoice_id"));
        invoice.setCustomer_id(rs.getInt("customer_id"));
        invoice.setOrder_date(rs.getDate("order_date").toLocalDate());
        invoice.setPickup_date(rs.getDate("pickup_date").toLocalDate());
        invoice.setReturn_date(rs.getDate("return_date").toLocalDate());
        invoice.setLate_fee(rs.getBigDecimal("late_fee"));

        return invoice;
    }
}
