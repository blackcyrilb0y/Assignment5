package simpledatabase;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Table extends Operator {
	private BufferedReader br = null;
	private boolean getAttribute = false;
	private Tuple tuple;

	public Table(String from) {
		this.from = from;

		// Create buffer reader
		try {
			br = new BufferedReader(
					new InputStreamReader(getClass().getResourceAsStream("/datafile/" + from + ".csv")));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Create a new tuple and return the tuple to its parent. Set the attribute
	 * list if you have not prepare the attribute list
	 * 
	 * @return the tuple
	 */
	@Override
	public Tuple next() {
		try {
			if (getAttribute == false) {
				tuple = new Tuple(br.readLine(), br.readLine(), br.readLine());
				tuple.setAttributeName();
				tuple.setAttributeType();
				tuple.setAttributeValue();
				getAttribute = true;
			} else {
				String tupleLine;
				if ((tupleLine = br.readLine()) != null) {
					String col[] = tupleLine.split(",");
					ArrayList<Attribute> attributeList = new ArrayList<Attribute>();
					for (int i = 0; i < tuple.getAttributeList().size(); i++) {
						Attribute attribute = new Attribute();
						attribute.attributeName = tuple.getAttributeName(i);
						attribute.attributeType = tuple.getAttributeType(i);
						attribute.attributeValue = tuple.getAttributeValue(i);
						attributeList.add(attribute);
					}

					for (int i = 0; i < attributeList.size(); i++) {
						attributeList.get(i).setAttributeValue(attributeList.get(i).getAttributeType(), col[i]);
						tuple = new Tuple(attributeList);
					}

				} else {
					return null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tuple;
	}

	/**
	 * The function is used to get the attribute list of the tuple
	 * 
	 * @return the attribute list
	 */
	public ArrayList<Attribute> getAttributeList() {
		return tuple.getAttributeList();
	}

}