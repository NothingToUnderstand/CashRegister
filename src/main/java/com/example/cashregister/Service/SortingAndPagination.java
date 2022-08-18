package com.example.cashregister.Service;

import com.example.cashregister.dao.MainDao;

import java.util.ArrayList;

public class SortingAndPagination {
    private final MainDao<?> dao;
    private String dir = "ASC";
    private  int page = 1;
    private  int perpage = 20;
    private  String column = "id";
    private int amountInDb;
    private int numberOfPages;

    public SortingAndPagination(MainDao<?> dao) {
        this.dao = dao;
    }

    public ArrayList<?> getList() {
        return this.dao.getAll(column, dir, setLimFrom(), perpage);
    }

    public void setParams(String column,String dir,String page,String perpage) {
        if(column!=null){
            this.column=column;
        }
        if(dir!=null){
            this.dir =dir;
        }
        if(page!=null){
            this.page = Integer.parseInt(page);
        }
        if(perpage!=null){
            this.perpage = Integer.parseInt(perpage);
        }
    }
    public int getAmount() {
        amountInDb=dao.countRows();
       return amountInDb;
    }
    public int getNumberOfPages() {
        numberOfPages=this.amountInDb/perpage;
       if(amountInDb%perpage!=0){
           numberOfPages++;
       }
       return numberOfPages;
    }

    public String getDir() {
        return dir;
    }

    public int getPage() {
        return page;
    }

    public int getPerpage() {
        return perpage;
    }

    public String getColumn() {
        return column;
    }

    private int setLimFrom(){
        return (page*perpage)-perpage;
    }
}
