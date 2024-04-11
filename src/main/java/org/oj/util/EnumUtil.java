//package org.oj.util;
//
//import org.oj.base.Enumerable;
//import org.oj.base.SelectListEnumItem;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.reflections.Reflections;
//
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Set;
//
///**
// * 枚举类型工具
// *
// * @author DH
// * @create 2020-12-15
// */
//@Slf4j
//public class EnumUtil {
//
//    /**
//     * 初始化所有枚举类型中的map，保存枚举值和名称为Map
//     */
//    public static void initEnumsMap() {
//        try {
//            Reflections reflections = new Reflections("com.spms.constant");
//            Set<Class<? extends Enumerable>> classes = reflections.getSubTypesOf(Enumerable.class);
//            for (Class<?> clazz : classes) {
//                try {
//                    HashMap<Integer, Enumerable> map = new HashMap<>(classes.size());
//                    Object[] items = clazz.getEnumConstants();
//                    for (Object item : items) {
//                        Enumerable be = (Enumerable) item;
//                        map.put(be.getKey(), be);
//                    }
//                    Field f = clazz.getDeclaredField("map");
//                    f.setAccessible(true);
//                    f.set(null, map);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (Exception e) {
//            log.error(e.getMessage(), e.toString());
//            throw e;
//        }
//    }
//
//    /**
//     * 获取指定枚举类型的枚举值和名称的集合
//     *
//     * @param clazz 枚举类型
//     * @return 枚举值和名称的SelectListItem集合
//     */
//    public static List<SelectListEnumItem> toList(Class<?> clazz) {
//        return toItems(clazz, null);
//    }
//
//    /**
//     * 获取指定枚举类型的枚举值和名称的集合
//     *
//     * @param clazz   枚举类型
//     * @param exclude 排除的枚举名称，多个值时，以任意符号隔开
//     * @return 枚举值和名称的SelectListItem集合
//     */
//    public static List<SelectListEnumItem> toList(Class<?> clazz, String exclude) {
//        return toItems(clazz, exclude);
//    }
//
//    /**
//     * 获取所有枚举类型的枚举值和名称的集合
//     *
//     * @return 所有枚举类型的枚举值和名称的集合
//     */
//    public static HashMap<String, List<SelectListEnumItem>> allToList() {
//        try {
//            Reflections reflections = new Reflections("com.spms.constant");
//            Set<Class<? extends Enumerable>> classes = reflections.getSubTypesOf(Enumerable.class);
//            HashMap<String, List<SelectListEnumItem>> results = new HashMap<>(classes.size());
//            for (Class<?> clazz : classes) {
//                try {
//                    String variableName = clazz.getSimpleName();
//                    variableName = variableName.substring(0, 1).toLowerCase() + variableName.substring(1);
//                    results.put(variableName, toItems(clazz, null));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            return results;
//        } catch (Exception e) {
//            log.error(e.getMessage(), e.toString());
//            throw e;
//        }
//    }
//
//    public static String getNameByValue(Class<?> clazz, int theValue) {
//        try {
//            Object[] items = clazz.getEnumConstants();
//            for (Object item : items) {
//                Enumerable be = (Enumerable) item;
//                if (be.getKey() == theValue) {
//                    return be.getName();
//                }
//            }
//            return null;
//        } catch (Exception e) {
//            log.error(e.getMessage(), e.toString());
//            throw e;
//        }
//    }
//
//    private static List<SelectListEnumItem> toItems(Class<?> clazz, String exclude) {
//        try {
//            List<SelectListEnumItem> selectList = new ArrayList<>();
//            Object[] items = clazz.getEnumConstants();
//            for (Object item : items) {
//                Enumerable be = (Enumerable) item;
//                if (!StringUtils.isEmpty(exclude) && exclude.contains(be.getName())) {
//                    continue;
//                }
//                try {
//                    if (clazz.getDeclaredField(be.getName()).isAnnotationPresent(EnumMapIgnore.class)) {
//                        continue;
//                    }
//                } catch (Exception e) {
//                    continue;
//                }
//                selectList.add(new SelectListEnumItem(be.getKey(), be.getName()));
//            }
//            return selectList;
//        } catch (Exception e) {
//            log.error(e.getMessage(), e.toString());
//            throw e;
//        }
//    }
//}
