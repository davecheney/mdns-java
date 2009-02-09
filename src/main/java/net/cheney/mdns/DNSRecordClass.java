package net.cheney.mdns;

public enum DNSRecordClass {

	IN(1),
	CS(2),
	CH(3),
	HS(4),
	
	ANY(255)
	;
	
	private final short value;

	DNSRecordClass(int value) {
		this.value = (short) value;
	}
	
	public static DNSRecordClass parse(short i) {
		for(DNSRecordClass t : values()) {
			if(t.value == (i & 0x00ff)) return t;
		}
		return null;
	}

	public short value() {
		return value;
	}
}
