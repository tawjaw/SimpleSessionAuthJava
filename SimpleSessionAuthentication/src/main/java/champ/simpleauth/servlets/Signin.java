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
 * Servlet implementation class Signin
 */
@WebServlet("/Signin")
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Resource(name="jdbc/simpleauthapp")
	private DataSource dataSource;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//check if user is already signed in. If so... redirect to the home page
		if(AuthHelper.isLoggedIn(request.getSession()) != null) {
			response.sendRedirect("./index.html");
			return; //return so that we stop here!
		}
		if(request.getParameter("isSignInSubmitted") != null) {
			//get email and pass from form
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
		
			//create the dbhelper
			DbHelper dbHelper = new DbHelper(dataSource);
			
			//get the user. If the email and pass matches then we get a user
			//if not we will get null
			User user = dbHelper.getUser(email, password);

			if(user != null)
			{
				System.out.println(user.getFullName());

				//save the user in session
				AuthHelper.saveUserInSession(request.getSession(), user);
				//add the user to the request 
				request.setAttribute("user", user);
				//dispatch to the home page
				request.getRequestDispatcher("/home.jsp").forward(request, response);
				//return to end this here
				return;
			}
			
			else {
				//tried to signin and failed

				request.setAttribute("message", "Error signing in!");
				request.setAttribute("messageClass", "alert-danger");
				
				//we will want to dispatch to signin page but we will do that at the end
			}
		}
		
		//if we didnt return yet, it means either the sign in form has not been submitted yet
		//or the sign in got an error
		//so either way we will dispatch to the signin.jsp
		
		request.getRequestDispatcher("/signin.jsp").forward(request, response);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
