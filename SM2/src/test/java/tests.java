import entity.CompInfo;
import method.AES.AES_128;
import method.Read.ReadTxt;
import method.SM2.SM2Utils;
import org.bouncycastle.util.encoders.Hex;
import util.ScpUtil;
import util.Util;
import method.ZipDecrypt.ZipDe;
import org.junit.Test;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    @Test
    public void testZipDe() {
        ZipDe zipDe = new ZipDe();
        zipDe.unzipWithPassword("D:\\WORK\\TargetField\\test\\解压.zip", "D:\\WORK\\TargetField\\test\\UnZip", "123456");
    }

    @Test
    public void testZipEn() {
        ZipDe zipDe = new ZipDe();
        zipDe.enZipWithPassword("D:\\WORK\\TargetField\\test\\解压.zip", "D:\\WORK\\TargetField\\test\\UnZip", "123456");
    }


    @Test
    public void testComp2txt() {
        ReadTxt readTxt = new ReadTxt();

        String filePath = "D:\\WORK\\TargetField\\CoreFiles.txt";
        String s1 = "java_error_in_webstorm64.hprof";
        String s2 = "1,155,720,249";
        String s3 = "java_error_in_webstorm64.hprof";
        String s4 = "1,155,720,249";
        boolean status = false;

        List<CompInfo> compInfoList = new ArrayList<CompInfo>();
        CompInfo compInfo1 = new CompInfo(s1, s2);
        CompInfo compInfo2 = new CompInfo(s3, s4);
        CompInfo compInfo3 = new CompInfo(s1, s2);

        compInfoList.add(compInfo1);
        compInfoList.add(compInfo2);
        compInfoList.add(compInfo3);

        status = readTxt.comp2txt(filePath, compInfoList);

        System.out.println(status);
    }

    @Test
    public void scpTest() {
        ScpUtil scpUtil = new ScpUtil();

        try {
            scpUtil.getFile("/root/scpTest/BAKA.txt", "D:\\WORK\\TargetField");
            scpUtil.putFile("D:\\WORK\\TargetField\\CoreFiles.txt", "/root/scpTest");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestAES_128() {
        AES_128 aes = new AES_128();
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[16]; // 128 bits are converted to 16 bytes;
        byte bytes1[] = new byte[16];
        random.nextBytes(bytes);
        bytes1 = bytes;
        String temp = Util.byteToHex(bytes);
        bytes1[0] = (byte) (bytes1[0] + 3);
        bytes = Util.hexToByte(temp);/*
        System.out.println(Util.byteToHex(bytes));
        System.out.println(Util.byteToHex(bytes1));*/


//   加解密 密钥
        byte[] keybytes = { 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38 };
        String content = "103323652";
        // 加密字符串
        System.out.println("加密前的密文1：" + Util.byteToHex(bytes));
        System.out.println("加密前的密文2：" + Util.byteToHex(bytes1));
        System.out.println("加密密钥：" + new String(keybytes));
        // 加密方法
        byte[] enc = aes.encrypt(bytes, keybytes);
        byte[] enc1 = aes.encrypt(bytes1, keybytes);
        System.out.println("加密密文1后的内容：" + Util.byteToHex(enc));
        System.out.println("加密密文2后的内容：" + Util.byteToHex(enc));
        // 解密方法
        byte[] dec = aes.decrypt(enc, keybytes);
        byte[] dec1 = aes.decrypt(enc1, keybytes);
        System.out.println("解密密文1后的内容：" + Util.byteToHex(dec));
        System.out.println("解密密文2后的内容：" + Util.byteToHex(dec1));
    }

}
