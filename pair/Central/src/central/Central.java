package central;
import structures.*;
import tools.*;
import proxy.*;
import comLink.ComLinkTM;
import java.util.*;

public class Central {
	
	//**** LET THIS PUBLIC ***/
	public ComLinkTM comLinkTM;
	
	private DriverDataProxy ddp = null;
	private OrderDataProxy odp = null;
	private OperatorDataProxy operatordp = null;
	public TaxiDataProxy tdp = null;
	
	private int nextOrderNumber; //Number of the next Order
	private double pricepersecond = 1; // Price per second
	
	private OrderDispatchThread odt = null;
	
	public Central() {
		odp = new OrderDataProxy();
		tdp = Import.readTaxis("taxi.txt");
		ddp = Import.readDriver("driver.txt");
		operatordp = new OperatorDataProxy();
		nextOrderNumber = 1;
		
		//** Set the signal at the ComLink for testing **//
		comLinkTM = new ComLinkTM(this,0);
		/* This part starts the OrderdispatchThread (see it at the
		designdocument). If you need it just remove the
		comment.
		odt = new OrderDispatchThread(odp, tdp, comLinkTM, operatordp);
		odt.start();
		*/
	}
	

	public void acceptOrderO(int orderNumber, int operatorNumber, int taxiNumber, int zoneNumber,
			int estimateTime) {
	
	}
	

	public void ackMessageO(int taxiNumber, int messageNumber, int operatorNumber) {
		if(tdp.get(taxiNumber).status==TaxiStruct.OFFLINE ||
				tdp.get(taxiNumber)==null)
			Output.printError("Taxi not logged in. Cannot perform operation.");
		else if (operatordp.get(operatorNumber) == null)
			Output.printError("Operator mot logged in, cannot perform operation.");
		else{
			operatordp.get(operatorNumber).ackMessage(taxiNumber, messageNumber);
		}
	}
	
	public void cancelOrder(int taxiNumber, int orderNumber) {

	}
	
	public void confirmOrderTM(int orderNumber, int operatorNumber, int taxiNumber) {
		if (operatordp.get(operatorNumber) == null) 
			Output.printError("Operator not logged in.");
		else if(tdp.get(taxiNumber)==null) 
			Output.printError("Taxi not logged in. Cannot perform operation.");
		else if(odp.get(orderNumber)==null)
			Output.printError("OrderNumber invalid.");
		else {
			TaxiStruct ts = tdp.get(taxiNumber);
			if (ts.status == TaxiStruct.AVAILABLE) {
				System.out.println("Taxi " + taxiNumber + " is driving order "+ orderNumber+".");
				ts.status = TaxiStruct.WAITING_FOR_CUSTOMER;
				tdp.update(ts);
				comLinkTM.confirmOrder(orderNumber, operatorNumber, taxiNumber);
			}
		}	
	}
	
	public void confirmVoiceO(int taxiNumber, int operatorNumber) {
		if (operatordp.get(operatorNumber) == null)
			Output.printError("Operator not logged in, cannot perform operation.");
		else if(tdp.get(taxiNumber)==null)
			Output.printError("Taxi not logged in. Cannot perform operation.");
		else {
			Operator op = operatordp.get(operatorNumber);
			if (op!=null) {
				op.confirmVoice(taxiNumber);
			}
			
		}
			
	
	}

	public void confirmVoiceTM(int taxiNumber, int operatorNumber) {

	}

	public void detailedPosition(int taxiNumber, int zoneNumber, int position) {
		TaxiStruct ts = tdp.get(taxiNumber);
		ts.zone = zoneNumber;
		ts.position = position;
		tdp.update(ts);
	}

	public void driverAvailable(String taxiNumber) {

	}

	public void driverSoonAvailable(int taxiNumber) {
		if(tdp.get(taxiNumber).status==TaxiStruct.OFFLINE ||
				tdp.get(taxiNumber)==null)
			Output.printError("Taxi not logged in. Cannot perform operation.");
		else{
			TaxiStruct ts = tdp.get(taxiNumber);
			if (ts.status == TaxiStruct.DRIVING_A_CUSTOMER) {
				ts.status = TaxiStruct.SOON_AVAILABLE;
				tdp.update(ts);
			}
		}
	}

	public TaxiStruct getFirstAvailableTaxi() {
		return null;
	}

	private int getRandomOperatorNumber() {
		int nr = 0;
		Enumeration e = operatordp.numbers();
		Vector v = new Vector();
		while (e.hasMoreElements()) {
			v.add(e.nextElement());
		}
		nr = ((Integer) v.get(Randomizer.getInt(v.size()-1))).intValue();
		return nr;
	}

