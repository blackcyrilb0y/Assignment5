package simpledatabase;

import java.util.ArrayList;

public class Projection extends Operator {

	ArrayList<Attribute> newAttributeList;
	private String attributePredicate;

	public Projection(Operator child, String attributePredicate) {

		this.attributePredicate = attributePredicate;
		this.child = child;
		newAttributeList = new ArrayList<Attribute>();

	}

	/**
	 * Return the data of the selected attribute as tuple format
	 * 
	 * @return tuple
	 */
	@Override
	public Tuple next() {
		Tuple tuple = child.next();
		newAttributeList.clear();
		if (tuple != null) {
			ArrayList<Attribute> oldAttributeList = tuple.getAttributeList();
			for (int i = 0; i < oldAttributeList.size(); i++) {
				if (oldAttributeList.get(i).getAttributeName().equals(attributePredicate)) {
					newAttributeList.add(oldAttributeList.get(i));
				}
			}
			tuple = new Tuple(newAttributeList);
		}
		return tuple;
	}

	/**
	 * The function is used to get the attribute list of the tuple
	 * 
	 * @return attribute list
	 */
	public ArrayList<Attribute> getAttributeList() {
		return child.getAttributeList();
	}

}