package cn.caam.gs.common.util;

import java.awt.*;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * QRCodeHelper
 *
 */
public class QRCodeUtil {
    // 默认编码方式
    private static final String DEFAULT_CHAR_SET = "UTF-8";
    // 默认二维码图片格式
    private static final String DEFAULT_FORMAT_NAME = "PNG";


    // 默认二维码宽度
    private static final int DEFAULT_QR_CODE_WIDTH = 300;
    // 默认二维码高度
    private static final int DEFAULT_QR_CODE_HEIGHT = 300;

    public static BitMatrix createBitMatrix(String content) throws  WriterException {
        return createBitMatrix(content, DEFAULT_QR_CODE_WIDTH, DEFAULT_QR_CODE_WIDTH);
    }

    /**
     * 创建BitMatrix比特矩阵
     *
     * @param content 二维码里的内容
     * @param width 二维码宽度
     * @param height 二维码高度
     * @return com.google.zxing.common.BitMatrix 二维码比特矩阵
     * @throws WriterException
     */
    public static BitMatrix createBitMatrix(String content , int width , int height) throws WriterException {
        if (width <= 0) {
            width = DEFAULT_QR_CODE_WIDTH;
        }
        if (height <= 0) {
            height = DEFAULT_QR_CODE_HEIGHT;
        }

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M); // 纠错等级L,M,Q,H
        hints.put(EncodeHintType.CHARACTER_SET, DEFAULT_CHAR_SET);// 编码utf-8
        hints.put(EncodeHintType.MARGIN, 1);  // 边距

