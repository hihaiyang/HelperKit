package cn.one.java.kit;

import java.util.List;
import java.util.Map;

/**
 *
 * @author yanghy
 */
public class DateKit {

    public String formatDate(String dateStr) {
        return formatDate(dateStr, "-");
    }

    public String formatDate(String dateStr, String character) {
        String regex = "(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})";
        String replacement = "$1"+character+"$2"+character+"$3 $4:$5:$6";
        return dateStr.replaceAll(regex, replacement);
    }

    /**
     * 日期搜索文字
     *
     * @param date
     *            yyyyMMddHHmmss
     * @return yyyy yyyyMM yyyyMMdd
     */
    public String dateSswz(String date) {
        if (null == date || date.length() < 4) {
            return date;
        }
        StringBuilder sb;
        try {
            sb = new StringBuilder();
            for (int i = 4; i <= date.length();) {
                sb.append(" ").append(date.substring(0, i));
                i += 2;
            }
        } catch (Exception e) {
            return null;
        }
        return sb.toString().replaceFirst(" ", "");
    }

    /**
     * 处理日期时间
     *
     * @param date
     * @return yyyyMMddHHmmss/yyyyMMdd
     */
    public String dateClean(String date) {
        if (null == date || date.trim().length() == 0 || date.trim().toLowerCase().equals("null")) {
            return null;
        }
        date = date.replaceAll("[^0-9]", "");
        if (date.length() > 14) {
            return date.substring(0, 14);
        }
        for (int i=date.length(); i<14; i++) {
            date += "0";
        }
        return date;
    }

    /**
     * 转换Map中日期字段
     *
     * @param map
     * @param field
     * @return
     * date:20180101
     * <br/>date_zhwz:2018-01-01
     * <br/>date_sswz:2018 201801 20180101
     */
    public DateKit invert(Map<String, String> map, String field) {
        if (null != map) {
            String value = map.get(field);
            if (null != value && value.trim().length() > 0) {
                String datelong = dateClean(value);
                if (null != datelong) {
                    map.put(field + "_zhwz", formatDate(datelong));
                    map.put(field + "_sswz", dateSswz(datelong));
                }
            }
        }
        return this;
    }

    /**
     * 转换Map中日期字段
     *
     * @param list
     * @param field
     */
    public DateKit invert(List<Map<String, String>> list, String field) {
        if (null != list && list.size() > 0) {
            for (Map<String, String> map : list) {
                invert(map, field);
            }
        }
        return this;
    }
}
