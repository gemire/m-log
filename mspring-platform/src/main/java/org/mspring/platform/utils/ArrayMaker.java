/**
 * 
 */
package org.mspring.platform.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gao Youbo
 * @since 2012-12-3
 * @Description
 * @TODO
 */
public class ArrayMaker<T> {
    private Class type;

    public ArrayMaker(Class type) {
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    T[] createArray(int size) {
        return (T[]) Array.newInstance(type, size);
    }

    List createList() {
        return new ArrayList();
    }
}
