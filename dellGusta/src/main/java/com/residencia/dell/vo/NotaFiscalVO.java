package com.residencia.dell.vo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

public class NotaFiscalVO {

    private Integer orderId;
    private Calendar orderDate;
    private String FirstName;
    private String LastName;
    private BigDecimal netAmount;
    private BigDecimal totalAmount;
    private List<ItemOrderlinesVO> listItemOrderLinesVO;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Calendar getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Calendar orderDate) {
        this.orderDate = orderDate;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<ItemOrderlinesVO> getListItemOrderLinesVO() {
        return listItemOrderLinesVO;
    }

    public void setListItemOrderLinesVO(List<ItemOrderlinesVO> listItemOrderLinesVO) {
        this.listItemOrderLinesVO = listItemOrderLinesVO;
    }
}