	public void login(int operatorNumber, Operator operator, String password) {
		Hashtable passwords = Import.readOperators("operator.txt");
		if (password.equals((String) passwords.get(new Integer(operatorNumber)))) {
			operatordp.add(operatorNumber, operator);
			operator.loginOk();
		}
		else
			operator.loginFailure();
	}

	public void loginSignal(int taxiNumber, int driverNumber) {
		TaxiStruct ts = tdp.get(taxiNumber);
		if ( (ts != null) && (ts.status == TaxiStruct.OFFLINE) &&
				(ddp.get(driverNumber) != null)) {
			ts.driverNumber = driverNumber;
			ts.status = TaxiStruct.AVAILABLE;
			tdp.update(ts);
			comLinkTM.loginOk(taxiNumber);
		}
		else
			comLinkTM.loginFailure(taxiNumber);
	}

	public void logout(int operatorNumber) {
		Operator op = operatordp.get(operatorNumber);
		if (op == null) {
			Output.printError("The operator is not logged in anymore.");
			
		} else {
			operatordp.remove(operatorNumber);
			Operator op = operatordp.get(operatorNumber);
			if (op == null) {
				op.logoutOk();
			} else {
				op.logoutFailure();
			}
		}

	}

	public void logoutSignal(int taxiNumber, int driverNumber) {
		TaxiStruct ts = tdp.get(taxiNumber);
		if ( (ts != null) && (ts.status = TaxiStruct.AVAILABLE) ){
			ts.status = TaxiStruct.OFFLINE;
			tdp.update(ts);
			ddp.remove(driverNumber);
			comLinkTM.logoutOk();
		}
		else
			comLinkTM.logoutFailure();
	}

	public void meterOn(int taxiNumber) {
		if(tdp.get(taxiNumber).status==TaxiStruct.OFFLINE ||
				tdp.get(taxiNumber)==null)
			Output.printError("Taxi not logged in. Cannot perform operation.");
		else{
			TaxiStruct ts = tdp.get(taxiNumber);
			if (ts.status == TaxiStruct.WAITING_FOR_CUSTOMER) {
				ts.status = TaxiStruct.DRIVING_A_CUSTOMER;
				ts.startTime = System.currentTimeMillis();
				OrderStructC order = odp.get(ts.orderNumber);
				if(order.orderNumber > 0){
					order.status = 2;
					odp.update(order);
					ts.meterOn = true;
					ts.zone = GPSProxy.getZoneNumber();
					ts.position = GPSProxy.getPosition();
					tdp.update(ts);
				}
			}
		}
	}
	
	public void meterOff(int taxiNumber){
		TaxiStruct ts = tdp.get(taxiNumber);
		if (ts != null){
			ts.meterOn = false;
			ts.status = TaxiStruct.AVAILABLE;
			ts.stopTime = System.currentTimeMillis();
			OrderStructC os = odp.get(ts.orderNumber);
			os.status = 3;
		}
	}

	public void newOrderO(OrderStructC order) {
		if(tdp.get(order.taxiNumber).status==TaxiStruct.OFFLINE ||
				tdp.get(order.taxiNumber)==null)
			Output.printError("Taxi not logged in. Cannot perform operation.");
		else{
			if (operatordp.size() == 0)
				Output.printError("No operator logged in, cannot perform operation.");
			else {
				int operatorNumber = getRandomOperatorNumber();
				TaxiStruct ts = tdp.get(order.taxiNumber);
				if (ts.status = TaxiStruct.AVAILABLE){
					ts.orderNumber = nextOrderNumber;

					order.orderNumber = nextOrderNumber;
					order.status = 1;
					nextOrderNumber++;
					tdp.update(ts);
					odp.update(order);
					operatordp.get(operatorNumber).newOrder(order.taxiNumber);
				}
			}
		}
	}

	public void newOrderTM(OrderStructC order, int operatorNumber) {
		if (operatordp.get(operatorNumber) != null) {
			if (order.orderNumber == -1) {
				order.orderNumber = nextOrderNumber;
				nextOrderNumber++;
			}
			order.status = 1;
			if (order.dispatchTime > 0) {
				odp.update(order);
				// update ist besser damit nicht ein objetkt 2 mal vorkommt
			}
			else if (order.dispatchTime==0) {

				int taxiNumber = order.taxiNumber;
				TaxiStruct ts = tdp.get(taxiNumber);
				if( ts!= null && taxiNumber > 0 && ts.status == TaxiStruct.AVAILABLE){
					
					ts.orderNumber = order.orderNumber+1;
					ts.status = TaxiStruct.WAITING_FOR_CUSTOMER;
					tdp.update(ts);
					odp.add(order);
					comLinkTM.newOrder(order, operatorNumber);
				}
				else{
					TaxiStruct ts = getFirstAvailableTaxi();
					if (ts != null) {
						order.taxiNumber = ts.taxiNumber;
						ts.orderNumber = order.orderNumber;
						ts.status = TaxiStruct.WAITING_FOR_CUSTOMER;
						tdp.update(ts);
						odp.add(order);
						comLinkTM.newOrder(order, operatorNumber);
					}
					else {
						order.dispatchTime = order.dispatchTime + 5;
						odp.add(order);
					}
				}//else
			} //else
		} //if
		else
			Output.printError("Operator not logged in.");
	}

