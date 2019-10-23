/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.mamangparking.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Wilhelmus Krisvan
 */
public abstract class DBConnect {
    Connection con;
    PreparedStatement ps;
    Statement st;
    ResultSet rs,sr;
    
    public abstract void connect();
}
