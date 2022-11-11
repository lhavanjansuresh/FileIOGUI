/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileioassignment;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Suresh Nathan
 */
public class FileIOGUI extends javax.swing.JFrame {

    String fName, lName, username, password, email;
    int a = 0;
    String[] usersArray = new String[25];
    File badPasswords = new File("badPasswords.txt");
    File usersFile = new File("Users.txt");

    /**
     * Creates new form FileIOGUI
     */
    public FileIOGUI() {
        initComponents();
        resetButton.setVisible(false);
        returnSignIn.setVisible(false);
        statusLabel.setText("");
        try {
            readFile();
        } catch (FileNotFoundException ex) {
        }
    }

    public void readFile() throws FileNotFoundException {
        Scanner input = new Scanner(usersFile);
        try {
            while (true) {
                String userAccInfo = input.nextLine();
                usersArray[a] = userAccInfo;
                a++;
            }
        } catch (NoSuchElementException ex) {
        }
        input.close();
    }

    public String encryptPass(String passIn) {
        String sb1 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); //setup for SHA-256 encryption
            //give the helper function the password
            md.update(passIn.getBytes());
            //perform the encryption
            byte byteData[] = md.digest();
            for (int i = 0; i < byteData.length; ++i) {
                sb1 += (Integer.toHexString((byteData[i] & 0xFF) | 0x100).substring(1, 3));
            }
        } catch (NoSuchAlgorithmException ex) {
        }
        return sb1;
    }

    public String typeVerfication(String first, String last, String user, String pass, String email) throws FileNotFoundException {
        String errorMsg = "";
        boolean cont = true;

        if (first.isEmpty() || last.isEmpty() || user.isEmpty() || pass.isEmpty() || email.isEmpty()) {
            errorMsg = "Please don't leave fields empty. ";
        } else if (user.contains(" ")) {
            errorMsg = "Username cannot include spaces. ";
        }

        for (int a = 0; first.length() > a; a++) {
            if (first.toUpperCase().charAt(a) >= 65 && first.toUpperCase().charAt(a) <= 90) {
                cont = true;
            } else {
                cont = false;
                a = first.length();
            }
        }
        if (cont == false) {
            errorMsg = errorMsg + "Name must only include alphabets. ";
        }
        for (int b = 0; last.length() > b; b++) {
            if (last.toUpperCase().charAt(b) >= 65 && last.toUpperCase().charAt(b) <= 90) {
                cont = true;
            } else {
                if (cont == true && errorMsg.isEmpty()) {
                    errorMsg = errorMsg + "Name must only include alphabets. ";
                }
                b = last.length();
            }
        }

        if (!email.contains("@") && !email.contains(".") || email.contains(" ")) {
            errorMsg = errorMsg + "Email is invalid. ";
        } else {

            if (email.substring(0, email.indexOf("@")).isEmpty() || email.charAt(email.indexOf("@") + 1) == '.') {
                errorMsg = errorMsg + "Email is invalid. ";
            }

            if (email.substring(email.indexOf("."), email.length() - 1).length() > 3
                    || email.substring(email.indexOf("."), email.length() - 1).length() < 2) {
                errorMsg = errorMsg + "Email is invalid. ";
            }
        }

        Scanner badpassTxt = new Scanner(badPasswords);
        try {
            while (true) {
                if (pass.length() < 4 && !errorMsg.contains("Please don't leave fields empty.")) { //if the password is less than 4 letters, and theres not already another signup error.
                    errorMsg = errorMsg + "Insecure password. ";
                } else if (badpassTxt.nextLine().equals(pass.toLowerCase())) { //if the bad passwords file contains their password, and theres not already another signup error.
                    errorMsg = errorMsg + "Insecure password. ";
                } else if (pass.contains(" ") && !errorMsg.contains("Insecure password.") && !errorMsg.contains("Password cannot include space.")) {
                    errorMsg = errorMsg + "Password cannot include space. ";
                }
            }
        } catch (NoSuchElementException ex) {
            badpassTxt.close();
        }

        return errorMsg;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        logUserText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        logPassText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        firstNameText = new javax.swing.JTextField();
        lastNameText = new javax.swing.JTextField();
        usernameText = new javax.swing.JTextField();
        passwordText = new javax.swing.JTextField();
        emailText = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        resetButton = new javax.swing.JButton();
        forgotPassButton = new javax.swing.JButton();
        returnSignIn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Sign-In:");

        jLabel2.setText("Username:");

        logUserText.setPreferredSize(new java.awt.Dimension(100, 26));

        jLabel3.setText("Password:");

        logPassText.setPreferredSize(new java.awt.Dimension(100, 26));
        logPassText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logPassTextActionPerformed(evt);
            }
        });

        jLabel4.setText("Sign-Up:");

        jLabel5.setText("First Name:");

        jLabel6.setText("Username:");

        jLabel7.setText("Last Name:");

        jLabel8.setText("Password:");

        jLabel9.setText("Email:");

        firstNameText.setPreferredSize(new java.awt.Dimension(100, 26));
        firstNameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstNameTextActionPerformed(evt);
            }
        });

        lastNameText.setPreferredSize(new java.awt.Dimension(100, 26));
        lastNameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastNameTextActionPerformed(evt);
            }
        });

        usernameText.setPreferredSize(new java.awt.Dimension(100, 26));
        usernameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextActionPerformed(evt);
            }
        });

        passwordText.setPreferredSize(new java.awt.Dimension(100, 26));
        passwordText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordTextActionPerformed(evt);
            }
        });

        emailText.setPreferredSize(new java.awt.Dimension(150, 26));
        emailText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTextActionPerformed(evt);
            }
        });

        jButton1.setText("Create Account");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Sign-In");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Close");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel10.setText("Lhavanjan's Virtual Log-In Machine");

        statusLabel.setText("jLabel11");

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        forgotPassButton.setText("Forgot Password");
        forgotPassButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forgotPassButtonActionPerformed(evt);
            }
        });

        returnSignIn.setText("Sign-In/Create Account");
        returnSignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnSignInActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(firstNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(usernameText, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emailText, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(228, 228, 228)
                                .addComponent(jButton2)
                                .addGap(37, 37, 37)
                                .addComponent(forgotPassButton))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(67, 67, 67)
                                    .addComponent(jButton1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(resetButton))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(25, 25, 25)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel8))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(passwordText, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(lastNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(statusLabel)))
                                    .addGap(33, 33, 33)))))
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel10))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(logUserText, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addGap(29, 29, 29)
                                .addComponent(logPassText, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(returnSignIn)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(34, 34, 34))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(logUserText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(logPassText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(forgotPassButton))
                .addGap(32, 32, 32)
                .addComponent(jLabel4)
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(firstNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusLabel))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(usernameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(emailText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(resetButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(returnSignIn))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logPassTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logPassTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logPassTextActionPerformed

    private void firstNameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstNameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstNameTextActionPerformed

    private void lastNameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastNameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameTextActionPerformed

    private void usernameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameTextActionPerformed

    private void passwordTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordTextActionPerformed

    private void emailTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTextActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        PrintWriter output;
        try {
            output = new PrintWriter(usersFile);
            String errorMsg = "";

            fName = firstNameText.getText().trim();
            lName = lastNameText.getText().trim();
            username = usernameText.getText().trim();
            password = passwordText.getText().trim();
            email = emailText.getText().trim();
            
            for (int x = 0; x < a; x++) {
            StringTokenizer info = new StringTokenizer(usersArray[x], ";");
            info.nextToken();
            info.nextToken();
            String savedUsername = info.nextToken();
            info.nextToken();
            String savedEmail = info.nextToken();

            if (email.equalsIgnoreCase(savedEmail)) {
                errorMsg = "Email already in use.";
                x = a;
               
            } else if (username.equalsIgnoreCase(savedUsername)){
                errorMsg = "Username already in use.";
                x = a;
            }
            else{
                errorMsg = typeVerfication(fName, lName, username, password, email);
            }
        }
    
            if (errorMsg.isEmpty()) {
                usersArray[a] = (fName + ";" + lName + ";" + username + ";" + encryptPass(password) + ";" + email);
                a++;
                output.flush();
                for (int b = 0; b < a; b++) {
                    output.print(usersArray[b] + "\n");
                }
                output.close(); //closes printwriter
                statusLabel.setForeground(Color.BLACK);
                statusLabel.setText("Account Created!");
                firstNameText.setText("");
                lastNameText.setText("");
                usernameText.setText("");
                passwordText.setText("");
                emailText.setText("");

            } else {
                statusLabel.setForeground(Color.RED);
                statusLabel.setText(errorMsg);
            }
        } catch (FileNotFoundException ex) {
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        //LOG IN BUTTON
        String userIn, passIn;
        boolean correct = true;

        userIn = logUserText.getText().trim();
        passIn = logPassText.getText().trim();
        passIn = encryptPass(passIn);

        for (int b = 0; b < a; b++) {
            StringTokenizer info = new StringTokenizer(usersArray[b], ";");
            info.nextToken();
            info.nextToken();
            String savedUsername = info.nextToken();
            String savedPassword = info.nextToken();

            if (userIn.equals(savedUsername) && passIn.equals(savedPassword)) {
                correct = true;
                b = a;
            } else {
                correct = false;
            }
        }
        if (correct == true) {
            statusLabel.setForeground(Color.BLACK);
            statusLabel.setText("Login Successful, " + userIn + "!");
            logUserText.setText("");
            logPassText.setText("");
        } else {
            statusLabel.setForeground(Color.RED);
            statusLabel.setText("Invalid!");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        // TODO add your handling code here:

        //Reset button
        String fIn, lIn, userIn, passIn, emailIn;
        boolean correct = true;
        
        PrintWriter output;
        
        try {
            output = new PrintWriter(usersFile);
        
            output.flush();

        fIn = firstNameText.getText().trim();
//        System.out.println(fIn);
        lIn = lastNameText.getText().trim();
//        System.out.println(lIn);
        userIn = usernameText.getText().trim();
//        System.out.println(userIn);
        passIn = passwordText.getText().trim();
//        System.out.println(passIn);
        emailIn = emailText.getText().trim();
//        System.out.println(emailIn);

        for (int b = 0; b < a; b++) {
            StringTokenizer info = new StringTokenizer(usersArray[b], ";");
            String name = info.nextToken();
            String last = info.nextToken();
            String user = info.nextToken();
            info.nextToken();
            String ema = info.nextToken();
            String errorMsg = "";

            try {
                errorMsg = typeVerfication(name, last, user, passIn, ema);
                
            } catch (FileNotFoundException ex) {

            }

            if (errorMsg.isEmpty()) {

                if (user.equals(userIn) && name.equals(fIn) && last.equals(lIn) && ema.equals(emailIn)) {
                    usersArray[b] = (name + ";" + last + ";" + user + ";" + encryptPass(passIn) + ";" + ema);
                    for (int x = 0; x < a; x++) {
                    output.print(usersArray[x] + "\n");
                }
                output.close(); //closes printwriter
                    b = a;
                    correct = true;
                } else {
                    correct = false;
                }
            } else {
                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Insecure password or password contains space.");
            }
        }
        if (correct == true) {
            statusLabel.setForeground(Color.BLACK);
            statusLabel.setText("Password has been reset, " + userIn + "!");
            jLabel8.setText("Password:");
            jLabel4.setText("Sign-Up:");
            jLabel1.setVisible(true);
            jLabel2.setVisible(true);
            logUserText.setVisible(true);
            logPassText.setVisible(true);
            jLabel3.setVisible(true);
            jButton2.setVisible(true);
            forgotPassButton.setVisible(true);
            jButton1.setVisible(true);
            returnSignIn.setVisible(false);
            resetButton.setVisible(false);
            firstNameText.setText("");
            lastNameText.setText("");
            usernameText.setText("");
            passwordText.setText("");
            emailText.setText("");
        } else {
            statusLabel.setForeground(Color.RED);
            statusLabel.setText("User not found/does not match!");
        }
        } catch (FileNotFoundException ex) {
        }

    }//GEN-LAST:event_resetButtonActionPerformed

    private void forgotPassButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forgotPassButtonActionPerformed
        // TODO add your handling code here:

        jLabel8.setText("New Password:");
        jLabel4.setText("Reset Password:");
        jLabel1.setVisible(false);
        jLabel2.setVisible(false);
        logUserText.setVisible(false);
        logPassText.setVisible(false);
        jLabel3.setVisible(false);
        jButton2.setVisible(false);
        forgotPassButton.setVisible(false);
        jButton1.setVisible(false);
        returnSignIn.setVisible(true);
        resetButton.setVisible(true);

    }//GEN-LAST:event_forgotPassButtonActionPerformed

    private void returnSignInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnSignInActionPerformed
        // TODO add your handling code here:

        //Goes back to signin/create account screen
        jLabel8.setText("Password:");
        jLabel4.setText("Sign-Up:");
        jLabel1.setVisible(true);
        jLabel2.setVisible(true);
        logUserText.setVisible(true);
        logPassText.setVisible(true);
        jLabel3.setVisible(true);
        jButton2.setVisible(true);
        forgotPassButton.setVisible(true);
        jButton1.setVisible(true);
        returnSignIn.setVisible(false);
        resetButton.setVisible(false);
    }//GEN-LAST:event_returnSignInActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FileIOGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FileIOGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FileIOGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FileIOGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FileIOGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField emailText;
    private javax.swing.JTextField firstNameText;
    private javax.swing.JButton forgotPassButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField lastNameText;
    private javax.swing.JTextField logPassText;
    private javax.swing.JTextField logUserText;
    private javax.swing.JTextField passwordText;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton returnSignIn;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JTextField usernameText;
    // End of variables declaration//GEN-END:variables
}
