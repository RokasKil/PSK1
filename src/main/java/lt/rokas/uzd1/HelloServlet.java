package lt.rokas.uzd1;

import lt.rokas.uzd1.entity.ExpenseGroup;

import java.io.*;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;

@RequestScoped
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    @PersistenceContext
    EntityManager entityManager;

    @Resource
    UserTransaction transaction;

    public void init() {
        message = "Hello World!";
    }

    @Transactional
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        ExpenseGroup g = new ExpenseGroup();
        entityManager.persist(g);
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println(g);
        out.println("</body></html>");
    }

    public void destroy() {
    }
}