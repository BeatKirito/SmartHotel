package com.smtl.app.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class CoverResponse extends HttpServletResponseWrapper {
	
	private PrintWriter tmpWriter;
	private StringWriter outPut;

	public CoverResponse(HttpServletResponse response) {
		super(response);
		outPut = new StringWriter();
		tmpWriter = new PrintWriter(outPut);
	}
	
	public void finalize() throws Throwable {
		super.finalize();
		outPut.close();
		tmpWriter.close();
	}
	
	public String getContent() {
		tmpWriter.flush();
		String s = outPut.toString();
		return s;
	}
	
	public PrintWriter getWriter() throws IOException {
		return tmpWriter;
	}
	
	public void close() throws IOException {
		tmpWriter.close();
	}

	
}
