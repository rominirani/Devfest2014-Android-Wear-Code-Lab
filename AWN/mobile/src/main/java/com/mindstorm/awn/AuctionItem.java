package com.mindstorm.awn;

public class AuctionItem {
   private String ID;
   private String title;
   private String maxBidAmount;
   private String timeRemaining;
   private String bids;

    public AuctionItem(String ID, String title, String maxBidAmount, String timeRemaining, String bids) {
        this.ID = ID;
        this.title = title;
        this.maxBidAmount = maxBidAmount;
        this.timeRemaining = timeRemaining;
        this.bids = bids;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMaxBidAmount() {
        return maxBidAmount;
    }

    public void setMaxBidAmount(String maxBidAmount) {
        this.maxBidAmount = maxBidAmount;
    }

    public String getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(String timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public String getBids() {
        return bids;
    }

    public void setBids(String bids) {
        this.bids = bids;
    }

    @Override
    public String toString() {
        return title + '\n' +
                "Current Bid Rs " + maxBidAmount + '\n' +
                "Ends in " + timeRemaining + '\n' +
                "Bids " + bids;
    }
}
