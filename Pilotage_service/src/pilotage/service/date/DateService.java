package pilotage.service.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import pilotage.service.constants.PilotageConstants;

public class DateService {
	public static final DateFormat pattern1Formatter 	= new SimpleDateFormat("dd/MM/yyyy");
	public static final DateFormat pattern1BisFormatter = new SimpleDateFormat("dd/MM/yy");
	public static final DateFormat pattern2Formatter 	= new SimpleDateFormat("dd-MM-yyyy");
	public static final DateFormat pattern2BisFormatter = new SimpleDateFormat("dd-MM-yy");
	public static final DateFormat pattern3Formatter 	= new SimpleDateFormat("ddMMyyyy");
	public static final DateFormat pattern3BisFormatter = new SimpleDateFormat("ddMMyy");
	public static final DateFormat pattern4Formatter 	= new SimpleDateFormat("yyyy-MM-dd");
	
	public static final DateFormat patternDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	public static final DateFormat patternDateTime1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	public static final DateFormat patternTime1 = new SimpleDateFormat("HH:mm");
	public static final DateFormat patternTime2 = new SimpleDateFormat("HH:mm:ss");
	
	public static final DateFormat patternMonthFormatter = new SimpleDateFormat("MMMM");
	public static final DateFormat patternDayFormatter = new SimpleDateFormat("EEEE dd MMMM");

	public static final DateTimeFormatter dtfpattern1Formatter 		= DateTimeFormat.forPattern("dd/MM/yyyy");
	public static final DateTimeFormatter dtfpattern1BisFormatter 	= DateTimeFormat.forPattern("dd/MM/yy");
	public static final DateTimeFormatter dtfpattern2Formatter 		= DateTimeFormat.forPattern("dd-MM-yyyy");
	public static final DateTimeFormatter dtfpattern2BisFormatter 	= DateTimeFormat.forPattern("dd-MM-yy");
	public static final DateTimeFormatter dtfpattern3Formatter 		= DateTimeFormat.forPattern("ddMMyyyy");
	public static final DateTimeFormatter dtfpattern3BisFormatter 	= DateTimeFormat.forPattern("ddMMyy");
	public static final DateTimeFormatter dtfpattern4Formatter 		= DateTimeFormat.forPattern("yyyy-MM-dd");
	
	public static final DateTimeFormatter dtfpatternDateTime 	= DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
	public static final DateTimeFormatter dtfpatternDateTime1 	= DateTimeFormat.forPattern("dd/MM/yyyy HH:mm");
	public static final DateTimeFormatter dtfpatternDateTime2 	= DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
	public static final DateTimeFormatter dtfpatternTime1 		= DateTimeFormat.forPattern("HH:mm");
	public static final DateTimeFormatter dtfpatternTime2 		= DateTimeFormat.forPattern("HH:mm:ss");

	public static final DateTimeFormatter dtfpatternMonthFormatter 	= DateTimeFormat.forPattern("MMMM");
	public static final DateTimeFormatter dtfpatternDayFormatter 	= DateTimeFormat.forPattern("EEEE dd MMMM yyyy");
	
	public static final String p1		= "[0-9]{2}/[0-9]{2}/[0-9]{4}";
	public static final String p1bis 	= "[0-9]{2}/[0-9]{2}/[0-9]{2}";
	public static final String p2 		= "[0-9]{2}-[0-9]{2}-[0-9]{4}";
	public static final String p2bis	= "[0-9]{2}-[0-9]{2}-[0-9]{2}";
	public static final String p3		= "[0-9]{8}";
	public static final String p3bis	= "[0-9]{6}";
	public static final String p4		= "[0-9]{4}-[0-9]{2}-[0-9]{2}";
	public static final String p5		= "EEEE dd MMMM yyyy";
	
	public static final String pdt1	= "[0-9]{2}/[0-9]{2}/[0-9]{4} [0-9]{2}:[0-9]{2}:[0-9]{2}";
	public static final String pdt2	= "[0-9]{2}/[0-9]{2}/[0-9]{4} [0-9]{2}:[0-9]{2}";
	public static final String pdt3	= "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}";
	public static final String pt1	= "[0-9]{2}:[0-9]{2}";
	public static final String pt2	= "[0-9]{2}:[0-9]{2}:[0-9]{2}";
	public static final String pt3	= "[0-9]{1,5}:[0-9]{2}:[0-9]{2}";
	public static final String pt4	= "[0-9]{1,5}:[0-9]{2}";
	
