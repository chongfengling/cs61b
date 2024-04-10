package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.TreeMap;

public class Stage implements Serializable {
    /*
    * create Stage inside the file INDEX for staging (additional & removal)
    * support read/save from/to the file INDEX
    * This Stage stores sha1 code as tree's key and file name as the value.
    * */

    private TreeMap<String, String> additionalStage;
    private TreeMap<String, String> removalStage;
    private int size;

    public Stage() {
        additionalStage = new TreeMap<String, String>();
        removalStage = new TreeMap<String, String>();
        size = 0;
    }

    public void add(Blob b) {
        this.additionalStage.put(b.getFileID(), b.getFileName());
        this.size += 1;
    }

    public void remove(Blob b) {
        this.additionalStage.remove(b.getFileID(), b.getFileName());
        this.size += -1;
    }

    public void save(File f) {
        Utils.writeObject(f, this);
    }

    public TreeMap getAdditionalStage() {
        return this.additionalStage;
    }

    public TreeMap getRemovalStage() {
        return this.removalStage;
    }

    public int getSize() {
        return this.size;
    }

    public static Stage fromFile(File f) {
        return Utils.readObject(f, Stage.class);
    }

    public void status() {
        // print files' name in the additional stage
        System.out.println("=== Staged Files ===");
        for (String key : this.additionalStage.keySet()) {
            String value = this.additionalStage.get(key);
            System.out.println(value);
        }
        System.out.println();
        // print files' name in the removal stage
        System.out.println("=== Removed Files ===");
        for (String key : this.removalStage.keySet()) {
            String value = this.removalStage.get(key);
            System.out.println(value);
        }
        System.out.println();
    }
    }
}
