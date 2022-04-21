/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author fadib
 */
public class calendar {
      private int id;
    private String title;
    private Date start;
    private Date  end;
    private String description;
    private boolean all_day;
    private String background_color	;
    private String border_color;
    private String text_color;
   

    public calendar( String title,Date start,Date  end,String description,boolean all_day,String background_color ,String border_color, String text_color) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.all_day = all_day;
        this.background_color = background_color;
        this.border_color = border_color;
        this.text_color = text_color;
    
    }
    
    
    public calendar(int id,String title,Date start,Date  end,String description,boolean all_day,String background_color ,String border_color, String text_color) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.all_day = all_day;
        this.background_color = background_color;
        this.border_color = border_color;
        this.text_color = text_color;
       
    }

    
    public calendar(int id) {
        this.id = id;
    }
  

    public int getId() {
        return id;
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
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }
     public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public boolean getAllDay() {
        return all_day;
    }

    public void setAllDay(boolean all_day) {
        this.all_day = all_day;
    }
    public String getBackgroundColor() {
        return background_color;
    }

    public void setBackgroundColor(String background_color) {
        this.background_color = background_color;
    }
    public String getBorderColor() {
        return border_color;
    }

    public void setBorderColor(String border_color) {
        this.border_color = border_color;
    }
        public String getTextColor() {
        return text_color;
    }

    public void setTextColor(String text_color) {
        this.text_color = text_color;
    }
    
    
}
