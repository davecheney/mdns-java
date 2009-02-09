package net.cheney.mdns;

public final class DNSQuery extends DNSMessage {

	private final String name;
	private final DNSRecordType type;
	private final DNSRecordClass clazz;

	public DNSQuery(String name, DNSRecordType type, DNSRecordClass clazz) {
		this.name = name;
		this.type = type;
		this.clazz = clazz;
	}
	
	@Override
	public String toString() {
		return String.format("%s [type %s, class %s]", name, type, clazz);
	}
	
	public String name() {
		return name;
	}
	
	public DNSRecordType type() {
		return type;
	}

	public DNSRecordClass clazz() {
		return clazz;
	}

}
