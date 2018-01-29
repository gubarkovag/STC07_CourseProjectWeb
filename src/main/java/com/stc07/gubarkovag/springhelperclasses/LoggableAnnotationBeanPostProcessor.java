package com.stc07.gubarkovag.springhelperclasses;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class LoggableAnnotationBeanPostProcessor implements BeanPostProcessor {
    private Map<String, Class> map = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Loggable.class)) {
            map.put(beanName, beanClass);
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = map.get(beanName);
        if (beanClass != null) {
            Field[] fields = beanClass.getDeclaredFields();
            for (Field field : fields) {
                if ("logger".equals(field.getName())) {
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, bean, Logger.getLogger(beanClass));
                    break;
                }
            }
        }

        return bean;
    }
}
