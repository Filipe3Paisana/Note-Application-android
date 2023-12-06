package pt.iade.ptiadefilipepaisanaatividade;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<NoteItem> notes = new ArrayList<>();
    private NoteAdapter notesAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.todo_list);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_new_note) {
            createNewNote();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void addNewNote(String title, String summary, String content, String creationDate, String modifiedDate) {
        notes.add(new NoteItem(title, summary, "2020-10-10", "2020-10-10"));
        notesAdapter.notifyItemInserted(notes.size() - 1);
    }
    private void createNewNote() {

        addNewNote(
                "Note " + (notes.size() + 1),
                "Summary " + (notes.size() + 1),
                "Content " + (notes.size() + 1),
                "2020-10-10",
                "2020-10-10");
    }
}