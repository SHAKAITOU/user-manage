package cn.caam.gs.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

public class FileReaderUtil {

    @SuppressWarnings("unchecked")
    public static List<List<String>> readCsv(File src) {
        List<List<String>> csvData = new ArrayList<>(new ArrayList<>());

        try {

            BufferedReader br = new BufferedReader(new FileReader(src));

            String str;
            while((str = br.readLine()) != null){
                csvData.add((List<String>) CollectionUtils.arrayToList(str.split(",")));

            }

            br.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return csvData;
    }

    public static List<List<String>> readCsv(String srcStr) {

        File src = new File(srcStr);
        
        return readCsv(src);
    }
    
    public static String read(File src) {
        StringBuffer sb = new StringBuffer();
        
        try {

            BufferedReader br = new BufferedReader(new FileReader(src));

            String str;
            while((str = br.readLine()) != null){
                sb.append(str);
            }

            br.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return sb.toString();
    }
    
    public static String read(String srcStr) {

        File src = new File(srcStr);
        
        return read(src);
    }
}
