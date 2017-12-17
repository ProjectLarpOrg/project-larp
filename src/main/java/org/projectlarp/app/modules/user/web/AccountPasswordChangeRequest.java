package org.projectlarp.app.modules.user.web;

/**
 * View Model object for storing the user's key and password.
 */
public class AccountPasswordChangeRequest {

    private String key;

    private String newPassword;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
