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
import pt.iade.ptiadefilipepaisanaatividade.utilities.WebRequest;
import java.util.GregorianCalendar;
import pt.iade.ptiadefilipepaisanaatividade.models.NoteItem;

public class NoteActivity extends AppCompatActivity {
    protected EditText noteTitle;
    protected EditText noteBody;
    protected TextView lastModified;

    protected TextView noteCreationDateView;

    protected NoteItem item;

    protected int listPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Intent intent = getIntent();

        listPosition = intent.getIntExtra("position", -1);

        item = (NoteItem) intent.getSerializableExtra("item");

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
            commitView();
            this.item.save();

            Intent returnIntent = new Intent();
            returnIntent.putExtra("position", this.listPosition);
            returnIntent.putExtra("item", this.item);
            setResult(AppCompatActivity.RESULT_OK, returnIntent);

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
        noteCreationDateView = findViewById(R.id.creation_date);


        populateView();
    }
    protected void populateView() {
        noteTitle.setText(item.getTitle());
        noteBody.setText(item.getContent());
        lastModified.setText(item.getModifiedDateAsString().toString());
    }

    protected void commitView() {
        item.setTitle(noteTitle.getText().toString());
        item.setContent(noteBody.getText().toString());
        item.setModifiedDate(new GregorianCalendar());
    }


    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmar");
        builder.setMessage("Tem certeza que deseja deletar esta nota?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("position", listPosition);
                setResult(RESULT_FIRST_USER, returnIntent);
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