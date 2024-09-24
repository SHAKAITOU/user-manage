package cn.caam.gs.common.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

public class PdfUtil {
	public static final float DEFAULT_DPI = 105;
	
	public static File pdfToImage(InputStream input) throws Exception{
        PDDocument pdDocument = PDDocument.load(input);
        PDFRenderer renderer = new PDFRenderer(pdDocument);

        File file = File.createTempFile("invoice", ".jpg");
        
        // 构造图片
        BufferedImage img_temp = renderer.renderImageWithDPI(0, DEFAULT_DPI, ImageType.RGB);
        // 设置图片格式
        Iterator<ImageWriter> it = ImageIO.getImageWritersBySuffix("jpg");
        // 将文件写出
        ImageWriter writer = (ImageWriter) it.next();
        ImageOutputStream imageout = ImageIO.createImageOutputStream(new FileOutputStream(file));
        writer.setOutput(imageout);
        writer.write(new IIOImage(img_temp, null, null));
        img_temp.flush();
        imageout.flush();
        imageout.close();
        pdDocument.close();
        
        return file;
    }
	
	 public static void main(String[] args) throws Exception{
		 try {
			 InputStream pdfInputStream = new FileInputStream("D:\\短信模板须知.pdf");
			 File file = PdfUtil.pdfToImage(pdfInputStream);
			 System.out.println(file.getAbsolutePath());
		 }catch(Exception ex) {
			 ex.printStackTrace();
		 }
	 }

}
