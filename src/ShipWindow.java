/*
 * Program to organize a file and its contents in a GUI window using J components such as
 * JFrame, JPanel, and much more
 * Bishwash Khadka
 * 02/24/2023
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class ShipWindow extends JFrame{
    private final int win_wid = 650;
    private final int win_hei = 240;
    private ArrayList<Ship> tia_RosasFleet;
    private ArrayList<String> tia_RosasFleet_ShipName;
    JComboBox selectShipBox;
    JTextField center_SN_TF;
    JTextField center_CR_TF;
    JTextField center_YC_TF;
    JTextField center_LW_TF;
    JTextField center_DW_TF;
    JTextField center_BW_TF;
    
    public ShipWindow(){//Method to display the GUI elements
        this.setTitle("Shipping News                                  Bishwash Khadka");
        this.setSize(win_wid, win_hei);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeShips("Tia_RosasFleet.csv");
        initInfoPanel();
        initSelectPanel();
        initButtonPan();
        this.setVisible(true);
    }
    
    public void initializeShips(String fileName){//Method to Read the file and initialize the elements to array
    tia_RosasFleet = new ArrayList<>();
    tia_RosasFleet_ShipName = new ArrayList<>();
        File inFile = new File(fileName);
        if(!inFile.exists()){
            String errorMessage = """
                                  File missing!!! Possible error: 
                                  1.File not found in the location
                                  2.File name Mistake
                                  """;
            JOptionPane.showMessageDialog(null, errorMessage,"404 Error File Not Found",2);
            System.exit(0);
        }
        try{
            Scanner inScan = new Scanner(inFile).useDelimiter("[,\n]");
            while (inScan.hasNext()){//Loop to instantiate proper ship object to the list
                String name = inScan.next();
                String nation = inScan.next();
                int year = inScan.nextInt();
                int length = inScan.nextInt();
                int draft = inScan.nextInt();
                int beam = inScan.nextInt();
                tia_RosasFleet.add(new Ship(name, nation, year, length, draft, beam));
            }
        }
        catch(IOException ioe){
            String errorMessage = """
                                  File missing!!! Possible error: 
                                  1.File not found in the location
                                  2.File name Mistake
                                  """;
            JOptionPane.showMessageDialog(null, errorMessage,"404 Error File Not Found",2);
            System.exit(0);
        }
        for(int dex = 0; dex < tia_RosasFleet.size(); dex++){
            tia_RosasFleet_ShipName.add(tia_RosasFleet.get(dex).getName());
        }
    }
    
    private void initInfoPanel(){//Method for the Center Panel
        JLabel center_SN = new JLabel("Ship Name: ");
        JLabel center_CR = new JLabel("Country of Registration: ");
        JLabel center_YC = new JLabel("Year of Construction: ");
        JLabel center_LW = new JLabel("Length at waterline: ");
        JLabel center_DW = new JLabel("Draft at waterline: ");
        JLabel center_BW = new JLabel("Beam at waterline: ");
        
        center_SN.setHorizontalAlignment(JLabel.RIGHT);
        center_CR.setHorizontalAlignment(JLabel.RIGHT);
        center_YC.setHorizontalAlignment(JLabel.RIGHT);
        center_LW.setHorizontalAlignment(JLabel.RIGHT);
        center_DW.setHorizontalAlignment(JLabel.RIGHT);
        center_BW.setHorizontalAlignment(JLabel.RIGHT);
        
        center_SN_TF = new JTextField();
        center_CR_TF = new JTextField();
        center_YC_TF = new JTextField();
        center_LW_TF = new JTextField();
        center_DW_TF = new JTextField();
        center_BW_TF = new JTextField();
        
        center_SN_TF.setHorizontalAlignment(SwingConstants.CENTER);
        center_CR_TF.setHorizontalAlignment(SwingConstants.CENTER);
        center_YC_TF.setHorizontalAlignment(SwingConstants.CENTER);
        center_LW_TF.setHorizontalAlignment(SwingConstants.CENTER);
        center_DW_TF.setHorizontalAlignment(SwingConstants.CENTER);
        center_BW_TF.setHorizontalAlignment(SwingConstants.CENTER);
                
        JPanel centerPan = new JPanel(new GridLayout(3,4));
        centerPan.add(center_SN);
        centerPan.add(center_SN_TF);
        centerPan.add(center_CR);
        centerPan.add(center_CR_TF);
        centerPan.add(center_YC);
        centerPan.add(center_YC_TF);
        centerPan.add(center_LW);
        centerPan.add(center_LW_TF);
        centerPan.add(center_DW);
        centerPan.add(center_DW_TF);
        centerPan.add(center_BW);
        centerPan.add(center_BW_TF);
        this.add(centerPan, BorderLayout.CENTER);
    }
    
    private void initSelectPanel(){//Method for the North Panel
        int font_Size = 26;
        int scalex = 164, scaley = 82;
        Font bigFont = new Font("Arial", 1, font_Size);
        JLabel lab = new JLabel("Select a Ship");
        lab.setFont(bigFont);
        //Functionality of showing info when a ship in list is selected
        selectShipBox = new JComboBox();
        selectShipBox.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                updateShipInfoHelper(ie);
            }
        });
        selectShipBox.setModel(new javax.swing.DefaultComboBoxModel<>(tia_RosasFleet_ShipName.toArray(String[]::new)));
        //Ship Image in the North Panel
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("Yacht_big.png"));
        JLabel iconLab = new JLabel(){
            public void paintComponent(Graphics g){
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, scalex, scaley, this);
            }
        };
        iconLab.setPreferredSize(new Dimension(scalex, scaley));
        iconLab.setIcon(icon);
        
        JPanel northPan = new JPanel();
        northPan.add(iconLab);
        northPan.add(lab);
        northPan.add(selectShipBox);
        this.add(northPan, BorderLayout.NORTH);
    }
    
    public void updateShipInfoHelper(ItemEvent ie){//Helper Method for the List Info Dump
        String listSelect = (String)ie.getItem();
        for(int dex_1 = 0; dex_1 < tia_RosasFleet.size(); dex_1++){
            if (listSelect.equals(tia_RosasFleet.get(dex_1).getName())){
                center_SN_TF.setText(tia_RosasFleet.get(dex_1).getName());
                center_CR_TF.setText(tia_RosasFleet.get(dex_1).getNation());
                center_YC_TF.setText(Integer.toString(tia_RosasFleet.get(dex_1).getYearBuilt()));
                center_LW_TF.setText(Integer.toString(tia_RosasFleet.get(dex_1).getLength()));
                center_DW_TF.setText(Integer.toString(tia_RosasFleet.get(dex_1).getDraft()));
                center_BW_TF.setText(Integer.toString(tia_RosasFleet.get(dex_1).getBeam()));
            }
        }
    }
    
    public void dataEntryHelper(){//Helper Method for the Submit Button
        if (!center_SN_TF.getText().isEmpty() && !center_CR_TF.getText().isEmpty()//TO check if user entered the Right Data in TextFields
            && !center_YC_TF.getText().isEmpty() && center_YC_TF.getText().matches("\\d+")
            && !center_LW_TF.getText().isEmpty() && center_LW_TF.getText().matches("\\d+")
            && !center_DW_TF.getText().isEmpty() && center_DW_TF.getText().matches("\\d+")
                && !center_BW_TF.getText().isEmpty() && center_BW_TF.getText().matches("\\d+")) {
            
            String revisedText = "";
            int indexChange = -1;
            for(int dex_1 = 0; dex_1 < tia_RosasFleet.size(); dex_1++){//To check if the data entered exists already or is new
                if(center_SN_TF.getText().equals(tia_RosasFleet.get(dex_1).getName())){
                    indexChange = dex_1;
                }
            }
            if( indexChange!= -1){
                    tia_RosasFleet.get(indexChange).setName( center_SN_TF.getText());
                    tia_RosasFleet.get(indexChange).setNation( center_CR_TF.getText());
                    tia_RosasFleet.get(indexChange).setYearBuilt( Integer.parseInt(center_YC_TF.getText()));
                    tia_RosasFleet.get(indexChange).setLength(Integer.parseInt(center_LW_TF.getText()));
                    tia_RosasFleet.get(indexChange).setDraft( Integer.parseInt(center_DW_TF.getText()));
                    tia_RosasFleet.get(indexChange).setBeam( Integer.parseInt(center_BW_TF.getText()));
            }
            else{        
                tia_RosasFleet.add(new Ship(center_SN_TF.getText(), center_CR_TF.getText(), Integer.parseInt(center_YC_TF.getText()),
                                                Integer.parseInt(center_LW_TF.getText()), Integer.parseInt(center_DW_TF.getText()), 
                                                    Integer.parseInt(center_BW_TF.getText())));
            }    
            for(int dex_1 = 0; dex_1 < tia_RosasFleet.size(); dex_1++){
                revisedText += tia_RosasFleet.get(dex_1).toString() + "\n";
            }
            //Writing the Data in the file 
            File outFile = new File("Tia_RosasFleet.csv");
            if(outFile.exists() && !outFile.canWrite()){
                String err_mess = "Cannot write in the file";
                System.err.println(err_mess);
                System.exit(0);
            }
            try{
                FileWriter outWriter = new FileWriter(outFile);
                outWriter.write(revisedText);
                outWriter.close();
            }
            catch(IOException ioe){
                String err_mess = "Trouble Writting data";
                System.err.print(err_mess);
                System.exit(0);
            } 
            dispose();//Closing the JFrame Window 
            ShipWindow Reloader = new ShipWindow();//Reloading file in new JFrame to show the new list
        }
        else{
            JOptionPane.showMessageDialog(null, "Wrong Input \n Please Enter the Right Data in the Box", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void clearUpHelper(){//Helper Method for the Clear Button
        center_SN_TF.setText("");
        center_CR_TF.setText("");
        center_YC_TF.setText("");
        center_LW_TF.setText("");
        center_DW_TF.setText("");
        center_BW_TF.setText("");
    }
    
    public void initButtonPan(){//Method for the Button on South Panel
        JButton clear_but = new JButton("Clear");
        JButton submit_but = new JButton("Submit");
        JButton exit_but = new JButton("Exit");
        
        clear_but.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ie){
                clearUpHelper();
            }
        });
        submit_but.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ie){
                dataEntryHelper();
            }
        });
        exit_but.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ie){
                System.exit(0);
            }
        });
        JPanel southPan = new JPanel();
        southPan.add(clear_but);
        southPan.add(submit_but);
        southPan.add(exit_but);
        this.add(southPan, BorderLayout.SOUTH);
    }
    
    public static void main(String[] args) {//Main Method
        ShipWindow mainGUI = new ShipWindow();
    }
}
