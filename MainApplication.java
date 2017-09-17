import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class MainApplication {

	private static List <Patient> patientList = new ArrayList<Patient>();
	public static List <Dentist> dentistList = new ArrayList<Dentist>();
	
	public static void main(String[] args) {
		
	}
	
	
	//----------------------------------------------------------------------------------------------------//
												/*Getters*/
	//----------------------------------------------------------------------------------------------------//
	
	public static int getSize() {
		return patientList.size();
	}
	public static String getDentistName(int i) {
		return dentistList.get(i).getName();
	}
	public static int getDentistSize() {
		return dentistList.size();
	}
	public static String getDentistPassword(int i) {
		return dentistList.get(i).getPassword();
	}
	public static String getName(int i) {
		return patientList.get(i).getName();
	}
	public static int getID(int i) {
		return patientList.get(i).getPatientNo();
	}
	public static Patient getPatient(int i) {
		return patientList.get(i);
	}
	public static int getProcListSize(int i) {
		return getPatient(i).getInvoice(0).getProcListSize();
	}
	public static Procedure getProcedure(int i, int j) {
		return patientList.get(i).getInvoice(0).getProcedure(j);
	}
	public static Payment getPayment(int i, int j) {
		return patientList.get(i).getInvoice(0).getPayment(j);
	}
	public static ArrayList<Patient> getArrayPatients() {
		return (ArrayList<Patient>) patientList;
	}
	public static int getInvoiceListSize(int i) {
		return patientList.get(i).getInvoiceList().size();
	}
	public static int getProcListSize(int i, int j) {
		return patientList.get(i).getInvoice(j).getIn_procList().size();
	}
	public static int getPaymentListSize(int i, int j) {
		return patientList.get(i).getInvoice(j).getIn_paymentList().size();
	}
	public static int getInvoiceListSize(int i, int j) {
		return patientList.get(i).getInvoiceList().size();
	}
	
	
	
	//----------------------------------------------------------------------------------------------------//
									/*Add, Remove, Display Patients*/
	//----------------------------------------------------------------------------------------------------//
	
	public static void addPatient(String name, String address, long phoneNo) {
		Patient P1 = new Patient(name, address, phoneNo);
		patientList.add(P1);
	}
	
	public static void removePatient(String name) {
		int removeID = findPNo(name);
		if (removeID == -1) {
			System.out.println("Patient not found!");
		}
		else
			patientList.remove(removeID);
	}
	
	public static String displayPatients() {
		String S1 = "";
		for (int i = 0; i < patientList.size(); i ++) {
			S1 = S1 + patientList.get(i).toString();
		}
		return S1;
	}
	
	
	//----------------------------------------------------------------------------------------------------//
								/*Add, Remove Procedures*/
	//----------------------------------------------------------------------------------------------------//

	
	public static void addProcedure(int cost, String procName, Date date, int ID) {
		for(int i = 0; i < patientList.size(); i ++) {
			if (ID == patientList.get(i).getPatientNo())
			{
				patientList.get(i).getInvoice(0).addProcedureToList(procName, cost, date);
			}
		}
	}
	
	public static void removeProcedure(String choice, int ID) {
		
		int removePos = -1;
		System.out.println("Choice: " + choice + "ID: " + ID);
		
		for(int i = 0; i < patientList.size(); i ++) {
			if( ID == patientList.get(i).getPatientNo()) {
				for(int j = 0; i < patientList.size(); i ++) {
					if(choice.equalsIgnoreCase(patientList.get(i).getInvoice(0).getProcedureName(j))) {
						removePos = j;
						if (removePos != -1)
							patientList.get(i).getInvoice(0).removeProcedure(removePos);
						else
							System.out.println("Error. Procedure does not exist.");
						}
				}
			}
		}
	}
	
	//----------------------------------------------------------------------------------------------------//
											/*Add Payment*/
	//----------------------------------------------------------------------------------------------------//

	public static void addPayment(int ID, int amount) {
		Date date = new Date();
		for(int i = 0; i < patientList.size(); i ++) {
			if( ID == patientList.get(i).getPatientNo()) {
				patientList.get(i).getInvoice(0).addPaymentToList(amount, date);
			}
		}
	}
	

	//----------------------------------------------------------------------------------------------------//
											/*Invoice*/
	//----------------------------------------------------------------------------------------------------//

	public static void createInvoice(int ID) {
		Date d1 = new Date();
		Invoice I1 = new Invoice(d1); 
		I1.addToCount();
		for(int i = 0; i < patientList.size(); i ++) {
			if (ID == patientList.get(i).getPatientNo())
			{
				//passing 0 into this method ensures that new invoices are always added at position 0
				patientList.get(i).addInvoiceToList(I1, 0);
			}
		}
	}

	public static void getInvoiceAmt() {
		double amt = 0.0;
		for(int i = 0; i < getSize(); i ++) {
			for(int j = 0; j < getProcListSize(i, 0); j ++) {
				amt = amt + patientList.get(i).getInvoice(0).getProcedure(j).getProcCost();
				patientList.get(i).getInvoice(0).setInvoiceAmt(amt);
			}
		}	
	}
	
	public static void addInvoice(int InvNo, double amt, Date date, List< Procedure> procList) {
		Invoice I1 = new Invoice(InvNo, amt, date, procList);
	}
	
	//----------------------------------------------------------------------------------------------------//
										/*Create Test Dentists*/
	//----------------------------------------------------------------------------------------------------//
	
	public static void createDentists() {
		Dentist D1 = new Dentist("John Smith", "22 W. Adams Street", "password");
		Dentist D2 = new Dentist("Emma Jones", "D12, Dublin", "password");
		Dentist D3 = new Dentist("Ryan Renolds", "New York City", "password");
		//Easy dentist for testing purposes
		Dentist D4 = new Dentist("x", "New York City", "x");
		
		dentistList.add(D1);
		dentistList.add(D2);
		dentistList.add(D3);
		dentistList.add(D4);
	}
	
	
	
	//----------------------------------------------------------------------------------------------------//
										/*Readers*/
	//----------------------------------------------------------------------------------------------------//

	public static void readProcedures(List<String> allProceduresList) {
		BufferedReader reader = null;
		try { 
			File file = new File("Procedures.txt");
			reader = new BufferedReader(new FileReader(file));
			
			String line;
			while((line = reader.readLine()) != null) {
				allProceduresList.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//----------------------------------------------------------------------------------------------------//
												/*Misc*/
	//----------------------------------------------------------------------------------------------------//

	
	public static void printDentists() {
		for(int i = 0; i < dentistList.size(); i ++) {
			System.out.println("Name: " + dentistList.get(i).getName() + "Password: " + dentistList.get(i).getPassword() + "\n");
		}
	}
	
	public static int findPNo(String name) {
		int returnNo = -1;
		for (int i = 0; i < patientList.size(); i ++) {
			if(patientList.get(i).getName().equals(name))
			{
				returnNo = i;
			}
		}
		return returnNo;
	}
	
	public static String printAll() {
		String list = "";
		for(int i = 0; i < patientList.size(); i ++) {
			list = list + patientList.get(i).toString();
			list = list + patientList.get(i).getInvoice(i).toString();
		}
		return list;
		
	}
	
	
	//----------------------------------------------------------------------------------------------------//
												/*Ordering*/
	//----------------------------------------------------------------------------------------------------//
			
	public static boolean smaller(Patient P1, Patient P2) {
		boolean small = true;
		if((P1.getName().compareTo(P2.getName())) > 0)
			small = false;
		return small;
	}
	
	public static long getTime(Date date) {
		long time = date.getTime();
		return time;
	}
	
	public static long get6Months() {
		long sixMths = 6*30*24*60*60;
		return sixMths;
	}
	
	public static ArrayList<String> getPatientsMoreThan6Mths() {
		
		ArrayList<String> list = new ArrayList<String>();
		String listStr = "";
		//for each patient 
		for(int i = 0; i < getSize(); i++) {
			//in each invoice
			for(int j = 0; j < getInvoiceListSize(i, 0); j ++) {
				//get each payment
				for(int k = 0; k < getPaymentListSize(i, 0); k ++) {
				//subtract todays milliseconds
				Date date = new Date();
				long todaysTime = getTime(date);
				long paymentTime = getTime(getPayment(j, 0).getPaymentDate());
				//is it > 6mths old
				long diff = todaysTime - paymentTime;
				if (diff > get6Months()) {
					listStr = patientList.get(i).getName() + "\n" + patientList.get(i).getInvoice(j).getPayment(k).getPaymentAmt() + "\n";
					list.add(listStr);
					}
				}
			}
		}
		return list;
	}
	
	public static String[] orderByOwing(ArrayList<String> list) {
		
		String strList2[] = new String[0];
		
		for(int i = 0; i < getSize(); i ++) {
			String strList[] = list.get(i).split("\n");
			int size1 = strList.length;
			strList2  = new String[size1];
			
			for(int k = 1; k < size1; k=k+2) {
				for(int j = 1; j < ((size1 - 1) - k); j = j+2) {
					if(owesLess(strList[j], strList[j + 2]) == false) {
						String str3 = strList[j+1];	//2
						String str4 = strList[j+2];	//3
						
						String str1 = strList[j-1];	//0
						String str2 = strList[j];	//1
						
						strList2[j-1] = str3;
						strList2[j] = str4;
						strList2[j+1] = str1;
						strList2[j+2] = str2;
					}
				}
			}
		}	
		return strList2;
	}
	
	public static boolean owesLess(String A1, String A2) {
		boolean small = true;
		Double obj1 = new Double(A1);
		Double obj2 = new Double(A2);
		int returnValue = obj1.compareTo(obj2);
		
		if (returnValue > 0)
			//obj1 is greater than obj2
			small = false;
		return small;
	}
	
		
}
