package com.lx.commontools.utils;

import android.app.Activity;
import android.os.Build;

import androidx.annotation.RequiresApi;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


/**
 * 创建时间：2018/9/11 14:52
 * 作者：Hyman峰
 * 功能描述：时间管理类
 */
public class DateUtils {


    /**
     * 返回时分秒
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getLocalHourTime(String time) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(time, fmt);
        int hour = dateTime.getHour();
        int minute = dateTime.getMinute();
        int seconds = dateTime.getSecond();
        return hour + ":" + minute + ":" + seconds;
    }

    /**
     * 判断时间大小
     * 传入：时分秒、状态
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean judgeLocalTime(String time, int state) {

        if (time.length()>5){
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime startTime = LocalTime.parse(time, fmt);
            //现在时间往后推10分钟
            LocalTime endTime = LocalTime.now().plusMinutes(10);

            //当前时间大于服药时间并且状态为0，添加未用药记录
            if (endTime.compareTo(startTime) == 1 && state == 0) {
                return true;
            }
        }else{
            time = time + ":01";
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime startTime = LocalTime.parse(time,fmt);
            //现在时间往后推10分钟
            LocalTime endTime = LocalTime.now().plusMinutes(10);
            //当前时间大于服药时间并且状态为0，添加未用药记录
            if (endTime.compareTo(startTime) == 1 && state == 0) {
                return true;
            }
        }


        return false;
    }





    /**
     * 设置每个阶段时间
     */
    private static final int seconds_of_1minute = 60;
    private static final int seconds_of_30minutes = 30 * 60;
    private static final int seconds_of_1hour = 60 * 60;
    private static final int seconds_of_1day = 24 * 60 * 60;
    private static final int seconds_of_15days = seconds_of_1day * 15;
    private static final int seconds_of_30days = seconds_of_1day * 30;
    private static final int seconds_of_6months = seconds_of_30days * 6;
    private static final int seconds_of_1year = seconds_of_30days * 12;

