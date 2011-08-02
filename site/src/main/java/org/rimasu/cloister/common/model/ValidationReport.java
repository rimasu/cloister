package org.rimasu.cloister.common.model;


public class ValidationReport {

	public enum Target {
		MEMBER
	}

	public enum Issue {
		NOT_POPULATED, NOT_VALID
	}

	private final Target targetType;
	private final String targetUuid;
	private final String fieldName;
	private final Issue issueType;

	public ValidationReport(Target targetType, String targetUuid,
			String fieldName, Issue issueType) {
		this.targetType = targetType;
		this.targetUuid = targetUuid;
		this.fieldName = fieldName;
		this.issueType = issueType;
	}

	public Target getTargetType() {
		return targetType;
	}

	public String getTargetUuid() {
		return targetUuid;
	}

	public String getFieldName() {
		return fieldName;
	}

	public Issue getIssueType() {
		return issueType;
	}

}
