package com.hm3;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		//get the client input from the data column in student survey form
		String data = request.getParameter("data");
		String[] strArr = data.split(",");
		//set the 10 input numbers in an array of int 
		int[] arr = new int[strArr.length];
		for(int i = 0 ; i < strArr.length ; i++){
		   arr[i] = Integer.parseInt(strArr[i]);
		}
		// do the calculation
		DataProcessor dp = new DataProcessor();
		dp.calculate(arr);
				
//		PrintWriter out = response.getWriter();
//		out.print("getParam from data is "+data);
//		//out.print("getParam from data is "+data.length());
//		out.print("The string array is "+ Arrays.toString(strArr));
//		out.print("getParam from data length"+strArr.length);
//		out.print("The array is "+ Arrays.toString(arr));
		
		//create a DataBean to store the calculation result of mean and standDev
		DataBean dBean = new DataBean();
	
		dBean.setMean(dp.mean);
		dBean.setStandDev(dp.standDev); 
		
		//store bean to a request object
		request.setAttribute("dBean", dBean);
		
		//if mean is greater or equal to 90, go to the win page
		if(dp.mean.compareTo(new BigDecimal(90)) >= 0 ) {
			RequestDispatcher rd1 = request.getRequestDispatcher("win.jsp");
			rd1.forward(request, response);
		}
		//otherwise go to the regular thank you page
		RequestDispatcher rd2 = request.getRequestDispatcher("thank.jsp");
		rd2.forward(request, response);
	}

}
