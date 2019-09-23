package com.cy.pj.common.web;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cy.pj.common.vo.JsonResult;
/**
 * 当控制层对象出现异常后：
 * 1)检测控制层对象内部有没有@ExceptionHandler描述异常处理方法
 * 2)检测Spring容器中是否有对象使用了@ControllerAdvice注解修饰
 * 假如有，则使用类中的@ExceptionHandler描述的异常处理方法，对
 * 特定异常进行处理。
 * @author Administrator
 */
//@ResponseBody
//@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {
	   /**
	    * @ExceptionHandler 描述的方法为异常处理方法
	    * 其中注解内部指定的异常类型，为此方法可处理的异常
	    * @param e
	    * @return
	    */
	   @ExceptionHandler(RuntimeException.class)
	   //@ResponseBody
	   public JsonResult doHandleRuntimeException(
			   RuntimeException e) {
		   e.printStackTrace();
		   return new JsonResult(e);
	   }
}
