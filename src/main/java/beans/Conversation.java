/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
/**
 *
 * @author lap11105-local
 */
public class Conversation {
   private ArrayList<UserAccount> participants ;
   private String count; 
   private String title;
   private String Id;
   
   public Conversation() {
        
   }
   
//   public String count() {
//       return count;
//   }
//   
//   public void count(String count) {
//       this.count = count;
//   }
   
   public String count() {
       return count;
   }
   
   public void count(String count) {
       this.count = count;
   }
   
   public String getId() {
       return Id;
   }
   
   public void setId(String Id) {
       this.Id = Id;
   }
   
   public String getTitle() {
       return title;
   }
   
   public void setTitile(String title) {
       this.title = title;
   }
}
