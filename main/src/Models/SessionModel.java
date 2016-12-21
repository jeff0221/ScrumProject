package Models;

/**
 * Created by B on 24-02-2016.
 */
public class SessionModel
{
    private User loggedInUser;
    private boolean isGuest;
    private static  SessionModel sessionUserModel = null;

    private SessionModel()
    {
        this.loggedInUser = null;
        isGuest = isGuest();
    }

    public static SessionModel getInstance()
    {
        if(sessionUserModel == null)
        {
            sessionUserModel = new SessionModel();
        }
        return sessionUserModel;
    }

    public void setUserSession(User usr)
    {
        loggedInUser = usr;
    }

    public User getLoggedInUser()
    {
        return loggedInUser;
    }

    public boolean isGuest()
    {
        if(getLoggedInUser() != null)
        {
            isGuest = false;
            System.out.println("False");
        }
        else
        {
            isGuest = true;
            System.out.println("True");
        }
        return isGuest;
    }
    public boolean getIsGuest()
    {
        return isGuest;
    }
}
