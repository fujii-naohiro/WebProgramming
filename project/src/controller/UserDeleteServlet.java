package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class UserDeleteServelet
 */
@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログインセッションがない場合、ログイン画面位にリダイレクトされる
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userInfo");
		if(user == null){
			// ユーザ一覧のサーブレットにリダイレクト
			response.sendRedirect("LoginServlet");
			return;
		}

		// URLからGETパラメータとしてIDを受け取る
		String id = request.getParameter("id");

		// 確認用：idをコンソールに出力
		System.out.println(id);

		//未実装：idを引数にして、idに紐づくユーザ情報を出力する
		UserDao userDao = new UserDao();
		User userD = userDao.userReference(id);

		//未実装：ユーザ情報をリクエストスコープにセットしてjspにフォワード
		request.setAttribute("userD", userD);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userDelete.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログインセッションがない場合、ログイン画面位にリダイレクトされる
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userInfo");
		if(user == null){
			// ユーザ一覧のサーブレットにリダイレクト
			response.sendRedirect("LoginServlet");
			return;
		}

		// URLからGETパラメータとしてIDを受け取る
		String id = request.getParameter("id");
		String submit = request.getParameter("submit");

		if(submit.equals("ok")) {
			//idを引数にして、idに紐づくユーザ情報を削除する
			UserDao userDao = new UserDao();
			userDao.userDelete(id);
		}
		// ユーザ一覧のサーブレットにリダイレクト
		response.sendRedirect("UserListServlet");
	}

}
