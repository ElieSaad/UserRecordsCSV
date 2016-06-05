/**
 * 
 */
package utils.enums;

/**
 * @author ES
 *
 */
public enum Measurement {
	TIME ("TIME"),
	SKIN_TEMPERATURE ("SKIN TEMPERATURE"),
	SKIN_CONDUCTANCE ("SKIN CONDUCTANCE"),
	BVP ("BLOOD VOLUME PRESSURE");
	
	private final String name;
	private Measurement (String name) {
		this.name = name;
	}
	
	/**
	 * @return the measurement name
	 */
	public String getName () {
		return name;
	}
}
