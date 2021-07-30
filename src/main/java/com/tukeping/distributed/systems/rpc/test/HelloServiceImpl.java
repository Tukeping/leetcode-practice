package com.tukeping.distributed.systems.rpc.test;

/**
 * @author tukeping
 * @date 2020/6/19
 **/
public class HelloServiceImpl implements HelloService {

    public String hello(String name) {
        return "Hello " + name;
    }
}
