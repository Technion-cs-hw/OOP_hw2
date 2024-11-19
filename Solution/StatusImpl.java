package OOP2.Solution;

import OOP2.Provided.Person;
import OOP2.Provided.Status;

import java.util.Set;

public class StatusImpl implements Status {
	public int id;
	public int likes;
	
	public String content; 
	
	public Person publisher;
	public Set<Person> liked;
	
	
	public StatusImpl(Person publisher, String content, Integer id) {
		this.publisher = publisher;
		this.content = content;
		this.id = id;
	}
	
	
	public Integer getId() {
		return id;
	}
	public Integer getLikesCount() {
		return likes;
	}
	public String getContent() {
		return content;
	}
	public Person getPublisher() {
		return publisher;
	}
	
	public void like(Person p) {
		/*
		boolean liked = false;
		
		
		for(Person current : this.liked) {
			if(current.getId() == p.getId())
				liked = true;
		}
		
		if(!liked) {
			(this.liked).add(p);
			this.likes++;
		}
		*/
		
		(this.liked).add(p);
	}
	public void unlike(Person p) {
		(this.liked).remove(p);
	}

	
	 @Override
	 public boolean equals(Object obj) {
		 if (this == obj) return true; // check if the objects are the same
	    
		 if (!(obj instanceof StatusImpl)) return false; // check if the parameter is of the correct type
		 
		 StatusImpl other = (StatusImpl) obj; // cast the parameter to the correct type
	     
		 return ((this.id == other.id) && 
	    		 ((this.publisher).getId() == (other.publisher).getId()));
	 }
}
