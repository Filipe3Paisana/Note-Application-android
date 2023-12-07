package pt.iade.ptiadefilipepaisanaatividade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.view.MenuItem;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;
import java.util.GregorianCalendar;

import pt.iade.ptiadefilipepaisanaatividade.models.NoteItem;

public class NoteActivity extends AppCompatActivity {
    protected EditText noteTitle;
    protected EditText noteBody;
    protected TextView lastModified;

    protected NoteItem item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Intent noteIntent = getIntent();

        item = (NoteItem) noteIntent.getSerializableExtra("item");

        setupComponents();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.save_note) {
            finish();
            return true;
        }

        if(item.getItemId() == R.id.delete_note) {
            showDeleteConfirmationDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupComponents() {
        setSupportActionBar(findViewById(R.id.toolbar));
        noteTitle =  findViewById(R.id.note_title);
        noteBody = findViewById(R.id.note_body);
        lastModified = findViewById(R.id.last_modified);
        populateView();
    }
    protected void populateView() {
        noteTitle.setText(item.getTitle());
        noteBody.setText(item.getContent());
        lastModified.setText(item.getModifiedDateAsString().toString());
    }





    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmar");
        builder.setMessage("Tem certeza que deseja deletar esta nota?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}