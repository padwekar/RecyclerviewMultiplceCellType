package lifesavi.com.expandablelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import java.util.List;

public class ExRecyclerAdapter  extends ExpandableRecyclerAdapter<TaskTitleHeaderViewHolder,TaskOptionsChildViewHolder>{
    Context mContext ;
    public ExRecyclerAdapter(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);
        mContext = context ;
    }

    @Override
    public TaskTitleHeaderViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_header,viewGroup,false);
        return new TaskTitleHeaderViewHolder(view);
    }

    @Override
    public TaskOptionsChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_child,viewGroup,false);
        return new TaskOptionsChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(TaskTitleHeaderViewHolder taskTitleHeaderViewHolder, int i, Object o) {
        taskTitleHeaderViewHolder.mTextViewHeader.setText("Hello");
    }

    @Override
    public void onBindChildViewHolder(TaskOptionsChildViewHolder taskOptionsChildViewHolder, int i, Object o) {

    }


}
 class TaskTitleHeaderViewHolder extends ParentViewHolder {
    TextView mTextViewHeader ;
    public TaskTitleHeaderViewHolder(View itemView) {
        super(itemView);
        mTextViewHeader = (TextView)itemView.findViewById(R.id.texttview_header_title);
    }
}

 class TaskOptionsChildViewHolder extends ChildViewHolder {
    public TaskOptionsChildViewHolder(View itemView) {
        super(itemView);
    }
}
