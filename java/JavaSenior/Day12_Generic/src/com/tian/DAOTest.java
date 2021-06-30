package com.tian;

import org.junit.jupiter.api.Test;

import java.util.List;

public class DAOTest {
    @Test
    public void test1(){
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.add(new Customer());
        List<Customer> forList = customerDAO.getForList(10);

    }
}
