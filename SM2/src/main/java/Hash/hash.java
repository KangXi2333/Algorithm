package Hash;

import SM2.Util;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;

public class hash {

    /** hash类型 */
    public static final String HASH_TYPE_MD5    = "MD5";
    public static final String HASH_TYPE_SHA1   = "SHA-1";
    public static final String HASH_TYPE_SHA256 = "SHA-256";
    public static final String HASH_TYPE_SHA384 = "SHA-384";
    public static final String HASH_TYPE_SHA512 = "SHA-512";
    public static final String HASH_TYPE_SM2    = "SM2";

    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Program Files (x86)\\Java\\jdk1.8.0_291\\bin\\java.exe";
        // System.out.println(hashCode(path, HASH_TYPE_SM2));
        String hex = "28b69b29cf2393347af974733e66d0904c548eb39d75a645acad69c24ecc2adc";
        Boolean flag = verify(path, hex);
        System.out.println(flag);
    }

    /**
     *
     * @param filePath
     * @param hashType
     * @return
     * @throws FileNotFoundException
     */
    public static String hashCode(String filePath, String hashType) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(filePath);

        return hashCode(fis, hashType);
    }

    /**
     *
     * @param fis
     * @param hashType
     * @return
     */
    public static String hashCode(FileInputStream fis, String hashType) {
        try{
            MessageDigest md = MessageDigest.getInstance(hashType);
            // 分多次将一个文件读入
            byte[] buffer = new byte[1024];
            int length = 1;
            while((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            fis.close();
            //转换并返回包含16个元素字节数，返回数值范围为-128到127
            byte[] md5Bytes = md.digest();
            BigInteger bigInt = new BigInteger(1, md5Bytes);
            return bigInt.toString(16);
        }catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    public static byte[] SM3Encrypt(String filePath) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(filePath);
        String resultHexString = "";
        byte[] sm3Bytes = null;
        try {
            SM3Digest sm3Digest = new SM3Digest();
            byte[] buffer = new byte[1024];
            int length = 1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                sm3Digest.update(buffer, 0, length);
            }
            fis.close();
            sm3Bytes = new byte[sm3Digest.getDigestSize()];
            sm3Digest.doFinal(sm3Bytes, 0);
            return sm3Bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sm3Bytes;
    }

    public static byte[] hmac(byte[] key, byte[] srcData) {
        KeyParameter keyParameter = new KeyParameter(key);
        SM3Digest digest = new SM3Digest();
        HMac mac = new HMac(digest);
        mac.init(keyParameter);
        mac.update(srcData, 0, srcData.length);
        byte[] result = new byte[mac.getMacSize()];
        mac.doFinal(result, 0);
        return result;
    }

    public static boolean verify(String filePath, String sm3HexString) {
        boolean flag = false;
        try {
            // byte[] srcData = Util.hexToByte(srcStr);
            byte[] sm3Hash = Util.hexToByte(sm3HexString);
            byte[] newHash = SM3Encrypt(filePath);
            if (Arrays.equals(newHash, sm3Hash)) {
                flag = true;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return flag;
    }
}
