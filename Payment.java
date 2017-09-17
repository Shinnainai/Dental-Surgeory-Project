import java.util.Date;

public class Payment {

	private int paymentNo;
	private double paymentAmt;
	private Date paymentDate;
	private static int numb = 0;
	private static int count = 0;
	
	public Payment(double pa, Date date) {
		paymentAmt = pa;
		paymentDate = date;
		setPaymentNo(numb);
		numb++;
	}
	public int getPaymentNo() {
		return paymentNo;
	}
	public void setPaymentNo(int paymentNo) {
		this.paymentNo = paymentNo;
	}
	public double getPaymentAmt() {
		return paymentAmt;
	}
	public void setPaymentAmt(double paymentAmt) {
		this.paymentAmt = paymentAmt;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	@Override
	public String toString() {
		return paymentNo + "\n" + paymentAmt + "\n" + paymentDate + "]\n";
	}
	
	public void print() {
		System.out.println(toString());
	}
	
	public void addToCount() {
		count++;
	}
}
