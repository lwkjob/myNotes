package com.lwk.serializable;  
   
/** 
 * Simple class storing city and state names that is NOT Serializable. 
 *  
 * @author Dustin 
 */ 
public class CityState  
{  
   private final String cityName;  
   private final String stateName;  
   
   public CityState(final String newCityName, final String newStateName)  
   {  
      this.cityName = newCityName;  
      this.stateName = newStateName;  
   }  
   
   public String getCityName()  
   {  
      return this.cityName;  
   }  
   
   public String getStateName()  
   {  
      return this.stateName;  
   }  
   
   @Override 
   public String toString()  
   {  
      return this.cityName + ", " + this.stateName;  
   }  
} 