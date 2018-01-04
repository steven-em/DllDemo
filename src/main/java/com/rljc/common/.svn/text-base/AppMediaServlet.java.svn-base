package com.rljc.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class AppMediaServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	protected final transient Log logger = LogFactory.getLog(AppMediaServlet.class);
	public AppMediaServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		doPost(arg0, arg1);
		// TODO Auto-generated method stub
	}

	@Override
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		File file;
		FileInputStream in = null;
		String path = RljcProperties.uploadPath;//ConfigUtil.getInstance().getMediaStorePath();
		String reqPath = URLDecoder.decode(arg0.getRequestURI(), "UTF-8");
		if (!path.endsWith("/")) {
			//path += "/";
		}
		String mediaCategoryStr = "/media";//ConfigUtil.getInstance().getMediaCategory();
		String filePath = path + mediaCategoryStr + reqPath.substring(reqPath.indexOf(mediaCategoryStr) + mediaCategoryStr.length());
		// System.out.println("filePath:"+filePath);
		if (logger.isDebugEnabled()) {
			logger.debug("filePath:" + filePath);
		}
		try {
			file = new File(filePath);
			if (!file.isFile()) { 
				return;
			}
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			return;
		} catch (SecurityException se) { // Be unavailable permanently
			throw (new ServletException("Servlet lacks appropriate privileges."));
		}

		String mimeType = getServletContext().getMimeType(filePath);
		arg1.setContentType(mimeType);
		OutputStream out = arg1.getOutputStream();
		byte[] buf = new byte[1024];
		int count = 0;
		while ((count = in.read(buf)) >= 0) {
			out.write(buf, 0, count);
		}
		in.close();
		out.close();
	}
}