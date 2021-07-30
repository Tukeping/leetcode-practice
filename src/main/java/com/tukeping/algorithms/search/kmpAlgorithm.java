package com.tukeping.algorithms.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by tukeping on 14-7-8.
 *
 * kmp算法思想参考网站:
 * http://www.ruanyifeng.com/blog/2013/05/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm.html
 *
 * 测试数据:
 * BBC ABCDAB ABCDABCDABDE
 * ABCDABD
 */
public class kmpAlgorithm {

    List<String> pre = new ArrayList<String>();
    List<String> nxt = new ArrayList<String>();

    public static void main(String[] args) {
        kmpAlgorithm kmpAlgorithm = new kmpAlgorithm();
        Scanner sc = new Scanner(System.in);
        String oriStr = sc.nextLine();
        String matchStr = sc.nextLine();
        kmpAlgorithm.kmp(oriStr,matchStr);
        /*String testStr = "ABCDABD";
        int[] pmt = new int[testStr.length()];
        for(int i=1;i<=testStr.length();i++){
            String tmp = testStr.substring(0,i);
            kmpAlgorithm.calcPreAndNxt(tmp);
            kmpAlgorithm.calcPmt(pmt,i-1);
        }
        for(int i=0;i<pmt.length;i++){
            System.out.print(pmt[i]+" ");
        }*/
    }

    public void kmp(String oriStr,String matchStr){
        //第一步 计算 Partial Match Table 部分匹配表
        int[] pmt = new int[matchStr.length()];
        int i,j;
        for(i=1;i<=matchStr.length();i++){
            String tmp = matchStr.substring(0,i);
            calcPreAndNxt(tmp);
            calcPmt(pmt, i - 1);
        }
        //第二步 逐个比较字符串与搜索词 并确定移动位数 [移动位数 = 已匹配的字符数 - 对应的部分匹配值]
        String t1,t2;
        int index = 0,move,mark = 0;
        boolean b = false;
        for(i=0;i<oriStr.length();){
            t1 = oriStr.substring(i,i+1);
            t2 = matchStr.substring(index,index+1);
            if(t1.equals(t2)){
                if(b) mark = i;
                index++;
                i++;
                b = false;
            }else{
                move = index == 0 ? 1 :index - pmt[index-1];
                index = 0;
                i = mark == 0 ? i+1 : mark + move;
                mark = 0;
                b = true;
            }
            //这里只找到第一个匹配的 如果需要查找全部 可以不要break继续搜索
            if(index >= matchStr.length()) break;
        }
        if(index == matchStr.length()){
            System.out.println("find it, Index is begin :"+(i-index)+" end :"+i
                    +" , String :"+oriStr.substring(i-index,i));
        }else{
            System.out.println("not find");
        }
    }

    private void calcPreAndNxt(String str){
        int len = str.length() , i;
        //calc pre
        for(i=1;i<=len-1;i++){
            pre.add(str.substring(0, i));
        }
        //calc nxt
        for(i=1;i<len;i++){
            nxt.add(str.substring(i, len));
        }
    }

    private void calcPmt(int[] pmt,int index){
        boolean b = false;
        for(int i=0;i<pre.size();i++){
            for(int j=0;j<nxt.size();j++){
                if(pre.get(i).equals(nxt.get(j))){
                    pmt[index] = pre.get(i).length();
                    b = true;
                    break;
                }
            }
            if(b) break;
        }
        pre.clear();
        nxt.clear();
    }
}