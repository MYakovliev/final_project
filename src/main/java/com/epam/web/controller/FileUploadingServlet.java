package com.epam.web.controller;


import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandResult;
import com.epam.web.command.CommandType;
import com.epam.web.util.RequestParameter;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WebServlet(name = "FileUploadServlet", urlPatterns = "/upload/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadingServlet extends HttpServlet {


    private static final Logger logger = LogManager.getLogger(FileUploadingServlet.class);
    private static final String IMAGES_DIRECTORY_NAME = "images";
    private static final String UPLOAD_DIR = "C:\\epam_tasks\\demo\\src\\main\\webapp\\img\\"
            + IMAGES_DIRECTORY_NAME;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<String> images = new ArrayList<>();
        if (ServletFileUpload.isMultipartContent(request)) {
            Collection<Part> parts = request.getParts();
            for (Part part : parts) {
                String filename = part.getSubmittedFileName();
                String uploadPath = UPLOAD_DIR + File.separator + filename;

                String imagePath = IMAGES_DIRECTORY_NAME + File.separator + filename;

                if (Files.exists(Paths.get(uploadPath + filename).toAbsolutePath())) {
                    imagePath = request.getParameter(RequestParameter.IMAGE_PATH);
                }

                boolean isSuccess;
                try (InputStream inputStream = part.getInputStream()) {
                    isSuccess = uploadFile(inputStream, uploadPath);
                }

                if (isSuccess) {
                    images.add(uploadPath);
                }
            }
            request.setAttribute(RequestParameter.IMAGE_PATH, images);
            process(request, response);
        }
    }

    private boolean uploadFile(InputStream inputStream, String path) throws ServletException {
        try {
            byte[] bytes = new byte[inputStream.available()];
            int result = inputStream.read(bytes);
            if (result != -1) {
                try (FileOutputStream fops = new FileOutputStream(path)) {
                    fops.write(bytes);
                }
            }
        } catch (IOException e) {
            logger.log(Level.ERROR, e);
            throw new ServletException(e);
        }
        return true;
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