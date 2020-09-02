/**
 * {@link GlobalExceptionHandler}
 * 
 */
package com.sales.order.exception.handler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sales.order.framework.base.BaseBO;
import com.sales.order.framework.base.BaseResponse;

/**
 * Global Exception Handler
 */
@ControllerAdvice
@Component
public class GlobalExceptionHandler<T> extends ResponseEntityExceptionHandler {

   private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
   //
   private static final String TYPE_MISMATCH = "Type Mismatch";
   private static final String METHOD_ARGUMENT_MISMATCH = "Method Argument Mismatch";
   private static final String MISSING_SERVLET_REQUEST_PART = "Missing Servlet Request Part";
   private static final String MISSING_SERVLET_REQUEST_PARAM = "Missing Servlet Request Param";
   private static final String NO_HANDLER_FOUND = "No Handler Found";
   private static final String HTTP_REQUEST_METHOD_NOT_SUPPORTED = "Http Request Method Not Supported";
   private static final String HTTP_MEDIA_TYPE_NOT_SUPPORTED = "Http Media Type Not Supported";
   //
   private static final String CUSTOM_EXCEPTION_OCCURRED = "Custom Exception Occurred, Unable to process the request";
   private static final String FRAMEWORK_EXCEPTION_ROOT = "com.sales.order.exception.handler.CustomException";
   
