package net.cheney.mdns;

import java.nio.ByteBuffer;

import org.apache.commons.lang.StringUtils;

public class DNSRequestBuilder {

	private DNSRequestBuilder() { };
	
	/*
	 * The header contains the following fields:

                                    1  1  1  1  1  1
      0  1  2  3  4  5  6  7  8  9  0  1  2  3  4  5
    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    |                      ID                       |
    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    |QR|   Opcode  |AA|TC|RD|RA|   Z    |   RCODE   |
    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    |                    QDCOUNT                    |
    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    |                    ANCOUNT                    |
    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    |                    NSCOUNT                    |
    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    |                    ARCOUNT                    |
    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
	 */
	public static ByteBuffer buildRequest(DNSQuery query) {
		ByteBuffer request = ByteBuffer.allocate(512);
		short id = 1;
		request.putShort(id);
		short flags = 0;
		request.putShort(flags);
		short qdcount = 1;
		request.putShort(qdcount);
		short ancount = 0;
		request.putShort(ancount);
		short nscount = 0;
		request.putShort(nscount);
		short arcount = 0;
		request.putShort(arcount);
		
		request.put(toQName(query.name()));
		
		request.putShort(query.type().value());
		request.putShort(query.clazz().value());
		return (ByteBuffer) request.flip();
	}
	
	private static ByteBuffer toQName(String string) {
		ByteBuffer buffer = ByteBuffer.allocate(512);
		for(String label : StringUtils.split(string, '.')) {
			buffer.put((byte) label.length());
			for(char c : label.toCharArray()) {
				buffer.put((byte) c);
			}
		}
		buffer.put((byte) 0);
		return (ByteBuffer) buffer.flip();
	}
}
