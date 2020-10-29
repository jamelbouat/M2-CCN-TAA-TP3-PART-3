package sample.data.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Kanban implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private String name;
    private List<Section> sections = new ArrayList<Section>();

    public Kanban() {
    }

    public Kanban(String name, List<Section> sections) {
        this.name = name;
        this.sections = sections;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "kanban")
    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}