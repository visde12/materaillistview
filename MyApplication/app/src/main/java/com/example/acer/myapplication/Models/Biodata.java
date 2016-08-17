package com.example.acer.myapplication.Models;

/**
 * Created by Acer on 17-08-2016.
 */
public class Biodata {
   // {"id":1,"first_name":"Harry","last_name":"Hughes",
        //     "email":"hhughes0@dot.gov","gender":"Male","imageurl":"http://dummyimage.com/126x139.png/dddddd/000000"},
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String gender;
    private String imageurl;


    public Biodata(String id,String firstname, String lastname, String email, String gender, String imageurl) {
      this.id =id;
      this.firstname=firstname;
      this.lastname=lastname;
      this.email=email;
      this.gender=gender;
      this.imageurl=imageurl;

    }

    //Adding get and set methods
    public String setId(String id) {

        return this.id=id;
    }

    public String getId() {

        return id;
    }

    public void setFName(String fname) {

        this.firstname = fname;
    }


    public String getFName()
    {
        return firstname;
    }

    public void setLName(String lname) {

        this.lastname = lname;
    }
    public String getLName() {

        return lastname;
    }

    public void setEmail(String email) {

        this.email = email;
    }
    public String getEmail()
    {
        return email;
    }

    public void setGender(String gender) {

        this.gender = gender;
    }
    public String getGender()
    {
        return gender;
    }

    public void setImageurl(String imageurl) {

        this.imageurl = imageurl;
    }
    public String getImageUrl() {

        return imageurl;
    }




}
