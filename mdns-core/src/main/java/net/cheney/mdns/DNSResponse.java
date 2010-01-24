package net.cheney.mdns;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class DNSResponse extends DNSMessage {

	private final short id;
	private final short flags;
	private final List<DNSQuery> questions;
	private final List<DNSRecord> answers, nameservers, additionalRecords;

	DNSResponse(short id, short flags, List<DNSQuery> questions,
			List<DNSRecord> answers, List<DNSRecord> nameservers,
			List<DNSRecord> additionalRecords) {
		this.id = id;
		this.flags = flags;
		this.questions = questions;
		this.answers = answers;
		this.nameservers = nameservers;
		this.additionalRecords = additionalRecords;
	}
	
	public static Builder builder(short id, short flags) {
		return new Builder(id, flags);
	}

	public static class Builder {
		
		private final short id, flags;
		private final List<DNSQuery> questions;
		private final List<DNSRecord> answers, nameservers, additionalRecords;
		
		Builder(short id, short flags) {
			this.id = id;
			this.flags = flags;
			questions = new ArrayList<DNSQuery>();
			answers = new ArrayList<DNSRecord>();
			nameservers = new ArrayList<DNSRecord>();
			additionalRecords = new ArrayList<DNSRecord>();
		}

		public void addQuestion(DNSQuery question) {
			questions.add(question);			
		}

		public DNSResponse build() {
			return new DNSResponse(id, flags, questions, answers, nameservers, additionalRecords);
		}

		public void addAnswer(DNSRecord answer) {
			answers.add(answer);			
		}

		public void addNameserver(DNSRecord nameserver) {
			nameservers.add(nameserver);
		}

		public void addAdditionalRecord(DNSRecord additionalRecord) {
			additionalRecords.add(additionalRecord);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(512);
		sb.append(format("Transaction ID: %d\n", id()));
		sb.append(format("Flags: %d\n", flags()));
		sb.append(format("Questions: %s\n", questions()));
		
		sb.append(format("Answers: %s\n", answers()));
		sb.append(format("Authority: %s\n", nameservers()));
		sb.append(format("Additional: %s\n", additionalRecords()));
		return sb.toString();
	}

	public List<DNSQuery> questions() {
		return questions;
	}
	
	public List<DNSRecord> answers() {
		return answers;
	}
	
	public List<DNSRecord> nameservers() {
		return nameservers;
	}
	
	public List<DNSRecord> additionalRecords() {
		return additionalRecords;
	}

	private int flags() {
		return flags;
	}
	
	public boolean isTruncated() {
		return ((flags & 64) == 64);
	}

	private int id() {
		return id;
	}
}
