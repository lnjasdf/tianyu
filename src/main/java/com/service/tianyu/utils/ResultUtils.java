package com.service.tianyu.utils;

import org.springframework.util.StringUtils;

/**
 * Created by intron on 2017/1/15 0015.
 */
public class ResultUtils {
    private static final String SYMBOL_SEMICOLON = ";";

    public static final String ERROR_RESULT = "";

    public static final String productConfigResult(int closed, String param) {
        if (StringUtils.isEmpty(param)) {
            return null;
        }
        return String.valueOf(closed) + SYMBOL_SEMICOLON + param;
    }
}