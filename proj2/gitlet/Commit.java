package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.*;
import static gitlet.Utils.*;

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author Chongfeng Ling
 */
public class Commit implements Serializable {
    /** The message of this Commit. */
    private String message;
    /** reference to the parent. */
    private String parentID;
    /** unique identifier for this commit*/
    private String commitID;
    /** Username. */
    private String author;
    /** Datestamp. 0 for the first commit. */
    private Date dateStamp;
    /** use TreeMap to represent the tree object associated with the commit.
     *  each commit points to a tree object, records blob identifier, path names and metadata.
     * */
    private TreeMap tree;

    // constructor 1: work for initial commit
    public Commit(String message) {
        this.message = message;
        // no parentID for the first commit
        this.parentID = "abc123";
        this.author = "Chongfeng";
        // timestamp of the first commit is time 0
        this.dateStamp = new Date(0);
        this.tree = new TreeMap();
        generateCommitID();
    }
    // generate commitID by log message, metadata (date, author), tree reference, parentID.
    private void generateCommitID() {
        List<Object> vals = new ArrayList<>();
        vals.add(this.message);
        vals.add(this.dateStamp.toString());
        vals.add(this.author);
        vals.add(this.tree.toString()); // TODO: 1. verify. 2. add blobs to the tree
        vals.add(this.parentID);
        this.commitID = Utils.sha1(vals);
    }

    // save commit object to file located in .gitlet/Objects/
    public void save() {
        File COMMIT_DIR = Utils.join(Repository.OBJECTS_DIR, this.commitID.substring(0, 2));
        // storage location based on the sha1 code of the commit
        if (!COMMIT_DIR.exists()) {
            COMMIT_DIR.mkdir();
        }
        File commitObject = Utils.join(COMMIT_DIR, this.commitID.substring(2, this.commitID.length()));
        Utils.writeObject(commitObject, toString()); // TODO: override toString()
    }
}
