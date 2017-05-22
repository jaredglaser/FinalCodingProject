package exceptions;

import rocketDomain.RateDomainModel;

public class RateException extends Exception {

	private RateDomainModel badRate= new RateDomainModel();
	//	TODO - RocketBLL RateException - RateDomainModel should be an attribute of RateException
	//	* Add RateRomainModel as an attribute
	//	* Create a constructor, passing in RateDomainModel
	//	* Create a getter (no setter, set value only in Constructor)
	
	public RateException(RateDomainModel badRate){
		this.badRate = badRate; 
	}

	public RateDomainModel getBadRate() {
		return badRate;
	}

}
