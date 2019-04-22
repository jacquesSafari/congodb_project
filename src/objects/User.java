
package objects;

import java.util.List;

public class User {
    
    private int             id;
    private String          name;
    private String          password;
    private String          mail;
    private List<Database>  databases;	
    
    public User() {}
    
    public User(int id, String name, String password) {
    	this.id         = id;
    	this.name       = name;
    	this.password   = password;
    }
    
    public User(int id, String name, String password, String mail) {
    	this.id         = id;
    	this.name       = name;
    	this.password   = password;
    	this.mail       = mail;
    }
    
    public User(int id, String name, String password, String mail, List<Database> databases) {
    	this.id         = id;
    	this.name       = name;
    	this.password   = password;
    	this.mail       = mail;
    	this.databases  = databases;
    }
    
    @Override
    public String toString() {
        String str = "";
        try{
            str = "Name : " + this.name +
                  ", Password : " + this.password + 
                  ", Mail : " + this.mail;
            for(int i = 0; i < databases.size(); i++)
                    str +=  ", Database : " + databases.get(i).getName();
        }catch(NullPointerException e){
                e.printStackTrace();
        }	
        return  str;
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
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public List<Database> getDatabases() {
		return databases;
	}

	public void setDatabases(List<Database> databases) {
		this.databases = databases;
	}
}
