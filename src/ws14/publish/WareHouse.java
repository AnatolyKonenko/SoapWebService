/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws14.publish;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 *
 * @author Linea
 */
@WebService(serviceName = "WarehouseWS",portName = "8078")
public class WareHouse {

    public WareHouse() {

    }

    @WebMethod(operationName = "ReportbyID")
    @WebResult(name = "report")
    public List<ReportInfo> getInfoByOrderID(
            @WebParam(name = "orderID") String orderID) {
        String query = "Select * from Reports where orderID=" + orderID;
        return makeSelect(query);
    }

    @WebMethod(operationName = "GetAll")
    @WebResult(name = "report")
    public List<ReportInfo> getInfoAll() {
        String query = "Select * from Reports;";
        return makeSelect(query);
    }

    @WebMethod(operationName = "GetReportByDepartmentName")
    @WebResult(name = "report")
    public List<ReportInfo> getInfoByDepartment(
            @WebParam(name = "department") String department) {
        String query = "Select * from Reports where department=" + department + ";";
        return makeSelect(query);
    }

    @WebMethod(operationName = "SaveInformation")
    @WebResult(name = "writing")
    public boolean saveInfo(
            @WebParam(name = "department_name") String department,
            @WebParam(name = "orderID") String orderID,
            @WebParam(name = "message") String message) {
        System.out.println(department + " " + orderID + " " + message);
        return writeToSQLite(department, orderID, message);
    }

    private boolean writeToSQLite(String depName, String orderID, String message) {
        Connection connection = null;
        Statement statement = null;
        Date dt = new Date();
        String date = dt.toString();
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:D:\\work\\SOA\\data.db");
            statement = (Statement) connection.createStatement();
            String ins = "Insert into 'Reports' (date,department,orderID,message) values ('"
                    + date + "','" + depName + "','" + orderID + "','" + message + "');";
            //System.out.println(ins);
            statement.executeUpdate(ins);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                statement.close();
                connection.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    private List<ReportInfo> makeSelect(String sqlQuery) {
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        List<ReportInfo> result = new ArrayList<>();
        ReportInfo node = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:D:\\work\\SOA\\data.db");
            statement = connection.createStatement();
            resultSet = statement
                    .executeQuery(sqlQuery);
            while (resultSet.next()) {
                node = new ReportInfo(resultSet.getString("date"), resultSet.getString("department"),
                        resultSet.getString("orderID"), resultSet.getString("message"));
                result.add(node);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
