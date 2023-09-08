package edu.javacourse.register;

import edu.javacourse.register.rest.MarriageController;
import edu.javacourse.register.view.MarriageRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Starter
{
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"springContext.xml"} // загрузка классов в память для дальнейшего использования(при повторном вызове этой функции бины пересоздадутся)
        );

        //        MarriageController controller = context.getBean(MarriageController.class);
        MarriageController controller = context.getBean("controller", MarriageController.class); //'controller' чтобы если при вытаскивании бина потом мы указали бы дед.class , то именем конкретизировали бы нужный бин, который реализует нужный класс(если классов несколько, например еще MarriageController extends MarriageController)
        controller.findMarriageCertificate(new MarriageRequest());
    }
}
