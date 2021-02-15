package com.epam.web.command.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.util.JspPath;

import javax.servlet.http.HttpServletRequest;

public class ToLogin implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return JspPath.LOGIN;
    }
}
