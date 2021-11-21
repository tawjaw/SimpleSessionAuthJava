package champ.simpleauth.model;


import javax.servlet.http.HttpSession;


import at.favre.lib.crypto.bcrypt.BCrypt;

public class AuthHelper {


	public static boolean verifyPassword(String pass, String hashedPass) {
		BCrypt.Result result = BCrypt.verifyer().verify(pass.toCharArray(), hashedPass);

		return result.verified;// result.verified;
	}
	
	public static String hashPassword(String pass) {
		return BCrypt.withDefaults().hashToString(12, pass.toCharArray());
	}
	public static User isLoggedIn(HttpSession session) {
		return (User) session.getAttribute("user");
	}
	public static void saveUserInSession(HttpSession session, User user) {
		session.setAttribute("user", user);
	}
}
