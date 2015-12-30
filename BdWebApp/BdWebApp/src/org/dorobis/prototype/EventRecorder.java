package org.dorobis.prototype;

import java.io.IOException;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.persistence.PersistenceContext;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class EventRecorder
 */
@WebServlet(value="/EventRecorder")
public class EventRecorder extends HttpServlet {
	@PersistenceContext(unitName="org.dorobis.prototype")
	public EntityManager entityManager;
	
    @EJB(lookup="java:global/BdWebApp/EventServiceBean!org.dorobis.prototype.EventServiceRemote")
    EventServiceRemote remote;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventRecorder() {
        super();
        try{
            InitialContext ctx=new InitialContext();
            EntityManagerFactory factory = (EntityManagerFactory)ctx.lookup("java:/MyEntityManagerFactory");
          }
       catch(Exception e)
          {
            System.out.println("\nException: " + e);
            e.printStackTrace();
          }
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
//		entityManagerFactory = Persistence.createEntityManagerFactory( "org.dorobis.prototype" );
		//initialize servlet with configuration innformation suppied by app server
		super.init(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// create a couple of events...
		try {
			remote.addEvent(new Event( 
					request.getRemoteHost() 
						+ ":" + request.getRemoteUser() 
						+ ":" + request.getSession().getId(), 
					new Date() ));

			// now lets pull events from the database and list them
			List<Event> result = remote.getEvents();
			request.setAttribute("eventResults", result);
			getServletContext().getRequestDispatcher("/EventResults.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
