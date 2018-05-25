package com.lin.utils.rsa;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtil {
    /**
     * @param str
     * @return 压缩
     */
    public static String compress(byte[] str) {

        ByteArrayOutputStream out = null;
        GZIPOutputStream gzip = null;
        String compress = "";
        try {
            out = new ByteArrayOutputStream();
            gzip = new GZIPOutputStream(out);
            gzip.write(str);
            gzip.close();
            // 这里增加base64编码
            byte[] compressed = out.toByteArray();
            compress = Base64.getEncoder().encodeToString(compressed);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return compress;
    }
 
    /**
     * @param str
     * @return 解压缩
     */
    public static String uncompress(byte[] str) {
        ByteArrayOutputStream out = null;
        ByteArrayInputStream in = null;
        GZIPInputStream gzip = null;
        String uncompress = "";
        try {
            out = new ByteArrayOutputStream();
            // 这里增加base64解码
            byte[] compressed =Base64.getDecoder().decode(str);
            in = new ByteArrayInputStream(compressed);
            gzip = new GZIPInputStream(in);
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = gzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            uncompress = out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != gzip) {
                try {
                    gzip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return uncompress;
    }


     public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
            sb.append("代码笔记：www.note52.com fjdlfjldsjlsjlfkjdslfjlsjflsjldjlf;sjfs;djsfsRFJDFJS4*(&FJLDJSL");
        String str = sb.toString();
        System.out.println("原长度：" + str.length());

        System.out.println("压缩后长度：" + GzipUtil.compress(str.getBytes()).length());
        String compress = GzipUtil.compress(str.getBytes());
        System.out.println("压缩后内容：" + compress);
        System.out.println("解压后内容：" + GzipUtil.uncompress(compress.getBytes()));
    }
}