package com.rljc.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
/**
 * 图片工具类， 图片水印，文字水印，缩放，补白等
 * @author Carl He
 */
public class ImageUtils {
	
	private static Logger log = LoggerFactory.getLogger(ImageUtils.class);
	
	/**  
     * 给图片添加水印  
     * @param iconPath 水印图片路径  
     * @param srcImgPath 源图片路径  
     * @param targerPath 目标图片路径  
     */  
    public static void markImageByIcon(String iconPath, String srcImgPath,   
            String targerPath,int x,int y,int width,int height) {   
        markImageByIcon(iconPath, srcImgPath, targerPath, null,x,y,width,height);   
    }   
  
    /**  
     * 给图片添加水印、可设置水印图片旋转角度  
     * @param iconPath 水印图片路径  
     * @param srcImgPath 源图片路径  
     * @param targerPath 目标图片路径  
     * @param degree 水印图片旋转角度  
     */  
    public static void markImageByIcon(String iconPath, String srcImgPath,   
            String targerPath, Integer degree,int x,int y,int width,int height) {   
        OutputStream os = null;   
        try {   
        	log.info("----------------------------------进入在图片上面添加水印，iconPath："+iconPath+"。x："+x+"。y："+y+"。宽度："+width+"。高度："+height+"。生成图片："+targerPath); 
            Image srcImg = ImageIO.read(new File(srcImgPath));   
            log.info("----------------------------------获取源图片"+srcImgPath+"。获取源图片信息："+srcImg);
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);   
            // 得到画笔对象   
            Graphics2D g = buffImg.createGraphics();   
            // 设置对线段的锯齿状边缘处理   
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,   
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);   
            log.info("--------------------------进入Graphics2D写入图片---------------------");
            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);   
            if (null != degree) {   
                // 设置水印旋转   
                g.rotate(Math.toRadians(degree),   
                        (double) buffImg.getWidth() / 2, (double) buffImg   
                                .getHeight() / 2);   
            }   
            log.info("--------------------------进读取iocnPath---------------------");
            // 水印图象的路径 水印一般为gif或者png的，这样可设置透明度   
            // 得到Image对象。   
            Image img = ImageIO.read(new File(iconPath));   
            float alpha = 1f; // 透明度   
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,   
                    alpha));   
            if(width == 0){
            	width = img.getWidth(null);
            }
            if(height == 0){
            	height = img.getHeight(null);
            }
            // 表示水印图片的位置   
            g.drawImage(img, x, y,width,height, null);   
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));   
            g.dispose();   
            os = new FileOutputStream(targerPath);   
            // 生成图片   
            ImageIO.write(buffImg, "jpg", os);   
            log.info("----------------------------------图片完成添加Icon印章完成-----------------------");
        } catch (Exception e) {   
        	log.error("----------------------------------图片完成添加Icon印章失败：",e);
        } finally {   
            try {   
                if (null != os)   
                    os.close();   
            } catch (Exception e) {   
            	log.error("----------------------------------图片完成添加Icon印章完成，关闭OutputStream失败：",e);   
            }   
        }   
    }   
    
    
    /**
	 * 图片添加水印
	 * @param srcImgPath 需要添加水印的图片的路径
	 * @param outImgPath 添加水印后图片输出路径
	 * @param markContentColor 水印文字的颜色
	 * @param waterMarkContent 水印的文字
	 */
	public static void mark(String srcImgPath, String outImgPath, Color markContentColor,int x,int y,int fontSize,String waterMarkContent) {
		try {
			log.info("---------------------进入生成文字水印：源图片："+srcImgPath+"。保存图片路径："+outImgPath+"。X："+x+"。Y："+y+"。字体大小："+fontSize+"。文字："+waterMarkContent);
			Image srcImg = null;
			int srcImgWidth = 2150;
			int srcImgHeight = 3024; 
			if(StringUtils.isNotBlank(srcImgPath)){
	            // 读取原图片信息
	            File srcImgFile = new File(srcImgPath);
	            srcImg = ImageIO.read(srcImgFile);
	            srcImgWidth = srcImg.getWidth(null);
	            srcImgHeight = srcImg.getHeight(null);
			}
            // 加水印
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            if(null != srcImg){
            	g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            }else{
            	g.setBackground(Color.WHITE);
            	g.clearRect(0, 0, srcImgWidth, srcImgHeight);//通过使用当前绘图表面的背景色进行填充来清除指定的矩形。
            }
            //Font font = new Font("Courier New", Font.PLAIN, 12);
            Font font = new Font("宋体", Font.BOLD, fontSize);  
            g.setColor(markContentColor); //根据图片的背景设置水印颜色
            
            g.setFont(font);
            //int x = (srcImgWidth - getWatermarkLength(watermarkStr, g)) / 2;
            //int y = srcImgHeight / 2;
            g.drawString(waterMarkContent, x, y);
            g.dispose();
            // 输出图片
            FileOutputStream outImgStream = new FileOutputStream(outImgPath);
            ImageIO.write(bufImg, "jpg", outImgStream);
            outImgStream.flush();
            outImgStream.close();
            log.info("---------------------结束生成文字水印");
        } catch (Exception e) {
        	log.error("---------------------生成文字水印失败：",e);
        }
	}
	
	/**
	 * 获取水印文字总长度
	 * @param waterMarkContent 水印的文字
	 * @param g
	 * @return 水印文字总长度
	 */
    public int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }
    
    public static void main(String[] args) throws IOException {
    	String srcImgPath = "F:/WechatIMG298.png";   
    	String iconPath = "F:/6490018.jpg";
    	String iconPath1 = "F:/13.jpg";
    	String iconPath2 = "F:/6490018.jpg";
    	String targerPath = "F:/img_mark_icon.jpg";
    	ImageUtils.markImageByIcon(iconPath1, srcImgPath, targerPath,104,567,347,270);
	     /*try{
	    	 Thread.sleep(1000);//等待1秒
	    	 ImageUtils.markImageByIcon(iconPath2, targerPath, targerPath,480,565,330,273);
		 }catch(Exception e){
			 log.error("----------------------------------执行右边图片水印线程失败：",e);   
		 }
	     try{
	    	 Thread.sleep(1000);//等待1秒
	    	 ImageUtils.markImageByIcon(iconPath, targerPath, targerPath,104,885,130,130);
		 }catch(Exception e){
			 log.error("----------------------------------执行二维码水印线程失败：",e);   
		 }*/
    	
    }
}
