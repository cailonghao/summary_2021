package com.jkl.delegate;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Leader {

    private Map<String, IEmployee> targets = new HashMap<String, IEmployee>();


    public Leader() {
        targets.put("加密", new EmployeeA());
        targets.put("登录", new EmployeeB());
    }

    public void doing(String commd) {
        targets.get(commd).doing();
    }
}
