import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import javafx.stage.FileChooser;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Assignment2 extends Application{//inherets from the parent class Application, gains methods from the parent class.
	TabPane root; //declaring an instance of the class TabPane.
	Tab tab1;
	Pane p1 = new Pane();//Declaring and initialising a
	Pane p2 = new Pane();
	Pane p3 = new Pane();
	Pane p4 = new Pane();
	Scene scene = new Scene(p1, 800, 600);
	Stage statStage = new Stage();
	Stage end = new Stage();
	Canvas canvas = new Canvas(800, 600);
	Timer t = new Timer();
	Timer t2 = new Timer();
	GraphicsContext gc;
	double bangX, bangY;//declaring a variable of type double.
	Image background = new Image(Assignment2.class.getResource("Resources/Space.jpg").toExternalForm());//Declaring a an instance of class Image and setting the source media from the resources class.
	Image bang = new Image(Assignment2.class.getResource("Resources/Explosion.gif").toExternalForm());
	Image heart = new Image(Assignment2.class.getResource("Resources/Heart.png").toExternalForm());
	Image keys = new Image(Assignment2.class.getResource("Resources/arrowkeys.png").toExternalForm());
	Image spacebar = new Image(Assignment2.class.getResource("Resources/spacebar.png").toExternalForm());
	Image ship = new Image(Assignment2.class.getResource("Resources/Spaceship.png").toExternalForm());
	TextField t1 = new TextField();
	Boolean boolL = true, boolR =true, boolB=false, boolT=false, boolM=false, boolD=false;//Declaring the multiple variables of type boolean.
	ArrayList<Ship> players = new ArrayList<Ship>();
	FileChooser fileChooser = new FileChooser();
	ExtensionFilter ef = new ExtensionFilter("CSV", "*.csv");//Declaring the extension filter so automatically save files as a CSV file.
	Random r1 = new Random();
	Random r2 = new Random();
	Random r3 = new Random();
	Label title = new Label();
	Label l1 = new Label();
	Label l2 = new Label();
	Label l3 = new Label();
	Label l4 = new Label();
	Label l5 = new Label();
	Label l6 = new Label();
	Label lA = new Label();
	Label lS = new Label();
	Label lD = new Label();
	Label lM = new Label();
	ListView lv1 = new ListView();
	MenuBar menuB = new MenuBar();
	Menu menu = new Menu("File");//Declaring and instantiating an instance of the moment of class Menu with the string values initialised as "File".
	MenuItem saveM = new MenuItem("Save");
	MenuItem openM = new MenuItem("Open");
	Button b1 = new Button("PLAY");
	Button b2 = new Button("Restart");
	Button b3 = new Button("Continue");
	Button b4 = new Button("Settings");
	Button bA = new Button("ADDITION");
	Button bS = new Button("SUBTRACTION");
	Button bM = new Button("MULTIPLICATION");
	Button bD = new Button("DIVISION");
	Button yes = new Button("Yes");
	Button no = new Button("No");
	int op, question=1, check;
	int rAns, score, life =0, mAns, dAns;
	int number=0, first=0, second=0, ans;
	int randoms, arraySize;
	int multy=0, divy=0, addy=0, suby=0;
	String name;
	URL resource = getClass().getResource("/Resources/Shake-It-Off.mp3");
	Media media = new Media(resource.toString());
	MediaPlayer mediaPlayer = new MediaPlayer(media);
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();//Creating an Arraylist to contain objects of class Bullet.
	ArrayList<Station> stations = new ArrayList<Station>();
	AnimationTimer at = new AnimationTimer(){//creating a thread of type animation timer.

		@Override
		public void handle(long now) {
			// TODO Auto-generated method stub
			if(life==3){//if statement to check whether the integer variable life is equal to 3;
				stations.clear();//when the if statement is true this will clear the array list 'stations'
				b2.setVisible(true);//sets the button b2 as invisible.
				bt.stop();//this will stop the animation timer bt.
				at.stop();
				l3.setText(" ");//This will set the text value of l3 to empty.
				gc.drawImage(background, 0, 0,800,600);//This tells the graphics context gc to draw the background image to the size specified.
				l1.setText("GAME OVER");
				l1.setFont(Font.font("ROCKWELL", 100));//This will change the font of label l1 to font ROCKWELL and size 50.
				l1.setLayoutX(100);//Changing the x coordinate of the label l1.
				l1.setLayoutY(210);
				if(op==1){//checking to see if integer variable op is equal to 1.
					addy=score;//sets the value of varible addy to the value of variable score.
					players.get(players.size()-1).add= addy;//changes the value of the players add variable to the value of addy.
				}
				if(op==2){
					suby=score;
					players.get(players.size()-1).sub=suby;
				}
				if(op==3){
					multy=score;
					players.get(players.size()-1).mult=multy;
				}
				if(op==4){
					divy=score;
					players.get(players.size()-1).div=divy;
				}
			}
			if(mediaPlayer.getCurrentTime()==mediaPlayer.getStopTime()){//this will check to see if the media player has stopped playing.
				mediaPlayer.play();//restarts the media player.
			}
			if(stations.isEmpty()){//checks if the arraylist stations is empty.
				for(int i=0; i<7; i++){//runs a for loop through 7 times.
					generate();//runs the generate method.
				}
			}
			gc.drawImage(background, 0, 0,800,600);
			if(rAns==0 && op==1){//checks if rAns is equal to 0 and op is equal to 1.
				rAns=r1.nextInt(18);//sets the value of rAns to a random integer.
				l1.setText("Make: "+rAns);
			}
			if((boolM==false || check==rAns) && op==3){
				multiply();//runs the multiply method.
			}
			if((boolD==false || rAns==0)&& op==4){
				divide();//runs the divide method
				l1.setText("Make: " +rAns);
			}
			if(stations.size()!=0){//checks if the station size is not equal to 0.
				for(int i=0; i<stations.size(); i++){//runs through a number equal to the size of the array list.
					stations.get(i).move();//runs the move method for all objects in the stations array list.
					gc.drawImage(stations.get(i).img, stations.get(i).r.getX(), stations.get(i).r.getY(), 100, 100);//draws the stations in their new positions.	
					gc.drawImage(players.get(players.size()-1).img, players.get(players.size()-1).r.getX(), 500, 100,100);//redraws the player.
					if(players.get(players.size()-1).r.intersects(stations.get(i).b)){//checks if the player is intersecting with any station.
						bangX=(players.get(players.size()-1).r.getX());//gets the x position of the player.
						bangY=players.get(players.size()-1).r.getY();//gets the y position of the player.
						stations.remove(i);//removes the stations.
						generate();//generates a new station.
						players.get(players.size()-1).r.setX(1000);//move the player to an off screen position.
						life=life+1;//inreases the variable life by 1.
						boolB=true;//sets the value of boolB to true.
					}
					if(stations.get(i).r.getY()>700){//checks if the stations have a greater y position than 700.
						stations.remove(stations.get(i));//removes the station from the arraylist.
						generate();//generates a new station.
					}
				}

				for(int i=0; i<stations.size(); i++){
					gc.drawImage(stations.get(i).img, stations.get(i).r.getX(), stations.get(i).r.getY(), 100 ,100);//redraws the stations.
				}
				for(int i=0; i<stations.size(); i++){
					for(int j=0; j<stations.size(); j++){
						if(i==j){
							//skips if the stations are the same.
						}
						else if(stations.get(i).r.intersects(stations.get(j).b)){//checks if any stations are in the same space.
							stations.remove(stations.get(j));//removes intersecting stations.
							generate();//generates a new stations.
						}
					}
				}
			}
			if(life==0){//if life equals 0 then it will draw 3 hearts.
				gc.drawImage(heart, 0, 75, 50, 50);
				gc.drawImage(heart, 75, 75, 50, 50);
				gc.drawImage(heart, 150, 75, 50, 50);
			}
			if(life==1){//if life equals 1 then it will draw 2 hearts.
				gc.drawImage(heart, 0, 75, 50, 50);
				gc.drawImage(heart, 75, 75, 50, 50);
			}
			if(life==2){
				gc.drawImage(heart, 0, 75, 50, 50);
			}
			if(boolB==true){//if boolB is equal to true.
				gc.drawImage(bang, bangX, bangY, 100, 100);//draws the gif in the bang positions.
				t2.schedule(new TimerTask(){//uses the t2 timer to schedule a new timer task in 760L milliseconds.
					@Override
					public void run() {
						// TODO Auto-generated method stub
						players.get(players.size()-1).r.setX(350);//puts the player back into position.
						boolB=false;//sets boolB to false;
					}}, 760L);
			}
		}};
		AnimationTimer bt = new AnimationTimer(){

			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub
				if(life==3){
					stations.clear();
					b2.setVisible(true);
					bt.stop();
					at.stop();
					l3.setText(" ");
					gc.drawImage(background, 0, 0,800,600);
					l1.setText("GAME OVER");
					l1.setFont(Font.font("ROCKWELL", 100));
					l1.setLayoutX(100);
					l1.setLayoutY(210);
					if(op==1){
						addy=score;
						players.get(players.size()-1).add= addy;
					}
					if(op==2){
						suby=score;
						players.get(players.size()-1).sub=suby;
					}
					if(op==3){
						multy=score;
						players.get(players.size()-1).mult=multy;
					}
					if(op==4){
						divy=score;
						players.get(players.size()-1).div=divy;
					}
				}
				gc.drawImage(background, 0, 0, 800, 600);//redraws the background.
				for(int i=0; i<bullets.size();i++){//runs through the bullets arraylist.
					bullets.get(i).move();//moves the bullets.
					gc.drawImage(bullets.get(i).img, bullets.get(i).r.getX(), bullets.get(i).r.getY(), 20, 60);//draws the new bullet position.
					for(int j=0; j<stations.size();j++){
						if(bullets.get(i).r.intersects(stations.get(j).b)){//checks if the bullet is intersecting a station.
							if(number==0){
								first=stations.get(j).id;//if number equals 0 it will set the value of first to the station hit.
								number=number+1;//increases the number variable
								l2.setText(""+first);//sets the text value of l2 to first.
								l4.setText("?");
							}
							else if(number==1){
								second=stations.get(j).id;
								if(op==1){//if op equals 0.
								ans=first+second;//adds first and second.
								}
								if(op==2){
								ans=first-second;//subtracts second from first.
								}
								if(op==3){
								ans=first*second;//multiplies first and second.
								}
								if(op==4){
								ans=first/second;//divides first by second.
								}
								if(ans==rAns){//checks if the answer is correct.
									boolT=true;
									score=score+50;//increases the score by 50.
									l5.setText("Score: " + score);
									if(op==1){//the following if statements will create an appropriate random value depending on the mathmatical operator.
									rAns=r1.nextInt(18);
									}
									if(op==2){
									rAns=r1.nextInt(9);
									}
									if(op==3){
									check=rAns;
									multiply();
									l1.setText("Make: "+rAns);
									}
									if(op==4){
									divide();
									l1.setText("Make: "+rAns);
									}
									if(rAns==1){
										rAns=r1.nextInt(18);
									}
									l1.setText("Make: "+rAns);
									question++;//increases the question value.
								}
								else{
									life=life+1;//is the get the question wrong they will lose a life.
									number=0;//sets value of number back to zero.
								}
								l4.setText(""+second);
								l2.setText(" ");
								l4.setText(" ");
								number=0;
							}
							bangX=(stations.get(j).r.getX());
							bangY=stations.get(j).r.getY();
							stations.remove(j);
							generate();//will generate a new station.
							bullets.remove(i);//removes the bullet.
							break;
						}
					}
					if(bullets.isEmpty()==false){//check to see if the bullets arraylist has values.
						if(bullets.get(i).r.getY()==(-100)){
							bullets.remove(bullets.get(i));
						}
					}
				}
				for(int i=0; i<stations.size();i++){
					gc.drawImage(stations.get(i).img, stations.get(i).r.getX(), stations.get(i).r.getY(), 100, 100);
				}
				if(boolT==true){
					gc.drawImage(bang, bangX, bangY, 100, 100);
					t.schedule(new TimerTask(){

						@Override
						public void run() {
							// TODO Auto-generated method stub
							boolT=false;
						}}, 760L);
				}
				if(life==0){
					gc.drawImage(heart, 0, 75, 50, 50);
					gc.drawImage(heart, 75, 75, 50, 50);
					gc.drawImage(heart, 150, 75, 50, 50);
				}
				if(life==1){
					gc.drawImage(heart, 0, 75, 50, 50);
					gc.drawImage(heart, 75, 75, 50, 50);
				}
				if(life==2){
					gc.drawImage(heart, 0, 75, 50, 50);
				}	
				if(life==3){
					stations.clear();
					at.stop();
					bt.stop();
				}
				gc.drawImage(players.get(players.size()-1).img, players.get(players.size()-1).r.getX(), 500, 100,100);
			}};
			EventHandler<KeyEvent> key = new EventHandler<KeyEvent>(){//creates an event handler of type key event.

				@Override
				public void handle(KeyEvent keyEvent) {
					// TODO Auto-generated method stub
					if(players.get(players.size()-1).r.getX()==0){//check if the player has hit the left side of the screen.
						boolL = false;//sets boolL to false.
					}
					else {
						boolL=true;

					}
					if (players.get(players.size()-1).r.getX()==700){
						boolR =false;
					}
					else{
						boolR=true;
					}
					if(keyEvent.getCode()==KeyCode.RIGHT && boolR ==true){//checks if the pressed the right arrow button and if they aren't touching the right hand side.
						players.get(players.size()-1).moveRight();//runs the players ove right method.
					}
					if(keyEvent.getCode()==KeyCode.LEFT && boolL == true){
						players.get(players.size()-1).moveLeft();//runs the players move left method.
					}
					if(keyEvent.getCode()==KeyCode.SPACE){
						bullets.add(new Bullet(players.get(players.size()-1).r.getX()+41, players.get(players.size()-1).r.getY()+20));//creates a new bullet and adds it to the arrray list.
						bt.start();	//starts the bt animation timer.			
					}
				}};
				public static void main(String[] args) {
					// TODO Auto-generated method stub
					launch(args);
				}

				@Override
				public void start(Stage stage) throws Exception {
					// TODO Auto-generated method stub

					stage.setTitle("Objects App");//sets the title of the stage to "Objects App"
					stage.setScene(scene);//set the Scene scene to the stage.

					root = new TabPane();//instantiating the TabPane root.
					scene.setOnKeyPressed(key);//setting the keyevent key to start when a key is pressed.
					tab1 = new Tab();
					tab1.setText("First Tab");//seting the title of the tab1.
					tab1.setClosable(false);//stops the user from closing the tab.
					root.getTabs().add(tab1);//sets tab1 to the root tabpane.
					
					
					for(int i=0; i<7; i++){
						generate();//generates 7 stations.
					}

					tab1.setContent(p1);//sets pane p1 as the content for tab1.
					p1.getChildren().addAll(canvas, title, b1, b2, t1, b4);//adds nodes to the pan p1.
					gc = canvas.getGraphicsContext2D();//sets the graphics context as the 2d graphics context from the canvas.
					gc.fillRect(0, 0, 800, 600);//sets the size for the graphics context.
					gc.drawImage(background, 0, 0, 800, 600);
					gc.drawImage(ship, 335, 230, 120, 120);		

					mediaPlayer.play();//starts the mediaPlayer.
					
					statStage.setScene(new Scene(p3, 400, 300));//set a new scene to a different stage for a separate window.
					statStage.initStyle(StageStyle.UTILITY);//
					statStage.initModality(Modality.WINDOW_MODAL);//sets it to a window mode.
					p3.getChildren().addAll(lv1, menuB, lA, lS, lM, lD);//adds nodes to the pane.
					
					lA.setLayoutX(200);//sets the x value of the label lA
					lA.setLayoutY(60);//sets the y value of lA.
					lA.setText("Addition Score: ");//sets the text of lA
					
					lS.setLayoutX(200);
					lS.setLayoutY(110);
					lS.setText("Subtraction Score: ");
					
					lM.setLayoutX(200);
					lM.setLayoutY(160);
					lM.setText("Multiplication Score: ");
					
					lD.setLayoutX(200);
					lD.setLayoutY(210);
					lD.setText("Division Score: ");
					
					menuB.getMenus().add(menu);//adds the menu menu to the menu bar.
					menu.getItems().addAll(openM, saveM);//adds menu items to the drop down menu.
					
					lv1.setPrefSize(150,220);//sets the size of the list view.
					lv1.setTranslateX(10);
					lv1.setTranslateY(50);

					end.setScene(new Scene(p4, 400, 300));//sets the new scene to a stage called end.
					end.initStyle(StageStyle.UTILITY);
					end.initModality(Modality.WINDOW_MODAL);
					
					p4.getChildren().addAll(l6, yes, no);//adds nodes to p4.
					l6.setText("Do you wish to save" + "\n" + "you Score?");//
					l6.setLayoutX(10);
					l6.setLayoutY(10);
					l6.setFont(Font.font("ROCKWELL", 30));
					
					yes.setLayoutX(150);
					yes.setLayoutY(100);
					yes.setMinHeight(50);
					yes.setMinWidth(75);
					
					no.setLayoutX(150);
					no.setLayoutY(200);
					no.setMinHeight(50);
					no.setMinWidth(75);
					
					title.setLayoutX(100);
					title.setLayoutY(100);
					title.setText("NUMBER ATTACK");
					title.setFont(Font.font("ROCKWELL", 70));
					title.setStyle("-fx-base: #000000");

					b1.setLayoutX(374);
					b1.setLayoutY(500);
					b1.setScaleX(1.5);
					b1.setScaleY(1.5);
										
					b2.setLayoutX(320);
					b2.setLayoutY(400);
					b2.setMinHeight(50);
					b2.setMinWidth(150);
					b2.setVisible(false);
					
					b4.setLayoutX(600);
					b4.setLayoutY(500);
					b2.setMinHeight(75);
					b4.setMinWidth(100);
					
					t1.setLayoutX(320);
					t1.setLayoutY(400);
					t1.setText("Enter your name...");
//----------------------------------------------------Settings------------------------------------------------------------------------------------------------
					EventHandler<ActionEvent> settings = new EventHandler<ActionEvent>(){//event handler called settings used to 

						@Override
						public void handle(ActionEvent arg0) {
							// TODO Auto-generated method stub
							statStage.show();//shows the new stage in a new window.
						}};
					b4.setOnAction(settings);//settings will start on the button press of b4.
//----------------------------------------------------Operator-Menu------------------------------------------------------------------------------------------
					EventHandler<ActionEvent> saveYN = new EventHandler<ActionEvent>(){

						@Override
						public void handle(ActionEvent arg0) {
							// TODO Auto-generated method stub
							end.show();
						}};

					EventHandler<ActionEvent> save = new EventHandler<ActionEvent>(){

						@Override
						public void handle(ActionEvent arg0) {
							File file = fileChooser.showSaveDialog(stage);//This set the file to save as the file that is selected in the save as box.
							if (file != null) {//Once a file has been selected it will run the saveFile method.
							saveFile(file);
							}
							end.close();//closes the end window
						}};
					fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));//This sets the initial directory to be the current working directory.
					yes.setOnAction(save);
					saveM.setOnAction(save);
					b2.setOnAction(saveYN);
					p1.getChildren().addAll(bA,bS,bM,bD);
					bA.setLayoutX(200);
					bA.setLayoutY(250);
					bA.setMinHeight(50);
					bA.setMinWidth(150);
					bA.setVisible(false);
					
					bS.setLayoutX(400);
					bS.setLayoutY(250);
					bS.setMinHeight(50);
					bS.setMinWidth(150);
					bS.setVisible(false);
					
					bM.setLayoutX(200);
					bM.setLayoutY(400);
					bM.setMinHeight(50);
					bM.setMinWidth(150);
					bM.setVisible(false);
					
					bD.setLayoutX(400);
					bD.setLayoutY(400);
					bD.setMinHeight(50);
					bD.setMinWidth(150);
					bD.setVisible(false);
					p1.getChildren().addAll(l1, l2, l3, l4, l5, b3);
					b3.setVisible(false);
					EventHandler<ActionEvent> instructions = new EventHandler<ActionEvent>(){

						@Override
						public void handle(ActionEvent arg0) {
							// TODO Auto-generated method stub
							if(t1.getText().equals("Enter your name...")){
								//does nothing if the user hasn't entered their name.
							}
							else{
							name=t1.getText();//sets the name string to the value of t1.
							players.add(new Ship(name, multy, divy, addy, suby, 350, 500));//creates a new player and adds it to the players arraylist.
							b4.setVisible(false);
							t1.setVisible(false);
							b1.setVisible(false);
							gc.drawImage(background, 0, 0, 800, 600);
							gc.drawImage(keys, 520, 185, 150, 100);
							gc.drawImage(spacebar, 520, 400, 200, 50);
							title.setText("INSTRUCTIONS:");
							title.setLayoutX(50);
							title.setLayoutY(50);
							title.setFont(Font.font("ROCKWELL", 50));
							
							l1.setLayoutX(50);
							l1.setLayoutY(200);
							l1.setVisible(true);
							l1.setFont(Font.font("ROCKWELL", 30));
							l1.setText("Use the left and right"+ "\n" + "arrow keys to move your ship");
							
							l2.setLayoutX(50);
							l2.setLayoutY(380);
							l2.setVisible(true);
							l2.setFont(Font.font("ROCKWELL", 30));
							l2.setText("Press the space bar to use"+ "\n" + "your ships lasers");
							
							b3.setVisible(true);
							b3.setLayoutX(520);
							b3.setLayoutY(500);
							b3.setMinHeight(50);
							b3.setMinWidth(100);
							}
						}};
					b1.setOnAction(instructions);
					EventHandler<ActionEvent> operator = new EventHandler<ActionEvent>(){//Creates the event handler called operator

						@Override
						public void handle(ActionEvent arg0) {
							// TODO Auto-generated method stub
							b2.setVisible(false);
							b3.setVisible(false);
							l1.setVisible(false);
							l2.setVisible(false);
							l5.setVisible(false);
							bA.setVisible(true);
							bS.setVisible(true);
							bM.setVisible(true);
							bD.setVisible(true);
							b1.setVisible(false);
							end.close();
							gc.drawImage(background, 0, 0, 800, 600);
							
							l2.setLayoutY(0);
							l2.setText("?");
							l2.setLayoutX(300);
							l2.setFont(Font.font("ROCKWELL", 50));
							life=0;
							title.setFont(Font.font("ROCKWELL", 50));
							title.setLayoutX(80);
							title.setLayoutY(50);
							title.setText("Please Choose An Operator:");
							title.setVisible(true);
						}};
					b3.setOnAction(operator);
					no.setOnAction(operator);
