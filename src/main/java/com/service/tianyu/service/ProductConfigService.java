package com.service.tianyu.service;

import com.service.tianyu.biz.ProductConfigBiz;
import com.service.tianyu.po.OperatorType;
import com.service.tianyu.po.ProductConfigPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by intron on 2017/1/15 0015.
 */
@Service
public class ProductConfigService {

    @Autowired
    private ProductConfigBiz productConfigBiz;

    public ProductConfigPo getProductConfig(String productId, String channelId, int operator, int mm) {
        ProductConfigPo productConfig;
        switch (OperatorType.values()[operator]) {
            case YD:
                productConfig = productConfigBiz.getProductConfigYD(productId, channelId, OperatorType.YD, mm);
                break;
            case DX:
                productConfig = productConfigBiz.getProductConfigDXLT(productId, channelId, OperatorType.DX);
                break;
            case LT:
                productConfig = productConfigBiz.getProductConfigDXLT(productId, channelId, OperatorType.LT);
                break;
            default:
                productConfig = null;
        }
        return productConfig;
    }
}
