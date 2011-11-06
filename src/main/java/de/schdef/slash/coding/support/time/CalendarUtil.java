package de.schdef.slash.coding.support.time;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public final class CalendarUtil {

    private CalendarUtil() {
    }

    /**
     * The instance XMLGregorianCalendar attributes contains current date and
     * time. Timezone and locale are taken from system defaults.
     *
     * @return instance of XMLGregorianCalendar
     */
    public static XMLGregorianCalendar createCurrentXmlCalendar() {
        GregorianCalendar calendar = new GregorianCalendar();
        return convert2XmlCalendar(calendar);
    }

    /**
     * Converts java.util.GregorianCalendar into
     * javax.xml.datatype.XMLGregorianCalendar
     *
     * @param calendar
     *            the calendar
     * @return instance of XMLGregorianCalendar
     */
    public static XMLGregorianCalendar convert2XmlCalendar(
            GregorianCalendar calendar) {
        if (calendar == null) {
            return null;
        }

        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(
                    calendar);
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the time in millis.
     *
     * @param xmlCalendar
     *            the xml calendar
     * @return the current time as UTC milliseconds from the epoch.
     */
    public static long getTimeInMillis(XMLGregorianCalendar xmlCalendar) {
        if (xmlCalendar == null) {
            throw new IllegalArgumentException(
                    "Parameter 'XMLGregorianCalendar xmlCalendar' is null!");
        }

        return xmlCalendar.toGregorianCalendar().getTimeInMillis();
    }

    /**
     * Parse input date string as {@link XMLGregorianCalendar}. We want, that
     * method {@link XMLGregorianCalendar#toXMLFormat} produce date format
     * 'yyyy-MM-dd'.
     *
     * @param dateStr
     *            string representing date
     * @param format
     *            format of the input string
     * @return representation of the date (no time set)
     * @throws ParseException
     *             if not possible to parse the input string wwith the given
     *             format
     * @see #convert2XmlDateWithoutTimezone
     */
    public static XMLGregorianCalendar parseAsXmlDateWithoutTimezone(
            String dateStr, String format) throws ParseException {
        if (dateStr == null || dateStr.length() < 1) {
            throw new IllegalArgumentException(
                    "Argument 'String dateStr' is null or empty string!");
        }

        if (format == null || format.length() < 1) {
            throw new IllegalArgumentException(
                    "Argument 'String format' is null or empty string!");
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setLenient(false);
        Date date = simpleDateFormat.parse(dateStr);
        return convert2XmlDateWithoutTimezone(date);
    }

    /**
     * Convert Date into XMLGregorianCalendar. We want, that method
     * {@link XMLGregorianCalendar#toXMLFormat} produce date format
     * 'yyyy-MM-dd'. To accomplish this, it is necessery to set TimeZone value
     * of the {@link XMLGregorianCalendar} to
     * {@link DatatypeConstants#FIELD_UNDEFINED} If TimeZone would be set to 0,
     * output would be yyyy-MM-ddZ, which is also correct but more applications
     * outside will be able process the first format.
     *
     * @param date
     *            the date
     * @return representation of the date (no time set)
     */
    public static XMLGregorianCalendar convert2XmlDateWithoutTimezone(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Argument 'Date date' is null!");
        }

        GregorianCalendar utilGregorianCalendar = new GregorianCalendar();
        utilGregorianCalendar.setTimeInMillis(date.getTime());
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendarDate(
                    utilGregorianCalendar.get(Calendar.YEAR),
                    utilGregorianCalendar.get(Calendar.MONTH) + 1,
                    utilGregorianCalendar.get(Calendar.DAY_OF_MONTH),
                    DatatypeConstants.FIELD_UNDEFINED);
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static XMLGregorianCalendar convert2XmlTime(Time time)
            throws RuntimeException {
        if (time == null) {
            throw new IllegalArgumentException("Argument 'Time' is null!");
        }

        GregorianCalendar utilGregorianCalendar = new GregorianCalendar();
        utilGregorianCalendar.setTimeInMillis(time.getTime());
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendarTime(
                    utilGregorianCalendar.get(Calendar.HOUR),
                    utilGregorianCalendar.get(Calendar.MINUTE),
                    utilGregorianCalendar.get(Calendar.SECOND),
                    DatatypeConstants.FIELD_UNDEFINED);
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Check if the two dates are equals comparing only those fields that are
     * given in the array.<br/>
     * Example:<br/>
     * <code>Date a = new Date();</code><br/>
     * <code>Date b = new Date();</code><br/>
     * <code>int[] fields = { Calendar.MONTH, Calendar.DAY_OF_MONTH };</code><br/>
     * <code>boolean equality = equals(fields, a, b);</code><br/>
     *
     * @param fields
     *            the fields to compare (Constants from Calendar)
     * @param dateA
     *            the first date to be compared with the second
     * @param dateB
     *            the second date to be compared with the first
     * @return <code>true</code> if they equal, <code>false</code> if not.
     */
    public static boolean equals(int[] fields, Date dateA, Date dateB) {
        Calendar calendar = Calendar.getInstance();
        // if both are null return true
        if (dateA == null && dateB == null) {
            return true;
        }
        // if either one is null then return false
        if (dateA == null || dateB == null) {
            return false;
        }

        boolean b = true;
        int[] values = new int[fields.length];

        calendar.setTime(dateA);
        for (int i = 0; i < fields.length; i++) {
            values[i] = calendar.get(fields[i]);
        }
        calendar.setTime(dateB);
        for (int i = 0; i < fields.length; i++) {
            int x = calendar.get(fields[i]);
            if (values[i] != x) {
                b = false;
                break;
            }
        }
        return b;
    }

    /**
     * Compare both dates and uses only year and month for comparing.
     *
     * @param date1
     *            must not be <code>null</code>
     * @param date2
     *            must not be <code>null</code>
     * @return <code>0</code>, if both dates (using year and month) are equals;
     *         <code>1</code>, if <code>date1</code> is later than
     *         <code>date2</code>; else <code>-1</code>
     */
    public static int compareMonthYear(Date date1, Date date2) {
        final Calendar cal = Calendar.getInstance();

        cal.setTime(date1);
        final int month1 = cal.get(Calendar.MONTH);
        final int year1 = cal.get(Calendar.YEAR);

        cal.setTime(date2);
        final int month2 = cal.get(Calendar.MONTH);
        final int year2 = cal.get(Calendar.YEAR);

        return year1 < year2 ? -1 : (year1 > year2 ? 1 : (month1 < month2 ? -1
                : (month1 > month2 ? 1 : 0)));
    }

}
