package edu.utsa.activitiesandviews;

import java.util.Date;
import java.util.ArrayList;

public class Journal {
    public static ArrayList<Journal> journalArrayList = new ArrayList<>();
    public static String JOURNAL_EDIT_EXTRA = "journalEdit";
    private int postId;
    private String title;
    private String description;
    private Date deleted;

    public Journal(int postId, String title, String description, Date deleted) {
        this.postId = postId;
        this.title = title;
        this.description = description;
        this.deleted = deleted;
    }

    public Journal(int postId, String title, String description) {
        this.postId = postId;
        this.title = title;
        this.description = description;
        deleted = null;
    }

    public static Journal getJournalForID(int passedJournalID) {
        for (Journal journal : journalArrayList) {
            if (journal.getId() == passedJournalID)
                return journal;
        }
        return null;
    }

    public static ArrayList<Journal> nonDeletedJournals() {
        ArrayList<Journal> nonDeleted = new ArrayList<>();
        for (Journal journal : journalArrayList) {
            if (journal.getDeleted() == null)
                nonDeleted.add(journal);
        }
        return nonDeleted;
    }

    public int getId() { return postId; }
    public void setId(int postId) { this.postId = postId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getDeleted() { return deleted; }
    public void setDeleted(Date deleted) { this.deleted = deleted; }
}