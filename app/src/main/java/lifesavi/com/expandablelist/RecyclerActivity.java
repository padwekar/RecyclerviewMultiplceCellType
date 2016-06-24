package lifesavi.com.expandablelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {
    List<Item> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView_task);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this);

        itemList.add(new Item("Android",false));
        itemList.add(new Item("IOS",false));
        itemList.add(new Item("Windows",false));
        itemList.add(new Item("MacOS",false));
        itemList.add(new Item("Amigo",false));
        itemList.add(new Item("Unix",false));
        itemList.add(new Item("Remix OS",false));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemTouchHelper.Callback callback = new MoveTouchHelper(recyclerViewAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
      //  helper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.addList(itemList);
    }

    private void addVersions() {
        this.itemList.add(new Item("Version 1",true));
    }
}
