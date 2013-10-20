package textgen.la.ui.displaymodels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import textgen.la.models.directory.LinguistText;
import textgen.la.models.directory.VerseReference;

public class LinguistTextTreeModel implements TreeModel {

	private List<TreeModelListener> mListeners = new ArrayList<>();

	private LinguistText mLinguistText;

	public LinguistTextTreeModel(LinguistText linguistText) {
		this.setLinguistText(linguistText);
	}

	@Override
	public Object getChild(Object object, int index) {
		TreeNode node = (TreeNode) object;
		return node.getChildAt(index);
	}

	@Override
	public int getChildCount(Object object) {
		TreeNode node = (TreeNode) object;
		return node.getChildCount();
	}

	@Override
	public int getIndexOfChild(Object object, Object childObj) {
		TreeNode node = (TreeNode) object;
		TreeNode child = (TreeNode) childObj;
		return node.getIndex(child);
	}

	@Override
	public Object getRoot() {
		return getLinguistText();
	}

	@Override
	public boolean isLeaf(Object object) {
		if (object instanceof VerseReference) {
			return true;
		}
		return false;
	}

	@Override
	public void addTreeModelListener(TreeModelListener listener) {
		mListeners.add(listener);
	}

	@Override
	public void removeTreeModelListener(TreeModelListener listener) {
		mListeners.remove(listener);
	}

	@Override
	public void valueForPathChanged(TreePath arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	public LinguistText getLinguistText() {
		return mLinguistText;
	}

	public void setLinguistText(LinguistText mLinguistText) {
		this.mLinguistText = mLinguistText;
	}

}
