package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimestampsConvert {

	public static long dateToTimestamps(String inputDate) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date parsedDate = dateFormat.parse(inputDate);
			return parsedDate.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public static String timestampsToDate(long inputTimestamps, String pattern) {
		long timestamp = inputTimestamps;
		Instant instant = Instant.ofEpochMilli(timestamp);
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		String formattedDate = localDateTime.format(formatter);
		return formattedDate;
	}
}
