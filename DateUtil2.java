
public class DateUtil extends com.feinno.bp.core.utils.DateUtil {

	/** yyyyMMddHHmmssSSS000 */
	public static final String	DEFAULT_DATE_FORMAT_MICROSEC	= "yyyyMMddHHmmssSSS000";

	/** 微秒 */
	public static final String	DEFAULT_DATE_FORMAT_MILL		= "yyyyMMddHHmmssSSS";

	/**
	 * 得到当前时间的格式化(精确到毫秒) "yyyyMMddHHmmssSSS000"
	 * 
	 * @param formatStr
	 * @return
	 */
	public static String getSystemCurrentDate_microSec() {
		return date2Str(Calendar.getInstance().getTime(), DEFAULT_DATE_FORMAT_MICROSEC);
	}

	/**
	 * 计算给定时间字符串strDate的lms之前的微秒级的时间字符串。
	 * 
	 * @param l
	 * @return
	 * @author <a href="mailto:lemom8000@gmail.com">zhangyan</a>
	 * @throws ParseException
	 * @sinc 2009-7-15
	 */
	public static String getDateStrBeforeMS(String strDate, long l) throws ParseException {
		SimpleDateFormat sdformat = new SimpleDateFormat(DEFAULT_DATE_FORMAT_MILL);
		Date date = sdformat.parse(strDate);
		return date2Str(new Date(date.getTime() - l), DEFAULT_DATE_FORMAT_MICROSEC);
	}

	/**
	 * @param nowSysDateTime
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 * @author <a href="mailto:lemom8000@gmail.com">zhangyan</a>
	 * @sinc 2009-3-24
	 */
	public static boolean compareThreeDate(Date nowSysDateTime, Date startTime, Date endTime) {
		return nowSysDateTime.compareTo(startTime) >= 0 && nowSysDateTime.compareTo(endTime) <= 0;
	}

	/**
	 * 取得下个月的一号
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getFirstDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);// 日，设为一号
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.add(Calendar.MONTH, 1);// 月份加一，得到下个月的一号
		return date2Str(cal.getTime(), DEFAULT_DATE_FORMAT);
	}

	/**
	 * 得到上一个月
	 * 
	 * @return
	 */
	public static String getLastMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		return date2Str(cal.getTime(), "yyyyMM");
	}
	/**在现在时间基础上增加addday天*/
	public static String dateadd(String dateformat, int addday) {
		SimpleDateFormat df = new SimpleDateFormat(dateformat);
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		calendar.add(Calendar.DATE, addday);
		return df.format(calendar.getTime());
	}

}
