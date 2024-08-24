package com.openwjk.central.commons.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Objects;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/10 16:00
 */
@Slf4j
public class FileUtils {
    public static String getParentAbsolutePath(File file) {
        if (Objects.nonNull(file)) {
            String absolutePath = file.getAbsolutePath();
            String parentAbsolutePath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator));
            return parentAbsolutePath;
        }
        return "";
    }

    public static void deleteFile(String path) {
        log.info("current file path is {}", path);
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        try {
            if (file.isFile()) {
                //如果此file对象是文件的话，直接删除
                Files.delete(Paths.get(path));
                return;
            }
            //当 file是文件夹的话，先得到文件夹下对应文件的string数组 ，递归调用本身，实现深度优先删除
            String[] list = file.list();
            for (int i = 0; i < list.length; i++) {
                deleteFile(path + File.separator + list[i]);

            }
            //当把文件夹内所有文件删完后，此文件夹已然是一个空文件夹，可以使用delete()直接删除
            Files.delete(Paths.get(path));
        } catch (IOException e) {
            log.error(String.format("file delete status fail,path:%s", path), e);
        }
    }

    /**
     * 获取文件头前缀,
     */
    public static String getFileHeader(File file) {
        try (InputStream is = new FileInputStream(file)) {
            return getFileHeader(is);
        } catch (Exception e) {
            log.error("{}", e.getMessage(), e);
        }
        return null;
    }

    /**
     * 获取文件头前缀,
     */
    public static String getFileHeader(InputStream is) {
        try {
            byte[] b = new byte[20];
            is.read(b, 0, b.length);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < b.length; i++) {
                int v = b[i] & 0xFF;
                String hv = Integer.toHexString(v);
                if (hv.length() < 2) {
                    stringBuilder.append(0);
                }
                stringBuilder.append(hv);
            }
            return stringBuilder.toString().toUpperCase();
        } catch (Exception e) {
            log.error("{}", e.getMessage(), e);
        }
        return null;
    }

    public static String md5InputStream(InputStream bis) {
        byte buffer[] = new byte[2048];
        int len;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            while ((len = bis.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }
            byte[] b = digest.digest();
            return byteToHexString(b);

        } catch (Exception e) {
            return null;
        }
    }

    public static String md5File(File file) {
        byte buffer[] = new byte[2048];
        int len;
        try (FileInputStream in = new FileInputStream(file);) {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            while ((len = in.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();

            byte[] b = digest.digest();
            return byteToHexString(b);

        } catch (Exception e) {
            return null;
        }
    }

    private static String byteToHexString(byte[] tmp) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        String s;
        // 用字节表示就是 16 个字节，每个字节用 16 进制表示的话，使用两个字符，所以表示成 16 进制需要 32 个字符
        char str[] = new char[16 * 2];
        // 表示转换结果中对应的字符位置
        int k = 0;
        // 从第一个字节开始，对 MD5 的每一个字节
        for (int i = 0; i < 16; i++) {
            // 转换成 16 进制字符的转换，取第 i 个字节
            byte byte0 = tmp[i];
            // 取字节中高 4 位的数字转换, >>> 为逻辑右移，将符号位一起右移
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            // 取字节中低 4 位的数字转换
            str[k++] = hexDigits[byte0 & 0xf];
        }
        // 换后的结果转换为字符串
        s = new String(str);
        return s;
    }
}
