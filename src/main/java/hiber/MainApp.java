package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Jon", "Malkovitch", "jon@mail.ru");
      Car car = new Car("Jeep", 123);
      user1.setCar(car);
      User user2 = new User("Jon2", "Malkovitch2", "jon2@mail.ru");
      Car car2 = new Car("Jeep2", 1234);
      user2.setCar(car2);
      User user3 = new User("Jon3", "Malkovitch3", "jon3@mail.ru");
      Car car3 = new Car("Jeep3", 12345);
      user3.setCar(car3);
      userService.add(user1);
      userService.add(user2);
      userService.add(user3);

      List<User> expUser = userService.getUserByCarModelAndSeries("Jeep2", 1234);
      for (User user: expUser) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("CarModel = " + user.getCar().getModel());
         context.close();
      }
   }
}
