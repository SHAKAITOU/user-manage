
package cn.caam.gs.common.enums;

import lombok.Getter;

/**
 * エンコーディングタイプ列挙型.
 *
 * @version 1.0.0
 * @author 謝海涛 
 *
 *
 */
public enum EncodingType {

    /** ISO8859タイプ "8859_1". */
    ISO8859("8859_1"),

    /** UTF8タイプ "UTF-8". */
    UTF8("UTF-8"),

    /** SJISタイプ "Shift_JIS". */
    SJIS("Shift_JIS"),

    ;

    /** キー. */
    @Getter
    private String key;

    /** 私設コンストラクタ. */
    private EncodingType(final String key) {
        this.key = key;
    }

    /**
     * 指定されたキーにたいして、列挙型オブジェクトを返す.
     *
     * @param key 指定されたキー
     * @return EncodingType 列挙型オブジェクト
     */
    public static EncodingType keyOf(final String key) {
        for (EncodingType b : EncodingType.values()) {
            if (b.key.equalsIgnoreCase(key)) {
                return b;
            }
        }
        return null;
    }
}
