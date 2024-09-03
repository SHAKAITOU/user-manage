package cn.caam.gs.common.util;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.data.PictureType;
import com.deepoove.poi.data.Pictures;

import cn.caam.gs.common.enums.EncodingType;
import cn.caam.gs.common.exception.ShaException;

public class FileUtil {

	@SuppressWarnings("unchecked")
	public static List<List<String>> readCsv(File src) {
		List<List<String>> csvData = new ArrayList<>(new ArrayList<>());

		try {

			BufferedReader br = new BufferedReader(new FileReader(src));

			String str;
			while((str = br.readLine()) != null){
				csvData.add((List<String>) CollectionUtils.arrayToList(str.split(Pattern.quote(","))));

			}

			br.close();

		} catch (Exception e) {
			throw new ShaException(e);
		}

		return csvData;
	}
	
	@SuppressWarnings("unchecked")
	public static List<List<String>> readCsv(File src, String quoteChar) {
		List<List<String>> csvData = new ArrayList<>(new ArrayList<>());

		try {

			BufferedReader br = new BufferedReader(new FileReader(src));

			String str;
			while((str = br.readLine()) != null){
				String [] chips = str.split(Pattern.quote("\",\""));
				for (int i=0; i<chips.length; i++) {
					chips[i] = chips[i].replace(quoteChar, "");
				}
				csvData.add((List<String>) CollectionUtils.arrayToList(chips));
			}

			br.close();

		} catch (Exception e) {
			throw new ShaException(e);
		}

		return csvData;
	}

	public static List<List<String>> readCsv(String srcStr) {

		File src = new File(srcStr);
		
		return readCsv(src);
	}
	
	public static String read(File src) {
		StringBuffer sb = new StringBuffer();
		
		try(BufferedReader br = new BufferedReader(new FileReader(src))){

			String str;
			while((str = br.readLine()) != null){
				sb.append(str);
			}
		} catch (Exception e) {
			throw new ShaException(e);
		}
		
		return sb.toString();
	}
	
	public static String read(String srcStr) {

		File src = new File(srcStr);
		
		return read(src);
	}
	
