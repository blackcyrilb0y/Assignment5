package simpledatabase;

import java.util.ArrayList;

public class Join extends Operator {

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;

	// Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate) {
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();

	}

	/**
	 * It is used to return a new tuple which is already joined by the common
	 * attribute
	 * 
	 * @return the new joined tuple
	 */
	// The record after join with two tables
	@Override
	public Tuple next() {
		Tuple leftChildTuple = leftChild.next();

		while (leftChildTuple != null) {
			tuples1.add(leftChildTuple);
			leftChildTuple = leftChild.next();
		}

		Tuple rightChildTuple = rightChild.next();

		Tuple tuple = null;
		if (rightChildTuple != null) {
			for (int k = 0; k < tuples1.size(); k++) {
				if (rightChildTuple.getAttributeList().get(2).getAttributeValue()
						.equals(tuples1.get(k).getAttributeValue(0))) {
					newAttributeList = tuples1.get(k).getAttributeList();
					tuple = new Tuple(newAttributeList);
					return tuple;
				}
			}
		}
		return tuple;
	}

	/**
	 * The function is used to get the attribute list of the tuple
	 * 
	 * @return attribute list
	 */
	public ArrayList<Attribute> getAttributeList() {
		if (joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return (newAttributeList);
	}

}