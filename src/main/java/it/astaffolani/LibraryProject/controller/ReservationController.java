package it.astaffolani.LibraryProject.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.astaffolani.LibraryProject.data.BookRepositoryBean;
import it.astaffolani.LibraryProject.data.ReservationRepositoryBean;
import it.astaffolani.LibraryProject.data.UserRepositoryBean;
import it.astaffolani.LibraryProject.entity.BookEntity;
import it.astaffolani.LibraryProject.entity.ReservationEntity;
import it.astaffolani.LibraryProject.entity.UserEntity;
import it.astaffolani.LibraryProject.event.UpdateBookEvent;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.*;
import java.util.List;

/**
 * Definition of the two JMS destinations used by the quickstart
 * (one queue and one topic).
 */
@JMSDestinationDefinitions(
        value = {
                @JMSDestinationDefinition(
                        name = "java:/topic/RESERVATIONTopic",
                        interfaceName = "javax.jms.Topic",
                        destinationName = "ReservationTopic"
                )
        }
)

@Stateless
public class ReservationController implements ReservationControllerLocal {

    @EJB
    private BookRepositoryBean bookRepository;

    @EJB
    private UserRepositoryBean userRepository;

    @EJB
    private ReservationRepositoryBean reservationRepository;

    @Inject
    private JMSContext context;

    @Resource(lookup = "java:/topic/RESERVATIONTopic")
    private Topic topic;

    @Override
    public ReservationEntity insert(ReservationEntity reservation) {
        return reservationRepository.insert(reservation);
    }

    @Override
    public ReservationEntity insert(JsonNode jsonReservation) throws Exception {
        ObjectNode jsonObject = (ObjectNode) jsonReservation;
        ObjectMapper mapper = new ObjectMapper();
        JsonNode userNode = jsonObject.get("user");
        JsonNode bookNode = jsonObject.get("book");
        if (userNode == null) {
            throw new Exception("User field is required and should be an int value");
        }
        if (bookNode == null) {
            throw new Exception("Book field is required and should be an int value");
        }
        UserEntity userEntity = userRepository.findById(userNode.asLong());
        BookEntity bookEntity = bookRepository.findById(bookNode.asLong());
        jsonObject.replace("user", mapper.valueToTree(userEntity));
        jsonObject.replace("book", mapper.valueToTree(bookEntity));
        ReservationEntity reservationEntity = mapper.treeToValue(jsonObject, ReservationEntity.class);

        ReservationEntity insertedEntity = reservationRepository.insert(reservationEntity);
        sendMessage(insertedEntity.getBook().getId(), 1);
        return insertedEntity;
    }

    @Override
    public List<ReservationEntity> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public ReservationEntity findById(long id) {
        return reservationRepository.findById(id);
    }

    private void sendMessage(Long bookId, int nBookReservation) {
        TopicPublisher publisher = (TopicPublisher) context.createProducer();
        UpdateBookEvent event = new UpdateBookEvent();
        event.setBookId(bookId);
        event.setnBookReservation(nBookReservation);
        try {
            publisher.publish(topic, event);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
