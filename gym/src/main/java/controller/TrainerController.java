package controller;

import dto.TrainerDTO;
import entity.Trainer;
import service.IGymClassService;
import service.ITrainerService;
import service.impl.GymClassService;
import service.impl.TrainerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet (name = "TrainerController", value = "/trainer")
public class TrainerController extends HttpServlet {
    private static ITrainerService trainerService = new TrainerService();
    private static IGymClassService gymClassService = new GymClassService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                req.setAttribute("gymClasses", gymClassService.getAll());
                req.getRequestDispatcher("WEB-INF/view/trainer/create.jsp").forward(req, resp);
                break;
            default:
                List<TrainerDTO> trainers = trainerService.getAllDTO();
                req.setAttribute("trainers", trainers);
                req.getRequestDispatcher("WEB-INF/view/trainer/list.jsp").forward(req, resp);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                String name = req.getParameter("name");
                String phone = req.getParameter("phone");
                String specialization = req.getParameter("specialization");


                resp.sendRedirect("/trainer?message=created");
                break;
            case "update":
                break;

        }
    }



}
