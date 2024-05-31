package controller;

import java.io.IOException;

import org.ektorp.CouchDbConnector;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Person;
import utils.DBConnection;
import utils.TimestampsConvert;

@WebServlet(urlPatterns = { "/edit" })
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Edit() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		CouchDbConnector CouchDB = DBConnection.getDatabase();
		Person person = CouchDB.find(Person.class, id);
		String date = TimestampsConvert.timestampsToDate(person.getDob(), "yyyy-MM-dd");
		request.setAttribute("date", date);
		request.setAttribute("person", person);
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/Edit.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CouchDbConnector CouchDB = DBConnection.getDatabase();
		String inputDate = request.getParameter("DoB");
		long timestamp = TimestampsConvert.dateToTimestamps(inputDate);
		String id = request.getParameter("id");
		Person person = CouchDB.find(Person.class, id);
		if (person != null) {
			person.setName(request.getParameter("inputString"));
			person.setDob(timestamp);
			person.setImage(request.getParameter("base64ImageInput"));

			CouchDB.update(person);
		}
		response.sendRedirect(request.getContextPath() + "/home");
	}
}
