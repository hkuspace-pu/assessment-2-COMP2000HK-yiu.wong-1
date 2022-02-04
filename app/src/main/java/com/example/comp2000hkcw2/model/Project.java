package com.example.comp2000hkcw2.model;

public class Project {

    private Integer projectID;
    private Integer studentID;
    private String first_Name;
    private String second_Name;
    private String title;
    private String description;
    private Integer year;
    private String thumbnailURL;
    private String posterURL;
    private String photo;

    @Override
    public String toString() {
        return "Project{" +
                "projectID=" + projectID +
                ", studentID=" + studentID +
                ", first_Name='" + first_Name + '\'' +
                ", second_Name='" + second_Name + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", year=" + year +
                ", thumbnailURL='" + thumbnailURL + '\'' +
                ", posterURL='" + posterURL + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

    public Integer getProjectID() {
        return projectID;
    }

    public void setProjectID(Integer projectID) {
        this.projectID = projectID;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getSecond_Name() {
        return second_Name;
    }

    public void setSecond_Name(String second_Name) {
        this.second_Name = second_Name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
