package com.tukeping.algorithms.search;

import java.util.Scanner;

/**
 * Created by tukeping on 14-7-8.
 *
 * bm算法思想参考网站:
 * http://www.ruanyifeng.com/blog/2013/05/boyer-moore_string_search_algorithm.html
 *
 * 测试数据:
 * HERE IS A SIMPLE EXAMPLE
 * EXAMPLE
 */
public class BoyerMoore {
    public static void main(String[] args) {
        BoyerMoore boyerMoore = new BoyerMoore();
        Scanner sc = new Scanner(System.in);
        String oriStr = sc.nextLine();
        String matchStr = sc.nextLine();
        boyerMoore.bm(oriStr, matchStr);
        /*String oriStr =   "HERE IS A SIMPLE EXAMPLE";
        String matchStr = "EXAMPLE";
        boyerMoore.bm(oriStr, matchStr);*/
    }

    public void bm(String oriStr,String matchStr){
        int len = matchStr.length();
        if(oriStr.length() < len){
            System.out.println("not find.");
        }
        int i,j,k,goodMove = 0,badMove,move,index,idx,alreadyMatch = 0;
        boolean findIt = false;
        String t1,t2,m1,m2;
        for(i=len-1;i<oriStr.length();){
            for(j=len-1;j>=0;j--){
                t1 = oriStr.substring(i-(len-1-j),i-(len-1-j)+1);
                t2 = matchStr.substring(j,j+1);
                if(!t1.equals(t2)){
                    //计算好后缀move
                    if(alreadyMatch > 0){
                        for(k=alreadyMatch;k<=len;k++){
                            m1 = matchStr.substring(0,k-1);
                            m2 = matchStr.substring(k-1,len);
                            idx = m1.indexOf(m2);
                            if(idx != -1){
                                goodMove = len - idx;
                                break;
                            }
                        }
                        alreadyMatch = 0;
                    }
                    //计算坏后缀move
                    index = matchStr.indexOf(t1);
                    badMove = j - index;
                    //取好后缀和坏后缀的最大值
                    move = Math.max(goodMove,badMove);
                    //移动匹配对象
                    i += move;
                    goodMove = 0;
                    break;
                }else{
                    alreadyMatch++;
                }

            }
            //这里只匹配首次就break，如果要找全部匹配值，可以不break继续下去
            if(alreadyMatch == len){
                findIt = true;
                break;
            }
        }
        if(findIt){
            System.out.println("find it. begin:"+(i-len)+" end:"+(i+1)+" String:"+oriStr.substring(i-len,i+1));
        }else{
            System.out.println("not find.");
        }
    }
}
