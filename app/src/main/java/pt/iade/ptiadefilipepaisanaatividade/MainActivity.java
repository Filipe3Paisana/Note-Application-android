package pt.iade.ptiadefilipepaisanaatividade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import androidx.appcompat.widget.Toolbar;
import pt.iade.ptiadefilipepaisanaatividade.adapters.NoteItemAdapter;
import pt.iade.ptiadefilipepaisanaatividade.models.NoteItem;


public class MainActivity extends AppCompatActivity {
    protected RecyclerView itemsListView;
    protected ArrayList<NoteItem> itemsList;
    protected NoteItemAdapter itemsRowAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemsList = NoteItem.List();

        setupComponents();
    }
    protected void onResume() {
        super.onResume();
        itemsList = NoteItem.List();
        itemsRowAdapter.notifyDataSetChanged();
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

            intent.putExtra("item", new NoteItem());


            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupComponents() {
        setSupportActionBar(findViewById(R.id.toolbar));

        itemsRowAdapter = new NoteItemAdapter(this, itemsList);
        itemsRowAdapter.setClickListener(new NoteItemAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                intent.putExtra("item", itemsList.get(position));
                startActivity(intent);
            }
        });

        itemsListView = (RecyclerView) findViewById(R.id.notas_list);
        itemsListView.setLayoutManager(new LinearLayoutManager(this));
        itemsListView.setAdapter(itemsRowAdapter);

    }
}