	/**
	 * MODIFICATION : AJOUT de heure à une date
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addMinutes(Date date, int amount){	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, amount);
		return calendar.getTime();
	}
	
	public static DateTime addMinutes(DateTime date, int amount){	
		DateTime dt = new DateTime(date);
		return dt.plusMinutes(amount);
	}
	
	/**
	 * MODIFICATION : AJOUT de heure à une date
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addHours(Date date, int amount){	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, amount);
		return calendar.getTime();
	}
	
	/**
	 * MODIFICATION : AJOUT de jours à une date
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addDays(Date date, int amount){	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, amount);
		return calendar.getTime();
	}
	
	/**
	 * MODIFICATION : AJOUT de semaines à une date
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addWeeks(Date date, int amount){	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.WEEK_OF_YEAR, amount);
		return calendar.getTime();
	}
	
	/**
	 * MODIFICATION : AJOUT de mois à une date
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addMonths(Date date, int amount){	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, amount);
		return calendar.getTime();
	}
	
	/**
	 * MODIFICATION : AJOUT d'une année
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addYear(Date date, int amount){	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, amount);
		return calendar.getTime();
	}
	
	/**
	 * MODIFICATION : RESET de l'heure
	 * @return
	 */
	public static Date getTodayWithoutHour(){	
		return getDayWithoutHour(new Date());
	}
	
