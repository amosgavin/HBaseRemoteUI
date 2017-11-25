package model;

public class HBaseCol {
	public String family;
	public String qualifier;

	public HBaseCol(String family, String qualifier) {
		this.family = family;
		this.qualifier = qualifier;
	}
}