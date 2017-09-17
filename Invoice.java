import java.util.*;

public class Invoice {

	private int invoiceNo;
	private double invoiceAmt;
	private Date invoiceDate;
	private List <Procedure> in_procList = new ArrayList<Procedure>();
	private List <Payment> in_paymentList = new ArrayList<Payment>();
	private static int numb = 0;
	private static int count = 0;
	
	public Invoice(Date d) {
		invoiceDate = d;
		setInvoiceNo(numb);
		numb ++;
	}
	public Invoice(int in, double amt, Date d, List< Procedure> proList) {
		
	}

	public int getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(int invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public double getInvoiceAmt() {
		return invoiceAmt;
	}

	public void setInvoiceAmt(double invoiceAmt) {
		this.invoiceAmt = invoiceAmt;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	
	public List<Procedure> getIn_procList() {
		return in_procList;
	}

	public void setIn_procList(List<Procedure> in_procList) {
		this.in_procList = in_procList;
	}

	public List<Payment> getIn_paymentList() {
		return in_paymentList;
	}

	public void setIn_paymentList(List<Payment> in_paymentList) {
		this.in_paymentList = in_paymentList;
	}
	
	public String getProcedureName(int i) {
		return in_procList.get(i).getProcName();
	}
	public Date getProcedureDate(int i) {
		return in_procList.get(i).getProcDate();
	}
	
	public int getProcListSize() {
		return in_procList.size();
	}
	
	public Procedure getProcedure(int i) {
		return in_procList.get(i);
	}
	public Payment getPayment(int i) {
		return in_paymentList.get(i);
	}

	@Override
	public String toString() {
		return invoiceNo + "\n" + invoiceAmt + "\n" + invoiceDate
				 + "\n" + in_procList.size() + "\n" + in_procList  
				 + "\n" + in_paymentList.size() + "\n" + in_paymentList 
				 + "\n";
	}
	 public void print() {
		 System.out.println(toString());
	 }
	 
	 
	 public void addProcedureToList(String procName, int procCost, Date date) {
		Procedure P1 = new Procedure(procName, procCost, date);
		P1.addToCount();
		in_procList.add(P1);
	 }
	 
	 public void removeProcedure(int pos) {
		 in_procList.remove(pos);
	 }
	 
	 public void setProcNo(int i, int j) {
		 in_procList.get(i).setProcNo(j);
	 }
	 public void addPaymentToList(double amount, Date date) {
		 Payment P1 = new Payment(amount, date);
		 P1.addToCount();
		 in_paymentList.add(P1);
	 }
	public void setPaymentNo(int i, int j) {
		in_paymentList.get(i).setPaymentNo(j);
	}
	 public void addToCount() {
			count++;
		} 
	
}
