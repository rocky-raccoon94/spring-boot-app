package com.example.demo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
public class Post implements Serializable {
    @Serial
    private static final long serialVersionUID = 7156526077883281623L;
    @Id
    @SequenceGenerator(name="SEQ_GEN",sequenceName = "SEQ_POST",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_GEN")
    private Long Personid;
    private String Lastname;
    private String Firstname;
    private String Post;

    public Post() {
    }

    public Post(String lastname, String firstname, String post) {
        Lastname = lastname;
        Firstname = firstname;
        Post = post;
    }

    @Override
    public String toString() {
        return "Post{" + "Personid=" + Personid + ", Firstname='" + Firstname + '\''
                + ", Lastname='" + Lastname + '\'' + ", Post='" + Post + '\'' + '}';
    }
}
