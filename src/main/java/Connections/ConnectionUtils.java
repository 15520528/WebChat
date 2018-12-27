/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connections;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author lap11105-local
 */
public class ConnectionUtils {
    public static Connection getMyConnection() throws SQLException,
            ClassNotFoundException {
        // Sử dụng Oracle.
        // Bạn có thể thay thế bởi Database nào đó.
        return MySQLConnUtils.getMySQLConnection();
    }  
}
