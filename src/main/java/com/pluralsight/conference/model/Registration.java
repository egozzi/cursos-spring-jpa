package com.pluralsight.conference.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "REGISTRATIONS")
@NamedQueries({
        @NamedQuery(name = Registration.REGISTRATION_REPORT, query = Registration.REGISTRATION_REPORT_JPQL)
})
public class Registration {

    public static final String REGISTRATION_REPORT = "registrationReport";

    public static final String REGISTRATION_REPORT_JPQL = "select new com.pluralsight.conference.model.RegistrationReport" +
            "(r.name, c.name, c.description) " +
            "from Registration r, Course c " +
            "where r.id = c.registration.id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Column(name = "NAME")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "registration", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Course> courses = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
