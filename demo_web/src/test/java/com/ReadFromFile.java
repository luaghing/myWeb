package com;

import java.io.*;

/**
 * Created by pc on 2015-11-10.
 */
public class ReadFromFile {
    /**
     * 已字节读取文件
     */
    public static void readFileBytes(String fileName){
        File file=new File(fileName);
        InputStream in=null;
        System.out.println("以字节为单位读取文件内容，一次读一个字节：");
        //一次一个字节





        try {

            in =new FileInputStream(file);
            int tempbyte;
            while((tempbyte=in.read())!=-1){

                System.out.println(tempbyte);
    }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void readFileByChars(String fileName){
        File file = new File(fileName);
        Reader reader = null;
        try {
            System.out.println("以字符为单位读取文件内容，一次读一个字节：");
// 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file),"GBK");
            int tempchar;
            while ((tempchar = reader.read()) != -1){
//对于windows下，rn这两个字符在一起时，表示一个换行。
//但如果这两个字符分开显示时，会换两次行。
//因此，屏蔽掉r，或者屏蔽n。否则，将会多出很多空行。
                if (((char)tempchar) != 'r'){
                    System.out.print((char)tempchar);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println("\r\n以字符为单位读取文件内容，一次读多个字节：");
//一次读多个字符
            char[] tempchars = new char[1024];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(fileName),"GBK");
//读入多个字符到字符数组中，charread为一次读取字符数
            while ((charread = reader.read(tempchars))!=-1){
//同样屏蔽掉r不显示
                if ((charread == tempchars.length)&&(tempchars[tempchars.length-1] != 'r')){
                    System.out.print(tempchars);
                }else{
                    for (int i=0; i<charread; i++){
                        if(tempchars[i] == 'r'){
                            continue;
//                            System.out.print(tempchars[i]);
                        }else{
                            System.out.print(tempchars[i]);
                        }
                    }
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
    public static void readFileByLines(String fileName){
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
            String tempString = null;
            int line = 1;
//一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null){
//显示行号
                System.out.println("line " + line + ": " +tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    public static void main(String[] args) {
        String xx="C:\\Users\\pc\\Desktop\\新建文本文档.txt";
        ReadFromFile.readFileByChars(xx);
//        ReadFromFile.readFileByLines(xx);
//        ReadFromFile.readFileBytes(xx);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
