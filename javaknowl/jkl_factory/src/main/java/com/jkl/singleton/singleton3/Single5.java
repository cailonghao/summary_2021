package com.jkl.singleton.singleton3;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册式单例 容器缓存
 */
public class Single5 {

    private Single5(){

    }
    private static Map<String,Object> ioc = new ConcurrentHashMap<String, Object>();

    public static Object getBean(String className){
        synchronized (ioc){
            if (!ioc.containsKey(className)) {
                Object obj = null;
                try {
                    obj = Class.forName(className).newInstance();
                    ioc.put(className, obj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return obj;
            } else {
                return ioc.get(className);
            }
        }
    }

}
