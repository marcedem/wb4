package tuwien.big.formel0.entities;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import javax.persistence.*;
import picasa.Avatar;
import picasa.AvatarControl;

@ManagedBean(name = "player")
@NoneScoped
@Entity
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    private String firstname = null;
    
    private String lastname = null;
    private String name = null;
    private String password = null;
    private String birthday = null;
    private String sex = null;
    
    //private String newavatar;
    
    
    
    /**
     * Creates a new instance of Player
     */
    public Player() {
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    
   /** public void setNewavatar(String newavatar) {
		this.newavatar = newavatar;
	}

	public String getNewavatar() {
		return this.newavatar;
	}

	public void setAvatardesc(String avatar) {
		setNewavatar(AvatarControl.getAvatar(avatar));
	}

	public String getAvatardesc() {
		if (this.newavatar != null) {
			return this.newavatar.getDescription();
		}
		return "kein Avatar";
	}

	public String getAvatar() {
		return this.newavatar;
	}

	public void setAvatarurl(String url) {
		this.newavatar.setUrl(url);
	}

	public String getAvatarurl() {
		return this.avatar.getUrl();
	}
    **/
}
