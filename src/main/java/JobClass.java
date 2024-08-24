
public class JobClass {
    private String jobId;
    private String applicantSkills;
    private String firstName;
    private String lastName;
    private String mobile;
    private String university;
    private String degree;
    private String passoutYear;
    private String email;
    private int experience;
    private String jobLocation;

    public JobClass(String jobId, String applicantSkills, String firstName, String lastName, String mobile, String university, String degree, String passoutYear, String email, int experience, String jobLocation) {
        this.jobId = jobId;
        this.applicantSkills = applicantSkills;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.university = university;
        this.degree = degree;
        this.passoutYear = passoutYear;
        this.email = email;
        this.experience = experience;
        this.jobLocation = jobLocation;
    }

    // Getters and setters
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getApplicantSkills() {
        return applicantSkills;
    }

    public void setApplicantSkills(String applicantSkills) {
        this.applicantSkills = applicantSkills;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getPassoutYear() {
        return passoutYear;
    }

    public void setPassoutYear(String passoutYear) {
        this.passoutYear = passoutYear;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }
}
