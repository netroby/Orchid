package com.eden.orchid.impl.options;

import com.eden.orchid.api.options.OptionExtractor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FloatOptionExtractor implements OptionExtractor<Float> {

    @Override
    public boolean acceptsClass(Class<?> clazz) {
        return clazz.equals(float.class)
                || clazz.equals(Float.class)
                || clazz.equals(float[].class)
                || clazz.equals(Float[].class);
    }

    @Override
    public Float getOption(Field field, JSONObject options, String key) {
        if(options.has(key) && options.get(key) instanceof Float) {
            return (float) options.getDouble(key);
        }
        else if(field.isAnnotationPresent(FloatDefault.class)) {
            return field.getAnnotation(FloatDefault.class).value();
        }
        else {
            return 0.0f;
        }
    }

    @Override
    public List<Float> getList(Field field, JSONObject options, String key) {
        JSONArray array = (options.has(key)) ? options.getJSONArray(key) : new JSONArray();
        List<Float> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add((float) array.getDouble(i));
        }
        return list;
    }

    @Override
    public Object getArray(Field field, JSONObject options, String key) {
        List<Float> list = this.getList(field, options, key);

        if (field.getType().equals(float[].class)) {
            float[] array = new float[list.size()];
            for (int i = 0; i < list.size(); i++) {
                array[i] = list.get(i);
            }
            return array;
        }
        else if (field.getType().equals(Float[].class)) {
            Float[] array = new Float[list.size()];
            list.toArray(array);
            return array;
        }

        return null;
    }
}
