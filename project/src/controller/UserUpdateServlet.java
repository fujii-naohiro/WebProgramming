package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

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
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateServlet() {
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
		User userU = userDao.userReference(id);

		//未実装：ユーザ情報をリクエストスコープにセットしてjspにフォワード
		request.setAttribute("userU", userU);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログインセッションがない場合、ログイン画面位にリダイレクトされる
		HttpSession session = request.getSession();
		User usera = (User) session.getAttribute("userInfo");
		if(usera == null){
			// ユーザ一覧のサーブレットにリダイレクト
			response.sendRedirect("LoginServlet");
			return;
		}

		 // リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");

		// URLからGETパラメータとしてIDを受け取る
		String id = request.getParameter("id");

		// 確認用：idをコンソールに出力
		System.out.println(id);

		// リクエストパラメータの入力項目を取得
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String passwordCheck = request.getParameter("passwordCheck");
		String name = request.getParameter("name");
		String birthDate = request.getParameter("birthDate");

		UserDao userU = new UserDao();

		/*更新失敗時*/
		if (!password.equals(passwordCheck) ||
			name.equals("") || birthDate.equals("")) {

			// リクエストスコープにエラーメッセージをセット
			request.setAttribute("errMsg", "入力された内容は正しくありません");

			//入力内容
			Date sqlDate = java.sql.Date.valueOf(birthDate);
			User user = new User(loginId, name, sqlDate);
			request.setAttribute("userU", user);

			// ユーザ情報更新jspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
			dispatcher.forward(request, response);
			return;

		}else if(password.equals("") && passwordCheck.equals("")){
			/*更新登録できた場合、パスワード以外の内容更新*/
			//リクエストパラメータの入力項目を因数に渡して、Daoのメソッドを実行
			userU.userUpdate(name, birthDate, loginId);

		}else {
			/*更新登録できた場合 全ての内容*/
			// リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行
			try {
				userU.userUpdate(password, name, birthDate, loginId);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

		}
		//ユーザ一覧のサーブレットにリダイレクト
		response.sendRedirect("UserListServlet");
	}
}
