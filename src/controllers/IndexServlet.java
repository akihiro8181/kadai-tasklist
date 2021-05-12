package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import util.DBUtil;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // EntityManager型のem変数を宣言し、DBUnitのStaticメソッド呼び出して初期化
        EntityManager em = DBUtil.createEntityManager();

        // List型コレクションtasksを宣言し、Taskクラス（DTO）で宣言した「getAllTasks」のSelect文を実行し代入
        List<Task> tasks = em.createNamedQuery("getAllTasks", Task.class).getResultList();

        // tasksテーブルのデータを取得できているため、emを閉じる(リソースの解放？)
        em.close();

        // List型配列のtasksをrequest先の"tasks"に値を渡す
        // index.jspで${tasks}等と書くことでtasksテーブルのデータを表示可能
        request.setAttribute("tasks", tasks);

        // RequestDispatcher型のrd変数を宣言し、リクエストをindex.jspに渡すディスパッチャを代入
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/index.jsp");
        // ディスパッチャに設定したindex.jspにrequestなどを送信
        rd.forward(request, response);
    }

}
