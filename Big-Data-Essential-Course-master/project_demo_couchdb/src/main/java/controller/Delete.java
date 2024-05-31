package controller;

import java.io.IOException;

import org.ektorp.CouchDbConnector;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Person;
import utils.DBConnection;

@WebServlet(urlPatterns = { "/delete" })
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Delete() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CouchDbConnector CouchDB = DBConnection.getDatabase();
		String id = request.getParameter("id");
		Person person = CouchDB.find(Person.class, id);
		CouchDB.delete(person);
		response.sendRedirect(request.getContextPath() + "/home");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
