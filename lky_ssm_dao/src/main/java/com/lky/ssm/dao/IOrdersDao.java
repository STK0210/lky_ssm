package com.lky.ssm.dao;

import com.lky.ssm.domain.Member;
import com.lky.ssm.domain.Orders;
import com.lky.ssm.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @auther likeyu
 * @create 2019-07-26-0:26
 **/
public interface IOrdersDao {

    @Select("select * from orders")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "com.lky.ssm.dao.IProductDao.findById"))
    })
    List<Orders> findAll() throws Exception;

    @Select("select * from orders where id = #{ordersId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "com.lky.ssm.dao.IProductDao.findById")),
            @Result(property = "member", column = "memberId",javaType = Member.class,one = @One(select = "com.lky.ssm.dao.IMemberDao.findById")),
            @Result(property = "travellers" ,column = "id",javaType = java.util.List.class,many = @Many(select = "com.lky.ssm.dao.ITravellerDao.findByOrdersId"))
    })
    Orders findById(String ordersId) throws Exception;
}
