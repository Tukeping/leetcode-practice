http://zh.wikipedia.org/wiki/%E6%8E%92%E5%BA%8F%E7%AE%97%E6%B3%95

OS: winxp, Compiler: vc8, CPU：Intel T7200,  Memory: 2G
不同数组长度下调用6种排序1000次所需时间（秒）

length          shell           quick           merge           insert          select          bubble
100             0.0141          0.359           1.875           0.204           0.313           0.421
1000            0.218           0.578           2.204           1.672           2.265           4
5000            1.484           3.25            14.14           41.392          63.656          101.703
10000           3.1             7.8             23.5            253.1           165.6           415.7
50000           21.8            40.6            121.9           411.88          6353.1          11648.5
100000          53.1            89              228.1           16465.7         25381.2         44250


结论：
数组长度不大的情况下不宜使用归并排序，其它排序差别不大。
数组长度很大的情况下Shell最快，Quick其次，冒泡最慢。

----------------------------------------------------------------------
ShellSort
希尔排序中最重要的步长计算(http://faculty.simpson.edu/lydia.sinapova/www/cmsc250/LN250_Weiss/L12-ShellSort.htm)：

Shell's original sequence: N/2 , N/4 , ..., 1 (repeatedly divide by 2);
Hibbard's increments: 1, 3, 7, ..., 2k - 1 ;
Knuth's increments: 1, 4, 13, ..., (3k - 1) / 2 ;
Sedgewick's increments: 1, 5, 19, 41, 109, ....

It is obtained by interleaving the elements of two sequences:
1, 19, 109, 505, 2161,….., 9(4k – 2k) + 1,    k = 0, 1, 2, 3,…
5, 41, 209, 929, 3905,….., 2k+2 (2k+2 – 3 ) + 1,   k = 0, 1, 2, 3, …