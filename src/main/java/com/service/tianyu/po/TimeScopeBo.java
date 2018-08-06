package com.service.tianyu.po;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liunanji
 * date 2018/8/5
 */
public class TimeScopeBo {
    private int week;
    private int begin;
    private int end;

    public TimeScopeBo(String format) {
        format = format.replace(" ", StringUtils.EMPTY);
        String[] split = format.split("#");
        String week = split[0];
        String timeScope = split[1].replace(":", StringUtils.EMPTY);
        String[] beginEnd = timeScope.split("-");
        this.week = Integer.valueOf(week);
        this.begin = Integer.valueOf(beginEnd[0]);
        this.end = Integer.valueOf(beginEnd[1]);
    }

    public static List<TimeScopeBo> createTimeScopeList(String format) {
        format = format.replace(" ", StringUtils.EMPTY);
        List<TimeScopeBo> collect = Arrays.stream(format.split(";")).map(TimeScopeBo::new).collect(Collectors.toList());
        return collect;
    }

    public boolean inScope(LocalDateTime dateTime) {
        if (dateTime.getDayOfWeek().getValue() != week) {
            return false;
        }
        String hm = String.valueOf(dateTime.getHour()) + String.format("%02d", dateTime.getMinute());
        if (Integer.valueOf(hm) >= begin && Integer.valueOf(hm) <= end) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        TimeScopeBo timeScopeBo = new TimeScopeBo("7#20:00-21:20");
        System.out.println(timeScopeBo.toString());
        System.out.println(String.format("%02d", 5));
        List<TimeScopeBo> timeScopeList = createTimeScopeList("7#20:00-21:20;7#20:00-21:20;");
        System.out.println(timeScopeBo.inScope(LocalDateTime.now()));
    }

    @Override
    public String toString() {
        return "TimeScopeBo{" + "week=" + week + ", begin=" + begin + ", end=" + end + '}';
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
