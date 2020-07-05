package com.tukeping.distributed.rpc.simple.test;

/**
 * @author tukeping
 * @date 2020/6/19
 **/
public class HelloServiceImpl implements HelloService {

    public String hello(String name) {
        return "Hello " + name;
    }
}
