package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import rocketDomain.RateDomainModel;

public class Rate_Test {
	
	ArrayList <RateDomainModel> rates= new ArrayList<RateDomainModel>();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		//grab all the rates from the server
		rates = RateDAL.getAllRates();
	}

	@After
	public void tearDown() throws Exception {
		//nothing is modified server-side. No need for tear down.
	}

	@Test
	public void testOrder() { 
		boolean inOrder = true;
		//rates should come in order
		for(int i = 1; i<=rates.size()-1;i++){
			if(rates.get(i).getiMinCreditScore() < rates.get(i-1).getiMinCreditScore()){
				//out of order
				inOrder = false;
			}
		}
		
		assertTrue(inOrder);
	}
	
}
