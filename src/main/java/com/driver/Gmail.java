package com.driver;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.Deque;

public class Gmail extends Email {

    //Mail Data Type
    public class Mail {
        Date date;
        String sender;
        String messeage;

        public Mail(Date date, String sender, String messeage) {
            this.date = date;
            this.sender = sender;
            this.messeage = messeage;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public String getMesseage() {
            return messeage;
        }

        public void setMesseage(String messeage) {
            this.messeage = messeage;
        }
    }

    int inboxCapacity; //maximum number of mails inbox can store

    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    Deque<Mail> Inbox;
    Deque<Mail> Trash;

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        Inbox = new ArrayDeque<>();
        Trash  = new ArrayDeque<>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        while(Inbox.size()>=inboxCapacity) {
            Mail oldestMail = Inbox.pollFirst();
            Trash.addLast(oldestMail);
        }
        Mail newMail = new Mail(date, sender, message);
        Inbox.addLast(newMail);
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(Mail currMail:Inbox) {
            if(currMail.getMesseage().equals(message)) {
                Inbox.remove(currMail);
                Trash.addLast(currMail);
                break;
            }
        }

    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(Inbox.size()>0) {
            return Inbox.peekLast().getMesseage();
        }
        return null;
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(Inbox.size()>0) {
            return Inbox.peekFirst().getMesseage();
        }
        return null;

    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count = 0;
        for(Mail currMail:Inbox) {
            if(currMail.getDate().compareTo(start)>=0 && currMail.getDate().compareTo(end)<=0) {
                count++;
            }
        }
        return count;

    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return Inbox.size();

    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return Trash.size();

    }

    public void emptyTrash(){
        // clear all mails in the trash
        while(!Trash.isEmpty()) {
            Trash.pop();
        }
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
