package pt.iade.ptiadefilipepaisanaatividade.models;

import android.util.Log;
import android.widget.Toast;
import android.widget.Toast;

import pt.iade.ptiadefilipepaisanaatividade.utilities.CalendarJsonAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.JsonAdapter;

import pt.iade.ptiadefilipepaisanaatividade.utilities.WebRequest;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import pt.iade.ptiadefilipepaisanaatividade.utilities.WebRequest;

public class NoteItem implements Serializable {
    private int id;

    private String title;
    private String content;
    @JsonAdapter(CalendarJsonAdapter.class)
    private Calendar creationDate;
    @JsonAdapter(CalendarJsonAdapter.class)
    private Calendar modifiedDate;

    public NoteItem(){
        this(0,"","",new  GregorianCalendar(),new GregorianCalendar());
    }

    public NoteItem(int id,String title, String content, Calendar creationDate, Calendar modifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.modifiedDate = modifiedDate;
    }

    public static void List(ListResponse response){
        ArrayList<NoteItem> items = new ArrayList<NoteItem>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    WebRequest request =   new WebRequest(new URL(WebRequest.LOCALHOST + "/api/notes" ));

                    String resp = request.performGetRequest();

                    Gson gson = new Gson();

                    NoteItem[] array = gson.fromJson(resp,NoteItem[].class);
                    //JsonObject json = new Gson().fromJson(resp,JsonObject.class);
                    //JsonArray array = json.getAsJsonArray("items");


                    ArrayList<NoteItem> items = new ArrayList<NoteItem>();

                    for(NoteItem elem : array){
                        items.add(elem);
                    }

                    response.onResponse(items);

                }catch(Exception e){
                    Log.e("noteItem",e.toString());
                }
            }
        });
        thread.start();
    }


    public static void GetById(int id,GetByIdResponse response){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    WebRequest request =   new WebRequest(new URL(WebRequest.LOCALHOST + "/api/notes/" + id));
                    String resp = request.performGetRequest();

                    NoteItem item = new Gson().fromJson(resp,NoteItem.class);

                    response.onResponse(item);

                }catch(Exception e){
                    Log.e("noteItem",e.toString());
                }
            }
        });
        thread.start();

    }

    public void save(){
        //TODO: send the note to the database
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    if( id == 0 ){

                        WebRequest request =   new WebRequest(new URL(WebRequest.LOCALHOST + "/api/notes"));
                        String response = request.performPostRequest(NoteItem.this);

                        NoteItem responseItem = new Gson().fromJson(response,NoteItem.class);
                        id = responseItem.getId();
                    } else{

                        WebRequest request =   new WebRequest(new URL(WebRequest.LOCALHOST + "/api/notes/" + id));
                        request.performPostRequest(NoteItem.this);
                    }
                }catch(Exception e){
                    Log.e("noteItem",e.toString());
                }
            }
        });
        thread.start();

    }
    public void delete(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    WebRequest request =   new WebRequest(new URL(WebRequest.LOCALHOST + "/api/notes/" + id));
                    request.performDeleteRequest();
                }catch(Exception e){
                    Log.e("noteItem",e.toString());
                }
            }
        });
        thread.start();
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }
    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public Calendar getModifiedDate() {
        return modifiedDate;
    }
    public String getModifiedDateAsString() {
        return modifiedDate.get(Calendar.YEAR)+ "-" + modifiedDate.get(Calendar.MONTH) + "-" + modifiedDate.get(Calendar.DAY_OF_MONTH);
    }
    public String getCreationDateAsString() {
        return creationDate.get(Calendar.YEAR)+ "-" + creationDate.get(Calendar.MONTH) + "-" + creationDate.get(Calendar.DAY_OF_MONTH);
    }
    public void setModifiedDate(Calendar modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public interface GetByIdResponse{
        public void onResponse(NoteItem item);
    }
    public interface ListResponse{
        public void onResponse(ArrayList<NoteItem> items);
    }

}
