/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import beans.Conversation;
import beans.UserAccount;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class ConversationDb {

    //load those conversations that user A attended.
    public static List<Conversation> loadConvesations(Connection conn, //
            String user_id) throws SQLException {
        
        List<Conversation> conversationList = new ArrayList<>();

        String sql = "select b.conversation_id, b.titile "
                + "from Participants a inner join Conversations b \n"
                + "on a.conversation_id = b.conversation_id where a.user_id = ?"; //

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, user_id);
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            String id = rs.getString("conversation_id");
            String title = rs.getString("titile");

            Conversation conversation  = new Conversation();
            conversation.setId(id);
            conversation.setTitile(title);
            conversationList.add(conversation);
        }
        return conversationList;
    }
}
