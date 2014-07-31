package com.mjsecurity;



import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminShiro {

	/**
	 * @param args
	 */
	
	private static final transient Logger log = LoggerFactory.getLogger(ShiroJ2SEApp.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
	     SecurityManager securityManager = factory.getInstance();
	     
	     SecurityUtils.setSecurityManager(securityManager);
	     
	  // get the currently executing user:
	        Subject usr = SecurityUtils.getSubject();
	     
	    
	            UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");

	            try {
	                usr.login(token);
	            } 
	            catch (AuthenticationException ae) {
	                //unexpected condition?  error?
	            	log.error(ae.toString()) ;
	            	return ;
	            }
	       
	     
	     log.info("User [" + usr.getPrincipal() + "] logged in successfully.");
	     
	    
	     
	     if (usr.isPermitted("File:write:xyz.doc")) {
	            log.info(usr.getPrincipal() + " has permission to write xyz.doc ");
	            
	        } else {
	            log.info(usr.getPrincipal() + " does not have permission to write xyz.doc ");
	        }

	     if (usr.isPermitted("File:read:xyz.doc")) {
	            log.info(usr.getPrincipal() + " has permission to read xyz.doc ");
	        } else {
	            log.info(usr.getPrincipal() + " does not have permission to read xyz.doc ");
	        }
	    
	     
	     usr.logout() ;
	     
	}

}

