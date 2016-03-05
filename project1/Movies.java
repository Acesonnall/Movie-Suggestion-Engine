package project1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Omar Taylor
 *         3/5/2016.
 */
public class Movies {

    private SplayTree<MovieInfo> inventory;

    public Movies() {
        this.inventory = new SplayTree<MovieInfo>();
    }

    public Movies(String movieFile) throws FileNotFoundException {
        this();
        bulkImport(movieFile);
    }
    public void bulkImport(String movieFile) throws FileNotFoundException, IllegalArgumentException {
        Scanner sc = new Scanner(new FileInputStream(new File(movieFile))).useDelimiter("\\||\\n");
        int count = 0;
        while (sc.hasNext()) {
            int mI = sc.nextInt();
            String mT = sc.next();
            String rD = sc.next();
            String vRD = sc.next();
            String IMBD = sc.next();
            int[] arr = {sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt()};

            inventory.addBST(new MovieInfo(mI, mT, rD, vRD, IMBD, arr));
            count++;
            if (count == 1680)
                count = count;
        }
        sc.close();
    }
}
