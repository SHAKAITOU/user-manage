package cn.caam.gs.common.util;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import com.idrsolutions.image.png.PngCompressor;


public class ImageUtil {
	private static int srcWidth;
    private static int srcHeight;
    private static int scaleWidth;
    static double support = 3.0;
    static double[] contrib;
    static double[] normContrib;
    static double[] tmpContrib;
    static int startContrib, stopContrib;
    static int nDots;
    static int nHalfDots;

	public static void main(String[] args) throws IOException {
//		File src = new File("C:\\shaWork\\bk\\other\\yasuku_photo\\test9.jpg");
//		File trg = ImageUtil.resize(src);
//		System.out.println(trg.getAbsolutePath());
		
		//缩放模式：jpg模式图像质量不好，png模式图像质量高。这里只是一个计算模式，不是图片后缀名
    	String type = "png";
    	//String type = "jpg";
    	
    	String imageName = "D:\\Photos\\1.png";
//    	String imageName = "D:\\Photos\\photo.jpeg";
    	String newImageName = imageName.substring(0, imageName.lastIndexOf("."))+"-new."+imageName.substring(imageName.lastIndexOf(".")+1);
    	
        BufferedImage image1 = ImageIO.read(new File(imageName));
        int w = 1000;
        int h = 1000;
        boolean keepScale = true;//是否保持长宽比
        BufferedImage image2 = ImageUtil.imageReSize(image1, w, h, type, keepScale);
        ImageIO.write(image2, type, new File(newImageName));
        
        if("png".equals(type)){
        	//png图像无损压缩（不改变分辨率）
        	PngCompressor.compress(new File(newImageName), new File(newImageName));
        }
	}
	
	public static InputStream toInputStream(BufferedImage image) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( image, "jpg", baos );
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			
			// byte配列をInputStreamに変換
			return new ByteArrayInputStream(imageInByte);
		} catch (IOException e) {
			return null;
		}
	}

	public static File resize(MultipartFile src) throws IOException {
		return resize(ImageIO.read(src.getInputStream()));
	}

	public static File resize(File src) throws IOException {
		return resize(ImageIO.read(src));
	}
	
	public static File resize(File src, File destFile) throws IOException {
		return resize(ImageIO.read(src), destFile);
	}

	public static File resize(BufferedImage image) throws IOException {
		File trgFile = File.createTempFile(DateUtility.getCurrentDateTime(), ".jpeg");
		return resize(image, trgFile);
	}
	
	public static File resize(BufferedImage image, File destFile) throws IOException {
		try (ImageOutputStream ios = ImageIO.createImageOutputStream(destFile);) {
			BufferedImage destImage = resizeTo(image);
			// 保存品質はユーザー指定に従う
			ImageWriter writer = ImageIO.getImageWritersByFormatName("jpeg").next();
			ImageWriteParam param = writer.getDefaultWriteParam();
			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			param.setCompressionQuality(1.0f);
			writer.setOutput(ios);
			writer.write(null, new IIOImage(destImage, null, null), param);
			writer.dispose();
			return destFile;
		}
	}
	
	public static BufferedImage resizeTo(File src) throws IOException {
		BufferedImage image = ImageIO.read(src);
		return resizeTo(image);
	}
	
	public static BufferedImage resizeTo(BufferedImage image) throws IOException {
		double scale = countScale(image.getWidth());
		BufferedImage destImage;
		if (scale < 1.0) {
			destImage = resizeImage(image, scale);
		} else {
			destImage = image;
		}
		
		return destImage;
	}

	public static BufferedImage resizeImage(BufferedImage image, double scale) 
			throws IOException {
		int width = (int) (image.getWidth() * scale);
		int height = (int) (image.getHeight() * scale);
		BufferedImage resizedImage = new BufferedImage(width, height, image.getType());

		// アフィン変換でリサイズ（画質優先）
		AffineTransform transform = AffineTransform.getScaleInstance(scale, scale);
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BICUBIC);
		op.filter(image, resizedImage);

		return resizedImage;
	}

	private static double countScale(int width) {

		if (width > 400) {
			return 400/(double)width;
		}else {
			return 1.0;
		}
	}
	
	public static String resizeImageToBase64(byte[] bytes, int w, int h, String type) throws IOException{
		if (bytes == null || bytes.length == 0) {
			return null;
		}
		
		return Base64.encodeBase64String(resizeImage(bytes, w, h, type));
	}
	
	public static byte[] resize(byte[] bytes, int targetW, int targetH) throws IOException {
		InputStream input = new ByteArrayInputStream(bytes);
		ImageIO.setUseCache(false);
    	BufferedImage source = ImageIO.read(input);
    	input.close();
    	
		 double sx = (double) targetW / source.getWidth();
		 double sy = (double) targetH / source.getHeight();
		 if (sx < sy) {
	        sx = sy;
	        targetW = (int) (sx * source.getWidth());
	    } else {
	        sy = sx;
	        targetH = (int) (sy * source.getHeight());
	    }
		 
		BufferedImage dest = new BufferedImage(targetW, targetH, source.getType());

		// アフィン変換でリサイズ（画質優先）
		AffineTransform transform = AffineTransform.getScaleInstance(sx, sy);
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BICUBIC);
		op.filter(source, dest);
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
    	ImageIO.write(dest, "jpg", os);
    	
		return os.toByteArray();
	}
	
