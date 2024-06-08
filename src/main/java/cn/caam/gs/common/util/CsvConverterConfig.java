package cn.caam.gs.common.util;

import org.supercsv.prefs.CsvPreference;
import org.supercsv.quote.AlwaysQuoteMode;
import org.supercsv.quote.ColumnQuoteMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import cn.caam.gs.common.enums.EncodingType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CsvConverterConfig {

    /** 囲む設定. */
    @Default
    private char quoteChar = '\"';

    /** 区切り設定. */
    @Default
    private char delimiterChar = ',';

    /** 改行設定. */
    @Default
    private String endOfLineSymbols = "\n";

    /** エンコーディングタイプ設定. */
    @Default
    private EncodingType encodingType = EncodingType.UTF8;

    /** 頭行処理指定. */
    @Default
    private boolean readOrWriteHeader = true;

    /** すべて出力項目を囲む設定. */
    @Default
    private boolean outputAlwaysQuoteMode = true;

    /** 指定項目に囲む設定. */
    @Default
    private int[] outputColumnQuote = new int[0];

    /**
     * CsvPreference設定を取得する.
     * @return CsvPreference
     */
    public CsvPreference getCsvPreference() {
        if (outputAlwaysQuoteMode) {
            return new CsvPreference.Builder(quoteChar, delimiterChar, endOfLineSymbols)
                    .useQuoteMode(new AlwaysQuoteMode())
                    .build();
        } else {
            if (null != outputColumnQuote && outputColumnQuote.length > 0) {
                return new CsvPreference.Builder(quoteChar, delimiterChar, endOfLineSymbols)
                        .useQuoteMode(new ColumnQuoteMode(outputColumnQuote))
                        .build();
            } else {
                return new CsvPreference.Builder(quoteChar, delimiterChar, endOfLineSymbols)
                        .build();
            }
        }
    }
}
