package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Boris", "Petrov", "boris@mail.ru", new Car("VAZ", 21099)));
      userService.add(new User("Max", "Karpov", "max@mail.ru", new Car("Volvo", 33)));
      userService.add(new User("Anna", "Grigoryan", "anna@mail.ru", new Car("Mazda", 100)));
      userService.add(new User("Ivan", "Saenko", "ivan@mail.ru", new Car("VAZ", 21099)));

      List<User> users1 = userService.listUsers();
      for (User element : users1) {
         System.out.println("Id = " + element.getId());
         System.out.println("First Name = " + element.getFirstName());
         System.out.println("Last Name = " + element.getLastName());
         System.out.println("Email = " + element.getEmail());
         System.out.println("Car = " + element.getCar());
         System.out.println();
      }

      List<User> userList = userService.getUserByModelAndSeriesCar("VAZ", 21099);
      System.out.println(userList);

      context.close();
   }
}
