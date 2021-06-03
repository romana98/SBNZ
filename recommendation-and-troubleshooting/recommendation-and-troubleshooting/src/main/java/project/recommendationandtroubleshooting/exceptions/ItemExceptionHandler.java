package project.recommendationandtroubleshooting.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.MessageFormat;

@ControllerAdvice
public class ItemExceptionHandler {
    private static final Logger logger = LogManager.getLogger(ItemExceptionHandler.class);

    @ExceptionHandler(UniqueConstraintException.class)
    public ResponseEntity<String> handleUniqueConstraintException(UniqueConstraintException exception) {
        logger.error(MessageFormat.format("[{0}:] {1}", UniqueConstraintException.class.getName(), exception.getMessage()));
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(InvalidIdException.class)
    public ResponseEntity<String> handleInvalidIdException(InvalidIdException exception) {
        logger.error(MessageFormat.format("[{0}:] {1}", InvalidIdException.class.getName(), exception.getMessage()));
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
