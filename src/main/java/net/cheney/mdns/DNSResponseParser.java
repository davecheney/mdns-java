package net.cheney.mdns;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import net.cheney.mdns.DNSResponse.Builder;

import org.apache.commons.lang.StringUtils;

public class DNSResponseParser {

	private DNSResponseParser() { };
	
	public static DNSResponse parse(ByteBuffer buffer) {
		short id = buffer.getShort();
		short flags = buffer.getShort();
		short qdcount = buffer.getShort(), ancount = buffer.getShort(), nscount = buffer.getShort(), arcount = buffer.getShort();
		
		Builder builder = DNSResponse.builder(id, flags);
		
		for(int i = 0 ; i < qdcount ; i++ ) {
			DNSQuery question = parseQuestion(buffer);
			builder.addQuestion(question);
		}

		for(int i = 0 ; i < ancount ; i++ ) {
			DNSRecord answer = parseResponseRecord(buffer);
			builder.addAnswer(answer);
		}

		for(int i = 0 ; i < nscount ; i++ ) {
			DNSRecord nameserver = parseResponseRecord(buffer);
			builder.addNameserver(nameserver);
		}

		for(int i = 0 ; i < arcount ; i++ ) {
			DNSRecord additionalRecord = parseResponseRecord(buffer);
			builder.addAdditionalRecord(additionalRecord);
		}
		
		return builder.build();
	}

	private static DNSQuery parseQuestion(ByteBuffer buffer) {
		String name = parseName(buffer);
		DNSRecordType type = DNSRecordType.parse(buffer.getShort());
		DNSRecordClass clazz = DNSRecordClass.parse(buffer.getShort());
		return new DNSQuery(name, type, clazz);
	}

	private static String parseName(ByteBuffer buffer) {
		return StringUtils.join(parseName0(buffer), '.');
	}
	
	private static List<String> parseName0(ByteBuffer buffer) {
		List<String> labels = new ArrayList<String>();
		for( ;; ) {
			byte length = buffer.get();
			if(length != 0) {
				if ((length & 192) == 192) {
					// inelegant support for pointers
					int offset = (length & 63);
					offset =+ (buffer.get() & 0xff);
					labels.addAll(parseName0((ByteBuffer) buffer.duplicate().position(offset)));
					break;
				} else {
					StringBuilder label = new StringBuilder(63);
					for(int i = 0 ; i < length ; i++) {
						label.append((char)buffer.get());
					}
					labels.add(label.toString());
				}
			} else {
				break;
			}
		}
		return labels;
	}

	private static DNSRecord parseResponseRecord(ByteBuffer buffer) {
		String name = parseName(buffer);
		short type = buffer.getShort();
		short clazz = buffer.getShort();
		int ttl = buffer.getInt();
		short rdlength = buffer.getShort();
		ByteBuffer rdata = (ByteBuffer) buffer.duplicate().position(buffer.position()).limit(buffer.position() + rdlength);
		buffer.position(buffer.position() + rdlength);
		return new DNSRecord(DNSRecordType.parse(type), DNSRecordClass.parse(clazz), name, ttl);
	}
}
