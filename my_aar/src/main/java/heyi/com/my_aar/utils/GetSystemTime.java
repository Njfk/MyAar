package heyi.com.my_aar.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by sev-14 on 2016/10/13.
 */

public class GetSystemTime {
    public static String[] chars = new String[]
            {
                    "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                    "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                    "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
            };

    public static String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }
    public static String getTimeWithSeconds() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

    //时间戳转为时间
    public static String formatTime(Long time) {
        String str = null;
        try {
            if (time == 0) {
                return "";
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date curDate = new Date(time * 1000);//获取当前时间
            str = formatter.format(curDate);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return str;
    }
    public static String formatTimeWithSecond(Long time) {
        String str = null;
        try {
            if (time == 0) {
                return "";
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date curDate = new Date(time * 1000);//获取当前时间
            str = formatter.format(curDate);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return str;
    }

    public static String addDate(String s) {
        if (s.length() == 7) {
            s = s + "-01";
        }
        return s;
    }

    //时间转为时间戳
    public static String dateToStamp(String s) {
        if (s==null||s.equals("")){
            return "";
        }
        String res;
        try {
            SimpleDateFormat simpleDateFormat = null;
            if (s.length() == 7) {
                simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            } else {
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            }
            Date date = null;
            try {

                date = simpleDateFormat.parse(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
            long ts = date.getTime();
            res = String.valueOf(ts);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return res.substring(0, res.length() - 3);
    }

    public static String getShortUuid() {
        StringBuffer stringBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 6; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int strInteger = Integer.parseInt(str, 16);
            stringBuffer.append(chars[strInteger % 0x3E]);
        }

        return stringBuffer.toString();
    }

    public static String getSubString(String s) {
        int start = (int) (Math.random() * (s.length() - 5));
        return s.substring(start, start + 5);
    }
}
