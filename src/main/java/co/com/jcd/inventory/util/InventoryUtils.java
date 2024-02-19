package co.com.jcd.inventory.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InventoryUtils {
	
	public static String generateDate() {
		LocalDateTime currentLocalDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = 
        		DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentLocalDateTime.format(dateTimeFormatter);        
	}

}
