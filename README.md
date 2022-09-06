
## 腾讯犀牛鸟开源人才培养计划
### KonaJDK项目
#### 任务二  ECDSA微基准测试

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

#### 任务三  SM2秘钥对生成
##### 生成私钥
需要考虑哪些问题？
##### 生成公钥
使用两种不同的方法去计算公钥。
比较这两种方法进行的优劣？
##### 性能测试
编写JMH性能测试程序，对上面两种生成公钥的方法进行性能测试。
分析性能差异的原因。

##### 参考wiki实现了几种常见标量乘法，并针对部分算法进行了一些优化
(1) Double-and-add
   1. Iterative algorithm, index increasing
```
 public ECPoint doubleAndAddInc(ECPoint p, BigInteger s) {
        ECPoint res = INFINTY;
        ECPoint temp = p;
        while (s.compareTo(BigInteger.ZERO) == 1) {
            if (s.testBit(0))
                res = pointAdd(res, temp);
            temp = pointAdd(temp, temp);
            s = s.shiftRight(1);
        }
        return res;
    }
```

   2. Iterative algorithm, index decreasing
```
  public ECPoint doubleAndAddDec(ECPoint p, BigInteger s) {
      ECPoint res = p;
      int i = s.bitLength() - 2;
      while (i >= 0) {
          res = pointAdd(res, res);
          if (s.testBit(i)) {
              res = pointAdd(res, p);
          }
          i = i - 1;
      }
      return res;
  }
 ```
   3. Iterative algorithm, index decreasing
 ```
  public ECPoint MontgomeryMuti(ECPoint p, BigInteger s) {
        if (s.equals(BigInteger.ZERO))
            return INFINTY;
        else if (s.equals(BigInteger.ONE))
            return p;
        else if (s.mod(TWO).equals(BigInteger.ONE))
            return pointAdd(p, MontgomeryMuti(p, s.subtract(BigInteger.ONE)));
        else
            return MontgomeryMuti(pointAdd(p, p), s.divide(TWO));
    }
 ```
   4. Search Table 
 ```
    public ECPoint doubleAndAddSearchTable(ECPoint p, BigInteger s) {
        ECPoint res = INFINTY;
        int i = 0;
        while (s.compareTo(BigInteger.ZERO) == 1) {
            if (s.testBit(0))
                res = pointAdd(res, mutiTable.get(i));
            i++;
            s = s.shiftRight(1);
        }
        return res;
    }
 ```
(2) NAF标量乘法
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
  基于预计算改进的NAF
 ```
  public ECPoint NAFMultiplySearchTable(ECPoint p, BigInteger k) {
        BigInteger []a = NAF(k);
        ECPoint temp = p;
        int t = k.bitLength()-1;
        for (int i = t; i >= 0; i--) {
            if (a[i].equals(BigInteger.ONE))
                temp = pointAdd(mutiTable.get(t-i+1), p);
            if (a[i].equals(BigInteger.ONE.negate()))
                temp = pointAdd(mutiTable.get(t-i+1), negate(p));
        }
        return temp;
    }
 ```

（3）Montgomery Ladder
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
 ##### 并对上述方法进行了性能测试结果如下

 Benchmark &emsp; &emsp; &emsp; &emsp;&emsp; &emsp;&emsp; &emsp;  &emsp;&emsp;   &emsp;&emsp; &emsp; (multiply) &emsp; &emsp;&emsp;  &emsp;  Mode&emsp;  &emsp;  Cnt &emsp;  &emsp;    Score   &emsp;  Error &emsp;    &emsp; Units<br/>
 keyPairGenTest.testPointMultiply &emsp;&emsp; doubleAndAddInc &emsp; &emsp;&emsp;&emsp;   thrpt  &emsp;&emsp;&emsp;   3  &emsp; 423.421 ±  779.448 &emsp; &emsp; ops/s <br/>
 keyPairGenTest.testPointMultiply &emsp;&emsp; doubleAndAddDec &emsp; &emsp;&emsp;  &emsp;thrpt &emsp;&emsp;&emsp;    3  &emsp; 405.562 ±  191.200 &emsp;&emsp;  ops/s <br/>
 keyPairGenTest.testPointMultiply &emsp;&emsp;    doubleAndAddRecursive&emsp;   thrpt &emsp; &emsp;&emsp;   3  &emsp; 398.297 ±  279.366 &emsp; &emsp;  ops/s <br/>
 keyPairGenTest.testPointMultiply &emsp;&emsp;  doubleAndAddSearchTable  thrpt &emsp; &emsp; &emsp; 3 &emsp; 1303.378 ± 1224.276 &emsp;  ops/s <br/>
 keyPairGenTest.testPointMultiply &emsp;&emsp; NAFMultiply  &emsp; &emsp; &emsp; &emsp; &emsp; &emsp;thrpt &emsp; &emsp;  3  &emsp; 473.275 ±  223.927 &emsp;  &emsp; ops/s <br/>
 keyPairGenTest.testPointMultiply &emsp;&emsp;   NAFMultiplySearchTable &emsp;thrpt  &emsp;&emsp; &emsp;  3 &emsp; 1737.896 ± 1997.995 &emsp; ops/s <br/>
 keyPairGenTest.testPointMultiply &emsp;&emsp;   montgomeryMultiply &emsp; &emsp; &emsp; thrpt &emsp; &emsp; 3  &emsp;&emsp; 291.631 ±  538.497 &emsp;  ops/s <br/>