   /**
    * Handle Method Argument Not valid
    * 
    * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleMethodArgumentNotValid(org.springframework.web.bind.MethodArgumentNotValidException,
    *      org.springframework.http.HttpHeaders,
    *      org.springframework.http.HttpStatus,
    *      org.springframework.web.context.request.WebRequest)
    */
   @SuppressWarnings("unchecked")
   @Override
   protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
         final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
      LOGGER.info("Framework Global Exception Handler - Method Argument Not Valid {}");
      BaseResponse<T> response = null;
      if (!CollectionUtils.isEmpty(ex.getBindingResult().getFieldErrors())
            || !CollectionUtils.isEmpty(ex.getBindingResult().getGlobalErrors())) {
         response = new BaseResponse<>();
         exceptionPropertyUpdate(response);
         final List<BaseBO> errors = new ArrayList<>();
         errors.addAll(bindFieldErrors(ex.getBindingResult()));
         errors.addAll(bindGlobalErrors(ex.getBindingResult()));
         response.setData((T) errors);
      }
      return new ResponseEntity<>(response, headers, HttpStatus.OK);
   }

   /**
    * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleBindException(org.springframework.validation.BindException,
    *      org.springframework.http.HttpHeaders,
    *      org.springframework.http.HttpStatus,
    *      org.springframework.web.context.request.WebRequest)
    */
   @SuppressWarnings("unchecked")
   @Override
   protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers,
         final HttpStatus status, final WebRequest request) {
      LOGGER.info("Framework Global Exception Handler - Handle Bind Exception {}");
      BaseResponse<T> response = null;
      if (!CollectionUtils.isEmpty(ex.getBindingResult().getFieldErrors())
            || !CollectionUtils.isEmpty(ex.getBindingResult().getGlobalErrors())) {
         response = new BaseResponse<>();
         exceptionPropertyUpdate(response);
         final List<BaseBO> errors = new ArrayList<>();
         errors.addAll(bindFieldErrors(ex.getBindingResult()));
         errors.addAll(bindGlobalErrors(ex.getBindingResult()));
         response.setData((T) errors);
      }
      return handleExceptionInternal(ex, response, headers, HttpStatus.BAD_REQUEST, request);
   }

   /**
    * Handle Type Mismatch
    * 
    * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleTypeMismatch(org.springframework.beans.TypeMismatchException,
    *      org.springframework.http.HttpHeaders,
    *      org.springframework.http.HttpStatus,
    *      org.springframework.web.context.request.WebRequest)
    */
   @SuppressWarnings("unchecked")
   @Override
   protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers,
         final HttpStatus status, final WebRequest request) {
      LOGGER.info("Framework Global Exception Handler - Handle Type Mismatch {}");
      BaseResponse<T> response = new BaseResponse<>();
      exceptionPropertyUpdate(response);
      String error = String.format("%s value for %s should be of type %s", ex.getValue(), ex.getPropertyName(),
            ex.getRequiredType());
      final List<BaseBO> errors = new ArrayList<>();
      errors.add(copyExceptionDetails(null, TYPE_MISMATCH, error));
      response.setData((T) errors);
      return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
   }

   /**
    * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleMissingServletRequestPart(org.springframework.web.multipart.support.MissingServletRequestPartException,
    *      org.springframework.http.HttpHeaders,
    *      org.springframework.http.HttpStatus,
    *      org.springframework.web.context.request.WebRequest)
    */
   @SuppressWarnings("unchecked")
   @Override
   protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex,
         final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
      LOGGER.info("Framework Global Exception Handler - Handle Missing Servlet Request Part {}");
      BaseResponse<T> response = new BaseResponse<>();
      exceptionPropertyUpdate(response);
      String error = String.format("%s part is missing", ex.getRequestPartName());
      final List<BaseBO> errors = new ArrayList<>();
      errors.add(copyExceptionDetails(null, MISSING_SERVLET_REQUEST_PART, error));
      response.setData((T) errors);
      return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
   }

   /**
    * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleMissingServletRequestParameter(org.springframework.web.bind.MissingServletRequestParameterException,
    *      org.springframework.http.HttpHeaders,
    *      org.springframework.http.HttpStatus,
    *      org.springframework.web.context.request.WebRequest)
    */
   @SuppressWarnings("unchecked")
   @Override
   protected ResponseEntity<Object> handleMissingServletRequestParameter(
         final MissingServletRequestParameterException ex, final HttpHeaders headers, final HttpStatus status,
         final WebRequest request) {
      LOGGER.info("Framework Global Exception Handler - Handle Missing Servlet Request Parameter {}");
      BaseResponse<T> response = new BaseResponse<>();
      exceptionPropertyUpdate(response);
      String error = String.format("%s parameter is missing", ex.getParameterName());
      final List<BaseBO> errors = new ArrayList<>();
      errors.add(copyExceptionDetails(null, MISSING_SERVLET_REQUEST_PARAM, error));
      response.setData((T) errors);
      return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
   }
   
   /**
    * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleNoHandlerFoundException(org.springframework.web.servlet.NoHandlerFoundException,
    *      org.springframework.http.HttpHeaders,
    *      org.springframework.http.HttpStatus,
    *      org.springframework.web.context.request.WebRequest)
    */
   @SuppressWarnings("unchecked")
   @Override
   protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex,
         final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
      LOGGER.info("Framework Global Exception Handler - No Handler Found Exception {}");
      BaseResponse<T> response = new BaseResponse<>();
      exceptionPropertyUpdate(response);
      String error = String.format("No handle found for %s %s", ex.getHttpMethod(), ex.getRequestURL());
      final List<BaseBO> errors = new ArrayList<>();
      errors.add(copyExceptionDetails(null, NO_HANDLER_FOUND, error));
      response.setData((T) errors);
      return new ResponseEntity<>(response, headers, HttpStatus.NOT_FOUND);
   }

   /**
    * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleHttpRequestMethodNotSupported(org.springframework.web.HttpRequestMethodNotSupportedException,
    *      org.springframework.http.HttpHeaders,
    *      org.springframework.http.HttpStatus,
    *      org.springframework.web.context.request.WebRequest)
    */
   @SuppressWarnings("unchecked")
   @Override
   protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException ex,
         final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
      LOGGER.info("Framework Global Exception Handler - Http Request Method Not Supported {}");
      BaseResponse<T> response = new BaseResponse<>();
      exceptionPropertyUpdate(response);
      String error = String.format("%s method is not supported for this request", ex.getMethod());
      final List<BaseBO> errors = new ArrayList<>();
      errors.add(copyExceptionDetails(null, HTTP_REQUEST_METHOD_NOT_SUPPORTED, error));
      response.setData((T) errors);
      return new ResponseEntity<>(response, headers, HttpStatus.METHOD_NOT_ALLOWED);
   }

   /**
    * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleHttpMediaTypeNotSupported(org.springframework.web.HttpMediaTypeNotSupportedException,
    *      org.springframework.http.HttpHeaders,
    *      org.springframework.http.HttpStatus,
    *      org.springframework.web.context.request.WebRequest)
    */
   @SuppressWarnings("unchecked")
   @Override
   protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex,
         final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
      LOGGER.info("Framework Global Exception Handler - Http Media Type Not Supported {}");
      BaseResponse<T> response = new BaseResponse<>();
      exceptionPropertyUpdate(response);
      String error = String.format("%s media type is not supported", ex.getContentType());
      final List<BaseBO> errors = new ArrayList<>();
      errors.add(copyExceptionDetails(null, HTTP_MEDIA_TYPE_NOT_SUPPORTED, error));
      response.setData((T) errors);
      return new ResponseEntity<>(response, headers, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
   }

   /**
    * Handle Method Argument Type Mismatch
    * 
    * @param ex
    * @param request
    * @return
    */
   @SuppressWarnings("unchecked")
   @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
   public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex,
         final WebRequest request) {
      LOGGER.info("Framework Global Exception Handler - Handle Method Argument Type Mismatch {}");
      BaseResponse<T> response = new BaseResponse<>();
      exceptionPropertyUpdate(response);
      String error = String.format("%s should be of type %s", ex.getRequiredType().getName());
      final List<BaseBO> errors = new ArrayList<>();
      errors.add(copyExceptionDetails(null, METHOD_ARGUMENT_MISMATCH, error));
      response.setData((T) errors);
      return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
   }

   /**
    * Handle Constraint Violation
    * 
    * @param ex
    * @param request
    * @return
    */
   @SuppressWarnings("unchecked")
   @ExceptionHandler({ ConstraintViolationException.class })
   public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex,
         final WebRequest request) {
      LOGGER.info("Framework Global Exception Handler - Handle Constraint Violation {}");
      BaseResponse<T> response = null;
      if (Objects.nonNull(ex) && !CollectionUtils.isEmpty(ex.getConstraintViolations())) {
         response = new BaseResponse<>();
         exceptionPropertyUpdate(response);
         final List<BaseBO> errors = new ArrayList<>();
         errors.addAll(bindConstraintViolations(ex));
         response.setData((T) errors);
      }
      return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
   }

   /**
    * @param ex
    * @param request
    * @return
    */
   @ExceptionHandler({ Exception.class })
   public ResponseEntity<Object> handleAnyException(final Exception ex, final WebRequest request) {
      LOGGER.info("Framework Global Exception Handler - Handle Any Exception {}");
      BaseResponse<T> response = new BaseResponse<>();
      exceptionPropertyUpdate(response);
      final List<BaseBO> errors = new ArrayList<>();
      if ((ex instanceof CustomException) || (ex instanceof RuntimeException && Objects.nonNull(ex.getMessage())
            && ex.getMessage().contains(FRAMEWORK_EXCEPTION_ROOT))) {
         generateCustomException(ex, errors);
      } else {
         BaseBO baseBO = copyExceptionDetails(ex.getClass().getName(), ex.getMessage(), getStackTrace(ex));
         errors.add(baseBO);
      }
      return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
   }

   /**
    * Exception case Base Response property update
    * 
    * @param response
    */
   private void exceptionPropertyUpdate(BaseResponse<T> response) {
      response.setExceptionFlag(true);
      response.setSuccess(false);
   }
   
   /**
    * Bind Exception field errors
    * 
    * @param bindingResult
    * @return
    */
   private Collection<? extends BaseBO> bindFieldErrors(BindingResult bindingResult) {
      List<BaseBO> bindErrors = new ArrayList<>();
      for (final FieldError error : bindingResult.getFieldErrors()) {
         BaseBO baseBO = copyExceptionDetails(error.getField(), error.getCode(), error.getDefaultMessage());
         bindErrors.add(baseBO);
      }
      return bindErrors;
   }
   
   /**
    * Bind Exception global errors
    * 
    * @param bindingResult
    * @return
    */
   private Collection<? extends BaseBO> bindGlobalErrors(BindingResult bindingResult) {
      List<BaseBO> gloablErrors = new ArrayList<>();
      for (final ObjectError error : bindingResult.getGlobalErrors()) {
         BaseBO baseBO = copyExceptionDetails(error.getObjectName(), error.getCode(), error.getDefaultMessage());
         gloablErrors.add(baseBO);
      }
      return gloablErrors;
   }
   
   /**
    * Bind Constraint Violations
    * 
    * @param ex
    * @return
    */
   private Collection<? extends BaseBO> bindConstraintViolations(ConstraintViolationException ex) {
      List<BaseBO> violationErrors = new ArrayList<>();
      for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
         String error = String.format("%s.%s", violation.getRootBeanClass().getName(), violation.getPropertyPath());
         BaseBO baseBO = copyExceptionDetails(null, error, violation.getMessage());
         violationErrors.add(baseBO);
      }
      return violationErrors;
   }
   
   /**
    * Copy Exception details
    * 
    * @param errorType
    * @param errorCode
    * @param errorMessage
    * @return
    */
   private BaseBO copyExceptionDetails(String errorType, String errorCode, String errorMessage) {
      BaseBO baseBO = new BaseBO();
      baseBO.setErrorType(errorType);
      baseBO.setErrorCode(errorCode);
      baseBO.setErrorMessage(errorMessage);
      return baseBO;
   }
   
   /**
    * Generate Custom Exception
    * 
    * @param ex
    * @param errors
    */
   private void generateCustomException(Exception ex, List<BaseBO> errors) {
      CustomException customException = null;
      BaseBO baseBO = null;
      //
      if (ex instanceof CustomException) {
         customException = (CustomException) ex;
      } else if (ex instanceof RuntimeException) {
         customException = (CustomException) ex.getCause();
      }
      if (Objects.isNull(customException)) {
         baseBO = copyExceptionDetails(ex.getClass().getName(), ex.getMessage(), CUSTOM_EXCEPTION_OCCURRED);
      }
      if (Objects.nonNull(baseBO)) {
         errors.add(baseBO);
      }
   }
   
   /**
    * Gets the stack trace from a Throwable as a String
    * 
    * @param throwable
    * @return
    */
   public static String getStackTrace(Throwable throwable) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw, true);
      throwable.printStackTrace(pw);
      if (Objects.nonNull(sw) && Objects.nonNull(sw.getBuffer())) {
         return sw.getBuffer().toString();
      }
      return null;
   }
   
}
