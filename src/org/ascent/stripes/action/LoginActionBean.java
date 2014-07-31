package org.ascent.stripes.action;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.Validate;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoginActionBean implements ActionBean {
	
	private ActionBeanContext context;
	@Validate(required=true)
	private String userName;
	private String password;
	private static final transient Logger log = LoggerFactory.getLogger(LoginActionBean.class);

	public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }
    
    
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}
	

	public void setPassword(String password) {
		this.password = password;
	}

	@DefaultHandler	
    public Resolution login() {

		
		
		
		if(authenticate(getUserName(),getPassword())){
			
			return new ForwardResolution("/welcome.jsp");
		}
		else
			return new ForwardResolution("/index.jsp");
	}
	
	public boolean authenticate(String u, String p)
	{
		SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Query q = session.createQuery("from where userName:name and password:pwd");
		q.setParameter("name", u);
		q.setParameter("pwd", p);
		if(q.list().size()>0)
			return true;
		else
			return false;
	}

	
		
	
	
}
