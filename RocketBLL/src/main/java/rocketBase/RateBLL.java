package rocketBase;

import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.*;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	public static double getRate(int GivenCreditScore) throws RateException 
	{
		double dInterestRate = 0;
		
		
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		
		for(int i = 1; i<rates.size();i++){
			if(GivenCreditScore < rates.get(i).getiMinCreditScore()){
				if(GivenCreditScore < rates.get(1).getiMinCreditScore()){//if it is below the lowest minimum
					RateDomainModel badRate = rates.get(i);
					throw new RateException(badRate);//throw an exception with the credit score below the minimum
				}
				else
					dInterestRate = rates.get(i-1).getdInterestRate();
			}
		
		}
		
		if(dInterestRate == 0){
			//must have been the best rate
			dInterestRate = rates.get(rates.size()-1).getdInterestRate();
		}
		
		return dInterestRate;
		
		
	}
	
	
	
	
	public static double getPayment(double r, double n, double p, double f, boolean t)
	{		
		return FinanceLib.pmt(r, n, p, f, t);
	}
}
