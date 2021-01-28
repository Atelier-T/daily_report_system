package controllers.folreports;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Follow;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class FolreportsIndexServlet
 */
@WebServlet("/folreports/index")
public class FolreportsIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FolreportsIndexServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        // ログインユーザのID
        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");

        // ログインユーザがフォローしているユーザ
        List<Follow> table_follow = em.createNamedQuery("tableFollow", Follow.class)
                                                .setParameter("follower_id", login_employee)
                                                .getResultList();

        // フォローしているユーザの日報
        List<Report> check_follow = new ArrayList<Report>();

        for(int i = 0; i < table_follow.size(); i++) {
            check_follow.addAll(em.createNamedQuery("checkFollow", Report.class)
                    .setParameter("employee", table_follow.get(i).getFollowed_id())
                    .getResultList());
        }

        // フォローしているユーザの日報の数
        long check_follow_count = check_follow.size();

        em.close();



        request.setAttribute("check_follow", check_follow);
        request.setAttribute("check_follow_count", check_follow_count);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/folreports/index.jsp");
        rd.forward(request, response);
    }

}
