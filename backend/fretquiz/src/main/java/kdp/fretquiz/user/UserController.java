package kdp.fretquiz.user;

import io.javalin.websocket.WsMessageContext;
import kdp.fretquiz.websocket.Request;
import kdp.fretquiz.websocket.Response;
import kdp.fretquiz.websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static kdp.fretquiz.App.userDao;

public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public static void login(WsMessageContext context) {
        var message = context.message(Request.Login.class);
        var name = message.name();

        var user = WebSocket.getUserFromContext(context)
                .withName(name);

        log.info("saving user: " + user);
        userDao.save(user);

        WebSocket.setUserAttributes(context, user);
        context.send(Response.loginOk(user));
    }
}
