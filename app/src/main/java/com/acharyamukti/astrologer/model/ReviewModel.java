package com.acharyamukti.astrologer.model;

public class ReviewModel {
    String review_id;
    String call_id;
    String name;
    String comment;
    String rating;
    String reply;
    String reply_date;
    String date;

    public ReviewModel(String review_id, String call_id, String name, String comment, String rating, String reply, String reply_date, String date) {
        this.review_id = review_id;
        this.call_id = call_id;
        this.name = name;
        this.comment = comment;
        this.rating = rating;
        this.reply = reply;
        this.reply_date = reply_date;
        this.date = date;
    }

    public String getReview_id() {
        return review_id;
    }

    public void setReview_id(String review_id) {
        this.review_id = review_id;
    }

    public String getCall_id() {
        return call_id;
    }

    public void setCall_id(String call_id) {
        this.call_id = call_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getReply_date() {
        return reply_date;
    }

    public void setReply_date(String reply_date) {
        this.reply_date = reply_date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}


//    public ReviewModel(String review_id, String call_id, String name, String comment, String rating, String reply, String reply_date, String date) {
//        this.review_id = review_id;
//        this.call_id = call_id;
//        this.name = name;
//        this.comment = comment;
//        this.rating = rating;
//        this.reply = reply;
//        this.reply_date = reply_date;
//        this.date = date;
//    }
//
//    public String getReply() {
//        return reply;
//    }
//
//    public void setReply(String reply) {
//        this.reply = reply;
//    }
//
//    public String getReply_date() {
//        return reply_date;
//    }
//
//    public void setReply_date(String reply_date) {
//        this.reply_date = reply_date;
//    }
//
//    public String getReview_id() {
//        return review_id;
//    }
//
//    public void setReview_id(String review_id) {
//        this.review_id = review_id;
//    }
//
//    public String getCall_id() {
//        return call_id;
//    }
//
//    public void setCall_id(String call_id) {
//        this.call_id = call_id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getComment() {
//        return comment;
//    }
//
//    public void setComment(String comment) {
//        this.comment = comment;
//    }
//
//    public String getRating() {
//        return rating;
//    }
//
//    public void setRating(String rating) {
//        this.rating = rating;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//}
