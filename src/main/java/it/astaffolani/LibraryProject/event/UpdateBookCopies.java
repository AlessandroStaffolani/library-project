package it.astaffolani.LibraryProject.event;

import it.astaffolani.LibraryProject.controller.BookControllerLocal;
import it.astaffolani.LibraryProject.entity.BookEntity;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.*;
import java.util.logging.Logger;

@MessageDriven(name = "ReservationTopic", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "topic/RESERVATIONTopic"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class UpdateBookCopies implements MessageListener {

    public static final String BOOK_ID_PROPERTY = "BOOK_ID";
    public static final String BOOK_N_RESERVATION_PROPERTY = "BOOK_N_RESERVATION";
    private static final Logger LOGGER = Logger.getLogger(UpdateBookCopies.class.toString());

    @EJB
    private BookControllerLocal bookController;

    @Override
    public void onMessage(Message msg) {
        UpdateBookEvent event = null;
        try {
            if (msg instanceof UpdateBookEvent) {
                event = (UpdateBookEvent) msg;
                long bookId = event.getLong(UpdateBookEvent.BOOK_ID_PROPERTY);
                int nBookReservations = event.getInt(UpdateBookEvent.N_BOOK_RESERVATION_PROPERTY);
                BookEntity book = bookController.findById(bookId);
                if (book != null) {
                    book.setnCopies(book.getnCopies() - nBookReservations);
                    bookController.update(book);
                    LOGGER.info("Book entity updated");
                } else {
                    LOGGER.warning("Book reserved doesn't exist");
                }
            } else {
                LOGGER.warning("Message of wrong type: " + msg.getClass().getName());
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
