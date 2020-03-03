package com.module.user.shuyou.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitAction {
    public static Map<Integer, List<Integer>> cartIdMap;

    public static void init() {
        cartIdMap = new HashMap();
    }
}