    /**
     * 格式化时间
     *
     * @param mTime
     * @return
     */
    public static String getTimeRange(String mTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        /**获取当前时间*/
        Date curDate = new Date(System.currentTimeMillis());
        String dataStrNew = sdf.format(curDate);
        Date startTime = null;
        try {
            /**将时间转化成Date*/
            curDate = sdf.parse(dataStrNew);
            startTime = sdf.parse(mTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        /**除以1000是为了转换成秒*/
        long between = (curDate.getTime() - startTime.getTime()) / 1000;
        int elapsedTime = (int) (between);
        if (elapsedTime < seconds_of_1minute) {
            return "刚刚";
        }
        if (elapsedTime < seconds_of_30minutes) {
            return elapsedTime / seconds_of_1minute + "分钟前";
        }
        if (elapsedTime < seconds_of_1hour) {
            return "半小时前";
        }
        if (elapsedTime < seconds_of_1day) {
            return elapsedTime / seconds_of_1hour + "小时前";
        }
        if (elapsedTime < seconds_of_15days) {
            return elapsedTime / seconds_of_1day + "天前";
        }
        if (elapsedTime < seconds_of_30days) {
            return mTime.substring(0, 10);
        }
        if (elapsedTime < seconds_of_30days) {
            return "半个月前";
        }
        if (elapsedTime < seconds_of_6months) {
            return elapsedTime / seconds_of_30days + "月前";
        }
        if (elapsedTime < seconds_of_1year) {
            return "半年前";
        }
        if (elapsedTime >= seconds_of_1year) {
            return elapsedTime / seconds_of_1year + "年前";
        }
        return "";
    }

    /**
     * 时间转换，
     *
     * @param formatType 获取的数据类型
     */
    public static String timeInMillsTrasToDate(int formatType) {
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
                formatter = new SimpleDateFormat("yyyy年MM月dd日 mm:ss");
                break;
            case 7:
                formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
                break;
            case 8:
                formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                break;
            case 9:
                formatter = new SimpleDateFormat("HH");
                break;
            case 10:
                formatter = new SimpleDateFormat("mm");
                break;
            case 11:
                formatter = new SimpleDateFormat("ss");
                break;
            case 12:
                formatter = new SimpleDateFormat("yyyy年 MM月");
                break;
            case 13:
                formatter = new SimpleDateFormat("yyyy");
                break;
            case 14:
                formatter = new SimpleDateFormat("MM");
                break;
            case 15:
                formatter = new SimpleDateFormat("dd");
                break;
            case 16:
                formatter = new SimpleDateFormat("yyyy-MM-");
                break;
            case 17:
                formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
                break;
            case 18:
                formatter = new SimpleDateFormat("HH:mm:ss");
                break;
        }
        Calendar calendar = Calendar.getInstance();
        return formatter.format(calendar.getTime());
    }

    public static int getMonth() {
        Calendar calendar = Calendar.getInstance();
        return Integer.parseInt(new SimpleDateFormat("MM").format(calendar.getTime()));
    }

    public static String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String getTimes(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    public static String getTimeMinutes(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    public static int getTimeDate(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("HH");
        return Integer.parseInt(format.format(date));
    }

//    public static String getDateFormat(long timestamp) {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss", Locale.getDefault());
//        return format.format(new Date(timestamp));
//    }
//
//    public static String getDateFormat1(long timestamp) {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd  HH:mm", Locale.getDefault());
//        return format.format(new Date(timestamp));
//    }
//
//    public static String getDateFormat2(long timestamp) {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault());
//        return format.format(new Date(timestamp));
//    }

    /**
     * 日期格式字符串转换成时间戳
     */
    public static long dateTimeStamp(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            long a = sdf.parse(date).getTime();
            return a;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 日期格式字符串转换成时间戳
     */
    public static long dateTimeStamps(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            long a = sdf.parse(date).getTime();
            return a;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }




    public static String getDateToString(long milSecond) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }
    public static String getDateToString2(long milSecond) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
    public static String getDateToStrings(long milSecond) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
//    public static String getTimess(Date date) {//可根据需要自行截取数据显示
//        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
//        return format.format(date);
//    }



//    /**
//     * 返回时分秒
//     *
//     * @param counts
//     * @return
//     */
//    public static String getDateFormat(BaseMvpActivity activity, int counts) {
//        LogUtils.e("总秒数：" + counts);
//        String timeStr;
//        int hour;
//        int minute;
//        if (counts <= 0)
//            return "";//00:00
//        else {
//            minute = counts / 60;
//            if (minute < 60) {
//                timeStr = getFormat(minute);
//            } else {
//                hour = minute / 60;
//                if (hour > 99)
//                    return "";//99:59:59
//                minute = minute % 60;
//                timeStr = getFormat(hour) + activity.getString(R.string.hours)
//                        + getFormat(minute) + activity.getString(R.string.minute);
//            }
//        }
//        return timeStr;
//    }
//
//    /**
//     * 是否需要补0
//     */
//    public static String getFormat(int i) {
//        String retStr;
//        if (i >= 0 && i < 10)
//            retStr = "" + i;
//        else
//            retStr = "" + i;
//        return retStr;
//    }

    /**
     * 计算时间秒数
     */
    public static String getSeconds(int counts) {
       // LogUtils.e("总秒数：" + counts);
        if (counts > 60) {
            //分钟数
            int minutes = counts / 60;
            int seconds = counts % 60;
            String minute;
            String second;
            if (minutes < 10) {
                minute = "0" + minutes;
            } else {
                minute = String.valueOf(minutes);
            }
            if (seconds < 10) {
                second = "0" + seconds;
            } else {
                second = String.valueOf(seconds);
            }
            return minute + ":" + second;
        } else if (counts < 10) {
            return "00:0" + counts;
        } else if (counts == 60) {
            return "01:" + "00";
        } else {
            return "00:" + counts;

        }
    }




    /**
     * 获取指定年月天数大小
     */
    public static int getCalendarThisMonth(int currentYear, int currentMonth) {
        Calendar c = Calendar.getInstance();
        c.clear();
        c.set(Calendar.YEAR, currentYear);
        c.set(Calendar.MONTH, currentMonth - 1);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取上个月月末大小
     *
     * @param currentYear
     * @param currentMonth
     * @return
     */
    public static int getEndLastMonth(int currentYear, int currentMonth) {
        //实例化集合
        List<Integer> list = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        c.clear();
        if (currentMonth - 1 == 0) {
            c.set(Calendar.YEAR, currentYear - 1);
            c.set(Calendar.MONTH, 11);
        } else {
            c.set(Calendar.YEAR, currentYear);
            c.set(Calendar.MONTH, (currentMonth - 2));
        }
        int last_sumDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        c.set(Calendar.DATE, last_sumDays);
        //得到一号星期几
        int weekDay = c.get(Calendar.DAY_OF_WEEK);
        if (weekDay < 7) {
            for (int i = weekDay - 1; i >= 0; i--) {
                list.add(i);
            }
        }
        return list.size();
    }



    /**
     * 根据当前日期获得是星期几
     * time=yyyy-MM-dd
     */
    public static String getWeekDay(String pTime) {
        AESUtils.toHex(AESUtils.decrypt(pTime.getBytes()));
        String Week = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //LogUtils.e("时间：" + pTime);
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(DateUtils.timeInMillsTrasToDate(16) + pTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            Week += "天";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 2) {
            Week += "一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 3) {
            Week += "二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 4) {
            Week += "三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 5) {
            Week += "四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 6) {
            Week += "五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 7) {
            Week += "六";
        }
        return Week;
    }

    /**
     * 获取上个月大小
     */
    public static Integer getLastMonth(int currentYear, int currentMonth) {
        //实例化集合
        List<Integer> list = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        c.clear();
        /**
         * 处理上个月月末
         */
        if (currentMonth - 1 == 0) {
            c.set(Calendar.YEAR, currentYear - 1);
            c.set(Calendar.MONTH, 11);
        } else {
            c.set(Calendar.YEAR, currentYear);
            c.set(Calendar.MONTH, (currentMonth - 2));
        }
        int last_sumDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        c.set(Calendar.DATE, last_sumDays);
        //得到一号星期几
        int weekDay = c.get(Calendar.DAY_OF_WEEK);
        if (weekDay < 7) {
            for (int i = weekDay - 1; i >= 0; i--) {
                list.add(i);
            }
        }
        return list.size() + Integer.parseInt(timeInMillsTrasToDate(15)) - 1;
    }

//    public static Integer getLastMonthNumber(int currentYear, int currentMonth) {
//        Calendar c = Calendar.getInstance();
//        c.clear();
//        int day = 0;
//        if (currentMonth - 1 == 0) {
//            c.set(Calendar.YEAR, currentYear - 1);
//            c.set(Calendar.MONTH, 11);
//        } else {
//            c.set(Calendar.YEAR, currentYear);
//            c.set(Calendar.MONTH, (currentMonth - 2));
//        }
//        int last_sumDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
//        c.set(Calendar.DATE, last_sumDays);
//        //得到一号星期几
//        int weekDay = c.get(Calendar.DAY_OF_WEEK);
//        if (weekDay < 7) {
//            for (int i = weekDay - 1; i >= 0; i--) {
//                day++;
//            }
//        }
//        return day;
//    }

    /**
     * list转string字符串
     */
    public static String returnList(List<String> list) {
        if (null == list && list.size() == 0) {
            return "";
        } else {
            //去除空格
            String str = String.valueOf(list).replaceAll(" ", "");
            return str.substring(1, str.length() - 1);
        }
    }

    /**
     * 0：本月第一天 1：明天 2：下月第一天
     */
    public static String getCalendarDay(int type) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        if (type == 0) {
            c.add(Calendar.MONTH, 0);
            c.set(Calendar.DAY_OF_MONTH, 1);//本月第一天
        } else if (type == 1) {
            c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));//本月最后一天
        } else if (type == 2) {
            c.add(Calendar.MONTH, 1);//月份设置为下个月
            c.set(Calendar.DAY_OF_MONTH, 1);//日期设置为1号
            c.add(Calendar.DAY_OF_MONTH, 0);//倒回到前一天
        }
        return format.format(c.getTime());
    }

    /**
     * 获取某天
     *
     * @return
     */
    public static String getPositionDay(int position) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        //在日历中找到当前日期
        c.setTime(c.getTime());
        //循环。当天与本周周一到周日相差的天数
        c.add(Calendar.DATE, position);
        return format.format(c.getTime());
    }


