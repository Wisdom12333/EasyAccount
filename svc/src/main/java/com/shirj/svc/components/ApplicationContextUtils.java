package com.shirj.svc.components;

import lombok.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * The Application context utils.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
@Component
public final class ApplicationContextUtils implements ApplicationContextAware {

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

    /**
     * Get bean by its name.
     *
     * @param beanName the bean name
     * @return the bean
     */
    public static Object getBean(String beanName) {
        return getContext().getBean(beanName);
    }

    /**
     * Get bean by its class.
     *
     * @param <T>   the bean class
     * @param clazz the clazz
     * @return the bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return getContext().getBean(clazz);
    }

    /**
     * Get bean and trigger its constructor.
     *
     * @param name the bean name
     * @param args the args of constructor
     * @return the bean
     */
    public static Object getBean(String name, Object... args) {
        return getContext().getBean(name, args);
    }

}
