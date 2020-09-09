package com.howie.handler;

import com.howie.factory.USBServiceFactory;
import com.howie.service.USBSell;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
// 3.创建InvocationHandler接口的实现类，在invoke方法中完成代理类的功能。增强功能+调用目标方法
public class MyHandler implements InvocationHandler {
    private Object target = null;
    // 动态代理：目标对象是活动的，不是固定的，需要传入进来，传入谁，就给谁创建代理
    public MyHandler(Object target){
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        result = method.invoke(target,args); // 执行目标方法
        // 增加功能
        if(result != null){
            result = (double) result + 20.0;
        }
        return result;
    }
}
