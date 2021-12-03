package com.shirj.pub.utils;

import lombok.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    /**
     * get Spring context.
     *
     * @return context, the Spring context.
     */
    public static ApplicationContext getContext() {
        return context;
    }

    public static Object getBean(String beanName) {
        return getContext().getBean(beanName);
    }

    public static <T> T getBean(Class<T> clazz) {
        return getContext().getBean(clazz);
    }

    public static Object getBean(String name, Object... args) {
        return getContext().getBean(name, args);
    }

}
