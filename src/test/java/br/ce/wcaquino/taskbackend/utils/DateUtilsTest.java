package br.ce.wcaquino.taskbackend.utils;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void testIsEqualOrFutureDate_FutureDate_ReturnsTrue() {
		
		LocalDate date = LocalDate.of(2030, 01, 01);
		
		Assert.assertTrue( DateUtils.isEqualOrFutureDate(date) );
				
	}
	
	
	@Test
	public void testIsEqualOrFutureDate_PastDate_ReturnsFalse() {
		
		LocalDate date = LocalDate.of(2010, 01, 01);
		
		Assert.assertFalse( DateUtils.isEqualOrFutureDate(date) );
				
	}	
	
	@Test
	public void testIsEqualOrFutureDate_PresentDate_ReturnsTrue() {
		
		LocalDate date = LocalDate.now();
		
		Assert.assertTrue( DateUtils.isEqualOrFutureDate(date) );
				
	}	
	
		
	
}
