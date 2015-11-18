package simpledatabase;

import java.util.ArrayList;
import java.util.Collections;

public class Sort extends Operator {

	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;

	public Sort(Operator child, String orderPredicate) {
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();

	}

	/**
	 * The function is used to return the sorted tuple
	 * 
	 * @return tuple
	 */
	@Override
	public Tuple next() {
		Tuple tuple = child.next();
		while (tuple != null) {
			tuplesResult.add(tuple);
			tuple = child.next();
		}

		if (!tuplesResult.isEmpty()) {
			Tuple minTuple = tuplesResult.get(0);
			if (tuplesResult.size() > 0) {
				for (int i = 0; i < tuplesResult.size(); i++) {
					Tuple curTuple = tuplesResult.get(i);
					for (int j = 0; j < curTuple.getAttributeList().size(); j++) {
						if (curTuple.getAttributeName(j).equals(orderPredicate)) {
							if (minTuple.getAttributeValue(j).toString()
									.compareTo(curTuple.getAttributeValue(j).toString()) > 0) {
								minTuple = curTuple;
								tuple = curTuple;
							} else {
								tuple = minTuple;
							}

						}
					}
				}
				for (int i = 0; i < tuplesResult.size(); i++)
					if (tuplesResult.get(i).equals(tuple)) {
						tuplesResult.remove(i);
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
		return child.getAttributeList();
	}

}