	/**
	 * MODIFICATION : RESET de l'heure
	 * @return
	 */
	public static Date getDayWithoutHour(Date jour){	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(jour);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * MODIFICATION : Modifier l'heure
	 * @return
	 */
	public static Date setHourFromDate(Date jour, int hour, int minute, int second){	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(jour);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * CONVERSION : de string en date
	 * @param strDate
	 * @return
	 */
	public synchronized static Date strToDate(String strDate){
		if(strDate == null)
			return null;
		try{
			if(strDate.matches(p1))
				return pattern1Formatter.parse(strDate);
			else if(strDate.matches(p1bis))
				return pattern1BisFormatter.parse(strDate);
			else if(strDate.matches(p2))
				return pattern2Formatter.parse(strDate);
			else if(strDate.matches(p2bis))
				return pattern2BisFormatter.parse(strDate);
			else if(strDate.matches(p3))
				return pattern3Formatter.parse(strDate);
			else if(strDate.matches(p3bis))
				return pattern3BisFormatter.parse(strDate);
			else if(strDate.matches(p4))
				return pattern4Formatter.parse(strDate);
			else if(strDate.matches(pdt1))
				return patternDateTime.parse(strDate);
			else if(strDate.matches(pdt2))
				return patternDateTime1.parse(strDate);
			else return null;
		}
		catch (Exception e) {
			return null;
		}
	}
	
	public static Date strToDate(String strDate, String strTime){
		if(strDate == null || strTime == null)
			return null;
		return strToDate(strDate + " " + strTime);
	}
	
	/**
	 * CONVERSION : Long (milliseconde) d'heure en date
	 * @param time
	 * @return
	 */
	public static Date strToDate(Long Millistime){
		if(Millistime == null)
			return null;
		return new Date(Millistime);
	}
		
	/**
	 * CONVERSION : de string en DateTime (fonction joba)
	 * @param strDate
	 * @return
	 */
	public static DateTime strToJodaDateTime(String strDate){
		if(strDate == null)
			return null;
		try{
			if(strDate.matches(p1))
				return DateTime.parse(strDate,dtfpattern1Formatter);
			else if(strDate.matches(p1bis))
				return DateTime.parse(strDate,dtfpattern1BisFormatter);
			else if(strDate.matches(p2))
				return DateTime.parse(strDate,dtfpattern2Formatter);
			else if(strDate.matches(p2bis))
				return DateTime.parse(strDate,dtfpattern2BisFormatter);
			else if(strDate.matches(p3))
				return DateTime.parse(strDate,dtfpattern3Formatter);
			else if(strDate.matches(p3bis))
				return DateTime.parse(strDate,dtfpattern3BisFormatter);
			else if(strDate.matches(p4))
				return DateTime.parse(strDate,dtfpattern4Formatter);
			else if(strDate.matches(pdt1))
				return DateTime.parse(strDate,dtfpatternDateTime);
			else if(strDate.matches(pdt2))
				return DateTime.parse(strDate,dtfpatternDateTime1);
			else if(strDate.matches(pdt3))
				return DateTime.parse(strDate,dtfpatternDateTime2);
			else return null;
		}
		catch (Exception e) {
			return null;
		}
	}
	
	public static DateTime strToJodaDateTime(String strDate, String strTime){
		if(strDate == null || strTime == null)
			return null;
		return strToJodaDateTime(strDate + " " + strTime);
	}
	
	/**
	 * CONVERSION : Long (milliseconde) d'heure en date
	 * @param time
	 * @return
	 */
	public static DateTime strToJodaDateTime(Long Millistime){
		if(Millistime == null)
			return null;
		return new DateTime(Millistime);
	}
		
	/**
	 * CONVERSION : de string en date
	 * @param strDate 
	 * @param strTime (HH:mm ou HH:mm:ss)
	 * @return
	 */
	@Deprecated
	public synchronized static Date strToDateTime(String strDate, String strTime){
		return strToDate(strDate, strTime);
	}
	/**
	 * CONVERSION : de string en date
	 * utiliser la fonction strToDate
	 * @param strDate (format accepté : jj/mm/yyyy hh:mm:ss ou jj/mm/yyyy hh:mm:ss)
	 * @return date
	 */
	@Deprecated
	public synchronized static Date strToDateTime(String strDateTime){
		return strToDate(strDateTime);
	}
	
	/**
	 * CONVERSION : de date vers string sans l'heure 
	 * @param date
	 * @param pattern 
	 * @return
	 */
	public synchronized static String dateToStr(Date date, String pattern)  {
		
		if(p1.equals(pattern))
			return pattern1Formatter.format(date);
		else if(p1bis.equals(pattern))
			return pattern1BisFormatter.format(date);
		else if(p2.equals(pattern))
			return pattern2Formatter.format(date);
		else if(p2bis.equals(pattern))
			return pattern2BisFormatter.format(date);
		else if(p3.equals(pattern))
			return pattern3Formatter.format(date);
		else if(p3bis.equals(pattern))
			return pattern3BisFormatter.format(date);
		else if(p4.equals(pattern))
			return pattern4Formatter.format(date);	
		else if(pdt1.equals(pattern))
			return patternDateTime.format(date);
		else if(pdt2.equals(pattern))
			return patternDateTime1.format(date);
		
		return null;
	}
	
	/**
	 * CONVERSION : de datetime (joda) vers string sans l'heure 
	 * @param datetime
	 * @param pattern 
	 * @return
	 */
	public synchronized static String dateTimeToStr(DateTime date, String pattern)  {
		
		if(p1.equals(pattern))
			return date.toString(dtfpattern1Formatter);
		else if(p1bis.equals(pattern))
			return date.toString(dtfpattern1BisFormatter);
		else if(p2.equals(pattern))
			return date.toString(dtfpattern2Formatter);
		else if(p2bis.equals(pattern))
			return date.toString(dtfpattern2BisFormatter);
		else if(p3.equals(pattern))
			return date.toString(dtfpattern3Formatter);
		else if(p3bis.equals(pattern))
			return date.toString(dtfpattern3BisFormatter);
		else if(p4.equals(pattern))
			return date.toString(dtfpattern4Formatter);	
		else if(p5.equals(pattern))
			return date.toString(dtfpatternDayFormatter);	
		else if(pdt1.equals(pattern))
			return date.toString(dtfpatternDateTime);
		else if(pdt2.equals(pattern))
			return date.toString(dtfpatternDateTime1);
		
		return null;
	}
	
	/**
	 * CONVERSION : l'heure dans une date vers string
	 * @param date
	 * @param pattern
	 * @return
	 */
	public synchronized static String getTime(Date date, String pattern){
		if(pattern == null || pt1.equals(pattern)){
			return patternTime1.format(date);
		}
		else if(pt2.equals(pattern))
			return patternTime2.format(date);
		return null;
	}
	
	/**
	 * CONVERSION : l'heure dans une date vers string
	 * @param datetime (joda)
	 * @param pattern
	 * @return
	 */
	public synchronized static String getTime(DateTime date, String pattern){
		if(pattern == null || pt1.equals(pattern)){
			return date.toString(dtfpatternTime1);
		}
		else if(pt2.equals(pattern))
			return date.toString(dtfpatternTime2);
		return null;
	}
	
	/**
	 * CONVERSION : String d'heure en date
	 * @param time
	 * @return
	 */
	public synchronized static Date getTimeFromString(String time){
		if(time == null)
			return null;
		try{
			if(time.matches(pt1))
				return patternTime1.parse(time);
			else if(time.matches(pt2))
				return patternTime2.parse(time);
			
			return null;
		}
		catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * CONVERSION : d'un interval de temps en en milliseconde
	 * @param time sous la forme H:mm:ss avec H pouvant avoir plus de 24 heures
	 * @return
	 */
	public  static Long strTimeToLong (String interval){
		String[] time = interval.split(":");
		if (interval.matches(pt3))
			return (((Long.parseLong(time[0]) * 60 + Long.parseLong(time[1]) ) * 60 + Long.parseLong(time[2]) ) * 1000);
		if (interval.matches(pt4))
			return (((Long.parseLong(time[0]) * 60 + Long.parseLong(time[1]) ) * 60 + 0 ) * 1000);
		return null;
	
	}
	
	/**
	 * RECUPERATION : du lundi dans 'weekNB' semaines 
	 * @param weekNB
	 * @return
	 */
	public static Date getNextMonday(Integer weekNB){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, weekNB*7);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);		
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	    
	    return calendar.getTime();
	}
	
	/**
	 * RECUPERATION : du lundi dans 'weekNB' semaines 
	 * @param weekNB
	 * @return
	 */
	public static DateTime getJodaNextMonday(Integer weekNB){
		DateTimeZone dtz =  DateTimeZone.UTC;
		DateTime dt = new DateTime(dtz);
		return dt.plusWeeks(weekNB).withDayOfWeek(1).withTimeAtStartOfDay();
	}
	
	/**
	 * RECUPERATION : du lundi de la 'weekNB' semaines de l'annee 'year'
	 * @param weekNB
	 * @param Year
	 * @return
	 */
	public static Date getMonday(Integer weekNB, Date year){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(year);
		calendar.set(Calendar.WEEK_OF_YEAR,weekNB);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);		
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	    
	    return calendar.getTime();
	}
	
