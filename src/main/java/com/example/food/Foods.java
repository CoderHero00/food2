package com.example.food;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Foods {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @Size(min=7, message = "more than 7 character")
    private String name;
    private String type;
    private String des;
    private String url;
    @Min(1)
    private int price;
    private long selld;
    private long updated;
    private int status;

    public Foods() {
        this.updated = System.currentTimeMillis();
    }

    public Foods(String name, String type, String des, String url, int price, long selld, long updated, int status) {
        this.name = name;
        this.type = type;
        this.des = des;
        this.url = url;
        this.price = price;
        this.selld = System.currentTimeMillis();;
        this.updated = System.currentTimeMillis();;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getSelld() {
        return selld;
    }

    public void setSelld(long selld) {
        this.selld = selld;
    }
    public void setSelldC() {
        this.selld = System.currentTimeMillis();
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = System.currentTimeMillis();;
    }

    public int getStatus() {
        return status;
    }

    public String getDates(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.selld);
        calendar.add(Calendar.MONTH, 1);

        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        return Integer.toString(mDay)+"/"+Integer.toString(mMonth)+"/"+Integer.toString(mYear);
    }
    public String getUpdateDates(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.getUpdated());
        calendar.add(Calendar.MONTH, 1);

        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        return Integer.toString(mDay)+"/"+Integer.toString(mMonth)+"/"+Integer.toString(mYear);
    }
    public  String getStatusName(){
        String statusName = "";
        switch (this.status){
            case 1:
                statusName = "Selling";
                break;
            case -1:
                statusName = "Out of stock";
                break;

            case 0:
                statusName = "Deleted";
            default:
                break;
        }
        return statusName;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
