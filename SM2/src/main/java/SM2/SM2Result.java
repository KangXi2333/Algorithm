package SM2;

import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;

public class SM2Result {
    public SM2Result() {

    }

    // sign/verify
    public BigInteger r;
    public BigInteger s;
    public BigInteger R;

    // change
    public byte[] sa;
    public byte[] sb;
    public byte[] s1;
    public byte[] s2;

    public ECPoint keyra;
    public ECPoint keyrb;
}
