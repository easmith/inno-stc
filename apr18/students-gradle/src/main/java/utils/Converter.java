package utils;

/**
 * Created by eku on 19.04.17.
 */
public class Converter {

    public static Integer strToInteger(String str){
        Integer integer = 0;
        if (str == null) {
            return 0;
        }
        if (str.matches("\\d+")){
            integer = Integer.parseInt(str);
        }
        return integer;
    }
}
