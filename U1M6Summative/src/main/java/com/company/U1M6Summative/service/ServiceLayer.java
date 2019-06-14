package com.company.U1M6Summative.service;

import com.company.U1M6Summative.dao.CustomerDao;
import com.company.U1M6Summative.dao.InvoiceDao;
import com.company.U1M6Summative.dao.InvoiceItemDao;
import com.company.U1M6Summative.dao.ItemDao;
import com.company.U1M6Summative.model.Invoice;
import com.company.U1M6Summative.model.InvoiceItem;
import com.company.U1M6Summative.model.Item;
import com.company.U1M6Summative.viewModel.InvoiceItemViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ServiceLayer {

    private CustomerDao customerDao;
    private InvoiceDao invoiceDao;
    private InvoiceItemDao invoiceItemDao;
    private ItemDao itemDao;

    @Autowired
    public ServiceLayer(CustomerDao customerDao, InvoiceDao invoiceDao, InvoiceItemDao invoiceItemDao, ItemDao itemDao) {
        this.customerDao = customerDao;
        this.invoiceDao = invoiceDao;
        this.invoiceItemDao = invoiceItemDao;
        this.itemDao = itemDao;
    }

    @Transactional
    public InvoiceItemViewModel saveInvoiceItem(InvoiceItemViewModel ivm) {
        InvoiceItem iItem = new InvoiceItem();
        iItem.setInvoiceId(ivm.getInvoice().getInvoice_id());
        iItem.setItemId(ivm.getItem().getItem_id());
        iItem.setQuantity(ivm.getQuantity());
        iItem.setUnitRate(ivm.getUnitRate());
        iItem.setDiscount(ivm.getDiscount());
        iItem = invoiceItemDao.addInvoiceItem(iItem);

        ivm.setInvoiceItemId(iItem.getInvoiceItemId());

        List<InvoiceItem> invoiceItems = ivm.getItemsList();

        invoiceItems.stream()
                .forEach(invoiceItem -> {
                    invoiceItem.setItemId(ivm.getInvoiceItemId());
                    invoiceItemDao.addInvoiceItem(invoiceItem);
                        });
//        invoiceItems =invoiceItemDao.getInvoiceItem(ivm.getInvoiceItemId());


        return ivm;
    }

public Invoice saveInvoice(Invoice invoice){
        return invoiceDao.addInvoice(invoice);
}
public Item saveItem(Item item){
        return itemDao.addItem(item);
}
public  InvoiceItemViewModel findInvoice(int id){
        InvoiceItem invoice = invoiceItemDao.getInvoiceItem(id);
        return buildInvoiceItemViewModel(invoice);

}
public InvoiceItemViewModel buildInvoiceItemViewModel ( InvoiceItem invoiceItem){
        InvoiceItem invoiceItem1 = invoiceItemDao.getInvoiceItem(invoiceItem.getInvoiceItemId());


        InvoiceItemViewModel avm =new InvoiceItemViewModel();
        avm.setInvoiceItemId(invoiceItem1.getInvoiceItemId());
        avm.setDiscount(new BigDecimal("2.23"));
        avm.setQuantity(23);
        avm.setUnitRate(new BigDecimal("12.12"));
        avm.setInvoiceItemId(14);
        return avm;
}
}
