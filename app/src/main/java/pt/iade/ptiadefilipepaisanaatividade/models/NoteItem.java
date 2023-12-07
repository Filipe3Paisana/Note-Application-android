package pt.iade.ptiadefilipepaisanaatividade.models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

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

    public NoteItem(int id,String title, String content, Calendar creationDate, Calendar modifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.modifiedDate = modifiedDate;
        this.summary = content.substring(0, Math.min(content.length(), 80));
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
