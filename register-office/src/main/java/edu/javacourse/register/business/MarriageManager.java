package edu.javacourse.register.business;

import edu.javacourse.register.dao.MarriageDao;
import edu.javacourse.register.dao.PersonDao;
import edu.javacourse.register.domain.MarriageCertificate;
import edu.javacourse.register.view.MarriageRequest;
import edu.javacourse.register.view.MarriageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //тут говорим, что для спринга класс. (есть Component- если внутри еще что-то, Service- для чистого api, но по сути одну и ту же функцию выполняют)
public class MarriageManager
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MarriageManager.class);

    private MarriageDao marriageDao;

    //  @Autowired //(нужен, но я в конструкторе сделал) ищи бин нужный(через @Configuration или что в конфигурационном файле)
    private PersonDao personDao;
    @Autowired
    public void setMarriageDao(MarriageDao marriageDao) {    //  @Autowired             можно @Autowired приписывать к сеттеру, равноценно как к полю класса
                                                             //  private MarriageDao marriageDao;
        this.marriageDao = marriageDao;
    }

    //    @Autowired     // можно и не комментить этот  @Autowired
    public MarriageManager(PersonDao personDao) {
        this.personDao = personDao;
    }

    public MarriageResponse findMarriageCertificate(MarriageRequest request) {
        LOGGER.info("findMarriageCertificate called");
        MarriageCertificate cert = marriageDao.findMarriageCertificate(request);

        return new MarriageResponse();
    }

}
