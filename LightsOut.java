//=============================================================================================================================
// Lights Out
// Michael Qian
// December 16, 2018
// Eclipse Java EE IDE for Web Developers, 20180619-1200
//=============================================================================================================================
// Problem Definition 	-Create a Light Bulb solitaire game with various features 
//I - User must input their name and click buttons 
//O - Outputs include a victory message, displaying an updated board, solutions, and a give up statement
//P - Processes include which buttons were pressed, how many were pressed, which lights turn off/on upon user action, etc.
//=============================================================================================================================

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
public class LightsOut extends Applet{//Start of main method 
	
	int []playerscore=new int[11];//array playerscore holding 11 int variables, used to store playerscores for reading/writing
	String []playername=new String[11];
	
	BufferedReader reader = null;//BufferedReader object name set to null, used for reading files 
	int playerinput=0;//int variable playerinput set to 0, used for tracking if a name was inputted by the user  
	int playerclickcount=1000;//int variable playerclickcount, used to store clickcounts and determines the smalles click count
	int giveup=0;//int giveup, determines whether user has pressed the give up button 
	Button buttonExit=new Button();//creations of buttonExit, a button that when pressed
	int SolutionCount=0;//int solutioncount, counts the amount of turned off lights during the solution actions 
	int SolutionTrack=0;//int SolutionTrack, ensures that solution actions keep repeating after a single press of button3, solution
	int y=0;//int y, used to track if the user has clicked 5 times 
	int x=0;//int x, used to track if the user has completed the game 
	int num1=5;//int num1, set to different values according to user action, used to output different statements depending on user action
	int z=0;//int z, used to determine if a statement indicating user click count appears 
	String player=null;//String player, used to store the users name 
	TextField name;	//textfield name, used to create a textfield for the user to input their name 
	
	Button buttons[] [] = new Button [5] [5];	//creates a 5x5 array of buttons where the lights will appear 
    Button button1=new Button();//creates the "reset" button
    Button button2=new Button();//creates the "new game" button 
    Button button3=new Button();//creates the "solution" button 
    int store[][]=new int[5][5];//creates a 2d array of type int to store the locations of lights turned on after a new game 
    
	int clickcount=0;//int clickcount, used to keep track of user clicks on the light grid (buttons[][])	
	
	/**processing method:
	 * This procedural method stores a sent 2d array within global array store [][] 

	 * @param int Replay[][]
	 *
	
	 */	
	public void NumStore(int Replay[][]) {
		
		for (int i=0;i<5;i++) {
			for (int j=0;j<5;j++) {
				store[i][j]=Replay[i][j];
				}
			}
		}
	
	/**output method:
	 * This procedural method sets up the array and its contents 
	 *
 
	 * @param none
	 
	 * 
	 */	
public void init (){
    	
        setLayout (null); 	//turns off the Layout manager so you must //position each item
        setSize (600, 500);
        
        name = new TextField(8);
        name.setBounds(120, 5, 100, 25);
        add(name);
        name.setText(null);
        
        button1.setBounds(140, 75, 80, 60);
        button1.setBackground(Color.gray);
        add(button1);
        button1.setLabel("Reset");
        
        button2.setBounds(220, 75, 80, 60);
        button2.setBackground(Color.gray);
        add(button2);
        button2.setLabel("New Game");
        
        button3.setBounds(60, 75, 80, 60);
        button3.setBackground(Color.gray);
        add(button3);
        button3.setLabel("Solution");
      
        for (int i = 0 ; i < 5 ; i++){
        	for (int j = 0 ; j < 5 ; j++){
        			
        			buttons [i] [j] = new Button();
        			buttons [i] [j].setBounds (i * 60 + (getSize ().width - 150) / 2,
        					j * 60 + (getSize ().height - 150) / 2, 60, 60);
        			buttons[i][j].setBackground(Color.black);
        			
        			add (buttons [i] [j]);
        		}
			}
   	
      }


