package pages;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class test {
	public static void main(String[] args) {
		 LocalDate currentDate = LocalDate.now();
		 
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
		 
		 String formattedDateTime = currentDate.format(formatter);
		 System.out.println("current date formatted: "+ currentDate.format(formatter).toString());
	        // Add 2 days to the current date
	        LocalDate newDate = currentDate.plusDays(2);
	        
	        System.out.println("Date after adding 2 days: " + newDate.format(formatter).toString());
	}
}
