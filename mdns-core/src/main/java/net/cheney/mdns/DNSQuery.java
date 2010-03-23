package net.cheney.mdns;

public final class DNSQuery extends DNSMessage {

	private final String name;
	private final Record.Type type;
	private final Record.Class clazz;

	public DNSQuery(String name, Record.Type type, Record.Class clazz) {
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
	
	public Record.Type type() {
		return type;
	}

	public Record.Class clazz() {
		return clazz;
	}

}
