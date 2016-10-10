package com.fdar.apress.s2.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeUtil {

    private boolean valid = false;

    private int hour = 0;
    private int minute = 0;
    private int amOrPm = 0;

    private static final int INVALID = -1;

    /**
     * Constructor.  Creates a valid TimeUtil object.
     *
     * @param time the time as a string.
     */
    public TimeUtil( String time ) {
        parseTime(time);
    }

    /**
     * Non-argument constructor to help unit testing.
     */
    protected TimeUtil() {
    }

    protected void parseTime( String time, Locale locale ) {
        if (time != null) {
            final String lowerCaseTime = time.toLowerCase();
            int ampmIndex = lowerCaseTime.indexOf("am");
            if (ampmIndex == INVALID) {
                ampmIndex = lowerCaseTime.indexOf("pm");
            }
            int timeEnd = lowerCaseTime.indexOf(' ');
            if (timeEnd == INVALID) {
                timeEnd = ampmIndex!=INVALID ? ampmIndex : time.length();
            }
            StringBuffer timeBuffer = new StringBuffer(time.substring(0, timeEnd));
            if ( lowerCaseTime.indexOf(':')==INVALID ) {
                timeBuffer.append(":00");
            }
            if ( ampmIndex != INVALID) {
                timeBuffer.append(' ');
                timeBuffer.append(time.substring(ampmIndex));
            }
            try {
                DateFormat df = DateFormat.getTimeInstance(DateFormat.SHORT, locale);
                df.setLenient(false);
                Date parsed = df.parse(timeBuffer.toString());
                Calendar cal = Calendar.getInstance();
                cal.setTime(parsed);
                this.amOrPm = cal.get(Calendar.AM_PM);
                this.hour = cal.get(Calendar.HOUR_OF_DAY);
                this.minute = cal.get(Calendar.MINUTE);
                this.valid = true;
            } catch ( ParseException e ) {
                this.valid = false;
            }
        }
    }

    protected void parseTime( String time ) {
        parseTime( time, Locale.getDefault());
    }

    /**
     * Validates that the time is valid.
     * <ul>
     *  <li>required: A digit 0..12 / 0..24 depending on Locale</li>
     *  <li>optional: a colon and digit 00..59</li>
     *  <li>optional: space</li>
     *  <li>depending on Locale: case insensitive, either "am" or "pm" for 12 hour formats, empty for 24 hour formats</li>
     * </ul>
     *
     * Example:
     * <ul>
     *  <li>12:30 am</li>
     *  <li>12:30pm</li>
     *  <li>9</li>
     *  <li>21:00</li>
     * </ul>
     *
     * @return true if valid, otherwise false.
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * Returns the hour of day in 24 hour scale.
     *
     * @return the hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * Returns the hour in 12 hour scale.
     *
     * @return the hour
     */
    public int getHourAmPm() {
        return hour % 12;
    }

    /**
     * Returns the minute.
     *
     * @return the minute
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Returns whether this is an Calendar.AM or Calendar.PM time.
     *
     * @return either Calendar.AM or Calendar.PM
     */
    public int getAmOrPm() {
        return amOrPm;
    }

    protected Date resolveDate( Date baseDate, int offset, int hour, int minute ) throws ParseException {
        if (baseDate != null) {
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+0"));
            cal.setTime(baseDate);
            StringBuffer sb = new StringBuffer("GMT");
            if ( offset>=0 ) {
                sb.append("+");
            }
            sb.append(offset);
            cal.setTimeZone(TimeZone.getTimeZone(sb.toString()));
            cal.set(Calendar.HOUR_OF_DAY, hour);
            cal.set(Calendar.MINUTE, minute);
            return cal.getTime();
        } else {
            return null;
        }
    }

    public Date resolveDate( Date baseDate, int offset ) throws ParseException {
        return resolveDate( baseDate, offset, this.hour, this.minute);
    }

}
