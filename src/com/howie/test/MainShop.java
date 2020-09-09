package com.howie.test;

import com.howie.factory.USBServiceFactory;
import com.howie.handler.MyHandler;
import com.howie.service.USBSell;

import java.lang.reflect.Proxy;

public class MainShop {
    public static void main(String[] args) {
        // 4.使用Proxy类的静态方法，创建代理对象，并把返回值转为接口类型
        // 1.创建目标对象
        USBSell factory = new USBServiceFactory();
        // 2.创建InvocationHandler对象
        MyHandler myHandler = new MyHandler(factory);
        // 3.创建代理对象
        USBSell proxy = (USBSell) Proxy.newProxyInstance(factory.getClass().getClassLoader(),factory.getClass().getInterfaces(),myHandler);
        double price = proxy.sellUSB(1);
        System.out.println("购买了一个U盘，花费：" + price);
    }
}
