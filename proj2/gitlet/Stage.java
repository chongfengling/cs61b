package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.TreeMap;

public class Stage implements Serializable {
    /*
    * create Stage inside the file INDEX for staging (additional & removal)
    * support read/save from/to the file INDEX
    * */

    private TreeMap<String, byte[]> additionalStage;
    private TreeMap<String, byte[]> removalStage;
    private int size;

    public Stage() {
        additionalStage = new TreeMap<String, byte[]>();
        removalStage = new TreeMap<String, byte[]>();
        size = 0;
    }

    public void add(Blob b) {
        this.additionalStage.put(b.getFileID(), b.getFileContent());
        this.size += 1;
    }

    public void remove(Blob b) {
        this.additionalStage.remove(b.getFileID(), b.getFileContent());
    }

    public void read(File f) {
        Stage stage = Utils.readObject(f, this.getClass());
        if (!(stage == null)) {
            this.additionalStage = stage.additionalStage;
            this.removalStage = stage.removalStage;
            this.size = stage.size;
        }
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
}
