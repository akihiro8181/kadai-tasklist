package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import util.DBUtil;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // String型の_token変数を宣言し、 hiddenとして送信された_tokenを代入
        String _token = request.getParameter("_token");

        // 送信された_tokenがない場合やrequestのsessionIdが値と異なっていた場合にデータの登録ができないようにするif
        if(_token != null && _token.equals(request.getSession().getId())) {
            // EntityManager型のem変数を宣言し、DBUnitのStaticメソッド呼び出して初期化
            EntityManager em = DBUtil.createEntityManager();

            // Task(DTO)のインスタンス生成
            Task t = new Task();

            // String型のcontent変数を宣言し、requestから受け取ったcontentを代入
            String content = request.getParameter("content");
            // Taskインスタンスのcontentにcontent変数を代入
            t.setContent(content);

            // Timestamp型のcurrentTime変数を宣言し、Timestampのインスタンスを生成(現在時刻);
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            // Taskインスタンスのcreated_atにcurrentTime変数を代入
            t.setCreated_at(currentTime);
            t.setUpdated_at(currentTime);

            // トランザクションの始め
            em.getTransaction().begin();
            em.persist(t);  // tasksテーブルに設定したTaskインスタンスの値(content,created_at,updated_at)を保存
            em.getTransaction().commit();   // 処理の確定
            em.close(); // emを閉じる(リソースの解放？)

            // indexServletを通じてindex.jspにリダイレクト
            response.sendRedirect(request.getContextPath() + "/index");
        }
    }

}
