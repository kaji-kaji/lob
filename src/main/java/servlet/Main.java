package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Access;
import model.PostTimeLogic;
import model.User;

@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {

		// つぶやきリストをアプリケーションスコープから取得
		ServletContext application = this.getServletContext();
		List<Access> accessList =
				(List<Access>) application.getAttribute("accessList");

		List<Access> qList =
				(List<Access>) application.getAttribute("qList");
		// 取得できなかった場合は、つぶやきリストを新規作成して
		// アプリケーションスコープに保存
		if (accessList == null) {
			accessList = new ArrayList<>();
			application.setAttribute("accessList", accessList);
			qList = new ArrayList<>();
			application.setAttribute("qList", qList);
		}
		// ログインしているか確認するため
		// セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) { // ログインしていない場合
			// リダイレクト
			response.sendRedirect("/lob/");
		} else { // ログイン済みの場合
			// フォワード
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {

		Date now = new Date(); // Dateのインスタンス"now"を生成
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd　　HH:mm");
		String inoutTime = dateFormat.format(now); // formatメソッドでフォーマット 

		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String time = request.getParameter("time");//ラジオボタンで選択されたやつ
		String intime = "";
		String outtime = "";


		// 入力値チェック
		if (time != null && time.length() != 0) {
			ServletContext application = this.getServletContext();
			List<Access> accessList =
					(List<Access>) application.getAttribute("accessList");

			List<Access> qList =
					(List<Access>) application.getAttribute("qList");

			// セッションスコープに保存されたユーザー情報を取得
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");


			if(time.equals("入室")) {
				intime = inoutTime; //入室時間をきろく

			}else{
				outtime = inoutTime;//退室時間を記録
			}


			Access access = new Access(loginUser.getName(), intime, outtime);//インスタンスの生成
			PostTimeLogic postTimeLogic = new PostTimeLogic();
			postTimeLogic.execute(access, accessList);

			for(int i = 0; i < accessList.size(); i++) {
				//先に退出してしまったとき
				//listに保存されている、入室時間が空かつ前に入室されていたか
				if((accessList.get(i).getInTime().equals(""))) {
					if(accessList.size() == 1) {
						RequestDispatcher dispatcher =
								request.getRequestDispatcher("/WEB-INF/jsp/early.jsp");

						dispatcher.forward(request, response);
						accessList.remove(i);
						break;
					}

					for(int j = i+1; j < accessList.size(); j++) {//前に入室しているか探すループ
						if(!(accessList.get(j).getInTime().equals("")) && accessList.get(j).getUserName() == accessList.get(i).getUserName()) {
							break;
						}else {
							RequestDispatcher dispatcher =
									request.getRequestDispatcher("/WEB-INF/jsp/early.jsp");

							dispatcher.forward(request, response);
							accessList.remove(i);//listの削除
						}
					}
				}

				if(!(accessList.get(i).getInTime().equals("")) && !(accessList.get(i).getOutTime().equals(""))) {
					qList.add(0, accessList.get(i));//三つの引数をもったlistが生成されたら、qlistにいれる
					accessList.remove(i);//accesslistは消去
					break;
				}
			}

			// アプリケーションスコープにつぶやきリストを保存
			application.setAttribute("accessList", accessList);
			application.setAttribute("qList", qList);
		} else {
			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "入室か退室のどちらかを選択してください");
		}

		// メイン画面にフォワード

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");

		dispatcher.forward(request, response);
	}
}