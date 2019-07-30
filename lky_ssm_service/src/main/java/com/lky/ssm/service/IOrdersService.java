package com.lky.ssm.service;

import com.lky.ssm.domain.Orders;

import java.util.List;

/**
 * @auther likeyu
 * @create 2019-07-26-17:46
 **/
public interface IOrdersService {

    List<Orders> findAll(int page,int size) throws Exception;

    Orders findById(String ordersId) throws Exception;
}
