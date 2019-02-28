package creator;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class CustomFilter extends FileFilter {
	
	/** Description of extension that will appear in filechooser dialogue */
    private String filterDescription;
    
    /** Array of all accepted extensions for filter */
    private String filterExt[];
    
	/**
	 * CustomFilter() - takes in the extension and description for filter
	 * 
	 * @author Fernando Palacios
	 */
    public CustomFilter(String desc, String ext) {
        this(desc, new String[] { ext });
    }
    
	/**
	 * CustomFilter() - takes in the extension and description for filter
	 * 
	 * @author Fernando Palacios
	 */
    public CustomFilter(String desc, String ext[]) {
        if (desc == null) {
          filterDescription = ext[0];
        } else {
          filterDescription = desc;
        }
        filterExt = (String[]) ext.clone();
        toLower(filterExt);
      }
    
	/**
	 * toLower() - simple  method to turn array into lowercase
	 * 
	 * @author Fernando Palacios
	 */
    private void toLower(String array[]) {
        for (int i = 0, n = array.length; i < n; i++) {
          array[i] = array[i].toLowerCase();
        }
      }
    
	/**
	 * accept() - override accept to change the files shown, will only show files that return true and match filter ext
	 * 
	 * @author Fernando Palacios
	 */
    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
          return true;
        } else {
          String path = file.getAbsolutePath().toLowerCase();
          for (int i = 0, n = filterExt.length; i < n; i++) {
            String extension = filterExt[i];
            if ((path.endsWith(extension) && (path.charAt(path.length() - extension.length() - 1)) == '.')) {
              return true;
            }
          }
        }
        return false;
      }
    
	/**
	 * getDescription() - grabs description, regular getter has to be implemented
	 * 
	 * @author Fernando Palacios
	 */
    @Override
    public String getDescription() {
        return filterDescription;
    }
}
