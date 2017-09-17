import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patient extends Person implements Comparable<Patient> {

	private int patientNo;
	private List <Invoice> p_invoiceList = new ArrayList<Invoice>();
	private long phoneNo;
	private static int numb = -1;
	 
	public Patient (String name, String address, long pn) {
		super(name, address);		
		phoneNo = pn;
		numb ++;
		setPatientNo(numb);
	}

	public Invoice getInvoice(int i) {
		return p_invoiceList.get(i);
	}
	public List<Invoice> getInvoiceList() {
		return p_invoiceList;
	}
	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}

	public int getPatientNo() {
		return patientNo;
	}

	public void setPatientNo(int patientNo) {
		this.patientNo = patientNo;
	}

	
	@Override
	public String toString() {
		return super.getName() + "\n" + super.getAddress() + "\n" + patientNo + "\n";
	}
	
	public void print() {
		System.out.println(toString());
	}
	
	public void addInvoiceToList(Invoice I1, int pos) {
		p_invoiceList.add(pos, I1);
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof Patient)) 
			return false;
		Patient P = (Patient) o;
		return P.getName().equalsIgnoreCase(getName());
	}

	public int compareTo(Patient P) {
		return this.getName().compareToIgnoreCase(P.getName());
	}
}
