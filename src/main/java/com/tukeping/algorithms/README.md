### 算法的运行时间
算法需要执行的基本操作次数

### 大O记号
两个特性
+ 对于任意常数c>0，有O(f(n))=O(c∙f(n))
+ 对于任意常数a>b>0,有O(na+nb)=O(na)

#### 算法复杂度
+ 对数复杂度 O(log n)
> 无限接近于O(1)
  O(1)<O(logn)<O(n^n)
+ 多项式复杂度 O(n^n)
> 常熟可以去除
  低次项可以去除
+ 线性复杂度 O(n) 和 O(n^2)
+ 指数复杂度 T(n) = a^n 记为O(2^n)

### 算法分析
##### 高级语言的基本指令，均等效于常数条RAM的基本指令  
* 分支专项 goto
* 迭代循环 for() while()
> 调用 + 递归  

#### 算数级数: 与末项平方同阶  
T(n) = 1+2+3+...+n=n(n+1)/2 = O(n^2)  Sum(n) = (首项+末项) * 项数 / 2  
#### 幂方级数: 比幂次高出一阶  
T2(n) = 1^2 + 2^2 + 3^3 + ... + n^2 = O(n^3)  
#### 几何级数(a>1): 与末项同阶  
Ta(n) = a^0 + a^1 + ... + a^n = O(a^n)  
#### 收敛级数:  
1/1/2 + 1/2/3 + 1/3/4 + ... + 1/(n-1)/n = 1 - 1/n = O(1)  
h(n) = 1 + 1/2 + 1/3 + ... + 1/n = O(logn)  
log1 + log2 + log3 + ... + logn = log(n!) = O (nlogn)  

#### 循环 vs 级数
'''java
for(int i=0;i<n;i++){
  for(int j=0;j<n;j++){
	operation(i,j);
  }
}
'''
算术复杂度:
sum(n) = n + n + ... + n = n*n = O(n^2)  
<br/>

'''java
for(int i=0;i<n;i++){
  for(int j=0;j<i;j++){
  	operation(i,j);
  }
}
'''
算术复杂度:
sum(n) = 0 + 1 + ... + (n-1) = n*(n-1) / 2 = O(n^2)  
<br/>
'''java
for(int i=0;i<n;i++){
  for(int j=0;j<i;j+=2013){
  	operation(i,j);
  }
}
'''  
算法复杂度: O(n^2)  
'''java
for(int i=0;i<n;i<<=1){
  for(int j=0;j<i;j++){
  	operation(i,j);
  }
}
'''  
算法复杂度: O(n)

#### 练习题
'''java
x = n;
y = 1;
while( x >= (y-1)*(y-1) )
  y++;
'''
算法复杂度:
由程序可知(y-1)^2 <= n 那么y <= sqrt(n)+1
O(sqrt(n))

#### 封底估算
地球(赤道)周长 = 787 * 360/7.2
             = 787 * 50
             = 39350 km
1 天 = 24 hr * 60 min * 60 sec
     = 25 * 4000 = 10^5 sec

1生 = 1世纪
   = 100yr * 365 = 3*10^4day = 3*10^9 sec
三生三世 = 300 yr = 10^10sec
宇宙大爆炸 = 10^21 sec

<br/>

1G Hz电脑 = 10^9 浮点运算 flops
天河1A千万亿次 = 1P = 10^15 flops

BubbleSort
(10^9)^2 = 10^18 / 10^9 = 10^9 sec = 30yr
10^18 / 10^15 = 10^3 sec = 20min
MergeSort
10^9 * log(10^9) = 3 * 10^10 / 10^9 = 30 sec
3 * 10^10 / 10^15 = 0.03 ms

* * *

总结
-
2^n > n^3 > n^2 > n*log(n) > n > sqrt(n) > log(n) > O(1)  
从O(n^c) c为常熟 到 O(2^n) 是 从有效算法到无效算法的分水岭  
书籍: concrete Mathmatics 具体数学