/**
  * Copyright 2018 bejson.com 
  */
package com.phoenix.rxweather.data;

/**
 * Auto-generated: 2018-08-30 10:26:30
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Weather {

    private int id;
    private String main;
    private String description;
    private String icon;
    public void setId(int id) {
         this.id = id;
     }
     public int getId() {
         return id;
     }

    public void setMain(String main) {
         this.main = main;
     }
     public String getMain() {
         return main;
     }

    public void setDescription(String description) {
         this.description = description;
     }
     public String getDescription() {
         return description;
     }

    public void setIcon(String icon) {
         this.icon = icon;
     }
     public String getIcon() {
         return icon;
     }

}