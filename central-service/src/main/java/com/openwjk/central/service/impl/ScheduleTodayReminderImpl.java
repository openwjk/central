package com.openwjk.central.service.impl;

import com.google.common.collect.Maps;
import com.openwjk.central.commons.enums.QwRobotEnum;
import com.openwjk.central.commons.enums.ScheduledTaskEnum;
import com.openwjk.central.remote.dto.request.QwRobotReqDTO;
import com.openwjk.central.remote.service.QwRobotService;
import com.openwjk.central.service.service.ScheduledService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/23 21:53
 */
@Service
public class ScheduleTodayReminderImpl implements ScheduledService {
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

    @Autowired
    @Qualifier("qwRobotService")
    QwRobotService qwRobotService;

    @Override
    public ScheduledTaskEnum getCode() {
        return ScheduledTaskEnum.TODAY_REMINDER;
    }

    @Override
    public void execute(Date date) {
        String verbalTrick = getBaiduData();
        if (StringUtils.isNotBlank(verbalTrick)) {
            sendRobotMsg(verbalTrick);
        }
    }

    public void sendRobotMsg(String verbalTrick) {
        QwRobotReqDTO reqDTO = new QwRobotReqDTO();
        reqDTO.setRobotEnum(QwRobotEnum.XXW);
        reqDTO.setVerbalTrick(verbalTrick);
        qwRobotService.sendTextRobot(reqDTO);
    }

    @SneakyThrows
    private String getBaiduData() {
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
        Elements elements = document.getElementsByClass("footer_3iz2Q");
        String verbalTrick=findTodayTag(elements);
        browser.close();
        return verbalTrick;
    }

    private String findTodayTag(Elements elements) {
        String ver = "今天：" + DateUtil.formatNow(DateUtil.FORMAT_DATE_NORMAL);
        if (CollectionUtils.isNotEmpty(elements)) {
            String lunarDate = elements.get(0).getElementsByClass("date_1NCuX").text();
            if (StringUtils.isNotBlank(lunarDate)) {
                ver = ver + "\n农历：" + lunarDate;
            }
            Elements cuEle = elements.get(0).getElementsByClass("cu-ml-base");
            if (CollectionUtils.isNotEmpty(elements)) {
                for(Element element:cuEle){
                    if(element.text().contains("距离")){
                        ver = ver + "\n" + element.text().replaceFirst("距离 ","距离");
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
}
