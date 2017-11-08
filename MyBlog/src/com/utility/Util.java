package com.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Util {
    public static String getSysTime()
    {
    	DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date date=new Date();
    	String timeString=format.format(date);
    	return timeString;
    }
}
