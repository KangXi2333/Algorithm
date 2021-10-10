import SM2.SM2Utils;
import SM2.Util;
import org.junit.Test;

public class tests {

    @Test
    public void SM2_TEST() throws Exception
    {
        String plainText = "1122334455667788";
        byte[] sourceData = Util.hexStringToBytes(plainText);

        // 国密规范测试私钥
        String prik = "969FC0F73FA117A040B37D5B5018382A74D40590EAA02809B87FA09196F8276D";

        // 国密规范测试公钥
        String pubk = "04ABC2230A05A72CEB667B20019C4F2A580E4D0A3BE9D20BF914565AB3B82631E1C0E15803FA3ADE3E6D9EEF293CBD8BAECC51D82B61404A39584198B6985686FB";
        System.out.println("加密: ");
        byte[] cipherText = SM2Utils.encrypt(Util.hexStringToBytes(pubk), sourceData);
        System.out.println(Util.encodeHexString(cipherText));
        System.out.println("");

        System.out.println("解密: ");
        String data = "04ADE0AFDD137B5E9B2CF3F4D71D329E06F3E8006598A12BB8B6A4B31F8E1D2266EFB1015812E10DC058940A3C8AB8BA29FFE788F85A5D236C3526BBA8D0E0A10D5806DAE0C2DEFADC1A49CE657D4311CDF65D9F38F1CF5004F2E4BA922EA538C9E75007CA0C7AADD8";
        plainText = Util.encodeHexString(SM2Utils.decrypt(Util.hexStringToBytes(prik), cipherText));
        System.out.println(plainText);

    }
}
