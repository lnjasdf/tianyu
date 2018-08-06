package com.service.tianyu.biz;

import com.service.tianyu.dao.ProductConfigDao;
import com.service.tianyu.po.OperatorType;
import com.service.tianyu.po.ProductConfigPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by intron on 2017/1/15 0015.
 */
@Component
public class ProductConfigBiz {

    @Autowired
    private ProductConfigDao productConfigDao;

    public ProductConfigPo getProductConfigYD(String productId, String channelId, OperatorType operator, int mm) {
        return productConfigDao.findFirstByProductIdAndChannelIdAndOperatorAndMm(productId, channelId, operator.ordinal(), mm);
    }

    public ProductConfigPo getProductConfigDXLT(String productId, String channelId, OperatorType operator) {
        return productConfigDao.findFirstByProductIdAndChannelIdAndOperator(productId, channelId, operator.ordinal());
    }
}
