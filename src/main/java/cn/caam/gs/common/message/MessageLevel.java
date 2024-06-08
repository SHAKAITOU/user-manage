package cn.caam.gs.common.message;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MessageLevel {

    DEBUG("DEBUG"),

    INFO("INFO"),

    WARNING("WARNING"),

    ERROR("ERROR"),

    FATAL("FATAL");

    /** リザルトコードの表現。 */
    private String level;

    /**
     * リザルトコードの表現を設定します。
     *
     * @param resultNum リザルトコードに設定する数値。
     */
    private MessageLevel(String level) {
        this.level = level;
    }

    /**
     * リザルトコードの表現を返却します。
     *
     * @return　リザルトコードの数値表現。
     */
    @JsonValue
    public String toValue() {
        return level;
    }
}
