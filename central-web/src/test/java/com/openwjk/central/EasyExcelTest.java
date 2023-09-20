package com.openwjk.central;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Lists;
//import com.openwjk.central.dao.mapper.MapDOMapperExt;
import com.openwjk.central.dao.model.MapDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author wangjunkai
 * @description
 * @date 2023/9/12 14:36
 */
@SpringBootTest(classes = BaseTest.class)
@RunWith(SpringRunner.class)
public class EasyExcelTest {
//    @Autowired
//    private MapDOMapperExt mapDOMapperExt;

    @Test
    public void getArea() {
//        String response = HttpClientUtil.httpGet("https://www.mca.gov.cn/mzsj/xzqh/2022/202201xzqh.html", null, null);
//        System.out.println(1);
        ExcelReader reader = EasyExcelFactory.read("D:/1.xlsx").build();
        List<Area> list = Lists.newArrayList();
        ReadSheet sheet = EasyExcelFactory
                .readSheet("Sheet1")
                .head(Area.class)
                .registerReadListener(new AreaListener(list))
                .build();
        reader.read(sheet);
        for (Area area : list) {
            MapDO mapDO = new MapDO();
            mapDO.setCode(area.getCode());
            mapDO.setValue(area.getValue());
            mapDO.setBizCode("IDN_AREA");
//            mapDOMapperExt.insert(mapDO);
        }
        System.out.println(1);
    }


    public static class Area {
        @ExcelProperty("code")
        private String code;
        @ExcelProperty("value")
        private String value;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class AreaListener extends AnalysisEventListener<Area> {
        List<Area> list;

        public AreaListener(List<Area> list) {
            this.list = list;
        }

        @Override
        public void invoke(Area area, AnalysisContext analysisContext) {
            area.setCode(area.getCode().trim());
            area.setValue(area.getValue().replaceAll(" ", ""));
//            System.out.println(JSON.toJSONString(area));
            list.add(area);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext analysisContext) {

        }

    }
}
