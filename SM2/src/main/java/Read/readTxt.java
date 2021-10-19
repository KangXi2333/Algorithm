package Read;

import org.bouncycastle.util.StringList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class readTxt {
    /**
     * 读取txt文件的内容
     * @param filePath 想要读取的文件对象
     * @return 返回文件内容
     */
    public static boolean comp2txt(String filePath, String fileName, String fileSize){
        List<String> strList = new ArrayList<String>();
        boolean flag = false;
        File file = new File(filePath);
        InputStreamReader read = null;
        BufferedReader reader = null;
        try {
            read = new InputStreamReader(new FileInputStream(file),"GBK");
            reader = new BufferedReader(read);
            String line;
            while ((line = reader.readLine()) != null) {
                strList.add(line);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (read != null) {
                try {
                    read.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }

        for (String s : strList) {
            if(flag = (s.contains(fileName) && s.contains(fileSize))){
                // System.out.println("存在");
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args) {

        String filePath = "D:\\WORK\\TargetField\\CoreFiles1.txt";
        String s1 = "java_error_in_webstorm64.hprof";
        String s2 = "1,155,720,249";
        boolean status = false;

        status = comp2txt(filePath, s1, s2);

        System.out.println(status);
    }
}
