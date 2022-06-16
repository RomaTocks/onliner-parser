package com.onliner.tocks.model.product.additional.values;

import java.lang.reflect.Field;

public abstract class Values
{
    public Object getFieldValue(String name) {
        Field field = null;
        try
        {
            field = this.getClass().getDeclaredField(name);
            return field.get(this);
        }
        catch (NoSuchFieldException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
