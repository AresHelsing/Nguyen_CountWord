/*Author Name: Tran Nguyen
 * Date: 03/06/2022
 * Program Name: Nguyen_CountWord
 * Purpose: Count word from file
 */
package application;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * The class Item represents a generic word.
 * 
 * @author buham
 *
 */
class Item {
	int count;
	String word;
	Item(int count, String word){
		this.count =count;
		this.word= word;
	}
	
}
/**
 * The class ItemComparater represent the process compare the word count.
 * @author buham
 *
 */
class ItemComparator implements Comparator<Item>{
	
	public int compare(Item item1, Item item2){
	    
	    if( item1.count > item2.count ){
	        return -1;
	    }
	    if( item1.count < item2.count ){
	        return 1;
	    }
	    else{
	        return 0;
	    }
	}
}
/**
 * The class testClass represents the p
 * @author buham
 *
 */
class testClass{
	/**
	 * 
	 * @param words - the words count in the file
	 * @return - the words and the number count in the file
	 * @throws FileNotFoundException
	 */
	public static String countWord(File words) throws FileNotFoundException {
		String message = "The word count:\n";
		Scanner wordread = new Scanner(words);
		ArrayList<String> wordcount = new ArrayList<>();
		ArrayList<Integer> numbercount = new ArrayList<>();
		while(wordread.hasNext()) {
			String temp = wordread.next();
			if (wordcount.contains(temp)) {
				int index = wordcount.indexOf(temp);
				numbercount.set(index, numbercount.get(index)+1);
			}else if(!wordcount.contains(temp)) {
				wordcount.add(temp);
				numbercount.add(1);
			}
			
		}
		
		
		ArrayList<Item> itemList = new ArrayList<Item>();
		for(int i = 0; i < wordcount.size();i++) {
			itemList.add(new Item(numbercount.get(i),wordcount.get(i)));
		}
		
		// Sort
        Collections.sort(itemList, new ItemComparator());
        for(int i = 0; i<20; i++) {
    		message += i+1 + ". "+itemList.get(i).word + " - "+itemList.get(i).count + "\n";
   		}

		return message;
	}
}





/**
 * The class Nguyen_CountWord represents the GUI of count word function
 * @author buham
 *
 */
public class Nguyen_CountWord extends Application {
	Button button;
	Scene scene;
	Stage primeStage;
	TextField text;
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	@Override
	/**
	 * Create GUI
	 */
	public void start(Stage primeStage) throws Exception{
		primeStage.setTitle("Count word in File");
		button = new Button("Start to count word");
		Text enter = new Text("Enter the file:");
		text = new TextField();
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					showList();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		GridPane layout = new GridPane();
		layout.setPadding(new Insets(5,5,5,5));
		layout.setVgap(5);
		layout.setHgap(5);
		layout.addRow(0, enter, text, button);
	
		layout.setAlignment(Pos.CENTER);
		
		scene = new Scene(layout,500,200);
		primeStage.setScene(scene);
		primeStage.show();
		
		
		
		
		
		
	}
	
	
	/**
	 * 
	 * @throws FileNotFoundException
	 */
	public void showList() throws FileNotFoundException{		
		Scanner scanner = new Scanner(text.getText());
		//Scan the input
		String file = scanner.next();
		File words = new File(file);
		String message = testClass.countWord(words);
		System.out.println(message);
		scanner.close();

        
       
		Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle("List of words");
		a.setHeaderText(null);
		a.setContentText(message);
		a.show();
	}
	}


