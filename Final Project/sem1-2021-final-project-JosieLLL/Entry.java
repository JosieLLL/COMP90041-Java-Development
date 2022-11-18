/*
 * Student name: Jiayi Li
 * Student ID: 1097419
 * LMS username: jiayi9
 */

import java.io.Serializable;

public class Entry implements Comparable<Entry>, Serializable {

    private int entryId;
    private String billId;
    private String memberId;
    private int prize;
    private boolean ifWon;

    /**
     * Default Constructor
     */
    public Entry(){}



    /**
     * Constructor with entry ID
     * @param entryId   entry ID
     */
    public Entry(int entryId){
        this.entryId = entryId;
    }



    /**
     * Constructor with more information
     * @param entryId   entry ID
     * @param billId    bill ID
     * @param memberId  Member ID
     */
    public Entry(int entryId, String billId, String memberId){
        this.entryId = entryId;
        this.billId = billId;
        this.memberId = memberId;
    }



    /**
     * Setter - set entry ID
     * @param entryId   entry ID
     */
    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }



    /**
     * Setter - set bill ID
     * @param billId    billID
     */
    public void setbillId(String billId) {
        this.billId = billId;
    }



    /**
     * Setter - set member ID
     * @param memberId member ID
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }



    /**
     * Setter - set prize for this entry
     * @param prize prize
     */
    public void setPrize (int prize) { this.prize = prize; }



    /**
     * Setter - set whether this entry has won a prize
     * @param ifWon true - has won, false - has not won
     */
    public void setIfWon(boolean ifWon) { this.ifWon = ifWon; }



    /**
     * Getter - get entry ID
     * @return  entry ID
     */
    public int getEntryId() {
        return entryId;
    }



    /**
     * Getter - get bill ID
     * @return bill ID
     */
    public String getBillId() {
        return billId;
    }



    /**
     * Getter - get member ID
     * @return
     */
    public String getMemberId() {
        return memberId;
    }



    /**
     * Getter - get prize for this entry
     * @return  prize
     */
    public int getPrize () { return prize; }



    /**
     * Getter - get whether this entry has won a prize
     * @return  true - has won, false - has not won
     */
    public boolean getIfWon() { return ifWon; }



    /**
     * Printer for this entry
     */
    public void printEntryID(){
        System.out.printf("Entry ID: %-2d    \n", getEntryId());
    }



    /**
     * Comparator for this entry
     * @param o other entry
     * @return  positive number - greater than, negative number - less than, zero - equal to
     */
    @Override
    public int compareTo(Entry o) {
        if (getEntryId() > o.getEntryId()){
            return 1;
        } else if (getEntryId() < o.getEntryId()){
            return  -1;
        } else {
            return 0;
        }
    }

}
