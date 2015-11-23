package de.nordakademie.iaa.mcnak.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Sonja Scholz
 * @author Nicolas Storl
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession httpSession = request.getSession();

        String requestUrl = request.getRequestURL().toString();
        Boolean continueWithRequest;

        if (requestUrl.endsWith("login.action") || requestUrl.contains("/css/"))
        {
            // User has requested the login page. Don't check if he is logged in.
            continueWithRequest = true;
        }
        else
        {
            Object loggedInAttribute = httpSession.getAttribute("loggedIn");

            // Check if user is logged in
            if (loggedInAttribute != null && loggedInAttribute.equals("true"))
            {
                continueWithRequest = true;
            }
            else
            {
                continueWithRequest = false;
            }
        }

        if (continueWithRequest)
        {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else
        {
            response.sendRedirect("login.action");
        }
    }

    @Override
    public void destroy() {

    }
}
