package com.tukeping;

import com.google.gson.Gson;
import org.apache.commons.lang3.time.StopWatch;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * @author tukeping
 * @date 2021/8/13
 **/
public class Example {

    class Node {
        String item;
        Integer count;

        public Node(String item, Integer count) {
            this.item = item;
            this.count = count;
        }
    }

    Map<String, Integer> map = new HashMap<>();
    PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o2.count - o1.count);
    Gson gson = new Gson();
    static StopWatch stopWatch = new StopWatch();

    private void initData(String[] data) {
        for (String datum : data) {
            initData(datum);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }
    }

    private void initData(List<String> data) {
        for (String datum : data) {
            initData(datum);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }
    }

    private void initData(String datum) {
        HashMap<String, ArrayList<String>> row = gson.fromJson(datum, HashMap.class);
        for (Map.Entry<String, ArrayList<String>> entry : row.entrySet()) {
            for (String val : entry.getValue()) {
                String[] item = val.split("#");
                String itemName = item[0].trim();
                Integer itemCount = Integer.parseInt(item[1]);
//                System.out.println("add item[" + itemName + "#" + itemCount + "]");
                map.merge(itemName, itemCount, Integer::sum);
            }
        }
    }

    public List<String> topN(int N) {
        List<Node> ret = new ArrayList<>();
        int n = N;
        while (!pq.isEmpty() && n-- > 0) {
            ret.add(pq.poll());
        }
        for (Node node : ret) {
            pq.offer(node);
        }
        System.out.println("offer top" + N);
        return ret.stream().map(anode -> anode.item + "#" + anode.count).collect(Collectors.toList());
    }

    public static Example readFromText() {
        String[] data = new String[]{
                "{\"盒饭\":[\"盒饭#1\"],\"主食\":[\"牛肉炒饭#1\",\"杂酱面#1\"]}",
                "{\"熟食类\":[\"自制黄牛肉0.6斤48元#1\",\"韩泡菜 0.5斤 7元#1\"],\"点心\":[\"吐司面包3元/份#2\"]}",
                "{\"粮油类\":[\"长粒香米#1\"]}",
                "{\"点心类\":[\"小米糕#2\",\"肉包#2\"]}",
                "{\"点心类\":[\"青菜香菇包#2\",\"小米糕#2\"]}",
                "{\"盒饭\":[\"盒饭#1\"]}",
                "{\"点心类\":[\"黑米馒头#2\",\"咸菜香菇包#2\",\"抹茶蛋糕#1\"]}",
                "{\"半荤类\":[\"大蒜 羊排肉#1\"],\"点心类\":[\"葱卷#2\",\"肉包#2\",\"核桃蛋糕#2\"]}"
        };

        Example example = new Example();
        example.initData(data);
        return example;
    }

    public static Example readFromFile(String fileName) throws IOException {
        List<String> rows = Files.readAllLines(Paths.get(URI.create(fileName)), StandardCharsets.UTF_8);
        System.out.println("read file rows:" + rows.size());
        Example example = new Example();
        example.initData(rows);
        return example;
    }

    public static void main(String[] args) throws IOException {
        stopWatch.start();
        Example topN_cost = readFromFile(args[0]);
        stopWatch.stop();
        System.out.println("readFromFile cost " + stopWatch);

        topN_cost(topN_cost, 2);
        topN_cost(topN_cost, 5);
        topN_cost(topN_cost, 10);
        topN_cost(topN_cost, 100);
    }

    private static void topN_cost(Example inst, int n) {
        stopWatch.reset();
        stopWatch.start();
        System.out.println("================");
        List<String> top5 = inst.topN(n);
        for (String s : top5) System.out.println(s);
        stopWatch.stop();
        System.out.println("top" + n + " cost " + stopWatch);
    }
}
