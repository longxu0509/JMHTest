
# 腾讯犀牛鸟开源人才培养计划
## KonaJDK项目
### 任务二  ECDSA微基准测试

使用不同的数据量128B 256B 1024B 1024k

使用不同椭圆曲线参数secp256r1 secp256k1

分别对签名和验签操作进行测试, 结果如下：

---

Benchmark &emsp; &emsp; &emsp; &emsp;&emsp; &emsp; &emsp; &emsp;&emsp; &emsp;  &emsp;  (size)&emsp; (stdName)  &emsp;  Mode &emsp;     Cnt  &emsp;   Score &emsp;     Error  &emsp;    Units <br/>
JMHTestSHA256withECDSA.testSign    &emsp; &emsp;         128  &emsp; &emsp; secp256r1 &ensp; thrpt &emsp;  5 &emsp; 2920.543 ± 378.380  ops/s <br/>
JMHTestSHA256withECDSA.testSign   &emsp; &emsp;          128  &emsp; &emsp; secp256k1 &ensp; thrpt &emsp;  5 &emsp;  2409.650 ± 577.175  ops/s <br/>
JMHTestSHA256withECDSA.testSign   &emsp; &emsp;          256  &emsp; &emsp; secp256r1 &ensp; thrpt &emsp;  5 &emsp;  2875.413 ± 454.075  ops/s <br/>
JMHTestSHA256withECDSA.testSign   &emsp; &emsp;          256  &emsp; &emsp; secp256k1 &ensp; thrpt &emsp;  5 &emsp; 2470.993 ± 403.869  ops/s <br/>
JMHTestSHA256withECDSA.testSign   &emsp; &emsp;         1024  &emsp;&ensp;&ensp;  secp256r1  &ensp; thrpt &emsp;  5 &emsp; 2773.597 ± 426.970  ops/s <br/>
JMHTestSHA256withECDSA.testSign  &emsp; &emsp;          1024  &emsp;&ensp;&ensp;  secp256k1  &ensp; thrpt &emsp;  5 &emsp; 2349.296 ± 576.974   ops/s <br/>
JMHTestSHA256withECDSA.testSign   &emsp; &emsp;      1048576 &ensp;  secp256r1  &ensp; thrpt &emsp;  5 &emsp; 448.171 ±  58.295 &ensp;  ops/s <br/>
JMHTestSHA256withECDSA.testSign   &emsp; &emsp;      1048576 &ensp;  secp256k1  &ensp; thrpt &emsp;  5 &emsp;  433.320 ±  70.351 &ensp; ops/s <br/>
JMHTestSHA256withECDSA.testVeritySign      128  &emsp; &emsp; secp256r1  &ensp; thrpt &emsp;  5 &emsp; 1312.574 ± 380.016  ops/s <br/>
JMHTestSHA256withECDSA.testVeritySign      128  &emsp; &emsp;  secp256k1 &ensp; thrpt &emsp;  5 &emsp; 1240.508 ± 138.699  ops/s <br/>
JMHTestSHA256withECDSA.testVeritySign      256   &emsp; &emsp; secp256r1 &ensp; thrpt &emsp;  5 &emsp; 1273.728 ± 366.293  ops/s <br/>
JMHTestSHA256withECDSA.testVeritySign      256   &emsp; &emsp; secp256k1 &ensp; thrpt &emsp;  5 &emsp; 1127.165 ± 229.770  ops/s <br/>
JMHTestSHA256withECDSA.testVeritySign     1024  &emsp;&ensp;&ensp;   secp256r1  &ensp; thrpt &emsp;  5 &emsp; 1218.472 ± 450.555  ops/s <br/>
JMHTestSHA256withECDSA.testVeritySign     1024   &emsp;&ensp;&ensp;  secp256k1  &ensp; thrpt &emsp;  5 &emsp; 1326.752 ±  93.560  ops/s <br/>
JMHTestSHA256withECDSA.testVeritySign  1048576            &ensp;     secp256r1  &ensp; thrpt &emsp;  5 &emsp;  415.643 ±  14.197 &ensp;  ops/s <br/>
JMHTestSHA256withECDSA.testVeritySign  1048576            &ensp;     secp256k1  &ensp; thrpt &emsp;  5 &emsp;  402.980 ±  39.169 &ensp;  ops/s <br/>

