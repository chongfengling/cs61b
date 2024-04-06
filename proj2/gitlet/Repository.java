package gitlet;

import java.io.File;
import static gitlet.Utils.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author Chongfeng Ling
 */
public class Repository {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");

    public static final File OBJECTS_DIR = join(GITLET_DIR, "objects");

    public static void gitletHelp() {
        System.out.println("Gitlet help message.");
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
}
