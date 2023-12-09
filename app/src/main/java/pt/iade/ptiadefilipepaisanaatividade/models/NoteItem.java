package pt.iade.ptiadefilipepaisanaatividade.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class NoteItem implements Serializable {
    private int id;

    private String title;
    private String summary;
    private  String content;
    private Calendar creationDate;
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

    public static ArrayList<NoteItem> List(){
        //TODO: Replace this with a call to the database

        ArrayList<NoteItem> items = new ArrayList<NoteItem>();

        items.add(new NoteItem(1,"Note 1", "This is the first note", new GregorianCalendar(), new GregorianCalendar()));
        items.add(new NoteItem(2,"Note 2", "This is the second note", new GregorianCalendar(), new GregorianCalendar()));
        items.add(new NoteItem(3,"Note 3", "This is the third note", new GregorianCalendar(), new GregorianCalendar()));
        return items;
    }


    public static NoteItem GetById(int id){

         return new NoteItem(id,"Note ", "This is the note number ", new GregorianCalendar(), new GregorianCalendar());
    }

    public void save(){
        //TODO: send the note to the database

        if( id == 0 ){

            id = new Random().nextInt(1000)+1;

        } else{

        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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
        return modifiedDate.get(Calendar.DAY_OF_MONTH) + "/" +
                modifiedDate.get(Calendar.MONTH) + "/" +
                modifiedDate.get(Calendar.YEAR) + " ";
    }
    public void setModifiedDate(Calendar modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

}
