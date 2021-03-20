package kdp.fretquiz.websocket;

import io.javalin.websocket.WsCloseContext;
import io.javalin.websocket.WsContext;
import io.javalin.websocket.WsMessageContext;
import kdp.fretquiz.game.GameController;
import kdp.fretquiz.user.User;
import kdp.fretquiz.user.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import static kdp.fretquiz.App.gameDao;
import static kdp.fretquiz.App.userDao;

public class WebSocket {
    private static final Logger log = LoggerFactory.getLogger(WebSocket.class);
    private static final List<WsContext> contexts = new ArrayList<>();

    public static void onConnect(WsContext context) {
        contexts.add(context);

        var sessionId = context.getSessionId();
        log.info("ws connection: " + sessionId);

        var user = new User(sessionId);
        log.info("saving user: " + user);
        userDao.save(user);

        setUserAttributes(context, user);
        context.send(Response.loginOk(user));

        var gameIds = gameDao.getAllIds();
        context.send(Response.getGameIds(gameIds));
    }

    public static void onMessage(WsMessageContext context) {
        Request message = context.message(Request.Default.class);

        log.info("message type: " + message.type());
        switch (message.type()) {
            case LOGIN -> UserController.login(context);
            case LOGOUT -> {}
            case GET_GAME_IDS -> GameController.getAllIds(context);
            case CREATE_GAME -> GameController.createGame(context);
            case JOIN_GAME -> GameController.joinGame(context);
            case GUESS -> GameController.handleGuess(context);
        }
    }

    public static void onClose(WsCloseContext context) {
        contexts.remove(context);
    }

    /**
     * Send a Response to each connected user.
     */
    public static void broadcast(Response response) {
        contexts.forEach(context -> {
            context.send(response);
        });
    }

    /**
     * Store the user's info as Jetty session attributes.
     */
    public static void setUserAttributes(WsContext context, User user) {
        context.attribute("userId", user.id());
        context.attribute("userName", user.name());
    }

    /**
     * Get's a user info from the Jetty context.
     * Assumes that there is a "userId" attribute already set (which should be done on connect).
     */
    public static User getUserFromContext(WsContext context) {
        var userId = Objects.requireNonNull(context.attribute("userId")).toString();

        return userDao.getUserById(userId)
                .orElseThrow(NoSuchElementException::new);
    }
}
