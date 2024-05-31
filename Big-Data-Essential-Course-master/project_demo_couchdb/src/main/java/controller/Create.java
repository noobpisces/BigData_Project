package controller;

import java.io.IOException;

import org.ektorp.CouchDbConnector;
import org.ektorp.UpdateConflictException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Person;
import utils.DBConnection;
import utils.TimestampsConvert;

@WebServlet(urlPatterns = { "/create" })
public class Create extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Create() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/Create.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ID = request.getParameter("ID");
		String Name = request.getParameter("inputString");
		String inputDate = request.getParameter("DoB");
		long timestamp = TimestampsConvert.dateToTimestamps(inputDate);
		String base64Image = request.getParameter("base64ImageInput");
		CouchDbConnector CouchDB = DBConnection.getDatabase();
		try {
			Person person = new Person();
			person.setId(ID);
			person.setName(Name);
			person.setDob(timestamp);
			person.setImage(base64Image);
			CouchDB.create(person);
			response.sendRedirect(request.getContextPath() + "/home");
		} catch (UpdateConflictException e) {
			request.setAttribute("errorString", "ID bị trùng");
//			System.out.println(e);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/Create.jsp");
			dispatcher.forward(request, response);
		}
	
	}
}
