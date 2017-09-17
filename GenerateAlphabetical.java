import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GenerateAlphabetical implements EventHandler<Event>, Serializable {

	private List<Patient> patientList;
	private String content;
	
	public GenerateAlphabetical(List<Patient> pl) {
		this.patientList = pl;
	}
	public void handle(Event event) {
		displayAlertBox();
		
	}
	
	public void displayAlertBox() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Generate Report");
		window.setMinWidth(300);
		window.setMinHeight(200);
		
	    FileChooser fileChooser = new FileChooser();
	  
	    //Set extension filter
	    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
	    fileChooser.getExtensionFilters().add(extFilter);
	              
	    //Show save file dialog
	    File file = fileChooser.showSaveDialog(window);
	       
	    sort();
	    content = getContent();
	    if(file != null){
	         SaveFile(content, file);
	       }
	    }
	
	private void SaveFile(String content, File file){
		try {
			FileWriter fileWriter = null;
			fileWriter = new FileWriter(file);
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException ex) {
			System.out.println("Error saving file.");
			ex.printStackTrace();
		} 
	}
	
	public void sort() {
		for(int i = 0; i < MainApplication.getSize() - 1; i ++) {
			for(int j = 0; j < ((MainApplication.getSize() -1) - i); j ++) {
				if(MainApplication.smaller(patientList.get(j), patientList.get(j+1)) == false) {
					Patient P2 = patientList.get(j+1);
					patientList.remove(j+1);
					Patient P1 = patientList.get(j);
					patientList.remove(j);
					patientList.add(j, P2);
					patientList.add(j+1, P1);
				}
			}
		}
	}
	
	public String getContent() {
		String list = "";
		for(int i = 0; i < MainApplication.getSize(); i ++) {
			list = list + patientList.get(i).toString();
			for(int j = 0; j < MainApplication.getInvoiceListSize(i); j ++) {
				list = list + patientList.get(i).getInvoice(j).toString();
			}
		}
		return list;
	}
}