### 任务三  SM2秘钥对生成任务报告


#### 1 项目背景

Java长期霸榜编程语言第一，OpenJDK也是开源阆苑中的一朵仙葩。腾讯KonaJDK青出于OpenJDK，腾讯KonaJDK针对大数据，机器学习和云计算场景进行了独特的优化，在高性能及安全性方面多有建树。腾讯KonaJDK团队拥有多位OpenJDK Reviewer，Committer和Author，在国内JDK团队中首屈一指。而OpenJDK开源社区活跃且成熟，它的协作模式也是开源项目中的典范，无论对于初识的爱好者，还是长期的贡献者都十分友好。参与OpenJDK的开发，不仅可以领略世界级基础软件的设计理念与实现细节，还可以积累开源项目的贡献经验。

#### 2 任务三描述

高阶任务：SM2密钥对生成
	 将OpenJDK sun.security.util.math和sun.security.ec中的工具应用到国密SM2算法，以生成SM2的密钥对。
需要考虑：如何检验生成的密钥对符合SM2曲线的要求？密钥对的安全强度是否达标？在完成这个任务的过程中，同学们将会对椭圆曲线密码学的原理有更为深刻地理解。

***生成密钥对***

**生成私钥**

需要考虑哪些问题？

**生成公钥**

使用两种不同的方法去计算公钥。

比较这两种方法进行的优劣？

**性能测试**

编写JMH性能测试程序，对上面两种生成公钥的方法进行性能测试。

分析性能差异的原因。

#### 3 SM2秘钥对生成

##### 3.1 椭圆曲线计算原理的理解

高阶任务是SM2秘钥对生成器，为了实现此任务，我们需要做一些准备工作，对公钥密码学、密码学安全的随机数、椭圆曲线计算原理和中国椭圆曲线SM2有一个大体上的了解。

公钥密码学的精髓是陷门单向函数，在公钥密码学中往往借助于一些数学中的困难问题来构造陷门单向函数，例如大整数分解问题，有限域上的离散对数问题、椭圆曲线离散对数问题。ECC（椭圆曲线密码学），是一种基于椭圆曲线数学的公开密钥加密算法，ECC的安全性依赖于椭圆曲线离散对数问题的难解性，在多倍点运算中，已知多倍点与基点，求解倍数的问题称为椭圆曲线离散对数问题。对于一般椭圆曲线的离散对数问题，目前只存在指数级计算复杂度的求解方法。与大数分解问题及有限域上离散对数问题相比，椭圆曲线离散对数问题的求解难度要大得多。因此，在相同安全程度要求下，椭圆曲线密码较其它公钥密码所需的密钥规模要小得多。是在使用更小的密钥的同时，提供更快的性能和更高等级的安全。

