/*
 * Student name: Jiayi Li
 * Student ID: 1097419
 * LMS username: jiayi9
 */

public class Bill {

    private final int NO_VALUE = -1;

    private int id;
    private int memberId;
    private float totalAmount;
    private boolean ifUsed;

    /**
     * Constructor
     * @param id                    bill ID
     * @param memberId              member ID
     * @param amount                bill amount
     * @param ifUsed                whether the bill has been used before
     * @throws DataFormatException  If the above information is not in the correct format
     */
    public Bill (String id, String memberId, String amount, String ifUsed)
            throws DataFormatException{
        setId(id);
        setMemberId(memberId);
        setTotalAmount(amount);
        setIfUsed(ifUsed);
    }



    /**
     * Setter - set bill ID
     * @param id                    bill ID
     * @throws DataFormatException  If the bill ID is not in the correct format
     */
    public void setId (String id) throws DataFormatException{
        try {
            if (!id.isEmpty()) {
                this.id = Integer.parseInt(id);
            } else {
                this.id = NO_VALUE; // -1 means there isn't a bill ID for this bill
            }
        } catch (NumberFormatException e) {
            throw new DataFormatException("Invalid bill id! It must be a 6-digit number.");
        }
    }



    /**
     * Setter - set member ID
     * @param memberId              member ID
     * @throws DataFormatException  If the member ID is not in the correct format
     */
    public void setMemberId (String memberId) throws DataFormatException{
        try {
            if (!memberId.isEmpty()){
                this.memberId = Integer.parseInt(memberId);
            } else {
                this.memberId = NO_VALUE; // -1 means there isn't a member ID for this bill
            }
        } catch (NumberFormatException e) {
            throw new DataFormatException ("Invalid member id! It must be a 6-digit number.");
        }
    }



    /**
     * Setter - set bill amount
     * @param amount                bill amount
     * @throws DataFormatException  If the bill amount is not in the correct format
     */
    public void setTotalAmount (String amount) throws DataFormatException {
        try {
            if (!amount.isEmpty()) {
                this.totalAmount = Float.parseFloat(amount);
            } else {
                this.totalAmount = NO_VALUE; // -1 means there isn't an amount for this bill
            }
        } catch (NumberFormatException e) {
            throw new DataFormatException("Invalid total amount! It must be a integer or decimal.");
        }
    }



    /**
     * Setter - set whether the bill has been used (String type)
     * @param ifUsed                true - used, false - not used
     * @throws DataFormatException  If the ifUsed is not in the correct format
     */
    public void setIfUsed (String ifUsed) throws DataFormatException{
        try {
            if (!ifUsed.isEmpty()) {
                this.ifUsed = Boolean.parseBoolean(ifUsed);
            } else {
                this.ifUsed = false; // if usage information is unknown, set it as false
            }
        } catch (NumberFormatException e) {
            throw new DataFormatException("Invalid usage information! It must be true/false.");
        }
    }



    /**
     * Setter - set whether the bill has been used (boolean type)
     * @param ifUsed                true - used, false - not used
     */
    public void setIfUsed(boolean ifUsed) { this.ifUsed = ifUsed; }



    /**
     * Getter - get bill ID
     * @return  bill ID
     */
    public int getId (){
        return id;
    }



    /**
     * Getter - get member ID
     * @return  member ID
     */
    public int getMemberId (){
        return memberId;
    }



    /**
     * Getter - get bill amount
     * @return  bill amount
     */
    public float getTotalAmount (){
        return totalAmount;
    }



    /**
     * Getter - get whether the bill has been used (String type)
     * @return  rue - used, false - not used
     */
    public boolean getIfUsed (){
        return ifUsed;
    }



    /**
     * Getter - get the representative number if the bill has no member ID
     * @return  -1
     */
    public int getNoValue(){ return NO_VALUE;}



    /**
     * Make the bill to String
     * @return  a string displaying the bill
     */
    public String toString(){
        String memberId = String.valueOf(this.memberId);

        if (memberId.equals(String.valueOf(NO_VALUE))){
            memberId = "";
        }
        String[] bill = {String.valueOf(id), memberId,
                            String.valueOf(totalAmount), String.valueOf(ifUsed)};
        return String.join(",", bill);
    }

}
