package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.ektorp.CouchDbConnector;
import org.ektorp.ViewQuery;
import org.ektorp.ViewResult;
import org.ektorp.ViewResult.Row;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Person;
import utils.DBConnection;

@WebServlet(urlPatterns = { "/home" })
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Home() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CouchDbConnector CouchDB = DBConnection.getDatabase();
		List<Person> listPerson = new ArrayList<>();
		
		ViewQuery query = new ViewQuery()
                .designDocId("_design/testDesign") 
                .viewName("testView"); 
		/*
		function (doc) {
  			emit(doc._id, doc);
		}
		*/
        ViewResult result = CouchDB.queryView(query);
        for (Row row : result.getRows()) {
            Person person = CouchDB.get(Person.class, row.getId()); // Ánh xạ từ CouchDB ID
            listPerson.add(person);
        }
        request.setAttribute("listPerson", listPerson);
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/Home.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
