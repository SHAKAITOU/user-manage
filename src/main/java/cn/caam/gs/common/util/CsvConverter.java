package cn.caam.gs.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.mygreen.supercsv.io.CsvAnnotationBeanReader;
import com.github.mygreen.supercsv.io.CsvAnnotationBeanWriter;

public class CsvConverter {

    /** ログ出力クラス初期化. */
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /** 配置設定情報. */
    private CsvConverterConfig config;

    /**
     * コンストラクタ.
     */
    public CsvConverter() {
        this.config = new CsvConverterConfig();
    }

    /**
     * コンストラクタ.<br>
     * CSVファイル頭行あるかチェック
     * @param readOrWriteHeader true:頭行あり;false:頭行なし
     *
     */
    public CsvConverter(final boolean readOrWriteHeader) {
        this.config = new CsvConverterConfig();
        this.config.setReadOrWriteHeader(readOrWriteHeader);
    }

    /**
     * コンストラクタ.<br>
     * 配置設定情報引数あり
     * @param config 配置設定情報
     */
    public CsvConverter(final CsvConverterConfig config) {
        this.config = config;
    }

    /**
     * CSVファイルを読み込む.
     * @param <T> 変換格納ビーンリスト
     * @param csvFilePath 読み込み元CSVファイルパス
     * @param csvBeanClass 変換格納ビーンクラスタイプ
     * @return
     */
    public <T> List<T> read(final String csvFilePath, final Class<T> csvBeanClass) {
        return read(new File(csvFilePath), csvBeanClass);
    }

    /**
     * CSVファイルを読み込む.
     * @param <T> 変換格納ビーンリスト
     * @param csvFile 読み込み元CSVファイル
     * @param csvBeanClass 変換格納ビーンクラスタイプ
     * @return
     */
    public <T>  List<T> read(final File csvFile, final Class<T> csvBeanClass) {
        try {
            return read(new FileInputStream(csvFile), csvBeanClass);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    /**
     * CSVファイルを読み込む.
     * @param <T> 変換格納ビーンリスト
     * @param csvInputStream 読み込み元CSVデータストリーム
     * @param csvBeanClass 変換格納ビーンクラスタイプ
     * @return
     */
    public <T> List<T> read(final InputStream csvInputStream, final Class<T> csvBeanClass) {
        List<T> resultList = new ArrayList<>();

        try (CsvAnnotationBeanReader<T> listReader = new CsvAnnotationBeanReader<T>(
                csvBeanClass, new InputStreamReader(csvInputStream, config.getEncodingType().getKey()),
                config.getCsvPreference());) {

            if (config.isReadOrWriteHeader()) {
                listReader.getHeader(config.isReadOrWriteHeader());
            }

            resultList = listReader.readAll();

        } catch (IOException e) {
            return null;
        }

        return resultList;
    }

    /**
     * CSVファイルを書き込む.
     * @param <T> 書き込む元CSVデータリスト
     * @param csvData 書き込む元CSVデータリスト
     * @param toFilePath 書き込む先CSVファイルパス
     */
    public <T> void write(final List<T> csvData, final String toFilePath) {

        FileUtil.write(buildWriter(csvData).toString(), toFilePath);
    }

    /**
     * CSVファイルを書き込む.
     * @param <T> 書き込む元CSVデータリスト
     * @param csvData 書き込む元CSVデータリスト
     * @param toFile 書き込む先CSVファイル
     */
    public <T> void write(final List<T> csvData, final File toFile) {

        FileUtil.write(buildWriter(csvData).toString(), toFile);
    }

    /**
     * CSVファイルを書き込む.
     * @param <T> 書き込む元CSVデータリスト
     * @param csvData 書き込む元CSVデータリスト
     * @param outputStream 書き込む先出力ストリーム
     */
    public <T> void write(final List<T> csvData, final OutputStream outputStream) {

        FileUtil.write(buildWriter(csvData).toString(), outputStream);
    }

    /**
     * CSVデータコンテキストを書き込む.
     * @param <T> 書き込む元CSVデータリスト
     * @param csvData 書き込む元CSVデータリスト
     * @return 書き込むコンテキスト
     */
    private <T> StringWriter buildWriter(final List<T> csvData) {
        StringWriter strWriter = new StringWriter();

        if (null != csvData && csvData.size() > 0) {
            @SuppressWarnings("unchecked")
            Class<T> clazz = (Class<T>) csvData.get(0).getClass();
            try (CsvAnnotationBeanWriter<T> csvWriter =
                    new CsvAnnotationBeanWriter<T>(clazz,
                        strWriter, config.getCsvPreference());) {

                if (config.isReadOrWriteHeader()) {
                    csvWriter.writeHeader();
                }

                csvWriter.writeAll(csvData);

            } catch (IOException e) {
                return null;
            }
        }

        return strWriter;
    }
}
