package method.ZipDecrypt;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;

public class ZipDe {
    public void unzipWithPassword(String zipPath, String destPath, String password){
        // 先判断zip源文件是否存在，不存在退出
        if (StringUtils.isBlank(zipPath)){
            return;
        }
        ZipFile zipFile;
        try{
            zipFile = new ZipFile(zipPath);
            // 设置字符集
            zipFile.setFileNameCharset("utf-8");
            // 判断是否加密
            if (zipFile.isEncrypted()){
                // 添加密码
                zipFile.setPassword(password);
            }
            //解压
            zipFile.extractAll(destPath);
        }catch (Exception e){
            System.out.println("解压失败");
        }
    }

    public void enZipWithPassword(String zipPath, String destPath, String password) {
        // 先判断zip源文件是否存在，不存在退出
        if (StringUtils.isBlank(zipPath)){
            return;
        }
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(zipPath);
        } catch (ZipException e) {
            e.printStackTrace();
        }

        ArrayList<File> files = new ArrayList<>();

        //设置压缩文件参数
        ZipParameters parameters = new ZipParameters();
        //设置压缩方法
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);

        //设置压缩级别
        //DEFLATE_LEVEL_FASTEST     - Lowest compression level but higher speed of compression
        //DEFLATE_LEVEL_FAST        - Low compression level but higher speed of compression
        //DEFLATE_LEVEL_NORMAL  - Optimal balance between compression level/speed
        //DEFLATE_LEVEL_MAXIMUM     - High compression level with a compromise of speed
        //DEFLATE_LEVEL_ULTRA       - Highest compression level but low speed
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

        //设置压缩文件加密
        parameters.setEncryptFiles(true);

        //设置加密方法
        parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);

        //设置aes加密强度
        parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);

        //设置密码
        parameters.setPassword("wzx");

        //添加文件到压缩文件
        try {
            zipFile.addFiles(files, parameters);
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }
}
