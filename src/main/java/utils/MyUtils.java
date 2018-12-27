/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import beans.UserAccount;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lap11105-local
 */
public class MyUtils {
    // Lưu trữ thông tin người dùng đã login vào Session.
    public static void storeLoginedUser(HttpSession session, UserAccount loginedUser) {
        // Trên JSP có thể truy cập thông qua ${loginedUser}
        session.setAttribute("loginedUser", loginedUser);
    }
 
    // Lấy thông tin người dùng lưu trữ trong Session.
    public static UserAccount getLoginedUser(HttpSession session) {
        UserAccount loginedUser = (UserAccount) session.getAttribute("loginedUser");
        return loginedUser;
    }
}
