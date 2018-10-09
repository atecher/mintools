package com.atecher.mintools.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.encoder.SymbolShapeHint;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class QRCode {

    // 图片宽度的一半
    /**
     * 二维码写码器
     */
    private static final MultiFormatWriter MUTI_WRITER = new MultiFormatWriter();

    /**
     * 描述：TODO
     *
     * @throws Exception
     * @作者 mark.han
     * @日期 2014-9-28
     * @邮箱 hongwei.han@qq.com
     */
    private static void encode(QRCodeSetting qrCodeSetting) throws Exception {
        try {
            BufferedImage bufferedImage = genBarcode(qrCodeSetting);
            //如果有logo则添加
            if (qrCodeSetting.getLogoPath() != null) {
                insertImage(bufferedImage, qrCodeSetting, true);
            }
            ImageIO.write(bufferedImage, "jpg", new File(qrCodeSetting.getOutputPath()));
        } catch (IOException | WriterException e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入LOGO
     *
     * @param source
     * @param qrCodeSetting
     * @param needCompress
     * @throws Exception
     */
    private static void insertImage(BufferedImage source, QRCodeSetting qrCodeSetting, boolean needCompress) throws Exception {
        File file = new File(qrCodeSetting.getLogoPath());
        if (!file.exists()) {
            System.err.println("" + qrCodeSetting.getLogoPath() + "   该文件不存在！");
            return;
        }
        Image src = ImageIO.read(new File(qrCodeSetting.getLogoPath()));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        /**
         * 压缩LOGO
         */
        if (needCompress) {
            if (width > qrCodeSetting.getLogoWidth()) {
                width = qrCodeSetting.getLogoWidth();
            }
            if (height > qrCodeSetting.getLogoHeight()) {
                height = qrCodeSetting.getLogoHeight();
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            /**
             * 绘制缩小后的图
             */
            g.drawImage(image, 0, 0, null);
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (qrCodeSetting.getQrWidth() - width) / 2;
        int y = (qrCodeSetting.getQrHeight() - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    /**
     * 描述：生成qrcode
     *
     * @param qrCodeSetting
     * @return
     * @throws WriterException
     * @throws IOException
     * @作者 mark.han
     * @日期 2014-9-28
     * @邮箱 hongwei.han@qq.com
     */

    private static BufferedImage genBarcode(QRCodeSetting qrCodeSetting) throws WriterException {
        Hashtable<EncodeHintType, Object> hint = new Hashtable<>();
        hint.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hint.put(EncodeHintType.DATA_MATRIX_SHAPE, SymbolShapeHint.FORCE_SQUARE);
        hint.put(EncodeHintType.ERROR_CORRECTION, qrCodeSetting.getCorrectionLevel());
        // 生成二维码
        BitMatrix matrix = MUTI_WRITER.encode(qrCodeSetting.getContent(), BarcodeFormat.QR_CODE, qrCodeSetting.getQrWidth(), qrCodeSetting.getQrHeight(), hint);
        Color color = new Color(Integer.parseInt(qrCodeSetting.getColor(), 16));
        Color fadingColor = new Color(Integer.parseInt(qrCodeSetting.getFadingColor(), 16));
        int backgroundColorRGB = new Color(Integer.parseInt(qrCodeSetting.getBackgroundColor(), 16)).getRGB();

        BufferedImage image = new BufferedImage(qrCodeSetting.getQrWidth(), qrCodeSetting.getQrHeight(), BufferedImage.TYPE_INT_RGB);

        //渐变色和背景色相同，不做渐变操作
        if (fadingColor.equals(color) || qrCodeSetting.getFadeType() == 0) {
            for (int y = 0; y < matrix.getHeight(); y++) {
                for (int x = 0; x < matrix.getWidth(); x++) {
                    image.setRGB(x, y, matrix.get(x, y) ? color.getRGB() : backgroundColorRGB);
                }
            }
        } else {//渐变色和背景色不同，执行渐变操作
            double color_red = color.getRed();
            double color_green = color.getGreen();
            double color_blue = color.getBlue();
            double fade_red = fadingColor.getRed();
            double fade_green = fadingColor.getGreen();
            double fade_blue = fadingColor.getBlue();
            if (qrCodeSetting.getFadeType() == 1) {
                for (int y = 0; y < matrix.getHeight(); y++) {
                    for (int x = 0; x < matrix.getWidth(); x++) {
                        //二维码颜色
                        double red = color_red - (color_red - fade_red) / matrix.getHeight() * (y + 1);
                        double green = color_green - (color_green - fade_green) / matrix.getHeight() * (y + 1);
                        double blue = color_blue - (color_blue - fade_blue) / matrix.getHeight() * (y + 1);
                        Color color1 = new Color((int) red, (int) green, (int) blue);
                        // 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；
                        image.setRGB(x, y, matrix.get(x, y) ? color1.getRGB() : backgroundColorRGB);
                    }
                }
            } else if (qrCodeSetting.getFadeType() == 2) {
                for (int y = 0; y < matrix.getHeight(); y++) {
                    for (int x = 0; x < matrix.getWidth(); x++) {
                        //二维码颜色
                        double red = color_red - (color_red - fade_red) / matrix.getWidth() * (x + 1);
                        double green = color_green - (color_green - fade_green) / matrix.getWidth() * (x + 1);
                        double blue = color_blue - (color_blue - fade_blue) / matrix.getWidth() * (x + 1);
                        Color color1 = new Color((int) red, (int) green, (int) blue);
                        // 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；
                        image.setRGB(x, y, matrix.get(x, y) ? color1.getRGB() : backgroundColorRGB);
                    }
                }
            } else if (qrCodeSetting.getFadeType() == 3) {
                for (int y = 0; y < matrix.getHeight(); y++) {
                    for (int x = 0; x < matrix.getWidth(); x++) {
                        //二维码颜色
                        double red = color_red - ((color_red - fade_red) / matrix.getHeight() * (y + 1) + (color_red - fade_red) / matrix.getWidth() * (x + 1)) / 2;
                        double green = color_green - ((color_green - fade_green) / matrix.getHeight() * (y + 1) + (color_green - fade_green) / matrix.getWidth() * (x + 1)) / 2;
                        double blue = color_blue - ((color_blue - fade_blue) / matrix.getHeight() * (y + 1) + (color_blue - fade_blue) / matrix.getWidth() * (x + 1)) / 2;
                        Color color1 = new Color((int) red, (int) green, (int) blue);
                        // 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；
                        image.setRGB(x, y, matrix.get(x, y) ? color1.getRGB() : backgroundColorRGB);
                    }
                }
            } else if (qrCodeSetting.getFadeType() == 4) {
                for (int y = 0; y < matrix.getHeight(); y++) {
                    for (int x = 0; x < matrix.getWidth(); x++) {
                        //二维码颜色
                        double red = color_red - (color_red - fade_red) / matrix.getHeight() * (y + 1) + fade_red - (fade_red - color_red) / matrix.getWidth() * (x + 1);
                        double green = color_green - (color_green - fade_green) / matrix.getHeight() * (y + 1) + fade_green - (fade_green - color_green) / matrix.getWidth() * (x + 1);
                        double blue = color_blue - (color_blue - fade_blue) / matrix.getHeight() * (y + 1) + fade_blue - (fade_blue - color_blue) / matrix.getWidth() * (x + 1);
                        Color color1 = new Color((int) red / 2, (int) green / 2, (int) blue / 2);
                        // 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；
                        image.setRGB(x, y, matrix.get(x, y) ? color1.getRGB() : backgroundColorRGB);
                    }
                }
            } else if (qrCodeSetting.getFadeType() == 5) {
                int c_y = matrix.getHeight() / 2;
                int c_x = matrix.getWidth() / 2;
                int radius = c_y > c_x ? c_x : c_y;
                for (int y = 0; y < matrix.getHeight(); y++) {
                    for (int x = 0; x < matrix.getWidth(); x++) {
                        //二维码颜色
                        double red = color_red - (color_red - fade_red) / radius * getResult(radius, c_x, c_y, x, y);
                        double green = color_green - (color_green - fade_green) / radius * getResult(radius, c_x, c_y, x, y);
                        double blue = color_blue - (color_blue - fade_blue) / radius * getResult(radius, c_x, c_y, x, y);
                        Color color1 = new Color((int) red, (int) green, (int) blue);
                        // 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；
                        image.setRGB(x, y, matrix.get(x, y) ? color1.getRGB() : backgroundColorRGB);

                    }
                }
            }
        }
        return image;
    }

    private static double getResult(int radius, int c_x, int c_y, int x, int y) {
        double len = Math.sqrt(Math.pow(x - c_x, 2) + Math.pow(y - c_y, 2));
        if (len > radius) {
            len = radius;
        }
        return len;
    }

    public static void main(String[] args) throws Exception {
        QRCodeSetting codeSetting = new QRCodeSetting();
        codeSetting.setCorrectionLevel(ErrorCorrectionLevel.H);
        String contents = "中文测试.baidu.com/";
        codeSetting.setContent(contents);
        codeSetting.setFrameWidth(0);
        codeSetting.setLogoPath("D:\\a\\md5.png");
        codeSetting.setOutputPath("D:\\a\\2013-01.jpg");
        codeSetting.setColor("000000");
        codeSetting.setFadingColor("72d0eb");
        codeSetting.setFadeType(3);
        QRCode.encode(codeSetting);
    }

}