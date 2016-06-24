package lifesavi.com.expandablelist;


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;


public class MoveTouchHelper extends ItemTouchHelper.SimpleCallback {
    private RecyclerViewAdapter mMovieAdapter;

    public MoveTouchHelper(RecyclerViewAdapter movieAdapter){
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.mMovieAdapter = movieAdapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mMovieAdapter.swap(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {//Remove item
        int tempPos = viewHolder.getAdapterPosition();
        mMovieAdapter.remove(tempPos);
        mMovieAdapter.notifyItemRemoved(tempPos);
    }

    @Override
    public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof RecyclerViewAdapter.RecyclerViewHolderChild)
            return 0;
        return super.getSwipeDirs(recyclerView, viewHolder);
    }
}