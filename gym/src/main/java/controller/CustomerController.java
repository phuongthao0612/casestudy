package controller;

import dto.CustomerDTO;
import entity.Customer;
import entity.GymClass;
import service.ICustomerService;
import service.IGymClassService;
import service.impl.CustomerService;
import service.impl.GymClassService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerController", value = "/customer")
public class CustomerController extends HttpServlet {
    private static ICustomerService customerService = new CustomerService();
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
                req.getRequestDispatcher("WEB-INF/view/customer/create.jsp").forward(req, resp);
                break;
            case "update":
                showEditForm(req, resp);
                break;
            case "delete":
                deleteCustomer(req, resp);
                break;
            default:
                List<CustomerDTO> customers = customerService.getAllDTO();
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
            List<GymClass> gymClasses = gymClassService.getAll();
            req.setAttribute("gymClasses", gymClasses);

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

        // Kiểm tra nếu tham số "age" là null hoặc không phải là số hợp lệ
        int age = 0;
        String ageParam = req.getParameter("age");
        if (ageParam != null && !ageParam.isEmpty()) {
            try {
                age = Integer.parseInt(ageParam);  // Chuyển đổi thành số nguyên
            } catch (NumberFormatException e) {
                // Xử lý lỗi nếu không thể chuyển đổi "age" thành số
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Age must be a valid number");
                return;  // Dừng xử lý nếu có lỗi
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Age is required");
            return;  // Dừng xử lý nếu "age" là null hoặc rỗng
        }

        String phone = req.getParameter("phone");
        String email = req.getParameter("email");

        // Kiểm tra tham số "idClass" và xử lý ngoại lệ nếu không hợp lệ
        Integer idClass = null;
        String idClassParam = req.getParameter("idClass");
        if (idClassParam != null && !idClassParam.isEmpty()) {
            try {
                idClass = Integer.valueOf(idClassParam);  // Chuyển đổi thành số nguyên
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "idClass must be a valid number");
                return;  // Dừng xử lý nếu có lỗi
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "idClass is required");
            return;  // Dừng xử lý nếu "idClass" là null hoặc rỗng
        }

        // Tạo đối tượng Customer và thêm vào dịch vụ
        Customer customer = new Customer(name, age, phone, email, idClass);
        customerService.add(customer);

        // Chuyển hướng sau khi thêm thành công
        resp.sendRedirect("/customer?message=created");
    }

    private static void updateCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String updatedName = req.getParameter("updatedName");
        String updatedAge = req.getParameter("updatedAge");
        String updatedPhone = req.getParameter("updatedPhone");
        String updatedEmail = req.getParameter("updatedEmail");
        String updatedClassId = req.getParameter("updatedClassId");
        if (id == null || updatedName == null || updatedAge == null || updatedPhone == null || updatedEmail == null) {
            resp.sendRedirect("/customer");
            return;
        }
        Customer customer = customerService.getById(Integer.parseInt(id));
        if (customer != null) {
            customer.setName(updatedName);
            customer.setAge(Integer.parseInt(updatedAge));
            customer.setPhone(updatedPhone);
            customer.setEmail(updatedEmail);
            if (updatedClassId != null && !updatedClassId.isEmpty()) {
                customer.setIdClass(Integer.parseInt(updatedClassId));
            }
            customerService.update(customer);
            resp.sendRedirect("/customer?message=updated");
        } else {
            resp.sendRedirect("/customer");
        }
    }
}
