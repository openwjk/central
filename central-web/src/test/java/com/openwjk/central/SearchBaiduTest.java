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
import lombok.SneakyThrows;
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
        weekMap.put(1, "周日");
        weekMap.put(2, "周一");
        weekMap.put(3, "周二");
        weekMap.put(4, "周三");
        weekMap.put(5, "周四");
        weekMap.put(6, "周五");
        weekMap.put(7, "周六");
    }

    /**
     * centos更新yum源 curl -o /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo
     * centos安装依赖 yum install pango.x86_64 libXcomposite.x86_64 libXcursor.x86_64 libXdamage.x86_64 libXext.x86_64 libXi.x86_64 libXtst.x86_64 cups-libs.x86_64 libXScrnSaver.x86_64 libXrandr.x86_64 GConf2.x86_64 alsa-lib.x86_64 atk.x86_64 gtk3.x86_64 nss -y
     * @throws InterruptedException
     * @throws IOException
     * @throws ExecutionException
     */
    @Test
    void name() throws InterruptedException, IOException, ExecutionException {
        // 初始化浏览器相关https://cdn.npmmirror.com/binaries/chromium-browser-snapshots/Linux_x64/494755/chrome-linux.zip
        BrowserFetcher.downloadURLs.get("chrome").put("host", "https://cdn.npmmirror.com/binaries");
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
        inputField.type("今天天气");
        // 模拟人工点击搜索按钮
        ElementHandle confirmSearch = page.$("#su");
        confirmSearch.click();
        Thread.sleep(5000);
        // 获取页面所有内容(HTML格式)
        String content = page.content();
        // 解析页面元素，方便后面定位
        Document document = Jsoup.parse(content);
        // 找出我们上面说的那个class所在的div标签
        Elements elements = document.getElementsByAttributeValue("id", "content_left");
        findTodayWeather(elements, page);
    }

    @SneakyThrows
    private void findTodayWeather(Elements elements, Page page) {
        if (CollectionUtils.isEmpty(elements)) return;
        String weather = "天气：";
        Element element = elements.get(Constant.INT_ZERO);
        Elements weatherEles = element.getElementsByClass("cu-mr-base");
        for (Element ele : weatherEles) {
            weather += ele.text() + Constant.SPACE;
        }
        for(Element ele:element.getElementsByClass("cos-col item-box_5uby1")){
            if(ele.text().contains("穿衣")){
                String href = element.getElementsByClass("cos-col item-box_5uby1").get(0).getElementsByAttribute("href").attr("href");
                page.goTo(href);
                Thread.sleep(5000);
                // 获取页面所有内容(HTML格式)
                String content = page.content();
                // 解析页面元素，方便后面定位
                Document document = Jsoup.parse(content);
                Elements ws = document.getElementsByTag("li");
                for (Element temp : ws) {
                    if (temp.text().contains("穿衣")) {
                        weather += document.getElementsByClass("sfc-weather-number-today-desc c-color-gray-a c-gap-top").text().replace("。","");
                    }
                }
            }
        }
    }

    private String findTodayTag(Elements elements) {
        String ver = "今天是" + DateUtil.formatNow(DateUtil.FORMAT_DATE_NORMAL);
        if (CollectionUtils.isNotEmpty(elements)) {
            String lunarDate = elements.get(0).getElementsByClass("date_1NCuX").text();
            if (StringUtils.isNotBlank(lunarDate)) {
                ver = ver + "，农历" + lunarDate;
            }
            Elements cuEle = elements.get(0).getElementsByClass("cu-ml-base");
            if (CollectionUtils.isNotEmpty(elements)) {
                for (Element element : cuEle) {
                    if (element.text().contains("距离")) {
                        ver = ver + "\n" + element.text();
                    }
                }
            }
            String good = elements.get(0).getElementsByClass("flex_47rSm cu-line-clamp-1").text();
            if (StringUtils.isNotBlank(good)) {
                good = good.replaceFirst("宜", "宜：");
                ver = ver + "\n" + good;
            }
            String bad = elements.get(0).getElementsByClass("flex_47rSm cu-line-clamp-1 cu-mt-sm").text();
            if (StringUtils.isNotBlank(bad)) {
                bad = bad.replaceFirst("忌", "忌：");
                ver = ver + "\n" + bad;
            }

        }
        return ver;
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
