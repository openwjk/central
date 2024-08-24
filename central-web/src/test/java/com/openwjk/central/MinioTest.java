package com.openwjk.central;

import com.alibaba.fastjson2.JSON;
import com.openwjk.central.service.impl.MinioService;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;


/**
 * @author wangjunkai
 * @description
 * @date 2023/12/13 17:02
 */
@SpringBootTest(classes = BaseTest.class)
@RunWith(SpringRunner.class)
public class MinioTest {

    @Autowired
    @Qualifier("minioService")
    private MinioService minioService;

    @Test
    public void test() {
        System.out.println(minioService.listBuckets());
        System.out.println(1);
    }

    @Test
    public void putObject() throws FileNotFoundException {
        File file = new File("C:\\Users\\51342\\Desktop\\IMG_3934.MOV");
        minioService.putObject(new FileInputStream(file), "album", file.getName());
        System.out.println(1);
    }

    @Test
    public void getObject() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        Iterable<Result<Item>> itable = minioService.listObjects("photo", true);
        Iterator<Result<Item>> it = itable.iterator();
        int i = 1;
        while (it.hasNext()) {
            Result<Item> result = it.next();
            Item item = result.get();
            try (InputStream inputStream = minioService.getObject("photo", item.objectName())) {
                FileUtils.copyInputStreamToFile(inputStream, new File("D:\\minio\\" + i + ".png"));
            }
            i++;
            System.out.println(JSON.toJSONString(result.get()));
        }
        System.out.println(1);
    }
}
