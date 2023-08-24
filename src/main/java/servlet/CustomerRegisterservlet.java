package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import object.Admin;
import object.Customer;
import sql.Login;
import sql.Register;

/**
 * Servlet implementation class CustomerRegisterServlet
 */
@WebServlet("/CustomerRegisterServlet")
public class CustomerRegisterservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//顧客登録画面を表示させる
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/customer_register.jsp");
		dispatcher.forward(request,  response);
	}

	
	//顧客登録画面より入力された値をもとにデータベースへ顧客情報を登録する
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字コードの設定
		response.setContentType("text/html; charset=UTF-8");;
		request.setCharacterEncoding("UTF-8");
		
		//顧客登録画面で入力された値を取得
		String customer_name = request.getParameter("customer_name");
		String customer_address = request.getParameter("customer_address");
		
		//管理者のセッションを取得
		HttpSession session = request.getSession(true);
		Admin admin = (Admin) session.getAttribute("admin");
		
		Register register = new Register();
		
		//顧客情報登録処理を実行
		register.customer_register(admin.getId(), customer_name, customer_address);
		
		Login login = new Login();
		List<Customer> customer = null;
		
		//データベースから取得した顧客情報を格納
		customer = login.getCustomerInfo(String.valueOf(admin.getId()));
		
		//格納した顧客情報を遷移先の画面に渡す
		request.setAttribute("customer",  customer);
		
		RequestDispatcher dispatcher = 
			request.getRequestDispatcher("WEB-INF/jsp/customer_list.jsp");
		dispatcher.forward(request,  response);
				
	} 
    /**
     * @see HttpServlet#HttpServlet()
     
    public CustomerRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
*/
}
