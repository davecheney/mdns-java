package net.cheney.mdns;

public class CachedRecord extends Record {

	public CachedRecord(Type type, Class recordClass, String name, int ttl) {
		super(type, recordClass, name, ttl);
	}

}
