/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author lap11105-local
 */
public class UserAccount {
 
   public static final String GENDER_MALE ="M";
   public static final String GENDER_FEMALE = "F";
    
   private String userName;
   private String gender;
   private String password;
   private String status; 
   private String fullName;
   private String Id;
   
   public UserAccount() {
        
   }
    
   public String getUserName() {
       return userName;
   }
 
   public void setUserName(String userName) {
       this.userName = userName;
   }
 
   public String getGender() {
       return gender;
   }
 
   public void setGender(String gender) {
       this.gender = gender;
   }
 
   public String getPassword() {
       return password;
   }
 
   public void setPassword(String password) {
       this.password = password;
   }
   
   public String getStatus() {
       return status;
   }
 
   public void setStatus(String Status) {
       this.status = status;
   }
   
   public String getFulName(){
       return fullName;
   }
   
   public void setFullName(String FullName) {
       this.fullName = FullName;
   }
   
   public String getId(){
       return Id;
   }
   
   public void setId(String Id) {
       this.Id = Id;
   }
}