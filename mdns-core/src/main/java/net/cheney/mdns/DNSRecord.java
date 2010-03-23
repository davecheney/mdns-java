package net.cheney.mdns;

public abstract class DNSRecord {

	private final String name;
	private final Type type;
	private final Class clazz;
	private final int ttl;
	
	public DNSRecord(Type type, Class recordClass, String name, int ttl) {
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
	
	public final Type type() {
		return type;
	}
	
	public final Class clazz() {
		return clazz;
	}

	public enum Class {

		IN(1),
		CS(2),
		CH(3),
		HS(4),
		
		ANY(255)
		;
		
		private final short value;

		Class(int value) {
			this.value = (short) value;
		}
		
		public static Class parse(short i) {
			for(Class t : values()) {
				if(t.value == (i & 0x00ff)) return t;
			}
			return null;
		}

		public short value() {
			return value;
		}
	}
	
	// see http://en.wikipedia.org/wiki/List_of_DNS_record_types

	public enum Type {

		A(1),
		AAAA(28),
		AFSDB(18),
		CERT(37),
		DHCID(49),
		DLV(32769),
		DNAME(39),
		DNSKEY(48),
		DS(43),
		HIP(55),
		IPSECKEY(45),
		KEY(25),
		LOC(29),
		NAPTR(35),
		NSEC(47),
		NSEC3(50),
		NSEC3PARAM(51),
		RRSIG(46),
		SIG(24),
		SPF(99),
		SRV(33),
		SSHFP(44),
		TA(32768),
		NS(2),
		MD(3),
		MF(4),
		CNAME(5),
		SOA(6),
		MB(7),
		MG(8),
		MR(9),
		NULL(10),
		WKS(11),
		PTR(12),
		HINFO(13),
		MINFO(14),
		MX(15),
		TXT(16),
		
		OPT(41),
		
		TKEY(249),
		TSIG(250),
		
		IXFR(251),
		AXFR(252),
		MAILB(253),
		MAILA(254),
		
		ANY(255)
		;
		
		private final short value;

		Type(int value) {
			this.value = (short) value;
		}
		
		public static Type parse(short i) {
			for(Type t : values()) {
				if(t.value == i) return t;
			}
			return null;
		}

		public short value() {
			return value;
		}
	}

	
}