    public static Date getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取下个月的1号
     *
     * @return
     */
    public static String getNextMonth() {
        Calendar lastDay = Calendar.getInstance();//获取当前时间
        lastDay.add(Calendar.MONTH, 1);//月份设置为下个月
        lastDay.set(Calendar.DAY_OF_MONTH, 1);//日期设置为1号
        lastDay.add(Calendar.DAY_OF_MONTH, 0);//倒回到前一天
        return new SimpleDateFormat("yyyy-MM-dd").format(lastDay.getTime());
    }

    /**
     *  * 获取指定日期下个月的第一天
     *
     * @param dateStr
     * @param format
     * @return
     */
//    public static String getFirstDayOfNextMonth(String dateStr) {
//        Calendar cal = Calendar.getInstance();
//        // 不加下面2行，就是取当前时间前一个月的第一天及最后一天
//        cal.set(Calendar.YEAR, DateUtils.timeInMillsTrasToDate(13));
//        cal.set(Calendar.MONTH, "18");
//        cal.set(Calendar.DAY_OF_MONTH, 1);
//        cal.set(Calendar.HOUR_OF_DAY, 0);
//        cal.set(Calendar.MINUTE, 0);
//        cal.set(Calendar.SECOND, 0);
//
//        cal.add(Calendar.DAY_OF_MONTH, -1);
//        Date lastDate = cal.getTime();
//        cal.set(Calendar.DAY_OF_MONTH, 1);
//        Date firstDate = cal.getTime();
//        return firstDate.toString();
//    }

    /**
     * 本月最后一天
     */
//    public static String getLastDay() {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar ca = Calendar.getInstance();
//        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
//        return format.format(ca.getTime());
//    }
}
