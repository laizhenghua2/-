package com.howie.factory;

import com.howie.service.USBSell;

// 2.创建接口实现类，即目标类
public class USBServiceFactory implements USBSell {
    @Override
    public double sellUSB(int count) {
        // 目标方法
        System.out.println("目标类中，执行了sellUSB目标方法");
        return 10.0 * count;
    }
}
