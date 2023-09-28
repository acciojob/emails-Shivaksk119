package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character

        if(!oldPassword.equals(password)) { //if the oldPassword is equal to current password
            return;
        }
        if(newPassword.length()<8) { //newPassword It contains at least 8 characters
            return;
        }

        int UpperCaseCount = 0;
        int LowerCaseCount = 0;
        int digitCount = 0;
        int SplCharCount = 0;

        for(char ch:newPassword.toCharArray()) {
            if(ch>='a' && ch<='z') {
                LowerCaseCount++;
            }
            else if(ch>='A' && ch<='Z') {
                UpperCaseCount++;
            }
            else if(ch>='0' && ch<='9') {
                digitCount++;
            }
            else {
                SplCharCount++;
            }
        }

        if(UpperCaseCount>0 && LowerCaseCount>0 && digitCount>0 && SplCharCount>0) {
            this.password = newPassword;
        }
    }
}