	public void rejectOrder(int taxiNumber, int operatorNumber, int orderNumber) {
		if(tdp.get(taxiNumber).status==TaxiStruct.OFFLINE ||
				tdp.get(taxiNumber)==null)
			Output.printError("Taxi not logged in. Cannot perform operation.");
		else if (operatordp.get(operatorNumber) == null) 
			Output.printError("Operator not logged in.");
		else{
			//TaxiStruct ts = tdp.get(taxiNumber);
			//ts.orderNumber = -1;
			//ts.status = TaxiStruct.AVAILABLE;
			//tdp.update(ts);
			System.out.println("Operator " + operatorNumber + " manual dispatch Order " + orderNumber+".");
			OrderStructC order = (OrderStructC) odp.get(orderNumber);
			operatordp.get(operatorNumber).manualDispatchOrder(taxiNumber,order);
		}
	}

	public void requestPrice(int taxiNumber) {
		TaxiStruct ts = tdp.get(taxiNumber);
		comLinkTM.responsePrice();
	}

	public void resetAlarmTM(int taxiNumber, int operatorNumber) {

	}

	public void sendVoiceO(int taxiNumber, int operatorNumber) {
		
	}

	private void sendVoiceTM(int taxiNumber, int operatorNumber) {
		

	}

	public void startAlarm(int taxiNumber) {
		if (operatordp.size() == 0)
			Output.printError("No operator logged in, cannot perform operation.");
		else if(tdp.get(taxiNumber).status==TaxiStruct.OFFLINE ||
				tdp.get(taxiNumber)==null)
			Output.printError("Taxi not logged in. Cannot perform operation.");
		else {
			int operatorNumber = getRandomOperatorNumber();
			tdp.get(taxiNumber).alarm = true;
			operatordp.get(operatorNumber).stopVoiceC(taxiNumber);
		}
	}

	public void startVoiceO(int taxiNumber, int operatorNumber) {

	}

	public void startVoiceTM(int taxiNumber, int operatorNumber) {
		if (operatordp.get(operatorNumber) == null)
			Output.printError("Operator not logged in, cannot perform operation.");
		else if(tdp.get(taxiNumber)==null)
			Output.printError("Taxi not logged in. Cannot perform operation.");
		else
			comLinkTM.startVoice(taxiNumber, operatorNumber);

	}

	public void stopVoiceO(int taxiNumber, int operatorNumber) {

	}

	public void stopVoiceTM(int taxiNumber, int operatorNumber) {

	}

	public void terminateVoiceO(int taxiNumber, int operatorNumber) {
		if (operatordp.get(operatorNumber) == null)
			Output.printError("Operator not logged in, cannot perform operation.");
		else if(tdp.get(taxiNumber)==null)
			Output.printError("Taxi not logged in. Cannot perform operation.");
		else
			operatordp.get(operatorNumber).terminateVoice(taxiNumber);
	}

	public void terminateVoiceTM(int taxiNumber, int operatorNumber) {
		comLinkTM.terminateVoice(taxiNumber, operatorNumber);			
	}

	public void textMessageTM(int[] taxiNumbers, int messageNumber, String textMessage,
			int operatorNumber) {
		if (operatordp.get(operatorNumber)!= null) {

			if (taxiNumbers.length == 0) {
				Enumeration e = tdp.objects();
					while (e.hasMoreElements()) {
						comLinkTM.textMessage(e.nextElement(),messageNumber,textMessage);
					}
			} else {
				for (int i = 0; i<taxiNumbers.length;i++) {
					comLinkTM.textMessage(taxiNumbers[i],messageNumber,textMessage);
				}	
			}
		} else {
			Output.printError("Operator not found!")
		}

	}

	public void voiceMessageO(int taxiNumber, int operatorNumber, String voicedata) {

	}

	public void voiceMessageTM(int taxiNumber, int operatorNumber, String voicedata) {

	}
}