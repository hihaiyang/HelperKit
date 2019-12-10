package cn.one.java.kit;

import org.apache.commons.collections.CollectionUtils;

import java.util.*;

public class ListKit {

    /**
     * List集合拷贝
     *
     * @param list from来源
     * @param dest to目标(创建后的实例对象)
     */
    public static <T> void deepCopy(List<T> list, List<T> dest) {
        CollectionUtils.addAll(dest, new Object[list.size()]);
        Collections.copy(dest, list);
    }

    /**
     * List集合分页
     *
     * @param list 集合
     * @param pageNo 页码
     * @param pageSize 条数
     * @return {List}
     */
    public static <T> List<T> page(List<T> list, int pageNo, int pageSize) {
        if (null == list || list.size() < pageSize) {
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
     * 移除重复的数据
     *
     * @param list 集合
     * @param fields 判断唯一的字段
     * @return {List}
     */
    public static <T> List<Map<String, T>> filterRepeat(List<Map<String, T>> list, String[] fields) {
        Set<String> set = new HashSet<String>();
        List<Map<String, T>> newList = new ArrayList<Map<String, T>>();
        if (null == list || list.size() < 1 || null == fields
                || fields.length < 1) {
            return newList;
        }
        for (Map<String, T> map : list) {
            String key = "";
            for (String field : fields) {
                if (!map.containsKey(field)) {
                    continue;
                }
                key += (null == map.get(field)) ? "null" : String.valueOf(map.get(field));
            }
            if (set.contains(key)) continue;
            set.add(key);
            newList.add(map);
        }
        return newList;
    }

    /**
     *
     * @param list
     * @param asc
     */
    public static <T> void sort(List<T> list, boolean asc) {
        if (null == list || list.size() == 0) return;
        Collections.sort(list, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return 0;
            }
        });
    }
}
