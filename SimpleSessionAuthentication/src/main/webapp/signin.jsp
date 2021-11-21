<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign In</title>

<!-- Font Awesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"
	rel="stylesheet" />
<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
	rel="stylesheet" />
<!-- MDB -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.10.0/mdb.min.css"
	rel="stylesheet" />

<!-- MDB -->
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.10.0/mdb.min.js"></script>
<style>
.divider:after, .divider:before {
	content: "";
	flex: 1;
	height: 1px;
	background: #eee;
}

.h-custom {
	height: calc(100% - 73px);
}

@media ( max-width : 450px) {
	.h-custom {
		height: 100%;
	}
}
</style>
</head>
<body>

	<section class="vh-100" style="background-color: #eee;">
		<div class="container h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-lg-12 col-xl-11">
					<div class="card text-black" style="border-radius: 25px;">
						<div class="card-body p-md-5">
							<div class="row justify-content-center">



								<div
									class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">
									<img
										src="https://mdbootstrap.com/img/Photos/new-templates/bootstrap-login-form/draw2.png"
										class="img-fluid" alt="Sample image">
								</div>
								<div class="col-md-11 col-lg-6 col-xl-5 order-2 order-lg-1">

									<p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Sign
										In</p>

									<form class="mx-1 mx-md-4" action="Signin" method="POST">



										<!-- Email input -->
										<div class="form-outline mb-4 ">
											<input type="email" id="form3Example3"
												class="form-control form-control-lg" name="email"
												placeholder="Enter a valid email address" required /> <label
												class="form-label" for="form3Example3">Email address</label>
										</div>

										<!-- Password input -->
										<div class="form-outline mb-3">
											<input type="password" id="form3Example4"
												class="form-control form-control-lg" name="password"
												placeholder="Enter password" required /> <label
												class="form-label" for="form3Example4">Password</label>
										</div>
										<input type="hidden" name="isSignInSubmitted" value="true" />



										<div class="text-center text-lg-start mt-4 pt-2">
											<button type="submit" class="btn btn-primary btn-lg"
												style="padding-left: 2.5rem; padding-right: 2.5rem;">Login</button>
											<p class="small fw-bold mt-2 pt-1 mb-0">
												Don't have an account? <a href="./Signup"
													class="link-danger">Register</a>
											</p>
										</div>

									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<c:if test="${not empty message}">
			<div class="alert  fixed-top ${messageClass }" role="alert">
				<strong> ${message} </strong>
			</div>
		</c:if>
	</section>




</body>
</html>