在基于椭圆曲线的加解密实现方案中，首先要给出椭圆曲线域参数来确定一条椭圆曲面。基于椭圆曲线的密码系统主要有7个系统参数T=（p，FR，a，b，G，n，h），其中p表示所选择的有限域；FR是有限域上的元素的表示方法；a和b表示椭圆曲线的参数；G是在曲线上选择的基点；n表示该基点的阶，是一个足够大的素数；h是n的余因子，是一个小整数。椭圆曲线是连续的，并不适合用于加密；所以必须把椭圆曲线变成离散的点，要把椭圆曲线定义在有限域上。而椭圆曲线密码所使用的椭圆曲线是定义在有限域内，有限域最常见的例子是有限域GF(p)，指给定某质数p，由0,1,2...p-1共p个元素组成的整数集合中加法、二倍运算。定义在Fp(p是大于3的素数)上的椭圆曲线方程为：
$$
y^2=x^3+ax+b
$$
如下是方程表示的曲线，随着a和b的不同，椭圆曲线也会在平面上呈现出不同的形状，但椭圆曲线始终是关于x轴对称的。以下是不同参数的椭圆曲线图。

 ![image.png](https://s2.loli.net/2022/09/15/fnXgoIjr4KJtQNE.png)

​                                  图1 不同参数的椭圆曲线

椭圆曲线也可以有运算，像实数的加减乘除一样，这就需要使用到加群。它的点加法是这样定义的，椭圆曲线上两个点P和Q他们相加的结果就是过两点做一条直线，与曲线相交与第三个点R，R关于X轴的称点就是相加的结果，二倍运算，可以看成P+P，则是过P点做切线后续步骤和之前一样如下图所示。通过加法运算可以构造一个循环阿贝尔群，群的生成元为g点，群的阶数为n。这个有什么用呢，由于椭圆曲线系数乘法的机制，在已知参数k与基点P坐标时，求公钥Q=kP的运算是相对容易的；我们可以通过Double-and-add和Montgomery Ladder等算法快速求解，时间复杂度是$O（logn）$。

![image.png](https://s2.loli.net/2022/09/15/mukFHWpesDZdnx5.png)

​                                                                         图2 P+Q =R

##### 3.2 标量乘法的实现

标量乘法可以表示为$Q=kP$其中k为整数，p定义在有限域椭圆曲线E上的一个点。在椭圆曲线ECC加密中最核心最耗时的运算就是标量乘$kP$的计算，它的快速实现依赖于标量乘$kP$的运行效率，因此接下来我将对几种常见的标量乘法进行介绍，并提出了一些自己的改进。

(1) Double-and-add

Double-and-add为标量乘法中比较简单、最经典的算法，其一次运算时点加和倍点所构成，整数k可以表示成一个长度为$l$的二进制序列，则k可以表示如下：

![wps7.png](https://s2.loli.net/2022/09/15/oXEjBgRJWADHNeS.png)

则kP可以表示如下可以变成$l$个部分相加，从而大大减少倍乘次数

![wps10.png](https://s2.loli.net/2022/09/15/XzhiFHpVN3B84Ss.png)

以下是Double-and-add的几种实现：

   1. Iterative algorithm, index increasing
```
 public ECPoint doubleAndAddInc(ECPoint p, BigInteger k) {
        ECPoint res = INFINTY;
        ECPoint temp = p;
        while (k.compareTo(BigInteger.ZERO) == 1) {
            if (k.testBit(0))
                res = pointAdd(res, temp);
            temp = pointAdd(temp, temp);
            k = k.shiftRight(1);
        }
        return res;
 }
```

   2. Iterative algorithm, index decreasing
```
  public ECPoint doubleAndAddDec(ECPoint p, BigInteger k) {
      ECPoint res = p;
      int i = k.bitLength() - 2;
      while (i >= 0) {
          res = pointAdd(res, res);
          if (k.testBit(i)) {
              res = pointAdd(res, p);
          }
          i = i - 1;
      }
      return res;
  }
```
   3. Iterative algorithm, index decreasing
 ```
  public ECPoint MontgomeryMuti(ECPoint p, BigInteger k) {
        if (k.equals(BigInteger.ZERO))
            return INFINTY;
        else if (k.equals(BigInteger.ONE))
            return p;
        else if (k.mod(TWO).equals(BigInteger.ONE))
            return pointAdd(p, MontgomeryMuti(p, k.subtract(BigInteger.ONE)));
        else
            return MontgomeryMuti(pointAdd(p, p), k.divide(TWO));
    }
 ```
$l$表示k的二进制长度，二进制算法里非0比特的数量是$l/2$，故上述算法1和2执行一次标量乘的花费近似$l/2$次点加与$l$次倍点。算法3采用递归进行求解，算法复杂度上和算法1,2类似，优点是递归代码编写方便，容易理解，缺点是递归会占用额外的栈空间，造成空间的浪费，且执行效率没有提升。

   4. Search Table 

​        仔细观察$kp$的二进制展开式，我发现需要相加的部分总是固定的，![wps16.png](https://s2.loli.net/2022/09/15/miLYhr973pPySFZ.png)所以想到能不能才用空间换时间的方式，预先将这些中间值给算好，保留到数组中，需要用到时直接从数组中拿，从而减少了点的倍乘操作。经JMH测试发现采用计算效率可以提升**3倍**左右。

 ```
 private List<ECPoint> mutiTable = new ArrayList<>();  
 public SM2() {  
     ...  
     // 预先计算倍点
     ECPoint temp = g;  
     for (int i = 0; i < 256; i++) {  
         this.mutiTable.add(temp);  
         temp = pointAdd(temp, temp);  
     }  
 }  
 
 public ECPoint doubleAndAddSearchTable(ECPoint p, BigInteger s) {  
     ECPoint res = INFINTY;  
     int i = 0;  
     while (s.compareTo(BigInteger.ZERO) == 1) {  
         if (s.testBit(0))  
             res = pointAdd(res, mutiTable.get(i));  
         i++;  
         s = s.shiftRight(1);  
     }  
     return res;  
 }  
 ```
(2) NAF标量乘法

在上述基于二进制展开的算法中，虽然我采用了预计算查表的方式进行了优化，可以将减少倍点操作的次数，但还是需要很多点加操作，所需的点加操作与其二进制展开式中非零比特位的位数相等。为了继续减少点加的次数，我们就需要是零比特位尽量多，这样就引出了带符号的非相邻表示型NAF，是对k进行重新编码，表示为{-1,0,1}的序列，其中NAF(k)在k的所有带符号表示序列中非零位个数最少，降低了点加的操作，且长度最多比二进制表示形式的长度大1。一下是NAF算法以及NAF标量乘法的具体实现：

 ```
// get k NAF code
    public BigInteger[] NAF(BigInteger k) {
        BigInteger []res = new BigInteger [k.bitLength()+1];
        int m = 0;
        while (k.compareTo(BigInteger.ZERO) == 1) {
            if (k.mod(TWO).equals(BigInteger.ONE)) {
                res[m] = TWO.subtract(k.mod(FOUR));
                k = k.subtract(res[m]);
            } else {
                res[m] = BigInteger.ZERO;
            }
            k = k.divide(TWO);
            m++;
        }
        return res;
    }

    // NAF Multiply
    public ECPoint NAFMultiply(ECPoint p, BigInteger k) {
        BigInteger []a = NAF(k);
        ECPoint temp = p;
        for (int i = k.bitLength()-1; i >= 0; i--) {
            temp = pointAdd(temp, temp);
            if (a[i].equals(BigInteger.ONE))
                temp = pointAdd(temp, p);
            if (a[i].equals(BigInteger.ONE.negate()))
                temp = pointAdd(temp, negate(p));
        }
        return temp;
    }
 ```
 关于NAF标量乘法的具体计算效率，我们可以做一个简单的分析，是私钥k的二进制表示序列长度为$l_1$，NAF表示的序列长度为$l_2$，一次倍点的运算时间为M，由于在椭圆曲线上可以忽略由$P$计算$-P$的花费，所以这里设点加和点减运算的时间均为A。

由上分析可知，k的二进制序列中1的个数为$l_1/2$，没有优化的二进制标量乘法的运算时间$T_1$约为$l_1$次倍点与$l_2/2$次点加运行所消耗的时间的总和：

![wps25.png](https://s2.loli.net/2022/09/15/aGuRQ1MwIcNzTPs.png)

根据NAF的性质可知，二元NAF序列中非零个数约为![img](file:///C:\Users\xldream\AppData\Local\Temp\ksohtml49700\wps26.png)，则一次二元NAF标量乘法的运算时间![img](file:///C:\Users\xldream\AppData\Local\Temp\ksohtml49700\wps27.png)约为![img](file:///C:\Users\xldream\AppData\Local\Temp\ksohtml49700\wps28.png)次倍点和![img](file:///C:\Users\xldream\AppData\Local\Temp\ksohtml49700\wps29.png)次点加或点减运算所消耗时间的总和：

![wps30.png](https://s2.loli.net/2022/09/15/oRiEdSKXNUcmqLJ.png)

由NAF性质可知$l_2$最多比$l_1$大1，由于k的长度较长，$l_2$和可$l_1$以近似相等，可以看出NAF标量乘法在效率上有了较大的提高。同理也可以采用查表的方式进行对倍乘项M的消除，此时消耗时间就只剩下$l_2/3$次点加或点减运算。

​		 NAF标量乘法效率这么高，那是不是就可以高枕无忧了呢，其实它也缺陷，NAF算法不能很好的抵抗SPA攻击。SPA攻击是通过采集密码芯片在进行NAF标量乘运算过程的功耗波形，利用密钥与运算间的相关性从功耗波形中对秘钥进行分析提取。NAF有倍乘、点加、点减这三种操作，在功耗波形中，0与非0很容易分辨出，即使采用了查表法改进的NAF虽然消除了倍点运算，但是点加，点减也较容易从功耗波形看出明显的区别，所以NAF算法在遭受SPA攻击时，对于信息的安全会构成很大的威胁。

​	这里能不能继续对NAF进行改进呢，平衡NAF标量乘法中运算的能耗差异，现在我们主要剩下点加和点减操作，这里都是对P点进行操作，能不能先预计算出-P，这样点减操作，就转化成点加操作，使得点加和点减功耗波形相同，隐藏了密钥在非0时计算的相关性，以至于攻击者无法从功耗波形中分辨出点加还是点减，提高了标量乘法抵御SPA攻击的能力。

 ```
  public ECPoint NAFMultiplySearchTable(ECPoint p, BigInteger k) {
        BigInteger []a = NAF(k);
        ECPoint temp = p;
        ECPoint np = negate(p);
        int t = k.bitLength()-1;
        for (int i = t; i >= 0; i--) {
            if (a[i].equals(BigInteger.ONE))
                temp = pointAdd(mutiTable.get(t-i+1), p);
            if (a[i].equals(BigInteger.ONE.negate()))
                temp = pointAdd(mutiTable.get(t-i+1), np);
        }
        return temp;
    }
 ```

（3）Montgomery Ladder

Montgomery阶梯算法如下所示.实际上,算法中的p0总是存储了目前为止所求出的结果,这样,最后输出的p0就是要求的结果.在算法的循环体中,由于每次循环都需要做一次二倍运算和一次除子类的加法运算,这就使得每次执行循环体时的运算量是一样的.所以,Montgomery阶梯标量乘算法一定程度上可以抵抗简单边带信道攻击.

 ```
  public ECPoint montgomeryMultiply(ECPoint p, BigInteger s) {
        ECPoint p0 = INFINTY;
        ECPoint p1 = p;
        int idx = s.bitLength();
        while (idx >= 0) {
            if (s.testBit(idx--)) {
                p0 = pointAdd(p0, p1);
                p1 = pointAdd(p1, p1);
            } else {
                p1 = pointAdd(p0, p1);
                p0 = pointAdd(p0, p0);
            }
        }
        return p0;
    }
 ```
以上就是标量乘法，已知k和P我们计算公钥Q。而已知基点P和公钥Q坐标情况下求解参数k则是非常困难的，因为椭圆曲线上加法的特性，这些点之间没有规律可言，并且此时的k足够大，目前最好的解法的时间复杂度是 $O(n^{1/2})$看似是多项式的复杂度，请注意这里的n非常大，以256位为例，则时间复杂度就是 $O(n^{256/2})$, 是指数时间复杂度，这个数字非常大，常数时间复杂度不可解。对于一般曲线的离散对数问题，目前的求解方法都为指数级计算复杂度，未发现有效的亚指数级 计算复杂度的一般攻击方法；而对于某些特殊曲线的离散对数问题，存在多项式级计算复杂度或者亚 指数级计算复杂度算法。选择曲线时，应避免使用易受上述方法攻击的密码学意义上的弱椭圆曲线。

##### 3.3 ECC算法生成秘钥对

参考SM2椭圆曲线公钥密码算法总则，实现SM2秘钥对的生成。

输入一个有效的Fq(q = p且p为大于3的素数，或q = 2 m )上椭圆曲线系统参数的集合。这里的SM2椭圆曲线参数如下：

![image-20220915181011708](C:\Users\xldream\AppData\Roaming\Typora\typora-user-images\image-20220915181011708.png)

输出：与椭圆曲线系统参数相关的一个秘钥对(d,P)。

① 用随机数发生器产生整数![wps38.png](https://s2.loli.net/2022/09/15/HLdc92jFUR5nMb8.png) 作为算法的私钥。

② G为基点，计算点![wps39.png](https://s2.loli.net/2022/09/15/kLKQDqN7zcxZoYV.png)

③ 密钥对是(d,P)，其中d为私钥，P为公钥。

​	实现如下：

```
public Pair<BigInteger, ECPoint> generateKeyPair(String multiply) {
    int nBitLength = getN().bitLength();
    int minWeight = nBitLength >>> 2;
    BigInteger d;
    while(true) {
        d = new BigInteger(nBitLength, this.random); 
        if (d.compareTo(BigInteger.ONE) < 0  || (d.compareTo(n) >= 0))
            continue;

        if (WNafUtil.getNafWeight(d) < minWeight)
            continue;
        break;
    }
    ECPoint Q = null;
    switch (multiply) {
        case "doubleAndAddSearchTable":
            Q = doubleAndAddSearchTable(getG(), d); break;
        case "doubleAndAddDec":
            Q = doubleAndAddDec(getG(), d); break;
        case "doubleAndAddInc":
            Q = doubleAndAddInc(getG(), d); break;
        case "doubleAndAddRecursive":
            Q = doubleAndAddRecursive(getG(), d); break;
        case "NAFMultiply":
            Q = NAFMultiply(getG(), d); break;
        case "NAFMultiplySearchTable":
            Q = NAFMultiplySearchTable(getG(), d); break;
        case "montgomeryMultiply":
            Q = montgomeryMultiply(getG(), d); break;
        default:
            System.out.println("please input correct method name");
            break;
    }
    return new Pair<BigInteger, ECPoint>(d, Q);
}

```

公钥生成了，还需要对它进行验证，验证生成的公钥是否满足要求，我们的算法是否是正确的。参考SM2总则，一般需要验证以下几点：

a) 验证公钥Q不是无穷远点O

b) 验证公钥Q的坐标是域$F_p$中的元素

c) 验证![wps42.png](https://s2.loli.net/2022/09/15/ORT6MjSxgyQHLfP.png)

d) 验证$nP=O$

   以下是验证公钥的代码实现：

```
public boolean verityPublicKey(ECPoint pk) {
    if (p.equals(INFINTY))
        return false;

    if (pk.getAffineX().compareTo(BigInteger.ZERO) == -1 ||
            pk.getAffineX().compareTo(getP().subtract(BigInteger.ONE)) == 1)
        return false;

    if (pk.getAffineY().compareTo(BigInteger.ZERO) == -1 ||
            pk.getAffineY().compareTo(getP().subtract(BigInteger.ONE)) == 1)
        return false;

    if ((pk.getAffineY().multiply(pk.getAffineY()).mod(p))
        .compareTo(pk.getAffineX().pow(3).add(getA()
        .multiply(pk.getAffineX())).add(getB()).mod(getP())) != 0)
        return false;

    if (!doubleAndAddDec(pk, getN()).equals(INFINTY))
        return false;

    return true;
}
```

​		SM2椭圆曲线公钥密码算法是我国自主设计的公钥密码算法, SM2是非对称加密，基于ECC。由于该算法基于ECC，故其签名速度与秘钥生成速度都快于RSA。ECC 256位安全强度比RSA 2048位高，且运算速度快于RSA。使用国密算法是非常有意义的，随着金融安全上升到国家安全高度，近年来国家有关机关和监管机构站在国家安全和长远战略的高度提出了推动国密算法应用实施、加强行业安全可控的要求。摆脱对国外技术和产品的过度依赖，建设行业网络安全环境，增强我国行业信息系统的“安全可控”能力显得尤为必要和迫切。

***在生成私钥中需要注意的地方？***

​        一定要保证了生成的私钥足够大，否则私钥很小的话很容易进行破解。算法里对d的最低的位数进行了限制, 保证了私钥足够大。

​		私钥的是采用随机数生成器进行生成的，所以这里在随机数生成器选择上尽量选择安全性高一些的随机数生成器，例如SecureRandom，它可以理解为Random升级，它的种子选取比较多，主要有：时间、cpu、使用情况、点击事件等一些种子，可以用这些来得到一个近似随机的种子。这样的话，除了理论上有破解的可能，实际上基本没有被破解的可能。

### 5 性能测试

有了对以上几种标量乘法算法的分析，接下来我们对他们的性能进行测试和分析，以下是JMH的测试结果，我们可以看到前面三种Double-and-add计算效率是差不多的，它们需要的倍点和点加操作没有得到减少，所有效率都是比较低的。后来我们提出了基于预计算查表的Double-and-add，下图中第4个算法，消除倍乘操作，计算效率得到了显著的提升，较前三种算法提升3倍左右。

后来我们发现上述改进算法虽然消除了倍点操作，但是还是有点加操作，于是想到能不能对k进行重新编码，使非0位个数变少，这样就引出了带符号的非相邻表示型NAF，采用NAF算法我们可以减少点加操作。下图第5个算法可以看出，计算效率确实较Double-and-add有了一定的提升。我们接着对NAF也进行了预计算优化，可以看到效率得到显著的的提升。最后我们测试的算法是Montgomery阶梯算法，虽然效率不高，但每次执行循环体时的运算量是一样的.所以,Montgomery阶梯标量乘算法一定程度上可以抵抗简单边带信道攻击。性能测试结果如下：

 Benchmark &emsp; &emsp; &emsp; &emsp;&emsp; &emsp;&emsp; &emsp;  &emsp;&emsp;   &emsp;&emsp; &emsp; (multiply) &emsp; &emsp;&emsp;  &emsp;  Mode&emsp;  &emsp;  Cnt &emsp;  &emsp;    Score   &emsp;  Error &emsp;    &emsp; Units<br/>
 keyPairGenTest.testPointMultiply &emsp;&emsp; doubleAndAddInc &emsp; &emsp;&emsp;&emsp;   thrpt  &emsp;&emsp;&emsp;   3  &emsp; 423.421 ±  779.448 &emsp;     &emsp; ops/s <br/>
 keyPairGenTest.testPointMultiply &emsp;&emsp; doubleAndAddDec &emsp; &emsp;&emsp;  &emsp;thrpt &emsp;&emsp;&emsp;    3  &emsp; 405.562 ±  191.200 &emsp;   &emsp;  ops/s <br/>
 keyPairGenTest.testPointMultiply &emsp;&emsp; doubleAndAddRecursive&emsp;     thrpt &emsp; &emsp;&emsp;   3  &emsp; 398.297 ±  279.366 &emsp; &emsp;     ops/s <br/>
 keyPairGenTest.testPointMultiply &emsp;&emsp; doubleAndAddSearchTable    thrpt &emsp; &emsp; &emsp; 3 &emsp; 1303.378 ± 1224.276 &emsp;         ops/s <br/>
 keyPairGenTest.testPointMultiply &emsp;&emsp; NAFMultiply  &emsp; &emsp; &emsp; &emsp; &emsp;  &emsp;thrpt &emsp; &emsp;      3  &emsp; 473.275 ±  223.927 &emsp;  &emsp;     ops/s <br/>
 keyPairGenTest.testPointMultiply &emsp;&emsp; NAFMultiplySearchTable &emsp;     thrpt  &emsp;&emsp; &emsp;3 &emsp; 1737.896 ± 1997.995 &emsp;         ops/s <br/>
 keyPairGenTest.testPointMultiply &emsp;&emsp; montgomeryMultiply &emsp; &emsp; &emsp; thrpt &emsp; &emsp;     3  &emsp;&emsp; 291.631 ±  538.497 &emsp;        ops/s <br/>

### 6 致谢

​        非常荣幸能够参与本次的腾讯犀牛鸟开源人才培养计划，转瞬之间已进行尾声，非常感谢两位老师两个月以来对我们的细心指导，对我的成长帮助很大，这是我参与的第一个开源项目，对我而言意义非凡，第一次提交PR，第一次被合并，第一次接触密码学。通过参与本次项目让我椭圆曲线密码学的原理有了一定的了解，更让我对开源产生了极大的兴趣，希望我可以怀揣着这份兴趣，在开源的世界里继续探索。

​       最后，再次感谢两位老师，祝老师们工作顺利，万事胜意。



### 7 参考资料

[1] https://www.oscca.gov.cn/sca/xxgk/2010-12/17/1002386/files/b791a9f908bb4803875ab6aeeb7b4e03.pdf

[2] https://www.oscca.gov.cn/sca/xxgk/2010-12/17/1002386/files/b965ce832cc34bc191cb1cde446b860d.pdf

[3] https://en.wikipedia.org/wiki/Elliptic_curve_point_multiplication

[4] https://github.com/openjdk/jdk/blob/master/src/jdk.crypto.ec/share/classes/sun/security/ec/ECOperations.java#L231

[5] https://github.com/bcgit/bc-java/blob/master/core/src/main/java/org/bouncycastle/math/ec/FixedPointCombMultiplier.java

[6] https://eprint.iacr.org/2015/1060.pdf