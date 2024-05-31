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

@WebServlet(urlPatterns = { "/info" })
public class Info extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Info() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		CouchDbConnector CouchDB = DBConnection.getDatabase();
		Person person = CouchDB.find(Person.class, id);
		String date = TimestampsConvert.timestampsToDate(person.getDob(), "dd/MM/yyyy");
		request.setAttribute("date", date);
		request.setAttribute("person", person);
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/Info.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
