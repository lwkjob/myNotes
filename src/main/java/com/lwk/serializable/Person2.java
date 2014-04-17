package com.lwk.serializable;  
   
import java.io.Serializable;  
   
/** 
 * Person class. 
 *  
 * @author Dustin 
 */ 
public class Person2 implements Serializable  
{  
   private final String lastName;  
   private final String firstName;  
   private final SerializableCityState cityAndState;  
   
   public Person2(  
      final String newLastName, final String newFirstName,  
      final SerializableCityState newCityAndState)  
   {  
      this.lastName = newLastName;  
      this.firstName = newFirstName;  
      this.cityAndState = newCityAndState;  
   }  
   
   public String getFirstName()  
   {  
      return this.firstName;  
   }  
   
   public String getLastName()  
   {  
      return this.lastName;  
   }  
   
   @Override 
   public String toString()  
   {  
      return this.firstName + " " + this.lastName + " of " + this.cityAndState;  
   }  
}  