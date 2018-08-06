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
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private ProductConfigService productConfigService;

    @RequestMapping("config2")
    @ResponseBody
    public String productConfig(HttpServletRequest request,
                                @RequestParam("productId") String productId,
                                @RequestParam("channelId") String channelId,
                                @RequestParam("operator") int operator,
                                @RequestParam("mm") int mm,
                                AndroidInfoBo androidInfoBo) {
        IpInfoPo ipInfo = getIpInfo(request);
        ProductConfigPo productConfig = productConfigService.getProductConfig(productId, channelId, operator, mm);
        ResultBo resultBo = new ResultBo(ipInfo, productConfig);
        resultLog(ipInfo, productConfig, resultBo.get(), androidInfoBo);
        return resultBo.get();
    }

    private IpInfoPo getIpInfo(HttpServletRequest request) {
        IpInfoPo ipInfo = null;
        try {
            String ip = NetworkUtils.getIpAddress(request);
            if (StringUtils.isEmpty(ip)) {
                throw new Exception();
            }
            String ipInfoStr = IpSearch.getInstance().Get(ip);
            if (StringUtils.isEmpty(ipInfoStr)) {
                throw new Exception();
            }
            ipInfo = IpInfoPo.parseString(ip, ipInfoStr);
            if (ipInfo == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ipInfo;
    }

    private void resultLog(IpInfoPo ipInfoPo, ProductConfigPo productConfigPo, String result, AndroidInfoBo androidInfoBo) {
        if (Objects.isNull(ipInfoPo) || Objects.isNull(productConfigPo)) {
            return;
        }

        LinkedHashMap<String, String> map = Maps.newLinkedHashMap();
        map.put("ip", ipInfoPo.getIp());
        map.put("productId", productConfigPo.getProductId());
        map.put("channelId", productConfigPo.getChannelId());
        map.put("operator", String.valueOf(productConfigPo.getOperator()));
        map.put("mm", String.valueOf(productConfigPo.getMm()));
        map.put("result", result);
        map.put("province", ipInfoPo.getProvince());
        map.put("city", ipInfoPo.getCity());
        map.put("imei", androidInfoBo.getImei());
        map.put("phoneBrand", androidInfoBo.getPhoneBrand());
        map.put("phoneModel", androidInfoBo.getPhoneModel());
        map.put("androidVersion", androidInfoBo.getAndroidVersion());

        List<String> collect = map.entrySet().stream().map(e -> e.getKey() + ":" + e.getValue()).collect(Collectors.toList());
        String join = String.join(" | ", collect);
        logger.info(join);
    }
}
