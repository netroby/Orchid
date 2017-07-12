package com.eden.orchid.impl.options;

import com.eden.orchid.api.options.OptionExtractor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class IntOptionExtractor implements OptionExtractor<Integer> {

    @Override
    public boolean acceptsClass(Class<?> clazz) {
        return clazz.equals(int.class)
                || clazz.equals(Integer.class)
                || clazz.equals(int[].class)
                || clazz.equals(Integer[].class);
    }

    @Override
    public Integer getOption(Field field, JSONObject options, String key) {
        if(options.has(key) && options.get(key) instanceof Integer) {
            return options.getInt(key);
        }
        else if(field.isAnnotationPresent(IntDefault.class)) {
            return field.getAnnotation(IntDefault.class).value();
        }
        else {
            return 0;
        }
    }

    @Override
    public List<Integer> getList(Field field, JSONObject options, String key) {
        JSONArray array = (options.has(key)) ? options.getJSONArray(key) : new JSONArray();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add(array.getInt(i));
        }
        return list;
    }

    @Override
    public Object getArray(Field field, JSONObject options, String key) {
        List<Integer> list = this.getList(field, options, key);

        if (field.getType().equals(int[].class)) {
            int[] array = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                array[i] = list.get(i);
            }
            return array;
        }
        else if (field.getType().equals(Integer[].class)) {
            Integer[] array = new Integer[list.size()];
            list.toArray(array);
            return array;
        }

        return null;
    }
}
