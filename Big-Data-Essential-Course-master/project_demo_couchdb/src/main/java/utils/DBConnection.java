package utils;

import java.net.MalformedURLException;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

public class DBConnection {

	public DBConnection() {
		// TODO Auto-generated constructor stub
	}

	public static CouchDbConnector getDatabase() throws MalformedURLException {
		String username = "admin";
		String password = "123";
		String str_conn = "http://" + username + ":" + password + "@localhost:5984/";
		HttpClient httpClient = new StdHttpClient.Builder().url(str_conn).build();
		CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
		CouchDbConnector db = new StdCouchDbConnector("demo_bigdata", dbInstance);
		db.createDatabaseIfNotExists();
		return db;
	}
}