//----------------------------------------------------Game-----------------------------------------------------------------------------------------------
					
					l1.setText("Make: "+rAns);
					l1.setFont(Font.font("ROCKWELL", 50));
					l1.setStyle("-fx-base: #000000");
					l1.setVisible(false);

					l2.setLayoutX(300);
					l2.setFont(Font.font("ROCKWELL", 50));
					l2.setStyle("-fx-base: #000000");
					l2.setVisible(false);

					l3.setLayoutX(350);
					l3.setFont(Font.font("ROCKWELL", 50));
					l3.setStyle("-fx-base: #000000");
					l3.setVisible(false);

					l4.setLayoutX(400);
					l4.setFont(Font.font("ROCKWELL", 50));
					l4.setStyle("-fx-base: #000000");
					l4.setVisible(false);

					l5.setText("Score: "+ score);
					l5.setLayoutX(500);
					l5.setFont(Font.font("ROCKWELL", 50));
					l5.setStyle("-fx-base: #000000");
					l5.setVisible(false);

					EventHandler<ActionEvent> add = new EventHandler<ActionEvent>(){

						@Override
						public void handle(ActionEvent arg0) {
							// TODO Auto-generated method stub
							op = 1;
							bA.setVisible(false);
							bS.setVisible(false);
							bM.setVisible(false);
							bD.setVisible(false);
							rAns=r1.nextInt(18);
							l1.setFont(Font.font("ROCKWELL", 50));
							l1.setLayoutX(0);
							l1.setLayoutY(0);
							l1.setText("Make: "+rAns);
							l3.setText("+");
							title.setVisible(false);
							l1.setVisible(true);
							l2.setVisible(true);
							l3.setVisible(true);
							l4.setVisible(true);
							l5.setVisible(true);
							at.start();
						}};
					
					EventHandler<ActionEvent> sub = new EventHandler<ActionEvent>(){

						@Override
						public void handle(ActionEvent arg0) {
							// TODO Auto-generated method stub
							op = 2;
							bA.setVisible(false);
							bS.setVisible(false);
							bM.setVisible(false);
							bD.setVisible(false);
							rAns=r1.nextInt(8);
							l1.setFont(Font.font("ROCKWELL", 50));
							l1.setLayoutX(0);
							l1.setLayoutY(0);
							l1.setText("Make: "+rAns);
							l3.setText("-");
							title.setVisible(false);
							l1.setVisible(true);
							l2.setVisible(true);
							l3.setVisible(true);
							l4.setVisible(true);
							l5.setVisible(true);
							at.start();
						}};
					EventHandler<ActionEvent> mult = new EventHandler<ActionEvent>(){

						@Override
						public void handle(ActionEvent arg0) {
							// TODO Auto-generated method stub
							op = 3;
							bA.setVisible(false);
							bS.setVisible(false);
							bM.setVisible(false);
							bD.setVisible(false);
							l1.setFont(Font.font("ROCKWELL", 50));
							l1.setLayoutX(0);
							l1.setLayoutY(0);
							multiply();
							l1.setText("Make: "+rAns);
							l3.setText("X");
							title.setVisible(false);
							l1.setVisible(true);
							l2.setVisible(true);
							l3.setVisible(true);
							l4.setVisible(true);
							l5.setVisible(true);
							at.start();
						}};
					EventHandler<ActionEvent> div = new EventHandler<ActionEvent>(){

						@Override
						public void handle(ActionEvent arg0) {
							// TODO Auto-generated method stub
							op = 4;
							bA.setVisible(false);
							bS.setVisible(false);
							bM.setVisible(false);
							bD.setVisible(false);
							l1.setFont(Font.font("ROCKWELL", 50));
							l1.setLayoutX(0);
							l1.setLayoutY(0);
							divide();
							l1.setText("Make: "+rAns);
							l3.setText("/");
							title.setVisible(false);
							l1.setVisible(true);
							l2.setVisible(true);
							l3.setVisible(true);
							l4.setVisible(true);
							l5.setVisible(true);
							at.start();
						}};
						EventHandler<MouseEvent> click = new EventHandler<MouseEvent>(){

							@Override
							public void handle(MouseEvent arg0) {
								// TODO Auto-generated method stub
								Ship v = players.get(lv1.getSelectionModel().getSelectedIndex());//This is setting the values of Viewer v as being the person who they have selected in ListView 1.
								//This is an example of setting the string value of a text field to one of the values of a viewer. In this case it was their name.
								lA.setText("Addition Score: "+ v.add/50);//gets the value of the addition score of the player selected.
								lS.setText("Subtraction Score: "+v.sub/50);
								lM.setText("Multiplication Score"+v.mult/50);
								lD.setText("Division Score: "+v.div/50);
							}};
						lv1.setOnMouseClicked(click);//click will activate when the listview is pressed.
						fileChooser.getExtensionFilters().add(ef);//sets the extension filter to fileChoose.
						openM.setOnAction(new EventHandler<ActionEvent>(){//will activate when the openM menu item is pressed.

							@Override
							public void handle(ActionEvent arg0) {
								// TODO Auto-generated method stub
								File file = fileChooser.showOpenDialog(stage);//This sets the file that is to be opened when it is selected.
								if(file!=null){
									openFile(file);//This will run the openFile method.
									for(Ship v: players){//This runs through everybody in the file being opened and adds then to the list view.
										lv1.getItems().add(v.name);
									}
								}}
						private void openFile(File file) {
							String str;
							FileInputStream myfis = null;//This is the file that is going into the application.
							try{
								myfis = new FileInputStream(file);//This is setting the Input stream as being the file that is selected.
							} catch(FileNotFoundException e){//This is incase they try to open when no file is selected.
								System.out.println(e.getMessage());
							}
							BufferedReader myBr
								= new BufferedReader(new InputStreamReader(myfis));//This buffers the information so that the application can handle it and doesn't crash when it trys to open the file.
							try{//A try and catch in case an invalid input is added. 
								players.clear();//This clears the current viewer array list in the calculations class.
								while ( (str=myBr.readLine()) != null) { 
									String[] strs = str.split(",");//This is saying that the file is comma separated.
									String name=strs[0];//This is getting the getting the name from column 0,etc.
									String mult = strs[1];
									String div = strs[2];
									String add = strs[3];
									String sub = strs[4];
									String x = strs[5];
									String y = strs[6];
									//The line bellow adds the current person selected to the ArrayList that my viewers are stored in.
									players.add(new Ship(name, Integer.parseInt(mult), Integer.parseInt(div), Integer.parseInt(add), Integer.parseInt(sub), Double.parseDouble(x), Double.parseDouble(y)));
								}
							} catch(Exception e){
								System.out.println(e.getMessage());
							}
							statStage.close();
						}});
						
						bA.setOnAction(add);
						bS.setOnAction(sub);
						bM.setOnAction(mult);
						bD.setOnAction(div);
						stage.show();

				}

				public void multiply(){
					mAns = r1.nextInt(81);
					for(int i=1; i<10; i++){
						for(int j=1; i<10; i++){
							if(mAns == j*i){//checks that the mAns can be achieved by multiplication.
								rAns = mAns;
								l1.setText("Make: " +rAns);
								boolM=true;
								break;
							}
						}
					}
				}
				public void divide(){
					dAns = r1.nextInt(9);
					for(int i=1; i<10; i++){
						for(int j=1; i<10; i++){
							if(dAns == i/j){//checks that dAns can be achieved by division.
								rAns=dAns;
								boolD=true;
								break;
							}
						}
					}
				}
				public void generate(){
					randoms = r1.nextInt(9);//selects a value between 0 and 9;
					if(randoms==0){//if the random number is 0 it will create a station 1.
						stations.add(new Station1(r2.nextInt(700),-(r3.nextInt(500))));
					}
					if(randoms==1){
						stations.add(new Station2(r2.nextInt(700),-(r3.nextInt(500))));
					}
					if(randoms==2){
						stations.add(new Station3(r2.nextInt(700),-(r3.nextInt(500))));
					}
					if(randoms==3){
						stations.add(new Station4(r2.nextInt(700),-(r3.nextInt(500))));
					}
					if(randoms==4){
						stations.add(new Station5(r2.nextInt(700),-(r3.nextInt(500))));
					}
					if(randoms==5){
						stations.add(new Station6(r2.nextInt(700),-(r3.nextInt(500))));
					}
					if(randoms==6){
						stations.add(new Station7(r2.nextInt(700),-(r3.nextInt(500))));
					}
					if(randoms==7){
						stations.add(new Station8(r2.nextInt(700),-(r3.nextInt(500))));
					}
					if(randoms==8){
						stations.add(new Station9(r2.nextInt(700),-(r3.nextInt(500))));
					}
				}

				void saveFile(File file) {
					FileOutputStream myFos = null;//the stream of data that is being saved.
					try {
						myFos = new FileOutputStream(file);
					} catch (FileNotFoundException e) {//in case they try to save a file that has not been given a title.
						System.out.println(e.getMessage());
					} 
					PrintWriter myPw = new PrintWriter(myFos, true);//writes the data onto the save file.
					for (Ship v : players)
						//runs through the viewer array list and gets all of the values that they have selected out of it.
						myPw.println(v.name+","+v.mult+","+v.div+","+v.add+","+v.sub+","+v.x+","+v.y);
					try {
						myFos.close();//closes the output stream so that it stops the saving process.
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
				}
				void showList(){
					lv1.getItems().clear();//clears the current list view.
					for(Ship v : players)//for each loop to scan through the players array list.
						lv1.getItems().add(v.name);//adds all of the names of the people in the players arraylist.
				
			}

}

