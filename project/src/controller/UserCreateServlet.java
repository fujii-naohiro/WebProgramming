package controller;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;

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
 * Servlet implementation class UserCreateServlet
 */
@WebServlet("/UserCreateServlet")
public class UserCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCreateServlet() {
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

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userNew.jsp");
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

        // リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの入力項目を取得
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String passwordCheck = request.getParameter("passwordCheck");
		String name = request.getParameter("name");
		String birthDate = request.getParameter("birthDate");

		// リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行
		UserDao check = new UserDao();
		boolean seach = check.loginIdCheck(loginId);

		/*登録失敗時*/
		if (seach == false ||
			!password.equals(passwordCheck) ||
			loginId.equals("") || password.equals("") || passwordCheck.equals("") || name.equals("") || birthDate.equals("")) {

			// リクエストスコープにエラーメッセージをセット
			request.setAttribute("errMsg", "入力された内容は正しくありません");

			// ログインjspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userNew.jsp");
			dispatcher.forward(request, response);

		}else {
		/*新規登録できた場合*/
			// リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行
			UserDao userDao = new UserDao();

			try {
				userDao.createUser(loginId, password,name,birthDate);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

			//ユーザ一覧のサーブレットにリダイレクト
			response.sendRedirect("UserListServlet");
		}

	}
}
