/*
import com.apirestescalante.model.User;
import com.apirestescalante.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.*;

@Component
public class MyRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Autowired
    private UserRepository repository;

    @Override
    public void run(String... args) throws Exception {
        LocalDateTime fecha = LocalDateTime.now();
        repository.deleteAll();
        repository.save(new User(9, "Eduardo", "Salvio", "edusalvio@gmail.com", "edusal22", fecha, "Resistencia", "Chaco", "Argentina"));
        repository.save(new User(11,"Juan","Delfino","jdelf@gmail.com","delf15",fecha,"Santa Fe","Santa Fe","Argentina"));
        repository.save(new User(12,"Carlos","Magno","carlo@gmail.com","carmag",fecha,"Wanda","Misiones","Argentina"));
        repository.save(new User(10,"Octavio","Lell","octalel@gmail.com","octalel33",fecha,"Santa Fe","Santa Fe","Argentina"));
        repository.findAll().forEach((city) -> {
            logger.info("{}", city);
        });
    }
}
*/