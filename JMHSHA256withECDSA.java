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

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 5)
@Threads(4)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)

public class JMHSHA256withECDSA {

    @Param(value = {"128", "256", "1024", "10240"})
    private int size;

    /**
     * verify signed
     * @param pk public key
     * @param signed  private key and message generated signature
     * @param message message that needs to be signed
     * @return boolean
     * @throws Exception
     */
    public static boolean veritySign(PublicKey pk, byte[] signed, byte[] message) throws Exception {
        Signature v = Signature.getInstance("SHA256withECDSA");
        v.initVerify(pk);
        v.update(message);
        boolean valid = v.verify(signed);
        return valid;
    }

    /**
     * making sign
     * @param sk  private key
     * @param message message that needs to be signed
     * @throws Exception
     */
    public static byte[] makeSign(PrivateKey sk, byte[] message) throws Exception {
        Signature s = Signature.getInstance("SHA256withECDSA");
        s.initSign(sk);
        s.update(message);
        byte[] signed = s.sign();
        return signed;
    }

    /**
     * Generate key pair
     * @return
     * @throws Exception
     */
    public static KeyPair getKeyPair(String stdName) throws Exception {
        ECGenParameterSpec ecSpec = new ECGenParameterSpec(stdName);
        KeyPairGenerator kp = KeyPairGenerator.getInstance("EC");
        kp.initialize(ecSpec, new SecureRandom());
        KeyPair keyPair = kp.generateKeyPair();
        return keyPair;
    }

    @Benchmark
    public void testSecp256k1() throws Exception {
        KeyPair keyPair = getKeyPair("secp256k1");
        // get public pk
        PublicKey pk  = keyPair.getPublic();
        // get private pk
        PrivateKey sk = keyPair.getPrivate();

        byte[] message = new byte[size];
        byte[] signed = makeSign(sk, message);
        boolean verifyECDSA = veritySign(pk, signed, message);
    }

    @Benchmark
    public void testSecp256r1() throws Exception {
        KeyPair keyPair = getKeyPair("secp256r1");
        // get public pk
        PublicKey pk  = keyPair.getPublic();
        // get private pk
        PrivateKey sk = keyPair.getPrivate();

        byte[] message = new byte[size];
        byte[] signed = makeSign(sk, message);
        boolean verifyECDSA = veritySign(pk, signed, message);
    }

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(JMHSHA256withECDSA.class.getSimpleName())
                .result("result.json")
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}

