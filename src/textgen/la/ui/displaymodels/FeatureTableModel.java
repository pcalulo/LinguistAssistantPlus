package textgen.la.ui.displaymodels;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import textgen.la.models.Constituent;
import textgen.la.models.Feature;

public class FeatureTableModel implements TableModel {
	private List<Feature> mFeatures = new ArrayList<Feature>();
	private List<TableModelListener> mListeners = new ArrayList<TableModelListener>();
	private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

	private static final int COL_FEATURE_NAME = 0;
	private static final int COL_FEATURE_VALUE = 1;

	public FeatureTableModel(Constituent constituent) {
		mFeatures = constituent.getFeatureList().getFeatures();

		System.out.println("Initialized FTM");
	}

	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public int getColumnCount() {
		// We're showing two columns: feature name, and feature value
		return 2;
	}

	public int getRowCount() {
		// Return the number of features in the constituent
		if (mFeatures != null) {
			return mFeatures.size();
		} else {
			return 0;
		}
	}

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

	/**
	 * Convenience method -- allows us to easily insert an empty row to the
	 * feature table. If given an index that is 0 or less, the new row is
	 * appended to the table.
	 */
	public void insertEmptyRow(int index) {
		Feature emptyFeature = new Feature("", "");
		if (index >= 0) {
			logger.info("Index specified: " + index);
			
			// Insert it *after* the selected element
			mFeatures.add(index + 1, emptyFeature);
		} else {
			logger.info("Index not specified, appending");
			mFeatures.add(emptyFeature);
		}
		
		sendChangeEvent();
	}
	
	public void removeRowAt(int index) {
		mFeatures.remove(index);
		sendChangeEvent();
	}

	public void addTableModelListener(TableModelListener listener) {
		mListeners.add(listener);
	}

	public void removeTableModelListener(TableModelListener listener) {
		mListeners.remove(listener);
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return true;
	}

	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		// the row selects which Feature, the column selects which property
		Feature feature = mFeatures.get(rowIndex);
		String newValue = value.toString();
		
		switch (columnIndex) {
		case COL_FEATURE_NAME:
			feature.setName(newValue);
			break;
		case COL_FEATURE_VALUE:
			feature.setValue(newValue);
			break;
		default:
			logger.warning("Invalid columnIndex specified: " + columnIndex);
		}
	}

	public void sendChangeEvent() {
		TableModelEvent event = new TableModelEvent(this);
		
		for (TableModelListener listener : mListeners) {
			listener.tableChanged(event);
		}
	}
}
