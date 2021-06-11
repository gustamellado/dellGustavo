package com.residencia.dell.services;

import com.residencia.dell.entities.Customers;
import com.residencia.dell.entities.Orderlines;
import com.residencia.dell.entities.Orders;
import com.residencia.dell.entities.Products;
import com.residencia.dell.repositories.CustomersRepository;
import com.residencia.dell.repositories.OrdersRepository;
import com.residencia.dell.repositories.ProductsRepository;
import com.residencia.dell.vo.ItemOrderlinesVO;
import com.residencia.dell.vo.NotaFiscalVO;
import com.residencia.dell.vo.OrderlinesVO;
import com.residencia.dell.vo.OrdersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersService {

    @Autowired
    public OrdersRepository ordersRepository;

    @Autowired
    public ProductsRepository productsRepository;

    @Autowired
    public CustomersRepository customersRepository;


    public Orders findById(Integer id){
        return ordersRepository.findById(id).get();
    }

//    public List<Orders> findAll(){
//        return ordersRepository.findAll();
//    }

    //COM PAGINACAO
    public List<Orders> findAll(Integer pagina, Integer qtdRegistros) throws Exception{
        Pageable page = null;
        List<Orders> listOrders = null;
        List<Orders> listOrdersComPaginacao = null;
        try{
            if(null != pagina && null != qtdRegistros){
                page = PageRequest.of(pagina,qtdRegistros);
                listOrdersComPaginacao = ordersRepository.findAll(page).getContent();
                return listOrdersComPaginacao;
            } else {
                listOrders = ordersRepository.findAll();
                return listOrders;
            }
        } catch (Exception e){
            throw new Exception("Não foi possivel recuperar a lista de pedidos ::" + e.getMessage());
        }
    }

    //COM VO
    public List<OrdersVO> findAllVO(Integer pagina, Integer qtdRegistros) throws Exception {
        Pageable page = null;
        List<Orders> listOrders = null;
        List<Orders> listOrdersComPaginacao = null;
        List<OrdersVO> listOrdersVO = new ArrayList<>();

        try {
            if (null != pagina && null != qtdRegistros) {

                page = PageRequest.of(pagina, qtdRegistros);
                listOrdersComPaginacao = ordersRepository.findAll(page).getContent();

                for (Orders lOrders : listOrdersComPaginacao) {
                    listOrdersVO.add(convertEntidadeParaVO(lOrders));
                }

            } else {
                listOrders = ordersRepository.findAll();

                for (Orders lOrders : listOrders) {
                    listOrdersVO.add(convertEntidadeParaVO(lOrders));
                }

            }
        } catch (Exception e) {
            throw new Exception("Não foi possível recuperar a lista de pedidos ::" + e.getMessage());
        }

        return listOrdersVO;
    }

    private OrdersVO convertEntidadeParaVO(Orders orders) {
        OrdersVO ordersVO = new OrdersVO();
        List<OrderlinesVO> listOrderLinesVO = new ArrayList<>();

        ordersVO.setNetAmount(orders.getNetAmount());
        ordersVO.setOrderDate(orders.getOrderDate());
        ordersVO.setOrderId(orders.getOrderId());
        ordersVO.setTax(orders.getTax());
        ordersVO.setTotalAmount(orders.getTotalAmount());

        for (Orderlines lOrderLines : orders.getListOrdersLines()) {
            OrderlinesVO orderLinesVO = new OrderlinesVO();

            orderLinesVO.setOrderDate(lOrderLines.getOrderDate());
            orderLinesVO.setOrderLineId(lOrderLines.getOrderLineId());
            orderLinesVO.setProdId(lOrderLines.getProdId());
            orderLinesVO.setQuantity(lOrderLines.getQuantity());

            listOrderLinesVO.add(orderLinesVO);
        }

        ordersVO.setListOrderLinesVO(listOrderLinesVO);

        return ordersVO;
    }

    public Long count(){
        return ordersRepository.count();
    }

    public Orders save(Orders orders){
        Orders newOrders = ordersRepository.save(orders);
        return newOrders;
    }

    public Orders update(Orders orders){
        return ordersRepository.save(orders);
    }

    public boolean delete (Integer id) {
        if (id != null) {
            ordersRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

    public NotaFiscalVO NFfindById(Integer orderId) {
        NotaFiscalVO notaFiscal = new NotaFiscalVO();
        Orders order = ordersRepository.getById(orderId);
        Customers customer = order.getCustomers();

        notaFiscal.setFirstName(customer.getFirstName());
        notaFiscal.setLastName(customer.getLastName());
        notaFiscal.setNetAmount(order.getNetAmount());
        notaFiscal.setTotalAmount(order.getTotalAmount());
        notaFiscal.setOrderDate(order.getOrderDate());
        notaFiscal.setOrderId(orderId);

        List<ItemOrderlinesVO> listnfpVO= new ArrayList<>();
        for(Orderlines orderline : order.getListOrdersLines()) {
            ItemOrderlinesVO nfpVO = converteOrderlineParaNFPVO(orderline);

            listnfpVO.add(nfpVO);
        }
        notaFiscal.setListItemOrderLinesVO(listnfpVO);
        return notaFiscal;
    }


    private ItemOrderlinesVO converteOrderlineParaNFPVO(Orderlines orderline) {
        ItemOrderlinesVO nfpVO = new ItemOrderlinesVO();
        Products product = productsRepository.getById(orderline.getProdId());

        nfpVO.setProdId(orderline.getProdId());
        nfpVO.setQuantity(orderline.getQuantity());
        nfpVO.setTitle(product.getTitle());

        return nfpVO;
    }

//NOOOOOSSSOOOOOO!!!!!!!!
    public OrdersVO saveVO(OrdersVO ordersVO) {
        Orders newOrders = ordersRepository.save(convertVOParaEntidade(ordersVO));
        return convertEntidadeParaVO(newOrders);
    }

   private Orders convertVOParaEntidade(OrdersVO ordersVO) {
        Orders orders = new Orders();
       Customers customer = customersRepository.getById(ordersVO.getCustomerId());

        List<Orderlines> listOrderlines = new ArrayList<>();

        orders.setOrderId(ordersVO.getOrderId());
        orders.setOrderDate(ordersVO.getOrderDate());
        orders.setNetAmount(ordersVO.getNetAmount());
        orders.setTax(ordersVO.getTax());
        orders.setTotalAmount(ordersVO.getTotalAmount());
        orders.setCustomers(customer);


        for (OrderlinesVO lOrderLinesVO : ordersVO.getListOrderLinesVO()) {
            Orderlines orderlines = new Orderlines();

            orderlines.setOrderDate(lOrderLinesVO.getOrderDate());
            orderlines.setOrderLineId(lOrderLinesVO.getOrderLineId());
            orderlines.setProdId(lOrderLinesVO.getProdId());
            orderlines.setQuantity(lOrderLinesVO.getQuantity());

            listOrderlines.add(orderlines);
        }

        orders.setListOrdersLines(listOrderlines);


        return orders;
    }

//    teste*****
//    public Orders saveVO(OrdersVO ordersVO) {
//
//        Orders newOrders = ordersRepository.save(convertVOParaEntidade(ordersVO));
//
//        return newOrders;
//
//    }
//
//    private Orders convertVOParaEntidade(OrdersVO ordersVO) {
//        Orders orders = new Orders();
//
//        Customers customer = customersRepository.getById(ordersVO.getCustomerId());
//
//        orders.setNetAmount(ordersVO.getNetAmount());
//        orders.setOrderDate(ordersVO.getOrderDate());
//        orders.setOrderId(ordersVO.getOrderId());
//        orders.setTax(ordersVO.getTax());
//        orders.setTotalAmount(ordersVO.getTotalAmount());
//        orders.setCustomers(customer);
//
//        List<Orderlines> listOrderLines = new ArrayList<>();
//        for (OrderlinesVO itemOrderLinesVO : ordersVO.getListOrderLinesVO()) {
//            Orderlines orderlines = new Orderlines();
//
//            orderlines.setOrderDate(itemOrderLinesVO.getOrderDate());
//            orderlines.setOrderLineId(itemOrderLinesVO.getOrderLineId());
//            orderlines.setProdId(itemOrderLinesVO.getProdId());
//            orderlines.setQuantity(itemOrderLinesVO.getQuantity());
//
//            listOrderLines.add(orderlines);
//        }
//
//        orders.setListOrdersLines(listOrderLines);
//
//        return orders;
//    }
}