	public static String read(InputStream inputStream) {

		StringBuffer sb = new StringBuffer();
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))){

			String str;
			while((str = br.readLine()) != null){
				sb.append(str);
			}
		} catch (Exception e) {
			throw new ShaException(e);
		}
		
		return sb.toString();
	}
	
	public static File save(MultipartFile multipartFile, String path, String fileName) {
		try {
			File filePath = new File(path);
			if(!filePath.exists()) {
				filePath.mkdirs();
			}
            // アップロードファイルを置く
            File uploadFile = new File(path+System.getProperty("file.separator")+fileName);
            byte[] bytes = multipartFile.getBytes();
            BufferedOutputStream uploadFileStream =
                    new BufferedOutputStream(new FileOutputStream(uploadFile));
            uploadFileStream.write(bytes);
            uploadFileStream.close();
            return uploadFile;
        } catch (Exception e) {
        	throw new ShaException(e);
        } catch (Throwable t) {
        	throw new ShaException(t);
        }
	}
	

	/**
	 * ストリームを読み込み又は書き込み時のデータバファサイズ.
	 */
	private static final int BUFFER_SIZE = 8 * 1024;

	/**
	 * 指定されたファイルパスにデフォルトエンディングUTF-8でテキストファイルを読み込む.
	 * @param fromFilePath 指定されたファイルパス
	 * @return String テキストファイル内容
	 */
	public static String readText(final String fromFilePath) {

		File src = new File(fromFilePath);

		return readText(src);
	}
	
	/**
	 * 指定されたファイルパスにデフォルトエンディングUTF-8でテキストファイルを読み込む.
	 * @param fromFilePath 指定されたファイルパス
	 * @return String テキストファイル内容
	 */
	public static List<String> readTextToLineList(final String fromFilePath) {

		File src = new File(fromFilePath);

		return readTextToLineList(src);
	}

	/**
	 * 指定されたファイルからデフォルトエンディングUTF-8でテキスト内容を読み込む.
	 *
	 * @param fromFile 指定されたファイル
	 * @return String テキストファイル内容
	 */
	public static String readText(final File fromFile) {
		try {
			return readText(new InputStreamReader(new FileInputStream(fromFile)));
		} catch (FileNotFoundException e) {
			throw new ShaException(e);
		}
	}
	
	
	/**
	 * 指定されたファイルからデフォルトエンディングUTF-8でテキスト内容を読み込む.
	 *
	 * @param fromFile 指定されたファイル
	 * @return String テキストファイル内容
	 */
	public static List<String> readTextToLineList(final File fromFile) {
		try {
			return readTextToLineList(new InputStreamReader(new FileInputStream(fromFile)));
		} catch (FileNotFoundException e) {
			throw new ShaException(e);
		}
	}

	/**
	 * 指定されたファイルパスに指定エンディングでテキストファイルを読み込む.
	 * @param fromFilePath 指定されたファイルパス
	 * @param encodingType エンディングタイプ
	 * @return String テキストファイル内容
	 */
	public static String readText(final String fromFilePath, final EncodingType encodingType) {

		File src = new File(fromFilePath);

		return readText(src, encodingType);
	}
	

	/**
	 * 指定されたファイルパスに指定エンディングでテキストファイルを読み込む.
	 * @param fromFilePath 指定されたファイルパス
	 * @param encodingType エンディングタイプ
	 * @return String テキストファイル内容
	 */
	public static List<String> readTextToLineList(final String fromFilePath, final EncodingType encodingType) {

		File src = new File(fromFilePath);

		return readTextToLineList(src, encodingType);
	}


	/**
	 * 指定されたファイルから指定エンディングでテキスト内容を読み込む.
	 *
	 * @param fromFile 指定されたファイル
	 * @param encodingType エンディングタイプ
	 * @return String テキストファイル内容
	 */
	public static String readText(final File fromFile, final EncodingType encodingType) {
		try {
			return readText(new InputStreamReader(new FileInputStream(fromFile), encodingType.getKey()));
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			throw new ShaException(e);
		}
	}
	
	/**
	 * 指定されたファイルから指定エンディングでテキスト内容を読み込む.
	 *
	 * @param fromFile 指定されたファイル
	 * @param encodingType エンディングタイプ
	 * @return String テキストファイル内容
	 */
	public static List<String> readTextToLineList(final File fromFile, final EncodingType encodingType) {
		try {
			return readTextToLineList(new InputStreamReader(new FileInputStream(fromFile), encodingType.getKey()));
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			throw new ShaException(e);
		}
	}

	

	/**
	 * 指定されたストリームからデフォルトエンディングUTF-8でテキスト内容を読み込む.
	 *
	 * @param inputStream 指定されたストリーム
	 * @return String テキストファイル内容
	 */
	public static String readText(final InputStream inputStream) {

		try {
			return readText(new InputStreamReader(inputStream, EncodingType.UTF8.getKey()));
		} catch (UnsupportedEncodingException e) {
			throw new ShaException(e);
		}
	}
	
	/**
	 * 指定されたストリームからデフォルトエンディングUTF-8でテキスト内容を読み込む.
	 *
	 * @param inputStream 指定されたストリーム
	 * @return String テキストファイル内容
	 */
	public static List<String> readTextToLineList(final InputStream inputStream) {

		try {
			return readTextToLineList(new InputStreamReader(inputStream, EncodingType.UTF8.getKey()));
		} catch (UnsupportedEncodingException e) {
			throw new ShaException(e);
		}
	}

	/**
	 *指定されたストリームから指定エンディングでテキスト内容を読み込む.
	 *
	 * @param inputStream 指定されたストリーム
	 * @param encodingType エンディングタイプ
	 * @return String テキストファイル内容
	 */
	public static String readText(final InputStream inputStream, final EncodingType encodingType) {

		try {
			return readText(new InputStreamReader(inputStream, encodingType.getKey()));
		} catch (UnsupportedEncodingException e) {
			throw new ShaException(e);
		}
	}
	

	/**
	 *指定されたストリームから指定エンディングでテキスト内容を読み込む.
	 *
	 * @param inputStream 指定されたストリーム
	 * @param encodingType エンディングタイプ
	 * @return String テキストファイル内容
	 */
	public static List<String> readTextToLineList(final InputStream inputStream, final EncodingType encodingType) {

		try {
			return readTextToLineList(new InputStreamReader(inputStream, encodingType.getKey()));
		} catch (UnsupportedEncodingException e) {
			throw new ShaException(e);
		}
	}


	/**
	 * 指定されたリーダーからテキスト内容を読み込む.
	 *
	 * @param reader 指定されたリーダー
	 * @return String テキストファイル内容
	 */
	public static String readText(final Reader reader) {
		StringBuffer sb = new StringBuffer();

		try (BufferedReader br = new BufferedReader(reader)) {

			String str;
			while ((str = br.readLine()) != null) {
				sb.append(str);
			}
		} catch (Exception e) {
			throw new ShaException(e);
		}

		return sb.toString();
	}
	
	
	/**
	 * 指定されたリーダーからテキスト内容を読み込む.
	 *
	 * @param reader 指定されたリーダー
	 * @return String テキストファイル内容
	 */
	public static List<String> readTextToLineList(final Reader reader) {
		List<String> lines = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(reader)) {

			String str;
			while ((str = br.readLine()) != null) {
				lines.add(str);
			}
		} catch (Exception e) {
			throw new ShaException(e);
		}

		return lines;
	}

	/**
	 * 指定されたテキスト内容を指定する出力先ファイルパスに出力する.
	 * @param context 指定されたテキスト内容
	 * @param toFilePath 出力先ファイルパス
	 */
	public static void write(final String context, final String toFilePath) {
		File file = new File(toFilePath);
		write(context, file);
	}

	/**
	 * 指定されたテキスト内容を指定する出力先ファイルに出力する.
	 * @param context 指定されたテキスト内容
	 * @param toFile 出力先ファイル
	 */
	public static void write(final String context, final File toFile) {

		if (!toFile.getParentFile().exists()) {
			toFile.getParentFile().mkdirs();
		}

		try (PrintWriter out = new PrintWriter(toFile)) {
			out.print(context);
		} catch (FileNotFoundException e) {
			throw new ShaException(e);
		}
	}


	/**
	 * 指定されたテキスト内容を指定するoutputStreamに出力する.
	 * @param context 指定されたテキスト内容
	 * @param outputStream 出力ストリーム
	 */
	public static void write(final String context, final OutputStream outputStream) {
		try (PrintWriter out = new PrintWriter(outputStream)) {
			out.print(context);
		}
	}

	/**
	 * 指定されたinputStreamを指定する出力先ファイルパスに出力する.
	 * @param inputStream 指定されたinputStream
	 * @param toFilePath 出力先ファイルパス
	 */
	public static void write(final InputStream inputStream, final String toFilePath) {
		File file = new File(toFilePath);
		write(inputStream, file);
	}

	/**
	 * 指定されたinputStreamを指定する出力先ファイルに出力する.
	 * @param inputStream 指定されたinputStream
	 * @param toFile 出力先ファイル
	 */
	public static void write(final InputStream inputStream, final File toFile) {

		if (!toFile.getParentFile().exists()) {
			toFile.getParentFile().mkdirs();
		}

		try (FileOutputStream stream = new FileOutputStream(toFile)) {
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				stream.write(buffer, 0, bytesRead);
			}
		} catch (IOException e) {
			throw new ShaException(e);
		}
	}

	/**
	 * 指定されたinputStreamを指定するoutputStreamに出力する.
	 * @param inputStream 指定inputStream
	 * @param outputStream 出力outputStream
	 */
	public static void write(final InputStream inputStream, final OutputStream outputStream) {
		try {
			IOUtils.copy(inputStream, outputStream);
		} catch (IOException e) {
			throw new ShaException(e);
		}
	}

	/**
	 * 指定されたzipファイルパスから、zipファイルを指定するパスに解凍する.
	 *
	 * @param zipFilePath 指定されたzipファイルパス
	 * @param destDirectory 指定するパス
	 */
	public static void unzip(final String zipFilePath, final String destDirectory) {
		try {
			unzip(new FileInputStream(zipFilePath), destDirectory);
		} catch (IOException e) {
			throw new ShaException(e);
		}
	}

	/**
	 * 指定されたzipファイルから、zipファイルを指定するパスに解凍する.
	 *
	 * @param zipFile 指定されたzipファイル
	 * @param destDirectory 指定するパス
	 */
	public static void unzip(final File zipFile, final String destDirectory) {
		try {
			unzip(new FileInputStream(zipFile), destDirectory);
		} catch (IOException e) {
			throw new ShaException(e);
		}
	}

	/**
	 * 指定されたinputStreamを指定するパスに解凍する.
	 *
	 * @param inputStream 指定されたinputStream
	 * @param destDirectory 指定するパス
	 */
	public static void unzip(final InputStream inputStream, final String destDirectory) {
		File destDir = new File(destDirectory);
		if (!destDir.exists()) {
			destDir.mkdir();
		}

		try {
			ZipInputStream zipIn = new ZipInputStream(inputStream);
			ZipEntry entry = zipIn.getNextEntry();
			// iterates over entries in the zip file
			while (entry != null) {
				String filePath = destDirectory + File.separator + entry.getName();
				if (!entry.isDirectory()) {
					// if the entry is a file, extracts it
					write(zipIn, filePath);
				} else {
					// if the entry is a directory, make the directory
					File dir = new File(filePath);
					dir.mkdir();
				}
				zipIn.closeEntry();
				entry = zipIn.getNextEntry();
			}
			zipIn.close();
		} catch (IOException e) {
			throw new ShaException(e);
		}
	}
	
	public static int[] getImageWidthAndHeight(File imgFile) {
		BufferedImage image;
		try {
			image = ImageIO.read(imgFile);
		} catch (IOException e) {
			throw new ShaException(e);
		}
		
		return new int[] {image.getWidth(), image.getHeight()};
	}
	
	public static int[] getImageWidthAndHeight(MultipartFile multipartFile) {
		BufferedImage image;
		try {
			image = ImageIO.read(multipartFile.getInputStream());
		} catch (IOException e) {
			throw new ShaException(e);
		}
		
		return new int[] {image.getWidth(), image.getHeight()};
	}
	
	public static String extractImageToBase64String (File imgPath) {
		try {
			FileInputStream imageInFile = new FileInputStream(imgPath);
            byte imageData[] = new byte[(int) imgPath.length()];
            imageInFile.read(imageData);
            imageInFile.close();
            // Converting Image byte array into Base64 String
            return Base64.encodeBase64String(imageData);
		} catch (IOException e) {
			throw new ShaException(e);
		}
	}
	
	public static String extractImageToBase64String (String ImageName) {
		File imgPath = new File(ImageName);
		try {
			FileInputStream imageInFile = new FileInputStream(imgPath);
            byte imageData[] = new byte[(int) imgPath.length()];
            imageInFile.read(imageData);
            imageInFile.close();
            // Converting Image byte array into Base64 String
            return Base64.encodeBase64String(imageData);
		} catch (IOException e) {
			throw new ShaException(e);
		}
	}
	
	public static String extractImageToBase64String (MultipartFile multipartFile) {
		try {
            byte imageData[] = IOUtils.toByteArray(multipartFile.getInputStream());
            // Converting Image byte array into Base64 String
            return Base64.encodeBase64String(imageData);
		} catch (IOException e) {
			throw new ShaException(e);
		}
	}
	
	public static String getFileExtentName(String fileName) {
		String[] chips = fileName.split(Pattern.quote("."));
		return chips[chips.length-1];
	}
	/*
	public static DataSource getMailAttachment(MultipartFile multipartFile) {
		DataSource source = new FileDataSource(multipartFile.);
	}
	*/
	
	public static void main(String[] args) throws Exception{
		// 将要替换的文字、图片写入map集合中
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("sex", "男");
        map.put("address", "北京市XX区XX路");
        PictureRenderData pictureRenderData = null;
        try {
            // 图片的处理
//            pictureRenderData = Pictures.ofStream(new FileInputStream("d:\\1686009838039_0.jpg"), PictureType.JPEG).size(150, 200).create();
        	pictureRenderData = Pictures.ofStream(new FileInputStream("d:\\证件.jpeg"), PictureType.JPEG).size(150, 200).create();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        map.put("photo", pictureRenderData);

        String wordPath = "D:\\中国针灸学会普通会员入会申请表.docx";
        // 生成的新文件保存路径
        File repotrFile = new File("D:\\new.docx");
        try {
        // 生成新的文件并写到硬盘
            XWPFTemplate.compile(wordPath).render(map, new FileOutputStream(repotrFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
	
}

