

import java.util.List;

public class Job {
    private String title;
    private List<String> skills;
    private String description;
    private int experience;
    private String location;
    private String company;
    private String recruiterMail;
    private String recruiterMobile;
    private String jobId;
	private String recruiterId;

    public Job(String title, List<String> skills, String description, int experience, String location, String company, String recruiterMail,  String jobId, String recruiterId) {
        this.title = title;
        this.skills = skills;
        this.description = description;
        this.experience = experience;
        this.location = location;
        this.company = company;
        this.recruiterId = recruiterId;
        this.recruiterMail = recruiterMail;
        this.recruiterMobile = recruiterMobile;
        this.jobId = jobId;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getSkills() {
        return skills;
    }

    public String getDescription() {
        return description;
    }

    public int getExperience() {
        return experience;
    }

    public String getLocation() {
        return location;
    }

    public String getCompany() {
        return company;
    }
    public String getRecruiterId() {
        return recruiterId;
    }

    public String getRecruiterMail() {
        return recruiterMail;
    }

   

    public String getJobId() {
        return jobId;
    }
}

