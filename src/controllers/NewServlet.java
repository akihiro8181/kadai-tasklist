package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/new")
public class NewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // CSRF対策
        request.setAttribute("_token", request.getSession().getId());

        // おまじないとしてのインスタンスを生成
        // update時に_form.jspを使いまわすためにインスタンスを生成
        request.setAttribute("task", new Task());

        // RequestDispatcher型のrd変数を宣言し、リクエストをnew.jspに渡すディスパッチャを代入
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/new.jsp");
        // ディスパッチャに設定したnew.jspにrequestなどを送信
        rd.forward(request, response);


    }

}
