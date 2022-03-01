package com.lx.commontools.utils;

import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
/**
 * 创建时间：2019/11/11 17:32
 * 作者：Hyman峰
 * 功能描述：AES加密工具
 */
public class AESUtils {

    /**
     * 加密
     */
    public static byte[] encrypt(String content) {
        try {
            Log.e("encrypt","encrypt:"+content);
            IvParameterSpec zeroIv = new IvParameterSpec(AppConfig.VIPARA.getBytes());
            SecretKeySpec key = new SecretKeySpec(AppConfig.AESPassword.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
            byte[] encryptedData = cipher.doFinal(content.getBytes());
            return encryptedData; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param content 待解密内容
     * @return
     */
    public static byte[] decrypt(byte[] content) {
        try {
            IvParameterSpec zeroIv = new IvParameterSpec(AppConfig.VIPARA.getBytes());
            SecretKeySpec key = new SecretKeySpec(AppConfig.AESPassword.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
            byte[] encryptedData = cipher.doFinal(content);
            return encryptedData; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toHex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }

    private static void appendHex(StringBuffer sb, byte b) {
        final String HEX = "0123456789ABCDEF";
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }

    /**
     * 生成加密字符串
     *
     * @param params 请求
     *               参数
     */
    /**
     * 生成加密字符串
     * @param  order 网络请求命令
     * @param  appInstallId 设备注册ID
     * @param params 请求参数
     */
    public static String encryptFirsth(String order,String appInstallId,String... params){
        StringBuffer aesBuffer = new StringBuffer();
        aesBuffer.append(order+"&");
        if(params!=null){
            for(String param:params){
                aesBuffer.append((param==null?"":param)+"&");
            }
        }
        Log.e("Scoket","下发指令:"+aesBuffer.toString());
        return aesBuffer.append(AESUtils.parseByteBase64(AESUtils.encrypt((TextUtils.isEmpty(appInstallId)?"app_first_run":appInstallId)+"&"+TimeInMillsTrasToDate(null,9)))).toString();
    };

    /**
     * 将二进制转换成Base64
     */
    public static String parseByteBase64(byte[] stringBytes) {
        return Base64Utils.encode(stringBytes);
    }
    /**
     * 将String 通过base 64 转换成 byte[]
     */
    public static byte[]  decodeStringBase64(String stringBytes) {
        return Base64Utils.decode(stringBytes);
    }
    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static boolean getBoolean(String json) {
        return (json != null && json.length() > 0);
    }

    /**
     * 时间转换，
     *
     * @param time       毫秒数，为null为获取当前时间
     * @param formatType 获取的数据类型
     */
    public static String TimeInMillsTrasToDate(Long time, int formatType) {
        Date now = new Date();
        if (time == null) {
            time = now.getTime();
        }

        DateFormat formatter = null;
        switch (formatType) {
            case 1:
                formatter = new SimpleDateFormat("yyyy年MM月dd日");
                break;
            case 2:
                formatter = new SimpleDateFormat("yyyy-MM-dd");
                break;
            case 3:
                formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                break;
            case 4:
                formatter = new SimpleDateFormat("yyyy.MM.dd");
                break;
            case 5:
                formatter = new SimpleDateFormat("HH:mm");
                break;
            case 6:
                formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                break;
            case 7:
                formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
                break;
            case 8:
                formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            case 9:
                formatter = new SimpleDateFormat("yyyyMMddHHmmss");;
        }
        Calendar calendar = null;
        if (time != null) {
            calendar = Calendar.getInstance();
            calendar.setTimeInMillis(time);
        }
        return formatter.format(calendar.getTime());
    }
}
