package com.dotin.dotintasktwo.utility;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ULocale;



public class Time {

    public Time() {
    }

    public String getTime() {
        ULocale locale = new ULocale("fa_IR@calendar=persian");

        Calendar calendar = Calendar.getInstance(locale);
        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, locale);

//        System.out.println("////////////////////////////////////");
//        System.out.println(df.format(calendar));
//        System.out.println("////////////////////////////////////");
        return df.format(calendar);
    }


}
