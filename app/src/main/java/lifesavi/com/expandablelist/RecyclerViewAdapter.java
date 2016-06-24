package lifesavi.com.expandablelist;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context mContext ;
    List<Item> mTaskList  ;
    int lastOpenPosition = -2 ;
    boolean isOpen = false ;
    public RecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addList(List<Item> taskList){
        mTaskList = taskList ;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = null ;
        if(viewType==0){
            view = inflater.inflate(R.layout.item_header,parent,false);
            return new RecyclerViewHolderParent(view);
        }else{
            view = inflater.inflate(R.layout.item_child,parent,false);
            return new RecyclerViewHolderChild(view);

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
          if(!mTaskList.get(position).isChild){
              RecyclerViewHolderParent parent = (RecyclerViewHolderParent)holder;
              parent.mTextViewTask.setText(mTaskList.get(position).task);
          }

    }
    public void swap(int firstPosition, int secondPosition){
        Collections.swap(mTaskList, firstPosition, secondPosition);
        notifyItemMoved(firstPosition, secondPosition);
    }
    public void remove(int position) {
        mTaskList.remove(position);
        notifyItemRemoved(position);
    }


    @Override
    public int getItemViewType(int position) {
        int type = 1 ;
        if(!mTaskList.get(position).isChild)
            type = 0 ;

    return type ;
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }

    public class RecyclerViewHolderParent extends RecyclerView.ViewHolder{
        TextView mTextViewTask ;
        public RecyclerViewHolderParent(View itemView) {
            super(itemView);
            mTextViewTask = (TextView)itemView.findViewById(R.id.texttview_header_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition()+1 ;

                    if (position<mTaskList.size() && mTaskList.get(position).isChild){
                        mTaskList.remove(position);
                        notifyItemRemoved(position);
                        isOpen = false ;
                    }else{
                        if(isOpen){

                            mTaskList.remove(lastOpenPosition);
                            notifyItemRemoved(lastOpenPosition);
                            isOpen = false ;
                            if(position>lastOpenPosition)
                                position = position - 1 ;
                        }

                        mTaskList.add(position,new Item("",true));
                        notifyItemInserted(position);
                        isOpen = true ;
                        lastOpenPosition = position ;
                    }

                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for(int i = 0 ; i < mTaskList.size() ; i++){
                                if(mTaskList.get(i).isChild){
                                    mTaskList.remove(i);

                                }
                            }

                            ((Activity) mContext).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    notifyDataSetChanged();
                                }
                            });
                        }
                    }).start();

                    return  true ;
                }
            });
        }
    }

    public class RecyclerViewHolderChild extends RecyclerView.ViewHolder{

        public RecyclerViewHolderChild(View itemView) {
            super(itemView);
        }
    }
}
