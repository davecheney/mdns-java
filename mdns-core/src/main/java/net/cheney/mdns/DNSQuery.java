package net.cheney.mdns;

public final class DNSQuery extends DNSMessage {

	private final String name;
	private final DNSRecord.Type type;
	private final DNSRecord.Class clazz;

	public DNSQuery(String name, DNSRecord.Type type, DNSRecord.Class clazz) {
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
	
	public DNSRecord.Type type() {
		return type;
	}

	public DNSRecord.Class clazz() {
		return clazz;
	}

}
