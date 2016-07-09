package com.femto.shipper.utils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
 
public class Times {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 
    public static void main(String args[]) {
        
        System.out.println(Times.getDateBefore(new Date(),7));
 
    }
    
    
public static int todayIsNub(){
	
	Calendar c = Calendar.getInstance();
	int day=c.getTime().getDate();
	return day;
}
 //获取上个月一号
    public static Date getLastMonthStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (1 - index));
        return calendar.getTime();
   }
    //获取本月一号
    public static Date getMonthStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (1 - index));
        return calendar.getTime();
    }
    //获取本月16号
    public static Date getMonthSix(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (16 - index));
        return calendar.getTime();
    }
    //获取本月15号
    public static Date getMonthFi(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (15 - index));
        return calendar.getTime();
    }
 //获取上月最后一日
    public static Date getLastMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (1 - index)-1);
        return calendar.getTime();
    }
   //获取本月最后一日
    public static Date getMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (-index));
        return calendar.getTime();
    }
 //获取整月日期
    public static Date getNext(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }
    /** 
     * 得到几天前的时间 
     *  
     * @param d 
     * @param day 
     * @return 
     */  
    public static Date getDateBefore(Date d, int day) {  
        Calendar now = Calendar.getInstance();  
        now.setTime(d);  
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);  
        return now.getTime();  
    }    
    /** 
     * 得到几天后的时间 
     *  
     * @param d 
     * @param day 
     * @return 
     */  
    public static Date getDateAfter(Date d, int day) {  
        Calendar now = Calendar.getInstance();  
        now.setTime(d);  
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);  
        return now.getTime();  
    }
 
}