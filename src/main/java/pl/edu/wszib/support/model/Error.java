package pl.edu.wszib.support.model;

import javax.persistence.*;

@Entity(name = "error")
public class Error {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    private Application app;
    @Enumerated(EnumType.STRING)
    private Error.State state;
    private String title;
    private String description;

    public Error() {
    }

    public Error(int id, User user, Application app, State state, String title, String description) {
        this.id = id;
        this.user = user;
        this.app = app;
        this.state = state;
        this.title = title;
        this.description = description;
    }

    public enum State{
        NEW,
        ACTIVE,
        RESOLVED
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Application getApp() {
        return app;
    }

    public void setApp(Application app) {
        this.app = app;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        if(state!=State.NEW
                && state!=State.ACTIVE
                && state!=State.RESOLVED){
            state=State.NEW;
        }
        this.state = state;
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
}