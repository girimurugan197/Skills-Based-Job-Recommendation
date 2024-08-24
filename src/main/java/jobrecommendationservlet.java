
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/jobrecommendationservlet")
public class jobrecommendationservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	String Email = (String) session.getAttribute("Email");
        List<Job> jobData = loadJobData("A:\\job.csv"); // Replace with your actual file path

        List<String> skills = Arrays.asList(request.getParameter("skills").split(","))
                                    .stream()
                                    .map(String::trim)
                                    .collect(Collectors.toList());

        int experience = Integer.parseInt(request.getParameter("experience"));

        if (skills.size() < 2) {
            // Alert for fewer than 4 skills
            response.setContentType("text/html");
            response.getWriter().println("<html><head><title>Alert</title>");
            response.getWriter().println("<style>");
            response.getWriter().println(".alert { padding: 20px; background-color: #f44336; color: white; text-align: center; }"); // Red alert
            response.getWriter().println("</style></head><body>");
            response.getWriter().println("<div class='alert'>Please enter at least 3 skills.</div>");
            response.getWriter().println("</body></html>");
            return;
        }

        String[] locationsArray = request.getParameter("locations").split(",");
        List<String> locations = Arrays.asList(locationsArray)
                                    .stream()
                                    .map(String::trim)
                                    .collect(Collectors.toList());

        List<Job> recommendedJobs = recommendJobs(jobData, skills, locations, experience);

        if (recommendedJobs.isEmpty()) {
            // Alert for no jobs found
            response.setContentType("text/html");
            response.getWriter().println("<html><head><title>Alert</title>");
            response.getWriter().println("<style>");
            response.getWriter().println(".alert { padding: 20px; background-color: #f44336; color: white; text-align: center; }"); // Red alert
            response.getWriter().println("</style></head><body>");
            response.getWriter().println("<div class='alert'>No jobs match your criteria.</div>");
            response.getWriter().println("</body></html>");
            return;
        }

        // Print recommended jobs or inform user if no matches found
        if (!recommendedJobs.isEmpty()) {
            response.setContentType("text/html");
            response.getWriter().println("<html><head><title>Job Recommendations</title>");
            response.getWriter().println("<style>");
            response.getWriter().println("table { width: 95%; margin: 10px auto; border-collapse: collapse; border-radius: 10px; overflow: hidden; box-shadow: 0 0 20px rgba(0, 0, 0, 0.1); }"); // Set table width, margins, border collapse, border radius, overflow, and box shadow
            response.getWriter().println("th, td { padding: 15px; text-align: left; border-bottom: 1px solid #ddd; }"); // Set padding, alignment, and border
            response.getWriter().println("th { background-color: #4CAF50; color: white; }"); // Green background color for table headers
            response.getWriter().println("tr:nth-child(even) { background-color: #f2f2f2; }"); // Light gray background color for even table rows
            response.getWriter().println("tr:hover { background-color: #ddd; }"); // Dark gray background color on hover
            response.getWriter().println("</style></head><body>");
            response.getWriter().println("<h2 style='text-align: center;'>Job Recommendations</h2>"); // Centered header
            response.getWriter().println("<table>");
            response.getWriter().println("<tr><th>Title</th><th>Skills</th><th>Job Description</th><th>Experience</th><th>Location</th><th>Company</th><th>Recruiter Mail</th><th>Job ID</th><th>Apply</th></tr>");
            for (Job job : recommendedJobs) {
                response.getWriter().println("<tr><td>" + job.getTitle() + "</td><td>" + String.join(", ", job.getSkills()) + "</td><td>" + job.getDescription() + "</td><td>" + job.getExperience() + "</td><td>" + job.getLocation() + "</td><td>" + job.getCompany() + "</td><td>" + job.getRecruiterMail() + "</td><td>" + job.getJobId() + "</td><td><button id='applyButton_" + job.getJobId() + "' onclick=\"applyJob('" + Email + "', '" + job.getJobId() + "', '" + job.getRecruiterId() +  "', '" + job.getRecruiterMail() + "', '" + experience + "', '" + String.join(",", skills) + "', '" + job.getLocation() + "')\">Apply</button></td></tr>");
            }

            response.getWriter().println("</table>");
            response.getWriter().println("<script>");
            response.getWriter().println("function applyJob(jobTitle, jobId, recruiterId, recruiterMail, experience, skills, location) {");
            response.getWriter().println("var xhr = new XMLHttpRequest();");
            response.getWriter().println("xhr.open('POST', 'applyjob', true);");
            response.getWriter().println("xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');");
            response.getWriter().println("xhr.onreadystatechange = function() {");
            response.getWriter().println("if (xhr.readyState == 4 && xhr.status == 200) {");
            response.getWriter().println("alert(xhr.responseText);"); // Show response from servlet
            response.getWriter().println("document.getElementById('applyButton_' + jobId).innerText = 'Applied';"); // Update button text
            response.getWriter().println("document.getElementById('applyButton_' + jobId).disabled = true;");
            response.getWriter().println("}");
            response.getWriter().println("};");
            response.getWriter().println("var params = 'jobId=' + encodeURIComponent(jobId) + '&recruiterId=' + encodeURIComponent(recruiterId) +'&RecruiterMail=' + encodeURIComponent(recruiterMail)+ '&email=' + encodeURIComponent('" + getEmailFromSession(request) + "') + '&skills=' + encodeURIComponent(skills) + '&experience=' + encodeURIComponent(experience) + '&location=' + encodeURIComponent(location);");
            response.getWriter().println("xhr.send(params);");

            response.getWriter().println("}");
            response.getWriter().println("</script>");
            response.getWriter().println("</body></html>");
        }
    }

    // Helper method to get email from session
    private String getEmailFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("Email");
        return email != null ? email : "";
    }

    // Method to load job data from CSV file
    private List<Job> loadJobData(String filePath) {
        List<Job> jobData = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true; // Skip header
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length >= 9) { // Ensure all attributes are present
                    String title = parts[0].trim();
                    List<String> skills = Arrays.asList(parts[1].split(";")).stream()
                            .map(String::trim)
                            .collect(Collectors.toList());
                    String description = parts[2].trim().isEmpty() ? "N/A" : parts[2].trim(); // Default description to "N/A" if empty
                    int experience = parseExperience(parts[3]);
                    String location = parts[4].trim();
                    String company = parts[5].trim();
                    String recruiterId = parts[6].trim();
                    String recruiterMail = parts[7].trim().isEmpty() ? "N/A" : parts[7].trim(); // Default recruiterMail to "N/A" if empty
                    String jobId = parts[9].trim().isEmpty() ? "N/A" : parts[9].trim(); // Default jobId to "N/A" if empty
                    jobData.add(new Job(title, skills, description, experience, location, company, recruiterMail, jobId, recruiterId));
                } else {
                    System.err.println("Invalid data format in CSV line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading job data from CSV: " + e.getMessage());
        }
        return jobData;
    }

    // Method to parse experience, handling non-integer values
    private int parseExperience(String experienceStr) {
        try {
            return Integer.parseInt(experienceStr.trim());
        } catch (NumberFormatException e) {
            // If experience is not a valid integer, return a default value (e.g., 0)
            return 0;
        }
    }

    // Method to recommend jobs based on criteria
    private List<Job> recommendJobs(List<Job> jobData, List<String> skills, List<String> locations, int experience) {
        List<Job> recommendedJobs = new ArrayList<>();
        for (Job job : jobData) {
            List<String> jobSkills = job.getSkills().stream()
                                            .map(String::toLowerCase)
                                            .collect(Collectors.toList());
            List<String> lowerCaseSkills = skills.stream()
                                                 .map(String::toLowerCase)
                                                 .collect(Collectors.toList());
            int matchedSkills = 0;
            for (String skill : lowerCaseSkills) {
                if (jobSkills.contains(skill)) {
                    matchedSkills++;
                }
            }
            if (matchedSkills >= lowerCaseSkills.size() && job.getExperience() <= experience && locations.contains(job.getLocation())) {
                recommendedJobs.add(job);
            }
        }
        return recommendedJobs;
   }
}
