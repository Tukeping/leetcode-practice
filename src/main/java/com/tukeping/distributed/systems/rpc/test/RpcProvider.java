package com.tukeping.distributed.systems.rpc.test;

import com.tukeping.distributed.systems.rpc.RpcFramework;

/**
 * @author tukeping
 * @date 2020/6/19
 **/
public class RpcProvider {

    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        RpcFramework.export(service, 1234);
    }
}
