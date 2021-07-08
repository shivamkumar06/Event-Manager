package com.example.eventmanager;

public class model {
    String Event_Name,date,venue;

    public model() {
    }

    public model(String event_Name, String date, String venue) {
        Event_Name = event_Name;
        this.date = date;
        this.venue = venue;
    }

    public String getEvent_Name() {
        return Event_Name;
    }

    public void setEvent_Name(String event_Name) {
        Event_Name = event_Name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
}
