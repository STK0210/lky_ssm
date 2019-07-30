package com.lky.ssm.dao;

import com.lky.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @auther likeyu
 * @create 2019-07-26-20:59
 **/
public interface ITravellerDao {

    @Select("select * from traveller where id in(select travellerId from order_traveller where orderId = #{ordersId})")
    List<Traveller> findByOrdersId(String ordersId) throws Exception;

}