class Ship{//the class of the player called ship.
	Image img;//declaring a field of image class.
	Rectangle r;
	Bounds b;
	int dx =10, dy=10;
	String name;
	double x, y;//declaring double values of x and y.
	int mult, div, add, sub;
	public Ship(String name, int mult, int div, int add, int sub, double x, double y) {//constructor method that will that requires the variables to be satisfied when instantiated.
		super();
		this.name=name;//setting the field variable to the variable in the constructor method.
		this.mult=mult;
		this.div=div;
		this.add=add;
		this.sub=sub;
		this.x=x;
		this.y=y;
		img = new Image(Assignment2.class.getResource("Resources/Spaceship.png").toExternalForm());
		r = new Rectangle(x,y,80,80);
		b = r.getBoundsInParent();
	}
	public void moveLeft(){//a move method called moveLeft that will alter the position of the 
		r.setX(r.getX()-dx);
		b = r.getBoundsInParent();
	}
	public void moveRight(){
		r.setX(r.getX()+ dx);
		b = r.getBoundsInParent();
	}
}
class Station{//creating the parent class called station.
	Image img;
	Rectangle r;
	Bounds b;
	int id;
	int dy=3;
	public Station(double x, double y) {//constructor methods that only require an x and y value.
		super();
		r = new Rectangle(x,y,80,80);
		b = r.getBoundsInParent();
	}
	public void move(){
		r.setY(r.getY()+dy);
		b = r.getBoundsInParent();		
	}
}
class Station1 extends Station{//using polymorphism to inherit methods and fields from the parent class station.

