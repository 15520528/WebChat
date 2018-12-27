
import Connections.ConnectionUtils;
import beans.UserAccount;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import servletHanler.LoginServlet;
import utils.UserDb;
import utils.ConversationDb;
import beans.Conversation;
import servletHanler.LoadFriend;
import javax.json.Json;
import javax.json.stream.JsonGenerator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lap11105-local
 */
public class t {

    public static void main(String[] args) throws SQLException,
            ClassNotFoundException {
        LoadConversation();
    }

    public static void loadFriend()throws SQLException, ClassNotFoundException {
        List<UserAccount> friendList = new ArrayList<>();
        Connection conn = null;

        conn = ConnectionUtils.getMyConnection();
        System.out.println("Get connection " + conn);
        System.out.println("Done!");

        friendList = UserDb.loadFriend(conn, "1");
        for (UserAccount userAccount : friendList) {
            System.out.println(userAccount.getId() + "  " + userAccount.getFulName());
        }
    }
    
    public static void LoadConversation() throws SQLException, ClassNotFoundException {
       
        List<Conversation> conversationsList = new ArrayList<>();
        Connection conn = null;

        conn = ConnectionUtils.getMyConnection();
        System.out.println("Get connection " + conn);
        System.out.println("Done!");

        conversationsList = ConversationDb.loadConvesations(conn, "1");
        for (Conversation c : conversationsList) {
            System.out.println(c.getId() + "  " + c.getTitle());
        }
    }
}
