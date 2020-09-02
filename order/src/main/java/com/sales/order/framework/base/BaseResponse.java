/**
 * {@link BaseResponse}
 * 
 */
package com.sales.order.framework.base;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Base Response of Response Entity instance
 */
@Getter
@Setter
@NoArgsConstructor
public class BaseResponse<T> implements Serializable {
   // Generated Serial VersionUID
   private static final long serialVersionUID = 8556149860207989057L;
   // Business data
   private T data;
   // Request
   private boolean isSuccess = true;
   // Exception details
   private boolean exceptionFlag = false;
   
   /**
    * @param inStream
    * @throws IOException
    * @throws ClassNotFoundException 
    */
   @SuppressWarnings("unchecked")
   private void readObject(ObjectInputStream inStream) throws IOException, ClassNotFoundException {
      this.data = (T) inStream.readObject();
      this.isSuccess = (boolean) inStream.readObject();
      this.exceptionFlag = (boolean) inStream.readObject();
   }
   
   /**
    * @param outStream
    * @throws IOException
    */
   private void writeObject(ObjectOutputStream outStream) throws IOException {
      outStream.writeObject(this.data);
      outStream.writeObject(this.isSuccess);
      outStream.writeObject(this.exceptionFlag);
   }
   
}
