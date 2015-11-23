package de.nordakademie.iaa.mcnak.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;

import com.sun.org.apache.xpath.internal.operations.Bool;
import de.nordakademie.iaa.mcnak.dataType.UserRole;

/**
 * @author Sonja Scholz
 * @author Nicolas Storl
 */
public class UserRoleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession httpSession = request.getSession();

        String requestUrl = request.getRequestURI();
        Object userRoleAttribute = httpSession.getAttribute("role");

        if (userRoleAttribute == null)
        {
            // This should not happen
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        UserRole userRole = (UserRole)userRoleAttribute;

        Boolean isValid = isAllowedOnUrl(userRole, requestUrl);

        if (isValid)
        {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else
        {
            // The user is not allowed on this page. Send him to his home view
            response.sendError(220);
        }
    }

    private Boolean isAllowedOnUrl(UserRole role, String url)
    {
        Boolean studentNamespace = url.contains("/student/");
        Boolean teacherNamespace = url.contains("/teacher/");
        Boolean adminNamespace = url.contains("/admin/");

        switch (role)
        {
            case Student:
                return teacherNamespace == false && adminNamespace == false;
            case Teacher:
                return adminNamespace == false && studentNamespace == false;
            case Admin:
                return teacherNamespace == false && studentNamespace == false;
            default:
                return false;
        }
    }

    private String getUserHomePage(UserRole role)
    {
        switch (role)
        {
            case Student:
                break;
            case Teacher:
                break;
            case Admin:
                break;
            default:
                break;
        }
        return "Bla";
    }

    @Override
    public void destroy() {

    }
}
