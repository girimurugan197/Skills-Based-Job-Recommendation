<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta charset="utf-8">
<title>Register</title>
<!-- <link rel="stylesheet" type="text/css" href="adminhome.css"> -->

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin=""></script>

<style class="input" type="text/css">
form {
	padding: 10px;
	margin-left: 50px;
	margin-bottom: 20px;
	margin-top: 10px;
	background: linear-gradient(45deg, #D98EFF, #8ED6FF, #E0FF8E, #FFCF8E);
	background-size: 100% 400%;
	animation: animateGradient 5s ease-in-out infinite;
	animation-delay: 2s;
	border-radius: 20px;
}

label {
	margin-right: 10px;
	width: 150px;
	/* Adjust this value to the desired width of your labels */
	text-align: right;
	padding-right: 10px;
	padding-bottom: 10px;
}

#header {
	position: fixed;
	top: 0px;
	width: 100%;
}

input[type="text"], input[type="number"], input[type="email"] {
	height: 25px;
	width: 250px;
	font-size: 15px;
	margin-bottom: 20px;
	background-color: #fff;
	padding-left: 5px;
}

#skills {
	margin-left: 0px;
	margin-top: 2px;
}

@
keyframes animateGradient { 0% {
	background-position: 0 0;
}
50
%
{
background-position
:
0
100%;
}
100
%
{
background-position
:
0
0;
}
}
</style>
</head>

<body style="background-color: grey">

	<br>
	<br>
	<div>
		<br>
		<center>
			<h1>
				<b style="color: white">JOB REGISTRATION</b>
			</h1>
		</center>
		<div class="container">
			<form action="CandidateUpdate" method="post">
				<br>

				<!--  <div style="margin-left:20px;margin-top:10px;font-size:30px"> Student's Marks Information </div> -->
				<!--  <h3 style="margin-left: 50px;">FirstName</h3> -->
				<div>
					<div>
						<label>FirstName:</label> <input class="input" type="text"
							name="FirstName" max="20" value="${requestScope.FirstName}"
							required>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label>LastName:</label> <input class="input" type="text"
							name="LastName" max="20" value="${requestScope.LastName}"
							required><br>
						<br>
					</div>
					<div>
						<label>Email:</label> <input class="input" id="email" type="email"
							name="Email" value="${requestScope.Email}" readonly>



						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label>Mobile No:</label> <input class="input" type="tel"
							name="numericField" maxlength="10" pattern="[0-9]{10}"
							value="${requestScope.numericField}"
							title="Please include only numbers in this field" required><br>
						<br>
					</div>
					<div>
						<label>University/School:</label> <input class="input" type="text"
							name="University" value="${requestScope.University}" required>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label>Degree:</label> <select id="Degree" name="Degree">
							<option value="B.TECH"
								${requestScope.Degree == "B.TECH" ? 'selected' : ''}>B.TECH</option>
							<option value="B.E"
								${requestScope.Degree == "B.E" ? 'selected' : ''}>B.E</option>
							<option value="BSC"
								${requestScope.Degree == "BSC" ? 'selected' : ''}>BSC</option>
							<option value="B.COM"
								${requestScope.Degree == "B.COM" ? 'selected' : ''}>B.COM</option>
							<option value="M.Tech"
								${requestScope.Degree == "M.Tech" ? 'selected' : ''}>M.Tech</option>
							<option value="M.E"
								${requestScope.Degree == "M.E" ? 'selected' : ''}>M.E</option>
							<option value="MCA"
								${requestScope.Degree == "MCA" ? 'selected' : ''}>MCA</option>
						</select> <br> <br>
					</div>
					<div>
						<label>specialization:</label> <input class="input" type="text"
							name="specialization" 
							value="${Specialization}" required>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label>Year of Graduation:</label> <select id="year" name="year">
							<option value="2024"
								${requestScope.year == "2024" ? 'selected' : ''}>2024</option>
							<option value="2023"
								${requestScope.year == "2023" ? 'selected' : ''}>2023</option>
							<option value="2022"
								${requestScope.year == "2022" ? 'selected' : ''}>2022</option>
							<option value="2021"
								${requestScope.year == "2021" ? 'selected' : ''}>2021</option>
							<option value="2020"
								${requestScope.year == "2020" ? 'selected' : ''}>2020</option>
							<option value="2019"
								${requestScope.year == "2019" ? 'selected' : ''}>2019</option>
							<option value="2018"
								${requestScope.year == "2018" ? 'selected' : ''}>2018</option>
						</select> <br>
						<br>
					</div>
					
				</div>
				<br>
				<center>
					<div>

						<input type="submit" class="btn btn-primary" name="submit"
							value="Update">

					</div>
					<br>
				</center>

			</form>
			<!--    </div>    -->
		</div>
</body>
</html>