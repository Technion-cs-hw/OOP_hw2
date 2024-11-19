package OOP2.Solution;

import OOP2.Provided.*;

import java.util.*;

public class FaceOOPImpl implements FaceOOP {

	/**
	 * Constructor - receives no parameters and initializes the system.
	 */
	List<Person> peopleInSystem;

	public FaceOOPImpl()
	{
		this.peopleInSystem =new LinkedList<>();
	}

	@Override
	public Person joinFaceOOP(Integer id, String name) throws PersonAlreadyInSystemException {
		Person p = new PersonImpl(id,name);

		if(this.peopleInSystem.contains(p))
		{
			throw new PersonAlreadyInSystemException();
		}

		this.peopleInSystem.add(p);

		return p;
	}

	@Override
	public int size() {
		return this.peopleInSystem.size();
	}

	@Override
	public Person getUser(Integer id) throws PersonNotInSystemException {


		for(Person p: this.peopleInSystem)
		{
			if(p.getId() == id )
				return p;
		}
		throw new PersonNotInSystemException();
	}

	@Override
	public void addFriendship(Person p1, Person p2) throws PersonNotInSystemException, SamePersonException, ConnectionAlreadyExistException {
		if(!this.peopleInSystem.contains(p1) || !this.peopleInSystem.contains(p2)){
			throw new PersonNotInSystemException();
		}

		try{
			p1.addFriend(p2);
			p2.addFriend(p1);
		}
		catch (Exception e)
		{
			throw e;
		}

	}

	@Override
	public StatusIterator getFeedByRecent(Person p) throws PersonNotInSystemException {
		if(!this.peopleInSystem.contains(p) ){
			throw new PersonNotInSystemException();
		}
		return null;
	}

	@Override
	public StatusIterator getFeedByPopular(Person p) throws PersonNotInSystemException {
		if(!this.peopleInSystem.contains(p) ){
			throw new PersonNotInSystemException();
		}
		return null;
	}

	@Override
	public Integer rank(Person source, Person target) throws PersonNotInSystemException, ConnectionDoesNotExistException {

		if(!this.peopleInSystem.contains(source) || !this.peopleInSystem.contains(target)){
			throw new PersonNotInSystemException();
		}

		int depth = 0;
		LinkedList<Person> Visiting = new LinkedList<Person>( );
		LinkedList<Person> toVisit = new LinkedList<Person>( );
		toVisit.add(source);

		PersonImpl currPers;

		while (!toVisit.isEmpty() )
		{
			Visiting = toVisit;
			toVisit = new LinkedList<Person>( );
			while(!Visiting.isEmpty())
			{
				currPers =(PersonImpl)Visiting.pollFirst();
				currPers.setRank(depth);
				if(currPers.equals(target))
				{
					for (Person p: this.peopleInSystem) {
						currPers = (PersonImpl) p;
						currPers.setRank(-1);
					}
					return depth;
				}
				for (Person p: currPers.getFriends()) {
					currPers = (PersonImpl) p;
					if(currPers.getRank() == -1)
						toVisit.add(p);
				}


			}
		}


		throw new ConnectionDoesNotExistException();
	}

	@Override
	public Iterator<Person> iterator() {
		return null;
	}
}
