package com.company.U1M6Summative.controller;

import com.company.U1M6Summative.model.Customer;
import com.company.U1M6Summative.model.Item;
import com.company.U1M6Summative.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class RentalStoreController {
    @Autowired
    ServiceLayer service;

//    @RequestMapping(value = "/customer/{cutomer_id}", method = RequestMethod.PUT)
//    @ResponseStatus(value = HttpStatus.OK)
//    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable int  customer_id){
//      customer.setCustomer_id(customer_id);
//      return service.u
//    }
@RequestMapping(value = "/item", method = RequestMethod.GET)
@ResponseStatus(value = HttpStatus.OK)
public List<Item> updateItem(){
    return service.findAllItems();
}
    @RequestMapping(value = "/item", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Item createItem(@RequestBody Item item){
        return service.saveItem(item);
    }
    @RequestMapping(value = "/item/{item_id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public Item updateItem(@PathVariable int item_id, @RequestBody Item item){
    item.setItem_id(item_id);
        return  service.updateItem(item);
    }
    @RequestMapping(value = "/item/{item_id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable int item_id){
        service.removeItem(item_id);
    }
    
}
