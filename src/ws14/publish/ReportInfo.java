/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws14.publish;



/**
 *
 * @author Linea
 */
public class ReportInfo {
    public String date;
    public String department;
    public String orderID;
    public String message;

    public ReportInfo() {
        this.date="daaaaa";
        this.department="dep";
        this.orderID="oid";
        this.message="msg";
    }

    public ReportInfo(String date, String department, String orderID, String message) {
        this.date = date;
        this.department = department;
        this.orderID = orderID;
        this.message = message;
    }
    
    
    
}
