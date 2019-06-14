package com.company.U1M6Summative.service;

import com.company.U1M6Summative.dao.CustomerDao;
import com.company.U1M6Summative.dao.InvoiceDao;
import com.company.U1M6Summative.dao.InvoiceItemDao;
import com.company.U1M6Summative.dao.ItemDao;
import com.company.U1M6Summative.model.Customer;
import com.company.U1M6Summative.model.Invoice;
import com.company.U1M6Summative.model.InvoiceItem;
import com.company.U1M6Summative.model.Item;
import com.company.U1M6Summative.viewModel.InvoiceItemViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    //Invoice Model
    @Transactional
    public InvoiceItemViewModel saveInvoiceItem(InvoiceItemViewModel invoiceItemViewModel) {
        InvoiceItem i = new InvoiceItem();
        i.setInvoiceId(invoiceItemViewModel.getInvoice().getInvoice_id());
        i.setItemId(invoiceItemViewModel.getItem().getItem_id());
        i.setQuantity(invoiceItemViewModel.getQuantity());
        i.setUnitRate(invoiceItemViewModel.getUnitRate());
        i.setDiscount(invoiceItemViewModel.getDiscount());
        i = invoiceItemDao.addInvoiceItem(i);
        invoiceItemViewModel.setInvoiceItemId(i.getInvoiceItemId());

        return invoiceItemViewModel;
    }
//    @Transactional
//    public InvoiceItemViewModel saveInvoiceItem(InvoiceItemViewModel ivm) {
//        InvoiceItem iItem = new InvoiceItem();
//        iItem.setInvoiceId(ivm.getInvoice().getInvoice_id());
//        iItem.setItemId(ivm.getItem().getItem_id());
//        iItem.setQuantity(ivm.getQuantity());
//        iItem.setUnitRate(ivm.getUnitRate());
//        iItem.setDiscount(ivm.getDiscount());
//        iItem = invoiceItemDao.addInvoiceItem(iItem);
//
//        ivm.setInvoiceItemId(iItem.getInvoiceItemId());
//
//        List<InvoiceItem> invoiceItems = ivm.getItemsList();
//        invoiceItems.stream()
//                .forEach(invoiceItem -> {
//                    invoiceItem.setItemId(ivm.getInvoiceItemId());
//                    invoiceItemDao.addInvoiceItem(invoiceItem);
//                });
//        invoiceItems =invoiceItemDao.getInvoiceItem(ivm.getInvoiceItemId());
//        return ivm;
//    }



    @Transactional
    public InvoiceItemViewModel findInvoiceItem(int id) {
        InvoiceItem invoiceItem = invoiceItemDao.getInvoiceItem(id);
        return buildInvoiceItemViewModel(invoiceItem);
    }
    //Invoice
    public Invoice saveInvoice(Invoice invoice){
        return invoiceDao.addInvoice(invoice);
    }



    //Item

    public Item saveItem(Item item) {
        return itemDao.addItem(item);
    }
    public Item findItem(int id) {
        return itemDao.getItem(id);
    }
    public List<Item> findAllItems(){
        return itemDao.getAllItem();
    }
    public Item updateItem(Item item) {
        return itemDao.updateItem(item);
    }
    public int removeItem(int id) {
        // -1 if failed
        // 0 if item with item id does not exist
        // 1 if delete is successful
        int returnVal = -1;
        Item beforeDelete = itemDao.getItem(id);
        if(beforeDelete == null){
            returnVal = 0;
        }else{
            try{
                itemDao.deleteItem(id);
                return 1;
            }catch(Exception ex){
                System.out.println("delete error");
                return -1;
            }
        }
        return returnVal;
    }

    private InvoiceItemViewModel buildInvoiceItemViewModel(InvoiceItem invoiceItem) {

        Invoice invoice = invoiceDao.findInvoiceById(invoiceItem.getInvoiceId());
        Item item = itemDao.getItem(invoiceItem.getItemId());

        InvoiceItemViewModel ivm = new InvoiceItemViewModel();
        ivm.setInvoiceItemId(invoiceItem.getInvoiceItemId());
        ivm.setInvoice(invoice);
        ivm.setItem(item);
        ivm.setQuantity(invoiceItem.getQuantity());
        ivm.setUnitRate(invoiceItem.getUnitRate());
        ivm.setDiscount(invoiceItem.getDiscount());
        return ivm;

    }

}
