package OOP2.Solution;

import OOP2.Provided.ConnectionAlreadyExistException;
import OOP2.Provided.Person;
import OOP2.Provided.SamePersonException;
import OOP2.Provided.Status;

import java.util.*;


public class PersonImpl implements Person {


	Integer id;
	String name;

	int nextStatusId;

	Vector<Person> friends;
	Vector<Status> statuses;

	int rank;
	static class popularityComparator implements Comparator<Status>{

		@Override
		public int compare(Status o1, Status o2) {
			if(o1.getLikesCount() == o2.getLikesCount())
			{
				return o2.getId()- o1.getId();
			}

			return o1.getLikesCount()- o2.getLikesCount();
		}
	}
	static class recentComparator implements Comparator<Status> {
		@Override
		public int compare(Status o1, Status o2) {
			return o2.getId() - o1.getId();
		}
	}

	/**
	 * Constructor receiving person's id and name.
	 */
	public PersonImpl(Integer id, String name)
	{
		this.id=id;
		this.name=name;

		this.friends = new Vector<Person>();
		this.statuses = new Vector<>();

		this.nextStatusId = 0;

		this.rank = -1;
	}

	@java.lang.Override
	public Integer getId() {
		return this.id;
	}

	@java.lang.Override
	public String getName() {
		return name;
	}

	@java.lang.Override
	public Status postStatus(String content) {

		Status newStatus = new StatusImpl(this, content,this.nextStatusId);
		nextStatusId++;
		this.statuses.add(newStatus);

		return newStatus;
	}

	@java.lang.Override
	public void addFriend(Person p) throws SamePersonException, ConnectionAlreadyExistException {
		if(this == p)
		{
			throw new SamePersonException();

		}
		if(this.friends.contains(p))
		{
			throw new ConnectionAlreadyExistException();
		}
	}

	@java.lang.Override
	public Collection<Person> getFriends() {
		return this.friends;
	}

	@java.lang.Override
	public Iterable<Status> getStatusesRecent() {


		Collections.sort(this.statuses,new recentComparator());

		return this.statuses;
	}

	@java.lang.Override
	public Iterable<Status> getStatusesPopular() {
		Collections.sort(this.statuses,new popularityComparator());

		return this.statuses;
	}

	@Override
	public int compareTo(Person o) {
		return this.id-o.getId();
	}

	@Override
	public boolean equals(Object o)
	{
		if(o.getClass() == this.getClass())
		{
			if(this.id == ((Person) o).getId())
			{
				return true;
			}
		}
		return false;

	}

	public void setRank(int rank){this.rank = rank;}
	public int getRank() {return  this.rank;}

}
