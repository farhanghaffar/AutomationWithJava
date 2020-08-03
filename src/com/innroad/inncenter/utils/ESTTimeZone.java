package com.innroad.inncenter.utils;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.poi.hssf.record.StyleRecord;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;

public class ESTTimeZone {

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <CompareDate> ' Description: <this method compare two
	 * dates. like if you wanna compare past date with today then u have to send
	 * past date in parameter > ' Input parameters: < pass date as string in
	 * method> ' Return value: <boolean> ' Created By: <Farhan Ghaffar> '
	 * Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public static boolean CompareDate(String getDate) throws ParseException {
		boolean isLess = false;
		String[] str = getDate.split(" ");
		System.out.println(str.length);
		String day = str[0];
		String month = str[1];
		String year = str[3];
		System.out.println("day : " + day);
		System.out.println("day : " + month);
		System.out.println("day : " + year);
		str = str[2].split(",");

		getDate = day + ", " + str[0] + " " + month + " " + year;

		System.out.println(getDate);

		SimpleDateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy");
		dateFormat.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
		Date d1 = dateFormat.parse(getDate);
		Date date = new Date();
		String TodayDate = dateFormat.format(date);

		Date d2 = dateFormat.parse(TodayDate);

		if (d1.compareTo(d2) > 0) {
			// When Date d1 > Date d2
			isLess = false;
		}

		else if (d1.compareTo(d2) < 0) {

			// When Date d1 < Date d2
			System.out.println("Date1 is before Date2");
			isLess = true;
		}

		else if (d1.compareTo(d2) == 0) {

			// When Date d1 = Date d2
			System.out.println("Date1 is equal to Date2");
			isLess = true;
			;
		}

		return isLess;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <getDateforAccountBalance> ' Description: < Get date
	 * format for account balance report > ' Input parameters: <pass day as
	 * integer value and then will return base on day like pass 0 as day then it
	 * will return today date> ' Return value: <String> ' Created By: <Farhan
	 * Ghaffar> ' Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public static String getDateforAccountBalance(int days) throws ParseException {

		DateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
		dateFormat.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
		Date date = new Date();
		String DF = dateFormat.format(date);

		Calendar c = Calendar.getInstance();
		c.setTime(dateFormat.parse(DF));

		c.add(Calendar.DATE, days);
		String arrivalDate = dateFormat.format(c.getTime());

		DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
		Date date1 = format.parse(arrivalDate);
		System.out.println(date1);
		String d = date1.toString();

		String[] split_String = d.split(" ");
		String weakday = "";
		switch (split_String[0]) {
		case "Mon":
			weakday = "Monday";
			break;
		case "Tue":
			weakday = "Tuesday";
			break;
		case "Wed":
			weakday = "Wednesday";
			break;
		case "Thu":
			weakday = "Thursday";
			break;
		case "Fri":
			weakday = "Friday";
			break;
		case "Sat":
			weakday = "Saturday";
			break;
		case "Sun":
			weakday = "Sunday";
			break;
		}

		// start month
		String month = "";
		switch (split_String[1]) {
		case "Jan":
			month = "January";
			break;
		case "Feb":
			month = "February";
			break;
		case "Mar":
			month = "March";
			break;
		case "Apr":
			month = "April";
			break;
		case "May":
			month = "May";
			break;
		case "Jun":
			month = "June";
			break;
		case "Jul":
			month = "July";
			break;
		case "Aug":
			month = "August";
			break;
		case "Sep":
			month = "September";
			break;
		case "Oct":
			month = "October";
			break;
		case "Nov":
			month = "November";
			break;
		case "Dec":
			month = "December";
			break;

		}

		String get_date = split_String[2];
		if (get_date.charAt(0) == '0') {
			get_date = String.valueOf(get_date.charAt(1));
		}

		String getDate = weakday + ", " + month + " " + get_date + ", " + split_String[split_String.length - 1];

		return getDate;

	}

	public static String DateFormateForLineItem(int days) throws ParseException {

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
		Date date = new Date();
		String DF = dateFormat.format(date);

		Calendar c = Calendar.getInstance();
		c.setTime(dateFormat.parse(DF));
		c.add(Calendar.DATE, days);
		String selectDate = dateFormat.format(c.getTime());
		return selectDate;
	}

