<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Dashboard</title>
<link rel="stylesheet" type="text/css" href="UserDashBoard.css">
</head>
<body>
<div class="navcontainer">
<nav>
<ul>
    
    <li><a href="#Home">Home</a></li>
    <li><a href="CandidateProfileServlet">Profile</a></li>
    <li  class="dropdown">
        <a href="#" class="dropbtn">Services</a>
        <div class="dropdown-content">
            <a href="javascript:openForm();">SearchJob</a>
             <a href="ViewAppliedJobs">View Applied Jobs</a>
            <a href=Welcome.html>Logout</a>
           
        </div>
    </li >
    <li><a href="#" id="careerLink">Career Development</a></li>
</ul>
</nav>
</div>
<div class="container2">
    <p class="welcome">WELCOME ${FirstName}&nbsp;${LastName}</p>
    
</div>

<div class="overlay" id="overlay"></div>
<div class="popup" id="popup">
<span class="close" onclick="closePopup()">&times;</span>
<h2>Explore Your Desired Learning Path</h2>

<p>Learning path: <a href="https://roadmap.sh/frontend" target="_blank">Front-end Developer</a></p>
<p>Learning path: <a href="https://roadmap.sh/backend" target="_blank">Back-end Developer</a></p>
<p>Learning path: <a href="https://roadmap.sh/devops" target="_blank">Devops</a></p>
<p>Learning path: <a href="https://roadmap.sh/android" target="_blank">Android Developer</a></p>
<p>Learning path: <a href="https://roadmap.sh/flutter" target="_blank">Flutter Developer</a></p>
<p>Learning path: <a href="https://roadmap.sh/cyber-security" target="_blank">Cyber-Security</a></p>
<p>Learning path: <a href="https://roadmap.sh/data-analyst" target="_blank">Data-Analyst</a></p>
<p>Learning path: <a href="https://roadmap.sh/full-stack" target="_blank">Full Stack Developer</a></p>
<!-- <button onclick="closePopup()">Close</button> -->
</div>
<br>
<br>
<div class="container">
    <div class="subdiv">
        <p id="post" class="hand-icon">PROFILE</p>
        <button class="card1" onclick="location.href='CandidateProfileServlet'"></button>
    </div>
    <div class="subdiv">
        <p id="action" class="hand-icon">SEARCH JOB</p>
        <button class="card2" onclick="openForm()"></button>
    </div>
    <div class="subdiv">
        <p id="jobs" class="hand-icon">APPLIED JOBS</p>
        <button class="card3" onclick="ViewAppliedJobs()"></button>
    </div>
</div>
<!-- Modal content for the form -->
<div id="formModal" class="modal">
<div class="modal-content">
<span class="close" onclick="closeForm()">&times;</span>
<form id="myForm" action="jobrecommendationservlet" method="post">
<label for="skills">Skills:</label>
<input type="text" id="skills" name="skills" value="${Skills}"  required>
<br>
<label for="experience">Experience:</label>
<input type="text" id="experience" name="experience" value="${Experience}" required>
<br>
<label for="locations">Locations:</label>
<input type="text" id="locations" name="locations" value="${Location}" required>
<br>
<input type="submit" value="RECOMMEND">
</form>

</div>
</div>
<script>
function openForm() {
  var modal = document.getElementById("formModal");
  modal.style.display = "block";
}

function closeForm() {
  var modal = document.getElementById("formModal");
  modal.style.display = "none";
}


function showPopup() {
    document.getElementById("overlay").style.display = "block";
    document.getElementById("popup").style.display = "block";
}

// Function to close the popup
function closePopup() {
    document.getElementById("overlay").style.display = "none";
    document.getElementById("popup").style.display = "none";
}
function ViewAppliedJobs(){
	window.location.href="ViewAppliedJobs";
}

// Attach click event listener to the "Career Development" link
document.getElementById("careerLink").addEventListener("click", function(event) {
    event.preventDefault(); // Prevent the default behavior of the link
    showPopup(); // Show the popup
});
</script>

</body>
</html>
