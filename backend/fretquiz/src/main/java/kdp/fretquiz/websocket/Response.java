package kdp.fretquiz.websocket;

import kdp.fretquiz.game.Game;
import kdp.fretquiz.user.User;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class Response {

    enum Type {
        BROADCAST("BROADCAST"),
        LOGIN_OK("LOGIN_OK"),
        LOGOUT_OK("LOGOUT_OK"),
        GET_GAMES("GET_GAMES"),
        GAME_CREATED("GAME_CREATED"),
        GUESS_RESPONSE("GUESS_RESPONSE");

        Type(String type) {}
    }

    public static Map<String, Object> broadcast(String message) {
        return Map.of(
                "type", Type.BROADCAST,
                "message", message
        );
    }

    public static Map<String, Object> loginOk(User user) {
        return Map.of(
                "type", Type.LOGIN_OK,
                "user", user
        );
    }

    public static Map<String, Object> logoutOk(User user) {
        return Map.of(
                "type", Type.LOGOUT_OK,
                "user", user
        );
    }

    public static Map<String, Object> gameCreated(Game game) {
        return Map.of(
                "type", Type.GAME_CREATED,
                "game", game
        );
    }

    public static Map<String, Object> getGames(Collection<Game> games) {
        return Map.of(
                "type", Type.GET_GAMES,
                "games", games
        );
    }

    public static Map<String, Object> guessResponse(Game.GuessResult result) {
        return Map.of(
                "type", Type.GUESS_RESPONSE,
                "guessResult", result
        );
    }
}
