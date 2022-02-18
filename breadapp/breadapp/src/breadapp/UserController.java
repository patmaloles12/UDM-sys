/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breadapp;


import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import java.util.Date;
import java.time.LocalDate;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class UserController implements Initializable {
    Connection con;
    Statement st;
    ResultSet rs;
    int userID;
    String bdate="", gender="";
    boolean selected = false;

    
    @FXML 
    private final ToggleGroup group1 = new ToggleGroup();
    
    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private DatePicker dob;

    @FXML
    private RadioButton rbMale;

    @FXML
    private RadioButton rbFemale;

    @FXML
    private Button btn_add;
        
    @FXML
    private Button btn_update;
    
    @FXML
    private Button btn_delete;

    @FXML
    private TableView<ModelTable> userTable;

    @FXML
    private TableColumn<ModelTable, String> colUserId;

    @FXML
    private TableColumn<ModelTable, String> colFirstName;

    @FXML
    private TableColumn<ModelTable, String> colLastName;

    @FXML
    private TableColumn<ModelTable, String> colDOB;

    @FXML
    private TableColumn<ModelTable, String> colGender;

    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
    
    public void loadTable(){
        try {
            ConnectionDb.connectDB();
            con = ConnectionDb.con;
            String select = "SELECT * FROM users";
            st = con.createStatement();
            rs = st.executeQuery(select);
            while(rs.next()){
                oblist.add(new ModelTable(rs.getString("ID"), rs.getString("fname"), rs.getString("lastname"), rs.getString("bdate") , rs.getString("gender")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        colUserId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("fname"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lname"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("bdate"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        
        userTable.setItems(oblist); 
    }
    
    
    @FXML
    void onClickFemale(ActionEvent event) {
        rbFemale.setToggleGroup(group1);
        rbMale.setToggleGroup(group1);
        gender = "FEMALE";
        System.out.println(gender);
    }

    @FXML
    void onClickMale(ActionEvent event) {
        rbFemale.setToggleGroup(group1);
        rbMale.setToggleGroup(group1);
        gender = "MALE";
        System.out.println(gender);
    }
     @FXML
    void onClickDelete(ActionEvent event) {
        String getID = userTable.getSelectionModel().getSelectedItem().getId();
        userID = Integer.parseInt(getID);
        int delete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this User?", "Delete?",JOptionPane.YES_NO_OPTION);
        if(delete == 0){
            try {
                ConnectionDb.connectDB();
                con = ConnectionDb.con;
                String delete1 = "DELETE FROM `users` WHERE ID='"+userID+"'";
                Statement st = con.createStatement();
                st.execute(delete1);
                JOptionPane.showMessageDialog(null, "Account Deleted!");
                userTable.getItems().clear();
                loadTable();
            } catch (Exception e) {
                System.out.println(e);
            }
        }else{
            
        }
            
                
    }
    @FXML
    void onClickAdd(ActionEvent event) {
        if (btn_add.getText().equals("Add")){
            ConnectionDb.connectDB();
            System.out.println("sdasdad");
            String fname = firstName.getText();
            String lname = lastName.getText();
            if(fname.equals("") && lname.equals("") && bdate.equals("") && gender.equals("")){
                JOptionPane.showMessageDialog(null,"All fields are required!","Empty Fields Detected!", 2);
            }else if(fname.equals("")){
                JOptionPane.showMessageDialog(null,"First Name is Required!", "Empty Fields Detected!", 2);
            }else if(lname.equals("")){
                JOptionPane.showMessageDialog(null,"Last Name is Required!", "Empty Fields Detected!", 2);
            }else if(bdate.equals("")){
                JOptionPane.showMessageDialog(null,"Birth Date is required!", "Empty Fields Detected!", 2);
            }else if(gender.equals("")){
                JOptionPane.showMessageDialog(null,"Gender is required!", "Empty Fields Detected!", 2);
            }else{
                try {
                    String insert = "INSERT INTO `users`( `fname`, `lastname`, `bdate`, `gender`) "
                            + "VALUES ('"+fname+"','"+lname+"','"+bdate+"','"+gender+"')";
                    con = ConnectionDb.con;
                    st = con.createStatement();
                    st.executeUpdate(insert);
                    JOptionPane.showMessageDialog(null,"User Succesfully Saved!", "Success!", 1);
                    userTable.getItems().clear();
                    loadTable();
                    firstName.setText("");
                    lastName.setText("");
                    bdate = "";
                    dob.setValue(null);

                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }else if(btn_add.getText().equals("Update")){
            ConnectionDb.connectDB();
            System.out.println("sdasdad");
            String fname = firstName.getText();
            String lname = lastName.getText();
            if(fname.equals("") && lname.equals("") && bdate.equals("") && gender.equals("")){
                JOptionPane.showMessageDialog(null,"All fields are required!","Empty Fields Detected!", 2);
            }else if(fname.equals("")){
                JOptionPane.showMessageDialog(null,"First Name is Required!", "Empty Fields Detected!", 2);
            }else if(lname.equals("")){
                JOptionPane.showMessageDialog(null,"Last Name is Required!", "Empty Fields Detected!", 2);
            }else if(bdate.equals("")){
                JOptionPane.showMessageDialog(null,"Birth Date is required!", "Empty Fields Detected!", 2);
            }else if(gender.equals("")){
                JOptionPane.showMessageDialog(null,"Gender is required!", "Empty Fields Detected!", 2);
            }else{
                try {
                    String update = "UPDATE `users` SET `fname`='"+fname+"',`lastname`='"+lname+"',`bdate`='"+bdate+"',`gender`='"+gender+"' WHERE ID ='"+userID+"'";
                    con = ConnectionDb.con;
                    st = con.createStatement();
                    st.executeUpdate(update);
                    JOptionPane.showMessageDialog(null,"User Data Updated!", "Success!", 1);
                    userTable.getItems().clear();
                    loadTable();
                    firstName.setText("");
                    lastName.setText("");
                    bdate = "";
                    dob.setValue(null);
                    btn_add.setText("Add");
                    btn_update.setText("Update");
                    btn_delete.setDisable(false);
                    firstName.setText("");
                    lastName.setText(null);
                    dob.setValue(null);
                    rbFemale.setSelected(false);
                    rbMale.setSelected(selected);

                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
    }

    @FXML
    void onClickUpdate(ActionEvent event) {
        if(userTable.getSelectionModel().getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Please Select a user to Update!","No user Selected",2);
        }else{
            if(btn_update.getText().equals("Update")){
                btn_add.setText("Update");
                btn_update.setText("Cancel");
                btn_delete.setDisable(true);
                
                String getID = userTable.getSelectionModel().getSelectedItem().getId();
                userID = Integer.parseInt(getID);
                firstName.setText(userTable.getSelectionModel().getSelectedItem().getFname());
                lastName.setText(userTable.getSelectionModel().getSelectedItem().getLname());
                String date = userTable.getSelectionModel().getSelectedItem().getBdate();
                LocalDate localDate = LocalDate.parse(date);
                dob.setValue(localDate);
                String gend = userTable.getSelectionModel().getSelectedItem().getGender();
                
                if(gend.equals("MALE")){
                    rbMale.setSelected(true);
                    gender = "MALE";
                }else{
                    rbFemale.setSelected(true);
                    gender = "FEMALE";
                }
            }else{
                btn_add.setText("Add");
                btn_update.setText("Update");
                btn_delete.setDisable(false);
                firstName.setText("");
                lastName.setText(null);
                dob.setValue(null);
                rbFemale.setSelected(false);
                rbMale.setSelected(selected);
            }  
        }
    }
    
    @FXML
    void getDate(ActionEvent event) {
        if(dob.getValue()==null){
            
        }else{
            LocalDate mydate=dob.getValue();
            bdate = mydate.toString();
        }
    }
    
    
    @FXML
    void onMouseClicked(MouseEvent event) {
       
           
    }
    
    
    public void initialize(URL url, ResourceBundle rb) {
        loadTable();
    
    }    
    
}
