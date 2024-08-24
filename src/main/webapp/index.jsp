<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport"
		content="width=device-width, 
						initial-scale=1.0">
	<title>job Recommendations</title>
	<link rel="stylesheet"
		href="style.css">
</head>

<body>
	<header>
		<h1 class="heading">JOB PORTAL LOGIN</h1>
		<!-- <h3 class="title">Login & Registration Form</h3> -->
	</header>


	<div class="container">


		<div class="slider"></div>
		<div class="btn">
			<button class="login">Login</button>
			<button class="signup">Signup</button>
		</div>


		<div class="form-section">
		
			<form action="LoginServlet" method="post">

				<div class="login-box">
				
					<input type="email" class="email ele"
						placeholder="youremail@email.com" name="email" required> <input
						type="password" class="password ele" placeholder="password"
						name="password" required>
		
						<span style="color:red;">${error}</span>
					<button class="clkbtn">Login</button>

				</div>

			</form>
			<form action="signupservlet" method="post" >
				<div class="signup-box">
					<input type="text" class="name ele" placeholder="Enter your name" name="name" required>
					<input type="email" class="email ele"
						placeholder="youremail@email.com" name="email" required> <input type="password"
						class="password ele" placeholder="password" name="password" required> 
					<button class="clkbtn" >SignUp</button>
				</div>
			</form>
		</div>
	</div>
	<script src="index.js "></script>


</body>

</html>
