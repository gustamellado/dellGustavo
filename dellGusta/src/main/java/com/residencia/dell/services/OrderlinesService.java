package com.residencia.dell.services;

import com.residencia.dell.entities.Orderlines;
import com.residencia.dell.repositories.OrderlinesRepository;
import com.residencia.dell.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderlinesService {
    @Autowired
    public OrderlinesRepository orderlinesRepository;

    @Autowired
    public OrdersRepository ordersRepository;

    public Orderlines findById(Integer id){
        return orderlinesRepository.findById(id).get();
    }

    public List<Orderlines> findAll(){
        return orderlinesRepository.findAll();
    }

    public Long count(){
        return orderlinesRepository.count();
    }

    public Orderlines save(Orderlines orderlines){
        Orderlines newOrderlines = orderlinesRepository.save(orderlines);
        return newOrderlines;
    }

    public Orderlines update(Orderlines orderlines){
        return orderlinesRepository.save(orderlines);
    }

    public boolean delete (Integer id) {
        if (id != null) {
            orderlinesRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }

    }

//    public Orderlines findById(Integer orderlineId, Integer orderId){
//        Orders orders = ordersRepository.findById(orderId).get();
//        return orderlinesRepository.findByOrderlineIdAndOrderId(orderlineId,orders);
//    }


}
