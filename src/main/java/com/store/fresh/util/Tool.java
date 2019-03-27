package com.store.fresh.util;

import org.apache.commons.lang.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.UUID;

public class Tool {

    public static boolean isWindow = isWindowsOS();

    // windows 路径分割正反斜杠皆可，不需要区分
    // Linux 只用正斜杠
    private static String DIR_SEPATRATOR = "/";

    public static void resizeImage(File srcFile, File destFile) throws IOException {

        Image srcImg = ImageIO.read(srcFile);
        BufferedImage buffImg = null;
        buffImg = new BufferedImage(87, 20, BufferedImage.TYPE_INT_RGB);
        buffImg.getGraphics().drawImage(srcImg.getScaledInstance(87, 20, Image.SCALE_SMOOTH), 0, 0, null);

        ImageIO.write(buffImg, "png", destFile);

    }

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }

    public static String getDateStr() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf.format(date);
        return str;
    }

    public static Date strToDate(String str, String format) {
        if (format == null) {
            format = "yyyy-MM-dd HH:mm:ss";// default
        }
        Date date = new Date();
        try {
            DateFormat df = new SimpleDateFormat(format);
            date = df.parse(str);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String dateToStr(Date date, String format) {
        if (format == null) {
            format = "yyyy-MM-dd HH:mm:ss";// default
        }
        String str;
        DateFormat df = new SimpleDateFormat(format);
        str = df.format(date);
        return str;
    }

    public static boolean isWindowsOS() {
        boolean isWindowsOS = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().indexOf("windows") > -1) {
            isWindowsOS = true;
        }
        return isWindowsOS;
    }

    public static boolean copyFile(File src, File dst) {
        if (!src.exists())
            return false;
        try {
            if (!dst.exists())
                dst.createNewFile();
            FileInputStream fin = new FileInputStream(src);
            FileOutputStream fout = new FileOutputStream(dst);
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = fin.read(buf)) != -1)
                fout.write(buf, 0, len);
            fout.flush();
            fout.close();
            fin.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static File createFile(String fileName) throws IOException {
        File f = new File(fileName);
        if (!f.exists()) {
            createDirs(f.getParentFile()); //
            f.createNewFile(); //
        }
        return f;
    }

    public static void createDirs(File dir) throws IOException {

        if (dir == null || dir.exists()) {
            return;
        }
        createDirs(dir.getParentFile());
        dir.mkdir();
    }

    public static void main(String[] args) {
        System.out.println(Tool.getMD5("123456"));
    }

    @SuppressWarnings("rawtypes")
    public static String hashMapToJson(HashMap<String, Integer> map) {
        String string = "{";
        for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
            Entry e = (Entry) it.next();
            string += "\"" + e.getKey() + "\":";
            string += "\"" + e.getValue() + "\",";
        }
        string = string.substring(0, string.lastIndexOf(","));
        string += "}";
        return string;
    }

    @SuppressWarnings("rawtypes")
    public static String hashMapToJsonWithString(HashMap<String, String> map) {
        String string = "{";
        for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
            Entry e = (Entry) it.next();
            string += "\"" + e.getKey() + "\":";
            string += "\"" + e.getValue() + "\",";
        }
        string = string.substring(0, string.lastIndexOf(","));
        string += "}";
        return string;
    }

    //
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    //
    public static String genLegalFilename(String originName) {
        return getUUID() + '.' + getExtensionName(originName);
    }

    // Return Example: 20140807
    public static String getYearMonthDayStr() {
        Date aDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String formattedDate = formatter.format(aDate);
        return formattedDate;
    }

    // 根据action，操作系统和时间组合路径
    public static String genUploadBaseDir(String actionDirName) {
        String savePath = "";
        if (Tool.isWindowsOS()) {
            savePath = Constants.WinPath;
        } else {
            savePath = Constants.LinuxPath;
        }

        savePath += DIR_SEPATRATOR + actionDirName + DIR_SEPATRATOR + getYearMonthDayStr();

        File f1 = new File(savePath);
        if (!f1.exists()) {
            f1.mkdirs();
        }

        return savePath;
    }

    public static String getLocalIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    public static int compareDate(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
}
