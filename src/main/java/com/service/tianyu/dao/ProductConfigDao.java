package com.service.tianyu.dao;

import com.service.tianyu.po.ProductConfigPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author liunanji
 * date 2018/8/5
 */
@Repository
public interface ProductConfigDao extends JpaRepository<ProductConfigPo, Long> {
    ProductConfigPo findFirstByProductIdAndChannelIdAndOperatorAndMm(String productId, String channelId, int operator, int mm);

    ProductConfigPo findFirstByProductIdAndChannelIdAndOperator(String productId, String channelId, int operator);
}
