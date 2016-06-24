package lifesavi.com.expandablelist;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.ArrayList;
import java.util.List;

public class TaskName implements ParentObject {
    List mTaskOptionList = new ArrayList<>();
    @Override
    public List<Object> getChildObjectList() {
        return mTaskOptionList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        mTaskOptionList.clear();
        mTaskOptionList.addAll(list);
    }
}
