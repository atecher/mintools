package com.atecher.mintools.util;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.Data;

@Data
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

}
