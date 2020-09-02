/**
 * {@link BaseBO}
 */
package com.sales.order.framework.base;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * BaseBO
 */
@Getter
@Setter
@NoArgsConstructor
public class BaseBO implements Serializable {
   // Serial Version UID
   private static final long serialVersionUID = 8204557508278826934L;
   //
   private String flagCRUD;
   private String locale;
   private String dateFormat;
   private String createdBy;
   private LocalDateTime createdTime;
   private String modifiedBy;
   private LocalDateTime modifiedTime;
   //
   private String errorType;
   private String errorCode;
   private String errorMessage;
   
}
