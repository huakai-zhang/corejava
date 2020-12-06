package com.spring.framework.web.servlet;

import java.io.File;
import java.util.Locale;

/**
 * @author 花开不合阳春暮
 * @date 2020/11/28 下午5:29
 */
public class ViewResolver {

    private final String DEFAULT_TEMPLATE_SUFFX = ".html";

    private File templateRootDir;

    //private View view;

    public ViewResolver(String templateRoot) {
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();
        this.templateRootDir = new File(templateRootPath);
    }

    public View resolveViewName(String viewName, Locale locale) {
        if (null == viewName || "".equals(viewName.trim())) {
            return null;
        }

        viewName = viewName.endsWith(DEFAULT_TEMPLATE_SUFFX) ? viewName : (viewName + DEFAULT_TEMPLATE_SUFFX);

        File templateFile = new File((templateRootDir.getPath() + "/" + viewName).replaceAll("/+", "/"));

        return new View(templateFile);
    }
}
