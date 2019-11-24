package wms.model.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
    @ManyToMany(mappedBy = "roles", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<User> users;
	
	public Role() {
		super();
		this.users = new HashSet<>();
	}
	
	public Role(String name) {
		super();
        this.name = "ROLE_"+name;
		this.users = new HashSet<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = "ROLE_"+name;
	}

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
	
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}


	
	
}
