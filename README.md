# JMHTest
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
