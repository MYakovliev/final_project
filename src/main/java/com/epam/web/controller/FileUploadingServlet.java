package com.epam.web.controller;


import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandResult;
import com.epam.web.command.CommandType;
import com.epam.web.util.RequestParameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.util.UUID;

@WebServlet(urlPatterns = {"/upload/*"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadingServlet extends HttpServlet {
    private static final String IMAGE_DIRECTORY_NAME = "img" + File.separator + "uploads" + File.separator;
    private static final String UPLOAD_DIR = "C:" + File.separator + "epam_tasks" +
            File.separator + "demo" + File.separator + "src" + File.separator + "main" + File.separator +
            "webapp" + File.separator + "img" + File.separator + "uploads";
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        List<String> images = new ArrayList<>();
        String uploadFileDir = UPLOAD_DIR + File.separator;
        logger.debug(uploadFileDir);
        File fileSaveDir = new File(uploadFileDir);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        Collection<Part> parts = request.getParts();
        if (parts.size() > 0) {
            parts.stream().forEach(part -> {
                try {
                    String path = part.getSubmittedFileName();
                    logger.debug("path:{}", path);
                    if (path != null && !path.isEmpty()) {
                        String randFilename = UUID.randomUUID() + path.substring(path.lastIndexOf("."));//
                        logger.debug("randFileName:{}", randFilename);
                        String pathToFile = uploadFileDir + randFilename;
                        part.write(pathToFile);
                        String filePath = IMAGE_DIRECTORY_NAME + randFilename;
                        images.add(filePath);
                    }
                } catch (IOException e) {
                    logger.error(e);
                }
            });
        }
        request.setAttribute(RequestParameter.IMAGE_PATH, images);
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name;

        name = request.getParameter(RequestParameter.COMMAND);
        CommandType command = CommandType.valueOf(name.toUpperCase());
        ActionCommand actionCommand = command.getCommand();
        CommandResult commandResult = actionCommand.execute(request);
        if (commandResult.isRedirect()) {
            response.sendRedirect(getServletContext().getContextPath() + commandResult.getPage());
        } else {
            getServletContext().getRequestDispatcher(commandResult.getPage()).forward(request, response);
        }
    }
}