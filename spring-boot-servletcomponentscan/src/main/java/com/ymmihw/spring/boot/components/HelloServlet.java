package com.ymmihw.spring.boot.components;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/hello", initParams = {@WebInitParam(name = "msg", value = "hello")})
public class HelloServlet extends HttpServlet {
  private static final long serialVersionUID = 3104760419006478301L;
  private ServletConfig servletConfig;

  @Override
  public void init(ServletConfig servletConfig) {
    this.servletConfig = servletConfig;
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) {
    try {
      response.getOutputStream().write(servletConfig.getInitParameter("msg").getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
