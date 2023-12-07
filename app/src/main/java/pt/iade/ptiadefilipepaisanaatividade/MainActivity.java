package pt.iade.ptiadefilipepaisanaatividade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.GregorianCalendar;

import pt.iade.ptiadefilipepaisanaatividade.models.NoteItem;


public class MainActivity extends AppCompatActivity {

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

            intent.putExtra("item", new NoteItem());


            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupComponents() {
        setSupportActionBar(findViewById(R.id.toolbar));

    }
}