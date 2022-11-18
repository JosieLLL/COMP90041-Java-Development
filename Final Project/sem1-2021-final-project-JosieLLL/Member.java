/*
 * Student name: Jiayi Li
 * Student ID: 1097419
 * LMS username: jiayi9
 */

public class Member {

    private final int NO_VALUE = -1;

    private int id;
    private String name;
    private String address;

    /**
     * Constructor
     * @param id                    member ID
     * @param name                  member name
     * @param address               member address
     * @throws DataFormatException  If the above information is not in the correct format
     */
    public Member (String id, String name, String address) throws DataFormatException{
        setId(id);
        setName(name);
        setAddress(address);
    }



    /**
     * Setter - set member ID
     * @param id                    member ID
     * @throws DataFormatException  If the above information is not in the correct format
     */
    public void setId (String id) throws DataFormatException{
        try {
            if (!id.isEmpty()) {
                this.id = Integer.parseInt(id);
            } else {
                this.id = NO_VALUE; // -1 means there isn't a bill ID for this bill
            }
        } catch (NumberFormatException e) {
            throw new DataFormatException("Invalid member id! It must be a 6-digit number.");
        }
    }



    /**
     * Setter - set member name
     * @param name  member name
     */
    public void setName (String name){
        this.name = name;
    }



    /**
     * Setter - set member address
     * @param address   member address
     */
    public void setAddress(String address){
        this.address = address;
    }



    /**
     * Getter - get member ID
     * @return  member ID
     */
    public int getId(){
        return id;
    }



    /**
     * Getter - get member name
     * @return  member name
     */
    public String getName(){
        return name;
    }



    /**
     * Getter - get member address
     * @return  member address
     */
    public String getAddress(){
        return address;
    }



    /**
     * Getter - get representative number if the member has no member ID
     * @return  -1
     */
    public int getNoValue(){ return NO_VALUE;}



    /**
     * Make the member to String
     * @return
     */
    public String toString(){
        String[] member = {String.valueOf(id), name, address};
        return String.join(",", member);
    }

}
