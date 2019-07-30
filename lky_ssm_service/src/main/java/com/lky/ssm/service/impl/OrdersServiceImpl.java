package com.lky.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.lky.ssm.dao.IOrdersDao;
import com.lky.ssm.domain.Orders;
import com.lky.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther likeyu
 * @create 2019-07-26-0:29
 **/
@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page, int size) throws Exception {
        //页码和每页的显示条数，会在查询语句后拼接查询条件，位置必须在执行的分页语句前面
        PageHelper.startPage(page, size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String ordersId) throws Exception {
        return ordersDao.findById(ordersId);
    }

}
