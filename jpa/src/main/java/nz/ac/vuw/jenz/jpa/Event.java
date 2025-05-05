package nz.ac.vuw.jenz.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Events")
public class Event {

    public Event() {
    }

    public Event(String title) {
        this.title = title;
    }

    @Id
    @GeneratedValue
    // @Column(name = "eventDate")   // optional
    private Long id;

    private String title;

    @Override
    public String toString() {
        return "Event{" +
            "id=" + id +
            ", title='" + title + '\'' +
            '}';
    }
}


