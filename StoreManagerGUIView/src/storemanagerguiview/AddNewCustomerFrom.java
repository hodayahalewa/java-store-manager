/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storemanagerguiview;

import controller.Backend_DAO_List;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import model.Customer;

/**
 *
 * @author User
 */
public class AddNewCustomerFrom extends JFrame{
   //יצירת אוביקטים
    private JButton jButtonOK;
    private JLabel jLabelID;
    private JLabel jLabelName;
    private JLabel jLabelAddress;
    private JTextField jTextFieldId;
    private JTextField jTextFieldName;
    private JTextField jTextFieldAddress;

    
    //ctor
    public AddNewCustomerFrom() 
    {
       //איתחול הנתונים
        this.jLabelID=new JLabel("ID");
        this.jLabelName=new JLabel("Name");
        this.jLabelAddress=new JLabel("Address");
        this.jButtonOK=new JButton("OK");
         
        this.jTextFieldId = new JTextField();
        this.jTextFieldName = new JTextField();
        this.jTextFieldAddress = new JTextField();
        
      //הוספת הרכיבים  לטופס
       super.getContentPane().add(this.jLabelID);
       super.getContentPane().add(this.jTextFieldId);

       super.getContentPane().add(this.jLabelName);
       super.getContentPane().add(this.jTextFieldName);

       super.getContentPane().add(this.jLabelAddress);
       super.getContentPane().add(this.jTextFieldAddress);
       
       super.getContentPane().add(this.jButtonOK);
       
       //קביעת גודל החלון
       super.setPreferredSize(new Dimension(500, 300));
       super.getContentPane().setLayout(new GridLayout(0,2,10,10));
       
       //סגירת הטופס ייסגר הטופס הנוכחי כבררת מחדל
       this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
       
       //סידור רכיבים באופן פורפורציונאלי
       this.pack();
       
        //אפשרות לכתיבה רק של מספרים ב id 
       jTextFieldId.addKeyListener(new KeyAdapter() {
           
            @Override
            public void keyTyped(KeyEvent e) {
                if(!Character.isDigit(e.getKeyChar())||jTextFieldId.getText().trim().length()>9)
                   e.consume();//ביטול האירוע 
            }
                      
       });
       //בדיקה שזה רק תווים לשם 
       jTextFieldName.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
               if(Character.isDigit(e.getKeyChar()))
                   e.consume();
            }
           
});
       
//       //בדיקה שזה רק תווים בכתובת
//       jTextFieldAddress.addKeyListener(new KeyAdapter() {
//
//            @Override
//            public void keyTyped(KeyEvent e) {
//               if(Character.isDigit(e.getKeyChar()))
//                   e.consume();
//            }
//           
//});
       
       
       
       //בעת לחיצה על
       //OK
       jButtonOK.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    //בדיקת הid 
                    //id 
                    if(jTextFieldId.getText().length()<9||jTextFieldId.getText().length()==0)
                          throw  new Exception("Id is not good, Enter a id");
                    //בדיקה של כתובת שהוכנס ערך והערך הוא לא מספר
                    if(jTextFieldAddress.getText().length()==0)
                        throw  new Exception("Addres is not good, Enter a address");
                    //בדיקה של השם שהוכנס ערך והערך הוא לא מספר
                    if(jTextFieldName.getText().length()==0)
                        throw  new Exception("Name is not good, Enter a name");
                
                    
                    //יצירת לקוח
                    model.Customer cus=new Customer();
                
                   //הכנסת הערכים
                   cus.setId(Long.parseLong(jTextFieldId.getText().trim()));
                   cus.setName(jTextFieldName.getText().trim());
                   cus.setAddress(jLabelAddress.getText().trim());
                
                  //הוספת לקוח למערך לקוחות
                  Backend_DAO_List.getBdl_singleton().AddCustomer(cus);
                
                  //הדפסת הודעה 
                  JOptionPane.showMessageDialog
                                  (AddNewCustomerFrom.this, 
                                  "Customer successfully added",
                                  "message", 
                                   JOptionPane.INFORMATION_MESSAGE);
                  //אם הצליח מרוקן את תיבות הטקסט כדי למלא מישהו חדש
                   jTextFieldId.setText(" ");
                    jTextFieldAddress.setText(" ");
                    jTextFieldName.setText(" ");
                }
                //שגיאות
                catch(Exception ex)
                {
                    //הדפסת הודעה לפי הערך שיתקבל
                    JOptionPane.showMessageDialog
                                  (AddNewCustomerFrom.this, 
                                   ex.getMessage(),
                                   "message", 
                                   JOptionPane.INFORMATION_MESSAGE);
                }
                
                      //ריקון תיבות טקסט
                    jTextFieldId.setText(" ");
                    jTextFieldAddress.setText(" ");
                    jTextFieldName.setText(" ");
            }
        });
       
  
    }

    
    


    
    
    
    


}
