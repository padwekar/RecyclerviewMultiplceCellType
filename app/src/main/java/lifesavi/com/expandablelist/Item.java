package lifesavi.com.expandablelist;

public class Item {
    String task ;
    boolean isChild ;

    public Item(String task, boolean isChild) {
        this.task = task;
        this.isChild = isChild;
    }

    public String getTask() {
        return task;
    }

    public boolean isChild() {
        return isChild;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setChild(boolean child) {
        isChild = child;
    }
}
