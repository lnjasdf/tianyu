package com.service.tianyu.po;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author liunanji
 * date 2018/8/5
 */
public class ResultBo {
    private ProductConfigPo productConfigPo;
    private IpInfoPo ipInfoPo;

    public ResultBo(IpInfoPo ipInfoPo, ProductConfigPo productConfigPo) {
        this.ipInfoPo = ipInfoPo;
        this.productConfigPo = productConfigPo;
    }

    private ResultType matchProvince(String province) {
        if (StringUtils.isNotEmpty(province)) {
            if (StringUtils.isNotEmpty(ipInfoPo.getCity()) && province.indexOf(ipInfoPo.getCity()) >= 0) {
                return ResultType.RESULT_CLOSE_CLOSED;
            } else if (StringUtils.isNotEmpty(ipInfoPo.getProvince()) && province.indexOf(ipInfoPo.getProvince()) >= 0) {
                return ResultType.RESULT_CLOSE_CLOSED;
            }
        }
        return ResultType.RESULT_CLOSE_OPENED;
    }

    private String matchProvinceClosed(String province) {
        return String.valueOf(matchProvince(province).ordinal());
    }

    private String matchProvinceAllowed(String province) {
        ResultType res = ResultType.RESULT_CLOSE_CLOSED;
        if (matchProvince(province) == ResultType.RESULT_CLOSE_CLOSED) {
            res = ResultType.RESULT_CLOSE_OPENED;
        }
        return String.valueOf(res.ordinal());
    }

    private String matchControl(String control) {
        if (StringUtils.isEmpty(control)) {
            return "9999";
        }
        return control;
    }

    private String processTimeBan() {
        ResultType res = ResultType.RESULT_CLOSE_OPENED;
        if (StringUtils.isNotEmpty(productConfigPo.getTimeBan())) {
            List<TimeScopeBo> timeScopeList = TimeScopeBo.createTimeScopeList(productConfigPo.getTimeBan());
            LocalDateTime now = LocalDateTime.now();
            for (TimeScopeBo timeScopeBo : timeScopeList) {
                if (timeScopeBo.inScope(now)) {
                    res = ResultType.RESULT_CLOSE_CLOSED;
                    break;
                }
            }
        }
        return String.valueOf(res.ordinal());
    }

    private List<String> getResultTrue() {
        List<String> result = Lists.newArrayListWithCapacity(8);
        result.add(matchProvinceClosed(productConfigPo.getProvinceClosed()));
        result.add(matchProvinceClosed(productConfigPo.getProvinceClosed2()));
        result.add(matchProvinceClosed(productConfigPo.getProvinceClosed3()));
        result.add(matchProvinceAllowed(productConfigPo.getProvinceAllowed1()));
        result.add(matchProvinceAllowed(productConfigPo.getProvinceAllowed2()));
        result.add(processTimeBan());
        result.add(matchControl(productConfigPo.getControl()));
        result.add(matchControl(productConfigPo.getInterval()));
        return result;
    }

    public String get() {
        if (Objects.isNull(ipInfoPo)) {
            return "3;3;3;3;3;3;9999;9999";
        }
        if (Objects.isNull(productConfigPo)) {
            return "2;2;2;2;2;2;9999;9999";
        }
        return String.join(";", getResultTrue());
    }
}
