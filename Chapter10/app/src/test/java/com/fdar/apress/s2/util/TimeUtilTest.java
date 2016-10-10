package com.fdar.apress.s2.util;

import junit.framework.TestCase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtilTest extends TestCase {

    TimeUtil timeUtil;

    public void testParseTimeUS() throws Exception {
        final Locale locale = Locale.US;

        this.timeUtil = new TimeUtil();
        timeUtil.parseTime("09:10 am", locale);
        assertTrue(timeUtil.isValid());
        assertEquals(timeUtil.getHour(), 9);
        assertEquals(timeUtil.getMinute(), 10);
        assertEquals(Calendar.AM, timeUtil.getAmOrPm());

        this.timeUtil = new TimeUtil();
        timeUtil.parseTime("9 am", locale);
        assertTrue(timeUtil.isValid());
        assertEquals(timeUtil.getHour(), 9);
        assertEquals(timeUtil.getMinute(), 0);
        assertEquals(Calendar.AM, timeUtil.getAmOrPm());

        this.timeUtil = new TimeUtil();
        timeUtil.parseTime("9:10 PM", locale);
        assertTrue(timeUtil.isValid());
        assertEquals(21, timeUtil.getHour());
        assertEquals(10, timeUtil.getMinute());
        assertEquals(Calendar.PM, timeUtil.getAmOrPm());

        this.timeUtil = new TimeUtil();
        timeUtil.parseTime("09:10Am", locale);
        assertTrue(timeUtil.isValid());
        assertEquals(timeUtil.getHour(), 9);
        assertEquals(timeUtil.getMinute(), 10);
        assertEquals(Calendar.AM, timeUtil.getAmOrPm());

        this.timeUtil = new TimeUtil();
        timeUtil.parseTime("13:10 am", locale);
        assertFalse(timeUtil.isValid());

        this.timeUtil = new TimeUtil();
        timeUtil.parseTime("12:10 bm", locale);
        assertFalse(timeUtil.isValid());

        this.timeUtil = new TimeUtil();
        timeUtil.parseTime(null);
        assertFalse(timeUtil.isValid());
    }

    public void testParseTimeDE() throws Exception {
        final Locale locale = Locale.GERMANY;

        this.timeUtil = new TimeUtil();
        timeUtil.parseTime("09:10", locale);
        assertTrue(timeUtil.isValid());
        assertEquals(timeUtil.getHour(), 9);
        assertEquals(timeUtil.getMinute(), 10);
        assertEquals(Calendar.AM, timeUtil.getAmOrPm());

        this.timeUtil = new TimeUtil();
        timeUtil.parseTime("9", locale);
        assertTrue(timeUtil.isValid());
        assertEquals(timeUtil.getHour(), 9);
        assertEquals(timeUtil.getMinute(), 0);
        assertEquals(Calendar.AM, timeUtil.getAmOrPm());

        this.timeUtil = new TimeUtil();
        timeUtil.parseTime("21:10", locale);
        assertTrue(timeUtil.isValid());
        assertEquals(21, timeUtil.getHour());
        assertEquals(10, timeUtil.getMinute());
        assertEquals(Calendar.PM, timeUtil.getAmOrPm());

        this.timeUtil = new TimeUtil();
        timeUtil.parseTime("9:10Am", locale);
        assertTrue(timeUtil.isValid());
        assertEquals(timeUtil.getHour(), 9);
        assertEquals(timeUtil.getMinute(), 10);
        assertEquals(Calendar.AM, timeUtil.getAmOrPm());

        this.timeUtil = new TimeUtil();
        timeUtil.parseTime("25:10", locale);
        assertFalse(timeUtil.isValid());

        this.timeUtil = new TimeUtil();
        timeUtil.parseTime(null);
        assertFalse(timeUtil.isValid());
    }

    public void testResolveDate() throws Exception {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm z");
        final Date baseDate = dateFormat.parse("2000-01-01 00:00 GMT");

        Date d = new TimeUtil().resolveDate(baseDate, -1, 22, 11);
        assertEquals(dateFormat.parse("2000-01-01 23:11 GMT"), d);

        d = new TimeUtil().resolveDate(baseDate, 2, 22, 11);
        assertEquals(dateFormat.parse("2000-01-01 20:11 GMT"), d);

        assertNull(new TimeUtil().resolveDate(null, 0,0,0));
    }
}