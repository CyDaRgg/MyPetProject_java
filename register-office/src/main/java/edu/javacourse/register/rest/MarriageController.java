package edu.javacourse.register.rest;

import edu.javacourse.register.business.MarriageManager;
import edu.javacourse.register.view.MarriageRequest;
import edu.javacourse.register.view.MarriageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("controller") //тут говорим, что для спринга класс. (есть Component- если внутри еще что-то, Service- для чистого api, но по сути одну и ту же функцию выполняют)
//'controller' чтобы если при вытаскивании бина потом мы указали бы дед.class , то именем конкретизировали бы нужный бин, который реализует нужный класс(если классов несколько, например еще MarriageController extends MarriageController)
public class MarriageController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MarriageController.class);

    @Autowired
    private MarriageManager marriageManager;


    public MarriageResponse findMarriageCertificate(MarriageRequest request) {
        LOGGER.info("findMarriageCertificate called");
        return marriageManager.findMarriageCertificate(request);
    }
}
