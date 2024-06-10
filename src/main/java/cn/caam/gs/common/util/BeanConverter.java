package cn.caam.gs.common.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class BeanConverter {
    
    /** BEAN加工処理クラス用クラス用エラーログタイトル01. */
    public static final String BEAN_UTILITY_ERROR_LOG_TITLE_01 = "ビーン変換失敗";

    /** BEAN加工処理クラス用エラーメッセージ内容01. */
    public static final String BEAN_DATE_UTILITY_ERROR_MSG_01_01 = "ビーンMapping変換を失敗しました。";

    /** BEAN加工処理クラス用エラーメッセージ内容02. */
    public static final String BEAN_DATE_UTILITY_ERROR_MSG_01_02 = "ビーンプロパティ値の書込みにを失敗しました。";

    /** BEAN加工処理クラス用エラーメッセージ内容03. */
    public static final String BEAN_DATE_UTILITY_ERROR_MSG_01_03 = "ビーンプロパティ値の読込みにを失敗しました。";

    /** ログ出力クラス初期化. */
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * 指定されたビーンオブジェクトの全てプロパティ名を取得する.
     * 標準のJava規約に準拠している、getter、setter、isのビーンメソッドを定義されたプロパティ範囲のみ。
     *
     * @param orgBean 指定されたビーンオブジェクト
     * @return プロパティ名リスト
     */
    public static List<String> getProperties(final Object orgBean) {
        return getProperties(orgBean.getClass());
    }

    /**
     * 指定されたビーンクラスの全てプロパティ名を取得する.
     * 標準のJava規約に準拠している、getter、setter、isのビーンメソッドを定義されたプロパティ範囲のみ。
     *
     * @param clazz 指定されたビーンクラス
     * @return プロパティ名リスト
     */
    public static List<String> getProperties(final Class<?> clazz) {
        List<String> properties = new ArrayList<String>();
        for (Field field : clazz.getDeclaredFields()) {

            // privateプロパティのみ
            if (Modifier.isPrivate(field.getModifiers())) {

                // 一番目文字を大文字に変換
                String name = field.getName();
                String upperCaseName = name.substring(0, 1).toUpperCase()
                        + name.substring(1);
                //  getter と setter と is メソッドを判定
                try {
                    String simpleType = field.getType().getSimpleName();
                    if (simpleType.equals("boolean")) {
                        if ((clazz.getDeclaredMethod("is" + upperCaseName) != null)
                                && (clazz.getDeclaredMethod("set" + upperCaseName,
                                        field.getType()) != null)) {
                            properties.add(name);
                        }
                    } else {
                        if ((clazz.getDeclaredMethod("get" + upperCaseName) != null)
                                && (clazz.getDeclaredMethod("set" + upperCaseName,
                                        field.getType()) != null)) {
                            properties.add(name);
                        }
                    }
                } catch (NoSuchMethodException | SecurityException e) {
                    LOGGER.error(BEAN_UTILITY_ERROR_LOG_TITLE_01, BEAN_DATE_UTILITY_ERROR_MSG_01_03, e);
                }
            }
        }

        return properties;
    }

    /**
     * 標準のJava規約に準拠している、指定したビーンオブジェクトに対してビーンプロパティ名に基づく、
     * setterメソッドを呼び出す.
     *
     * @param orgBean 指定したビーンオブジェクト
     * @param propertyName プロパティ名
     * @param value セット値
     */
    public static void invokeSetter(final Object orgBean, final String propertyName, final Object value) {
        try {
            PropertyDescriptor nameProp = new PropertyDescriptor(propertyName, orgBean.getClass());
            Method nameSetter = nameProp.getWriteMethod();
            nameSetter.invoke(orgBean, value);
        } catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            LOGGER.error(BEAN_UTILITY_ERROR_LOG_TITLE_01, BEAN_DATE_UTILITY_ERROR_MSG_01_02, e);
        }
    }

    /**
     * 標準のJava規約に準拠している、指定したビーンオブジェクトに対してビーンプロパティ名に基づく、
     * getterメソッドを呼び出す.
     *
     * @param orgBean 指定したビーンオブジェクト
     * @param propertyName プロパティ名
     * @return プロパティ値
     */
    public static Object invokeGetter(final Object orgBean, final String propertyName) {
        try {
            PropertyDescriptor nameProp = new PropertyDescriptor(propertyName, orgBean.getClass());
            Method nameGetter = nameProp.getReadMethod();
            return nameGetter.invoke(orgBean);
        } catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            LOGGER.error(BEAN_UTILITY_ERROR_LOG_TITLE_01, BEAN_DATE_UTILITY_ERROR_MSG_01_03, e);
            return null;
        }
    }
    
    /**
     * json string to bean object
     * @param <T>
     * @param params
     * @param classOfT
     * @return
     */
    public static <T> T toObject(String params, Type classOfT) {
        return new Gson().fromJson(params, classOfT);
    }
    
    /**
     * json array string to bean list
     * @param <T>
     * @param params
     * @param clazz
     * @return
     */
    public static <T> List<T> toList(String params, Class<T> clazz) {
        Type type = TypeToken.getParameterized(List.class, clazz).getType();
        return new Gson().fromJson(params, type);
    }
    
    /**
     * object to json string
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }
}
