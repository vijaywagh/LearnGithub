package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mjsecurity.ShiroJ2SEApp;

/**
 * Servlet implementation class LoginAction
 */
@WebServlet("/login")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	private static final transient Logger log = LoggerFactory.getLogger(LoginAction.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String u = request.getParameter("uname");
		String p = request.getParameter("pwd");
		
		
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
	     SecurityManager securityManager = factory.getInstance();
	     
	     SecurityUtils.setSecurityManager(securityManager);
	     
	  // get the currently executing user:
	        Subject usr = SecurityUtils.getSubject();
	     
	    
	            UsernamePasswordToken token = new UsernamePasswordToken(u, p);

	            try {
	                usr.login(token);
	            } 
	            catch (AuthenticationException ae) {
	                //unexpected condition?  error?
	            	log.error(ae.toString());
	            	return ;
	            }
	       
	     
	     log.info("User [" + usr.getPrincipal() + "] logged in successfully.");
	     
	    
	     
	     if (usr.isPermitted("File:write:xyz.doc")) {
	            log.info(usr.getPrincipal() + " has permission to write xyz.doc ");
	            out.println(u+ " has permission to write xyz.doc");
	        } else {
	            log.info(usr.getPrincipal() + " does not have permission to write xyz.doc ");
	            out.println(u+" does not have permission to write xyz.doc ");
	        }

	     if (usr.isPermitted("File:read:xyz.doc")) {
	            log.info(usr.getPrincipal() + " has permission to read xyz.doc ");
	            out.println(u+" has permission to read xyz.doc ");
	        } else {
	            log.info(usr.getPrincipal() + " does not have permission to read xyz.doc ");
	            out.println(u+" does not have permission to read xyz.doc ");
	        }
	    
	     
	     usr.logout() ;
		
	}

}
