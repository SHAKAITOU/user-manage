package cn.caam.gs.common.util;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.springframework.web.multipart.MultipartFile;

public class ImageUtil {

	public static void main(String[] args) throws IOException {
		File src = new File("C:\\shaWork\\bk\\other\\yasuku_photo\\test9.jpg");
		File trg = ImageUtil.resize(src);
		System.out.println(trg.getAbsolutePath());
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
}
