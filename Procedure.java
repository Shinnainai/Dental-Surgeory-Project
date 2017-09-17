import java.util.Date;

public class Procedure {

	private int procNo;
	private String procName;
	private double procCost;
	private Date procDate;
	private static int numb = 0;
	private static int count;
	
	public Procedure(String pn, double pc, Date d) {
		procName = pn;
		procCost = pc;
		procDate = d;
		setProcNo(numb);
		numb++;
	}

	public int getProcNo() {
		return procNo;
	}

	public void setProcNo(int procNo) {
		this.procNo = procNo;
	}

	public String getProcName() {
		return procName;
	}

	public void setProcName(String procName) {
		this.procName = procName;
	}

	public double getProcCost() {
		return procCost;
	}

	public void setProcCost(double procCost) {
		this.procCost = procCost;
	}
	
	public Date getProcDate() {
		return procDate;
	}

	public void setProcDate(Date procDate) {
		this.procDate = procDate;
	}


	@Override
	public String toString() {
		return procNo + "\n" + procName + "\n" + procCost + "\n" + procDate + "\n";
	}
		
	public void print() {
		System.out.println(toString());
	}
	
	public void addToCount() {
		count++;
	}
}


