package mate.controller.cars;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.lib.Injector;
import mate.model.Car;
import mate.model.Manufacturer;
import mate.service.CarService;
import mate.service.CarServiceImpl;
import mate.service.ManufacturerService;
import mate.service.ManufacturerServiceImpl;

public class CreateCarsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate");
    private static final CarService carService =
            (CarService) injector.getInstance(CarServiceImpl.class);
    private static final ManufacturerService manufacturerService =
            (ManufacturerService) injector.getInstance(ManufacturerServiceImpl.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/cars/create.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Car car = new Car();
        car.setModel("model");
        Manufacturer manufacturer = manufacturerService
                .get(Long.valueOf(req.getParameter("manufacturer_id")));
        car.setManufacturer(manufacturer);
        carService.create(car);
        resp.sendRedirect(req.getContextPath() + "/cars");

    }
}