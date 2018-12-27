/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import beans.UserAccount;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lap11105-local
 */
public class UserDb {

    public static UserAccount findUser(Connection conn, //
            String userName, String password) throws SQLException {
        
        String sql = "Select a.user_id, a.username, a.password, a.gender from Users a " //
                + " where a.username = ? and a.password= ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            String id = rs.getString("user_id");
            String gender = rs.getString("gender");
            UserAccount user = new UserAccount();
            user.setId(id);
            user.setUserName(userName);
            user.setPassword(password);
            user.setGender(gender);
            return user;
        }
        return null;
    }

    public static List<UserAccount> loadFriend(Connection conn, //
            String user_id) throws SQLException {
        
        List<UserAccount> contacList = new ArrayList<>();

        String sql = "select b.user_id, b.full_name, b.is_oneline "
                + "from FriendRelationship a, Users b \n"
                + "where a.user_id1 = ? and a.user_id2 = b.user_id"; //

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, user_id);
        ResultSet rs = pstm.executeQuery();

        while(rs.next()) {
            String id = rs.getString("user_id");
            String fullName = rs.getString("full_name");
            String is_online = rs.getString("is_oneline");
            
            UserAccount user = new UserAccount();
            user.setFullName(fullName);
            user.setId(id);

            user.setStatus(is_online);

            contacList.add(user);
        }
        return contacList;
    }
}
