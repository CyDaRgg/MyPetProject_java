package edu.javacourse.city.web;

import edu.javacourse.city.dao.PersonCheckDao;
import edu.javacourse.city.dao.PoolConnectionBuilder;
import edu.javacourse.city.domain.PersonRequest;
import edu.javacourse.city.domain.PersonResponse;
import edu.javacourse.city.exception.PersonCheckException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.time.LocalDate;

@Path("/check")
@Singleton//один раз создать и в памяти сервера держать класс
public class CheckPersonService
{
  private static final Logger logger= LoggerFactory.getLogger(CheckPersonService.class);

  private PersonCheckDao dao;

  @PostConstruct
  public void init()
  {
    logger.info("SERVLET is created");
    dao= new PersonCheckDao();
    dao.setConnectionBuilder(new PoolConnectionBuilder());
  }
//  @PreDestroy
//  public void destroy()
//  {
//    logger.info("FINISH");
//  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
    public PersonResponse checkPerson(PersonRequest request) throws PersonCheckException {
      logger.info(request.toString());
      return dao.checkPerson(request);
    }

}

// GET /ciyt-register-1.0/rest/check/101?name=value