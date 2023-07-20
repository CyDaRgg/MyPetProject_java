package edu.javacourse.register.rest;

import org.springframework.stereotype.Service;

@Service
public class MarriageController2 extends MarriageController{  //'controller' чтобы если при вытаскивании бина потом мы указали бы дед.class , то именем конкретизировали бы нужный бин, который реализует нужный класс(если классов несколько, например еще MarriageController extends MarriageController)
}
