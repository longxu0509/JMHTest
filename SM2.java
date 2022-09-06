package ustc.edu.cn;

import javafx.util.Pair;
import org.bouncycastle.math.ec.WNafUtil;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xulong
 * @version 1.0
 * @email longxu@mail.ustc.edu.cn
 */
public class SM2 {

    public static String[] sm2_param = {
            "FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFF",
            "FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFC",
            "28E9FA9E9D9F5E344D5A9E4BCF6509A7F39789F515AB8F92DDBCBD414D940E93",
            "FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFF7203DF6B21C6052B53BBF40939D54123",
            "32C4AE2C1F1981195F9904466A39C9948FE30BBFF2660BE1715A4589334C74C7",
            "BC3736A2F4F6779C59BDCEE36B692153D0A9877CC62A474002DF32E52139F0A0"
    };

    public final static ECPoint INFINTY = new ECPoint(BigInteger.ZERO, BigInteger.ONE);
    public final static BigInteger TWO = new BigInteger("2");
    public final static BigInteger THREE = new BigInteger("3");
    public final static BigInteger FOUR = new BigInteger("4");

    public static SM2 Instance()
    {
        return new SM2();
    }

    private final BigInteger p;
    private final BigInteger a;
    private final BigInteger b;
    private final BigInteger n;
    private final BigInteger gx;
    private final BigInteger gy;
    private final EllipticCurve curve;
    private final ECPoint g;
    private final int cofactor;
    private  SecureRandom random;
    private List<ECPoint> mutiTable = new ArrayList<>();

    public SM2() {
        this.p = new BigInteger(sm2_param[0], 16);
        this.a = new BigInteger(sm2_param[1], 16);
        this.b = new BigInteger(sm2_param[2], 16);
        this.n = new BigInteger(sm2_param[3], 16);
        this.gx = new BigInteger(sm2_param[4], 16);
        this.gy = new BigInteger(sm2_param[5], 16);
        this.g = new ECPoint(gx, gy);
        this.curve = new EllipticCurve(new ECFieldFp(p), a, b);
        this.cofactor = 1;
        this.random = new SecureRandom();
        // init mutiTable
        ECPoint temp = g;
        for (int i = 0; i <= 256; i++) {
            this.mutiTable.add(temp);
            temp = pointAdd(temp, temp);
        }
    }

    public BigInteger getP() {
        return p;
    }

    public BigInteger getA() {
        return a;
    }

    public BigInteger getB() {
        return b;
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getGx() {
        return gx;
    }

    public BigInteger getGy() {
        return gy;
    }

    public EllipticCurve getCurve() {
        return curve;
    }

    public int getCofactor() {
        return cofactor;
    }

    public ECPoint getG() {
        return g;
    }

    public SecureRandom getRandom() {
        return random;
    }

    // point negation
    public ECPoint negate(ECPoint p) {
        if (p.equals(INFINTY))
            return INFINTY;
        else
            return new ECPoint(p.getAffineX(), p.getAffineY().negate());
    }

    // point add
    public ECPoint pointAdd(ECPoint p1, ECPoint p2) {
        if (p1.equals(INFINTY))
            return p2;

        if (p2.equals(INFINTY))
            return p1;

        BigInteger k;
        if (p1.getAffineX().subtract(p2.getAffineX()).mod(p).equals(BigInteger.ZERO)) {
            if (p1.getAffineY().subtract(p2.getAffineY()).mod(p).equals(BigInteger.ZERO)) { // p1 == p2
                k = (THREE.multiply(p1.getAffineX().multiply(p1.getAffineX()).mod(p)).add(a).mod(p))
                        .multiply(TWO.multiply(p1.getAffineY()).modInverse(p)).mod(p);
            } else {
                return INFINTY;
            }
        } else { // p1 != p2
            k = p2.getAffineY().subtract(p1.getAffineY())
                    .multiply(p2.getAffineX().subtract(p1.getAffineX()).modInverse(p)).mod(p);

        }
        BigInteger p3x = k.multiply(k).mod(p).subtract(p1.getAffineX()).subtract(p2.getAffineX()).mod(p);
        BigInteger p3y = k.multiply(p1.getAffineX().subtract(p3x)).mod(p).subtract(p1.getAffineY()).mod(p);
        return new ECPoint(p3x, p3y);
    }

    // Double-and-add, Iterative algorithm, index  increasing
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

    // Double-and-add, Iterative algorithm, index decreasing
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

    // doubleAndAdd Recursive algorithm:
    public ECPoint doubleAndAddRecursive(ECPoint p, BigInteger s) {
        if (s.equals(BigInteger.ZERO))
            return INFINTY;
        else if (s.equals(BigInteger.ONE))
            return p;
        else if (s.mod(TWO).equals(BigInteger.ONE))
            return pointAdd(p,
                    doubleAndAddRecursive(p, s.subtract(BigInteger.ONE)));
        else
            return doubleAndAddRecursive(pointAdd(p, p), s.divide(TWO));
    }

    // doubleAndAdd SearchTable
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

    // NAF Multiply SearchTable
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

    // Montgomery Ladder
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

    // generate keyPair
    public Pair<BigInteger, ECPoint> generateKeyPair(String multiply) {
        int nBitLength = getN().bitLength();
        int minWeight = nBitLength >>> 2;
        BigInteger d;
        while(true) {
            d = new BigInteger(nBitLength, this.random);  // generate BigInteger rand number
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

    // verity publicKey
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
                .compareTo(pk.getAffineX().pow(3).add(getA().multiply(pk.getAffineX())).add(getB()).mod(getP())) != 0)
            return false;

        if (!doubleAndAddDec(pk, getN()).equals(INFINTY))
            return false;

        return true;
    }

    public static void main(String[] args) throws InvalidAlgorithmParameterException {
        SM2 sm2 = SM2.Instance();
        Pair<BigInteger, ECPoint> keyPair = sm2.generateKeyPair("NAFMultiplySearchTable");
        if (sm2.verityPublicKey(keyPair.getValue())){
            System.out.println("verity success");
        } else {
            System.out.println("verity failed");
        }
    }
}
