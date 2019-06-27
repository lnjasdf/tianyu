package com.service.tianyu.controller;

import com.google.common.collect.Maps;
import com.service.tianyu.po.AndroidInfoBo;
import com.service.tianyu.po.IpInfoPo;
import com.service.tianyu.po.ProductConfigPo;
import com.service.tianyu.po.ResultBo;
import com.service.tianyu.service.ProductConfigService;
import com.service.tianyu.utils.NetworkUtils;
import com.service.tianyu.utils.qqzeng.ip.IpSearch;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by intron on 2017/1/15 0015.
 */
@RequestMapping("product")
@Controller
public class ProductConfigController {
    private static final Logger logger = LoggerFactory.getLogger(ProductConfigController.class);

    @Autowired
    private ProductConfigService productConfigService;

    @RequestMapping("config2")
    @ResponseBody
    public String productConfig(HttpServletRequest request,
                                @RequestParam("productId") String productId,
                                @RequestParam("channelId") String channelId,
                                @RequestParam("operator") int operator,
                                @RequestParam("mm") int mm,
                                AndroidInfoBo androidInfoBo) throws IOException {
        String ip = NetworkUtils.getIpAddress(request);
        IpInfoPo ipInfo = getIpInfo(ip);
        ProductConfigPo productConfig = productConfigService.getProductConfig(productId, channelId, operator, mm);
        ResultBo resultBo = new ResultBo(ipInfo, productConfig);
        resultLog(ip, ipInfo, productConfig, resultBo.get(), androidInfoBo);
        return resultBo.get();
    }

    private IpInfoPo getIpInfo(String ip) {
        if (StringUtils.isBlank(ip)) {
            return null;
        }
        String ipInfoStr = IpSearch.getInstance().Get(ip);
        if (StringUtils.isBlank(ipInfoStr)) {
            return null;
        }
        return IpInfoPo.parseString(ip, ipInfoStr);
    }

    private static void resultLog(String ip, IpInfoPo ipInfoPo, ProductConfigPo productConfigPo, String result, AndroidInfoBo androidInfoBo) {
        LinkedHashMap<String, String> map = Maps.newLinkedHashMap();
        map.put("ip", ip);
        if (Objects.nonNull(productConfigPo)) {
            map.put("productId", productConfigPo.getProductId());
            map.put("channelId", productConfigPo.getChannelId());
            map.put("operator", String.valueOf(productConfigPo.getOperator()));
            map.put("mm", String.valueOf(productConfigPo.getMm()));
        } else {
            map.put("productId", null);
            map.put("channelId", null);
            map.put("operator", null);
            map.put("mm", null);
        }
        map.put("result", result);
        if (Objects.nonNull(ipInfoPo)) {
            map.put("province", ipInfoPo.getProvince());
            map.put("city", ipInfoPo.getCity());
        } else {
            map.put("province", null);
            map.put("city", null);
        }
        if (Objects.nonNull(androidInfoBo)) {
            map.put("imei", androidInfoBo.getImei());
            map.put("phoneBrand", androidInfoBo.getPhoneBrand());
            map.put("phoneModel", androidInfoBo.getPhoneModel());
            map.put("androidVersion", androidInfoBo.getAndroidVersion());
        } else {
            map.put("imei", null);
            map.put("phoneBrand", null);
            map.put("phoneModel", null);
            map.put("androidVersion", null);
        }
        List<String> collect = map.entrySet().stream().map(e -> e.getKey() + ":" + e.getValue()).collect(Collectors.toList());
        String join = String.join(" | ", collect);
        logger.info(join);
    }
}
