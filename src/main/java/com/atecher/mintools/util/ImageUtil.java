package com.atecher.mintools.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2015/8/14.
 */
public class ImageUtil {
    /**
     * 为图片做圆角
     * @param image 图片对象
     * @param cornerRadius 圆角半径
     * @return
     */

    public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));

        // ... then compositing the image on top,
        // using the white shape from above as alpha source
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        g2.dispose();

        return output;
    }
    public static BufferedImage makeRoundedCorner(String imagePath, int cornerRadius){
        File img=new File(imagePath);
        if(img.exists()){
            try {
                BufferedImage icon = ImageIO.read(img);
                return makeRoundedCorner(icon,cornerRadius);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }else{
            return null;
        }

    }

}
