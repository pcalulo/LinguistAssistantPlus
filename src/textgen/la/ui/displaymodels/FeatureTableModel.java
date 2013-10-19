package textgen.la.ui.displaymodels;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.table.DefaultTableModel;

import textgen.la.models.Constituent;
import textgen.la.models.Feature;

public class FeatureTableModel extends DefaultTableModel {
	private List<Feature> mFeatures = new ArrayList<Feature>();

	private static final int COL_FEATURE_NAME = 0;
	private static final int COL_FEATURE_VALUE = 1;

	public FeatureTableModel(Constituent constituent) {
		mFeatures = constituent.getFeatureList().getFeatures();

		System.out.println("Initialized FTM");
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public int getColumnCount() {
		// We're showing two columns: feature name, and feature value
		return 2;
	}

	@Override
	public int getRowCount() {
		// Return the number of features in the constituent
		if (mFeatures != null) {
			return mFeatures.size();
		} else {
			return 0;
		}
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case COL_FEATURE_NAME:
			return "Name";
		case COL_FEATURE_VALUE:
			return "Value";
		default:
			return null;
		}
	}

	@Override
	public Object getValueAt(int row, int column) {
		// row selects which feature, column selects whether we need to give a
		// name or a value
		String valueToReturn;
		Feature feature = mFeatures.get(row);

		switch (column) {
		case COL_FEATURE_NAME:
			valueToReturn = feature.getName();
			break;
		case COL_FEATURE_VALUE:
			valueToReturn = feature.getValue();
			break;
		default:
			Logger.getLogger(this.getClass().getSimpleName()).warning(
					"Requested table value out of bounds");
			valueToReturn = null;
		}

		return valueToReturn;
	}
}
