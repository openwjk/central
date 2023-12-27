package com.openwjk.central;

import com.google.common.collect.Maps;
import com.openwjk.commons.utils.Constant;
import com.openwjk.commons.utils.DateUtil;
import com.ruiyun.jvppeteer.core.Puppeteer;
import com.ruiyun.jvppeteer.core.browser.Browser;
import com.ruiyun.jvppeteer.core.browser.BrowserFetcher;
import com.ruiyun.jvppeteer.core.page.ElementHandle;
import com.ruiyun.jvppeteer.core.page.Page;
import com.ruiyun.jvppeteer.options.LaunchOptions;
import com.ruiyun.jvppeteer.options.LaunchOptionsBuilder;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author wangjunkai
 * @description
 * @date 2023/12/27 11:03
 */
public class SearchBaiduTest {
    private static final Map<Integer, String> weekMap = Maps.newHashMap();

    static {
        weekMap.put(1,"周日");
        weekMap.put(2,"周一");
        weekMap.put(3,"周二");
        weekMap.put(4,"周三");
        weekMap.put(5,"周四");
        weekMap.put(6,"周五");
        weekMap.put(7,"周六");
    }

    @Test
    void name() throws InterruptedException, IOException, ExecutionException {
        // 初始化浏览器相关
        BrowserFetcher.downloadIfNotExist(null);
        ArrayList<String> argList = new ArrayList<>();
        LaunchOptions options = new LaunchOptionsBuilder().withArgs(argList).withHeadless(false).build();
        argList.add("--no-sandbox");
        argList.add("--disable-setuid-sandbox");
        Browser browser = Puppeteer.launch(options);

        // 打开页面，开爬
        Page page = browser.newPage();
        page.goTo("https://www.baidu.com/");
        // 模拟人工搜索关键字
        ElementHandle inputField = page.$("#kw");
        inputField.type("今天");
        // 模拟人工点击搜索按钮
        ElementHandle confirmSearch = page.$("#su");
        confirmSearch.click();
        Thread.sleep(5000);
        // 获取页面所有内容(HTML格式)
        String content = page.content();
        // 解析页面元素，方便后面定位
        Document document = Jsoup.parse(content);
        // 找出我们上面说的那个class所在的div标签
        Elements elements = document.getElementsByClass("item_uMLQg");
//        // 去 class所在的div标签中找出需要的 字段信息
//        for (int i = 0; i < elements.size(); i++) {
//            Element element = elements.get(i);
//            String date = element.getElementsByClass("WA_LOG_BTN").attr("data-module");
//            System.out.println(date);
//            if (!StringUtils.equals(DateUtil.formatDate(DateUtil.getNow(), "yyyy.MM.dd"), date)) {
//                continue;
//            }
//            Elements tagEles =  element.getElementsByClass("tag_4m9Nx");
//            if(CollectionUtils.isNotEmpty(tagEles)){
//                System.out.println(tagEles.get(Constant.INT_ZERO).text());
//            }
//            System.out.println(1);
//        }
        System.out.println(findTomorrowTag(elements));
    }

    @Test
    void name2() {
        System.out.println(DateUtil.formatDate(DateUtil.plusDays(new Date(),5),"yyyy.M.d"));
    }

    private String findTomorrowTag(Elements elements) {
        for (int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i);
            String baiduDate = element.getElementsByClass("WA_LOG_BTN").attr("data-module");
            System.out.println(baiduDate);
            if (!StringUtils.equals("2024.2.4", baiduDate)) {
                continue;
            }
            String tomorrow = DateUtil.formatDate(DateUtil.getTomorrowAtStart(), DateUtil.FORMAT_DATE_NORMAL);
            int week = getDayOfWeek("2024-02-04");
            Elements tagEles = element.getElementsByClass("tag_4m9Nx");
            if (CollectionUtils.isNotEmpty(tagEles)) {
                String tag = tagEles.get(Constant.INT_ZERO).text();
                if (week == 1 || week == 7) {
                    if (StringUtils.equals("班", tag)) {
                        return "明天是" + tomorrow + "," + weekMap.get(week) + ",为补班日，记得调好闹钟哦~";
                    }
                } else {
                    if (StringUtils.equals("休", tag)) {
                        return "明天是" + tomorrow + "," + weekMap.get(week) + ",为假期休息日，记得关闭闹钟哦~";
                    }
                }
            }
        }
        return null;
    }

    /**
     * 根据前台的日期字符串"XXXX-XX-XX"返回当前的星期数
     *
     * @param sDateInput
     * @return
     */
    public static int getDayOfWeek(String sDateInput) {
        if (sDateInput == null) {
            sDateInput = DateUtil.formatDate(DateUtil.getNow(), DateUtil.FORMAT_DATE_NORMAL);
        }
        return StringtoCalendar(sDateInput).get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 根据前台的日期字符串"XXXX-XX-XX"生成一个Calendar类实例
     *
     * @param sDateInput
     * @return
     */
    public static Calendar StringtoCalendar(String sDateInput) {
        if (sDateInput == null) {
            sDateInput = DateUtil.formatDate(DateUtil.getNow(), DateUtil.FORMAT_DATE_NORMAL);
        }
        ;
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, Integer.parseInt(sDateInput.substring(0, 4)));
        c.set(Calendar.MONTH, Integer.parseInt(sDateInput.substring(5, 7)) - 1);
        c.set(Calendar.DAY_OF_MONTH,
                Integer.parseInt(sDateInput.substring(8, 10)));
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c;
    }
}
