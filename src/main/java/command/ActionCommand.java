package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Natallia_Khadunai on 8/24/2016.
 */
public interface ActionCommand {
    void execute(HttpServletRequest request, HttpServletResponse response);
}
