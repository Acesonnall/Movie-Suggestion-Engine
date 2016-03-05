package project1;

import java.io.FileNotFoundException;

/**
 * @author Omar Taylor
 *         <p>
 *         This class implements the project.
 */
public class Implementation {

    public static void main(String[] args) throws FileNotFoundException {
//        SuggestionEngine s = new SuggestionEngine();
//        s.getTopX(50);

        Movies m = new Movies("C:\\Users\\O\\Documents\\GitHub\\Intellij IDEA\\Movie Suggestion Engine\\ml-100k\\ml-100k\\u.item.txt");
    }
}
