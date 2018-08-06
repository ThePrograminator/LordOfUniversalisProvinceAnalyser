package SearchObjects.Finders;

import java.io.FileNotFoundException;

public interface Finder
{
    boolean loadFiles(String directory) throws FileNotFoundException;
    String getFinderName();
}
