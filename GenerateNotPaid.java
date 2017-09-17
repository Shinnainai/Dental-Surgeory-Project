import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GenerateNotPaid implements EventHandler<Event>{

	
	private String content;
	

	public void handle(Event event) {
		displayAlertBox();
	}
	
	public void displayAlertBox() {
		Stage window = new Stage();
		GridPane grid = new GridPane();
		
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
		       
		    String[] orderedList = sort();
		    content = getContent(orderedList);
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
		
		public String[] sort() {
			ArrayList<String> list = MainApplication.getPatientsMoreThan6Mths();
			String orderedList[] = MainApplication.orderByOwing(list);
			return orderedList;
		}
		
		public String getContent(String[] orderedList) {
			String list = "";
			for(int i = 0; i < orderedList.length; i ++) {
				list = list + orderedList[i] + "\n";
			}
			return list;
		}
}
