package edu.utsa.activitiesandviews;

public class Account {

    private int id;
    private String name;
    private String email;
    private String age;
    private String displayedUname;
    private String profilePic;

    public Account(int id, String name, String email, String age, String displayedUname, String profilePic) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.displayedUname =displayedUname;
        this.profilePic =profilePic;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getAge() { return age; }
    public void setAge(String age) {this.age = age; }

    public String getDisplayedUname() { return displayedUname; }
    public void setDisplayedUname(String displayedUname) {this.displayedUname = displayedUname; }

    public String getProfilePic() { return profilePic; }
    public void setProfilePic(String profilePic) { this.profilePic = profilePic; }

}
