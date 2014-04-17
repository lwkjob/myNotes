package com.lwk.serializable;  
   
import java.io.IOException;  
import java.io.InvalidObjectException;  
import java.io.ObjectInputStream;  
import java.io.ObjectOutputStream;  
import java.io.ObjectStreamException;  
import java.io.Serializable;  
   
/** 
 * Simple class storing city and state names that IS Serializable. This class 
 * decorates the non-Serializable CityState class and adds Serializability. 
 *  
 * @author Dustin 
 */ 
public class SerializableCityState implements Serializable  
{  
   private CityState cityState;  
   
   public SerializableCityState(final String newCityName, final String newStateName)  
   {  
      this.cityState = new CityState(newCityName, newStateName);  
   }  
   
   public String getCityName()  
   {  
      return this.cityState.getCityName();  
   }  
   
   public String getStateName()  
   {  
      return this.cityState.getStateName();  
   }  
   
   @Override 
   public String toString()  
   {  
      return this.cityState.toString();  
   }  
   
   /** 
    * Serialize this instance. 
    *  
    * @param out Target to which this instance is written. 
    * @throws IOException Thrown if exception occurs during serialization. 
    */ 
   private void writeObject(final ObjectOutputStream out) throws IOException  
   {  
      out.writeUTF(this.cityState.getCityName());  
      out.writeUTF(this.cityState.getStateName());  
   }  
    
   /** 
    * Deserialize this instance from input stream. 
    *  
    * @param in Input Stream from which this instance is to be deserialized. 
    * @throws IOException Thrown if error occurs in deserialization. 
    * @throws ClassNotFoundException Thrown if expected class is not found. 
    */ 
   private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException  
   {  
      this.cityState = new CityState(in.readUTF(), in.readUTF());  
   }  
   
   private void readObjectNoData() throws ObjectStreamException  
   {  
      throw new InvalidObjectException("Stream data required");  
   }  
}  