	public void paint(Graphics g){
		
		int datanum=310;
		int linenum=310;
		
		super.paint(g);
       
       g.drawString("Top Scores:", 10, 290);
 	  String fileName = "c:\\playername.txt";
 	  String line = null;
 	  try { int accumulate=1;
			
 		  FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader= new BufferedReader(fileReader);
			while((line = bufferedReader.readLine()) != null) {
				 playername[accumulate]=line;
				g.drawString(line, 10, linenum);
            linenum=linenum+15;
           
           accumulate++;
         }   
			bufferedReader.close();         
 	  } 
 	  catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} 
 	 catch (IOException e) {
			
			e.printStackTrace();
		}
 	  
 	 String DataName = "c:\\data.txt";
 	 try {	int accumulate=1;
 		
 		 reader = new BufferedReader(new FileReader(DataName));
 	    String text = null;
 	    
 	    while ((text= reader.readLine())!= null) {
 	       playerscore[accumulate]=Integer.parseInt(text);
 	      
           g.drawString(text, 95, datanum);
           datanum=datanum+15;
          
         
          accumulate++;
         
        }   
 	   reader.close();  
	  } 
	  catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} 
	 catch (IOException e) {
			
			e.printStackTrace();
		}  
 
 	  
 	  
        String msg=("Mode Selection");
		g.drawString (msg, 140, 65);
		
		
		g.drawString("Enter Your Name: ",15,20);

       
         
        if (num1==0) {
        	String msg1=("Lights Out Reset");
        	g.drawString (msg1, 365, 100);
           }
           else if (num1==1) {
        	   String msg2=("New Game Chosen");
           	g.drawString (msg2, 365, 100);
           }
           else if (num1==2) {
        	   String msg2=("Game Cannot Be Reset");
           	g.drawString (msg2, 335, 100);
           		String msg3=("Please Select New Game");
           	g.drawString (msg3, 330, 115);
           	num1=5;
           }
           else if (num1==3) {
        	   String msg4=("Solution Chosen");
           	g.drawString (msg4, 365, 100);
           }
           else if (num1==4) {
        	   String msg5=("Solution Unavailible");
           	g.drawString (msg5, 335, 100);
           		String msg6=("Please Select New Game");
           	g.drawString (msg6, 330, 115);
           	num1=5;
           }
        
        if (x==1) {
        	 String msg4=("You finished the puzzle with "+clickcount+" clicks");
            	g.drawString (msg4, 305, 160); 
            	int track=0;
            	String nametrack=null;
            	if(clickcount<playerclickcount){
            		playerclickcount=clickcount;
            		playerscore[0]=playerclickcount;
            		playername[0]=player;
            	}
            	 for (int i = 0; i < 11-1; i++)  {
            		 for (int j = 0; j < 11-i-1; j++)  {
            			 if(playerscore[j]>playerscore[j+1]) {
            				  track=playerscore[j+1];
            				 playerscore[j+1]=playerscore[j];
            				 playerscore[j]=track;
            				 nametrack=playername[j+1];
            				 playername[j+1]=playername[j];
            				 playername[j]=nametrack;
            			
            			 }
            		 }
            	 }
            	 for (int i = 0; i < 10; i++)  {
            		 System.out.println(playerscore[i]);
            		 System.out.println(playername[i]);
            	 }
            	 try {
					FileOutputStream file =new FileOutputStream("c:\\data.txt");
					         
					 OutputStreamWriter Output = new OutputStreamWriter(file);
					 Writer write = new BufferedWriter(Output);
					 
					 for (int i=0;i<10;i++)  {
						 write.write(String.valueOf(playerscore[i])+"\n");
						
						 }	
					  write.close();
					 }
             catch (IOException e) {
					
					e.printStackTrace();
				}
            	 try {
 					FileOutputStream namefile =new FileOutputStream("c:\\playername.txt");
 					         
 					 OutputStreamWriter Output2 = new OutputStreamWriter(namefile);
 					 Writer write2 = new BufferedWriter(Output2);
 					 
 					 for (int i=0;i<10;i++)  {
 						 write2.write((playername[i])+"\n");
 						
 						 }	
 					  write2.close();
 					 }
              catch (IOException e) {
 					
 					e.printStackTrace();
 				}
					 
            	 x=0;
             	z=0;
             	clickcount=0;
         
					 }
				
      if (y==1) {
          	 String msg5=("You have clicked 5 times. Give up?");
              	g.drawString (msg5, 10, 175);  y=0; 
          }
        
        if (z==1) {
         	 String msg6=("You have clicked "+clickcount+" times");
             	g.drawString (msg6, 10, 160);  
         }
        if(giveup==1) {
        	g.drawRect (0,0,600,600);
        	g.fillRect (0,0,600,600);
            g.setColor(Color.black);
            String msg5=("You have given up");
        	g.drawString (msg5, 305, 160); 
       
        }
        if (playerinput==1) {
        	 String msg7=("Enter a name before continuing");
            	g.drawString (msg7, 230, 20);  
            	playerinput=0;
        }
        else if (playerinput==2) {
       	 String msg8=("Welcome, " +player);
           	g.drawString (msg8, 230, 20);  
           	playerinput=0;
       }
    }
   
  
  
	
  
    public boolean action (Event e, Object o ){
    	int Replay[][]=new int[5][5];
    
    
    		double num=0;
    		
    		 try
             {
            	 player=name.getText();
             
             }
             catch(Exception f)
             {
            	 
             }
    		
    		 if (player.equals("")){
    			playerinput=1;
    			repaint();
    		}
    		else if(!player.equals("")){
    			playerinput=2;
    			repaint();
    		}
    		
    		
    		if (e.target == button2&&playerinput!=1) {  
    			buttonExit.setVisible(false);
        		z=0;
    			clickcount=0;
    			SolutionTrack=0;
        		num1=1;
        		repaint();
        		
        		for (int i = 0 ; i < 5 ; i++){
                     for (int j = 0 ; j < 5 ; j++){
                   	  buttons[i][j].setLabel("");
                   	
                     }
           	  }
        		
        		for (int i = 0 ; i < 5 ; i++){
        			for (int j = 0 ; j < 5 ; j++){
        				buttons[i][j].setBackground(Color.black);
        			
        			}
        		}
        		
        		for (int i = 0 ; i < 5 ; i++){
        			for (int j = 0 ; j < 5 ; j++){
        				num=(Math.round(Math.random()*1));
            			if(num==1) {
            				
            				Replay[i][j]=1;
            				
            				if(i==0&&j==0) {
                        		Switcher(buttons[i][j]);
                        		Switcher(buttons[i+1][j]);
                        		Switcher(buttons[i][j+1]);
                        	
                        	}
                        	
                        	else if(i==0&&j==4) {
                        		Switcher(buttons[i][j]);
                        		Switcher(buttons[i+1][j]);
                        		Switcher(buttons[i][j-1]);
                        	
                        	}
                        	
                        	else if(i==4&&j==0) {
                        		Switcher(buttons[i][j]);
                        		Switcher(buttons[i-1][j]);
                        		Switcher(buttons[i][j+1]);
                        	
                        	}
                        	
                        	else if(i==4&&j==4) {
                        		Switcher(buttons[i][j]);
                        		Switcher(buttons[i-1][j]);
                        		Switcher(buttons[i][j-1]);
                        	
                        	}
                        	
                        	else if(i==0) {
                        		Switcher(buttons[i][j]);
                        		Switcher(buttons[i+1][j]);
                        		Switcher(buttons[i][j+1]);
                        		Switcher(buttons[i][j-1]);
                        	}
                        	
                        	else if(j==0) {
                        		Switcher(buttons[i][j]);
                        		Switcher(buttons[i+1][j]);
                        		Switcher(buttons[i-1][j]);
                        		Switcher(buttons[i][j+1]);
                        	}
                        	
                        	else if(i==4) {
                        		Switcher(buttons[i][j]);
                        		Switcher(buttons[i-1][j]);
                        		Switcher(buttons[i][j+1]);
                        		Switcher(buttons[i][j-1]);
                        	}
                        	
                        	else if(j==4) {
                        		Switcher(buttons[i][j]);
                        		Switcher(buttons[i-1][j]);
                        		Switcher(buttons[i+1][j]);
                        		Switcher(buttons[i][j-1]);
                        	}
                        	else {
                        	for(int x=-1;x<=1;x++) {
                        		Switcher(buttons[i+x][j]);
                        		}
                        	Switcher(buttons[i][j+1]);
                        	Switcher(buttons[i][j-1]);
                        	}
                        
            			}
            			else{Replay[i][j]=0;}
            			num=0;
            			
                	}
        		}
        		NumStore(Replay);
        	 }
    		
    		else if (e.target == button1&&playerinput!=1) {        		
    			buttonExit.setVisible(false);
    			SolutionTrack=0;
    			clickcount=0;
    			y=0;
    			z=0;
        		if (num1==1||num1==3) {
        			num1=0;
        			repaint();
        		}
        		else if (num1==5) {
        			num1=2;
        			repaint();
        		}
        		
        		repaint();
        		 
        		for (int i = 0 ; i < 5 ; i++){
                     for (int j = 0 ; j < 5 ; j++){
                   	  buttons[i][j].setLabel("");
                   	
                     }
           	  }
        		
        		if(num!=3) {
        		Replay=store;
        		for (int i = 0 ; i < 5 ; i++){
        			for (int j = 0 ; j < 5 ; j++){
        				buttons[i][j].setBackground(Color.black);
        				
        			}
        		}
        		
        		for (int i = 0 ; i < 5 ; i++){
        			for (int j = 0 ; j < 5 ; j++){
        				
            			if(Replay[i][j]==1) {
            				
            				if(i==0&&j==0) {
                        		Switcher(buttons[i][j]);
                        		Switcher(buttons[i+1][j]);
                        		Switcher(buttons[i][j+1]);
                        	
                        	}
                        	
                        	else if(i==0&&j==4) {
                        		Switcher(buttons[i][j]);
                        		Switcher(buttons[i+1][j]);
                        		Switcher(buttons[i][j-1]);
                        	
                        	}
                        	
                        	else if(i==4&&j==0) {
                        		Switcher(buttons[i][j]);
                        		Switcher(buttons[i-1][j]);
                        		Switcher(buttons[i][j+1]);
                        	
                        	}
                        	
                        	else if(i==4&&j==4) {
                        		Switcher(buttons[i][j]);
                        		Switcher(buttons[i-1][j]);
                        		Switcher(buttons[i][j-1]);
                        	
                        	}
                        	
                        	else if(i==0) {
                        		Switcher(buttons[i][j]);
                        		Switcher(buttons[i+1][j]);
                        		Switcher(buttons[i][j+1]);
                        		Switcher(buttons[i][j-1]);
                        	}
                        	
                        	else if(j==0) {
                        		Switcher(buttons[i][j]);
                        		Switcher(buttons[i+1][j]);
                        		Switcher(buttons[i-1][j]);
                        		Switcher(buttons[i][j+1]);
                        	}
                        	
                        	else if(i==4) {
                        		Switcher(buttons[i][j]);
                        		Switcher(buttons[i-1][j]);
                        		Switcher(buttons[i][j+1]);
                        		Switcher(buttons[i][j-1]);
                        	}
                        	
                        	else if(j==4) {
                        		Switcher(buttons[i][j]);
                        		Switcher(buttons[i-1][j]);
                        		Switcher(buttons[i+1][j]);
                        		Switcher(buttons[i][j-1]);
                        	}
                        	else {
                        	for(int x=-1;x<=1;x++) {
                        		Switcher(buttons[i+x][j]);
                        		}
                        	Switcher(buttons[i][j+1]);
                        	Switcher(buttons[i][j-1]);
                        	}
                        
            			}
            			
                	}
        		}
        		
        	}
    		}
        	
          
        for (int i = 0 ; i < 5 ; i++){
            for (int j = 0 ; j < 5 ; j++){
                if (e.target == buttons [i] [j]&&playerinput!=1){
                	z=1;
                	repaint();
                	if(i==0&&j==0) {
                		Switcher(buttons[i][j]);
                		Switcher(buttons[i+1][j]);
                		Switcher(buttons[i][j+1]);
                	
                	}
                	
                	else if(i==0&&j==4) {
                		Switcher(buttons[i][j]);
                		Switcher(buttons[i+1][j]);
                		Switcher(buttons[i][j-1]);
                	
                	}
                	
                	else if(i==4&&j==0) {
                		Switcher(buttons[i][j]);
                		Switcher(buttons[i-1][j]);
                		Switcher(buttons[i][j+1]);
                	
                	}
                	
                	else if(i==4&&j==4) {
                		Switcher(buttons[i][j]);
                		Switcher(buttons[i-1][j]);
                		Switcher(buttons[i][j-1]);
                	
                	}
                	
                	else if(i==0) {
                		Switcher(buttons[i][j]);
                		Switcher(buttons[i+1][j]);
                		Switcher(buttons[i][j+1]);
                		Switcher(buttons[i][j-1]);
                	}
                	
                	else if(j==0) {
                		Switcher(buttons[i][j]);
                		Switcher(buttons[i+1][j]);
                		Switcher(buttons[i-1][j]);
                		Switcher(buttons[i][j+1]);
                	}
                	
                	else if(i==4) {
                		Switcher(buttons[i][j]);
                		Switcher(buttons[i-1][j]);
                		Switcher(buttons[i][j+1]);
                		Switcher(buttons[i][j-1]);
                	}
                	
                	else if(j==4) {
                		Switcher(buttons[i][j]);
                		Switcher(buttons[i-1][j]);
                		Switcher(buttons[i+1][j]);
                		Switcher(buttons[i][j-1]);
                	}
                	else {
                	for(int x=-1;x<=1;x++) {
                		Switcher(buttons[i+x][j]);
                		}
                	Switcher(buttons[i][j+1]);
                	Switcher(buttons[i][j-1]);
                	}
                	 clickcount++;
                }
                
            }	
            
        }
          Checker(buttons);
         
          if (e.target == button3&&playerinput!=1||SolutionTrack==1&&playerinput!=1){
        	  buttonExit.setVisible(false);
        	  if(num1==5) {
        		  num1=4;
        		  repaint();
        	  }
        	  else if(num1==1||num1==0) {
        		  num1=3;
        		  repaint();
        	  }
        	  SolutionCount=0;
        	  SolutionTrack=1;
        	  for (int i = 0 ; i < 5 ; i++){
                  for (int j = 0 ; j < 5 ; j++){
                	  buttons[i][j].setLabel("");
                	
                  }
        	  }
        	 
        	  for (int i = 0 ; i < 5 ; i++){
                  for (int j = 0 ; j < 4 ; j++){  
                	  if (buttons[i][j].getBackground().equals(Color.black)) {
            		  SolutionCount++;
            	  }
              }
    	  }
        	  if (SolutionCount==20) {
        		  
        		  for (int i = 0 ; i < 5 ; i++){
        			  if(buttons[0][4].getBackground().equals(Color.yellow)
        					  &&buttons[1][4].getBackground().equals(Color.yellow)&&
        					  buttons[2][4].getBackground().equals(Color.yellow)) {
        				  Switcher(buttons[1][0]);
        				  Switcher(buttons[0][0]);
        				  Switcher(buttons[2][0]);
        				  Switcher(buttons[1][1]);
        			  }
        			  else if(buttons[0][4].getBackground().equals(Color.yellow)
        					  &&buttons[1][4].getBackground().equals(Color.yellow)&&
        					  buttons[3][4].getBackground().equals(Color.yellow)&&
        					  buttons[4][4].getBackground().equals(Color.yellow)) {
        				  Switcher(buttons[2][0]);
        				  Switcher(buttons[1][0]);
        				  Switcher(buttons[3][0]);
        				  Switcher(buttons[2][1]);
        			  }
        			  else if(buttons[0][4].getBackground().equals(Color.yellow)
        					  &&buttons[2][4].getBackground().equals(Color.yellow)&&
        					  buttons[3][4].getBackground().equals(Color.yellow)) {
        				  Switcher(buttons[4][0]);
        				  Switcher(buttons[3][0]);
        				  Switcher(buttons[4][1]);
        			  }
        			  else if(buttons[0][4].getBackground().equals(Color.yellow)&&
        					  buttons[4][4].getBackground().equals(Color.yellow)) {
        				  Switcher(buttons[0][0]);
        				  Switcher(buttons[1][0]);
        				  Switcher(buttons[0][1]);
        				  Switcher(buttons[1][0]);
        				  Switcher(buttons[0][0]);
        				  Switcher(buttons[2][0]);
        				  Switcher(buttons[1][1]);
        			  }
        			  else if(buttons[1][4].getBackground().equals(Color.yellow)&&
        					  buttons[2][4].getBackground().equals(Color.yellow)&&
        					  buttons[4][4].getBackground().equals(Color.yellow)) {
        				  Switcher(buttons[0][0]);
        				  Switcher(buttons[1][0]);
        				  Switcher(buttons[0][1]);
        				  }
        			  else if(buttons[1][4].getBackground().equals(Color.yellow)&&
        					  buttons[3][4].getBackground().equals(Color.yellow)) {
        				  Switcher(buttons[0][0]);
        				  Switcher(buttons[1][0]);
        				  Switcher(buttons[0][1]);
        				  Switcher(buttons[3][0]);
        				  Switcher(buttons[2][0]);
        				  Switcher(buttons[4][0]);
        				  Switcher(buttons[3][1]);
        			  }
        			  else if(buttons[2][4].getBackground().equals(Color.yellow)&&
        					  buttons[3][4].getBackground().equals(Color.yellow)&&
        					  buttons[4][4].getBackground().equals(Color.yellow)) {
        				  Switcher(buttons[3][0]);
        				  Switcher(buttons[2][0]);
        				  Switcher(buttons[4][0]);
        				  Switcher(buttons[3][1]);
        				  }
        		  }
        	  }
        	  
        	  for (int i = 0 ; i < 5 ; i++){
                  for (int j = 1 ; j < 5 ; j++){
                	  if (buttons [i][j-1].getBackground().equals(Color.yellow)){
                		  	buttons[i][j].setLabel("Press");
                		  	buttons[i][j].setForeground(Color.gray);
                		  	
                	  }
                  }
        	  }
          }
          if (e.target == buttonExit&&clickcount>=5){
        	  for (int i = 0 ; i < 5 ; i++){
                  for (int j = 0 ; j < 5 ; j++){
                	  buttons[i][j].setEnabled(false);
                	  buttons[i][j].setBackground(Color.white);
                	
                	  
                  }
        	  }
        	  button1.setEnabled(false);
        	  button2.setEnabled(false);
        	  button3.setEnabled(false);
        	  
        	  giveup=1;
        	
          }
        return false;
    }
   
    public static Button Switcher(Button buttons) {
        if (buttons.getBackground().equals(Color.black)){
            buttons.setBackground(Color.yellow);
        }
        else {
            buttons.setBackground(Color.black);

        }
        return buttons;
    
    
    }
    public void Checker(Button buttons[][]) {
    	int count1=0; 
    	for (int i = 0 ; i < 5 ; i++){
             for (int j = 0 ; j < 5 ; j++){
            	 if(buttons[i][j].getBackground().equals(Color.black)) {
            		 count1++;
            	 }
             }
             
    	}
    	if (count1==25&&num1!=2&&num1!=4&&num1!=5) { x=1;
    		repaint();
    		}
    	if (clickcount==5) {y=1;
    		ButtonCreate();
    		repaint();
    	}
    	
    }
    
    public void ButtonCreate() {
    	
    	buttonExit.setBounds(20, 180, 80, 60);
    	buttonExit.setBackground(Color.gray);
         add(buttonExit);
         buttonExit.setVisible(true);
         buttonExit.setLabel("Give Up");
    }
    
      
}