<html>
    <head>
    	<meta charset="utf-8">
	    <title> Recruiter Register</title>
        <!-- <link rel="stylesheet" type="text/css" href="adminhome.css"> -->

	    <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous">
       </script>

        <style class="input" type="text/css">
             form{
                padding: 10px;
                margin-left: 50px;
                margin-bottom: 20px;
                margin-top: 10px;
                background: linear-gradient(45deg, #D98EFF,#8ED6FF,#E0FF8E,#FFCF8E);
                background-size: 100% 400%;
                animation:  animateGradient 5s ease-in-out infinite;
                animation-delay: 2s;
                border-radius: 20px;
            }
            label {
                margin-right:10px;
                width: 150px; /* Adjust this value to the desired width of your labels */
                text-align: right;
                padding-right: 10px;
                padding-bottom: 10px;
            }
            #header{
                position:fixed;
                top:0px;
                width: 100%;
            }
            input[type="text"],input[type="number"],input[type="email"]{
	
	height: 25px;
	width: 250px;
	font-size: 15px;
	margin-bottom: 20px;
	background-color: #fff;
	padding-left: 5px;
}
            #skills{
                 margin-left:0px;
                 margin-top:2px;
            }

            @keyframes animateGradient { 
                0%   {background-position: 0 0;}
                50%  {background-position: 0 100%;}
                100% {background-position: 0 0;}
            }

            
            
        </style> 
	</head>

<body style="background-color:grey">

	<br>
	<br>
	<div>
		<br>
        <center>
		<h1 >
			<b style="color:white">RECRUITER REGISTRATION</b>
		</h1>
		</center>
		<div class="container">
			<form action="RECRUITERSUBMIT" method="post" >
				<br>

				<!--  <div style="margin-left:20px;margin-top:10px;font-size:30px"> Student's Marks Information </div> -->
				<!--  <h3 style="margin-left: 50px;">FirstName</h3> -->
				<div>
					<div>
						<label>FirstName:</label> <input class="input" type="text"
							name="FirstName" max="20" value="${FirstName}" readonly required>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label>LastName:</label> <input class="input" type="text"
							name="LastName" max="20" required><br><br>
					</div>
					<div>
						<label>Email:</label> 
						 <input class="input" id="email" type="email" name="Email" value="${email}" readonly required>
						  
						 
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label>Mobile No:</label> <input class="input" type="tel"
							name="numericField" maxlength="10" pattern="[0-9]{10}" title="Please include only numbers in this field" required><br><br>
					</div>
					<div>
						<label>Company/Organization:</label> <input class="input" type="text"
							name="Company" required>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label>Recruiter Id:</label> <input type="text id="RecruiterId" name="RecruiterId" value="" placeholder="Enter your id">
							
						<br>
						<br>
					</div>
					
					<div>
						<label>Location:</label> <input class="input" type="text"
							name="Location" placeholder="Location"required>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						
					
					</div>
				</div>
				<br>
				<center>
					<div>

						<input type="submit" class="btn btn-primary" name="submit"
							value="SUBMIT">

					</div>
					<br>
				</center>
				
			</form>
			<!--    </div>    -->
		</div>
			

</body>
</html>