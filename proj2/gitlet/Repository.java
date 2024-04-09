package gitlet;

import java.io.File;
import static gitlet.Utils.*;

/** Represents a gitlet repository.
 * .gitlet/ -- top level folder for all persistent data required for version control initialized by `gitlet init`
 *    - HEAD      -- HEAD of current branch
 *    - index     -- staging area
 *    - objects/  -- all "objects", including blob (files), trees (directories), commits (reference to a tree,
 *    parent commit, etc.)
 *    - refs/     -- pointers to commit objects
 *      - heads/  -- HEADs of each branch
 *
 *  @author Chongfeng Ling
 */
public class Repository {

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");

    public static final File OBJECTS_DIR = join(GITLET_DIR, "objects");

    public static final File REFS_DIR = join(GITLET_DIR, "refs");

    public static void gitletHelp() {
        System.out.println("Gitlet help message.");
    }

    /*
     * TODO: single current branch: master
     * */
    public static void initCommand() {
        // when there is already a repo, abort.
        if (GITLET_DIR.exists()) {
            Utils.message("A gitlet version-control system already exists in the current directory.");
            System.exit(0);
        } else {
            GITLET_DIR.mkdir();
            OBJECTS_DIR.mkdir();
            REFS_DIR.mkdir();
            initCommand("initial commit");
        }
        return;
    }

    public static void addCommand(String fileName) {
        // check if to be added file exists
        File f = Utils.join(CWD, fileName);
        checkFileExistence(f);
    }

    }

    private static void initCommand(String message) {
        Commit commit = new Commit(message);
        String sha1Code = Utils.sha1(commit.toString());
        File COMMIT_DIR = Utils.join(OBJECTS_DIR, sha1Code.substring(0, 2));
        // storage location based on the commit's sha1 code
        if (!COMMIT_DIR.exists()) {
            COMMIT_DIR.mkdir();
        }
        File commitFile = Utils.join(COMMIT_DIR, sha1Code.substring(2, sha1Code.length()));
        Utils.writeObject(commitFile, commit);
    }

    private static void checkFileExistence(File f) {
        if (!f.exists()) {
            Utils.error("File does not exist");
            System.exit(0);
        }
    }

}
