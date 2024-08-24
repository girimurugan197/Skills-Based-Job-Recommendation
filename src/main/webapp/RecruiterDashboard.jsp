<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recruiter Dashboard</title>
    <link rel="stylesheet" type="text/css" href="RecruiterDashboard.css">
    
</head>
<body>
<div class="navcontainer">
<nav>
    <ul>
    	<li style="float: left;margin-left:10px;">
            <p class="FirstName">Welcome!! ${FirstName}&nbsp; ${LastName}</p>
        </li>
        <li style="float: right;"><a href="#">Home</a></li>
        <li style="float: right;" class="dropdown">
            <a href="#" class="dropbtn">Services</a>
            <div class="dropdown-content">
                <a href="javascript:togglePopup();">Post a Job</a>
                <a href="JobActions">Actions on Job</a>
                 <a href="ViewJobs">View Posted Jobs</a>
                <a href=Welcome.html>Logout</a>
               
            </div>
        </li >
        <li style="float: right;"><a href="RecruiterProfileServlet">Profile</a></li>
    </ul>
</nav>
</div>
<br>
<br>


<div class="container">
    <div class="subdiv">
        <p id="post" class="hand-icon">POST JOB</p>
        <button class="card1" onclick="togglePopup()"></button>
    </div>
    <div class="subdiv">
        <p id="action" class="hand-icon">ACTIONS ON JOB</p>
        <button class="card2" onclick="JobAction()"></button>
    </div>
    <div class="subdiv">
        <p id="jobs" class="hand-icon">POSTED JOBS</p>
        <button class="card3" onclick="ViewAllJobs()"></button>
    </div>
</div>


<div class="popup" id="jobPostPopup" style="overflow-y:auto;">
    <div class="jobpost">
        <form action="PostJobServlet" method="post">
        	<label for="JobId">JobId:</label>
        	<input type="text" id="JobId" name="JobId" required><br><br>
        
            <label for="JobRole">Job Role:</label>
            <input type="text" id="JobRole" name="JobRole" required><br><br>
            
            <label for="RequiredSkills">Required Skills:</label>
            <input type="text" id="RequiredSkills" name="RequiredSkills" required><br><br>
            
            <label for="JobDescription">Job Description:</label>
            <input type="text" id="JobDescription" name="JobDescription" required><br><br>
            
            <label for="Experience">Experience:</label>
            <input type="text" id="Experience" name="Experience" required><br><br>
            
            
            <input type="hidden" id="RecruiterId" name="RecruiterId" value="${RecruiterId}" required>
            
            <label for="Location">Location:</label>
            <input type="text" id="Location" name="Location" required><br><br>
            
            <label for="Company">Company/Organization:</label>
            <input type="text" id="Company" name="Company" required><br><br>
            
            <label for="RecruiterMail">Recruiter Mail:</label>
            <input type="email" id="RecruiterMail" name="RecruiterMail" required><br><br>
            
            <label for="RecruiterMobile">Recruiter Mobile:</label>
            <input type="tel" id="RecruiterMobile" name="RecruiterMobile" required><br><br>
            
            <label for="CareerPageLink">Career Page Link:</label>
            <input type="url" id="CareerPageLink" name="CareerPageLink"><br><br>
           <div class="btn"> 
            <input type="submit" value="Submit">
            <input type="button" value="close" class="close-btn" onclick="togglePopup()">
            </div>
        </form>
    </div>
</div>



<script>
    function togglePopup() {
        var popup = document.getElementById("jobPostPopup");
        popup.style.display = (popup.style.display === "block") ? "none" : "block";
        popup.scrollTop=0;
    }
    function JobAction(){
    	window.location.href="JobActions";
    }
    function ViewAllJobs(){
    	window.location.href="ViewJobs";
    }
</script>
<script>
    function togglePopupActions() {
        var popup = document.getElementById("Actions");
        popup.style.display = (popup.style.display === "block") ? "none" : "block";
        popup.scrollTop=0;
    }
</script>
</body>
</html>
