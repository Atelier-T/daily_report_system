package controllers.follow;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Employee;
import models.Follow;
import utils.DBUtil;

/**
 * Servlet implementation class FollowCreateServlet
 */
@WebServlet("/follow/create")
public class FollowCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowCreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Follow f = new Follow();

        HttpSession session = ((HttpServletRequest)request).getSession();

        Employee e = em.find(Employee.class, Integer.parseInt(request.getParameter("id")));
        Employee login_e = (Employee)session.getAttribute("login_employee");

        f.setFollower_id(login_e);
        f.setFollowed_id(e);

        em.getTransaction().begin();
        em.persist(f);
        em.getTransaction().commit();
        request.getSession().setAttribute("flush", "フォローしました。");
        em.close();

        response.sendRedirect(request.getContextPath() + "/empview/index");
    }
}
