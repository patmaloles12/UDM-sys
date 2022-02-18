/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breadapp;

import java.sql.*;
import javax.swing.JOptionPane;
public class ConnectionDb {
    public static Connection con;
    public static Statement st;
    public static void connectDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/breadapp","root","");
            st= con.createStatement();
            
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        
    }
    
}