	/**
	 * RECUPERATION : du lundi de la 'weekNB' semaines de l'annee 'year'
	 * @param weekNB
	 * @param Year
	 * @return
	 */
	public static Date getMonday(int weekNB, int year){
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.WEEK_OF_YEAR,weekNB);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);		
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	    
	    return calendar.getTime();
	}
	
	/**
	 * RECUPERATION : de la semaine
	 * @param date
	 * @return
	 */
	public static Integer getWeekOfYear(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	public static Integer getWeekOfYear(DateTime dt) {
		return dt.getWeekOfWeekyear();
	}
	
	/**
	 * CONVERSION : des 2 dates en période du ... au ...
	 * @param startDate
	 * @param endDate
	 * @param pattern
	 * @return
	 */
	public synchronized static String getPeriode(Date startDate, Date endDate, String pattern){
		StringBuffer buffer = new StringBuffer();
		DateFormat formatter = null;
		
		if(p1.equals(pattern))
			formatter = pattern1Formatter;
		else if(p1bis.equals(pattern))
			formatter = pattern1BisFormatter;
		else if(p2.equals(pattern))
			formatter = pattern2Formatter;
		else if(p2bis.equals(pattern))
			formatter = pattern2BisFormatter;
		else if(p3.equals(pattern))
			formatter = pattern3Formatter;
		else if(p3bis.equals(pattern))
			formatter = pattern3BisFormatter;
		else
			formatter = pattern1Formatter;
		
		
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		buffer.append("du ");
		buffer.append(formatter.format(startDate));
		buffer.append(" ");
		buffer.append(patternTime1.format(startDate));
		buffer.append(" au ");
		buffer.append(formatter.format(endDate));
		buffer.append(" ");
		buffer.append(patternTime1.format(endDate));
		
		return buffer.toString();
	}

	/**
	 * RECUPERATION : de la weekNB-ième semaine suivante au format "Semaine ... : du Lundi ... au Lundi..."
	 * @param weekNB
	 * @return
	 */
	public static String getHeaderString(Integer weekNB){
		Date startDate = DateService.getNextMonday(weekNB);
		StringBuffer buffer = new StringBuffer();
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		buffer.append("Semaine ");
		buffer.append(c.get(Calendar.WEEK_OF_YEAR));
		buffer.append(" : du Lundi ");
		buffer.append(c.get(Calendar.DATE));
		buffer.append(" ");
		buffer.append(c.getDisplayName(Calendar.MONTH, 2, Locale.FRANCE));
		buffer.append(" ");
		buffer.append(c.get(Calendar.YEAR));
		
		Date endDate = DateService.addDays(startDate, 7);
		c.setTime(endDate);
		buffer.append(" au Lundi ");
		buffer.append(c.get(Calendar.DATE));
		buffer.append(" ");
		buffer.append(c.getDisplayName(Calendar.MONTH, 2, Locale.FRANCE));
		buffer.append(" ");
		buffer.append(c.get(Calendar.YEAR));
		
		return buffer.toString();
	}

	/**
	 * MODIFICATION : AJOUT d'un temps à une heure  
	 * @param heure
	 * @param ajout
	 * @return
	 */
	public synchronized static Date addTime(Date heure, Date ajout) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(heure.getTime() + getDecalageTimeStamp(null, ajout));
		return cal.getTime();
	}
	
	public synchronized static Date addTime(Date heure, Long ajout) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(heure.getTime() + ajout);
		return cal.getTime();
	}
	
	/**
	 * MODIFICATION : AJOUT d'une durée à un datetime (joda)
	 * @param heure
	 * @param ajout
	 * @return
	 */
	public static DateTime addTime(DateTime heure, Duration ajout) {
		return heure.plus(ajout);
	}
	
	/**
	 * MODIFICATION : AJOUT d'une durée à une durée (joda)
	 * @param heure
	 * @param ajout
	 * @return
	 */
	public static Duration addTime(Duration duree, Duration ajout) {
		return duree.plus(ajout);
	}
	
	/**
	 * MODIFICATION :  Conversion d'integer en Date
	 * @param heure
	 * @return
	 */
	public static Date setTime(long heure){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(heure);
		return cal.getTime();
	}
	/**
	 * MODIFICATION : Soustraction d'un temps à une date/heure  
	 * @param date
	 * @param tempsModif
	 * @return
	 */
	public synchronized static Date removeTime(Date date, Date tempsModif) {
		if(date == null || tempsModif == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date.getTime() - getDecalageTimeStamp(null, tempsModif));
		return cal.getTime();
	}
	
	public synchronized static Date removeTime(Date date, long tempsModif) {
		if(date == null )
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date.getTime() - tempsModif);
		return cal.getTime();
	}
	
	/**
	 * RECUPERATION : du temps en milliseconde entre les dates
	 * @param depart
	 * @param arrivee
	 * @return
	 */
	public synchronized static Long getDecalageTimeStamp(Date depart, Date arrivee){
		try{
			if(depart == null)
				depart = pattern1Formatter.parse("01/01/1970");
			
			return arrivee.getTime() - depart.getTime();
		}
		catch (Exception e) {
			return null;
		}
	}

	/**
	 * RECUPERATION : du temps en milliseconde entre les dates
	 * @param depart
	 * @param arrivee
	 * @return
	 */
	public static Long getDecalageTimeStamp(DateTime depart, DateTime arrivee){
		if(depart == null)
			return arrivee.getMillis() - arrivee.getZone().getOffset(arrivee.getMillis());
		else
		return arrivee.getMillis() - depart.getMillis();
	}

	/**
	 * Conversion d'un timestamp en String représentant heure
	 * @param ecart
	 * @return
	 */
	public synchronized static String getTime(Long ecart) throws Exception{
		Date depart = pattern1Formatter.parse("01/01/1970");
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(ecart + depart.getTime()); // ajout du offset par gmt
		
		return getTime(calendar.getTime(), null);
	}
	
	/**
	 * Conversion d'une duree timestamp en String représentant heure
	 * @param ecart
	 * @return
	 */
	public  static String getJodaTime(Long ecart) throws Exception{
		DateTimeZone dtz =  DateTimeZone.UTC;
		DateTime dt = new DateTime(ecart,dtz);
		return getTime(dt, null);
	}
	
	/**
	 * Conversion d'une timestamp en Date représentant heure
	 * @param ecart
	 * @return
	 */
	public synchronized static Date getDateTime(Long ecart) throws Exception{
		Date depart = pattern1Formatter.parse("01/01/1970");
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(ecart + depart.getTime()); // ajout du offset par gmt
		return calendar.getTime();
	}
	
	/**
	 * Conversion d'un decalage en Long
	 * @param arrivee
	 * @return
	 */
	public synchronized static Long getLongTime(Date arrivee) throws Exception{
		Date depart = pattern1Formatter.parse("01/01/1970");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(arrivee.getTime() - depart.getTime()); // ajout du offset par gmt
		
		return arrivee.getTime() - depart.getTime();
	}
	
	/**
	 * RECUPERATION : jour de paques
	 * @param annee
	 * @return
	 */
	private static Date getPaques(Integer annee){
		int g = (annee % 19) + 1;
		int c = annee / 100 + 1;
		int x = 3 * c / 4 - 12;
		int z = (8 * c + 5) / 25 - 5;
		int d = 5 * annee / 4 - x - 10;
		int e = (11 * g + 20 + z - x) % 30;
		if ( e == 25 && g > 11 || e == 24 )
			++e;
		int n = 44 - e;
		if (n < 21)
			n = n + 30;
		int j = n + 7 - ((d + n) % 7);
		if (j > 31)
			return strToDate(((j - 31) < 10 ? "0" : "") + (j - 31) + "/04/" + annee);
		else
			return strToDate(j + "/03/" + annee);
	}

	/**
	 * RECUPERATION : test si le jour est un veille/jour/lendemain d'un jour férié. Retour de l'id du jour férié
	 * @param today
	 * @param veilleFerie
	 * @return
	 */
	public static Integer getJourFerie(Date today, Integer lendeveille) {
		//si on veut voir si je jour est une veille de férié, on ajoute un jour avant test d'égalité
		if(PilotageConstants.VEILLE_FERIE.equals(lendeveille)){
			today = addDays(today, 1);
		}
		//si on veut voir si je jour est un lendemain de férié, on retire un jour avant test d'égalité
		else if(PilotageConstants.LENDEMAIN_FERIE.equals(lendeveille)){
			today = addDays(today, -1);
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		
		//jour de l'an
		if(calendar.get(Calendar.DAY_OF_MONTH) == 1 && calendar.get(Calendar.MONTH) == Calendar.JANUARY){
			return PilotageConstants.JOUR_AN;
		}
		//Fete du travail
		else if(calendar.get(Calendar.DAY_OF_MONTH) == 1 && calendar.get(Calendar.MONTH) == Calendar.MAY){
			return PilotageConstants.FETE_TRAVAIL;
		}
		//Armistice 8 mai
		else if(calendar.get(Calendar.DAY_OF_MONTH) == 8 && calendar.get(Calendar.MONTH) == Calendar.MAY){
			return PilotageConstants.ARMISTICE_8_MAI;
		}
		//Fete nationale
		else if(calendar.get(Calendar.DAY_OF_MONTH) == 14 && calendar.get(Calendar.MONTH) == Calendar.JULY){
			return PilotageConstants.FETE_NATIONAL;
		}
		//Assomption
		else if(calendar.get(Calendar.DAY_OF_MONTH) == 15 && calendar.get(Calendar.MONTH) == Calendar.AUGUST){
			return PilotageConstants.ASSOMPTION;
		}
		//Toussaint
		else if(calendar.get(Calendar.DAY_OF_MONTH) == 1 && calendar.get(Calendar.MONTH) == Calendar.NOVEMBER){
			return PilotageConstants.TOUSSAINT;
		}
		//Armistice 11 nov
		else if(calendar.get(Calendar.DAY_OF_MONTH) == 11 && calendar.get(Calendar.MONTH) == Calendar.NOVEMBER){
			return PilotageConstants.ARMISTICE_11_NOV;
		}
		//Noel
		else if(calendar.get(Calendar.DAY_OF_MONTH) == 25 && calendar.get(Calendar.MONTH) == Calendar.DECEMBER){
			return PilotageConstants.NOEL;
		}
		//Lendemain Noel
		else if(calendar.get(Calendar.DAY_OF_MONTH) == 26 && calendar.get(Calendar.MONTH) == Calendar.DECEMBER){
			return PilotageConstants.LENDEMAIN_NOEL;
		}
		
		Date paques = getPaques(calendar.get(Calendar.YEAR));
		//lundi de paques
		if(today.equals(addDays(paques, 1))){
			return PilotageConstants.LUNDI_PAQUES;
		}
		//vendredi de paques
		else if(today.equals(addDays(paques, -2))){
			return PilotageConstants.VENDREDI_PAQUES;
		}
		//Jeudi de l'ascension
		else if(today.equals(addDays(paques, 39))){
			return PilotageConstants.JEUDI_ASCENSION;
		}
		//lundi de pentecote
		else if(today.equals(addDays(paques, 50))){
			return PilotageConstants.LUNDI_PENTECOTE;
		}
		
		return null;
	}
	
	/**
	 * RECUPERATION : parité de la semaine
	 * @param today
	 * @return
	 */
	public static Integer getWeekParite(Date today) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		
		return calendar.get(Calendar.WEEK_OF_YEAR) % 2 == 0 ? PilotageConstants.PAIR : PilotageConstants.IMPAIR;
	}
	
	/**
	 * RECUPERATION : du jour de la semaine
	 * @param today
	 * @return
	 */
	public static Integer getJour(Date today) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);

		int jour = calendar.get(Calendar.DAY_OF_WEEK);
		if(jour == Calendar.MONDAY)
			return PilotageConstants.LUNDI;
		else if(jour == Calendar.TUESDAY)
			return PilotageConstants.MARDI;
		else if(jour == Calendar.WEDNESDAY)
			return PilotageConstants.MERCREDI;
		else if(jour == Calendar.THURSDAY)
			return PilotageConstants.JEUDI;
		else if(jour == Calendar.FRIDAY)
			return PilotageConstants.VENDREDI;
		else if(jour == Calendar.SATURDAY)
			return PilotageConstants.SAMEDI;
		else if(jour == Calendar.SUNDAY)
			return PilotageConstants.DIMANCHE;

		return null;
	}

	/**
	 * RECUPERATION : du numéro du jour (1er/2eme/... lundi,....) dans le mois 
	 * @param today
	 * @return
	 */
	public static Integer getPlaceOfDayInMonth(Date today) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		
		Calendar index = Calendar.getInstance();
		index.setTime(today);
		index.set(Calendar.DAY_OF_MONTH, 1);
		
		//on positionne index sur le 1er même jour que today
		while(calendar.get(Calendar.DAY_OF_WEEK) != index.get(Calendar.DAY_OF_WEEK)){
			index.add(Calendar.DATE, 1);
		}
		
		//on avance de semaine en semaine en incrémentant
		int place = 1;
		while(index.before(calendar)){
			index.add(Calendar.DATE, 7);
			place++;
		}
		
		return place;
	}

	/**
	 * RECUPERATION : teste si on est dans le dernier de ce jour (Lundi, ...) du mois 
	 * @param today
	 * @return
	 */
	public static boolean isLastPlaceOfDayInMonth(Date today) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		int mois = calendar.get(Calendar.MONTH);
		calendar.add(Calendar.DATE, 7);
		
		if(mois == calendar.get(Calendar.MONTH))
			return false;
		else
			return true;
	}
	
	/**
	 * CONVERSION : de l'heure passé en paramètre au format avec l'heure GMT 
	 * @param horaire
	 * @return
	 */
	public synchronized static Date getHeureGMT(Date horaire){
		try{
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			format.setTimeZone(TimeZone.getTimeZone("GMT"));
			
			return patternDateTime.parse(format.format(horaire));
		}
		catch (Exception e) {
			return null;
		}
	}
	
	public static void main(String[] args) {
		
		DateService.convertFromTimeZoneToTimeZone(new Date(), TimeZone.getTimeZone("Europe/Amsterdam"), null);
	}

	/**
	 * RECUPERATION : de la date avec l'heure setté
	 * @param jour
	 * @param heureExecution
	 * @return
	 */
	public static Date getDateHeure(Date jour, Date heureExecution) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(jour);
		
		Calendar calendarHeure = Calendar.getInstance();
		calendarHeure.setTime(heureExecution);
		
		calendar.set(Calendar.HOUR_OF_DAY, calendarHeure.get(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendarHeure.get(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendarHeure.get(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendarHeure.get(Calendar.MILLISECOND));
		return calendar.getTime();
	}

	
	/**
	 * MODIFICATION : conversion de la date d'un timezone à l'autre
	 * @param date
	 * @param tzFrom
	 * @param tzTo
	 * @return
	 */
	public static Date convertFromTimeZoneToTimeZone(Date date, TimeZone tzFrom, TimeZone tzTo){
		if(tzFrom == null)
			tzFrom = TimeZone.getTimeZone("GMT");
		
		if(tzTo == null)
			tzTo = TimeZone.getTimeZone("GMT");
		
		if(tzFrom.equals(tzTo))
			return date;
		
		if(date == null)
			return null;
		//on récupère la date pour le parser
		Calendar serverCalendar = Calendar.getInstance();
		serverCalendar.setTime(date);
		
		//on fabrique la date avec le timezone From
		Calendar clientCalendar = Calendar.getInstance();
		clientCalendar.setTimeZone(tzFrom);
		clientCalendar.set(Calendar.DATE, serverCalendar.get(Calendar.DATE));
		clientCalendar.set(Calendar.MONTH, serverCalendar.get(Calendar.MONTH));
		clientCalendar.set(Calendar.YEAR, serverCalendar.get(Calendar.YEAR));
		clientCalendar.set(Calendar.HOUR_OF_DAY, serverCalendar.get(Calendar.HOUR_OF_DAY));
		clientCalendar.set(Calendar.MINUTE, serverCalendar.get(Calendar.MINUTE));
		clientCalendar.set(Calendar.SECOND, serverCalendar.get(Calendar.SECOND));
		
		//on fabrique la date avec le timezone To
		DateFormat dfTo = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		dfTo.setTimeZone(tzTo);
		DateFormat dfLocal = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date dateTo = null;
		try {
			dateTo = dfLocal.parse(dfTo.format(clientCalendar.getTime()));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		return dateTo;
	}
	
	/**
	 * TEST : retourne si la date en paramètre est pris en compte pour le jour actuel. Le critère est qu'un jour est de 7h30 à 7h30
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isCurrentDay730(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date.getTime());
		
		Calendar cBefore = Calendar.getInstance();
		cBefore.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 7, 30, 0);
		cBefore.set(Calendar.MILLISECOND, 0);
		
		return c.compareTo(cBefore) >= 0;
	}
	
	/**
	 * TEST : retourne si la date en paramètre est pris en compte pour le mois actuel. Le critère est qu'un mois est de 7h30 au prochain mois 7h30
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isCurrentMonth730(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date.getTime());
		
		if(c.get(Calendar.DATE) != 1){
			return true;
		}
		else{
			Calendar cBefore = Calendar.getInstance();
			cBefore.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 7, 30, 0);
			cBefore.set(Calendar.MILLISECOND, 0);
			
			return c.compareTo(cBefore) >= 0;
		}
	}
	
	/**
	 * MODIFICATION : retourne le prochain jour correspondant à 7h30
	 * Par exemple si nous sommes le 2011-06-06 5:30, la méthode retourne 2011-06-06 7:30
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateLater(Date date) throws ParseException {
		Date result;
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		c.setTime(date);
		StringBuilder sb = new StringBuilder();

		if (isCurrentDay730(date)) {
			sb.append(Integer.toString(c.get(Calendar.YEAR)));
			sb.append("-");
			sb.append(Integer.toString(c.get(Calendar.MONTH) + 1));
			sb.append("-");
			sb.append(Integer.toString(c.get(Calendar.DATE) + 1));
			sb.append(" 7:29:59:999");

		} 
		else {
			sb.append(Integer.toString(c.get(Calendar.YEAR)));
			sb.append("-");
			sb.append(Integer.toString(c.get(Calendar.MONTH) + 1));
			sb.append("-");
			sb.append(Integer.toString(c.get(Calendar.DATE)));
			sb.append(" 7:29:59:999");
		}
		result = sdf.parse(sb.toString());
		
		return result;
	}
	
	/**
	 * MODIFICATION : retourne le prochain mois correspondant à 7h30
	 * Par exemple si nous sommes le 2011-06-06 5:30, la méthode retourne 2011-07-01 7:30
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getMonthLater(Date date) throws ParseException {
		Date result;
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		c.setTime(date);
		StringBuilder sb = new StringBuilder();

		if (isCurrentMonth730(date)) {
			sb.append(Integer.toString(c.get(Calendar.YEAR)));
			sb.append("-");
			sb.append(Integer.toString(c.get(Calendar.MONTH) + 2));
			sb.append("-");
			sb.append(1);
			sb.append(" 7:29:59:999");

		} 
		else {
			sb.append(Integer.toString(c.get(Calendar.YEAR)));
			sb.append("-");
			sb.append(Integer.toString(c.get(Calendar.MONTH) + 1));
			sb.append("-");
			sb.append(1);
			sb.append(" 7:29:59:999");
		}
		result = sdf.parse(sb.toString());
		
		return result;
	}
	
	/**
	 * MODIFICATION : retourne le jour précédent correspondant à 7h30
	 * Par exemple si on est le 2011-06-06 5:30 la méthode retourne 2011-06-05 7:30
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateEarlier(Date date) throws ParseException {
		Date result;
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		c.setTime(date);
		StringBuilder sb = new StringBuilder();

		if (isCurrentDay730(date)) {
			sb.append(Integer.toString(c.get(Calendar.YEAR)));
			sb.append("-");
			sb.append(Integer.toString(c.get(Calendar.MONTH) + 1));
			sb.append("-");
			sb.append(Integer.toString(c.get(Calendar.DATE)));
			sb.append(" 7:30:00:000");

		} 
		else {
			sb.append(Integer.toString(c.get(Calendar.YEAR)));
			sb.append("-");
			sb.append(Integer.toString(c.get(Calendar.MONTH) + 1));
			sb.append("-");
			sb.append(Integer.toString(c.get(Calendar.DATE) - 1));
			sb.append(" 7:30:00:000");
		}
		result = sdf.parse(sb.toString());
		
		return result;
	}
	
	/**
	 * MODIFICATION : retourne le 1er jour du mois correspondant à 7h30
	 * Par exemple si on est le 2011-06-06 5:30 la méthode retourne 2011-06-01 7:30
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getMonthEarlier(Date date) throws ParseException {
		Date result;
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		c.setTime(date);
		StringBuilder sb = new StringBuilder();

		if (isCurrentMonth730(date)) {
			sb.append(Integer.toString(c.get(Calendar.YEAR)));
			sb.append("-");
			sb.append(Integer.toString(c.get(Calendar.MONTH) + 1));
			sb.append("-");
			sb.append(1);
			sb.append(" 7:30:00:000");

		} 
		else {
			sb.append(Integer.toString(c.get(Calendar.YEAR)));
			sb.append("-");
			sb.append(Integer.toString(c.get(Calendar.MONTH)));
			sb.append("-");
			sb.append(1);
			sb.append(" 7:30:00:000");
		}
		result = sdf.parse(sb.toString());
		
		return result;
	}
	
	/**
	 * TEST : si le jour en paramètre est le jour actuel
	 * 
	 * @param selectedDate
	 * @return
	 */
	public static boolean isToday(Date date) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		
		Calendar ct = Calendar.getInstance();
		return (cd.get(Calendar.YEAR) == ct.get(Calendar.YEAR) && cd.get(Calendar.DAY_OF_YEAR) == ct.get(Calendar.DAY_OF_YEAR));
	}
	
	/**
	 * RECUPERATION : du 1er jour du mois passé en paramètre
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) throws Exception{
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		cd.set(Calendar.DAY_OF_MONTH,1);
		
		return cd.getTime();
	}
	
	/**
	 * RECUPERATION : du dernier jour du mois passé en paramètre
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date) throws Exception{
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		
		int monthEnd = cd.getActualMaximum(Calendar.DAY_OF_MONTH);
		cd.set(Calendar.DATE, monthEnd);
		
		return cd.getTime();
	}

	/**
	 * RECUPERATION : du numéro du jour
	 * @param selectedDate
	 * @return
	 */
	public static int getDayOfMonth(Date selectedDate) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(selectedDate);
		
		return cd.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * RECUPERATION : du nombre de jour dans le mois
	 * @param selectedDate
	 * @return
	 */
	public static int getNbDaysInMonth(Date selectedDate) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(selectedDate);
		
		return cd.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * RECUPERATION du mois sous forme de string
	 * @param selectMois
	 * @return
	 */
	public static String getMonthString(Date selectMois) {
		return patternMonthFormatter .format(selectMois);
	}
	
	/**
	 * RECUPERATION du 1er jour de la semaine
	 * @param date
	 * @return
	 * @throws Exception 
	 */
	public static Date getWeekStart(Date date) throws Exception{
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		cd.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cd.getTime();
	}
	
	/**
	 * RECUPERATION du dernier jour de la semaine
	 * @param date
	 * @return
	 * @throws Exception 
	 */
	public static Date getWeekEnd(Date date) throws Exception{
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		cd.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return cd.getTime();
	}
	
	/**
	 * RECUPERATION des dates de la semaine contenant le paramètre
	 * @param date
	 * @return
	 */
	public static List<Integer> getWeekDate(Date date) {
		List<Integer> result = new ArrayList<Integer>();
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		cd.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		result.add(cd.get(Calendar.DATE));
		cd.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		result.add(cd.get(Calendar.DATE));
		cd.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		result.add(cd.get(Calendar.DATE));
		cd.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		result.add(cd.get(Calendar.DATE));
		cd.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		result.add(cd.get(Calendar.DATE));
		cd.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		result.add(cd.get(Calendar.DATE));
		cd.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		result.add(cd.get(Calendar.DATE));
		
		return result;
	}
	
	/**
	 * RECUPERATION du jour de la semaine contenant selectedDate et identifié par dayOfWeek
	 * Lundi : dayOfWeek = 0
	 * Vendredi : dayOfWeek = 4
	 * @param selectedDate
	 * @param dayOfWeek
	 * @return
	 */
	public static Date getByDayOfWeek(Date selectedDate, int dayOfWeek) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(selectedDate);
		cd.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cd.add(Calendar.DATE, dayOfWeek);
		return cd.getTime();
	}
	
}
