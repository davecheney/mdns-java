package net.cheney.mdns;

public class CachedRecord extends DNSRecord {

	public CachedRecord(Type type, Class recordClass, String name, int ttl) {
		super(type, recordClass, name, ttl);
	}

}
