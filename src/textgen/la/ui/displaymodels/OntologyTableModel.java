package textgen.la.ui.displaymodels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import textgen.la.models.ontology.Noun;
import textgen.la.models.ontology.POS;

public class OntologyTableModel extends AbstractTableModel{

        private ArrayList<POS> list;

        public OntologyTableModel (ArrayList<POS> list) {
            this.list = list;
        }
        
        
        @Override
        public int getRowCount() {
            return list.size();
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public String getColumnName(int column) {
            String name = "??";
            switch (column) {
                case 0:
                    name = "Stem";
                    break;
                case 1:
                    name = "Definition";
                    break;
            }
            return name;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            Class type = String.class;
            return type;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            POS item = list.get(rowIndex);
            Object value = null;
            switch (columnIndex) {
                case 0:
                    value = item.getName();
                    break;
                case 1:
                    value = item.getValue();
                    break;
            }
            return value;
        }            
    
}
