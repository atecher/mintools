package com.atecher.mintools.web.controller;

import com.alibaba.druid.sql.SQLUtils;
import com.atecher.mintools.util.MD5Util;
import com.atecher.mintools.web.util.Constants;
import com.atecher.mintools.web.util.HtmlCompressor;
import com.atecher.mintools.web.util.ResponseResult;
import com.atecher.mintools.web.util.WebForwardConstants;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Controller
public class DeveloperController {
    @RequestMapping(value = "/js", method = RequestMethod.GET)
    public String js() {
        return WebForwardConstants.DEV_JAVASCRIPT;
    }

    @RequestMapping(value = "/css", method = RequestMethod.GET)
    public String css() {
        return WebForwardConstants.DEV_CSS;
    }

    @RequestMapping(value = "/html", method = RequestMethod.GET)
    public String html() {
        return WebForwardConstants.DEV_HTML;
    }

    @RequestMapping(value = "/html/format", method = RequestMethod.POST)
    @ResponseBody
    public String htmlFormat(@RequestParam("content") String content) {
        Document doc = Jsoup.parse(content);
        return doc.html();
    }

    @RequestMapping(value = "/html/compress", method = RequestMethod.POST)
    @ResponseBody
    public String htmlCompress(@RequestParam("content") String content) throws Exception {
        return HtmlCompressor.compress(content);
    }

    @RequestMapping(value = "/html/clear", method = RequestMethod.POST)
    @ResponseBody
    public String htmlClear(@RequestParam("content") String content) throws Exception {
        Document doc = Jsoup.parseBodyFragment(content);
        return doc.text();
    }

    @RequestMapping(value = "/base64", method = RequestMethod.GET)
    public String base64() {
        return WebForwardConstants.DEV_BASE64;
    }

    @RequestMapping(value = "/base64/encode", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult base64Encode(@RequestParam("content") String content) throws UnsupportedEncodingException {
        String result = Base64.encodeBase64String(content.getBytes(Constants.DEFAULT_CHARSET));
        System.out.println(result);
        return new ResponseResult("success", result);
    }

    @RequestMapping(value = "/base64/decode", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult base64Decode(@RequestParam("content") String content) throws Exception {
        return new ResponseResult("success", new String(Base64.decodeBase64(content.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8));
    }

    @RequestMapping(value = "/md5", method = RequestMethod.GET)
    public String md5() {
        return WebForwardConstants.DEV_MD5;
    }

    @RequestMapping(value = "/md5/encode/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult md5Encode(@RequestParam("content") String content, @PathVariable("type") Integer type) {
        return new ResponseResult("success", MD5Util.md5(content, type));
    }

    @RequestMapping(value = "/sql", method = RequestMethod.GET)
    public String sql() {
        return WebForwardConstants.DEV_SQL;
    }

    @RequestMapping(value = "/sql/format", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult sqlFormat(@RequestParam("sql") String sql, @RequestParam("type") String type) {
        if ("mysql".equalsIgnoreCase(type)) {
            sql = SQLUtils.formatMySql(sql);
        } else if ("oracle".equalsIgnoreCase(type)) {
            sql = SQLUtils.formatOracle(sql);
        } else if ("sqlserver".equalsIgnoreCase(type)) {
            sql = SQLUtils.formatSQLServer(sql);
        }
        return new ResponseResult("success", sql);
    }

    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public String json(@RequestParam(value = "dev", required = false) String json, Model model) {
        model.addAttribute("requestJson", json);
        return WebForwardConstants.DEV_JSON;
    }

    @RequestMapping(value = "/json/format", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult formatJson(String json, boolean pettyFormat) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Object obj = mapper.readValue(json, Object.class);
            return new ResponseResult("success", pettyFormat ? mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj) :  mapper.writeValueAsString(obj));
        } catch (JsonParseException e) {
            JsonLocation location = e.getLocation();
            int column = location.getColumnNr();
            int line=location.getLineNr();
            String message=e.getOriginalMessage();
            String respMessage="不合法的JSON：<br/>位置：第"+line+"行，第"+column+"列。<br/>原因："+message;
            return new ResponseResult("error", respMessage);
        }catch (Exception e) {
            return new ResponseResult("error", e.getMessage());
        }
    }

    @RequestMapping(value = "/xml", method = RequestMethod.GET)
    public String xml() {
        return WebForwardConstants.DEV_XML;
    }

    @RequestMapping(value = "/xml/format", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult format(@RequestParam("content") String content) throws DocumentException, IOException {
        SAXReader reader = new SAXReader();
        // 注释：创建一个串的字符输入流
        StringReader in = new StringReader(content);
        org.dom4j.Document doc = reader.read(in);
        // 注释：创建输出格式
        OutputFormat formater = OutputFormat.createPrettyPrint();
        formater.setIndentSize(4);
        formater.setNewLineAfterDeclaration(false);
        // 注释：设置xml的输出编码
        formater.setEncoding("utf-8");
        // 注释：创建输出(目标)
        // 注释：创建输出(目标)
        StringWriter out = new StringWriter();
        // 注释：创建输出流
        XMLWriter writer = new XMLWriter(out, formater);
        // 注释：输出格式化的串到目标中，执行后。格式化后的串保存在out中。
        writer.write(doc);
        writer.close();
        return new ResponseResult("success", out.toString());
    }

    @RequestMapping(value = "/xml/pack", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult pack(@RequestParam("content") String content) throws DocumentException, IOException {
        SAXReader reader = new SAXReader();

        // 注释：创建一个串的字符输入流
        StringReader in = new StringReader(content);
        org.dom4j.Document doc = reader.read(in);
        // 注释：创建输出格式
        OutputFormat formater = OutputFormat.createCompactFormat();
        // 注释：设置xml的输出编码
        formater.setEncoding("utf-8");
        // 注释：创建输出(目标)
        // 注释：创建输出(目标)
        StringWriter out = new StringWriter();
        // 注释：创建输出流
        XMLWriter writer = new XMLWriter(out, formater);
        // 注释：输出格式化的串到目标中，执行后。格式化后的串保存在out中。
        writer.write(doc);
        writer.close();
        return new ResponseResult("success", out.toString());
    }

    @RequestMapping(value = "/regex", method = RequestMethod.GET)
    public String regex() {
        return WebForwardConstants.DEV_REGEX;
    }

    @RequestMapping(value = "/codeblast", method = RequestMethod.GET)
    public String codeblast() {
        return WebForwardConstants.DEV_CODEBLAST;
    }

    @RequestMapping(value = "/rest", method = RequestMethod.GET)
    public String rest() {
        return WebForwardConstants.DEV_REST;
    }

    @RequestMapping(value = "/rest", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult restClient(String url, String method) {
        ResponseEntity<String> responseEntity = null;
        RestTemplate rest = new RestTemplate();
        if ("GET".equals(method)) {
            responseEntity = rest.getForEntity(url, String.class);
        }
        return new ResponseResult("success", responseEntity);
    }

}
