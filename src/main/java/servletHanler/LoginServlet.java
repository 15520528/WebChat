/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletHanler;

import Connections.ConnectionUtils;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Connections.MySQLConnUtils;
import beans.UserAccount;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import utils.MyUtils;


@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //System.out.println("123");
        RequestDispatcher dispatcher //
                        = this.getServletContext().
                                getRequestDispatcher("/WEB-INF/pages/chatDasboard.jsp");
 
                    dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserAccount user = null;
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(userName + "  " + password);

        // Lấy ra đối tượng Connection kết nối vào database.
        Connection conn = null;
        try {
            conn = ConnectionUtils.getMyConnection();
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // Tìm user trong DB.
            user = utils.UserDb.findUser(conn, userName, password);
            if (user == null) {
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    //out.println("username or password errors");
                    System.out.println("Error user");
                }
            }
            else{
                try (PrintWriter out = response.getWriter()) {
                    //store user into session
                    HttpSession session = request.getSession();
                    MyUtils.storeLoginedUser(session, user);
                    System.out.println("login successfuly" + user.getId());
                    
                    request.setAttribute("userid", user.getId());
                    user = new UserAccount();
                    user.setUserName(userName);
                    user.setPassword(password);
                    // Lưu các thông tin vào request attribute trước khi forward.
                    request.setAttribute("user", user);
                    
                    
                    RequestDispatcher dispatcher 
                        = this.getServletContext().
                                getRequestDispatcher("/WEB-INF/pages/chatDasboard.jsp");
 
                    dispatcher.forward(request, response);
                    
   
                }
            }
            //processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        //response.setContentType("text/html;charset=UTF-8");
    }

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
