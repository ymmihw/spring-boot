package com.ymmihw.spring.boot.components;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "echo servlet", urlPatterns = "/echo")
public class EchoServlet extends HttpServlet {
  private static final long serialVersionUID = 8226091488901897525L;

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) {
    try {
      Path path = File.createTempFile("echo", "tmp").toPath();
      Files.copy(request.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
      Files.copy(path, response.getOutputStream());
      Files.delete(path);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
