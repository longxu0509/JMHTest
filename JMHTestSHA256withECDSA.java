package ustc.edu.cn;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.concurrent.TimeUnit;

/**
 * @author xulong
 * @version 1.0
 * @email longxu@mail.ustc.edu.cn
 */

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5)
@Threads(4)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.SECONDS)

public class JMHTestSHA256withECDSA {
    private byte[] signed;
    private KeyPair keyPair;
    private PublicKey pk;
    private PrivateKey sk;
    private byte[] message;

    @Param(value = {"128", "256", "1024", "1048576"})
    private int size;

    @Param(value = {"secp256r1", "secp256k1"})
    private String stdName;

    @Setup
    public void setUp() throws Exception {
        message = new byte[size];
        keyPair = getKeyPair(stdName);
        pk = keyPair.getPublic();
        sk = keyPair.getPrivate();
        signed = makeSign(sk, message);
    }
    
    public static KeyPair getKeyPair(String stdName) throws Exception {
        ECGenParameterSpec ecSpec = new ECGenParameterSpec(stdName);
        KeyPairGenerator kp = KeyPairGenerator.getInstance("EC");
        kp.initialize(ecSpec, new SecureRandom());
        KeyPair keyPair = kp.generateKeyPair();
        return keyPair;
    }

    public boolean veritySign(PublicKey pk, byte[] signed, byte[] message) throws Exception {
        Signature v = Signature.getInstance("SHA256withECDSA");
        v.initVerify(pk);
        v.update(message);
        boolean valid = v.verify(signed);
        return valid;
    }
   
    public byte[] makeSign(PrivateKey sk, byte[] message) throws Exception {
        Signature s = Signature.getInstance("SHA256withECDSA");
        s.initSign(sk);
        s.update(message);
        byte[] signed = s.sign();
        return signed;
    }
    
    @Benchmark
    public void testSign() throws Exception {
        makeSign(sk, message);
    }

    @Benchmark
    public void testVeritySign() throws Exception {
        veritySign(pk, signed, message);
    }

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(JMHTestSHA256withECDSA.class.getSimpleName())
                .result("result.json")
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}
