package controller;

import entity.Customer;
import service.ICustomerService;
import service.impl.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerController", value = "/customer")
public class CustomerController extends HttpServlet {
    private static final ICustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                req.getRequestDispatcher("WEB-INF/view/customer/create.jsp").forward(req, resp);
                break;
            case "update":
                showEditForm(req, resp);
                break;
            case "delete":
                deleteCustomer(req, resp);
                break;
            default:
                List<Customer> customers = customerService.getAll();
                req.setAttribute("customers", customers);
                req.getRequestDispatcher("WEB-INF/view/customer/list.jsp").forward(req, resp);
                break;
        }
    }

    private static void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            Customer customerToUpdate = customerService.getById(Integer.parseInt(id));
            req.setAttribute("customer", customerToUpdate);
            req.getRequestDispatcher("WEB-INF/view/customer/update.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/customer");
        }
    }

    private static void deleteCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idToDelete = req.getParameter("id");
        if (idToDelete != null) {
            customerService.delete(Integer.parseInt(idToDelete));
            resp.sendRedirect("/customer?message=deleted");
        } else {
            resp.sendRedirect("/customer");
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
                insertCustomer(req, resp);
                break;
            case "update":
                updateCustomer(req, resp);
                break;
        }
    }

    private static void insertCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        int age = Integer.parseInt(req.getParameter("age"));
        String phone = req.getParameter("phone");

        Customer newCustomer = new Customer(name, age, phone, email);
        customerService.add(newCustomer);
        resp.sendRedirect("/customer?message=created");
    }

    private static void updateCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String updatedName = req.getParameter("updatedName");
        String updatedEmail = req.getParameter("updatedEmail");
        int updatedAge = Integer.parseInt(req.getParameter("updatedAge"));
        String updatedPhone = req.getParameter("updatedPhone");

        if (id != null) {
            Customer updatedCustomer = new Customer(Integer.parseInt(id), updatedName, updatedAge, updatedPhone, updatedEmail);
            customerService.update(updatedCustomer);
            resp.sendRedirect("/customer?message=updated");
        } else {
            resp.sendRedirect("/customer");
        }
    }
}
