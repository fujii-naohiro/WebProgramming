package controller;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class UserListServlet
 */
@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListServlet() {
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

		//ユーザ一覧情報を取得
		UserDao userDao = new UserDao();
		List<User> userList = userDao.findAll();

		//リクエストスコープにユーザ一覧情報をセット
		request.setAttribute("userList", userList);

		//ユーザ一覧のjspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp");
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
		String name = request.getParameter("name");
		String date1 = request.getParameter("date1");
		String date2 = request.getParameter("date2");

		//未実装：idを引数にして、idに紐づくユーザ情報を出力する
		UserDao userDao = new UserDao();
		List<User> userList = userDao.findAllSerch(loginId, name, date1, date2);


		//ユーザが表示できない場合
		if (loginId.equals("") && name.equals("") && date1.equals("") && date2.equals("")){	//(userList.size() == 0)
			// ログインjspにフォワード
			userList = userDao.findAll();
		}

		//リクエストスコープにユーザ一覧情報をセット
		request.setAttribute("userList", userList);

		//ユーザ一覧のjspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp");
		dispatcher.forward(request, response);
	}

}
