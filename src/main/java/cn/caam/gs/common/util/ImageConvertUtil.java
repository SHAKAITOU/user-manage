package cn.caam.gs.common.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;


public class ImageConvertUtil {
	
	public static int WCOLOR = (255 << 16 | 255 << 8 | 255);
	public static int BCOLOR = (0 << 16 | 0 << 8 | 0);
	
	public static String imgBase = "C:\\shaWork\\bk\\other\\yasuku_photo\\";
	public static String imagePath = "test2.jpg";
	public static String trgImagePath = "trg_" + imagePath;

	/*
	public static void main(String[] args) throws Exception {
				
		BufferedImage image = ImageIO.read(new File(imgBase + imagePath));
		
		// step1 resize
		BufferedImage step1 = ImageUtil.resizeTo(image);
		
		ImageConvertUtil.outputImg("resize_", step1);
		
		//step2 change to black and white
		BufferedImage step2 = new BufferedImage(
				step1.getWidth(), step1.getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int y=0; y<step1.getHeight(); y++) {
			for (int x=0; x<step1.getWidth(); x++) {
				int clr = step1.getRGB(x, y);
				int red =   (clr & 0x00ff0000) >> 16;
				int green = (clr & 0x0000ff00) >> 8;
				if (red > 100 && green > 100 && green > 100) {
					step2.setRGB(x, y, WCOLOR);
				} else {
					step2.setRGB(x, y, BCOLOR);
				}
			}
		}
		
		ImageConvertUtil.outputImg("bw_", step2);
		
		//step3 cut left and right merging
		int wCnt = 0;
		int bCnt = 0;
		int startCutPoint = 0;
		int endCutPoint = 0;
		List<Integer> cutPoint = new ArrayList<Integer>();
		boolean start = false;
		for (int y=0; y<step2.getHeight(); y++) {
			wCnt = 0;
			bCnt = 0;
			for (int x=0; x<step2.getWidth(); x++) {
				Color clr = new Color(step2.getRGB(x, y));
				if (clr.equals(Color.BLACK)) {// BCLOLOR
					bCnt++;
				} else {
					wCnt++;
				}
			}
			
			if (wCnt/(float)step2.getWidth() > 0.80 && !start) {
				start = true;
				cutPoint.add(y);
			} 
			
			if(wCnt/(float)step2.getWidth() < 0.80){
				start = false;
			}
			
			
		}
		
		System.out.println("cutPoint:" + cutPoint);
		
		for(int i=0; i<cutPoint.size(); i++) {
			if (i== (cutPoint.size()-1)) {
				break;
			}
			
			BufferedImage chip = new BufferedImage(
					step2.getWidth(), (cutPoint.get(i+1) - cutPoint.get(i)), BufferedImage.TYPE_INT_RGB);
			int chipY = 0;
			for (int y=cutPoint.get(i); y<cutPoint.get(i+1); y++) {
				for (int x=0; x<step1.getWidth(); x++) {
					chip.setRGB(x, chipY, step2.getRGB(x, y));
				}
				chipY++;
			}
			
			ImageConvertUtil.outputImg(i+"chip_", chip);
		}
	}
	*/
	
	private static void outputImg(String step, BufferedImage imgageB) throws IOException {
		
		File trgFile = new File(imgBase + step + trgImagePath);
		try (ImageOutputStream ios = ImageIO.createImageOutputStream(trgFile);) {

			// 保存品質はユーザー指定に従う
			ImageWriter writer = ImageIO.getImageWritersByFormatName("jpeg").next();
			ImageWriteParam param = writer.getDefaultWriteParam();
			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			param.setCompressionQuality(1.0f);
			writer.setOutput(ios);
			writer.write(null, new IIOImage(imgageB, null, null), param);
			writer.dispose();
		}
	}
}
