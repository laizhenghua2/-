package com.howie.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 练习动态代理的应用
public class ProxyTest {
    public static void main(String[] args) {
        // 不使用动态代理
        /*CountImpl count = new CountImpl();
        int number = count.countChar("hello");
        System.out.println("hello的字符数=" + number); 5 */

        // 使用动态代理
        Count factory = new CountImpl(); // 创建目标对象
        MyHandler myHandler = new MyHandler(factory); // 创建InvocationHandler对象
        // 使用Proxy类的静态方法创建代理类并把返回值转为接口类型
        Count count = (Count) Proxy.newProxyInstance(factory.getClass().getClassLoader(),factory.getClass().getInterfaces(),myHandler);
        int number = count.countChar("hello");
        System.out.println("hello的字符数=" + number);
    }
}

// 1.创建接口，定义目标类要完成的功能
interface Count {
    int countChar(String str);
}

// 2.创建接口实现类
class CountImpl implements Count {
    @Override
    public int countChar(String str) {
        return str.length();
    }
}

// 3.创建InvocationHandler接口实现类，在invoke方法中完成代理类的功能(调用目标方法+增强功能)
class MyHandler implements InvocationHandler {
    private Object target = null;

    public MyHandler(Object target){
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        result = method.invoke(target,args);
        if(result != null){
            result = (int) result + 2;
            System.out.println("哈哈没想到吧，不改变源码的情况下，长度多了2！");
        }
        return result;
    }
}
