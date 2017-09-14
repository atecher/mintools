package com.atecher.mintools.util;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeSetting {

    private int qrWidth = 512;//二维码宽度
    private int qrHeight = 512;//二维码高度
    private int logoWidth = 100;//logo宽度
    private int logoHeight = 100;//logo高度
    private String logoPath;//logo地址
    private String content;//文本内容
    private ErrorCorrectionLevel correctionLevel;//容错级别
    private int frameWidth = 4;//logo边框宽度
    private String outputPath;//输出地址
    private String color = "000000";//前景色
    private String fadingColor = "000000";//渐变色
    private String backgroundColor = "FFFFFF";//背景色
    private int fadeType = 0;//0为不渐变，1为垂直渐变，2为水平渐变，3为斜线渐变，4为反斜线渐变

    public int getQrWidth() {
        return qrWidth;
    }

    public void setQrWidth(int qrWidth) {
        this.qrWidth = qrWidth;
    }

    public int getQrHeight() {
        return qrHeight;
    }

    public void setQrHeight(int qrHeight) {
        this.qrHeight = qrHeight;
    }

    public int getLogoWidth() {
        return logoWidth;
    }

    public void setLogoWidth(int logoWidth) {
        this.logoWidth = logoWidth;
    }

    public int getLogoHeight() {
        return logoHeight;
    }

    public void setLogoHeight(int logoHeight) {
        this.logoHeight = logoHeight;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ErrorCorrectionLevel getCorrectionLevel() {
        return correctionLevel;
    }

    public void setCorrectionLevel(ErrorCorrectionLevel correctionLevel) {
        this.correctionLevel = correctionLevel;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFadingColor() {
        return fadingColor;
    }

    public void setFadingColor(String fadingColor) {
        this.fadingColor = fadingColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getFadeType() {
        return fadeType;
    }

    public void setFadeType(int fadeType) {
        this.fadeType = fadeType;
    }


}
