package io.github.xinfra.lab.telemetry.plugin.delegation;

import io.github.xinfra.lab.telemetry.exception.AgentClassLoadException;
import io.github.xinfra.lab.telemetry.log.LogManager;
import io.github.xinfra.lab.telemetry.plugin.Interceptors;
import io.github.xinfra.lab.telemetry.plugin.interceptor.ConstructorInterceptor;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;
import org.apache.logging.log4j.Logger;

public class ConstructorDelegation {
    private static final Logger LOGGER = LogManager.getLogger(ConstructorDelegation.class);
    private ConstructorInterceptor interceptor;

    public ConstructorDelegation(String methodInterceptorClassName, ClassLoader classLoader) {
        try {
            interceptor = Interceptors.load(methodInterceptorClassName, classLoader);
        } catch (Throwable t) {
            LOGGER.error("load ConstructorInterceptor class:{} failed.", methodInterceptorClassName, t);
            throw new AgentClassLoadException("load class failed. className:" + methodInterceptorClassName, t);
        }
    }

    @RuntimeType
    public void intercept(@This Object obj, @AllArguments Object[] allArguments) {
        try {
            interceptor.onConstruct(obj, allArguments);
        } catch (Throwable t) {
            LOGGER.error("ConstructorInterceptor onConstruct failed. interceptor:{}", interceptor.getClass(), t);
        }
    }
}
