package champ.simpleauth.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import champ.simpleauth.model.AuthHelper;
import champ.simpleauth.model.DbHelper;
import champ.simpleauth.model.User;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name="jdbc/simpleauthapp")
	private DataSource dataSource;
	
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Signup() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// check if user is already signed in. If so... redirect to the home page
		if (AuthHelper.isLoggedIn(request.getSession()) != null) {
			response.sendRedirect("./index.html");
			return; // return so that we stop here!
		}
		
		if(request.getParameter("isSignUpSubmitted") != null) {
			
			String fullName= request.getParameter("fullname");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String passwordRepeat = request.getParameter("passwordRepeat");
			
			//check if passwords match
			if(!password.equals(passwordRepeat)) {
				//return with message error
				request.setAttribute("message", "Error Signing Up! Passwords don't match");
				request.setAttribute("messageClass", "alert-danger");
				
				request.getRequestDispatcher("/signup.jsp").forward(request, response);

				return;
			}
			
			DbHelper dbHelper = new DbHelper(dataSource);
			String hashedPassword = AuthHelper.hashPassword(password);
			System.out.println(hashedPassword);
			
			if( dbHelper.insertNewUser(new User(-1, email, fullName), hashedPassword)) {
				//we got new user inserted 
				//dispatch to sign in with successful method for them to sign in
				request.setAttribute("message", "Account created successfylly! Please sign in.");
				request.setAttribute("messageClass", "alert-success");
				
				request.getRequestDispatcher("/signin.jsp").forward(request, response);
				return;
			}
			else {
				//error signing up
				//return with message error
				request.setAttribute("message", "Error Signing Up! Something went wrong.");
				request.setAttribute("messageClass", "alert-danger");
				
				request.getRequestDispatcher("/signup.jsp").forward(request, response);
				return;
			}
			
		}
		
		//if we didnt return at this point it means the form is not submitted so we just go to the signup page
		
		request.getRequestDispatcher("/signup.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
