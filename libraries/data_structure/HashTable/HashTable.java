public interface HashTable extends Container
{
	void insert(Object key, Object value);
	void remove(Object key);
	Object find(Object key);
}