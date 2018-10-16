package java8;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.logging.Logger;

public class Datetime {

	/**
	 * 时间偏移，按自然月偏移。 举例：入参为2018.1.15 18：30：20，向未来偏移1月，则结果是2018：2.28 23：59：59。
	 * 
	 * @param date
	 *            需要处理的时间
	 * @param n
	 *            偏移月数，正数为向未来偏移，负数为向过去偏移
	 * @return 偏移后的时间（取月底最后一天的23点59分59秒）
	 */
	public static Date moveNatureMonths(Date date, int n) {
		LocalDateTime dateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime dateTimeMonth = dateTime.plusMonths(n);
		LocalDateTime dateTimeDay = dateTimeMonth.with(TemporalAdjusters.lastDayOfMonth());
		LocalDateTime resultTime = dateTimeDay.withHour(23).withMinute(59).withSecond(59).withNano(0);
		Instant instant = resultTime.atZone(ZoneId.systemDefault()).toInstant();
		Date resultDate = Date.from(instant);
		return resultDate;
	}

	public static void main(String[] args) throws Exception {
		//System.out.println(moveNatureMonths(new Date(), 1));
		betweenTime();
	}

	/**
	 * 复杂
	 */
	public static void betweenDateTime() throws InterruptedException {
		LocalDateTime startTime = LocalDateTime.now();
		Thread.sleep(500);
		LocalDateTime finishedTime = LocalDateTime.now();
		Instant startInstant = startTime.atZone(ZoneId.systemDefault()).toInstant();
		Instant endInstant = finishedTime.atZone(ZoneId.systemDefault()).toInstant();
		Duration duration = Duration.between(startInstant, endInstant);
		Logger.getGlobal().info(() -> duration.getSeconds() +"." + duration.getNano()); 
	}
	
	/**
	 * 简单
	 */
	public static void betweenTime() throws InterruptedException {
		LocalDateTime startTime = LocalDateTime.now();
		Thread.sleep(500);
		LocalDateTime finishedTime = LocalDateTime.now();
		Duration duration = Duration.between(startTime, finishedTime);
		Logger.getGlobal().info(() -> duration.getSeconds() +"." + duration.getNano()); 
	}

}
