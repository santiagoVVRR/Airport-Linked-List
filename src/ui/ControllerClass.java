package ui;

import java.util.ArrayList;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import model.Flight;
import model.FlightScreen;;

public class ControllerClass {

	private FlightScreen fligths;
    private Flight firstFlight;
    
    @FXML
    private Button lastPage;

    @FXML
    private Label page;

    @FXML
    private Label time;
    
    @FXML
    private Button nextPage;
	
	@FXML
    private TextField numberOfFlights;

    @FXML
    private Button search;

    @FXML
    private ComboBox<String> searchBy;

    @FXML
    private TextField data;

    @FXML
    private ComboBox<String> searchingKind;

    @FXML
    private Button date;

    @FXML
    private Button airline;

    @FXML
    private Button flight;

    @FXML
    private Button destiny;

    @FXML
    private Button gate;

    @FXML
    private VBox dates;

    @FXML
    private VBox airlines;

    @FXML
    private VBox flies;

    @FXML
    private VBox destinies;

    @FXML
    private VBox gates;

    @FXML
    void choiceSearch(ActionEvent event) {
    	search.setVisible(true);
    	searchBy.setVisible(true);
    	data.setVisible(true);
 
    }

    @FXML
    void generate(ActionEvent event) {
        clearData();
    	int num = 0;
    	long timeInit=System.currentTimeMillis();
    	try {
    		num = Integer.parseInt(numberOfFlights.getText());
    	}catch(NumberFormatException e) {
    		Alert info = new Alert(AlertType.ERROR);
        	info.setTitle("Flights");
        	info.setHeaderText(null);
        	info.initStyle(StageStyle.UTILITY);
        	info.setContentText("Thats not a number, please introude a number");
        	info.show();
        	
    	} 
    	
    	fligths = new FlightScreen();
    	
    	for (int i = 0; i < num ; i++) {
    		Flight fl = new Flight(fligths.getRandomDate(), fligths.getRandomAirline(), fligths.getRandomCode(), fligths.getRandomDestiny(), fligths.randomChar());
    		if(firstFlight == null) {
    			firstFlight = fl;
    		}else {
    			Flight current = firstFlight;
    			while(current.getNextFlight() != null) {
    				current = current.getNextFlight();
    			}
    			current.setNextFlight(fl);
    			fl.setPreviousFlight(current);
    		}

		}   	
    	long timeFinal=System.currentTimeMillis()-timeInit;
    	time.setText("Time: "+timeFinal);
    	showTable();
    }

		

		
		
	
    @FXML
    void orderByAirline(ActionEvent event) {
    	long timeInit=System.currentTimeMillis();
    	fligths.sortByAirline();
    	clearData();
    	showTable();
    	long timeFinal=System.currentTimeMillis()-timeInit;
    	time.setText("Time: "+timeFinal);
    }

    @FXML
    void orderByDate(ActionEvent event) {

    }

    @FXML
    void orderByDestiny(ActionEvent event) {
    	long timeInit=System.currentTimeMillis();
    	//fligths.sortByDestiny();
    	clearData();
    	showTable();
    	long timeFinal=System.currentTimeMillis()-timeInit;
    	time.setText("Time: "+timeFinal);
    }

    @FXML
    void orderByFlight(ActionEvent event) {
    	long timeInit=System.currentTimeMillis();
    	//fligths.sortByFlight();
    	clearData();
    	showTable(); 
    	long timeFinal=System.currentTimeMillis()-timeInit;
    	time.setText("Time: "+timeFinal);
    }

    @FXML
    void orderByGate(ActionEvent event) {
    	long timeInit=System.currentTimeMillis();
    	//fligths.sortByGate();
    	clearData();
    	showTable();
    	long timeFinal=System.currentTimeMillis()-timeInit;
    	time.setText("Time: "+timeFinal);
    }