//	public static OutputStream resizeImage(byte[] bytes, int targetW, int targetH)  throws IOException{
//		boolean bolHirettsu = true;
//		InputStream input = new ByteArrayInputStream(bytes);
//		BufferedImage source = ImageIO.read(input);
//		input.close();
//	    //int type = source.getType();
//	    BufferedImage target = null;
//	    double sx = (double) targetW / source.getWidth();
//	    double sy = (double) targetH / source.getHeight();
//
//	    if ( bolHirettsu ){
//		    if (sx < sy) {
//		        sx = sy;
//		        targetW = (int) (sx * source.getWidth());
//		    } else {
//		        sy = sx;
//		        targetH = (int) (sy * source.getHeight());
//		    }
//	    }
//
//	    target = new BufferedImage(targetW, targetH, BufferedImage.TYPE_INT_RGB);
//
//	    target.getGraphics().drawImage(source.getScaledInstance(targetW, targetH, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
//
//	    ByteArrayOutputStream os = new ByteArrayOutputStream();
//    	ImageIO.write(target, "png", os);
//    	return os;
//	}
	
	/**
     * @param srcBufferImage	源图像
     * @param w					目标图像宽度
     * @param h					目标图像高度
     * @return BufferedImage
     */
    public static byte[] resizeImage(byte[] bytes, int w, int h, String type) throws IOException{
    	InputStream input = new ByteArrayInputStream(bytes);
    	BufferedImage newImage = imageReSize(ImageIO.read(input), w, h, type, true);
//    	input.close();
    	
//    	ByteArrayOutputStream os = new ByteArrayOutputStream();
//    	ImageIO.write(newImage, "png", os);
//    	return os.toByteArray();
    	
    	ByteArrayOutputStream os = new ByteArrayOutputStream();
    	ImageIO.write(newImage, type, os);
//    	ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
//    	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//    	
//        //png图像无损压缩（不改变分辨率）
//        PngCompressor.compress(is, outputStream);
//        is.close();
//        return os.toByteArray();
    	
    	return os.toByteArray();
    }
	/**
     * @param srcBufferImage	源图像
     * @param w					目标图像宽度
     * @param h					目标图像高度
     * @param lockScale			是否锁定宽高比例（以宽度为基准。false：否，true：是）
     * @return
     */
    public static BufferedImage imageReSize(BufferedImage srcBufferImage, int w, int h, String type, boolean lockScale) {
    	srcWidth = srcBufferImage.getWidth();
    	srcHeight = srcBufferImage.getHeight();
        scaleWidth = w;
        if (lockScale) {
            h = w * srcHeight / srcWidth;
        }
        if(w > srcWidth && h > srcHeight){
        	return srcBufferImage;
        }else if(w > srcWidth){
        	w = srcWidth;
        }else if(h > srcHeight){
        	h = srcHeight;
        }
 
        CalContrib();
        
        //单线程模式：速度较慢，内存占用小
        /*BufferedImage pbOut = HorizontalFiltering(srcBufferImage, w, type);
        BufferedImage pbFinalOut = VerticalFiltering(pbOut, h, type);*/
        
        //多线程模式：速度快，内存占用高
        BufferedImage pbOut = HorizontalFiltering2(srcBufferImage, w, type);
        BufferedImage pbFinalOut = VerticalFiltering2(pbOut, h, type);
        
        return pbFinalOut;
    }
 
 
    private static void CalContrib() {
        nHalfDots = (int) ((double) srcWidth * support / (double) scaleWidth);
        nDots = nHalfDots * 2 + 1;
        try {
            contrib = new double[nDots];
            normContrib = new double[nDots];
            tmpContrib = new double[nDots];
        } catch (Exception e) {
            System.out.println("init contrib,normContrib,tmpContrib" + e);
        }
 
        int center = nHalfDots;
        contrib[center] = 1.0;
 
        double weight = 0.0;
        int i = 0;
        for (i = 1; i <= center; i++) {
            contrib[center + i] = Lanczos(i, srcWidth, scaleWidth, support);
            contrib[center - i] = Lanczos(i, srcWidth, scaleWidth, support);
            weight += contrib[center + i];
        }
 
        weight = weight * 2 + 1.0;
        for (i = 0; i <= center; i++) {
            normContrib[i] = contrib[i] / weight;
            normContrib[center * 2 - i] = contrib[i] / weight;
        }
    }
    
    private static double Lanczos(int i, int inWidth, int outWidth, double Support) {
        double x;
        x = (double) i * (double) outWidth / (double) inWidth;
        return Math.sin(x * Math.PI) / (x * Math.PI) * Math.sin(x * Math.PI / Support) / (x * Math.PI / Support);
    }
 
    // 处理边缘
    private static void CalTempContrib(int start, int stop) { 
        double weight = 0;
        int i = 0;
        for (i = start; i <= stop; i++) {
            weight += contrib[i];
        }
        for (i = start; i <= stop; i++) {
            tmpContrib[i] = contrib[i] / weight;
        }
    }
    
    // 处理边缘
    private static double[] CalTempContrib2(int start, int stop) { 
    	double[] newContrib = new double[nDots];
    	double weight = 0;
    	int i = 0;
    	for (i = start; i <= stop; i++) {
    		weight += contrib[i];
    	}
    	for (i = start; i <= stop; i++) {
    		newContrib[i] = contrib[i] / weight;
    	}
    	return newContrib;
    }
 
    private static int GetOpacityValue(int rgbValue) {
        return (rgbValue >> 24) & 0x00ff;
    }
    
    private static int GetRedValue(int rgbValue) {
    	return (rgbValue >> 16) & 0x00ff;
    }
 
    private static int GetGreenValue(int rgbValue) {
    	return (rgbValue >> 8) & 0x00ff;
    }
 
    private static int GetBlueValue(int rgbValue) {
    	return rgbValue & 0x00ff;
    }
 
    private static int ComRGB(int opacityValue, int redValue, int greenValue, int blueValue) {
        return (opacityValue << 24) + (redValue << 16) + (greenValue << 8) + blueValue;
    }
 
    // 行水平滤波
    private static int HorizontalFilter(BufferedImage bufImg, int startX, int stopX, int start, int stop, int y, double[] pContrib) {
    	double valueRed = 0;
    	double valueGreen = 0;
        double valueBlue = 0;
        double valueOpacity = 0;
        int valueRGB = 0;
        int i, j;
        
        try {
			for (i = startX, j = start; i <= stopX; i++, j++) {
			    valueRGB = bufImg.getRGB(i, y);
			    //valueOpacity += GetOpacityValue(valueRGB) * (1 / Math.pow(2, Math.abs((startX+stopX+1)/2 - i)));
			    valueRed += GetRedValue(valueRGB) * pContrib[j];
			    valueGreen += GetGreenValue(valueRGB) * pContrib[j];
			    valueBlue += GetBlueValue(valueRGB) * pContrib[j];
			}
		} catch (Exception e) {
		}
        //valueRGB = ComRGB(Clip((int)valueOpacity), Clip((int)valueRed), Clip((int)valueGreen), Clip((int)valueBlue));
        valueRGB = ComRGB(255, Clip((int)valueRed), Clip((int)valueGreen), Clip((int)valueBlue));
        return valueRGB;
    }
 
    // 图片水平滤波
    private static BufferedImage HorizontalFiltering(BufferedImage bufImage, int iOutW, String type) {
        int dwInW = bufImage.getWidth();
        int dwInH = bufImage.getHeight();
        int value = 0;
        
        BufferedImage pbOut;
        if("png".equals(type)){
            pbOut = new BufferedImage(iOutW, dwInH, BufferedImage.TYPE_INT_ARGB);
        }else{
        	pbOut = new BufferedImage(iOutW, dwInH, BufferedImage.TYPE_INT_RGB);
        }
 
        for (int x = 0; x < iOutW; x++) {
            int startX;
            int start;
            int X = (int) (((double) x) * ((double) dwInW) / ((double) iOutW) + 0.5);
            int y = 0;
 
            startX = X - nHalfDots;
            if (startX < 0) {
                startX = 0;
                start = nHalfDots - X;
            } else {
                start = 0;
            }
 
            int stop;
            int stopX = X + nHalfDots;
            if (stopX > (dwInW - 1)) {
                stopX = dwInW - 1;
                stop = nHalfDots + (dwInW - 1 - X);
            } else {
                stop = nHalfDots * 2;
            }
 
            if (start > 0 || stop < nDots - 1) {
            	CalTempContrib(start, stop);
                for (y = 0; y < dwInH; y++) {
                    value = HorizontalFilter(bufImage, startX, stopX, start, stop, y, tmpContrib);
                    pbOut.setRGB(x, y, value);
                }
            } else {
                for (y = 0; y < dwInH; y++) {
                    value = HorizontalFilter(bufImage, startX, stopX, start, stop, y, normContrib);
                    pbOut.setRGB(x, y, value);
                }
            }
        }
        return pbOut;
    }
    
    // 图片水平滤波-多线程
    private static BufferedImage HorizontalFiltering2(final BufferedImage bufImage, final int iOutW, String type) {
    	final int dwInW = bufImage.getWidth();
    	final int dwInH = bufImage.getHeight();
    	
    	final BufferedImage pbOut;
    	if("png".equals(type)){
    		pbOut = new BufferedImage(iOutW, dwInH, BufferedImage.TYPE_INT_ARGB);
    	}else{
    		pbOut = new BufferedImage(iOutW, dwInH, BufferedImage.TYPE_INT_RGB);
    	}
    	
    	ExecutorService exe = Executors.newFixedThreadPool(4);
    	for (int x = 0; x < iOutW; x++) {
    		final int x1 = x;
    		exe.execute(new Runnable() {
				@Override
				public void run() {
					int startX;
		    		int start;
		    		int X = (int) (((double) x1) * ((double) dwInW) / ((double) iOutW) + 0.5);
		    		int y = 0;
		    		
		    		startX = X - nHalfDots;
		    		if (startX < 0) {
		    			startX = 0;
		    			start = nHalfDots - X;
		    		} else {
		    			start = 0;
		    		}
		    		
		    		int stop;
		    		int stopX = X + nHalfDots;
		    		if (stopX > (dwInW - 1)) {
		    			stopX = dwInW - 1;
		    			stop = nHalfDots + (dwInW - 1 - X);
		    		} else {
		    			stop = nHalfDots * 2;
		    		}
		    		
		    		if (start > 0 || stop < nDots - 1) {
		    			final double[] newContrib = CalTempContrib2(start, stop);
		    			for (y = 0; y < dwInH; y++) {
		    				pbOut.setRGB(x1, y, HorizontalFilter(bufImage, startX, stopX, start, stop, y, newContrib));
		    			}
		    		} else {
		    			for (y = 0; y < dwInH; y++) {
		    	    		pbOut.setRGB(x1, y, HorizontalFilter(bufImage, startX, stopX, start, stop, y, normContrib));
		    			}
		    		}
				}
    		});
    	}
    	exe.shutdown();
		while (true) {
			try {
				Thread.sleep(100L);//主线程休眠0.1秒，等待线程池运行结束，同时避免一直死循环造成CPU浪费
			} catch (InterruptedException e) {
			}
			if (exe.isTerminated()) {//线程池所有线程都结束运行
				break; 
			}
		}
    	return pbOut;
    }
 
    private static int VerticalFilter(BufferedImage pbInImage, int startY, int stopY, int start, int stop, int x, double[] pContrib) {
    	double valueRed = 0;
        double valueGreen = 0;
        double valueBlue = 0;
        double valueOpacity = 0;
        int valueRGB = 0;
        int i, j;
 
        try {
			for (i = startY, j = start; i <= stopY; i++, j++) {
			    valueRGB = pbInImage.getRGB(x, i);
			    //valueOpacity += GetOpacityValue(valueRGB) * (1 / Math.pow(2, Math.abs((startY+stopY+1)/2 - i)));
			    valueRed += GetRedValue(valueRGB) * pContrib[j];
			    valueGreen += GetGreenValue(valueRGB) * pContrib[j];
			    valueBlue += GetBlueValue(valueRGB) * pContrib[j];
			}
		} catch (Exception e) {
			for (int k = 0; k < pContrib.length; k++) {
				System.out.print(pContrib[k]+", ");
			}
			System.out.println();
		}
        //valueRGB = ComRGB(Clip((int)valueOpacity), Clip((int)valueRed), Clip((int)valueGreen), Clip((int)valueBlue));
        valueRGB = ComRGB(255, Clip((int)valueRed), Clip((int)valueGreen), Clip((int)valueBlue));
        return valueRGB;
    }
 
    //纵向滤波
    private static BufferedImage VerticalFiltering(BufferedImage pbImage, int iOutH, String type) {
        int iW = pbImage.getWidth();
        int iH = pbImage.getHeight();
        int value = 0;
        
        BufferedImage pbOut;
        if("png".equals(type)){
            pbOut = new BufferedImage(iW, iOutH, BufferedImage.TYPE_INT_ARGB);
        }else{
        	pbOut = new BufferedImage(iW, iOutH, BufferedImage.TYPE_INT_RGB);
        }
        
        for (int y = 0; y < iOutH; y++) {
            int startY;
            int start;
            int Y = (int) (((double) y) * ((double) iH) / ((double) iOutH) + 0.5);
            
            startY = Y - nHalfDots;
            if (startY < 0) {
                startY = 0;
                start = nHalfDots - Y;
            } else {
                start = 0;
            }
            
            int stop;
            int stopY = Y + nHalfDots;
            if (stopY > (int) (iH - 1)) {
                stopY = iH - 1;
                stop = nHalfDots + (iH - 1 - Y);
            } else {
                stop = nHalfDots * 2;
            }
 
            if (start > 0 || stop < nDots - 1) {
            	CalTempContrib(start, stop);
                for (int x = 0; x < iW; x++) {
                    value = VerticalFilter(pbImage, startY, stopY, start, stop, x, tmpContrib);
                    pbOut.setRGB(x, y, value);
                }
            } else {
                for (int x = 0; x < iW; x++) {
                    value = VerticalFilter(pbImage, startY, stopY, start, stop, x, normContrib);
                    pbOut.setRGB(x, y, value);
                }
            }
        }
        return pbOut;
    }
    
	//纵向滤波-多线程
    private static BufferedImage VerticalFiltering2(final BufferedImage pbImage, final int iOutH, String type) {
    	final int iW = pbImage.getWidth();
    	final int iH = pbImage.getHeight();
    	
    	final BufferedImage pbOut;
    	if("png".equals(type)){
    		pbOut = new BufferedImage(iW, iOutH, BufferedImage.TYPE_INT_ARGB);
    	}else{
    		pbOut = new BufferedImage(iW, iOutH, BufferedImage.TYPE_INT_RGB);
    	}
    	
    	ExecutorService exe = Executors.newFixedThreadPool(4);
    	for (int y = 0; y < iOutH; y++) {
    		final int y1 = y;
    		exe.execute(new Runnable() {
				@Override
				public void run() {
					int startY;
		    		int start;
		    		int Y = (int) (((double) y1) * ((double) iH) / ((double) iOutH) + 0.5);
		    		
		    		startY = Y - nHalfDots;
		    		if (startY < 0) {
		    			startY = 0;
		    			start = nHalfDots - Y;
		    		} else {
		    			start = 0;
		    		}
		    		
		    		int stop;
		    		int stopY = Y + nHalfDots;
		    		if (stopY > (int) (iH - 1)) {
		    			stopY = iH - 1;
		    			stop = nHalfDots + (iH - 1 - Y);
		    		} else {
		    			stop = nHalfDots * 2;
		    		}
		    		
		    		if (start > 0 || stop < nDots - 1) {
		    			double[] newContrib = CalTempContrib2(start, stop);
		    			for (int x = 0; x < iW; x++) {
				    		pbOut.setRGB(x, y1, VerticalFilter(pbImage, startY, stopY, start, stop, x, newContrib));
		    			}
		    		} else {
		    			for (int x = 0; x < iW; x++) {
				    		pbOut.setRGB(x, y1, VerticalFilter(pbImage, startY, stopY, start, stop, x, normContrib));
		    			}
		    		}
		    	}
			});
    	}
    	exe.shutdown();
		while (true) {
			try {
				Thread.sleep(100L);//主线程休眠0.1秒，等待线程池运行结束，同时避免一直死循环造成CPU浪费
			} catch (InterruptedException e) {
			}
			if (exe.isTerminated()) {//线程池所有线程都结束运行
				break; 
			}
		}
    	return pbOut;
    }
 
    private static int Clip(int x) {
        if (x < 0)
            return 0;
        if (x > 255)
            return 255;
        return x;
    }

}
