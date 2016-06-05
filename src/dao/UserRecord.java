/**
 * 
 */
package dao;

import java.util.ArrayList;
import java.util.List;

import utils.enums.Measurement;

/**
 * @author ES
 *
 */
public class UserRecord {
	private String date;
	private String sex;
	private int age;
	private List<Float> time;
	private List<Float> skinTemperature;
	private List<Float> conductance;
	private List<Float> bvp;
	
	public UserRecord(String date, String sex, int age) {
		this.date = date;
		this.sex = sex;
		this.age = age;
		time = new ArrayList<Float>();
		skinTemperature = new ArrayList<Float>();
		conductance = new ArrayList<Float>();
		bvp = new ArrayList<Float>();
	}
	
	
	public void addRecord(float time, float skinTemperature, float conductance, float bvp) {
		this.time.add(time);
		this.skinTemperature.add(skinTemperature);
		this.conductance.add(conductance);
		this.bvp.add(bvp);
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}


	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}


	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the time
	 */
	public List<Float> getTime() {
		return time;
	}


	/**
	 * @param time the time to set
	 */
	public void setTime(List<Float> time) {
		this.time = time;
	}
	
	/**
	 * @return the skinTemperature
	 */
	public List<Float> getSkinTemperature() {
		return skinTemperature;
	}

	/**
	 * @param skinTemperature the skinTemperature to set
	 */
	public void setSkinTemperature(List<Float> skinTemperature) {
		this.skinTemperature = skinTemperature;
	}

	/**
	 * @return the conductance
	 */
	public List<Float> getConductance() {
		return conductance;
	}

	/**
	 * @param conductance the conductance to set
	 */
	public void setConductance(List<Float> conductance) {
		this.conductance = conductance;
	}

	/**
	 * @return the bvp
	 */
	public List<Float> getBvp() {
		return bvp;
	}

	/**
	 * @param bvp the bvp to set
	 */
	public void setBvp(List<Float> bvp) {
		this.bvp = bvp;
	}
	
	public List<String[]> toStringArray() {
		List<String[]> stringArray = new ArrayList<String[]>();
		// Add user info
		stringArray.add(new String[] {"DATE", "SEX", "AGE"});
		stringArray.add(new String[] { this.date, this.sex, String.valueOf(this.age)} );
		stringArray.add(new String[] {"", "",""});
		// Column names
		stringArray.add(new String[] 
				{
					Measurement.TIME.getName(),
					Measurement.SKIN_TEMPERATURE.getName(), 
					Measurement.SKIN_CONDUCTANCE.getName(), 
					Measurement.BVP.getName()
				}
		);
		// Add measurements
		for (int i = 0; i < this.time.size(); ++i) {
			stringArray.add(
					new String[] 
					{
						this.time.get(i).toString(), 
						this.skinTemperature.get(i).toString(), 
						this.conductance.get(i).toString(), 
						this.bvp.get(i).toString()
					}
			);
		}
		return stringArray;
	}

}
