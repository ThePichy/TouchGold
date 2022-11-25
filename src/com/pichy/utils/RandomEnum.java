package com.pichy.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomEnum {
    private final List<?> list;
    private final int size;
    static final Random random = new Random();
    public RandomEnum(Object[] o){
        this.list = Collections.unmodifiableList(Arrays.asList(o));
        this.size = list.size();
    }
    public Object get() {
        return list.get(random.nextInt(size));
    }
}