    @FXML
    void searchAction(ActionEvent event) {
    	long timeInit=System.currentTimeMillis();
    	try {
        	String kindSearch=searchingKind.getValue();
            String kindOfData=searchBy.getValue();
            String datas=data.getText();
            Flight information=null;
            if(kindSearch.equals("Binaria")){
                information=fligths.binarySort(kindOfData,datas);
            }else {
            	 information=fligths.linealSort(kindOfData,datas);
            }
            String showData;
            if(information!=null) {
            	showData=information.toString();
            }else {
            	 showData="The value do not exisct";
            }
       		Alert info = new Alert(AlertType.INFORMATION);
        	info.setTitle("Information");
        	info.setHeaderText(null);
        	info.initStyle(StageStyle.UTILITY);
        	info.setContentText(showData);
        	info.show();
        	data.setText("");
        	long timeFinal=System.currentTimeMillis()-timeInit;
        	time.setText("Time: "+timeFinal);
        }catch(NullPointerException npe) {
       		Alert info = new Alert(AlertType.ERROR);
        	info.setTitle("Warning");
        	info.setHeaderText(null);
        	info.initStyle(StageStyle.UTILITY);
        	info.setContentText("Please first choice all the data \n"+"1. Kind of data to search \n"+"2. Put the data \n");
        	info.show();
        }

    }
    
    @FXML
    void initialize() {
       setNodes();
       configureComboBox();
    }
    public void setNodes() {
    	date.setDisable(false);
    	airline.setDisable(false);
    	flight.setDisable(false);
    	destiny.setDisable(false);
    	gate.setDisable(false);
    	search.setVisible(false);
    	searchBy.setVisible(false);
    	data.setVisible(false);
 
    }
    public void configureComboBox() {
    	page.setText("1");
    	searchBy.getItems().add("Date");
    	searchBy.getItems().add("Airline");
    	searchBy.getItems().add("Flight");
    	searchBy.getItems().add("Destiny");
    	searchBy.getItems().add("Gate");
    	searchingKind.getItems().add("Secuencial");
    	searchingKind.getItems().add("Binaria");
    	
    }
    
    public void showTable() {
    	int pages= amountPages();
    	Flight fls= firstFlight;
    	int total=1;
    	for(int j=0;j<pages;j++){  		
    		if(total%14 ==0) {
    	        int avance=0;
    			while( avance<14 && fls != null) {
    	        	fls=fls.getNextFlight();
    	        	avance++;
    	        }
    		}
    		total=0;
    		if(j+1==Integer.parseInt(page.getText())){  	
    	    	for (int i = 0; i <14 && fls!=null; i++) {
    	    		String date =fls.getDate().toString();
    	    		Label d = new Label("\t"+date.substring(0, 20));
    				Label a = new Label("\t"+fls.getAirline());
    				Label f = new Label("\t"+fls.getNumFlight());
    				Label de = new Label("\t"+fls.getDestiny());
    				Label g = new Label("\t"+fls.getGate());							
    				dates.getChildren().add(d);
    				airlines.getChildren().add(a);
    				flies.getChildren().add(f);
    				destinies.getChildren().add(de);
    				gates.getChildren().add(g);
    				fls=fls.getNextFlight();
    				total++;
    		    }
    	    }
		}
    }
    
    public void clearData() {
    	dates.getChildren().clear();
		airlines.getChildren().clear();
		flies.getChildren().clear();
		destinies.getChildren().clear();
		gates.getChildren().clear();
    }
    
    @FXML
    void lastPageGo(ActionEvent event) {
        int newPage= Integer.parseInt(page.getText())-1;
        if(newPage>0) {
        	page.setText(newPage+"");
        	clearData();
        	showTable();
        }
    }

    @FXML
    void nextPageGo(ActionEvent event) {
    	int newPage= Integer.parseInt(page.getText())+1; 
    	if(newPage<(amountPages()+2)) {
        	page.setText(newPage+"");
        	clearData();
        	showTable();
        }
    }
    
    public int amountPages() {
    	Flight fls= firstFlight;   	 
        int amount=0;
        while(fls != null) {
        	fls=fls.getNextFlight();
        	amount++;
        }
        int pages=amount/14;
        if(amount%14!=0) {
        	pages++;
        }
        return pages;
    }
    
}
