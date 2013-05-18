package com.mjt.action;



import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.mjt.util.WeatherReport;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class DailyLifeAction extends ActionSupport{

	public String weatherreport() {
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("UTF-8") ;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String city = request.getParameter("city");
		String info = WeatherReport.getWeather("π„÷›");
		request.setAttribute("info", info) ;
		//System.out.println(info);
		return SUCCESS;
	}
}
