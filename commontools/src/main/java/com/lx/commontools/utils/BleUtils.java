package com.lx.commontools.utils;

import java.nio.ByteBuffer;

/**
 * 作者：karl.wei
 * 创建日期： 2018/11/12 0012
 * 邮箱：karl.wei@yscoco.com
 * QQ：2736764338
 * 类介绍：byte,string,0x 转换工具
 */
public class BleUtils {
    /**
     * 把16进制字符串转换成字节数组
     * @param hex HexString
     * @return byte[]
     */
    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toUpperCase().toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static int toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }
    /**
     * 数组转成十六进制字符串
     * @param  b byte数组
     * @return HexString
     */
    public static String toHexString(byte[] b){
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < b.length; ++i){
            buffer.append(toHexString(b[i]));
        }
        return buffer.toString().toUpperCase();
    }
    /**
     * 数组转成字符串
     * @param  b byte数组
     * @return HexString
     */
    public static String toString(byte[] b){
        return new String (b);
    }

    public static byte[] replaceZero(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] != 0x00) {
                buffer.put(bytes[i]);
            }
        }
        byte[] validByte = new byte[buffer.position()];
        buffer.get(validByte, 0, buffer.position());
        return validByte;
    }
    /**
     * byte转成十六进制字符串
     * @param  b byte
     * @return HexString
     */
    public static String toHexString(byte b){
        String s = Integer.toHexString(b & 0xFF);
        if (s.length() == 1){
            return "0" + s;
        }else{
            return s.toUpperCase();
        }
    }
}
