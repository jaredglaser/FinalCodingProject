package rocket.app.view;

import java.net.URL;
import java.util.ResourceBundle;

import eNums.eAction;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import netgame.common.Client;
import rocket.app.MainApp;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController implements Initializable {

	@FXML TextField txtCreditScore;
	@FXML Label lblCalcPayment;
	@FXML TextField txtHouseCost;
	@FXML TextField txtDownPayment;
	@FXML TextField txtAnnualIncome;
	@FXML TextField txtMonthlyExpenses;
	@FXML ComboBox<Integer> cmboTerm = new ComboBox<Integer>();
	private TextField txtNew;

	private MainApp mainApp;


	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cmboTerm.getItems().removeAll(cmboTerm.getItems());
		cmboTerm.getItems().addAll(15,30);
		cmboTerm.getSelectionModel().select(15);
	}


	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		LoanRequest request = new LoanRequest();
		request.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		request.setExpenses(Integer.parseInt(txtMonthlyExpenses.getText()));
		request.setdAmount(Integer.parseInt(txtHouseCost.getText()));
		request.setIncome(Integer.parseInt(txtAnnualIncome.getText()));
		request.setiDownPayment(Integer.parseInt(txtDownPayment.getText()));
		request.setiTerm(cmboTerm.getValue());
		mainApp.messageSend(request);
		
	}

	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		//grab the payment
		double payment = lRequest.getdPayment();
		//round it
		payment = Math.round(payment * 100.0) / 100.0;
		System.out.println(payment);


		double incomePerMonth = lRequest.getIncome()/12;

		if((incomePerMonth*.28)>payment||((incomePerMonth*.36)-lRequest.getExpenses())>payment){
			//display it
			lblCalcPayment.setText(String.valueOf(payment));
		}
		else{
			lblCalcPayment.setText("House Cost is Too High");
		}
	}
}
