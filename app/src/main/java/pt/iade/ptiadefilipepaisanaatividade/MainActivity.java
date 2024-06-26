package pt.iade.ptiadefilipepaisanaatividade;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;
import pt.iade.ptiadefilipepaisanaatividade.adapters.NoteItemAdapter;
import pt.iade.ptiadefilipepaisanaatividade.models.NoteItem;


public class MainActivity extends AppCompatActivity {
    private static final int EDITOR_ACTIVITY_RETURN_ID = 1;
    protected RecyclerView itemsListView;
    protected NoteItemAdapter itemsRowAdapter;
    protected ArrayList<NoteItem> itemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setupComponents();
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_add_note) {

            Intent intent = new Intent(MainActivity.this, NoteActivity.class);

            intent.putExtra("position", -1);

            intent.putExtra("item", new NoteItem());


            startActivityForResult(intent, EDITOR_ACTIVITY_RETURN_ID);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDITOR_ACTIVITY_RETURN_ID) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                int position = data.getIntExtra("position", -1);
                NoteItem updatedItem = (NoteItem) data.getSerializableExtra("item");

                if (position == -1) {
                    itemsList.add(updatedItem);
                    itemsRowAdapter.notifyItemInserted(itemsList.size() - 1);
                } else {
                    itemsList.set(position, updatedItem);
                    itemsRowAdapter.notifyItemChanged(position);
                }
            } else if (resultCode == RESULT_FIRST_USER) {
                int position = data.getIntExtra("position", -1);
                if (position != -1) {
                    itemsList.remove(position);
                    itemsRowAdapter.notifyItemRemoved(position);
                }
            }
        }
    }

    private void setupComponents() {
        setSupportActionBar(findViewById(R.id.toolbar));
        itemsListView = (RecyclerView) findViewById(R.id.notas_list);
        itemsListView.setLayoutManager(new LinearLayoutManager(this));

        NoteItem.List(new NoteItem.ListResponse() {
            @Override
            public void onResponse(ArrayList<NoteItem> items) {
                itemsList = items;

                itemsRowAdapter = new NoteItemAdapter(MainActivity.this, itemsList);
                itemsRowAdapter.setClickListener(new NoteItemAdapter.ItemClickListener() {
                    @Override

                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                        intent.putExtra("position", position);
                        intent.putExtra("item", itemsList.get(position));

                        startActivityForResult(intent, EDITOR_ACTIVITY_RETURN_ID);
                    }
                });

                itemsListView.setAdapter(itemsRowAdapter);

            }
        });

    }

}