        // 创建比特矩阵
        return new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, width, height, hints);
    }

    /**
     * 按照自定义内容、宽度、高度、纠错等级、字符集、编剧创建QR码比特矩阵。
     *
     * @param content 二维码内容
     * @param width 二维码宽度
     * @param height 二维码高度
     * @param errCorrLevel 纠错等级，可以是<code>L, M, Q, H</code>
     * @param charSet 字符集
     * @param margin 边距
     * @return BitMatrix 二维码比特矩阵
     * @throws WriterException
     */
    public static BitMatrix createQRCodeBitMatrix(String content, int width, int height,
                                                  ErrorCorrectionLevel errCorrLevel,
                                                  String charSet, int margin) throws WriterException {
        if (width <= 0) {
            width = DEFAULT_QR_CODE_WIDTH;
        }
        if (height <= 0) {
            height = DEFAULT_QR_CODE_HEIGHT;
        }
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, errCorrLevel); // 设置纠错等级
        hints.put(EncodeHintType.CHARACTER_SET, charSet); // 设置编码方式
        hints.put(EncodeHintType.MARGIN, margin); // 设置边距
        return new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
    }

    /**
     * 创建二维码，返回字节数组
     *
     * @param content 二维码里的内容
     * @param imageFormat 图片后缀名
     * @param width 二维码宽度
     * @param height 二维码高度
     * @return byte[] 二维码字节流
     */
    public static byte[] createQRCode(String content , String imageFormat , int width , int height)
            throws WriterException, IOException {
        if (imageFormat == null || imageFormat.equals("")){
            imageFormat = DEFAULT_FORMAT_NAME;
        }
        BitMatrix bitMatrix = createBitMatrix(content , width, height);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, os);
        return os.toByteArray();
    }

    /**
     * 创建二维码，返回字节数组
     *
     * @param content 二维码内容
     * @param imageFormat 二维码图像格式
     * @param width 宽度
     * @param height 高度
     * @param errCorrLevel 纠错等级
     * @param charSet 字符集
     * @param margin 边距
     * @return byte[]
     * @throws WriterException
     * @throws IOException
     */
    public static byte[] createQRCode(String content, String imageFormat, int width, int height,
                                      ErrorCorrectionLevel errCorrLevel, String charSet, int margin)
            throws WriterException, IOException {
        if (imageFormat == null || imageFormat.equals("")){
            imageFormat = DEFAULT_FORMAT_NAME;
        }
        BitMatrix bitMatrix = createQRCodeBitMatrix(content, width, height, errCorrLevel, charSet, margin);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, os);
        return os.toByteArray();
    }

    /**
     * 创建二维码，返回base64字节流
     *
     * @param content 二维码里的内容
     * @param imageFormat 图片后缀名
     * @param width 二维码宽度
     * @param height 二维码高度
     * @return byte[] base64字节数组
     */
    public static byte[] createQRCodeBase64Bytes(String content , String imageFormat , int width , int height)
            throws WriterException, IOException {
        byte[] bytes = createQRCode(content , imageFormat , width, height);
        return Base64.getEncoder().encode(bytes);
    }

    /**
     * 创建二维码，返回base64字符串
     *
     * @param content 二维码里的内容
     * @param imageFormat 图片后缀名
     * @param width 二维码宽度
     * @param height 二维码高度
     * @return String base64字符串
     */
    public static String createQRCodeBase64(String content , String imageFormat , int width , int height,
                                            String encoding) throws WriterException, IOException {
        return new String(createQRCodeBase64Bytes(content, imageFormat, width, height), encoding);
    }

    /**
     * 转换为BufferedImage
     *
     * @param bitMatrix 比特矩阵
     * @param onColor 条码颜色
     * @param offColor 背景颜色
     * @return java.awt.image.BufferedImage
     */
    public static BufferedImage toBufferedImage(BitMatrix bitMatrix, int onColor, int offColor) {
        MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(onColor, offColor);
        return MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig);
    }

    /**
     * 转换为BufferedImage
     *
     * @param bitMatrix 比特矩阵
     * @return java.awt.image.BufferedImage
     */
    public static BufferedImage toBufferedImage(BitMatrix bitMatrix) {
//        MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(0xFF000001, 0xFFFFFFFF);
        MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig();    // 无参数为白底黑码
        return MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig);
    }

    /**
     * QR码编码
     *
     * @param content 编码内容
     * @param onColor 二维码颜色
     * @param offColor 背景颜色
     * @param width 宽度
     * @param height 高度
     * @param errCorrLevel 纠错等级
     * @param charSet 字符集
     * @param margin 边距
     * @return BufferedImage
     * @throws WriterException
     */
    public static BufferedImage encodeQRCode(String content, int onColor, int offColor, int width, int height,
                                             ErrorCorrectionLevel errCorrLevel, String charSet, int margin)
            throws WriterException {
        BitMatrix bitMatrix = createQRCodeBitMatrix(content, width, height, errCorrLevel, charSet, margin);
        return toBufferedImage(bitMatrix, onColor, offColor);
    }

    /**
     * QR码编码
     *
     * @param content 编码内容
     * @param width 宽度
     * @param height 高度
     * @return BufferedImage
     * @throws WriterException
     */
    public static BufferedImage encodeQRCode(String content, int width, int height) throws WriterException {
        BitMatrix bitMatrix = createBitMatrix(content, width, height);
        return toBufferedImage(bitMatrix);
    }

    /**
     * QR码编码
     *
     * @param content 要编码的内容
     * @return BufferedImage
     * @throws WriterException
     */
    public static BufferedImage encodeQRCode(String content) throws WriterException {
        return toBufferedImage(createBitMatrix(content));
    }

    /**
     * 根据内容和设置生成二维码图片到指定路径
     *
     * @param content 二维码里的内容
     * @param imagePath 生成图片路径
     * @param imageFormat 图片格式
     * @param onColor 二维码颜色
     * @param offColor 背景颜色
     * @param width 二维码宽度
     * @param height 二维码高度
     * @param errorCorrectionLevel 纠错等级
     * @param charSet 字符集
     * @param margin 边距
     * @throws WriterException
     * @throws IOException
     */
    public static void encodeQRCode(String content, String imagePath,
                                    String imageFormat, int onColor, int offColor, int width, int height,
                                    ErrorCorrectionLevel errorCorrectionLevel, String charSet, int margin)
            throws WriterException, IOException {
        if (imageFormat == null || imageFormat.equals("")){
            imageFormat = DEFAULT_FORMAT_NAME;
        }
        BufferedImage bufferedImage = encodeQRCode(content, onColor, offColor,
                width, height, errorCorrectionLevel, charSet, margin);
        ImageIO.write(bufferedImage, imageFormat, new File(imagePath));
    }

    /**
     * 解码二维码
     *
     * @param image 二维码BufferedImage
     * @return java.lang.String
     */
    public static String decodeQRCode(BufferedImage image) throws Exception {
        if (image == null) return "";
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
        hints.put(DecodeHintType.CHARACTER_SET, DEFAULT_CHAR_SET);
        Result result = new MultiFormatReader().decode(bitmap, hints);
        return result.getText();
    }

    /**
     * 给BufferedImage添加LOGO
     *
     * @param bufferedImage
     * @param logoFile
     * @param xPos LOGO在二维码中的x坐标
     * @param yPos LOGO在二维码中的y坐标
     * @param logoWidth LOGO在二维码中的宽度
     * @param logoHeight LOGO在二维码中的高度
     * @param strokeWidth
     * @param strokeCap
     * @param strokeJoin
     * @param arcWidth
     * @param arcHeight
     * @return
     * @throws IOException
     */
    public static BufferedImage addLogoToBufferedImage(BufferedImage bufferedImage, File logoFile,
                                                       int xPos, int yPos, int logoWidth, int logoHeight,
                                                       float strokeWidth, int strokeCap, int strokeJoin,
                                                       int arcWidth, int arcHeight) throws IOException {
        BufferedImage clone = deepCopy(bufferedImage); // 深复制BufferedImage避免污染原图
        Graphics2D graphics = clone.createGraphics();

        // 读取logo图片文件
        BufferedImage logo = ImageIO.read(logoFile);

        // 开始绘制图片
        graphics.drawImage(logo, xPos, yPos, logoWidth, logoHeight, null);
        graphics.setStroke(new BasicStroke(strokeWidth, strokeCap, strokeJoin));
        graphics.setColor(Color.white);
        // 绘制圆角矩形
        graphics.drawRoundRect(xPos, yPos, logoWidth, logoHeight, arcWidth, arcHeight);
        graphics.dispose();
        clone.flush();
        return clone;
    }


    /**
     * 在QR码图像中央加入一个1/5大小的LOGO
     * @param bufferedImage QR码图像
     * @param logoFile LOGO文件
     * @param strokeWidth LOGO圆角笔宽
     * @param strokeCap
     * @param strokeJoin
     * @param arcWidth 圆角宽，0则不圆
     * @param arcHeight 圆角高，0则不圆
     * @return
     * @throws IOException
     */
    public static BufferedImage addLogoToBufferedImage(BufferedImage bufferedImage, File logoFile,
                                                       float strokeWidth, int strokeCap, int strokeJoin,
                                                       int arcWidth, int arcHeight)
            throws IOException {
        int matrixWidth = bufferedImage.getWidth();
        int matrixHeight = bufferedImage.getHeight();
        //  计算logo放置位置
        int x = matrixWidth  / 5*2;
        int y = matrixHeight / 5*2;
        int width = matrixWidth / 5;
        int height = matrixHeight / 5;
        return addLogoToBufferedImage(bufferedImage, logoFile, x, y, width, height,
                strokeWidth, strokeCap, strokeJoin, arcWidth, arcHeight);
    }

    /**
     * 给BufferedImage添加LOGO，该方法选择自网络，修改了原来按引用传递带来的副作用（会改变原来的BufferedImage）。
     *
     * @deprecated
     * @param bufferedImage
     * @param logoFile
     * @return
     * @throws IOException
     */
    public static BufferedImage addLogoToBufferedImage(BufferedImage bufferedImage, File logoFile)
            throws IOException {
        BufferedImage clone = deepCopy(bufferedImage); // 深复制BufferedImage避免污染原图
        Graphics2D graphics = clone.createGraphics();
        int matrixWidth = clone.getWidth();
        int matrixHeight = clone.getHeight();

        // 读取logo图片文件
        BufferedImage logo = ImageIO.read(logoFile);

        //  计算logo放置位置
        int x = clone.getWidth()  / 5*2;
        int y = clone.getHeight() / 5*2;
        int width = matrixWidth / 5;
        int height = matrixHeight / 5;

        // 开始绘制图片
        graphics.drawImage(logo, x, y, width, height, null);
        graphics.setStroke(new BasicStroke(5.0F, 1, 1));
        graphics.setColor(Color.white);
        graphics.drawRoundRect(x, y, width, height, 15, 15);

        graphics.dispose();
        clone.flush();
        return clone;
    }

    /**
     * 给QR码的BufferedImage添加可透明的LOGO
     *
     * @param qrcode
     * @param overlay
     * @param overlayToQRCodeRatio
     * @param overlayTransparency
     * @return
     */
    public static BufferedImage getQRCodeWithOverlay(BufferedImage qrcode, BufferedImage overlay,
                                                     float overlayToQRCodeRatio, float overlayTransparency) {
        BufferedImage scaledOverlay = scaleOverlay(qrcode, overlay,overlayToQRCodeRatio);

        Integer deltaHeight = qrcode.getHeight() - scaledOverlay.getHeight();
        Integer deltaWidth  = qrcode.getWidth()  - scaledOverlay.getWidth();

        BufferedImage combined = new BufferedImage(qrcode.getWidth(), qrcode.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D)combined.getGraphics();
        g2.drawImage(qrcode, 0, 0, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, overlayTransparency));
        g2.drawImage(scaledOverlay, Math.round(deltaWidth/2), Math.round(deltaHeight/2), null);
        return combined;
    }

    /**
     * 按比例调整LOGO
     *
     * @param qrcode
     * @param overlay
     * @param overlayToQRCodeRatio
     * @return
     */
    private static BufferedImage scaleOverlay(BufferedImage qrcode, BufferedImage overlay, float overlayToQRCodeRatio) {
        Integer scaledWidth = Math.round(qrcode.getWidth() * overlayToQRCodeRatio);
        Integer scaledHeight = Math.round(qrcode.getHeight() * overlayToQRCodeRatio);

        BufferedImage imageBuff = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics g = imageBuff.createGraphics();
        g.drawImage(overlay.getScaledInstance(scaledWidth, scaledHeight, BufferedImage.SCALE_SMOOTH),
                0, 0, new Color(0,0,0), null);
        g.dispose();
        return imageBuff;
    }

    /**
     * BufferedImage 深复制
     * @param bi
     * @return
     */
    private static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(bi.getRaster().createCompatibleWritableRaster());
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
}


