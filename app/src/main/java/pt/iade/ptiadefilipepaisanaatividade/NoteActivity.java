package pt.iade.ptiadefilipepaisanaatividade;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

public class NoteActivity extends AppCompatActivity {
    private EditText titleEditText;
    private EditText contentEditText;
    private TextView modifiedDateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        titleEditText = findViewById(R.id.titleEditText);
        contentEditText = findViewById(R.id.contentEditText);
        modifiedDateTextView = findViewById(R.id.modifiedDateTextView);

        // Aqui você pode inicializar os campos com dados, se estiver editando uma nota existente
    }

    // Métodos para salvar e excluir a nota aqui
}
