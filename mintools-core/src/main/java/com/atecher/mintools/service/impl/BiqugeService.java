package com.atecher.mintools.service.impl;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.*;

/**
 * @description:
 * @author: hanhongwei
 * @date: 2018/9/25 上午11:49
 */
public class BiqugeService {
    public static void read(String url, String base) {
        Document doc = getDocument(url);
        Elements als = Objects.requireNonNull(doc).getElementById("list").getElementsByTag("a");

        ExecutorService pool = new ThreadPoolExecutor(50, 50,0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        List list = new ArrayList();
        for (int i = 0; i < als.size(); i++) {
            String detailUrl = als.get(i).attr("abs:href");
            Callable c = new MyCallable(i, als.get(i).text(), detailUrl, base);
            try {
                Future f = pool.submit(c);
                list.add(f);
            } catch (Exception e) {
                System.out.println(als.get(i).text());
                System.out.println(detailUrl);
            }

        }
        pool.shutdown();

    }

    public static void write(String base) throws IOException {
        File dir = new File(base + "/in/");
        List<File> files = Arrays.asList(Objects.requireNonNull(dir.listFiles()));

        files.sort((o1, o2) -> {
            if (o1.isDirectory() && o2.isFile()) {
                return -1;
            }else if (o1.isFile() && o2.isDirectory()) {
                return 1;
            }else{
                return Integer.valueOf(o1.getName().replace(".txt", "")) >= Integer.valueOf(o2.getName().replace(".txt", "")) ? 1 : -1;
            }

        });

        File out = new File(base + "/out/out.txt");
        for (File f : files) {
            System.out.println(f.getName());
            FileUtils.writeStringToFile(out, FileUtils.readFileToString(f, Charset.defaultCharset()) + "\n\n", Charset.defaultCharset(), true);
        }
    }

    static class MyCallable implements Callable {
        private final int taskNum;
        private final String url;
        private final String title;
        private final String base;

        MyCallable(int taskNum, String title, String url, String base) {
            this.taskNum = taskNum;
            this.url = url;
            this.title = title;
            this.base = base;
        }

        @Override
        public Object call() throws Exception {
            System.out.println(">>>" + taskNum + "任务启动");
            Date dateTmp1 = new Date();
            String baseDir = base + "/in/";
            File f = new File(baseDir + taskNum + ".txt");
            if (!f.exists()) {
                String content = getContent(url);
                FileUtils.writeStringToFile(f, title + "\n\n", Charset.defaultCharset(), true);
                FileUtils.writeStringToFile(f, content, Charset.defaultCharset(), true);
            }
            long time = System.currentTimeMillis() - dateTmp1.getTime();
            System.out.println(">>>" + taskNum + "任务终止");
            return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";
        }
    }

    private static String getContent(String url) {
        Document doc = getDocument(url);
        String content = Objects.requireNonNull(doc).getElementById("content").html();
        String text = Jsoup.parse(content.replaceAll("(?i)<br[^>]*>", "br2nl").replaceAll("\n", "br2nl")).text();
        text = text.replace("86_86873", "");
        text = text.replace("br2nlbr2nl", "br2nl");
        text = text.replace("br2nl br2nl", "br2nl");
        text = text.replace("br2nl", "\n");
        text = text.replace("br2nl", "\n");
        text = text.replace("\n\n", "\n");
        text = text.replace(" ", "");
        text = text.replace("    ", "");
        text = text.replace("&nbsp;", "");
        text = text.replace("最快更新，无弹窗阅读请。", "");
        text = text.replace("readx();", "").trim();
        return text;
    }


    private static Document getDocument(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
