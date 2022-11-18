/*
 * Student name: Jiayi Li
 * Student ID: 1097419
 * LMS username: jiayi9
 */

import java.io.*;
import java.util.ArrayList;

public class DataProvider {

    private final int MEMBER_ATTRIBUTES = 3;
    private final int BILL_ATTRIBUTES = 4;

    private ArrayList<Bill> Bills = new ArrayList<Bill>();
    private ArrayList<Member> Members = new ArrayList<Member>();
    private String billFileName;
    private String memberFileName;

    /**
     * Load member and bill data from specific files
     * @param memberFile            A path to the member file (e.g., members.csv)
     * @param billFile              A path to the bill file (e.g., bills.csv)
     * @throws DataAccessException  If a file cannot be opened/read
     * @throws DataFormatException  If the format of the the content is incorrect
     */
     public DataProvider(String memberFile, String billFile)
             throws DataAccessException, DataFormatException {

         BufferedReader readMemberInfo = null;
         BufferedReader readBillInfo = null;

         try {
             readMemberInfo = new BufferedReader(new FileReader(memberFile));
             readBillInfo = new BufferedReader(new FileReader(billFile));

             convertDataToObjects(readMemberInfo);
             convertDataToObjects(readBillInfo);

             readMemberInfo.close();
             readBillInfo.close();

             this.memberFileName = memberFile;
             this.billFileName = billFile;

         } catch (FileNotFoundException e){
             throw new DataAccessException("The file cannot be found/opened!");
         } catch (IOException e) {
             throw new DataFormatException("The file data is not in the correct format!");
         }
     }



    /**
     * Helper for DataProvider method
     * @param readData              BufferReader for reading data from the files
     * @throws IOException          If something wrong while reading the data
     * @throws DataFormatException  If the format of the the content is incorrect
     */
     private void convertDataToObjects(BufferedReader readData)
             throws IOException, DataFormatException {

         while (true){

             String line = readData.readLine();

             if (line != null){

                 String[] data = line.split(",");

                 if (data.length == BILL_ATTRIBUTES){
                     String billId = data[0];
                     String memberId = data[1];
                     String totalAmount = data[2];
                     String ifUsed = data[3];

                     try {
                         Bill bill = new Bill(billId, memberId, totalAmount, ifUsed);
                         Bills.add(bill);
                     } catch (DataFormatException e){
                         throw new IOException(e.getMessage());
                     }

                 } else if (data.length == MEMBER_ATTRIBUTES){
                     String memberId = data[0];
                     String memberName = data[1];
                     String memberAddress = data[2];

                     try {
                         Member member = new Member(memberId, memberName, memberAddress);
                         Members.add(member);
                     } catch (DataFormatException e){
                         throw new IOException(e.getMessage());
                     }

                 } else {
                     throw new DataFormatException("The file data is not in the correct format!");
                 }

             } else {
                 break;
             }

         }

     }



    /**
     * Getter - get a member from the member list
     * @param id    member ID
     * @return      a member instance
     */
     public Member getMember(int id){
         Member member = null;
         for (int i = 0; i < Members.size(); i++){
             if (id == Members.get(i).getId()){
                 member = Members.get(i);
             }
         }
         return member;
     }



    /**
     * Getter - get a bill from the bill list
     * @param id    bill ID
     * @return      a bill instance
     */
     public Bill getBill(int id){
         Bill bill = null;
         for (int i = 0; i < Bills.size(); i++){
             if (id == Bills.get(i).getId()){
                 bill = Bills.get(i);
             }
         }
         return bill;
     }



    /**
     * After the competition finish, update the bill file with the latest bill information
     * @throws DataAccessException  If a file cannot be opened/read
     */
     public void updateBill() throws DataAccessException{
        PrintWriter printWriter = null;
        try{
            printWriter = new PrintWriter(new FileOutputStream(billFileName));
            for (Bill bill: Bills){
                printWriter.println(bill);
            }
            printWriter.close();
        } catch (FileNotFoundException e){
            throw new DataAccessException("The bill file cannot be found/opened!");
        }
     }



}
