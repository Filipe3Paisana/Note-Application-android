package pt.iade.ptiadefilipepaisanaatividade.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class NoteItem implements Serializable {
   int id;

    String title;
    String summary;
    String content;
    Calendar creationDate;
    Calendar modifiedDate;

    public NoteItem(){
        this(0,"","",new  GregorianCalendar(),new GregorianCalendar());
    }

    public static ArrayList<NoteItem> List(){
        //TODO: Replace this with a call to the database

        ArrayList<NoteItem> items = new ArrayList<NoteItem>();
        items.add(new NoteItem(1,"Note 1", "This is the first note", new GregorianCalendar(), new GregorianCalendar()));
        items.add(new NoteItem(2,"Note 2", "This is the second note", new GregorianCalendar(), new GregorianCalendar()));
        items.add(new NoteItem(3,"Note 3", "This is the third note", new GregorianCalendar(), new GregorianCalendar()));
        items.add(new NoteItem(4,"Note 4", "This is the fourth note", new GregorianCalendar(), new GregorianCalendar()));
        items.add(new NoteItem(5,"Note 5", "This is the fifth note", new GregorianCalendar(), new GregorianCalendar()));
        items.add(new NoteItem(6,"Note 6", "This is the sixth note", new GregorianCalendar(), new GregorianCalendar()));
        items.add(new NoteItem(7,"Note 7", "This is the seventh note", new GregorianCalendar(), new GregorianCalendar()));
        items.add(new NoteItem(8,"Note 8", "This is the eighth note", new GregorianCalendar(), new GregorianCalendar()));
        items.add(new NoteItem(9,"Note 9", "This is the ninth note", new GregorianCalendar(), new GregorianCalendar()));
        items.add(new NoteItem(10,"Note 10", "This is the tenth note", new GregorianCalendar(), new GregorianCalendar()));
        return items;
    }
    public static NoteItem GetById(int id){

         return new NoteItem(id,"Note "+id, "This is the note number "+id, new GregorianCalendar(), new GregorianCalendar());
    }

    public NoteItem(int id,String title, String content, Calendar creationDate, Calendar modifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.modifiedDate = modifiedDate;
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
                modifiedDate.get(Calendar.YEAR) + " " +
                modifiedDate.get(Calendar.HOUR_OF_DAY) + ":" +
                modifiedDate.get(Calendar.MINUTE);
    }
    public void setModifiedDate(Calendar modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
