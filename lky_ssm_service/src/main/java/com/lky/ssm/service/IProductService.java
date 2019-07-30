package com.lky.ssm.service;

import com.lky.ssm.domain.Product;

import java.util.List;

/**
 * @auther likeyu
 * @create 2019-07-26-0:28
 **/
public interface IProductService {

    List<Product> findAll() throws Exception;

    void save(Product product) throws Exception;
}
