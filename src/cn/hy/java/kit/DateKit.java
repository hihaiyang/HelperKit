package cn.hy.java.kit;

import java.util.List;
import java.util.Map;

public class DateKit {
    /**
     * 格式化日期
     *
     * @param date
     *            yyyyMMddHHmmss
     * @return yyyy-MM-dd HH:mm:ss
     */
    public String formatDate(String date) {
        if (null == date) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder(date);
            if (date.length() == 8) {
                date = sb.insert(6, "-").insert(4, "-").toString();
            }
            else {
                date = sb.insert(12, ":").insert(10, ":").insert(8, " ").insert(6, "-")
                        .insert(4, "-").toString();
            }
        } catch (Exception e) {
            return null;
        }
        return date;
    }

    /**
     * 日期搜索文字
     *
     * @param date
     *            yyyyMMddHHmmss
     * @return yyyy yyyyMM yyyyMMdd
     */
    public String dateSswz(String date) {
        if (null == date) {
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
    public String handlerDate(String date) {
        if (null == date || date.length() < 8) {
            return null;
        }
        if (date.matches("[0-9]{8,}")) {
            if (date.length() > 14) {
                date = date.substring(0, 14);
            }
            else {
                for (int i = date.length(); i < 14; i++) {
                    date = date + "0";
                }
            }
        }
        else {
            date = date.replaceAll("\\.|\\|/|年|月", "-");
            date = date.replaceAll("/", "-");
            date = date.replaceAll("(日\\s{0,})|\\s{1,}", " ");
            date = date.replaceAll("时|分", ":");
            date = date.replaceAll("秒", "");
            String[] arr = date.split(" ");
            if (null != arr) {
                String[] dateArr = arr[0].split("-");
                if (null != dateArr && dateArr.length == 3) {
                    String y = dateArr[0].replaceAll("\\D", ""), m = dateArr[1].replaceAll("\\D", ""), d = dateArr[2].replaceAll("\\D", "");
                    if (y.length() == 4) {
                        m = m.length() == 1 ? "0" + m : m.substring(0, 2);
                        d = d.length() == 1 ? "0" + d : d.substring(0, 2);
                        String _time = "";
                        if (arr.length > 1) {
                            String[] timeArr = arr[1].split(":");
                            if (null != timeArr) {
                                for (String _t : timeArr) {
                                    _t = _t.replaceAll("\\D", "");
                                    if (_t.length() == 1) {
                                        _time += "0" + _t;
                                    }
                                    else if (_t.length() > 2) {
                                        _time += _t.substring(0, 2);
                                    }
                                    else {
                                        _time += _t;
                                    }
                                }
                            }
                        }
                        date = y + m + d + _time;
                        for (int i = date.length(); i < 14; i++) {
                            date = date + "0";
                        }
                    }
                    else {
                        date = null;
                    }
                }
                else {
                    date = null;
                }
            }
            else {
                date = null;
            }
        }
        if (null != date && date.endsWith("000000")) {
            date = date.substring(0, 8);
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
     * <br/>
     * date_zhwz:2018-01-01
     * <br/>
     * date_sswz:2018 201801 20180101
     */
    public DateKit invert(Map<String, String> map, String field) {
        if (null != map) {
            String value = map.get(field);
            if (null != value && value.trim().length() > 0) {
                String date = handlerDate(value);
                if (null != date) {
                    String fmtDate = formatDate(date);
                    if (null != fmtDate) {
                        map.put(field + "_zhwz", fmtDate);
                        map.put(field + "_sswz", dateSswz(date));
                    }
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
