package cn.hy.java.kit;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 公共类
 *
 * @author yanghy
 * @date 2019-01-01
 */
public class CommKit {
    /**
     * List分页
     *
     * @param list
     * @param pageSize
     * @param pageNo
     * @return
     */
    public static List<Map<String, Object>> pageList(List<Map<String, Object>> list, int pageSize, int pageNo) {
        if (null == list || list.size() < 1) {
            return list;
        }
        int fromIndex = pageNo == 1 ? 0 : (pageNo - 1) * pageSize;
        int toIndex = pageNo * pageSize;
        if (toIndex > list.size()) {
            toIndex = list.size();
        }
        return fromIndex >= toIndex ? list : list.subList(fromIndex, toIndex);
    }

    /**
     * 去除重复的数据
     *
     * @param list 数据集合
     * @param fields 验证的KEY
     * @return
     */
    public static List<Map<String, Object>> filterRepeatList(List<Map<String, Object>> list, String[] fields) {
        Set<String> set = new HashSet<String>();
        List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
        if (null == list || list.size() < 1 || null == fields
                || fields.length < 1) {
            return newList;
        }
        for (Map<String, Object> map : list) {
            String key = "";
            for (String field : fields) {
                if (!map.containsKey(field)) {
                    continue;
                }
                key += null == map.get(field) ? "null" : String.valueOf(map.get(field));
            }
            if (set.contains(key)) {
                continue;
            }
            set.add(key);
            newList.add(map);
        }
        return newList;
    }

    /**
     * Object转Map
     *
     * @param obj
     * @return
     */
    public static Map<String, Object> objectToMap(Object obj) {
        if (null == obj) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (key.compareToIgnoreCase("class") == 0) {
                    continue;
                }
                Method getter = property.getReadMethod();
                Object value = getter != null ? getter.invoke(obj) : null;
                map.put(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * Map转Object
     *
     * @param map
     * @param beanClass
     * @return
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) {
        if (null == map) {
            return null;
        }
        Object obj = null;
        try {
            obj = beanClass.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                Method setter = property.getWriteMethod();
                if (null != setter) {
                    setter.invoke(obj, map.get(property.getName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
