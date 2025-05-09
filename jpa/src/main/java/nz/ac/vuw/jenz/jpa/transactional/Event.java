package nz.ac.vuw.jenz.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

/**
 * Simple domain class with some ORM annotations.
 * @author jens dietrich
 */
@Entity
@Table(name = "Events")
public class Event {

    public Event() {
    }

    public Event(String title) {
        this.title = title;
    }

    @Id @GeneratedValue private Long id; // primary key

    private String title;

    public Long getId() {
        return id;
    }

    // not required and should be avoided as ids are generated and strictly read-only
    // used here to test what happens if this is ignored
    void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Event{" +
            "id=" + id +
            ", title='" + title + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) && Objects.equals(title, event.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}


