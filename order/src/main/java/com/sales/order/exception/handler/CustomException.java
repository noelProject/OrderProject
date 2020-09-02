/**
 * {@link CustomException}
 */
package com.sales.order.exception.handler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import lombok.Getter;
import lombok.Setter;

/**
 * Custom Exception Handler instance
 */
@Getter
@Setter
public class CustomException extends Exception {
   // Serial Version UID
   private static final long serialVersionUID = -8374250688876811087L;
   //
   public static final String DEFAULT_EXCEPTION = "SYSTEM EXCEPTION";
   //
   private String errorType;
   private String errorCode;
   private String errorMessage;

   /**
    * No Args Constructor
    */
   public CustomException() {
      super();
      this.errorCode = DEFAULT_EXCEPTION;
   }
   
   /**
    * @param errorType
    *           Business Error / System Error / Runtime Error
    * @param errorCode
    *           Specific Business Implementation Error code
    * @param errorMessage
    *           Specific error message for user interface
    */
   public CustomException(String errorType, String errorCode, String errorMessage) {
      this.errorType = errorType;
      this.errorCode = errorCode;
      this.errorMessage = errorMessage;
   }
   
   /**
    * @param inStream
    * @throws IOException
    * @throws ClassNotFoundException 
    */
   private void readObject(ObjectInputStream inStream) throws IOException, ClassNotFoundException {
      this.errorType = (String) inStream.readObject();
      this.errorCode = (String) inStream.readObject();
      this.errorMessage = (String) inStream.readObject();
   }
   
   /**
    * @param outStream
    * @throws IOException
    */
   private void writeObject(ObjectOutputStream outStream) throws IOException {
      outStream.writeObject(this.errorType);
      outStream.writeObject(this.errorCode);
      outStream.writeObject(this.errorMessage);
   }
   
}