	public Station1(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		id=1;
		img = new Image(Assignment2.class.getResource("Resources/spacestation 1.png").toExternalForm());
	}	
}
class Station2 extends Station{

	public Station2(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		id=2;
		img = new Image(Assignment2.class.getResource("Resources/spacestation 2.png").toExternalForm());
	}	
}
class Station3 extends Station{

	public Station3(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		id=3;
		img = new Image(Assignment2.class.getResource("Resources/spacestation 3.png").toExternalForm());
	}	
}
class Station4 extends Station{

	public Station4(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		id=4;
		img = new Image(Assignment2.class.getResource("Resources/spacestation 4.png").toExternalForm());
	}	
}
class Station5 extends Station{

	public Station5(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		id=5;
		img = new Image(Assignment2.class.getResource("Resources/spacestation 5.png").toExternalForm());
	}
}
class Station6 extends Station{

	public Station6(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		id=6;
		img = new Image(Assignment2.class.getResource("Resources/spacestation 6.png").toExternalForm());
	}	
}
class Station7 extends Station{

	public Station7(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		id=7;
		img = new Image(Assignment2.class.getResource("Resources/spacestation 7.png").toExternalForm());
	}	
}
class Station8 extends Station{

	public Station8(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		id=8;
		img = new Image(Assignment2.class.getResource("Resources/spacestation 8.png").toExternalForm());
	}	
}
class Station9 extends Station{

	public Station9(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		id=9;
		img = new Image(Assignment2.class.getResource("Resources/spacestation 9.png").toExternalForm());
	}
}
class Bullet{//an individual class for the bullets fired.
	Image img, bang;
	Rectangle r;
	Bounds b;
	int dy=-10;
	public Bullet(double x, double y){
		img = new Image(Assignment2.class.getResource("Resources/Lightsaber.png").toExternalForm());
		r = new Rectangle(x,y,20,60);
		b=r.getBoundsInParent();
	}
	public void move(){
		r.setY(r.getY()+dy);
		b=r.getBoundsInParent();
	}
	public void reset(){//resetting the position of the bullet.
		r.setY(500);
		r.setX(400);
	}
}
