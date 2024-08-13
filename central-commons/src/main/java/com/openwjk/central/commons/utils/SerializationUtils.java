package com.openwjk.central.commons.utils;

import java.io.*;

/**
 * @author wangjunkai
 * @description
 * @date 2024/8/13 17:19
 */
public class SerializationUtils {
    /**
     * 使用序列化实现对象的深拷贝。
     *
     * @param original 原始对象
     * @return 深拷贝后的新对象
     */
    public static <T> T clone(T original) {
        try {
            // 将对象写入字节流
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(original);
            out.close();

            // 从字节流中读取对象
            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            return (T) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalStateException("Unable to serialize or deserialize object", e);
        }
    }
}
