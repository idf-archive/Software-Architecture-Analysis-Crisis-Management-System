package safeguard;

public interface DAO<K,E> {

    void remove(E entity);
    E find(Class a, int id);
	void persist(E entity);
}



