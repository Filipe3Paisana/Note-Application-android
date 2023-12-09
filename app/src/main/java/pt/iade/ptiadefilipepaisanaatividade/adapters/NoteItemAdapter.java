package pt.iade.ptiadefilipepaisanaatividade.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import pt.iade.ptiadefilipepaisanaatividade.R;
import pt.iade.ptiadefilipepaisanaatividade.models.NoteItem;

public class NoteItemAdapter extends RecyclerView.Adapter<NoteItemAdapter.ViewHolder>{
    private ArrayList<NoteItem> items;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;


    public NoteItemAdapter(Context context, ArrayList<NoteItem> items) {
        inflater = LayoutInflater.from(context);
        this.items = items;
        clickListener = null;
    }

    public void setClickListener(ItemClickListener listener) {
        clickListener = listener;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        NoteItem item = items.get(position);

        holder.noteTitleView.setText(item.getTitle());
        holder.noteContentView.setText(item.getContent());
        holder.noteCreationDateView.setText(item.getModifiedDateAsString());
        holder.noteModifiedDateView.setText(item.getCreationDateAsString());

    }

    @Override
    public int getItemCount() {
        return  items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView noteTitleView;
        public TextView noteContentView;
        public TextView noteCreationDateView;
        public TextView noteModifiedDateView;
        public ViewHolder( View itemView) {
            super(itemView);

            noteTitleView = itemView.findViewById(R.id.note_title);
            noteContentView = itemView.findViewById(R.id.note_content);
            noteCreationDateView = itemView.findViewById(R.id.creation_date);
            noteModifiedDateView = itemView.findViewById(R.id.modified_date);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
