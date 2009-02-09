package net.cheney.mdns;

public class DNSRecord {

	private final String name;
	private final DNSRecordType type;
	private final DNSRecordClass clazz;
	private final int ttl;

	public DNSRecord(DNSRecordType type, DNSRecordClass recordClass, String name, int ttl) {
		this.name = name;
		this.type = type;
		this.clazz = recordClass;
		this.ttl = ttl;
	}
	
	@Override
	public String toString() {
		return String.format("%s ttl: %d [type %s, clazz %s]", name, ttl, type, clazz);
	}
	
	public final String name() {
		return name;
	}
	
	public final DNSRecordType type() {
		return type;
	}
	
	public final DNSRecordClass clazz() {
		return clazz;
	}

}
