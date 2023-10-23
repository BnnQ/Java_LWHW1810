package me.bnnq.homework.services;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.Getter;

import java.nio.file.Path;
import java.nio.file.Paths;

@WebListener
public class ContextPathHolder implements ServletContextListener
{
    @Getter
    private static String contextPath;
    @Getter
    private static Path projectPath;

    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        var context = sce.getServletContext();
        contextPath = context.getRealPath("/");
        projectPath = Paths.get(contextPath).getParent().getParent();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
        // empty
    }

}
