package cn.caam.gs.common.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.util.Strings;

/**
 * 图片添加水印
 * @author qzz
 */
public class CertificateCreateUtil {

    public static void main(String[] args) throws IOException {

    	Map<String, CertificateCreateUtil.DrawOject> data = new HashMap<String, CertificateCreateUtil.DrawOject>();
    	data.put("userCode", new CertificateCreateUtil.DrawOject(390, 700, 740, AligntType.LEFT, "M122602897M"));
    	data.put("registDate", new CertificateCreateUtil.DrawOject(390, 700, 860, AligntType.LEFT, "2020-01-01"));
    	data.put("validEndDate", new CertificateCreateUtil.DrawOject(390, 700, 988, AligntType.LEFT, "2027-01-01"));
    	data.put("name", new CertificateCreateUtil.DrawOject(1175, 1490, 318, AligntType.CENTER, "乔占海"));
    	data.put("sex", new CertificateCreateUtil.DrawOject(1615, 1792, 318, AligntType.CENTER, "男"));
    	data.put("birth", new CertificateCreateUtil.DrawOject(1280, 1792, 517, AligntType.CENTER, "1980-01-01"));
    	data.put("address", new CertificateCreateUtil.DrawOject(1280, 1806, 733, AligntType.CENTER, "甘肃省陇南市西和县中医院甘肃省陇南市西和县中医院"));
    	data.put("job", new CertificateCreateUtil.DrawOject(1280, 1806, 946, AligntType.CENTER, "主治医师"));
    	data.put("userType", new CertificateCreateUtil.DrawOject(1280, 1806, 1158, AligntType.CENTER, "普通会员"));
    	try {
	         String waterMarkImage="C:\\work_gs\\document\\picture\\证件.png";
	         BufferedImage image1 = ImageIO.read(new File(waterMarkImage));
	         ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image1, "png", baos );
			data.put("photo", new CertificateCreateUtil.DrawOject(344, 284, 273, 350, baos.toByteArray()));
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	try {
    		byte[] image = QRCodeUtil.createQRCode("http://1.94.203.151/admin", "png", 320, 320);
    		data.put("qrcode", new CertificateCreateUtil.DrawOject(411, 1026, 155, 155, image));
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}

    	 // 加载模板图片
        BufferedImage image = ImageIO.read(new File("C:\\work_gs\\document\\picture\\电子会员证_模板3.png"));
    	CertificateCreateUtil.draw(image, data);
        //待存储的地址
        String tarImgPath="C:\\work_gs\\document\\picture\\电子会员证_模板3_new.png";
        // 输出图片
        FileOutputStream outImgStream = new FileOutputStream(tarImgPath);
        ImageIO.write(image, "png", outImgStream);
        System.out.println("添加水印完成");
        outImgStream.flush();
        outImgStream.close();
        
        
        data = new HashMap<String, CertificateCreateUtil.DrawOject>();
    	data.put("validStartDate", new CertificateCreateUtil.DrawOject(165, 430, 510, AligntType.LEFT, "2020-01-01"));
    	data.put("validEndDate", new CertificateCreateUtil.DrawOject(540, 800, 510, AligntType.LEFT, "2027-01-01"));
    	
    	 // 加载模板图片
        image = ImageIO.read(new File("C:\\work_gs\\document\\picture\\电子会员证_模板4.png"));
    	CertificateCreateUtil.draw(image, data);
        //待存储的地址
        tarImgPath="C:\\work_gs\\document\\picture\\电子会员证_模板4_new.png";
        // 输出图片
        outImgStream = new FileOutputStream(tarImgPath);
        ImageIO.write(image, "png", outImgStream);
        System.out.println("添加水印完成");
        outImgStream.flush();
        outImgStream.close();
    }
    
    public static BufferedImage draw(BufferedImage image, Map<String, DrawOject> data) throws IOException {
    	return draw(image, data, "宋体", 50 ,Color.black);
    }
    
