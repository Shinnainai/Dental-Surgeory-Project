import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class loadFile implements EventHandler<Event> {

	private List<Patient> patientList;
	private Scanner sc;
	
	public void handle(Event event) {
		displayAlertBox();	
	}
	
	
	public void displayAlertBox() {
		Stage window = new Stage();
		GridPane grid = new GridPane();
		
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Load File");
		window.setMinWidth(300);
		window.setMinHeight(200);
		
		
		try {
			FileChooser fileChooser = new FileChooser();
			File selectedFile = fileChooser.showOpenDialog(window);
			System.out.println("File selected: " + selectedFile.getName());
			sc = new Scanner(selectedFile);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int size = MainApplication.getSize();
		for (int i = 0; i < size; i++) 
			patientList.remove(0);
		
		int count = 0;
		while(sc.hasNext()) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyy");
			
			//Patient: Name, Address, Patient No
			Patient P1 = new Patient(sc.next(), sc.next(), sc.nextInt());
			int noInvoices = sc.nextInt();
			for(int i = 0; i < noInvoices; i ++) {
				//Invoice No, Amt, Date
				Invoice I1 = null;
				I1.setInvoiceNo(sc.nextInt());
				I1.setInvoiceAmt(sc.nextDouble());
				Date d1 = null;
				try {
				d1 = formatter.parse(sc.next());
				} catch (ParseException ex) {
					ex.printStackTrace(); }
				I1 = new Invoice(d1);
				P1.addInvoiceToList(I1, count);
				int noProcedures = sc.nextInt();
				for (int j = 0; j < noProcedures; j ++) {
					//Proc List: No, Name, Cost
					Date d2 = null;
					try {
						d2 = formatter.parse(sc.next());
					} catch (ParseException ex2) {
						ex2.printStackTrace();
					}
					P1.getInvoice(0).setProcNo(0, sc.nextInt());
					P1.getInvoice(0).addProcedureToList(sc.next(), sc.nextInt(), d2);
					
				}
				
				int noPayments = sc.nextInt();
				for(int k = 0; k < noPayments; k ++) {
					//Payment List: No, Amt, Date
					Date d3 = null;
					try {
						d3 = formatter.parse(sc.next());
					} catch (ParseException ex3) {
						ex3.printStackTrace();
					}
					P1.getInvoice(0).setPaymentNo(0, sc.nextInt());
					P1.getInvoice(0).addPaymentToList(sc.nextDouble(), d3);
					
				}
			}
			
			patientList.add(P1);
			count = count + 1;
		}
		
	}
}
