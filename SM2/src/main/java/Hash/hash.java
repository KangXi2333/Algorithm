package Hash;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.MessageDigest;

public class hash {

    /** hash类型 */
    public static final String HASH_TYPE_MD5    = "MD5";
    public static final String HASH_TYPE_SHA1   = "SHA-1";
    public static final String HASH_TYPE_SHA256 = "SHA-256";
    public static final String HASH_TYPE_SHA384 = "SHA-384";
    public static final String HASH_TYPE_SHA512 = "SHA-512";
    public static final String HASH_TYPE_SM2    = "SM2";

    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Program Files (x86)\\Java\\jdk1.8.0_291\\bin\\jar.exe";
        System.out.println(hashCode(path, HASH_TYPE_SM2));
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
}
