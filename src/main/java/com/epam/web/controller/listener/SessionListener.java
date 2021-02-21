package com.epam.web.controller.listener;

import com.epam.web.util.Language;
import com.epam.web.util.SessionAttribute;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Locale;

@WebListener
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        Locale locale = Locale.getDefault();
        String lang = locale.getLanguage();
        try {
            Language.valueOf(lang.toUpperCase());
            session.setAttribute(SessionAttribute.LOCAL_LANG, lang);
        } catch (IllegalArgumentException e) {
            session.setAttribute(SessionAttribute.LOCAL_LANG, Language.EN.toString().toLowerCase());
        }
    }
}
