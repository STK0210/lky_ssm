package com.lky.ssm.service.impl;

import com.lky.ssm.dao.IProductDao;
import com.lky.ssm.domain.Product;
import com.lky.ssm.service.IProductService;
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
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> findAll() throws Exception {
        List<Product> products = productDao.findAll();
        System.out.println("开始执行service！");
        for (Product product : products) {
            System.out.println(product);
        }
        return products;
    }

    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }
}
