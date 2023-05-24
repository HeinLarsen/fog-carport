package dat.backend.model.services;

import dat.backend.model.entities.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Authentication
{
    public static boolean isRoleAllowed(int roleId, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null )
        {
            return user.getRoleId() == roleId;
        }
        return false;
    }
}
