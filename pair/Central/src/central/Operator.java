package central;
import structures.*;
import tools.Output;

public class Operator {

	private Central central;
	private int operatorNumber = -1; //the operators number
	//Variables for voice communication //
	private boolean voiceAlive = false;// the voice communication is established
	private boolean voiceConnecting = false; // the voice communication will be build up actually
	private boolean voiceSend = false; // i am allowed to talk
	
	public Operator(Central central) {
		this.central = central;
	}

	public void acceptOrder(int orderNumber, int taxiNumber, int zoneNumber, int positionNumber, int estimateTime) {
		central.confirmOrderTM(orderNumber,operatorNumber,taxiNumber);
	}


	public void confirmVoice(int taxiNumber) {
		
	}

	public void confirmVoiceC(int taxiNumber) {

	}

	public void loginC(int operatorNumber, String password) {
		if(operatorNumber<1) Output.printError("loginC:operatorNumber invalid");
		else if(password == null) Output.printError("loginC:password invalid");
		else{
			this.operatorNumber = operatorNumber;
			central.login(operatorNumber,this,password);
		}
	}

	public void loginFailure() {
		Output.printError("Login not successful.");	//
	}

	public void loginOk() {
		System.out.println("Login successful");	//
	}

	public void logoutC() {
		central.logout(this.operatorNumber);	//
	}

	public void logoutOk() {
		System.out.println("Operator wurde ausgeloggt!");
		
	}
	
	public void logoutFailure() {
		Output.printError("Operator konnte nicht ausgeloggt werden!");
	}

	public void manualDispatchOrder(int taxiNumber, OrderStructC order) {
 		order.manualDispatch = true;
 		TaxiStruct ts = central.getFirstAvailableTaxi();
 		if(ts == null){
 			order.dispatchTime = order.dispatchTime+2;	
 			Output.printError("No taxi available.");
 		}
 		else {
 			order.taxiNumber = ts.taxiNumber;
 			order.dispatchTime = 0;
 			System.out.println("Operator manual dispatches order "+order.orderNumber+" to taxi "+ts.taxiNumber);
 		}
 		central.newOrderTM(order,operatorNumber);
	}

	public void newOrder(int orderNumber, int taxinumber) {
		central.confirmOrderTM(orderNumber,this.operatorNumber,taxinumber);
	}

	public void newOrderC(OrderStructC order) {
		central.newOrderTM(order,this.operatorNumber);
	}

	public void resetAlarmC(int taxiNumber) {
		
	}

	public void sendVoice(int taxiNumber) {
		
	}

	public void sendVoiceC(int taxiNumber) {
		central.sendVoiceTM(taxiNumber,this.operatorNumber);
		
	}

	public void startVoice(int taxiNumber){
			
	}
	
	public void startVoiceC(int taxiNumber) {
		if(taxiNumber < 1) Output.printError("taxiNumber invalid.");
		else{
			if(voiceSend  && voiceAlive && !voiceConnecting){
				central.startVoiceTM(taxiNumber,operatorNumber);
			}	
			else
				System.out.println("Not allowed to speak on channel");
		}
	}

	public void stopVoice(int taxiNumber) {
		
	}

	public void stopVoiceC(int taxiNumber) {
		
	}

	public void terminateVoice(int taxiNumber) {
	
	}

	public void terminateVoiceC() {
		
	}

	public void textMessageC(int[] taxiNumber, int messageNumber, String textMessage) {
		boolean variablesok = true;
		for(int i = 0;i<taxiNumber.length;i++)
			if(taxiNumber[i] < 1){
				Output.printError("taxiNumber invalid.");
				variablesok = false;
			}
		if(variablesok==true)
			central.textMessageTM(taxiNumber,messageNumber,textMessage,this.operatorNumber);
	}

	public void voiceMessage(int taxiNumber, String voicedata) {
		System.out.println("Taxi "+taxiNumber+ " to Operator "+operatorNumber+":"+voicedata);
	}

	public void voiceMessageC(int taxiNumber, String voicedata) {
	
	}
	public void ackMessage(int taxiNumber, int messageNumber){
		System.out.println("Message mit der Nummer= "+messageNumber+" wurde an das Taxi mit der Taxinummer = "+ taxiNumber+" gesendet.");
	}
}