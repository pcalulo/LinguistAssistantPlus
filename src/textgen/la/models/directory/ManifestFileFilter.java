package textgen.la.models.directory;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ManifestFileFilter extends FileFilter {

	@Override
	public boolean accept(File file) {
		String fileName = file.getName();

		// Let's be case-insensitive
		fileName = fileName.toLowerCase();

		// If it's a directory, accept. We want the user to be able to actually
		// get the the manifest, after all :)
		if (file.isDirectory()) {
			return true;
		}

		// Now that we know it's a file, accept it if it's named like a manifest
		// file should.
		return fileName.equals(ManifestReader.MANIFEST_FILE_NAME);
	}

	@Override
	public String getDescription() {
		return "Linguist Assistant+ Manifest Files ("
				+ ManifestReader.MANIFEST_FILE_NAME + ")";
	}

}