	public static String GetDate(String Time_Zone, String Format) throws ParseException {

		DateFormat dateFormat = new SimpleDateFormat(Format);
		dateFormat.setTimeZone(TimeZone.getTimeZone(Time_Zone));
		Date date = new Date();
		String DF = dateFormat.format(date);

		Calendar c = Calendar.getInstance();
		c.setTime(dateFormat.parse(DF));
		String arrivalDate = dateFormat.format(c.getTime());

		System.out.println("date : " + arrivalDate);
		return arrivalDate;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <getDateforAccountBalance> ' Description: < Get date
	 * format for folio line item > ' Input parameters: <pass day as integer
	 * value and then will return base on day like pass 0 as day then it will
	 * return today date> ' Return value: <String> ' Created By: <Farhan
	 * Ghaffar> ' Created On: <MM/DD/YYYY>
	 *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public static String getDateforLineItem(int days, boolean LineItemDate) throws ParseException {

		DateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
		dateFormat.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
		Date date = new Date();
		String DF = dateFormat.format(date);

		Calendar c = Calendar.getInstance();
		c.setTime(dateFormat.parse(DF));

		c.add(Calendar.DATE, days);
		String arrivalDate = dateFormat.format(c.getTime());

		DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
		Date date1 = format.parse(arrivalDate);
		System.out.println(date1);
		String d = date1.toString();

		String[] split_String = d.split(" ");
		String weakday = "";
		switch (split_String[0]) {
		case "Mon":
			weakday = "Mon";
			break;
		case "Tue":
			weakday = "Tue";
			break;
		case "Wed":
			weakday = "Wed";
			break;
		case "Thu":
			weakday = "Thu";
			break;
		case "Fri":
			weakday = "Fri";
			break;
		case "Sat":
			weakday = "Sat";
			break;
		case "Sun":
			weakday = "Sun";
			break;
		}

		// start month
		String month = "";
		switch (split_String[1]) {
		case "Jan":
			month = "Jan";
			break;
		case "Feb":
			month = "Feb";
			break;
		case "Mar":
			month = "Mar";
			break;
		case "Apr":
			month = "Apr";
			break;
		case "May":
			month = "May";
			break;
		case "Jun":
			month = "Jun";
			break;
		case "Jul":
			month = "Jul";
			break;
		case "Aug":
			month = "Aug";
			break;
		case "Sep":
			month = "Sep";
			break;
		case "Oct":
			month = "Oct";
			break;
		case "Nov":
			month = "Nov";
			break;
		case "Dec":
			month = "Dec";
			break;

		}

		String get_date = split_String[2];
		/*
		 * if (get_date.charAt(0) == '0') { get_date =
		 * String.valueOf(get_date.charAt(1)); }
		 */

		String getDate = "";
		if (LineItemDate) {
			getDate = weakday + " " + month + " " + get_date + ", " + split_String[split_String.length - 1];

		} else {
			getDate = weakday + ", " + get_date + "-" + month + "-" + split_String[split_String.length - 1];
		}

		return getDate;

	}

	public static String getDateIncidental(int days, boolean LineItemDate) throws ParseException {

		DateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
		dateFormat.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
		Date date = new Date();
		String DF = dateFormat.format(date);

		Calendar c = Calendar.getInstance();
		c.setTime(dateFormat.parse(DF));

		c.add(Calendar.DATE, days);
		String arrivalDate = dateFormat.format(c.getTime());

		DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
		Date date1 = format.parse(arrivalDate);
		System.out.println(date1);
		String d = date1.toString();

		String[] split_String = d.split(" ");
		String weakday = "";
		switch (split_String[0]) {
		case "Mon":
			weakday = "Mon";
			break;
		case "Tue":
			weakday = "Tue";
			break;
		case "Wed":
			weakday = "Wed";
			break;
		case "Thu":
			weakday = "Thu";
			break;
		case "Fri":
			weakday = "Fri";
			break;
		case "Sat":
			weakday = "Sat";
			break;
		case "Sun":
			weakday = "Sun";
			break;
		}

		// start month
		String month = "";
		switch (split_String[1]) {
		case "Jan":
			month = "Jan";
			break;
		case "Feb":
			month = "Feb";
			break;
		case "Mar":
			month = "Mar";
			break;
		case "Apr":
			month = "Apr";
			break;
		case "May":
			month = "May";
			break;
		case "Jun":
			month = "Jun";
			break;
		case "Jul":
			month = "Jul";
			break;
		case "Aug":
			month = "Aug";
			break;
		case "Sep":
			month = "Sep";
			break;
		case "Oct":
			month = "Oct";
			break;
		case "Nov":
			month = "Nov";
			break;
		case "Dec":
			month = "Dec";
			break;

		}

		String get_date = split_String[2];
		/*
		 * if (get_date.charAt(0) == '0') { get_date =
		 * String.valueOf(get_date.charAt(1)); }
		 */

		String getDate = weakday + " " + month + " " + get_date + " ," + split_String[split_String.length - 1];

		return getDate;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <getComingSunday> ' Description: <This static method will
	 * provide coming Sunday date provided that Days will be provided in
	 * multiples of seven.> ' Input parameters: <int Days> ' Return value:
	 * <String> ' Created By: <Jamal Nasir> ' Created On: <05/06/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public static String getComingSunday(int Days) throws ParseException {
		if (Days < 7) {
			Days = 7;
		}
		DateFormat Format = new SimpleDateFormat("MM/dd/yyyy");
		Format.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
		Date date = new Date();
		String DF = Format.format(date);

		Calendar c = Calendar.getInstance();
		c.setTime(Format.parse(DF));

		int weekday = c.get(Calendar.DAY_OF_WEEK);
		int days = Calendar.SUNDAY - weekday;
		if (days <= 0) {
			// this will usually be the case since Calendar.SUNDAY is the
			// smallest
			days += Days;
		}
		c.add(Calendar.DAY_OF_YEAR, days);
		String arrivalDate = Format.format(c.getTime());

		Date date1 = Format.parse(arrivalDate);
		System.out.println(date1);
		String selectDate = Format.format(c.getTime());

		return selectDate;

	}
	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <reformatDate> ' Description: <This static method will
	 * take date and its format. then it changes date to required format.> '
	 * Input parameters: <String DateToFormat, String PreFormat, String
	 * PostFormat> ' Return value: <String> ' Created By: <Jamal Nasir> '
	 * Created On: <04/30/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public static String reformatDate(String DateToFormat, String preFormat, String postFormat) throws ParseException {
		DateFormat srcDf = new SimpleDateFormat(preFormat);

		// parse the date string into Date object
		Date date = srcDf.parse(DateToFormat);

		DateFormat destDf = new SimpleDateFormat(postFormat);

		// format the date into another format
		DateToFormat = destDf.format(date);

		return DateToFormat;
	}

	public static String SubString(String StringToBeParse, int StartingPoint, int EndPoint) {
		System.out.println(StringToBeParse.length());
		StringToBeParse = StringToBeParse.substring(StartingPoint, EndPoint);
		System.out.println("first part: " + StringToBeParse);
		return StringToBeParse;
		// String secondpart = getStr.substring(10, getStr.length());
		// System.out.println("second part: "+secondpart);
	}

	public static String parseDate(String date, String inFormate, String outFormate) {
		String result = null;
		try {
			Date date1 = new SimpleDateFormat(inFormate).parse(date);
			DateFormat requiredDateFormat = new SimpleDateFormat(outFormate);
			// requiredDateFormat.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
			result = requiredDateFormat.format(date1);
			System.out.println(result);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <DateFormateForCheckInCheckOut> ' Description: <This
	 * method will return Date for MR Reservations CheckIn and CheckOut> ' Input
	 * parameters: <int days> ' Return value: <String> ' Created By: <Jamal
	 * Nasir> ' Created On: <05/18/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public static String DateFormateForCheckInCheckOut(int days) throws ParseException {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
		Date date = new Date();
		String DF = dateFormat.format(date);

		Calendar c = Calendar.getInstance();
		c.setTime(dateFormat.parse(DF));
		c.add(Calendar.DATE, days);
		String selectDate = dateFormat.format(c.getTime());
		return selectDate;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <getDateWithTime> ' Description: <This method will return
	 * Date with Time> ' Input parameters: <int Minute> ' Return value: <String>
	 * ' Created By: <Adnan Ghaffar> ' Created On: <05/18/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public static String getDateWithTime(int minutes) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("E MMM d,yyyy hh:mm a");
		// dateFormat.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
		Date date = new Date();
		String DF = dateFormat.format(date);

		Calendar c = Calendar.getInstance();
		c.setTime(dateFormat.parse(DF));

		c.add(Calendar.MINUTE, minutes);
		String arrivalDate = dateFormat.format(c.getTime());
		return arrivalDate;
	}

	public static int daysBetween(Date d1, Date d2) {
		return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}

	public static int numberOfDaysBetweenDates(String startDate, String endDate) throws ParseException {
		Calendar cal1 = new GregorianCalendar();
		Calendar cal2 = new GregorianCalendar();

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

		Date date = sdf.parse(startDate);
		cal1.setTime(date);
		date = sdf.parse(endDate);
		cal2.setTime(date);

		System.out.println("Days= " + daysBetween(cal1.getTime(), cal2.getTime()));
		return daysBetween(cal1.getTime(), cal2.getTime());

	}

	public static String getDateFormatBasedOnClientDateFormartType(String clientDateFormatType) throws ParseException {
		String dateFormat = "";

		if (clientDateFormatType.equalsIgnoreCase("USA")) {
			dateFormat = "MM/dd/yyyy";

		} else if (clientDateFormatType.equalsIgnoreCase("International")) {
			dateFormat = "dd/MM/yyyy";
		} else {
			dateFormat = "This case isn't handled, Please handle it first in Utility";
		}
		return dateFormat;
	}

}
