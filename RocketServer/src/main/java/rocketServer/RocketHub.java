package rocketServer;

import java.io.IOException;

import exceptions.RateException;
import netgame.common.Hub;
import rocketBase.RateBLL;
import rocketData.LoanRequest;


public class RocketHub extends Hub {

	private RateBLL _RateBLL = new RateBLL();

	public RocketHub(int port) throws IOException {
		super(port);
	}

	@Override
	protected void messageReceived(int ClientID, Object message) {
		System.out.println("Message Received by Hub");
		double rate = 0;
		boolean goodRate = false;
		if (message instanceof LoanRequest) {
			resetOutput();

			LoanRequest lq = (LoanRequest) message;

			//Determine rate
			try {
				rate = RateBLL.getRate(lq.getiCreditScore());
				goodRate = true;
			} catch (RateException e) {
				System.out.println("Rate does not exist for a credit score of " +e.getBadRate().getiMinCreditScore());
			}
			System.out.println(goodRate);
			if(goodRate){
				//Determine payment with rate,iTerm(in years)*12, Value of home - down payment, $0, false
				lq.setdPayment(Math.abs(RateBLL.getPayment(rate*.01/12, lq.getiTerm()*12, lq.getdAmount(), 0, false)));
		
				
				sendToAll(lq);
			}
		}
	}
}