    public static BufferedImage draw(BufferedImage image, Map<String, DrawOject> data, String fontName, int fontSize, Color fontColor) throws IOException {
    	// 得到图片操作对象
        Graphics2D graphics = image.createGraphics();
        //消除文字锯齿
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        //消除图片锯齿
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 设置文字的颜色为黑色
        graphics.setColor(Color.black);
        // 设置文字的字体,大小
        graphics.setFont(new Font(fontName, Font.PLAIN, 50));
        
        for (Iterator<Map.Entry<String,DrawOject>> iterator = data.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry<String,DrawOject> entry = (Map.Entry<String,DrawOject>) iterator.next();
			DrawOject drawOject = entry.getValue();
			if (drawOject.objectType == ObjectType.STRING) {
				List<DrawStringInfo> list = getDrawLineList(graphics, drawOject.startX, drawOject.endX, drawOject.startY, drawOject.alignType, drawOject.content);
		        for(DrawStringInfo drawStringInfo_:list) {
		        	graphics.drawString(drawStringInfo_.content, drawStringInfo_.startX, drawStringInfo_.startY);
		        }
			}else if (drawOject.objectType == ObjectType.IMAGE) {
				  graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
			      BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(drawOject.image));
			      //绘制水印图片  坐标为中间位置
			      graphics.drawImage(bufferedImage, drawOject.startX, drawOject.startY, drawOject.wdith, drawOject.height, null);
			}
		}
        
        graphics.dispose();
        
        return image;
    }
    
    private static DrawStringInfo getDrawLine(Graphics2D graphics, int startX, int endX, int startY, AligntType alignType, String content) {
    	int width = endX - startX;
    	int contentWidth = getWatermarkLength(content, graphics);
    	if (alignType == AligntType.CENTER && width > contentWidth) {
    		startX +=  (int)((width - contentWidth)/2);
    	}
    	
    	return new DrawStringInfo(startX, startY, content);
    }
    
    private static List<DrawStringInfo> getDrawLineList(Graphics2D graphics, int startX, int endX, int startY, AligntType alignType, String content) {
    	List<DrawStringInfo> list = new ArrayList<>();
    	int width = endX - startX;
    	int contentWidth = getWatermarkLength(content, graphics);
    	if (width > contentWidth) {
    		list.add(getDrawLine(graphics, startX, endX, startY, alignType, content));
    	}else {
    		int tempWidth = 0;
    		String tempContent = "";
    		for(int index=0; index < content.length(); index++) {
    			tempWidth += getcharWidth(content.charAt(index), graphics);
    			if (tempWidth > width) {
    				list.add(getDrawLine(graphics, startX, endX, startY, alignType, tempContent));
    				startY += getCharHeight(content.charAt(index), graphics);
    				tempWidth = getcharWidth(content.charAt(index), graphics);
    				tempContent = ""+ content.charAt(index);
    			}else {
    				tempContent += content.charAt(index);
    			}
    		}
    		if (!Strings.isBlank(tempContent)) {
    			list.add(getDrawLine(graphics, startX, endX, startY, alignType, tempContent));
    		}
    	}
    	return list;
    }
    
    public static int getCharHeight(char ch, Graphics2D g) {
    	return g.getFontMetrics(g.getFont()).getHeight();
    }
    
    public static int getcharWidth(char ch, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charWidth(ch);
    }
    
    /**
     * 获取水印文字的长度
     * @param waterMarkContent
     * @param g
     * @return
     */
    public static int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }
    
    /**
     * 图片压缩-按照固定宽高原图压缩
     * @Title : thumbnail
     * @功能描述 : TODO
     * @设定文件 : @param img 本地图片地址
     * @设定文件 : @param width 图片宽度
     * @设定文件 : @param height 图片高度
     * @设定文件 : @return
     * @设定文件 : @throws IOException
     * @返回类型 : Image
     * @throws :
     */
    public static Image thumbnail(File img, int width, int height) throws IOException {
        BufferedImage BI = ImageIO.read(img);
        Image image = BI.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = tag.getGraphics();
        g.setColor(Color.RED);
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return image;
    }
    
    static class DrawStringInfo{
    	int startX;
    	int startY;
    	String content;
		public DrawStringInfo(int startX, int startY, String content) {
			super();
			this.startX = startX;
			this.startY = startY;
			this.content = content;
		}
    }
    
    public static class DrawOject{
    	ObjectType objectType = ObjectType.STRING;
    	AligntType alignType = AligntType.LEFT;
    	int startX;
    	int endX;
    	int startY;
    	int wdith;
    	int height;
    	String content;
    	byte[] image;
		public DrawOject(int startX, int endX, int startY, AligntType alignType, String content) {
			super();
			this.objectType = ObjectType.STRING;
			this.startX = startX;
			this.endX = endX;
			this.startY = startY;
			this.alignType = alignType;
			this.content = content;
		}
		
		public DrawOject(int startX, int startY, int wdith, int height, byte[] image) {
			super();
			this.objectType = ObjectType.IMAGE;
			this.startX = startX;
			this.startY = startY;
			this.wdith = wdith;
			this.height = height;
			this.image = image;
		}
    }
    
    public enum ObjectType {
        STRING,
        IMAGE;
    }
    
    public enum AligntType {
        LEFT,
        CENTER,
        RIGHT;
    }
}